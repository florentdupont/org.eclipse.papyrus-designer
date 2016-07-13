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

import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.AnnotationDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.ClassOrInterfaceDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.EmptyTypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.EnumDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.JavadocComment;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.TypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * <p>
 * This class represents the entire compilation unit. Each java file denotes a compilation unit.
 * </p>
 *
 * The CompilationUnit is constructed following the syntax:<br>
 * <code>
 * <table>
 * <tr valign=baseline>
 *   <td align=right>CompilationUnit</td>
 *   <td align=center>::=</td>
 *   <td align=left>
 *   ( {@link PackageDeclaration} )?<br>
 *   ( {@link ImportDeclaration} )*<br>
 *   ( {@link TypeDeclaration} )*<br>
 *   </td>
 * </tr>
 * </table>
 * </code>
 *
 * @author Julio Vilmar Gesser
 */
public final class CompilationUnit extends Node {

	private PackageDeclaration pakage;

	private List<ImportDeclaration> imports;

	private List<TypeDeclaration> types;

	private List<Comment> comments;

	public CompilationUnit() {
	}

	public CompilationUnit(int beginLine, int beginColumn, int endLine, int endColumn, PackageDeclaration pakage, List<ImportDeclaration> imports, List<TypeDeclaration> types, List<Comment> comments) {
		super(beginLine, beginColumn, endLine, endColumn);
		this.pakage = pakage;
		this.imports = imports;
		this.types = types;
		this.comments = comments;
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
	 * Return a list containing all comments declared in this compilation unit.
	 * Including javadocs, line comments and block comments of all types,
	 * inner-classes and other members.<br>
	 * If there is no comment, <code>null</code> is returned.
	 *
	 * @return list with all comments of this compilation unit or <code>null</code>
	 * @see JavadocComment
	 * @see LineComment
	 * @see BlockComment
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * Retrieves the list of imports declared in this compilation unit or <code>null</code> if there is no import.
	 *
	 * @return the list of imports or <code>null</code> if there is no import
	 */
	public List<ImportDeclaration> getImports() {
		return imports;
	}

	/**
	 * Retrieves the package declaration of this compilation unit.<br>
	 * If this compilation unit has no package declaration (default package), <code>null</code> is returned.
	 *
	 * @return the package declaration or <code>null</code>
	 */
	public PackageDeclaration getPakage() {
		return pakage;
	}

	/**
	 * Return the list of types declared in this compilation unit.<br>
	 * If there is no types declared, <code>null</code> is returned.
	 *
	 * @return the list of types or <code>null</code> null if there is no type
	 * @see AnnotationDeclaration
	 * @see ClassOrInterfaceDeclaration
	 * @see EmptyTypeDeclaration
	 * @see EnumDeclaration
	 */
	public List<TypeDeclaration> getTypes() {
		return types;
	}

	/**
	 * Sets the list of comments of this compilation unit.
	 *
	 * @param comments
	 *            the list of comments
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * Sets the list of imports of this compilation unit.
	 * The list is initially <code>null</code>.
	 *
	 * @param imports
	 *            the list of imports
	 */
	public void setImports(List<ImportDeclaration> imports) {
		this.imports = imports;
	}

	/**
	 * Sets or clear the package declarations of this compilation unit.
	 *
	 * @param pakage
	 *            the pakage declaration to set or <code>null</code> to default package
	 */
	public void setPakage(PackageDeclaration pakage) {
		this.pakage = pakage;
	}

	/**
	 * Sets the list of types declared in this compilation unit.
	 *
	 * @param types
	 *            the lis of types
	 */
	public void setTypes(List<TypeDeclaration> types) {
		this.types = types;
	}

}
