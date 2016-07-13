/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.TypedNode;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.List;

import static org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils.*;

/**
 * @author Julio Vilmar Gesser
 */
public final class ReferenceType extends Type implements TypedNode {

	private Type type;

	private int arrayCount;

    private List<List<AnnotationExpr>> arraysAnnotations;

    public ReferenceType() {
	}

	public ReferenceType(final Type type) {
		setType(type);
	}

	public ReferenceType(final Type type, final int arrayCount) {
		setType(type);
		setArrayCount(arrayCount);
	}

	public ReferenceType(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final Type type, final int arrayCount) {
		super(beginLine, beginColumn, endLine, endColumn);
		setType(type);
		setArrayCount(arrayCount);
	}

    public ReferenceType(int beginLine, int beginColumn, int endLine,
                         int endColumn, Type type, int arrayCount,
                         List<AnnotationExpr> annotations,
                         List<List<AnnotationExpr>> arraysAnnotations) {
        super(beginLine, beginColumn, endLine, endColumn, annotations);
        setType(type);
        setArrayCount(arrayCount);
        this.arraysAnnotations = arraysAnnotations;
    }

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public int getArrayCount() {
		return arrayCount;
	}

	@Override
	public Type getType() {
		return type;
	}

	public void setArrayCount(final int arrayCount) {
		this.arrayCount = arrayCount;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
		setAsParentNodeOf(this.type);
	}

	/**
	 * <p>Arrays annotations are annotations on the arrays modifiers of the type.
	 * Consider this example:</p>
	 * 
	 * <p><pre>
	 * {@code
	 * int @Ann1 [] @Ann2 [] array;
	 * }</pre></p>
	 * 
	 * <p>in this this method will return a list with the annotation expressions <pre>@Ann1</pre>
	 * and <pre>@Ann2</pre></p>
	 * 
	 * <p>Note that the first list element of arraysAnnotations will refer to the first array modifier encountered.
	 * Considering the example the first element will be a list containing just @Ann1 while the second element will
	 * be a list containing just @Ann2.
	 * </p>
	 *
	 * <p>This property is guaranteed to hold: <pre>{@code getArraysAnnotations().size() == getArrayCount()}</pre>
	 * If a certain array modifier has no annotation the corresponding entry of arraysAnnotations will be null</p>
	 */
    public List<List<AnnotationExpr>> getArraysAnnotations() {
        arraysAnnotations = ensureNotNull(arraysAnnotations);
        return arraysAnnotations;
    }

	/**
	 * For a description of the arrayAnnotations field refer to {@link #getArraysAnnotations()}
	 */
    public void setArraysAnnotations(List<List<AnnotationExpr>> arraysAnnotations) {
        this.arraysAnnotations = arraysAnnotations;
    }
}
