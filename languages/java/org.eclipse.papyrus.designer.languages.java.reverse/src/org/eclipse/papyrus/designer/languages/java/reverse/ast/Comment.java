/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 23/05/2008
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.JavadocComment;

/**
 * Abstract class for all AST nodes that represent comments.
 *
 * @see BlockComment
 * @see LineComment
 * @see JavadocComment
 * @author Julio Vilmar Gesser
 */
public abstract class Comment extends Node {

	private String content;

	public Comment() {
	}

	public Comment(int beginLine, int beginColumn, int endLine, int endColumn, String content) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.content = content;
	}

	/**
	 * Return the text of the comment.
	 *
	 * @return text of the comment
	 */
	public final String getContent() {
		return content;
	}

	/**
	 * Sets the text of the comment.
	 *
	 * @param content
	 *            the text of the comment to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
