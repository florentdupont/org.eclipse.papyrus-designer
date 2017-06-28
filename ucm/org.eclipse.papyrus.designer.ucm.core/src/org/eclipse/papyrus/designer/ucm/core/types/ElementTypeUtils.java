/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - initial API and implementation 
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.ucm.core.types;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementType;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementMatcher;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.papyrus.designer.ucm.core.Activator;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.services.edit.service.IElementEditService;

/**
 * Utility class for {@link ElementType}
 */
public class ElementTypeUtils {

	/**
	 * Checks the given element if it matches the provided Element types, found using the specified identifier
	 * 
	 * @param element
	 *            element to be checked
	 * @param elementTypeId
	 *            identifier of the element type to match
	 * @return <code>true</code> if the given element matches the given element type
	 */
	public static Boolean matches(EObject element, String elementTypeId) {
		IElementType type = ElementTypeRegistry.getInstance().getType(elementTypeId);
		if (!(type instanceof ISpecializationType)) { // check at the same time UCM element types are correctly loaded
			// Activator.log.error("Impossible to find element type to match: " + elementTypeId, null);
			return false;
		}
		IElementMatcher matcher = ((ISpecializationType) type).getMatcher();
		if (matcher == null) {
			Activator.log.error("no matcher provided for type: " + type, null);
			return false;
		}
		if (matcher.matches(element)) {
			return true;
		}
		return false;
	}

	/**
	 * Queries whether an {@code object} is an instance of an element-type.
	 * 
	 * @param object
	 *            an object
	 * @param elementType
	 *            an element-type
	 * @return whether the {@codee object} is an instance of the type
	 */
	public static boolean instanceOf(Object object, IElementType elementType) {
		boolean result;

		if (elementType instanceof ISpecializationType) {
			result = (object instanceof EObject) && ((ISpecializationType) elementType).getMatcher().matches((EObject) object);
		} else {
			result = elementType.getEClass().isInstance(object);
		}

		return result;
	}

	/**
	 * Returns <code>true</code> if the specified type is equal or a specialization type of the reference type
	 * 
	 * @param type
	 *            the type to check
	 * @param referenceType
	 *            the reference type
	 * @return <code>true</code> if the specified type is equal or a specialization type of the reference type
	 */
	public static boolean isTypeCompatible(IElementType type, IElementType referenceType) {
		if (type == null || referenceType == null) {
			return false;
		}

		if (type.equals(referenceType)) {
			return true;
		}

		IElementType[] superTypes = type.getAllSuperTypes();
		if (superTypes.length == 0) {
			return false;
		}
		for (int i = 0; i < superTypes.length; i++) {
			IElementType superType = superTypes[i];
			if (referenceType.equals(superType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtains a command that ensures that an {@code object} becomes an instance of
	 * a specific element {@code type}.
	 * 
	 * @param type
	 *            an element type
	 * @param object
	 *            an object that is an instance of the {@code type}'s metaclass but
	 *            otherwise does not conform to the {@code type}
	 * 
	 * @return the configure-as command
	 */
	public static ICommand getConfigureAsCommand(IElementType type, EObject object) {
		ICommand result;

		IElementEditService edit = ElementEditServiceUtils.getCommandProvider(object);

		if (edit == null) {
			// Can't do it
			result = UnexecutableCommand.INSTANCE;
		} else {
			TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(object);
			ConfigureRequest configure = new ConfigureRequest(editingDomain, object, type);
			result = edit.getEditCommand(configure);
		}

		return result;
	}

}
