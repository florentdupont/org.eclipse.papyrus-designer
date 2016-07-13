/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast;

public abstract class TreeVisitor {

    public void visitDepthFirst(Node node){
        process(node);
        for (Node child : node.getChildrenNodes()){
            visitDepthFirst(child);
        }
    }

    public abstract void process(Node node);

}
