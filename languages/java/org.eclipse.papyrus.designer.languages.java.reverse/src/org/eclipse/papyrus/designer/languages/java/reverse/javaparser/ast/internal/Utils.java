/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Federico Tomassetti
 * @since 3.0.0
 */
public class Utils {
    public static <T> List<T> ensureNotNull(List<T> list) {
        return list == null ? new ArrayList<T>() : list;
    }

    public static <E> boolean isNullOrEmpty(Collection<E> collection) {
        return collection == null || collection.isEmpty();
    }
}
