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

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.DocumentableNode;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.comments.JavadocComment;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.BlockStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class InitializerDeclaration extends BodyDeclaration implements DocumentableNode {

    private boolean isStatic;

    private BlockStmt block;

    public InitializerDeclaration() {
    }

    public InitializerDeclaration(boolean isStatic, BlockStmt block) {
        super(null);
        setStatic(isStatic);
        setBlock(block);
    }

    public InitializerDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, boolean isStatic, BlockStmt block) {
        super(beginLine, beginColumn, endLine, endColumn, null);
        setStatic(isStatic);
        setBlock(block);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public BlockStmt getBlock() {
        return block;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setBlock(BlockStmt block) {
        this.block = block;
		setAsParentNodeOf(this.block);
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
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
