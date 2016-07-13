/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.NamedNode;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.TypeArguments;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.List;

/**
 * @author Julio Vilmar Gesser
 */
public final class ClassOrInterfaceType extends Type implements NamedNode {

    private ClassOrInterfaceType scope;

    private String name;

    private TypeArguments typeArguments = TypeArguments.EMPTY;

    public ClassOrInterfaceType() {
    }

    public ClassOrInterfaceType(final String name) {
        setName(name);
    }

    public ClassOrInterfaceType(final ClassOrInterfaceType scope, final String name) {
        setScope(scope);
        setName(name);
    }

    /**
     *
     * @deprecated use the other constructor that takes {@link TypeArguments}
     */
    @Deprecated
    public ClassOrInterfaceType(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
                                final ClassOrInterfaceType scope, final String name, final List<Type> typeArgs) {
        this(beginLine, beginColumn, endLine, endColumn, scope, name, TypeArguments.withArguments(typeArgs));
    }

    public ClassOrInterfaceType(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
                                final ClassOrInterfaceType scope, final String name, final TypeArguments typeArguments) {
        super(beginLine, beginColumn, endLine, endColumn);
        setScope(scope);
        setName(name);
        setTypeArguments(typeArguments);
    }

    @Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
        return v.visit(this, arg);
    }

    @Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
        v.visit(this, arg);
    }

    @Override
    public String getName() {
        return name;
    }

    public ClassOrInterfaceType getScope() {
        return scope;
    }

    public List<Type> getTypeArgs() {
        return typeArguments.getTypeArguments();
    }

    public TypeArguments getTypeArguments() {
        return typeArguments;
    }

    public boolean isUsingDiamondOperator() {
        return typeArguments.isUsingDiamondOperator();
    }

    public boolean isBoxedType() {
        return PrimitiveType.unboxMap.containsKey(name);
    }

    public PrimitiveType toUnboxedType() throws UnsupportedOperationException {
        if (!isBoxedType()) {
            throw new UnsupportedOperationException(name + " isn't a boxed type.");
        }
        return new PrimitiveType(PrimitiveType.unboxMap.get(name));
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setScope(final ClassOrInterfaceType scope) {
        this.scope = scope;
        setAsParentNodeOf(this.scope);
    }

    /**
     * Allows you to set the generic arguments
     * @param typeArgs The list of types of the generics
     */
    public void setTypeArgs(final List<Type> typeArgs) {
        setTypeArguments(TypeArguments.withArguments(typeArgs));
    }

    public void setTypeArguments(TypeArguments typeArguments) {
        this.typeArguments = typeArguments;
        setAsParentNodeOf(this.typeArguments.getTypeArguments());
    }
}
