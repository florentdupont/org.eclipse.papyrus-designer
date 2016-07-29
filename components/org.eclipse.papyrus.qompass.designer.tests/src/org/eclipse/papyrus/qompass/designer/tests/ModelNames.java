/*
 * Copyright (c) 2014 Zeligsoft (2009) Limited.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.qompass.designer.tests;


@SuppressWarnings("nls")
/**
 * Name of models
 *
 */
public class ModelNames {

	public static final String MainModel ="QompassTest.uml";

	public static final String MainModelPath = "P/" + Activator.PLUGIN_ID + "/resources/" + MainModel;

	public static final String GenModelFolderName = "GenModels";

	public static final String ExpectedModelFolder = "ExpectedModels";

	
	// copy must be identic, use original model for comparison
	public static final String ExpectedModel_LazyCopierFullCopy ="QompassTest.uml";

	public static final String ExpectedModel_LazyCopierIncrementalCopyA ="QompassTestIncrementalCopyA.uml";

	public static final String ExpectedModel_LazyCopierIncrementalCopyB ="QompassTestIncrementalCopyB.uml";
}
