/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.NamedNode;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.Node;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class MemberValuePair extends Node implements NamedNode {

	private String name;

	private Expression value;

	public MemberValuePair() {
	}

	public MemberValuePair(final String name, final Expression value) {
		setName(name);
		setValue(value);
	}

	public MemberValuePair(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final String name, final Expression value) {
		super(beginLine, beginColumn, endLine, endColumn);
		setName(name);
		setValue(value);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	@Override
	public String getName() {
		return name;
	}

	public Expression getValue() {
		return value;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setValue(final Expression value) {
		this.value = value;
		setAsParentNodeOf(this.value);
	}
}
