/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class SingleMemberAnnotationExpr extends AnnotationExpr {

	private Expression memberValue;

	public SingleMemberAnnotationExpr() {
	}

	public SingleMemberAnnotationExpr(final NameExpr name, final Expression memberValue) {
		setName(name);
		setMemberValue(memberValue);
	}

	public SingleMemberAnnotationExpr(final int beginLine, final int beginColumn, final int endLine,
			final int endColumn, final NameExpr name, final Expression memberValue) {
		super(beginLine, beginColumn, endLine, endColumn);
		setName(name);
		setMemberValue(memberValue);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public Expression getMemberValue() {
		return memberValue;
	}

	public void setMemberValue(final Expression memberValue) {
		this.memberValue = memberValue;
		setAsParentNodeOf(this.memberValue);
	}
}
