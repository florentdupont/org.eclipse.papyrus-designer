/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.TypedNode;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.List;

import static org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils.*;

/**
 * @author Julio Vilmar Gesser
 */
public final class ArrayCreationExpr extends Expression implements TypedNode {

    private Type type;

    private int arrayCount;

    private ArrayInitializerExpr initializer;

    private List<Expression> dimensions;

    private List<List<AnnotationExpr>> arraysAnnotations;

    public ArrayCreationExpr() {
    }

    public ArrayCreationExpr(Type type, int arrayCount, ArrayInitializerExpr initializer) {
        setType(type);
        setArrayCount(arrayCount);
        setInitializer(initializer);
        setDimensions(null);
    }

    public ArrayCreationExpr(int beginLine, int beginColumn, int endLine, int endColumn, Type type, int arrayCount, ArrayInitializerExpr initializer) {
        super(beginLine, beginColumn, endLine, endColumn);
        setType(type);
        setArrayCount(arrayCount);
        setInitializer(initializer);
        setDimensions(null);
    }

    public ArrayCreationExpr(Type type, List<Expression> dimensions, int arrayCount) {
        setType(type);
        setArrayCount(arrayCount);
        setDimensions(dimensions);
        setInitializer(null);
    }

    public ArrayCreationExpr(int beginLine, int beginColumn, int endLine, int endColumn, Type type, List<Expression> dimensions, int arrayCount) {
        super(beginLine, beginColumn, endLine, endColumn);
        setType(type);
        setArrayCount(arrayCount);
        setDimensions(dimensions);
        setInitializer(null);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public int getArrayCount() {
        return arrayCount;
    }

    public List<Expression> getDimensions() {
        dimensions = ensureNotNull(dimensions);
        return dimensions;
    }

    public ArrayInitializerExpr getInitializer() {
        return initializer;
    }

    @Override
    public Type getType() {
        return type;
    }

    public void setArrayCount(int arrayCount) {
        this.arrayCount = arrayCount;
    }

    public void setDimensions(List<Expression> dimensions) {
        this.dimensions = dimensions;
		setAsParentNodeOf(this.dimensions);
    }

    public void setInitializer(ArrayInitializerExpr initializer) {
        this.initializer = initializer;
		setAsParentNodeOf(this.initializer);
    }

    @Override
    public void setType(Type type) {
        this.type = type;
		setAsParentNodeOf(this.type);
    }

    public List<List<AnnotationExpr>> getArraysAnnotations() {
        arraysAnnotations = ensureNotNull(arraysAnnotations);
        return arraysAnnotations;
    }

    public void setArraysAnnotations(
            List<List<AnnotationExpr>> arraysAnnotations) {
        this.arraysAnnotations = arraysAnnotations;
    }
}
