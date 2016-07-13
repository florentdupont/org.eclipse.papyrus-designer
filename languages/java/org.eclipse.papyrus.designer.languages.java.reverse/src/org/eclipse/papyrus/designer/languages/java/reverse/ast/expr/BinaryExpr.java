/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 05/10/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class BinaryExpr extends Expression {

	public static enum Operator {
		or, // ||
		and, // &&
		binOr, // |
		binAnd, // &
		xor, // ^
		equals, // ==
		notEquals, // !=
		less, // <
		greater, // >
		lessEquals, // <=
		greaterEquals, // >=
		lShift, // <<
		rSignedShift, // >>
		rUnsignedShift, // >>>
		plus, // +
		minus, // -
		times, // *
		divide, // /
		remainder, // %
	}

	private Expression left;

	private Expression right;

	private Operator op;

	public BinaryExpr() {
	}

	public BinaryExpr(int beginLine, int beginColumn, int endLine, int endColumn, Expression left, Expression right, Operator op) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.left = left;
		this.right = right;
		this.op = op;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public Expression getLeft() {
		return left;
	}

	public Operator getOperator() {
		return op;
	}

	public Expression getRight() {
		return right;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public void setOperator(Operator op) {
		this.op = op;
	}

	public void setRight(Expression right) {
		this.right = right;
	}

}
