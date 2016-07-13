/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.NamedNode;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public class NameExpr extends Expression implements NamedNode {

	private String name;

	public NameExpr() {
	}

	public NameExpr(final String name) {
		this.name = name;
	}

	public NameExpr(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final String name) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.name = name;
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	@Override
	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

}
