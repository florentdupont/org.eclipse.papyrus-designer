/*******************************************************************************
 * Copyright (c) 2013 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/
package org.eclipse.papyrus.designer.components.transformation.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.papyrus.designer.transformation.base.utils.LibraryUtils;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;

/**
 * This class adds "standard" model libraries for component-based development: the FCM profile, parts of the MARTE profile (allocation)
 * and the associated package imports to your model.
 */
public class AddStdModelLibs extends AbstractEMFOperation {

	public AddStdModelLibs(Package pkg, TransactionalEditingDomain domain) {
		super(domain, CMD_LABEL);
		selectedPkg = pkg;
		availableImportPackages = null;
		importList = null;
	}

	public static final String CMD_LABEL = "Add Designer CORE libraries"; //$NON-NLS-1$

	public static final String DESIGNER_BASIC_TRAFOS_URI = "pathmap://DML_TRAFOS/trafos.uml"; //$NON-NLS-1$

	public static final String DESIGNER_MARTE_CALLS_URI = "pathmap://QML_MARTE/marte.uml"; //$NON-NLS-1$

	protected EList<PackageImport> importList;

	protected EList<PackageImport> availableImportPackages;

	protected Package selectedPkg;

	/**
	 * Check whether a package import is already done
	 *
	 * @param selectedPkg
	 * @param pi
	 * @return
	 */
	boolean isAlreadyImported(Package selectedPkg, PackageImport pi) {
		for (PackageImport existingPI : selectedPkg.getPackageImports()) {
			if ((existingPI.getImportedPackage() == null) ||
					(pi.getImportedPackage() == null)) {
				// import package are null (should not happen?!)
				continue;
			}
			if ((existingPI.getImportedPackage().getName() == null) ||
					(pi.getImportedPackage().getName() == null)) {
				// import package name not set (should not happen?!)
				continue;
			} else if (existingPI.getImportedPackage().getName().equals(pi.getImportedPackage().getName())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */

	/**
	 * Return a list of package-imports for standard Qompass model libraries
	 *
	 * @return
	 * @throws ExecutionException
	 */
	public EList<PackageImport> getAvailableImportPackages() {
		if (availableImportPackages != null) {
			return availableImportPackages;
		}
		final ResourceSet resourceSet = selectedPkg.eResource().getResourceSet();
		availableImportPackages = new BasicEList<PackageImport>();

		PackageImport pi = LibraryUtils.getModelLibraryImportFromURI(URI.createURI(DESIGNER_BASIC_TRAFOS_URI), resourceSet);
		if (pi != null) {
			availableImportPackages.add(pi);
		}
		pi = LibraryUtils.getModelLibraryImportFromURI(URI.createURI(DESIGNER_MARTE_CALLS_URI), resourceSet);
		if (pi != null) {
			availableImportPackages.add(pi);
		}
		return availableImportPackages;
	}

	/**
	 * @return a list of package-imports that are already imported (within the selected pkg).
	 */
	public EList<PackageImport> getAlreadyImportedPackages() {
		EList<PackageImport> availablePackages = getAvailableImportPackages();
		EList<PackageImport> selection = new BasicEList<PackageImport>();
		for (PackageImport pi : availablePackages) {
			if (isAlreadyImported(selectedPkg, pi)) {
				selection.add(pi);
			}
		}
		return selection;
	}

	/**
	 * Initialize import list. Must be called, before the command gets executed.
	 *
	 * @param importList
	 *            a list of package-imports that will be added to the selectedPkg when
	 *            the command gets executed.
	 */
	public void setImportList(EList<PackageImport> importList) {
		this.importList = importList;
	}


	/**
	 * Initialize import list. Must be called, before the command gets executed.
	 * Convenience method for dialogs that return an array.
	 *
	 * @param importArray
	 *            an array of objects (which must contain package-imports) that will
	 *            be added to the selectedPkg when the command gets executed.
	 */
	public void setImportList(Object importArray[]) {
		importList = new BasicEList<PackageImport>();
		for (Object importPkg : importArray) {
			if (importPkg instanceof PackageImport) {
				importList.add((PackageImport) importPkg);
			}
		}
	}

	@Override
	protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {

		for (PackageImport pi : importList) {
			if (!isAlreadyImported(selectedPkg, pi)) {
				selectedPkg.getPackageImports().add(pi);
			}
		}
		return Status.OK_STATUS;
	}
}
