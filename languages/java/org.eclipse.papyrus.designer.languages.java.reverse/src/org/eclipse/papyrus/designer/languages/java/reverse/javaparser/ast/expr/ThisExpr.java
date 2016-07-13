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
public final class ThisExpr extends Expression {

	private Expression classExpr;

	public ThisExpr() {
	}

	public ThisExpr(final Expression classExpr) {
		setClassExpr(classExpr);
	}

	public ThisExpr(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final Expression classExpr) {
		super(beginLine, beginColumn, endLine, endColumn);
		setClassExpr(classExpr);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public Expression getClassExpr() {
		return classExpr;
	}

	public void setClassExpr(final Expression classExpr) {
		this.classExpr = classExpr;
		setAsParentNodeOf(this.classExpr);
	}
}
