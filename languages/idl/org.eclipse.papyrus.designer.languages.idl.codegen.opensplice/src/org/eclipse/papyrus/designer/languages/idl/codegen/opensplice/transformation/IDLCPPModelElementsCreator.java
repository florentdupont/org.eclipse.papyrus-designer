/*******************************************************************************
 * Copyright (c) 2016 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Ansgar Radermacher - Initial API and implementation (based on work from 
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.idl.codegen.opensplice.transformation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.papyrus.designer.languages.cpp.codegen.Constants;
import org.eclipse.papyrus.designer.languages.idl.codegen.opensplice.preferences.IDLCodeGenUtils;
import org.eclipse.papyrus.designer.languages.idl.codegen.transformation.IDLModelElementsCreator;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.PackageableElement;

public class IDLCPPModelElementsCreator extends IDLModelElementsCreator {

	/**
	 * Language tag for Papyrus code generators
	 */
	private static final String IDL_CPP_LANG = "IDLC++"; //$NON-NLS-1$

	public static final String OSPL_HOME = "OSPL_HOME"; //$NON-NLS-1$

	/**
	 * Compiler Executable
	 * 
	 * @parameter expression="${opensplice.idlc}" default-value="idlpp"
	 */
	public static final String IDLC = "idlpp"; //$NON-NLS-1$

	/**
	 * language parameter for idlpp. Can be java, c++ or c
	 * 
	 * @parameter expression="${opensplice.idlc.lang}" default-value="java"
	 */
	public static final String IDLPP_LANGUAGE = "c++"; //$NON-NLS-1$

	/**
	 * IDLPP_MODE can be stand-alone or CORBA (-S | -C)
	 * 
	 * @parameter expression="${opensplice.idlc.mode}" default-value="-S"
	 */
	public static final String IDLPP_MODE = "-S"; //$NON-NLS-1$


	public IDLCPPModelElementsCreator(IProject project) {
		super(project, IDL_CPP_LANG);
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
			// generate a new IDL file first (inherited)
			super.createPackageableElementFile(pe, monitor);
			String idlFileNameWSRelative = locStrategy.getFileName(pe) + Constants.DOT + idlExt;

			IFile idlFile = project.getFile(idlFileNameWSRelative);
			String idlFileName = idlFile.getLocation().toOSString();
			String idlDir = new File(idlFileName).getParent();

			List<String> arguments = new ArrayList<String>();
			// idlpp should be in path
			arguments.add(IDLC);

			arguments.add("-d"); //$NON-NLS-1$
			arguments.add(idlDir);

			arguments.add(IDLPP_MODE);

			arguments.add("-l"); //$NON-NLS-1$
			arguments.add(IDLPP_LANGUAGE);

			Map<String, String> env = System.getenv(); // environment variables
			String OSPL_HOME_VAR = env.get(OSPL_HOME);
			List<String> includeDirs = new ArrayList<String>();
			includeDirs.add(OSPL_HOME_VAR + "/etc/idl"); //$NON-NLS-1$
			// TODO: need to cover Workspace root?

			File preprocessor = new File(OSPL_HOME_VAR + "/etc/idlpp"); //$NON-NLS-1$

			if (preprocessor.exists()) {
				arguments.add("-c"); //$NON-NLS-1$
				arguments.add(preprocessor.getAbsolutePath());
			}

			List<String> macros = new ArrayList<String>();
			for (String macro : macros) {
				arguments.add("-D"); //$NON-NLS-1$
				arguments.add(macro);
			}
			for (String includeDir : includeDirs) {
				arguments.add("-I"); //$NON-NLS-1$
				arguments.add(includeDir);
			}

			ProcessWrapper proc = new ProcessWrapper(/* getLog() */);
			arguments.add(idlFileName);
			proc.process(arguments);
		}
	}
}
