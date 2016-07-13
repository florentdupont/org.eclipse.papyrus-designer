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

import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class LabeledStmt extends Statement {

	private String label;

	private Statement stmt;

	public LabeledStmt() {
	}

	public LabeledStmt(int beginLine, int beginColumn, int endLine, int endColumn, String label, Statement stmt) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.label = label;
		this.stmt = stmt;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public String getLabel() {
		return label;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
}
