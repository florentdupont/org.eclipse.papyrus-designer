/**
 * Copyright (c) 2016 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ansgar Radermacher - Initial API and implementation
 */

package org.eclipse.papyrus.designer.transformation.core.m2minterfaces;

import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Type;

/**
 * This interface should be implemented, if the transformations adds types to the
 * model (which my require a configuration within the deployment plan)
 */
public interface IM2MTrafoAddTypes extends IM2MTrafo {
	
	public EList<Type> getAddedTypes(M2MTrafo trafo, Element element) throws TransformationException;
}
