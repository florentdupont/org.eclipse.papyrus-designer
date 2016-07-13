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
public final class AssertStmt extends Statement {

	private Expression check;

	private Expression msg;

	public AssertStmt() {
	}

	public AssertStmt(final Expression check) {
		setCheck(check);
	}

	public AssertStmt(final Expression check, final Expression msg) {
		setCheck(check);
		setMessage(msg);
	}

	public AssertStmt(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final Expression check, final Expression msg) {
		super(beginLine, beginColumn, endLine, endColumn);
		
		setCheck(check);
		setMessage(msg);
		
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public Expression getCheck() {
		return check;
	}

	public Expression getMessage() {
		return msg;
	}

	public void setCheck(final Expression check) {
		this.check = check;
		setAsParentNodeOf(this.check);
	}

	public void setMessage(final Expression msg) {
		this.msg = msg;
		setAsParentNodeOf(this.msg);
	}
}
