/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 07/11/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class DoStmt extends Statement {

	private Statement body;

	private Expression condition;

	public DoStmt() {
	}

	public DoStmt(int beginLine, int beginColumn, int endLine, int endColumn, Statement body, Expression condition) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.body = body;
		this.condition = condition;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public Statement getBody() {
		return body;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setBody(Statement body) {
		this.body = body;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}
}
