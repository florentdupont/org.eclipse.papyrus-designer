/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 05/10/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.expr;

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class ArrayCreationExpr extends Expression {

	private Type type;

	private int arrayCount;

	private ArrayInitializerExpr initializer;

	private List<Expression> dimensions;

	public ArrayCreationExpr() {
	}

	public ArrayCreationExpr(int beginLine, int beginColumn, int endLine, int endColumn, Type type, int arrayCount, ArrayInitializerExpr initializer) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.type = type;
		this.arrayCount = arrayCount;
		this.initializer = initializer;
		this.dimensions = null;
	}

	public ArrayCreationExpr(int beginLine, int beginColumn, int endLine, int endColumn, Type type, List<Expression> dimensions, int arrayCount) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.type = type;
		this.arrayCount = arrayCount;
		this.dimensions = dimensions;
		this.initializer = null;
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

	public List<Expression> getDimensions() {
		return dimensions;
	}

	public ArrayInitializerExpr getInitializer() {
		return initializer;
	}

	public Type getType() {
		return type;
	}


	public void setArrayCount(int arrayCount) {
		this.arrayCount = arrayCount;
	}

	public void setDimensions(List<Expression> dimensions) {
		this.dimensions = dimensions;
	}

	public void setInitializer(ArrayInitializerExpr initializer) {
		this.initializer = initializer;
	}

	public void setType(Type type) {
		this.type = type;
	}


}
