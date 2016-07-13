/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 05/10/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.expr;

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class FieldAccessExpr extends Expression {

	private Expression scope;

	private List<Type> typeArgs;

	private String field;

	public FieldAccessExpr() {
	}

	public FieldAccessExpr(int beginLine, int beginColumn, int endLine, int endColumn, Expression scope, List<Type> typeArgs, String field) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.scope = scope;
		this.typeArgs = typeArgs;
		this.field = field;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public String getField() {
		return field;
	}

	public Expression getScope() {
		return scope;
	}

	public List<Type> getTypeArgs() {
		return typeArgs;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setScope(Expression scope) {
		this.scope = scope;
	}

	public void setTypeArgs(List<Type> typeArgs) {
		this.typeArgs = typeArgs;
	}

}
