/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class IfStmt extends Statement {

	private Expression condition;

	private Statement thenStmt;

	private Statement elseStmt;

	public IfStmt() {
	}

	public IfStmt(final Expression condition, final Statement thenStmt, final Statement elseStmt) {
		setCondition(condition);
		setThenStmt(thenStmt);
		setElseStmt(elseStmt);
	}

	public IfStmt(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final Expression condition, final Statement thenStmt, final Statement elseStmt) {
		super(beginLine, beginColumn, endLine, endColumn);
		setCondition(condition);
		setThenStmt(thenStmt);
		setElseStmt(elseStmt);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public Expression getCondition() {
		return condition;
	}

	public Statement getElseStmt() {
		return elseStmt;
	}

	public Statement getThenStmt() {
		return thenStmt;
	}

	public void setCondition(final Expression condition) {
		this.condition = condition;
		setAsParentNodeOf(this.condition);
	}

	public void setElseStmt(final Statement elseStmt) {
		this.elseStmt = elseStmt;
		setAsParentNodeOf(this.elseStmt);
	}

	public void setThenStmt(final Statement thenStmt) {
		this.thenStmt = thenStmt;
		setAsParentNodeOf(this.thenStmt);
	}
}
