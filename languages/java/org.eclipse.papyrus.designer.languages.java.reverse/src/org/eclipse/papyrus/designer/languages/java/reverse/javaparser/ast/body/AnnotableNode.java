/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.AnnotationExpr;

import java.util.List;

/**
 * An element which can be the target of annotations.
 *
 * @author Federico Tomassetti
 * @since July 2014
 */
public interface AnnotableNode {
    public List<AnnotationExpr> getAnnotations();
}
