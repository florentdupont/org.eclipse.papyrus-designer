/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.Parameter;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.Statement;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

import java.util.List;

import static org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils.*;

/**
 * Lambda expression.
 *
 * @author Raquel Pau
 */
public class LambdaExpr extends Expression {

	private List<Parameter> parameters;

	private boolean parametersEnclosed;

	private Statement body;

	public LambdaExpr() {
	}

	public LambdaExpr(int beginLine, int beginColumn, int endLine,
                      int endColumn, List<Parameter> parameters, Statement body,
                      boolean parametersEnclosed) {

		super(beginLine, beginColumn, endLine, endColumn);
		setParameters(parameters);
		setBody(body);
        setParametersEnclosed(parametersEnclosed);
	}

	public List<Parameter> getParameters() {
        parameters = ensureNotNull(parameters);
        return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
		setAsParentNodeOf(this.parameters);
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
		setAsParentNodeOf(this.body);
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public boolean isParametersEnclosed() {
		return parametersEnclosed;
	}

	public void setParametersEnclosed(boolean parametersEnclosed) {
		this.parametersEnclosed = parametersEnclosed;
	}

}
