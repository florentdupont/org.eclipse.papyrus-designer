/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.List;

import static org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils.*;

/**
 * @author Julio Vilmar Gesser
 */
public final class ForStmt extends Statement {

	private List<Expression> init;

	private Expression compare;

	private List<Expression> update;

	private Statement body;

	public ForStmt() {
	}

	public ForStmt(final List<Expression> init, final Expression compare,
			final List<Expression> update, final Statement body) {
		setCompare(compare);
		setInit(init);
		setUpdate(update);
		setBody(body);
	}

	public ForStmt(final int beginLine, final int beginColumn,
			final int endLine, final int endColumn,
			final List<Expression> init, final Expression compare,
			final List<Expression> update, final Statement body) {
		super(beginLine, beginColumn, endLine, endColumn);
		setCompare(compare);
		setInit(init);
		setUpdate(update);
		setBody(body);
	}

	@Override
	public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public Statement getBody() {
		return body;
	}

	public Expression getCompare() {
		return compare;
	}

	public List<Expression> getInit() {
        init = ensureNotNull(init);
        return init;
	}

	public List<Expression> getUpdate() {
        update = ensureNotNull(update);
        return update;
	}

	public void setBody(final Statement body) {
		this.body = body;
		setAsParentNodeOf(this.body);
	}

	public void setCompare(final Expression compare) {
		this.compare = compare;
		setAsParentNodeOf(this.compare);
	}

	public void setInit(final List<Expression> init) {
		this.init = init;
		setAsParentNodeOf(this.init);
	}

	public void setUpdate(final List<Expression> update) {
		this.update = update;
		setAsParentNodeOf(this.update);
	}
}
