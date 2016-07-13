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

import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class ReferenceType extends Type {

	private Type type;

	private int arrayCount;

	public ReferenceType() {
	}

	public ReferenceType(int beginLine, int beginColumn, int endLine, int endColumn, Type type, int arrayCount) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.type = type;
		this.arrayCount = arrayCount;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public int getArrayCount() {
		return arrayCount;
	}

	public Type getType() {
		return type;
	}

	public void setArrayCount(int arrayCount) {
		this.arrayCount = arrayCount;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
