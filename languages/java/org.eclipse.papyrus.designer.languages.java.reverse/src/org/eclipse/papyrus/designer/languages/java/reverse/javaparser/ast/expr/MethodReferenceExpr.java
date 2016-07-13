/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.TypeParameter;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.List;

import static org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils.*;

/**
 * Method reference expressions introduced in Java 8 specifically designed to simplify lambda Expressions.
 * These are some examples:
 *
 * System.out::println; 
 *
 * (test ? stream.map(String::trim) : stream)::toArray; 
 * @author Raquel Pau
 *
 */
public class MethodReferenceExpr extends Expression {

    private Expression scope;

    private List<TypeParameter> typeParameters;

    private String identifier;

    public MethodReferenceExpr() {
    }

    public MethodReferenceExpr(int beginLine, int beginColumn, int endLine,
                               int endColumn, Expression scope,
                               List<TypeParameter> typeParameters, String identifier) {

        super(beginLine, beginColumn, endLine, endColumn);
        setIdentifier(identifier);
        setScope(scope);
        setTypeParameters(typeParameters);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {

        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public Expression getScope() {
        return scope;
    }

    public void setScope(Expression scope) {
        this.scope = scope;
        setAsParentNodeOf(this.scope);
    }

    public List<TypeParameter> getTypeParameters() {
        typeParameters = ensureNotNull(typeParameters);
        return typeParameters;
    }

    public void setTypeParameters(List<TypeParameter> typeParameters) {
        this.typeParameters = typeParameters;
        setAsParentNodeOf(this.typeParameters);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

}
