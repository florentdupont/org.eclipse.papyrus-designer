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

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class TryStmt extends Statement {

	private BlockStmt tryBlock;

	private List<CatchClause> catchs;

	private BlockStmt finallyBlock;

	public TryStmt() {
	}

	public TryStmt(int beginLine, int beginColumn, int endLine, int endColumn, BlockStmt tryBlock, List<CatchClause> catchs, BlockStmt finallyBlock) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.tryBlock = tryBlock;
		this.catchs = catchs;
		this.finallyBlock = finallyBlock;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public List<CatchClause> getCatchs() {
		return catchs;
	}

	public BlockStmt getFinallyBlock() {
		return finallyBlock;
	}

	public BlockStmt getTryBlock() {
		return tryBlock;
	}

	public void setCatchs(List<CatchClause> catchs) {
		this.catchs = catchs;
	}

	public void setFinallyBlock(BlockStmt finallyBlock) {
		this.finallyBlock = finallyBlock;
	}

	public void setTryBlock(BlockStmt tryBlock) {
		this.tryBlock = tryBlock;
	}
}
