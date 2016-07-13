/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.TypedNode;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class InstanceOfExpr extends Expression implements TypedNode {

	private Expression expr;

	private Type type;

	public InstanceOfExpr() {
	}

	public InstanceOfExpr(final Expression expr, final Type type) {
		setExpr(expr);
		setType(type);
	}

	public InstanceOfExpr(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final Expression expr, final Type type) {
		super(beginLine, beginColumn, endLine, endColumn);
		setExpr(expr);
		setType(type);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public Expression getExpr() {
		return expr;
	}

	@Override
	public Type getType() {
		return type;
	}

	public void setExpr(final Expression expr) {
		this.expr = expr;
		setAsParentNodeOf(this.expr);
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
		setAsParentNodeOf(this.type);
	}
}
