/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class UnaryExpr extends Expression {

	public static enum Operator {
		positive, // +
		negative, // -
		preIncrement, // ++
		preDecrement, // --
		not, // !
		inverse, // ~
		posIncrement, // ++
		posDecrement, // --
	}

	private Expression expr;

	private Operator op;

	public UnaryExpr() {
	}

	public UnaryExpr(final Expression expr, final Operator op) {
		setExpr(expr);
		setOperator(op);
	}

	public UnaryExpr(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final Expression expr, final Operator op) {
		super(beginLine, beginColumn, endLine, endColumn);
		setExpr(expr);
		setOperator(op);
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

	public Operator getOperator() {
		return op;
	}

	public void setExpr(final Expression expr) {
		this.expr = expr;
		setAsParentNodeOf(this.expr);
	}

	public void setOperator(final Operator op) {
		this.op = op;
	}
}
