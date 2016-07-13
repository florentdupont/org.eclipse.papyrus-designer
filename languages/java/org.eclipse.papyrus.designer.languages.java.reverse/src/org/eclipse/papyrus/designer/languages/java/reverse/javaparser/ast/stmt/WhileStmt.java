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
public final class WhileStmt extends Statement {

	private Expression condition;

	private Statement body;

	public WhileStmt() {
	}

	public WhileStmt(final Expression condition, final Statement body) {
		setCondition(condition);
		setBody(body);
	}

	public WhileStmt(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final Expression condition, final Statement body) {
		super(beginLine, beginColumn, endLine, endColumn);
		setCondition(condition);
		setBody(body);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public Statement getBody() {
		return body;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setBody(final Statement body) {
		this.body = body;
		setAsParentNodeOf(this.body);
	}

	public void setCondition(final Expression condition) {
		this.condition = condition;
		setAsParentNodeOf(this.condition);
	}
}
