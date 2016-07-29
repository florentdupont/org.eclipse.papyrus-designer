/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.designer.transformation.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.designer.transformation.core.preferences.PapyrusDesignerPreferenceConstants;

/**
 * Class used to initialize default preference values.
 */
public class PapyrusDesignerPreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = org.eclipse.papyrus.designer.transformation.core.Activator.getDefault().getPreferenceStore();
		store.setDefault(PapyrusDesignerPreferenceConstants.P_SHOW_PROV_REQ_AS_PORT_ICONS, false);
		store.setDefault(PapyrusDesignerPreferenceConstants.P_ALL_ATTRIBUTES_ARE_CONFIG_ATTRIBUTES, false);
		store.setDefault(PapyrusDesignerPreferenceConstants.P_TREAT_NONE_AS_COMPOSITE, false);
		store.setDefault(PapyrusDesignerPreferenceConstants.P_CODE_GEN_PREFIX, "// generated with Qompass toolchain");
	}

}
