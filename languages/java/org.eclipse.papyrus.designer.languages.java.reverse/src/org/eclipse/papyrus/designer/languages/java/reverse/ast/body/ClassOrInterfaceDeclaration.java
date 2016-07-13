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

import org.eclipse.papyrus.designer.languages.java.reverse.ast.TypeParameter;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.ClassOrInterfaceType;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class ClassOrInterfaceDeclaration extends TypeDeclaration {

	private boolean interface_;

	private List<TypeParameter> typeParameters;

	private List<ClassOrInterfaceType> extendsList;

	private List<ClassOrInterfaceType> implementsList;

	public ClassOrInterfaceDeclaration() {
	}

	public ClassOrInterfaceDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, JavadocComment javaDoc, int modifiers, List<AnnotationExpr> annotations, boolean isInterface, String name, List<TypeParameter> typeParameters,
			List<ClassOrInterfaceType> extendsList, List<ClassOrInterfaceType> implementsList, List<BodyDeclaration> members) {
		super(beginLine, beginColumn, endLine, endColumn, annotations, javaDoc, name, modifiers, members);
		this.interface_ = isInterface;
		this.typeParameters = typeParameters;
		this.extendsList = extendsList;
		this.implementsList = implementsList;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public List<ClassOrInterfaceType> getExtends() {
		return extendsList;
	}

	public List<ClassOrInterfaceType> getImplements() {
		return implementsList;
	}

	public List<TypeParameter> getTypeParameters() {
		return typeParameters;
	}

	public boolean isInterface() {
		return interface_;
	}

	public void setExtends(List<ClassOrInterfaceType> extendsList) {
		this.extendsList = extendsList;
	}

	public void setImplements(List<ClassOrInterfaceType> implementsList) {
		this.implementsList = implementsList;
	}

	public void setInterface(boolean interface_) {
		this.interface_ = interface_;
	}

	public void setTypeParameters(List<TypeParameter> typeParameters) {
		this.typeParameters = typeParameters;
	}
}
