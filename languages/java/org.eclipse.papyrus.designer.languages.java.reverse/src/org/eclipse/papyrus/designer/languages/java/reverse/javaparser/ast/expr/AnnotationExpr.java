/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

/**
 * @author Julio Vilmar Gesser
 */
public abstract class AnnotationExpr extends Expression {

	protected NameExpr name;

	public AnnotationExpr() {}

	public AnnotationExpr(int beginLine, int beginColumn, int endLine,
			int endColumn) {
		super(beginLine, beginColumn, endLine, endColumn);
	}

	public NameExpr getName() {
		return name;
	}

	public void setName(NameExpr name) {
		this.name = name;
		setAsParentNodeOf(name);
	}
}
