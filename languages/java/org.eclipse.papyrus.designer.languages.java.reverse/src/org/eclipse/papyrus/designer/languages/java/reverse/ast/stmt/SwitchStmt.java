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

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class SwitchStmt extends Statement {

	private Expression selector;

	private List<SwitchEntryStmt> entries;

	public SwitchStmt() {
	}

	public SwitchStmt(int beginLine, int beginColumn, int endLine, int endColumn, Expression selector, List<SwitchEntryStmt> entries) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.selector = selector;
		this.entries = entries;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public List<SwitchEntryStmt> getEntries() {
		return entries;
	}

	public Expression getSelector() {
		return selector;
	}

	public void setEntries(List<SwitchEntryStmt> entries) {
		this.entries = entries;
	}

	public void setSelector(Expression selector) {
		this.selector = selector;
	}
}
