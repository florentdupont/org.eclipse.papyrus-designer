/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 05/10/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.body;

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.ClassOrInterfaceType;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class EnumDeclaration extends TypeDeclaration {

	private List<ClassOrInterfaceType> implementsList;

	private List<EnumConstantDeclaration> entries;

	public EnumDeclaration() {
	}

	public EnumDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, JavadocComment javaDoc, int modifiers, List<AnnotationExpr> annotations, String name, List<ClassOrInterfaceType> implementsList, List<EnumConstantDeclaration> entries,
			List<BodyDeclaration> members) {
		super(beginLine, beginColumn, endLine, endColumn, annotations, javaDoc, name, modifiers, members);
		this.implementsList = implementsList;
		this.entries = entries;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public List<EnumConstantDeclaration> getEntries() {
		return entries;
	}

	public List<ClassOrInterfaceType> getImplements() {
		return implementsList;
	}

	public void setEntries(List<EnumConstantDeclaration> entries) {
		this.entries = entries;
	}

	public void setImplements(List<ClassOrInterfaceType> implementsList) {
		this.implementsList = implementsList;
	}
}
