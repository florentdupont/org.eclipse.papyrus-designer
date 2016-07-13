/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type;

import java.util.HashMap;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class PrimitiveType extends Type {

	public enum Primitive {
		Boolean ("Boolean"),
		Char    ("Character"),
		Byte    ("Byte"),
		Short   ("Short"),
		Int     ("Integer"),
		Long    ("Long"),
		Float   ("Float"),
		Double  ("Double");

		final String nameOfBoxedType;

		public ClassOrInterfaceType toBoxedType() {
			return new ClassOrInterfaceType(nameOfBoxedType);
		}

		private Primitive(String nameOfBoxedType) {
			this.nameOfBoxedType = nameOfBoxedType;
		}
	}

	static final HashMap<String, Primitive> unboxMap = new HashMap<String, Primitive>();
	static {
		for(Primitive unboxedType : Primitive.values()) {
			unboxMap.put(unboxedType.nameOfBoxedType, unboxedType);
		}
	}

	private Primitive type;

	public PrimitiveType() {
	}

	public PrimitiveType(final Primitive type) {
		this.type = type;
	}

	public PrimitiveType(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final Primitive type) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.type = type;
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public Primitive getType() {
		return type;
	}

	public ClassOrInterfaceType toBoxedType() {
		return type.toBoxedType();
	}

	public void setType(final Primitive type) {
		this.type = type;
	}

}
