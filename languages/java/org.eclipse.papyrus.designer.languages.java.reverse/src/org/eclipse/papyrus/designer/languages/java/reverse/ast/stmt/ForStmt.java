/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 07/11/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt;

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class ForStmt extends Statement {

	private List<Expression> init;

	private Expression compare;

	private List<Expression> update;

	private Statement body;

	public ForStmt() {
	}

	public ForStmt(int beginLine, int beginColumn, int endLine, int endColumn, List<Expression> init, Expression compare, List<Expression> update, Statement body) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.compare = compare;
		this.init = init;
		this.update = update;
		this.body = body;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public Statement getBody() {
		return body;
	}

	public Expression getCompare() {
		return compare;
	}

	public List<Expression> getInit() {
		return init;
	}

	public List<Expression> getUpdate() {
		return update;
	}

	public void setBody(Statement body) {
		this.body = body;
	}

	public void setCompare(Expression compare) {
		this.compare = compare;
	}

	public void setInit(List<Expression> init) {
		this.init = init;
	}

	public void setUpdate(List<Expression> update) {
		this.update = update;
	}
}
