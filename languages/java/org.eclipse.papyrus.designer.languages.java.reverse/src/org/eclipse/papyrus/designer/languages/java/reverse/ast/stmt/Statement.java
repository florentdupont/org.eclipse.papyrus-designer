/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 03/11/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.Node;

/**
 * @author Julio Vilmar Gesser
 */
public abstract class Statement extends Node {

	public Statement() {
	}

	public Statement(int beginLine, int beginColumn, int endLine, int endColumn) {
		super(beginLine, beginColumn, endLine, endColumn);
	}

}
