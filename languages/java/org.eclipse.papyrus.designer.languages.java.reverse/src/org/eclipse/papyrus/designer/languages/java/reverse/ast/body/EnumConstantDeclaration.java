/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 05/11/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.body;

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class EnumConstantDeclaration extends BodyDeclaration {

	private String name;

	private List<Expression> args;

	private List<BodyDeclaration> classBody;

	public EnumConstantDeclaration() {
	}

	public EnumConstantDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, JavadocComment javaDoc, List<AnnotationExpr> annotations, String name, List<Expression> args, List<BodyDeclaration> classBody) {
		super(beginLine, beginColumn, endLine, endColumn, annotations, javaDoc);
		this.name = name;
		this.args = args;
		this.classBody = classBody;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public List<Expression> getArgs() {
		return args;
	}

	public List<BodyDeclaration> getClassBody() {
		return classBody;
	}

	public String getName() {
		return name;
	}

	public void setArgs(List<Expression> args) {
		this.args = args;
	}

	public void setClassBody(List<BodyDeclaration> classBody) {
		this.classBody = classBody;
	}

	public void setName(String name) {
		this.name = name;
	}
}
