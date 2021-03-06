/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.components.modellibs.core.mappingrules;

import org.eclipse.papyrus.designer.components.FCM.Port;
import org.eclipse.papyrus.designer.components.FCM.profile.IMappingRule;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Type;

public class ProvideInterface implements IMappingRule
{
	@Override
	public Type calcDerivedType(Port p, boolean update) {
		Type type = p.getBase_Port().getType();
		if (type instanceof Interface) {
			return ((Interface) type);
		}
		return null;
	}

	@Override
	public boolean needsUpdate(Port p) {
		return false;
	}
}
