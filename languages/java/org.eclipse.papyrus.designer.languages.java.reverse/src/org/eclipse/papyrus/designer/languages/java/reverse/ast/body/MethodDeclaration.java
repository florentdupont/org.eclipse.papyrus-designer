/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
  */
/*
 * Copyright (C) 2007 J�lio Vilmar Gesser.
 *
 * This file is part of Java 1.5 parser and Abstract Syntax Tree.
 *
 * Java 1.5 parser and Abstract Syntax Tree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Java 1.5 parser and Abstract Syntax Tree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Java 1.5 parser and Abstract Syntax Tree.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on 05/10/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.body;

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.TypeParameter;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.NameExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.BlockStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class MethodDeclaration extends BodyDeclaration {

	private int modifiers;

	private List<TypeParameter> typeParameters;

	private Type type;

	private String name;

	private List<Parameter> parameters;

	private int arrayCount;

	private List<NameExpr> throws_;

	private BlockStmt body;

	public MethodDeclaration() {
	}

	public MethodDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, JavadocComment javaDoc, int modifiers, List<AnnotationExpr> annotations, List<TypeParameter> typeParameters, Type type, String name, List<Parameter> parameters,
			int arrayCount, List<NameExpr> throws_, BlockStmt block) {
		super(beginLine, beginColumn, endLine, endColumn, annotations, javaDoc);
		this.modifiers = modifiers;
		this.typeParameters = typeParameters;
		this.type = type;
		this.name = name;
		this.parameters = parameters;
		this.arrayCount = arrayCount;
		this.throws_ = throws_;
		this.body = block;
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

	public BlockStmt getBody() {
		return body;
	}

	public int getModifiers() {
		return modifiers;
	}

	public String getName() {
		return name;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public List<NameExpr> getThrows() {
		return throws_;
	}

	public Type getType() {
		return type;
	}

	public List<TypeParameter> getTypeParameters() {
		return typeParameters;
	}

	public void setArrayCount(int arrayCount) {
		this.arrayCount = arrayCount;
	}

	public void setBody(BlockStmt body) {
		this.body = body;
	}

	public void setModifiers(int modifiers) {
		this.modifiers = modifiers;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public void setThrows(List<NameExpr> throws_) {
		this.throws_ = throws_;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setTypeParameters(List<TypeParameter> typeParameters) {
		this.typeParameters = typeParameters;
	}
}
