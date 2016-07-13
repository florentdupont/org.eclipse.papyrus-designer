/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 17/10/2007
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast;

import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.NameExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * <p>
 * This class represents the package declaration. The package declaration is optional for the {@link CompilationUnit}.
 * </p>
 *
 * The PackageDeclaration is constructed following the syntax:<br>
 * <code>
 * <table>
 * <tr valign=baseline>
 *   <td align=right>PackageDeclaration</td>
 *   <td align=center>::=</td>
 *   <td align=left>
 *       ( {@link AnnotationExpr} )* "package" {@link NameExpr} ) ";"
 *   </td>
 * </tr>
 * </table>
 * </code>
 *
 * @author Julio Vilmar Gesser
 */
public final class PackageDeclaration extends Node {

	private List<AnnotationExpr> annotations;

	private NameExpr name;

	public PackageDeclaration() {
	}

	public PackageDeclaration(int beginLine, int beginColumn, int endLine, int endColumn, List<AnnotationExpr> annotations, NameExpr name) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.annotations = annotations;
		this.name = name;
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
	 * Retrieves the list of annotations declared before the package
	 * declaration. Return <code>null</code> if there are no annotations.
	 *
	 * @return list of annotations or <code>null</code>
	 */
	public List<AnnotationExpr> getAnnotations() {
		return annotations;
	}

	/**
	 * Return the name of the package.
	 *
	 * @return the name of the package
	 */
	public NameExpr getName() {
		return name;
	}

	/**
	 *
	 * @param annotations
	 *            the annotations to set
	 */
	public void setAnnotations(List<AnnotationExpr> annotations) {
		this.annotations = annotations;
	}

	/**
	 * Sets the name of this package declaration.
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(NameExpr name) {
		this.name = name;
	}

}
