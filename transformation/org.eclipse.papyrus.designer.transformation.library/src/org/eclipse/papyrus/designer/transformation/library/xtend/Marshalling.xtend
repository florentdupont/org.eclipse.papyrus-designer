package org.eclipse.papyrus.designer.transformation.library.xtend

import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Class
import static extension org.eclipse.papyrus.designer.transformation.base.utils.OperationUtil.*
import static extension org.eclipse.papyrus.designer.transformation.library.xtend.CppUtils.cppType
import org.eclipse.uml2.uml.Parameter

class Marshalling {
	def static marshall(Operation operation) '''
		// now marshall in and inout parameters via ASN.1
		«FOR parameter : operation.parametersInInout»
			«parameter.marshall»
		«ENDFOR»
	'''

	def static marshallOutInout(Operation operation) '''
		// now marshall out and inout parameters via ASN.1
		«FOR parameter : operation.parametersOutInout»
			«parameter.marshall»
		«ENDFOR»
	'''

	def static marshall(Parameter parameter) '''
		{
			«parameter.type.cppType» varName_ASN = «parameter.name»;
			BEncAsnContent (&pBuffer, &varName_ASN);
		}
	'''
	
	def static unmarshall(Operation operation) '''
		«FOR parameter : operation.parametersInInout.reverse»
			«parameter.unmarshall»
		«ENDFOR»
	'''
	
	def static unmarshallOutInout(Operation operation) '''
		«FOR parameter : operation.parametersOutInout.reverse»
			«parameter.unmarshall»
		«ENDFOR»
	'''

	def static unmarshall(Parameter parameter) '''
		«parameter.type.cppType» «parameter.name»
		{
			«parameter.type.cppType» varName_ASN;
			BDecAsnContent (&pBuffer, &varName_ASN);
			«parameter.name» = varName_ASN;
		}
	'''	

	/**
	 * Used in a union that contains a struct for each operation. This allows for
	 * a parameter marshalling in which ... 
	 */
	def static cppParameterStorage(Class clazz) '''
		«FOR operation : clazz.ownedOperations»
			struct Op_«operation.name» {
				«FOR parameter : operation.ownedParameters»
					«parameter.type.cppType» «parameter.name»;
				«ENDFOR»
			} op_«operation.name»;
		«ENDFOR»
	'''
}