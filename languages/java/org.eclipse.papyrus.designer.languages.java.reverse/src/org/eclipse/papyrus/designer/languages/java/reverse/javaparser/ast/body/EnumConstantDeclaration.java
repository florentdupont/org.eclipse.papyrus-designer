/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.DocumentableNode;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.NamedNode;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.comments.JavadocComment;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.List;

import static org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils.ensureNotNull;

/**
 * @author Julio Vilmar Gesser
 */
public final class EnumConstantDeclaration extends BodyDeclaration implements DocumentableNode, NamedNode {

    private String name;

    private List<Expression> args;

    private List<BodyDeclaration> classBody;

    public EnumConstantDeclaration() {
    }

    public EnumConstantDeclaration(String name) {
        setName(name);
    }

    public EnumConstantDeclaration(List<AnnotationExpr> annotations, String name, List<Expression> args, List<BodyDeclaration> classBody) {
        super(annotations);
        setName(name);
        setArgs(args);
        setClassBody(classBody);
    }

    public EnumConstantDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, List<AnnotationExpr> annotations, String name, List<Expression> args, List<BodyDeclaration> classBody) {
        super(beginLine, beginColumn, endLine, endColumn, annotations);
        setName(name);
        setArgs(args);
        setClassBody(classBody);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public List<Expression> getArgs() {
        args = ensureNotNull(args);
        return args;
    }

    public List<BodyDeclaration> getClassBody() {
        classBody = ensureNotNull(classBody);
        return classBody;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setArgs(List<Expression> args) {
        this.args = args;
		setAsParentNodeOf(this.args);
    }

    public void setClassBody(List<BodyDeclaration> classBody) {
        this.classBody = classBody;
		setAsParentNodeOf(this.classBody);
    }

    public void setName(String name) {
        this.name = name;
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
