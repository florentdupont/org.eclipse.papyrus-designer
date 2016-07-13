/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.TypeParameter;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.comments.JavadocComment;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.ClassOrInterfaceType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.List;

import static org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils.ensureNotNull;

/**
 * @author Julio Vilmar Gesser
 */
public final class ClassOrInterfaceDeclaration extends TypeDeclaration {

	private boolean interface_;

	private List<TypeParameter> typeParameters;

	// Can contain more than one item if this is an interface
	private List<ClassOrInterfaceType> extendsList;

	private List<ClassOrInterfaceType> implementsList;

	public ClassOrInterfaceDeclaration() {
	}

	public ClassOrInterfaceDeclaration(final int modifiers, final boolean isInterface, final String name) {
		super(modifiers, name);
		setInterface(isInterface);
	}

	public ClassOrInterfaceDeclaration(final int modifiers,
			final List<AnnotationExpr> annotations, final boolean isInterface, final String name,
			final List<TypeParameter> typeParameters, final List<ClassOrInterfaceType> extendsList,
			final List<ClassOrInterfaceType> implementsList, final List<BodyDeclaration> members) {
		super(annotations, modifiers, name, members);
		setInterface(isInterface);
		setTypeParameters(typeParameters);
		setExtends(extendsList);
		setImplements(implementsList);
	}

	public ClassOrInterfaceDeclaration(final int beginLine, final int beginColumn, final int endLine,
			final int endColumn, final int modifiers,
			final List<AnnotationExpr> annotations, final boolean isInterface, final String name,
			final List<TypeParameter> typeParameters, final List<ClassOrInterfaceType> extendsList,
			final List<ClassOrInterfaceType> implementsList, final List<BodyDeclaration> members) {
		super(beginLine, beginColumn, endLine, endColumn, annotations, modifiers, name, members);
		setInterface(isInterface);
		setTypeParameters(typeParameters);
		setExtends(extendsList);
		setImplements(implementsList);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public List<ClassOrInterfaceType> getExtends() {
        extendsList = ensureNotNull(extendsList);
        return extendsList;
	}

	public List<ClassOrInterfaceType> getImplements() {
        implementsList = ensureNotNull(implementsList);
        return implementsList;
	}

	public List<TypeParameter> getTypeParameters() {
		typeParameters = ensureNotNull(typeParameters);
        return typeParameters;
	}

    public boolean isInterface() {
		return interface_;
	}

    /**
     * 
     * @param extendsList a null value is currently treated as an empty list. This behavior could change
     *                    in the future, so please avoid passing null
     */
	public void setExtends(final List<ClassOrInterfaceType> extendsList) {
		this.extendsList = extendsList;
		setAsParentNodeOf(this.extendsList);
	}

    /**
     * 
     * @param implementsList a null value is currently treated as an empty list. This behavior could change
     *                       in the future, so please avoid passing null
     */
	public void setImplements(final List<ClassOrInterfaceType> implementsList) {
		this.implementsList = implementsList;
		setAsParentNodeOf(this.implementsList);
	}

	public void setInterface(final boolean interface_) {
		this.interface_ = interface_;
	}

    /**
     *
     * @param typeParameters a null value is currently treated as an empty list. This behavior could change
     *                       in the future, so please avoid passing null
     */
	public void setTypeParameters(final List<TypeParameter> typeParameters) {
		this.typeParameters = typeParameters;
		setAsParentNodeOf(this.typeParameters);
	}

    @Override
    public void setJavaDoc(JavadocComment javadocComment) {
        this.javadocComment = javadocComment;
    }

    @Override
    public JavadocComment getJavaDoc() {
        return javadocComment;
    }

    private JavadocComment javadocComment;
}
