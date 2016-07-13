/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast;

/**
 * Access specifier. Represents one of the possible levels of
 * access permitted by the language.
 *
 * @author Federico Tomassetti
 * @since July 2014
 */
public enum AccessSpecifier {

    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),
    DEFAULT("");

    private String codeRepresenation;

    private AccessSpecifier(String codeRepresentation) {
        this.codeRepresenation = codeRepresentation;
    }

    public String getCodeRepresenation(){
        return this.codeRepresenation;
    }
}
