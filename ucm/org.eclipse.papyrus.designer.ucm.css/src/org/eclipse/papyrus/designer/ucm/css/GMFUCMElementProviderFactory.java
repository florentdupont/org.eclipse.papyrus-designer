/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher (based on code from Francois Le Fevre)
 *          - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.designer.ucm.css;

import org.eclipse.papyrus.infra.gmfdiag.css.engine.ICSSElementProviderFactory;
import org.eclipse.papyrus.infra.gmfdiag.css.notation.CSSDiagram;
import org.eclipse.papyrus.infra.gmfdiag.css.provider.IPapyrusElementProvider;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Profile;


/**
 * IElementProvider Factory for Diagrams related to UCM Elements
 */
public class GMFUCMElementProviderFactory implements ICSSElementProviderFactory {

	private static final String UCM_PROFILE = "UCMProfile"; //$NON-NLS-1$

	@Override
	public boolean isProviderFor(CSSDiagram diagram) {
		if(diagram.getElement() instanceof Element){
			//Retrieve the UML2 element
			Element element = (Element)diagram.getElement();
			
			//Introspect its packages to check if SysML profile is applied
			for (Profile profile : element.getNearestPackage().getAllAppliedProfiles()) {
				if (profile.getName() != null && profile.getName().contains(UCM_PROFILE)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public IPapyrusElementProvider createProvider(CSSDiagram diagram) {
		return new GMFUCMElementProvider();
	}
}
