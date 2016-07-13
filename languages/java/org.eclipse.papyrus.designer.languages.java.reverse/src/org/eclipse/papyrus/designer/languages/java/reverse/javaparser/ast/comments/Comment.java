/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.comments;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.Node;

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
    private Node commentedNode;

    public Comment() {
    }

    public Comment(String content) {
        this.content = content;
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

    public boolean isLineComment()
    {
        return false;
    }

    public LineComment asLineComment()
    {
        if (isLineComment())
        {
            return (LineComment) this;
        } else {
            throw new UnsupportedOperationException("Not a line comment");
        }
    }

    public Node getCommentedNode()
    {
        return this.commentedNode;
    }

    public void setCommentedNode(Node commentedNode)
    {
        if (commentedNode==null)
        {
            this.commentedNode = commentedNode;
            return;
        }
        if (commentedNode==this)
        {
            throw new IllegalArgumentException();
        }
        if (commentedNode instanceof Comment)
        {
            throw new IllegalArgumentException();
        }
        this.commentedNode = commentedNode;
    }

    public boolean isOrphan()
    {
        return this.commentedNode == null;
    }
}
