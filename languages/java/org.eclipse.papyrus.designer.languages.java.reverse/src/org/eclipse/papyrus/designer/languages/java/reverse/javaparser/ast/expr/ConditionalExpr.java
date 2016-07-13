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
public final class ConditionalExpr extends Expression {

    private Expression condition;

    private Expression thenExpr;

    private Expression elseExpr;

    public ConditionalExpr() {
    }

    public ConditionalExpr(Expression condition, Expression thenExpr, Expression elseExpr) {
        setCondition(condition);
        setThenExpr(thenExpr);
        setElseExpr(elseExpr);
    }

    public ConditionalExpr(int beginLine, int beginColumn, int endLine, int endColumn, Expression condition, Expression thenExpr, Expression elseExpr) {
        super(beginLine, beginColumn, endLine, endColumn);
        setCondition(condition);
        setThenExpr(thenExpr);
        setElseExpr(elseExpr);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getElseExpr() {
        return elseExpr;
    }

    public Expression getThenExpr() {
        return thenExpr;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
		setAsParentNodeOf(this.condition);
    }

    public void setElseExpr(Expression elseExpr) {
        this.elseExpr = elseExpr;
		setAsParentNodeOf(this.elseExpr);
    }

    public void setThenExpr(Expression thenExpr) {
        this.thenExpr = thenExpr;
		setAsParentNodeOf(this.thenExpr);
    }
}
