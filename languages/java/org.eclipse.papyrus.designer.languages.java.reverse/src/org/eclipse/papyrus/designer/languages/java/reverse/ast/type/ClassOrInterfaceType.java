/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 05/10/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.type;

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class ClassOrInterfaceType extends Type {

	private ClassOrInterfaceType scope;

	private String name;

	private List<Type> typeArgs;

	public ClassOrInterfaceType() {
	}

	public ClassOrInterfaceType(int beginLine, int beginColumn, int endLine, int endColumn, ClassOrInterfaceType scope, String name, List<Type> typeArgs) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.scope = scope;
		this.name = name;
		this.typeArgs = typeArgs;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public String getName() {
		return name;
	}

	public ClassOrInterfaceType getScope() {
		return scope;
	}

	public List<Type> getTypeArgs() {
		return typeArgs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScope(ClassOrInterfaceType scope) {
		this.scope = scope;
	}

	public void setTypeArgs(List<Type> typeArgs) {
		this.typeArgs = typeArgs;
	}
}
