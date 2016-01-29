package org.eclipse.papyrus.qompass.modellibs.tracing.acceleo

import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.Parameter
import org.eclipse.uml2.uml.DataType
import org.eclipse.uml2.uml.Type
import org.eclipse.uml2.uml.Operation

import static extension org.eclipse.papyrus.qompass.modellibs.tracing.acceleo.TraceUtils.*
import static extension org.eclipse.papyrus.qompass.designer.core.UMLTool.*
import static extension org.eclipse.papyrus.qompass.modellibs.core.xtend.CppUtils.*
import org.eclipse.papyrus.qompass.designer.core.transformations.TransformationContext
import org.eclipse.papyrus.qompass.designer.core.extensions.IXtend
import org.eclipse.uml2.uml.PrimitiveType

class TraceLTTng implements IXtend {

	public def declareTP(Classifier cl) '''
		#if !defined(HFILENAME_H) || defined(TRACEPOINT_HEADER_MULTI_READ)
		#define HFILENAME_H

		#ifdef __cplusplus
		extern "C" {
		#endif

		#include <lttng/tracepoint.h>
		«FOR operation : cl.getAllOperations()»
			«IF TraceUtils.hasTrace(operation)»
				«declareTPop(tpName1(), operation)»
			«ENDIF»
		«ENDFOR»

		#undef TRACEPOINT_PROVIDER
		#define TRACEPOINT_PROVIDER «tpName1»

		#undef TRACEPOINT_INCLUDE_FILE
		#define TRACEPOINT_INCLUDE_FILE «TransformationContext.classifier.getQualifiedName().replaceAll('::', '/')».h

		#include <lttng/tracepoint-event.h>

		#ifdef __cplusplus
		}
		#endif

		#endif
	'''

	public def declareEMF_URI(Classifier cl) '''
		#include <lttng/tracepoint.h>

		TRACEPOINT_MODEL_EMF_URI(UMLmodel, starting, "«TransformationContext.sourceRoot.getURI»")
	'''

	public def declareTPop(String tpName, Operation operation) '''
		#include <stdint.h>

		// declare trace point
		TRACEPOINT_EVENT(«tpName1()», «operation.tpName2»,
			TP_ARGS(const char *, instanceName«IF (operation.parametersInInout.size > 0)», «ENDIF»«FOR parameter : operation.parametersInInout SEPARATOR(', ')»«parameter.flattenParNameAndType»«ENDFOR»),
			TP_FIELDS(
				ctf_string(instanceName, instanceName)
				«FOR parameter : operation.parametersInInout»
					«TransformationContext.classifier.declareDependency(parameter.type)»«flattenCtfType(parameter.type, parameter.name)»
				«ENDFOR»
			)
		)
		TRACEPOINT_MODEL_EMF_URI(«tpName1», «operation.tpName2», "«operation.modelRef»")
	'''

	public def declareTracepointEventCreateClass(Classifier clazz) '''
		TRACEPOINT_EVENT(«tpName1», createClass, «clazz.qualifiedName»)
	'''
	
	public def declareTracepointEventDestroyClass(Classifier clazz) '''
		TRACEPOINT_EVENT(«tpName1», destroyClass, «clazz.qualifiedName»)
	'''
	
	public def declareCreateDestroy(String tpName) '''
		TRACEPOINT_EVENT(«tpName», createClass,
			TP_ARGS(const char *, classURI),
			TP_FIELDS(
				ctf_string(classURI, classURI)
			)
		)
		TRACEPOINT_EVENT(«tpName», destroyClass,
			TP_ARGS(const char *, className),
			TP_FIELDS(
				ctf_string(xmdID, className)
			)
		)
	'''

	/**
	 * The name that is used for the trace provider
	 */
	def tpName1() {
		TransformationContext.binding.getNearestPackage.qualifiedName.varName
	}


	/**
	 * The name that is used for the type
	 */
	def tpName2(Operation operation) {
		operation.name
	}

	/**
	 * Reference either the port (if available in the context) or the operation that is traced
	 */
	def modelRef(Operation operation) {
		val uri = TransformationContext.sourceRoot.eResource.URI
		if (TransformationContext.port == null) {
			uri + "#" + operation.xmlID()
		}
		else {
			uri + "#" + TransformationContext.port.xmlID()	
		}
	}
	
	public def declareTraceOp(Operation operation) {
	}

	public def invokeTP(Operation operation) '''
		// create event with operationID/portID and pass call
		«IF (operation.hasTrace)»
			// use (call) tracepoint
			tracepoint(«tpName1()», «operation.tpName2()», instanceName«IF (operation.parametersInInout.size > 0)», «ENDIF»«FOR parameter : operation.parametersInInout() SEPARATOR(', ')»«parameter.flattenParName.toString.trim»«ENDFOR»);
		«ENDIF»
		«IF (operation.type != null)»return «ENDIF»rconn->«operation.cppCall»;
	'''
	

	def flattenParNameAndType(Parameter parameter) '''
		«IF (parameter.type instanceof DataType) && !(parameter.type instanceof PrimitiveType)»
			«FOR attribute : (parameter.type as DataType).attributes SEPARATOR(', ')»«attribute.type.cppTypeWB.toString.trim», «parameter.name + '_' + attribute.name»«ENDFOR»
		«ELSE»
			«parameter.type.cppTypeWB.toString.trim», «parameter.name»
		«ENDIF»
	'''
	
	def flattenParName(Parameter parameter) '''
		«IF (parameter.type instanceof DataType) && !(parameter.type instanceof PrimitiveType)»
			«FOR attribute : (parameter.type as DataType).attributes SEPARATOR(', ')»«parameter.name».«attribute.name»«ENDFOR»
		«ELSE»
			«parameter.name»
		«ENDIF»
	'''
	

	def flattenCtfType(Type type, String name) '''
		«IF (type instanceof DataType) && !(type instanceof PrimitiveType)»
			«FOR attribute : (type as DataType).attributes SEPARATOR('\n')»
				«ctfType(attribute.type, name + '_' + attribute.name)»
			«ENDFOR»
		«ELSE»
			«ctfType(type, name)»
		«ENDIF»
	'''


	/**
	 * Support for C++ types with boolean
	 */
	def cppTypeWB(Type type) '''
		«IF
			(type.qualifiedName == 'UMLPrimitiveTypes::Boolean') ||
			(type.qualifiedName == 'PrimitiveTypes::Boolean') ||
			(type.qualifiedName == 'CORBA::Boolean')»
			/* bool */ unsigned char
		«ELSE»
			«type.cppType»
		«ENDIF»
	'''


	/**
	 * Calculate the typename, defaulting to ctf_integer
	 * TODO: treat pointer & ref stereotypes (can only evaulate on parameter or attribute, not on Type)
	 *
	 */
	def ctfType(Type type, String name) '''
		«IF
			(type.qualifiedName == 'CORBA::String') ||
			(type.qualifiedName == 'PrimitiveTypes::String') ||
			(type.qualifiedName == 'UMLPrimitiveTypes::String')»
			ctf_string(«name», «name»)
		«ELSEIF
			(type.qualifiedName == 'CORBA::Float') ||
			(type.qualifiedName == 'CORBA::Double') ||
			(type.qualifiedName == 'AnsiCLibrary::float') ||
			(type.qualifiedName == 'AnsiCLibrary::double')»
			ctf_float(«type.cppType», «name», «name»)
		«ELSE»
			ctf_integer(«type.cppTypeWB», «name», «name»)
		«ENDIF»
	'''	
}