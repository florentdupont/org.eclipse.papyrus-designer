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
public class StringLiteralExpr extends LiteralExpr {

	protected String value;

	public StringLiteralExpr() {
        this.value = "";
	}

	public StringLiteralExpr(final String value) {
        if (value.contains("\n") || value.contains("\r")) {
            throw new IllegalArgumentException("Illegal literal expression: newlines (line feed or carriage return) have to be escaped");
        }
		this.value = value;
	}

	public StringLiteralExpr(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final String value) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.value = value;
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public final String getValue() {
		return value;
	}

	public final void setValue(final String value) {
		this.value = value;
	}
}
