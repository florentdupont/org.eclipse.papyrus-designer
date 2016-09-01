/*******************************************************************************
 * Copyright (c) 2011 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.transformation.extensions;

/**
 * This interface needs to be implemented by the classes that implement text
 * transformations. It is deliberately left empty to enable the execution of
 * arbitrary methods (referenced from the model) within these classes via the
 * Java reflection mechanisms 
 */
public interface ITextTemplate {
}
