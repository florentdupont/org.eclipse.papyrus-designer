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

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class ExplicitConstructorInvocationStmt extends Statement {

	private List<Type> typeArgs;

	private boolean isThis;

	private Expression expr;

	private List<Expression> args;

	public ExplicitConstructorInvocationStmt() {
	}

	public ExplicitConstructorInvocationStmt(int beginLine, int beginColumn, int endLine, int endColumn, List<Type> typeArgs, boolean isThis, Expression expr, List<Expression> args) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.typeArgs = typeArgs;
		this.isThis = isThis;
		this.expr = expr;
		this.args = args;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public List<Expression> getArgs() {
		return args;
	}

	public Expression getExpr() {
		return expr;
	}

	public List<Type> getTypeArgs() {
		return typeArgs;
	}

	public boolean isThis() {
		return isThis;
	}

	public void setArgs(List<Expression> args) {
		this.args = args;
	}

	public void setExpr(Expression expr) {
		this.expr = expr;
	}

	public void setThis(boolean isThis) {
		this.isThis = isThis;
	}

	public void setTypeArgs(List<Type> typeArgs) {
		this.typeArgs = typeArgs;
	}
}
