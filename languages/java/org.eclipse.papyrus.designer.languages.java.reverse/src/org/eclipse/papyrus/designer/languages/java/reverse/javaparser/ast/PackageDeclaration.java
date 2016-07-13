/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.NameExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * This class represents the package declaration. The package declaration is
 * optional for the {@link CompilationUnit}.
 * </p>
 * The PackageDeclaration is constructed following the syntax:<br>
 * <pre>
 * {@code
 * PackageDeclaration ::= ( }{@link AnnotationExpr}{@code )* "package" }{@link NameExpr}{@code ) ";"
 * }
 * </pre>
 * @author Julio Vilmar Gesser
 */
public final class PackageDeclaration extends Node {

    private List<AnnotationExpr> annotations;

    private NameExpr name;

    public PackageDeclaration() {
    }

    public PackageDeclaration(NameExpr name) {
        setName(name);
    }

    public PackageDeclaration(List<AnnotationExpr> annotations, NameExpr name) {
        setAnnotations(annotations);
        setName(name);
    }

    public PackageDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, List<AnnotationExpr> annotations, NameExpr name) {
        super(beginLine, beginColumn, endLine, endColumn);
        setAnnotations(annotations);
        setName(name);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    /**
     * Retrieves the list of annotations declared before the package
     * declaration. Return <code>null</code> if there are no annotations.
     * 
     * @return list of annotations or <code>null</code>
     */
    public List<AnnotationExpr> getAnnotations() {
        annotations = Utils.ensureNotNull(annotations);
        return annotations;
    }

    /**
     * Return the name expression of the package.
     *
     * @return the name of the package
     */
    public NameExpr getName() {
        return name;
    }

    /**
     * Get full package name.
     */
    public String getPackageName() {
        return name.toString();
    }

    /**
     * @param annotations
     *            the annotations to set
     */
    public void setAnnotations(List<AnnotationExpr> annotations) {
        this.annotations = annotations;
        setAsParentNodeOf(this.annotations);
    }

    /**
     * Sets the name of this package declaration.
     * 
     * @param name
     *            the name to set
     */
    public void setName(NameExpr name) {
        this.name = name;
        setAsParentNodeOf(this.name);
    }

}
