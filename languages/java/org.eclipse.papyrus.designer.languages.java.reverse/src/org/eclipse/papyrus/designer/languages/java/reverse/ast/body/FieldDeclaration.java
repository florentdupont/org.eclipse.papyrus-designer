/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 05/10/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.body;

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class FieldDeclaration extends BodyDeclaration {

	private int modifiers;

	private Type type;

	private List<VariableDeclarator> variables;

	public FieldDeclaration() {
	}

	public FieldDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, JavadocComment javaDoc, int modifiers, List<AnnotationExpr> annotations, Type type, List<VariableDeclarator> variables) {
		super(beginLine, beginColumn, endLine, endColumn, annotations, javaDoc);
		this.modifiers = modifiers;
		this.type = type;
		this.variables = variables;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public int getModifiers() {
		return modifiers;
	}

	public Type getType() {
		return type;
	}

	public List<VariableDeclarator> getVariables() {
		return variables;
	}

	public void setModifiers(int modifiers) {
		this.modifiers = modifiers;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setVariables(List<VariableDeclarator> variables) {
		this.variables = variables;
	}
}
