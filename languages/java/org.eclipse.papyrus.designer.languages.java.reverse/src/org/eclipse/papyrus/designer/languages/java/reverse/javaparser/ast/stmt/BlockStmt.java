/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.List;

import static org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils.*;

/**
 * @author Julio Vilmar Gesser
 */
public final class BlockStmt extends Statement {

	private List<Statement> stmts;

	public BlockStmt() {
	}

	public BlockStmt(final List<Statement> stmts) {
		setStmts(stmts);
	}

	public BlockStmt(final int beginLine, final int beginColumn,
			final int endLine, final int endColumn, final List<Statement> stmts) {
		super(beginLine, beginColumn, endLine, endColumn);
		setStmts(stmts);
	}

	@Override
	public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public List<Statement> getStmts() {
        stmts = ensureNotNull(stmts);
        return stmts;
	}

	public void setStmts(final List<Statement> stmts) {
		this.stmts = stmts;
		setAsParentNodeOf(this.stmts);
	}
}
