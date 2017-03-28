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

package org.eclipse.papyrus.designer.ucm.core.utils;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.AbstractTypeBinding;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortTypeSpec;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDataType;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractDeclaration;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_contracts.AbstractInterface;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IPortType;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TemplateBinding;
import org.eclipse.uml2.uml.TemplateParameter;
import org.eclipse.uml2.uml.TemplateParameterSubstitution;
import org.eclipse.uml2.uml.TemplateSignature;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Utilities for binding of abstract types
 */
public class BindingUtils {
	// use a template binding
	private static final boolean USE_TB = true;

	public static EList<Type> getAbstractTypes(IPortType portType) {
		EList<Type> types = new BasicEList<Type>();
		if (portType != null) {
			Class portTypeBase = portType.getBase_Class();
			TemplateSignature signature = TemplateUtils.getSignature(portTypeBase.getNearestPackage());
			if (signature != null) {
				for (TemplateParameter parameter : signature.getParameters()) {
					ParameterableElement formal = parameter.getParameteredElement();
					if (formal instanceof Type) {
						types.add((Type) formal);
					}
				}
			}
		}
		return types;
	}

	public static Type getActual(PortTypeSpec portTypeSpec, Type formal) {
		if (USE_TB) {
			EList<TemplateBinding> bindings = portTypeSpec.getBase_Class().getTemplateBindings();
			if (bindings.size() > 0) {
				TemplateBinding binding = bindings.get(0);
				for (TemplateParameterSubstitution sub : binding.getParameterSubstitutions()) {
					if (sub.getFormal() == formal.getOwningTemplateParameter()) {
						return (Type) sub.getActual();
					}
				}
			}
		} else {
			for (Property attribute : portTypeSpec.getBase_Class().getAttributes()) {
				AbstractTypeBinding atb = UMLUtil.getStereotypeApplication(attribute, AbstractTypeBinding.class);
				if (atb != null) {
					AbstractDeclaration ad = atb.getAbstractType();
					if (ad instanceof AbstractInterface) {
						if (formal == ((AbstractInterface) ad).getBase_Interface()) {
							return attribute.getType();
						}
					} else if (ad instanceof AbstractDataType) {
						if (formal == ((AbstractDataType) ad).getBase_DataType()) {
							return attribute.getType();
						}
					}
				}
			}
		}
		return null;
	}

	public static void setActual(PortTypeSpec portTypeSpec, Type formal, Type actual) {
		if (USE_TB) {
			EList<TemplateBinding> bindings = portTypeSpec.getBase_Class().getTemplateBindings();
			if (bindings.size() == 0) {
				TemplateSignature signature = TemplateUtils.getSignature(portTypeSpec.getType().getBase_Class().getNearestPackage());
				portTypeSpec.getBase_Class().createTemplateBinding(signature);
			}
			TemplateBinding binding = bindings.get(0);
			for (TemplateParameterSubstitution sub : binding.getParameterSubstitutions()) {
				if (sub.getFormal() == formal) {
					sub.setActual(actual);
					return;
				}
			}
			// not found => create
			TemplateParameterSubstitution newSub = binding.createParameterSubstitution();
			newSub.setFormal(formal.getOwningTemplateParameter());
			newSub.setActual(actual);
		} else {
			for (Property attribute : portTypeSpec.getBase_Class().getAttributes()) {
				AbstractTypeBinding atb = UMLUtil.getStereotypeApplication(attribute, AbstractTypeBinding.class);
				if (atb != null) {
					AbstractDeclaration ad = atb.getAbstractType();
					if (ad instanceof AbstractInterface) {
						if (formal == ((AbstractInterface) ad).getBase_Interface()) {
							attribute.setType(actual);
							return;
						}
					} else if (ad instanceof AbstractDataType) {
						if (formal == ((AbstractDataType) ad).getBase_DataType()) {
							attribute.setType(actual);
							return;
						}
					}
				}
			}
			// not found => create
			Property binding = portTypeSpec.getBase_Class().createOwnedAttribute(String.format("bindingFor%s", formal.getName()), actual);
			AbstractTypeBinding atb = StereotypeUtil.applyApp(binding, AbstractTypeBinding.class);
			AbstractDeclaration ad = UMLUtil.getStereotypeApplication(formal, AbstractDeclaration.class);
			if ((atb != null) && (ad != null)) {
				atb.setAbstractType(ad);
			}
		}
	}

	public static void resetActual(PortTypeSpec portTypeSpec, Type formal) {
		if (USE_TB) {
			EList<TemplateBinding> bindings = portTypeSpec.getBase_Class().getTemplateBindings();
			if (bindings.size() > 0) {
				TemplateBinding binding = bindings.get(0);
				for (TemplateParameterSubstitution sub : binding.getParameterSubstitutions()) {
					if (sub.getFormal() == formal.getOwningTemplateParameter()) {
						sub.destroy();
						break;
					}
				}
			}
		} else {
			for (Property attribute : portTypeSpec.getBase_Class().getAttributes()) {
				AbstractTypeBinding atb = UMLUtil.getStereotypeApplication(attribute, AbstractTypeBinding.class);
				if (atb != null) {
					AbstractDeclaration ad = atb.getAbstractType();
					if (ad instanceof AbstractInterface) {
						if (formal == ((AbstractInterface) ad).getBase_Interface()) {
							attribute.destroy();
							break;
						}
					} else if (ad instanceof AbstractDataType) {
						if (formal == ((AbstractDataType) ad).getBase_DataType()) {
							attribute.destroy();
							break;
						}
					}
				}
			}
		}
	}
}
