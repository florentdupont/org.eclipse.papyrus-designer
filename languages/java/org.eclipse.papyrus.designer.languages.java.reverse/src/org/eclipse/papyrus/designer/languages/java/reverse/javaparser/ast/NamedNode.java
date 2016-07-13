/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast;

/**
 * A node having a name.
 *  
 * The main reason for this interface is to permit users to manipulate homogeneously all nodes with a getName method.
 * 
 * @since 2.0.1 
 */
public interface NamedNode {
    String getName();
}
