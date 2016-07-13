/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.List;

import static org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils.*;

/**
 * @author Julio Vilmar Gesser
 */
public final class NormalAnnotationExpr extends AnnotationExpr {

	private List<MemberValuePair> pairs;

	public NormalAnnotationExpr() {
	}

	public NormalAnnotationExpr(final NameExpr name, final List<MemberValuePair> pairs) {
		setName(name);
		setPairs(pairs);
	}

	public NormalAnnotationExpr(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final NameExpr name, final List<MemberValuePair> pairs) {
		super(beginLine, beginColumn, endLine, endColumn);
		setName(name);
		setPairs(pairs);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public List<MemberValuePair> getPairs() {
        pairs = ensureNotNull(pairs);
        return pairs;
	}

	public void setPairs(final List<MemberValuePair> pairs) {
		this.pairs = pairs;
		setAsParentNodeOf(this.pairs);
	}
}
