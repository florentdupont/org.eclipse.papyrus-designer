/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 05/10/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.body;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.Node;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class VariableDeclarator extends Node {

	private VariableDeclaratorId id;

	private Expression init;

	public VariableDeclarator() {
	}

	public VariableDeclarator(int beginLine, int beginColumn, int endLine, int endColumn, VariableDeclaratorId id, Expression init) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.id = id;
		this.init = init;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public VariableDeclaratorId getId() {
		return id;
	}

	public Expression getInit() {
		return init;
	}

	public void setId(VariableDeclaratorId id) {
		this.id = id;
	}

	public void setInit(Expression init) {
		this.init = init;
	}

}
