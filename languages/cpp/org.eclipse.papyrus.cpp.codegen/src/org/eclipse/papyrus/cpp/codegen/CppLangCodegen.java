/*******************************************************************************
 * Copyright (c) 2006 - 2015 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - ansgar.radermacher@cea.fr   initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.cpp.codegen;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.C_Cpp.C_CppPackage;
import org.eclipse.papyrus.codegen.base.ModelElementsCreator;
import org.eclipse.papyrus.codegen.extensionpoints.ILangCodegen2;
import org.eclipse.papyrus.codegen.extensionpoints.MethodInfo;
import org.eclipse.papyrus.codegen.extensionpoints.SyncInformation;
import org.eclipse.papyrus.cpp.codegen.preferences.CppCodeGenUtils;
import org.eclipse.papyrus.cpp.codegen.transformation.CppModelElementsCreator;
import org.eclipse.papyrus.cpp.codegen.utils.LocateCppProject;
import org.eclipse.papyrus.cpp.codegen.xtend.CppParameter;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Profile;

/**
 * C++ language support
 *
 */
public class CppLangCodegen implements ILangCodegen2 {

	protected ModelElementsCreator creator = null;

	protected IProject lastProject = null;

	@Override
	public String getDescription() {
		return Messages.C_CppLangCodegen_GeneratorDesc;
	}

	/**
	 * Check whether the code generator is able to produce code for the passed element:
	 * it must be a classifier and the C++ profile must be applied.  
	 */
	@Override
	public boolean isEligible(Element modelElement) {
		if (modelElement instanceof Classifier) {
			Package nearestPackage = modelElement.getNearestPackage();
			if (nearestPackage != null) {
				// check whether the C++ profile is applied
				for (Profile profile : nearestPackage.getAllAppliedProfiles()) {
					if (C_CppPackage.eINSTANCE.getName().equals(profile.getName())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public String getSuffix(FILE_KIND fileKind) {
		if (fileKind == FILE_KIND.BODY) {
			return CppCodeGenUtils.getBodySuffix();
		} else {
			return CppCodeGenUtils.getHeaderSuffix();
		}
	}

	@Override
	public void generateCode(IProject project, PackageableElement element, IProgressMonitor monitor) {
		manageCreator(project, element);
		creator.createPackageableElement(element, monitor);
	}

	@Override
	public void cleanCode(IProject project, PackageableElement element, IProgressMonitor monitor) {
		manageCreator(project, element);
		creator.removePackageableElement(element, monitor);
	}

	@Override
	public String getFileName(IProject project, NamedElement element) {
		manageCreator(project, element);
		return creator.getFileName(element);
	}

	@Override
	public IProject getTargetProject(PackageableElement pe, boolean createIfMissing) {
		return LocateCppProject.getTargetProject(pe, createIfMissing);
	}

	protected void manageCreator(IProject project, Element element) {
		if ((project == null) && (element instanceof PackageableElement)) {
			project = getTargetProject((PackageableElement) element, false);
		}
		if ((creator == null) || (project != lastProject)) {
			lastProject = project;
			creator = new CppModelElementsCreator(project);
		}
	}

	@Override
	public SyncInformation getSyncInformation(String methodName, String body) {
		return null;
	}

	@Override
	public MethodInfo getMethodInfo(NamedElement operationOrBehavior) {
		MethodInfo mi = new MethodInfo(operationOrBehavior.getName());
		EList<Parameter> parameters = null;
		if (operationOrBehavior instanceof Operation) {
			parameters = ((Operation) operationOrBehavior).getOwnedParameters();
		} else if (operationOrBehavior instanceof Behavior) {
			parameters = ((Behavior) operationOrBehavior).getOwnedParameters();
		}

		if (parameters != null) {
			for (Parameter parameter : parameters) {
				if (parameter.getDirection() != ParameterDirectionKind.RETURN_LITERAL) {
					mi.addParameterType(CppParameter.CppParameterForCDT(parameter).toString());
				}
			}
		}
		return mi;
	}
}
