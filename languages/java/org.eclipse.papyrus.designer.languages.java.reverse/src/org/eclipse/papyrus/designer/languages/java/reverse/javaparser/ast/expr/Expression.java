/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.Node;

/**
 * @author Julio Vilmar Gesser
 */
public abstract class Expression extends Node {

	public Expression() {
	}

	public Expression(final int beginLine, final int beginColumn, final int endLine, final int endColumn) {
		super(beginLine, beginColumn, endLine, endColumn);
	}

}
