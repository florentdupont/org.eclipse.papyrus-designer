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

import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class QualifiedNameExpr extends NameExpr {

	private NameExpr qualifier;

	public QualifiedNameExpr() {
	}

	public QualifiedNameExpr(int beginLine, int beginColumn, int endLine, int endColumn, NameExpr scope, String name) {
		super(beginLine, beginColumn, endLine, endColumn, name);
		this.qualifier = scope;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public NameExpr getQualifier() {
		return qualifier;
	}

	public void setQualifier(NameExpr qualifier) {
		this.qualifier = qualifier;
	}

}
