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

import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.BodyDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.ClassOrInterfaceType;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class ObjectCreationExpr extends Expression {

	private Expression scope;

	private ClassOrInterfaceType type;

	private List<Type> typeArgs;

	private List<Expression> args;

	private List<BodyDeclaration> anonymousClassBody;

	public ObjectCreationExpr() {
	}

	public ObjectCreationExpr(int beginLine, int beginColumn, int endLine, int endColumn, Expression scope, ClassOrInterfaceType type, List<Type> typeArgs, List<Expression> args, List<BodyDeclaration> anonymousBody) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.scope = scope;
		this.type = type;
		this.typeArgs = typeArgs;
		this.args = args;
		this.anonymousClassBody = anonymousBody;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public List<BodyDeclaration> getAnonymousClassBody() {
		return anonymousClassBody;
	}

	public List<Expression> getArgs() {
		return args;
	}

	public Expression getScope() {
		return scope;
	}

	public ClassOrInterfaceType getType() {
		return type;
	}

	public List<Type> getTypeArgs() {
		return typeArgs;
	}

	public void setAnonymousClassBody(List<BodyDeclaration> anonymousClassBody) {
		this.anonymousClassBody = anonymousClassBody;
	}

	public void setArgs(List<Expression> args) {
		this.args = args;
	}

	public void setScope(Expression scope) {
		this.scope = scope;
	}

	public void setType(ClassOrInterfaceType type) {
		this.type = type;
	}

	public void setTypeArgs(List<Type> typeArgs) {
		this.typeArgs = typeArgs;
	}

}
