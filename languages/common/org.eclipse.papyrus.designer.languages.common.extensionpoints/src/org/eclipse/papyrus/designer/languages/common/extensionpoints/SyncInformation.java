package org.eclipse.papyrus.designer.languages.common.extensionpoints;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Behavior;

/**
 * Some code generators map behaviors in a non-trivial way or add methods that have not been generated.
 * The following information allows (generic) synchronization code, as within the CDT editor to
 * make a useful update of the source model. 
 */
public class SyncInformation {
	/**
	 * true, iff the method has been added by the generator and does not need synchronization.
	 */
	public boolean isGenerated;
	
	/**
	 * the associated behavior within the source model, in case of a non-trivial method.
	 * If the element does not exist yet in the source model, it may be null.
	 */
	public Behavior behavior;
	
	/*
	 * The qualified name of a behavior to create in the model. This is used, if there should be an associated behavior in the
     * model, but it does not exist yet. Only one of the two attributes (behavior and createBehaviorName) must be set.
     */
	public String createBehaviorName;
	
	/**
	 * the element for which the CDT editor has been opened. This may for instance be the class that is edited.
	 */
	public EObject editedElement;
	
	/**
	 * The method body gets filtered during default synchronization (text between generation markers is removed), but the filtered information might be
	 * important for customized back-synchronization.
	 */
	public String unfilteredBody;
}