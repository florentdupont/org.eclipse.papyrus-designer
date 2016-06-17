/*
 * Copyright (c) 2014 Zeligsoft (2009) Limited.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.cpp.codegen.tests;

import org.eclipse.ui.plugin.AbstractUIPlugin;

public class TestPlugin extends AbstractUIPlugin {

	private static TestPlugin instance;

	public TestPlugin() {
		instance = this;
	}

	public static TestPlugin getInstance() {
		return instance;
	}
}
