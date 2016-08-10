/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
  */
/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2015 The JavaParser Team.
 *
 * This file is part of JavaParser.
 * 
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License 
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */

package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.Node;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class VariableDeclarator extends Node {

    private VariableDeclaratorId id;

    private Expression init;

    public VariableDeclarator() {
    }

    public VariableDeclarator(VariableDeclaratorId id) {
        setId(id);
    }

    /**
     * Defines the declaration of a variable.
     * @param id The identifier for this variable. IE. The variables name.
     * @param init What this variable should be initialized to.
     *             An {@link org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.AssignExpr} is unnecessary as the <code>=</code> operator is already added.
     */
    public VariableDeclarator(VariableDeclaratorId id, Expression init) {
    	setId(id);
    	setInit(init);
    }

    public VariableDeclarator(int beginLine, int beginColumn, int endLine, int endColumn, VariableDeclaratorId id, Expression init) {
        super(beginLine, beginColumn, endLine, endColumn);
        setId(id);
        setInit(init);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public VariableDeclaratorId getId() {
        return id;
    }

    public Expression getInit() {
        return init;
    }

    public void setId(VariableDeclaratorId id) {
        this.id = id;
		setAsParentNodeOf(this.id);
    }

    public void setInit(Expression init) {
        this.init = init;
		setAsParentNodeOf(this.init);
    }
}
