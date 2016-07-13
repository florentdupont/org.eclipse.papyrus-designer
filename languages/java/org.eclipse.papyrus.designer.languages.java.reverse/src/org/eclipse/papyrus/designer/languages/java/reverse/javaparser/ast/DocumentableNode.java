/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.comments.JavadocComment;

/**
 * Node which can be documented through a Javadoc comment.
 */
public interface DocumentableNode {

    public JavadocComment getJavaDoc();
    public void setJavaDoc(JavadocComment javadocComment);
}
