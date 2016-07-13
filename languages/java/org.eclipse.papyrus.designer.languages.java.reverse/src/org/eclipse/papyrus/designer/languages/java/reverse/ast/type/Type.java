/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 05/10/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.type;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.Node;

/**
 * @author Julio Vilmar Gesser
 */
public abstract class Type extends Node {

	public Type() {
	}

	public Type(int beginLine, int beginColumn, int endLine, int endColumn) {
		super(beginLine, beginColumn, endLine, endColumn);
	}

}
