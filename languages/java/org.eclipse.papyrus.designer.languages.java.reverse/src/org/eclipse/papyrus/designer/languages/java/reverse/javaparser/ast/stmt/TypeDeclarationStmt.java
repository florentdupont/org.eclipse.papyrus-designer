/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.TypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class TypeDeclarationStmt extends Statement {

	private TypeDeclaration typeDecl;

	public TypeDeclarationStmt() {
	}

	public TypeDeclarationStmt(final TypeDeclaration typeDecl) {
		setTypeDeclaration(typeDecl);
	}

	public TypeDeclarationStmt(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final TypeDeclaration typeDecl) {
		super(beginLine, beginColumn, endLine, endColumn);
		setTypeDeclaration(typeDecl);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public TypeDeclaration getTypeDeclaration() {
		return typeDecl;
	}

	public void setTypeDeclaration(final TypeDeclaration typeDecl) {
		this.typeDecl = typeDecl;
		setAsParentNodeOf(this.typeDecl);
	}
}
