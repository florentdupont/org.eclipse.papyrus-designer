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
public final class AssignExpr extends Expression {

	public static enum Operator {
		assign, // =
		plus, // +=
		minus, // -=
		star, // *=
		slash, // /=
		and, // &=
		or, // |=
		xor, // ^=
		rem, // %=
		lShift, // <<=
		rSignedShift, // >>=
		rUnsignedShift, // >>>=
	}

	private Expression target;

	private Expression value;

	private Operator op;

	public AssignExpr() {
	}

	public AssignExpr(int beginLine, int beginColumn, int endLine, int endColumn, Expression target, Expression value, Operator op) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.target = target;
		this.value = value;
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

	public Operator getOperator() {
		return op;
	}

	public Expression getTarget() {
		return target;
	}

	public Expression getValue() {
		return value;
	}

	public void setOperator(Operator op) {
		this.op = op;
	}

	public void setTarget(Expression target) {
		this.target = target;
	}

	public void setValue(Expression value) {
		this.value = value;
	}

}
