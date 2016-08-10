/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.transformation.core.generate;

/**
 * Constants for generation options (must be powers of 2)
 *
 * @author ansgar
 *
 */
public interface GenerationOptions {

	final public static int REWRITE_SETTINGS = 1;

	final public static int ONLY_CHANGED = 2;

	final public static int MODEL_ONLY = 4;

	final public static int CAC_ONLY = 8;

};
