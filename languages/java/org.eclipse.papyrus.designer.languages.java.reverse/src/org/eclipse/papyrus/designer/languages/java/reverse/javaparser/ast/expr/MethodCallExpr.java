/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.List;

import static org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils.*;

/**
 * @author Julio Vilmar Gesser
 */
public final class MethodCallExpr extends Expression {

	private Expression scope;

	private List<Type> typeArgs;

	private NameExpr name;

	private List<Expression> args;

	public MethodCallExpr() {
	}

	public MethodCallExpr(final Expression scope, final String name) {
		setScope(scope);
		setName(name);
	}

	public MethodCallExpr(final Expression scope, final String name, final List<Expression> args) {
		setScope(scope);
		setName(name);
		setArgs(args);
	}

	public MethodCallExpr(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final Expression scope, final List<Type> typeArgs, final String name, final List<Expression> args) {
		super(beginLine, beginColumn, endLine, endColumn);
		setScope(scope);
		setTypeArgs(typeArgs);
		setName(name);
		setArgs(args);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public List<Expression> getArgs() {
        args = ensureNotNull(args);
        return args;
	}

	public String getName() {
		return name.getName();
	}

	public NameExpr getNameExpr() {
		return name;
	}

	public Expression getScope() {
		return scope;
	}

	public List<Type> getTypeArgs() {
        typeArgs = ensureNotNull(typeArgs);
        return typeArgs;
	}

	public void setArgs(final List<Expression> args) {
		this.args = args;
		setAsParentNodeOf(this.args);
	}

	public void setName(final String name) {
		this.name = new NameExpr(name);
	}

	public void setNameExpr(NameExpr name) {
		this.name = name;
	}

	public void setScope(final Expression scope) {
		this.scope = scope;
		setAsParentNodeOf(this.scope);
	}

	public void setTypeArgs(final List<Type> typeArgs) {
		this.typeArgs = typeArgs;
		setAsParentNodeOf(this.typeArgs);
	}
}
