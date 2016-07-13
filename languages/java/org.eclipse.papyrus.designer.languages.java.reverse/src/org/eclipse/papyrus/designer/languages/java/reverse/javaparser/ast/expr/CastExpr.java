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
 * @author Julio Vilmar Gesser
 */
public final class CastExpr extends Expression implements TypedNode {

    private Type type;

    private Expression expr;

    public CastExpr() {
    }

    public CastExpr(Type type, Expression expr) {
    	setType(type);
    	setExpr(expr);
    }

    public CastExpr(int beginLine, int beginColumn, int endLine, int endColumn, Type type, Expression expr) {
        super(beginLine, beginColumn, endLine, endColumn);
        setType(type);
    	setExpr(expr);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public Expression getExpr() {
        return expr;
    }

    @Override
    public Type getType() {
        return type;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
		setAsParentNodeOf(this.expr);
    }

    @Override
    public void setType(Type type) {
        this.type = type;
		setAsParentNodeOf(this.type);
    }
}
