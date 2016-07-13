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

import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class InstanceOfExpr extends Expression {

	private Expression expr;

	private Type type;

	public InstanceOfExpr() {
	}

	public InstanceOfExpr(int beginLine, int beginColumn, int endLine, int endColumn, Expression expr, Type type) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.expr = expr;
		this.type = type;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public Expression getExpr() {
		return expr;
	}

	public Type getType() {
		return type;
	}

	public void setExpr(Expression expr) {
		this.expr = expr;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
