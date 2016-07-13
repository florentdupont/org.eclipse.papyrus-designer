/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 07/11/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.body;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.BlockStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class InitializerDeclaration extends BodyDeclaration {

	private boolean isStatic;

	private BlockStmt block;

	public InitializerDeclaration() {
	}

	public InitializerDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, JavadocComment javaDoc, boolean isStatic, BlockStmt block) {
		super(beginLine, beginColumn, endLine, endColumn, null, javaDoc);
		this.isStatic = isStatic;
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

	public boolean isStatic() {
		return isStatic;
	}

	public void setBlock(BlockStmt block) {
		this.block = block;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
}
