/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 03/11/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.body;

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.Node;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class Parameter extends Node {

	private int modifiers;

	private List<AnnotationExpr> annotations;

	private Type type;

	private boolean isVarArgs;

	private VariableDeclaratorId id;

	public Parameter() {
	}

	public Parameter(int beginLine, int beginColumn, int endLine, int endColumn, int modifiers, List<AnnotationExpr> annotations, Type type, boolean isVarArgs, VariableDeclaratorId id) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.modifiers = modifiers;
		this.annotations = annotations;
		this.type = type;
		this.isVarArgs = isVarArgs;
		this.id = id;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public List<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	public VariableDeclaratorId getId() {
		return id;
	}

	public int getModifiers() {
		return modifiers;
	}

	public Type getType() {
		return type;
	}

	public boolean isVarArgs() {
		return isVarArgs;
	}

	public void setAnnotations(List<AnnotationExpr> annotations) {
		this.annotations = annotations;
	}

	public void setId(VariableDeclaratorId id) {
		this.id = id;
	}

	public void setModifiers(int modifiers) {
		this.modifiers = modifiers;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setVarArgs(boolean isVarArgs) {
		this.isVarArgs = isVarArgs;
	}
}
