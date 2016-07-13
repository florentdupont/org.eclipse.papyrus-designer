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
public final class PrimitiveType extends Type {

	public enum Primitive {
		Boolean, Char, Byte, Short, Int, Long, Float, Double
	}

	private Primitive type;

	public PrimitiveType() {
	}

	public PrimitiveType(int beginLine, int beginColumn, int endLine, int endColumn, Primitive type) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.type = type;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public Primitive getType() {
		return type;
	}

	public void setType(Primitive type) {
		this.type = type;
	}

}
