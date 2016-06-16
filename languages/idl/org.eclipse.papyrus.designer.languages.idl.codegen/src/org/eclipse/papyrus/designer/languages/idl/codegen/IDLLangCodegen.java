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

package org.eclipse.papyrus.designer.languages.idl.codegen;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.papyrus.designer.languages.common.base.ModelElementsCreator;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen2;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.MethodInfo;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.SyncInformation;
import org.eclipse.papyrus.designer.languages.idl.codegen.preferences.IDLCodeGenUtils;
import org.eclipse.papyrus.designer.languages.idl.codegen.transformation.IDLModelElementsCreator;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * IDL language support
 *
 */
public class IDLLangCodegen implements ILangCodegen2 {

	protected ModelElementsCreator creator = null;

	protected IProject lastProject = null;

	@Override
	public String getDescription() {
		return "An IDL generator.";
	}

	@Override
	public boolean isEligible(Element modelElement) {
		if (modelElement instanceof Classifier) {
			return true;
		}
		return false;
	}

	@Override
	public String getSuffix(FILE_KIND fileKind) {
		return IDLCodeGenUtils.getIDLSuffix();
	}
	
	@Override
	public void generateCode(IProject project, PackageableElement element, IProgressMonitor monitor)
	{
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
		//return LocateCProject.getTargetProject(pe, createIfMissing);
		return null;
	}
	
	protected void manageCreator(IProject project, Element element) {
		if ((project == null) && (element instanceof PackageableElement)) {
			project = getTargetProject((PackageableElement) element, false);
		}
		if ((creator == null) || (project != lastProject)) {
			lastProject = project;
			creator = new IDLModelElementsCreator(project);
		}
	}

	// TODO: provide a suitable implementation
	@Override
	public SyncInformation getSyncInformation(String methodName, String body) {
		return null;
	}

	// TODO: provide a suitable implementation
	@Override
	public MethodInfo getMethodInfo(NamedElement operationOrBehavior) {
		return null;
	}
}
