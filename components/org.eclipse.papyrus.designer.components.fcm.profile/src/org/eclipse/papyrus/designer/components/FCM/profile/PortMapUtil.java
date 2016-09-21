/**
 * Copyright (c) 2013 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - Initial API and implementation
 *
 */

package org.eclipse.papyrus.designer.components.FCM.profile;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.papyrus.designer.components.FCM.Port;
import org.eclipse.papyrus.designer.components.FCM.PortKind;
import org.eclipse.papyrus.designer.components.FCM.TemplatePort;
import org.eclipse.papyrus.designer.transformation.base.utils.MapUtil;
import org.eclipse.papyrus.designer.transformation.base.utils.MapUtil.ECreateKind;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Type;


/**
 * This class is responsible for creating derived types associated with ports. The derived
 * type is the class that will type the port and contain imported and used interfaces depending
 * on the FCM type and kind.
 * A particular question related to the derived types is into which package we can place them
 * within the type hierarchy:
 * (1) Of course, the package must be writable. That means, it must not belong to an imported library.
 * (2) The source type must be clearly identifiable.
 * (3) If a template get's instantiated, derived types within (if we place them there) might
 * need (re-) instantiation. Imagine AMI_ interface that contains some of I's method. The creation of an AMI_I type within the template
 * 
 * Therefore, a global "derived types" package is created within the model that owns the port. This
 * package contains the full folder hierarchy of the original type and places the elements there.
 * TODO: Align with package template instantiation => create type specific sub-folder, e.g. kind_type. Use package specific side-folders.
 * Need specific solution for elements within template (placing into same template is finally not a good idea).
 * Idea of package template (and the possibility to extend) was, that common elements are instantiated once. Thus, we will have interfaceBased_<intfName>/UseInterface/Use.cpp,
 * not UseInterface_<intfName>/Use.cpp
 * [Users choice to define template with additional package or not?]
 */
public class PortMapUtil {

	private static final String S_FORMAT = "%s";

	// specific treatment of "root" model that is created by eC3M for deployment
	// It avoids that copies of derived interfaces are created at different places
	// [better alternative: avoid problem by using set of models instead of single large one]
	// [other option?: no-update flag?]
	public static final String rootModelName = "root"; //$NON-NLS-1$

	public static final String MAPPING_RULE_ID = "fcmPortMappingRule"; //$NON-NLS-1$

	/**
	 * Get or create a derived interface for a port using a fixed prefix
	 * type name
	 *
	 * @param port
	 *            The port
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            type name
	 * @return the derived interface or null (if it cannot be created)
	 */
	public static Class getDerivedClass(Port port, String prefix) {
		Type type = MapUtil.getOrCreateDerivedType(port.getBase_Port(), prefix+S_FORMAT, port.getType(), ECreateKind.NONE);
		return type instanceof Class ? (Class) type : null;
	}

	/**
	 * Get or create a derived interface for a port using a fixed prefix
	 * type name
	 *
	 * @param port
	 *            The port
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            type name
	 * @return the derived interface or null (if it cannot be created)
	 */
	public static Class getDerivedClass(Port port, String prefix, boolean update) {
		return update ? getOrCreateDerivedClass(port, prefix) : getDerivedClass(port, prefix);
	}

	/**
	 * Get a derived interface of a port. The interface is searched within the derived types
	 * folder based on the passed prefix
	 *
	 * @param port
	 *            The port
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            FCM port type
	 * @return the derived interface or null (if it cannot be created)
	 */
	public static Interface getDerivedInterface(Port port, String prefix) {
		Type type = MapUtil.getOrCreateDerivedType(port.getBase_Port(), prefix+S_FORMAT, port.getType(), ECreateKind.NONE);
		return type instanceof Interface ? (Interface) type : null;
	}

	/**
	 * Get or create a derived interface for a port using a fixed prefix
	 * type name
	 *
	 * @param port
	 *            The port
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            type name
	 * @return the derived interface or null (if it cannot be created)
	 */
	public static Interface getDerivedInterface(Port port, String prefix, boolean update) {
		return update ? getOrCreateDerivedInterface(port, prefix) : getDerivedInterface(port, prefix);
	}

	/**
	 * Get or create a derived interface for a port using a fixed prefix
	 * type name
	 *
	 * @param port
	 *            The port
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            type name
	 * @param createKind
	 *            if non NONE, create either an interfaces or a class, if it does not exist yet
	 * @return the derived interface or null (if it cannot be created)
	 */
	public static Class getOrCreateDerivedClass(Port port, String prefix) {
		Type type = MapUtil.getOrCreateDerivedType(port.getBase_Port(), prefix+S_FORMAT, port.getType(), ECreateKind.CLASS);
		return type instanceof Class ? (Class) type : null;
	}

