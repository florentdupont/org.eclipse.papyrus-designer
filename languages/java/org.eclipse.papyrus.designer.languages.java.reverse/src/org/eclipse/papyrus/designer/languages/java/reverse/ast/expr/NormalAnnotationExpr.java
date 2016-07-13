/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 21/11/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.expr;

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class NormalAnnotationExpr extends AnnotationExpr {

	private NameExpr name;

	private List<MemberValuePair> pairs;

	public NormalAnnotationExpr() {
	}

	public NormalAnnotationExpr(int beginLine, int beginColumn, int endLine, int endColumn, NameExpr name, List<MemberValuePair> pairs) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.name = name;
		this.pairs = pairs;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public NameExpr getName() {
		return name;
	}

	public List<MemberValuePair> getPairs() {
		return pairs;
	}

	public void setName(NameExpr name) {
		this.name = name;
	}

	public void setPairs(List<MemberValuePair> pairs) {
		this.pairs = pairs;
	}

}
