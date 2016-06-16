/*******************************************************************************
 * Copyright (c) 2016 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Ansgar Radermacher - Initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.idl.codegen.transformation;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.papyrus.designer.languages.common.base.HierarchyLocationStrategy;
import org.eclipse.papyrus.designer.languages.common.base.ModelElementsCreator;
import org.eclipse.papyrus.designer.languages.idl.codegen.GenIDL2;
import org.eclipse.papyrus.designer.languages.idl.codegen.GenIDL3;
import org.eclipse.papyrus.designer.languages.idl.codegen.preferences.IDLCodeGenUtils;
import org.eclipse.papyrus.infra.tools.file.ProjectBasedFileAccess;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.PackageableElement;

public class IDLModelElementsCreator extends ModelElementsCreator {

	private static final String IDL_LANG = "IDL"; //$NON-NLS-1$

	public IDLModelElementsCreator(IProject project) {
		this(project, IDL_LANG);
		idlExt = IDLCodeGenUtils.getIDLSuffix();
	}

	public IDLModelElementsCreator(IProject project, String language) {
		super(new ProjectBasedFileAccess(project), new HierarchyLocationStrategy(), language);
		this.project = project;
		idlExt = IDLCodeGenUtils.getIDLSuffix();
	}

	protected String idlExt;

	@Override
	protected boolean noCodeGen(Element element) {
		return false;
	}

	@Override
	protected void createPackageableElementFile(PackageableElement pe, IProgressMonitor monitor) {
		if (pe instanceof Classifier) {
			Classifier classifier = (Classifier) pe;
			final String fileNameIDL = locStrategy.getFileName(classifier) + "." + idlExt; //$NON-NLS-1$
			String result = ""; //$NON-NLS-1$
			if ((classifier instanceof Interface) || (classifier instanceof DataType)) {
				result = GenIDL2.genIDL2(classifier).toString();
			} else if (classifier instanceof Class) {
				result = GenIDL3.genIDL3((Class) classifier).toString();
			}
			fileSystemAccess.generateFile(fileNameIDL, result);
		}
	}
}