	/**
	 * Get or create a derived interface for a port using a fixed prefix
	 * type name
	 *
	 * @param port
	 *            The port
	 * @param prefix
	 *            prefix string
	 * @param type
	 *            type name
	 * @return the derived interface or null (if it cannot be created)
	 */
	public static Interface getOrCreateDerivedInterface(Port port, String prefix) {
		Type type = MapUtil.getOrCreateDerivedType(port.getBase_Port(), prefix+S_FORMAT, port.getType(), ECreateKind.INTERFACE);
		return type instanceof Interface ? (Interface) type : null;
	}



	/**b
	 * Calculate derived required interface in function of port type and kind
	 *
	 * @param port
	 *            the port, for which the calculation should be done
	 * @return
	 */
	public static Type getDerivedType(final Port port) {
		if (port.getBase_Port() == null) {
			// should not happen, but can occur in case of corrupted XMI files
			return null;
		}
		PortKind portKind = port.getKind();
		if (portKind != null) {
			String ruleName = portKind.isExtendedPort() ? "ExtendedPort" : portKind.getBase_Class().getName(); //$NON-NLS-1$
			final IMappingRule mappingRule = getMappingRule(ruleName);
			if (mappingRule != null) {
				return mappingRule.calcDerivedType(port, false);
			}
		}
		return null;
	}


	/**
	 * Obtain the mapping rule for a port when the name of the portKind is given
	 *
	 * @param portKindName
	 *            the name of the port-kind
	 *
	 * @return the mapping rule or null, if no rule could be found
	 */
	public static IMappingRule getMappingRule(String portKindName) {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = reg.getConfigurationElementsFor(Activator.PLUGIN_ID + "." + MAPPING_RULE_ID); //$NON-NLS-1$
		for (IConfigurationElement configElement : configElements) {
			try {
				final String extPortKindName = configElement.getAttribute("portKindName"); //$NON-NLS-1$
				if (extPortKindName.equals(portKindName)) {
					final Object obj = configElement.createExecutableExtension("class"); //$NON-NLS-1$
					if (obj instanceof IMappingRule) {
						return (IMappingRule) obj;
					}
				}
			} catch (CoreException exception) {
				exception.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Get the mapping rule for a port
	 *
	 * @param port
	 *            the FCM port
	 * @return the mapping rule or null, if no rule could be found
	 */
	public static IMappingRule getMappingRule(final Port port) {
		if (port.getBase_Port() == null) {
			// should not happen, but can occur in case of corrupted XMI files
			return null;
		}
		PortKind portKind = port.getKind();
		if (portKind == null) {
			return null;
		}
		if (portKind.getBase_Class() != null) {
			String ruleName = portKind.isExtendedPort() ? "ExtendedPort" : portKind.getBase_Class().getName(); //$NON-NLS-1$
			if (port instanceof TemplatePort) {
				ruleName = "TemplatePort"; //$NON-NLS-1$
			}
			return getMappingRule(ruleName);
		}
		return null;
	}

	public static PortKind getBoundType(final Port port) {
		if (port.getBase_Port() == null) {
			// should not happen, but can occur in case of corrupted XMI files
			return null;
		} else {
			String ruleName = "TemplatePort"; //$NON-NLS-1$
			final IMappingRule mappingRule = getMappingRule(ruleName);
			if (mappingRule instanceof ITemplateMappingRule) {
				return ((ITemplateMappingRule) mappingRule).getBoundType(port);
			}
		}
		return null;
	}

	/**
	 * Update the derived interfaces of a port this operation needs to be
	 * called in the context of an update command (transaction).
	 *
	 * @param port
	 */
	public static void update(final Port port) {
		final IMappingRule mappingRule = getMappingRule(port);

		if (mappingRule != null) {
			Type type = mappingRule.calcDerivedType(port, true);
			port.getBase_Port().setType(type);
			if (mappingRule instanceof ITemplateMappingRule) {
				((ITemplateMappingRule) mappingRule).updateBinding(port);
			}
		}
	}

	/**
	 * Check whether a port needs to be updated, since its derived interfaces
	 * are either not existing or out of date.
	 *
	 * @param port
	 * @return
	 */
	public static boolean needsUpdate(final Port port) {
		final IMappingRule mappingRule = getMappingRule(port);
		if (mappingRule != null) {
			return mappingRule.needsUpdate(port);
		}
		return false;
	}
}
