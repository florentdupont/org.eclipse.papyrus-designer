/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.Node;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils;

import java.util.List;

/**
 * @author Julio Vilmar Gesser
 */
public abstract class BodyDeclaration extends Node implements AnnotableNode {

    private List<AnnotationExpr> annotations;

    public BodyDeclaration() {
    }

    public BodyDeclaration(List<AnnotationExpr> annotations) {
    	setAnnotations(annotations);
    }

    public BodyDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, List<AnnotationExpr> annotations) {
        super(beginLine, beginColumn, endLine, endColumn);
    	setAnnotations(annotations);
    }

    @Override
    public final List<AnnotationExpr> getAnnotations() {
        annotations = Utils.ensureNotNull(annotations);
        return annotations;
    }

    /**
     *
     * @param annotations a null value is currently treated as an empty list. This behavior could change
     *                    in the future, so please avoid passing null
     */
    public final void setAnnotations(List<AnnotationExpr> annotations) {
        this.annotations = annotations;
		setAsParentNodeOf(this.annotations);
    }
}
