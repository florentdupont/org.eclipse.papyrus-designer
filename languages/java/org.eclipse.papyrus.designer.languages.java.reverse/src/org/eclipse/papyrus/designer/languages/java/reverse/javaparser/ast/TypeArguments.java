/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.Type;

import java.util.Collections;
import java.util.List;

import static org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils.ensureNotNull;
import static java.util.Collections.unmodifiableList;

public class TypeArguments {
    public static final TypeArguments EMPTY = withArguments(Collections.<Type>emptyList());

    private final List<Type> typeArguments;
    private final boolean usesDiamondOperator;

    private TypeArguments(List<Type> typeArguments, boolean usesDiamondOperator) {
        this.typeArguments = ensureNotNull(typeArguments);
        this.usesDiamondOperator = usesDiamondOperator;
    }

    public List<Type> getTypeArguments() {
        return unmodifiableList(typeArguments);
    }

    public boolean isUsingDiamondOperator() {
        return usesDiamondOperator;
    }

    public static TypeArguments withDiamondOperator() {
        return new TypeArguments(Collections.<Type>emptyList(), true);
    }

    public static TypeArguments withArguments(List<Type> typeArguments) {
        return new TypeArguments(typeArguments, false);
    }
}