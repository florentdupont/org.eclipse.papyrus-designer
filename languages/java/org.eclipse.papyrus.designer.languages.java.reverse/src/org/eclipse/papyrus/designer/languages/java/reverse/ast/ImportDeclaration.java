/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 05/10/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.NameExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * <p>
 * This class represents a import declaration. Imports are optional for the {@link CompilationUnit}.
 * </p>
 *
 * The ImportDeclaration is constructed following the syntax:<br>
 * <code>
 * <table>
 * <tr valign=baseline>
 *   <td align=right>ImportDeclaration</td>
 *   <td align=center>::=</td>
 *   <td align=left>
 *       "import" ( "static" )? {@link NameExpr} ( "." "*" )? ";"
 *   </td>
 * </tr>
 * </table>
 * </code>
 *
 * @author Julio Vilmar Gesser
 */
public final class ImportDeclaration extends Node {

	private NameExpr name;

	private boolean static_;

	private boolean asterisk;

	public ImportDeclaration() {
	}

	public ImportDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, NameExpr name, boolean isStatic, boolean isAsterisk) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.name = name;
		this.static_ = isStatic;
		this.asterisk = isAsterisk;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	/**
	 * Retrieves the name of the import.
	 *
	 * @return the name of the import
	 */
	public NameExpr getName() {
		return name;
	}

	/**
	 * Return if the import ends with "*".
	 *
	 * @return <code>true</code> if the import ends with "*", <code>false</code> otherwise
	 */
	public boolean isAsterisk() {
		return asterisk;
	}

	/**
	 * Return if the import is static.
	 *
	 * @return <code>true</code> if the import is static, <code>false</code> otherwise
	 */
	public boolean isStatic() {
		return static_;
	}

	/**
	 * Sets if this import is asterisk.
	 *
	 * @param asterisk
	 *            <code>true</code> if this import is asterisk
	 */
	public void setAsterisk(boolean asterisk) {
		this.asterisk = asterisk;
	}

	/**
	 * Sets the name this import.
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(NameExpr name) {
		this.name = name;
	}

	/**
	 * Sets if this import is static.
	 *
	 * @param static_
	 *            <code>true</code> if this import is static
	 */
	public void setStatic(boolean static_) {
		this.static_ = static_;
	}

}
