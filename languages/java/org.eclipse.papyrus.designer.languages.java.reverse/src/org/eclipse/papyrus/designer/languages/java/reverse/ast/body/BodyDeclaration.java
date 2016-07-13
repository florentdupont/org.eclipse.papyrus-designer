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

import org.eclipse.papyrus.designer.languages.java.reverse.ast.Node;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.AnnotationExpr;

/**
 * @author Julio Vilmar Gesser
 */
public abstract class BodyDeclaration extends Node {

	private JavadocComment javaDoc;

	private List<AnnotationExpr> annotations;

	public BodyDeclaration() {
	}

	public BodyDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, List<AnnotationExpr> annotations, JavadocComment javaDoc) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.javaDoc = javaDoc;
		this.annotations = annotations;
	}

	public final JavadocComment getJavaDoc() {
		return javaDoc;
	}

	public final List<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	public final void setJavaDoc(JavadocComment javaDoc) {
		this.javaDoc = javaDoc;
	}

	public final void setAnnotations(List<AnnotationExpr> annotations) {
		this.annotations = annotations;
	}

}
