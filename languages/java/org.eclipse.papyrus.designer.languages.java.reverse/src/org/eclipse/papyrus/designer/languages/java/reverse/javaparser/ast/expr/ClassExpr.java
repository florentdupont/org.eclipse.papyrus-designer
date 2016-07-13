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

/**
 * Defines an expression that accesses the class of a type.
 * Example:
 * <code>
 *     Object.class
 * </code>
 * @author Julio Vilmar Gesser
 */
public final class ClassExpr extends Expression implements TypedNode {

    private Type type;

    public ClassExpr() {
    }

    public ClassExpr(Type type) {
       setType(type);
    }

    public ClassExpr(int beginLine, int beginColumn, int endLine, int endColumn, Type type) {
        super(beginLine, beginColumn, endLine, endColumn);
        setType(type);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
		setAsParentNodeOf(this.type);
    }
}
