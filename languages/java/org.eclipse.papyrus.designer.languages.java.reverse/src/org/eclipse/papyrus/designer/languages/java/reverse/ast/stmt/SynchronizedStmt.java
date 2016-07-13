/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 18/11/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class SynchronizedStmt extends Statement {

	private Expression expr;

	private BlockStmt block;

	public SynchronizedStmt() {
	}

	public SynchronizedStmt(int beginLine, int beginColumn, int endLine, int endColumn, Expression expr, BlockStmt block) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.expr = expr;
		this.block = block;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public BlockStmt getBlock() {
		return block;
	}

	public Expression getExpr() {
		return expr;
	}

	public void setBlock(BlockStmt block) {
		this.block = block;
	}

	public void setExpr(Expression expr) {
		this.expr = expr;
	}

}
