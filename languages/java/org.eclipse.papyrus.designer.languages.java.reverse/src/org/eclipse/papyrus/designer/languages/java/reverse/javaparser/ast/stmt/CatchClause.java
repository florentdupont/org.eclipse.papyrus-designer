/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.Node;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.MultiTypeParameter;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.Parameter;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.VariableDeclaratorId;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.UnionType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.List;

/**
 * @author Julio Vilmar Gesser
 */
public final class CatchClause extends Node {

    private Parameter param;

    private BlockStmt catchBlock;

    public CatchClause() {
    }

    public CatchClause(final Parameter param, final BlockStmt catchBlock) {
        setParam(param);
        setCatchBlock(catchBlock);
    }

    public CatchClause(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
    	    final int exceptModifier, final List<AnnotationExpr> exceptAnnotations, final Type exceptTypes,
    	    final VariableDeclaratorId exceptId, final BlockStmt catchBlock) {
        super(beginLine, beginColumn, endLine, endColumn);
        setParam(new Parameter(beginLine, beginColumn, endLine, endColumn, exceptModifier, exceptAnnotations, exceptTypes, false, exceptId));
        setCatchBlock(catchBlock);
    }

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public BlockStmt getCatchBlock() {
		return catchBlock;
	}

	public Parameter getParam() {
		return param;
	}

	public void setCatchBlock(final BlockStmt catchBlock) {
		this.catchBlock = catchBlock;
		setAsParentNodeOf(this.catchBlock);
	}

	public void setParam(final Parameter param) {
		this.param = param;
		setAsParentNodeOf(this.param);
	}
}
