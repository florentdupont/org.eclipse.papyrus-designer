package org.eclipse.papyrus.codegen.extensionpoints;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;

public interface ILangCodegen2 extends ILangCodegen {

	/**
	 * Provide a user friendly description of the generator
	 */
	public String getDescription();

	/**
	 * Some code generators use a non-trivial mapping from behaviors to methods
	 * in the generated code or add methods that are not part of a existing
	 * behavior in the model. This is a problem for code synchronization (update
	 * of model from code) as done for instance with the CDT editor integration.
	 * 
	 * @param methodName
	 *            the name of the method as in the code
	 * @param body
	 *            the body
	 * @return the associated synchronization information. Null indicates that a
	 *         default mapping is used
	 */
	public SyncInformation getSyncInformation(String methodName, String body);

	/**
	 * Return true, if the generator is eligible for a certain element within a
	 * model. The code generator may check the element itself, but also for
	 * instance whether a certain profile (such as UML-RT) has been applied.
	 * Generators are allowed to return true for all models, if they do not have
	 * specific (profile related) requirements.
	 * 
	 * @param modelRoot
	 *            the root of a UML model (for which code should be generated)
	 * @return true, iff the generator is eligible for this model
	 */
	public boolean isEligible(Element modelElement);

	/**
	 * Return the suffix of a code generator. Some languages use different
	 * suffixes depending on the file kind, e.g. C distinguishes between HEADER
	 * and BODY languages. Other languages can ignore this parameter.
	 * 
	 * @param FILE_KIND
	 *            The file kind
	 * @return
	 */
	public String getSuffix(FILE_KIND fileKind);

	/**
	 * return additional information about the method that has been generated
	 * for a UML behavior. This information is used by the CDT editor
	 * integration to locate the method in the code.
	 */
	public MethodInfo getMethodInfo(NamedElement operationOrBehavior);
}
