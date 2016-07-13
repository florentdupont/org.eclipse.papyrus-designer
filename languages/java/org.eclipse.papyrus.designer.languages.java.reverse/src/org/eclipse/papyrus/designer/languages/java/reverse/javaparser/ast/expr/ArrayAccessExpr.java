/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class ArrayAccessExpr extends Expression {

    private Expression name;

    private Expression index;

    public ArrayAccessExpr() {
    }

    public ArrayAccessExpr(Expression name, Expression index) {
        setName(name);
        setIndex(index);
    }

    public ArrayAccessExpr(int beginLine, int beginColumn, int endLine, int endColumn, Expression name, Expression index) {
        super(beginLine, beginColumn, endLine, endColumn);
        setName(name);
        setIndex(index);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public Expression getIndex() {
        return index;
    }

    public Expression getName() {
        return name;
    }

    public void setIndex(Expression index) {
        this.index = index;
		setAsParentNodeOf(this.index);
    }

    public void setName(Expression name) {
        this.name = name;
		setAsParentNodeOf(this.name);
    }
}
