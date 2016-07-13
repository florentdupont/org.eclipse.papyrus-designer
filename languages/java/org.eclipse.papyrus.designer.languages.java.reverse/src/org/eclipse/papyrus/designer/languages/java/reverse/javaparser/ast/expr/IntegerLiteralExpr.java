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
public class IntegerLiteralExpr extends StringLiteralExpr {

	private static final String UNSIGNED_MIN_VALUE = "2147483648";

	protected static final String MIN_VALUE = "-" + UNSIGNED_MIN_VALUE;

	public IntegerLiteralExpr() {
	}

	public IntegerLiteralExpr(final String value) {
		super(value);
	}

	public IntegerLiteralExpr(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final String value) {
		super(beginLine, beginColumn, endLine, endColumn, value);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public final boolean isMinValue() {
		return value != null && //
				value.length() == 10 && //
				value.equals(UNSIGNED_MIN_VALUE);
	}
}
