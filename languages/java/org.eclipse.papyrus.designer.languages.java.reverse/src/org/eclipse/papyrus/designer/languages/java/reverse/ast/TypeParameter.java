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

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.ClassOrInterfaceType;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * <p>
 * This class represents the declaration of a genetics argument.
 * </p>
 *
 * The TypeParameter is constructed following the syntax:<br>
 * <code>
 * <table>
 * <tr valign=baseline>
 *   <td align=right>TypeParameter</td>
 *   <td align=center>::=</td>
 *   <td align=left>
 *       &lt;IDENTIFIER&gt; ( "extends" {@link ClassOrInterfaceType} ( "&" {@link ClassOrInterfaceType} )* )?
 *   </td>
 * </tr>
 * </table>
 * </code>
 *
 * @author Julio Vilmar Gesser
 */
public final class TypeParameter extends Node {

	private String name;

	private List<ClassOrInterfaceType> typeBound;

	public TypeParameter() {
	}

	public TypeParameter(int beginLine, int beginColumn, int endLine, int endColumn, String name, List<ClassOrInterfaceType> typeBound) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.name = name;
		this.typeBound = typeBound;
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
	 * Return the name of the paramenter.
	 *
	 * @return the name of the paramenter
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the list of {@link ClassOrInterfaceType} that this parameter
	 * extends. Return <code>null</code> null if there are no type.
	 *
	 * @return list of types that this paramente extends or <code>null</code>
	 */
	public List<ClassOrInterfaceType> getTypeBound() {
		return typeBound;
	}

	/**
	 * Sets the name of this type parameter.
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the list o types.
	 *
	 * @param typeBound
	 *            the typeBound to set
	 */
	public void setTypeBound(List<ClassOrInterfaceType> typeBound) {
		this.typeBound = typeBound;
	}

}
