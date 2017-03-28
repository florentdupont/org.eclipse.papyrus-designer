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

package org.eclipse.papyrus.designer.ucm.core.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.widgets.providers.AbstractStaticContentProvider;
import org.eclipse.papyrus.infra.widgets.providers.IStaticContentProvider;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;

/**
 * Similar to Papyrus MetaClassContentProvider: filter elements based on wanted meta-classes
 * but only navigate to packages, if there is a
 * dependency to it.
 * 
 * @author ansgar
 *
 */
public class UCMContentProvider extends AbstractStaticContentProvider implements IStaticContentProvider {

	public UCMContentProvider(Package rootPkg, EClass metaClass) {
		this(rootPkg, metaClass, null);
	}

	public UCMContentProvider(Package rootPkg, EClass metaClass, Class<? extends EObject> stereoAppFilter) {
		this.rootPkg = rootPkg;
		this.metaClass = metaClass;
		this.stereoApp = stereoAppFilter;
	}

	protected Package rootPkg;

	protected EClass metaClass;

	@Override
	public Object[] getElements() {
		List<Element> results = new ArrayList<Element>();
		List<Package> visitedPkgs = new ArrayList<Package>();
		getElements(rootPkg, results, visitedPkgs);
		return results.toArray();
	}

	public void getElements(Package pkg, List<Element> results, List<Package> visitedPkgs) {
		if (!visitedPkgs.contains(pkg)) {
			visitedPkgs.add(pkg);
			for (Element el : pkg.getPackagedElements()) {
				if (el instanceof Dependency) {
					Dependency dep = (Dependency) el;
					if (dep.getTargets().size() > 0 && (dep.getTargets().get(0) instanceof Package)) {
						getElements((Package) dep.getTargets().get(0), results, visitedPkgs);
					}
				}
				if (el instanceof Package) {
					getElements((Package) el, results, visitedPkgs);
				}
				if (metaClass.isInstance(el)) {
					if (stereoApp != null) {
						if (StereotypeUtil.isApplied(el, stereoApp)) {
							results.add(el);
						}
					} else {
						results.add(el);
					}
				}
			}
		}
	}

	Class<? extends EObject> stereoApp;
}
