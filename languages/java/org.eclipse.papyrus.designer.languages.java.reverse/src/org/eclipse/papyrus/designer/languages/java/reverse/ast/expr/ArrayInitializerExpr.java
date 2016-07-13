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

import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class ArrayInitializerExpr extends Expression {

	private List<Expression> values;

	public ArrayInitializerExpr() {
	}

	public ArrayInitializerExpr(int beginLine, int beginColumn, int endLine, int endColumn, List<Expression> values) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.values = values;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public List<Expression> getValues() {
		return values;
	}

	public void setValues(List<Expression> values) {
		this.values = values;
	}

}
