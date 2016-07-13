/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 23/05/2008
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * <p>
 * AST node that represent block comments.
 * </p>
 *
 * Block comments can has multi lines and are delimited
 * by "/&#42;" and "&#42;/".
 *
 * @author Julio Vilmar Gesser
 */
public final class BlockComment extends Comment {

	public BlockComment() {
	}

	public BlockComment(int beginLine, int beginColumn, int endLine, int endColumn, String content) {
		super(beginLine, beginColumn, endLine, endColumn, content);
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}
}
