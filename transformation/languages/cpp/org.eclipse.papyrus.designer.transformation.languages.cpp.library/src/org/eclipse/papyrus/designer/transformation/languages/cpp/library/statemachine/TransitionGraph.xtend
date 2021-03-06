/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Van Cam Pham        <VanCam.PHAM@cea.fr>
 *
 *****************************************************************************/
 
package org.eclipse.papyrus.designer.transformation.languages.cpp.library.statemachine

import org.eclipse.uml2.uml.State
import java.util.List
import org.eclipse.uml2.uml.Pseudostate
import org.eclipse.uml2.uml.Transition
import org.eclipse.emf.common.util.UniqueEList

class TransitionGraph {
	public List<State> S = new UniqueEList
	public List<State> L = new UniqueEList
	public List<Pseudostate> P = new UniqueEList<Pseudostate>
	public List<Transition> T = new UniqueEList
}