/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.Type;

/**
 * A node having a type.
 *
 * The main reason for this interface is to permit users to manipulate homogeneously all nodes with getType/setType
 * methods
 *
 * @since 2.3.1
 */
public interface TypedNode {
    Type getType();

    void setType(Type type);
}
