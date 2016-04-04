/*******************************************************************************
 * Copyright (c) 2006 - 2016 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Shuai Li (CEA LIST) <shuai.li@cea.fr> - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.java.codegen;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.languages.common.base.ModelElementsCreator;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen2;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.MethodInfo;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.SyncInformation;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.PapyrusJavaPackage;
import org.eclipse.papyrus.designer.languages.java.codegen.preferences.JavaCodeGenUtils;
import org.eclipse.papyrus.designer.languages.java.codegen.transformation.JavaModelElementsCreator;
import org.eclipse.papyrus.designer.languages.java.codegen.utils.LocateJavaProject;
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
 * Java language support
 */
public class JavaLangCodegen implements ILangCodegen2 {

	protected ModelElementsCreator creator = null;

	protected IProject lastProject = null;

	@Override
	public String getDescription() {
		return Messages.JavaLangCodegen_GeneratorDesc;
	}

	/**
	 * Check whether the code generator is able to produce code for the passed element:
	 * it must be a classifier and the Java profile must be applied.  
	 */
	@Override
	public boolean isEligible(Element modelElement) {
		if (modelElement instanceof Classifier) {
			Package nearestPackage = modelElement.getNearestPackage();
			if (nearestPackage != null) {
				// check whether the Java profile is applied
				for (Profile profile : nearestPackage.getAllAppliedProfiles()) {
					if (PapyrusJavaPackage.eINSTANCE.getName().equals(profile.getName())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public String getSuffix(FILE_KIND fileKind) {
		return JavaCodeGenUtils.getHeaderSuffix();
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
		return LocateJavaProject.getTargetProject(pe, createIfMissing);
	}

	protected void manageCreator(IProject project, Element element) {
		if ((project == null) && (element instanceof PackageableElement)) {
			project = getTargetProject((PackageableElement) element, false);
		}
		if ((creator == null) || (project != lastProject)) {
			lastProject = project;
			creator = new JavaModelElementsCreator(project);
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
					mi.addParameterType(org.eclipse.papyrus.designer.languages.java.codegen.xtend.JavaParameter.javaParameterForJDT(parameter).toString());
				}
			}
		}
		return mi;
	}
}
