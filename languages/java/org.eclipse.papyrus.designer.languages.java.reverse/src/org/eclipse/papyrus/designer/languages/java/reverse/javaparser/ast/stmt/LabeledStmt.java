/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class LabeledStmt extends Statement {

	private String label;

	private Statement stmt;

	public LabeledStmt() {
	}

	public LabeledStmt(final String label, final Statement stmt) {
		setLabel(label);
		setStmt(stmt);
	}

	public LabeledStmt(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final String label, final Statement stmt) {
		super(beginLine, beginColumn, endLine, endColumn);
		setLabel(label);
		setStmt(stmt);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public String getLabel() {
		return label;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public void setStmt(final Statement stmt) {
		this.stmt = stmt;
		setAsParentNodeOf(this.stmt);
	}
}
