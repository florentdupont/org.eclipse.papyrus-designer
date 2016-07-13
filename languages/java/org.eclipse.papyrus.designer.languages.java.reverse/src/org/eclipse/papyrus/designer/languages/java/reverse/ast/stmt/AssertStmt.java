/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 04/11/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class AssertStmt extends Statement {

	private Expression check;

	private Expression msg;

	public AssertStmt() {
	}

	public AssertStmt(int beginLine, int beginColumn, int endLine, int endColumn, Expression check, Expression msg) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.check = check;
		this.msg = msg;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public Expression getCheck() {
		return check;
	}

	public Expression getMessage() {
		return msg;
	}

	public void setCheck(Expression check) {
		this.check = check;
	}

	public void setMessage(Expression msg) {
		this.msg = msg;
	}
}
