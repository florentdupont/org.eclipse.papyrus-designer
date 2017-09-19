package org.eclipse.papyrus.designer.transformation.library.xtend

import org.eclipse.uml2.uml.NamedElement
import org.eclipse.uml2.uml.Operation
import org.eclipse.uml2.uml.Parameter
import org.eclipse.uml2.uml.ParameterDirectionKind
import org.eclipse.uml2.uml.Type
import static extension org.eclipse.papyrus.designer.transformation.base.utils.OperationUtils.parametersNonRet
import static extension org.eclipse.papyrus.designer.languages.common.base.ElementUtils.usedNamespaces
import static extension org.eclipse.papyrus.designer.languages.common.base.ElementUtils.dereferenceTypedef
import org.eclipse.papyrus.designer.transformation.extensions.ITextTemplate
import org.eclipse.uml2.uml.PrimitiveType
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Typedef
import org.eclipse.uml2.uml.util.UMLUtil
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.uml2.uml.Namespace
import org.eclipse.uml2.uml.Model
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.External
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.NoCodeGen

class CppUtils implements ITextTemplate {

	/**
	 * create the C++ signature for an operation (including parenthesis)
	 */
	public static def cppSignature(Operation operation) '''
		«operation.name»(«FOR parameter : operation.ownedParameters SEPARATOR(', ')»
			«parameter.cppParameter»
		«ENDFOR»
	'''


	/**
	 * make a C++ call, pass all parameters except the return parameter
	 */
	 public static def cppCall(Operation operation) '''
		«operation.name»(«FOR parameter : operation.parametersNonRet SEPARATOR(', ')»
			«parameter.name»
		«ENDFOR»)
	'''

	/**
	 *
	 * @param ne
	 * @return
	 */
	public static def cppQName(NamedElement ne) {
		if (StereotypeUtil.isApplied(ne, External) || StereotypeUtil.isApplied(ne, NoCodeGen)) {
			return ne.name
		}
		else {
			var qName = ne.name;
			for (Namespace ns : ne.allNamespaces()) {
				if (!(ns instanceof Model)) {
					qName = '''«ns.name»::«qName»'''
				}
			}
			return qName
		}
	}
	
	/**
	 *
	 * @param type
	 *            a type
	 * @return return the definition of a typedef, if the type has been defined via
	 *         the stereotype CppType of the Cpp profile
	 */
	public static def dereferenceTypedef(Type type) {
		if (type instanceof PrimitiveType) {
			val cppType = UMLUtil.getStereotypeApplication(type, Typedef)
			if (cppType !== null) {
				cppType.definition
			}
		}
		return type.qualifiedName

	}
	
	/**
	 * make a C++ call, pass all parameters except the return parameter, prefix with "return",
	 * if there is a return type in the operations declaration
	 */
	public static def returnCppCall(Operation operation) '''
		«IF (operation.type != null)»return «ENDIF»«operation.cppCall»
	'''
	
	public static def cppParameter(Parameter parameter) '''
		«parameter.type»«IF (parameter.direction == ParameterDirectionKind.OUT)»_out«ENDIF» «parameter.name»
	'''

	public static def cppType(Type type) '''
		«IF (type.qualifiedName == 'UMLPrimitiveTypes::Boolean')»
			bool
		«ELSEIF (type.qualifiedName == 'UMLPrimitiveTypes::Integer')»
			int
		«ELSE»
			«dereferenceTypedef(type)»
		«ENDIF»
	'''


	public static def cppRetType(Operation operation) '''
		«IF (operation.type === null)»
			void
		«ELSE»
			«operation.type.cppType»
		«ENDIF»
	'''

	/**
	 * Open a set of C++ namespaces associated with the packages of of the passed named element
	 * TODO: use indentTab? => requires making this script recursive
	 * Need to include referenced types (assuming a naming convention?
	 */
	public static def openNamespace(NamedElement namedElement) '''
		«FOR ns : namedElement.usedNamespaces.reverse»
			namespace «ns.name»
		«ENDFOR»
	'''

	/**
	 * Close a set of C++ namespaces associated with the packages of of the passed named element
	 */
	public static def closeNamespace(NamedElement namedElement) '''
		«FOR ns : namedElement.usedNamespaces»
			}; // of namespace [ns.name/]
		«ENDFOR»
	'''
}