/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.DocumentableNode;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.NamedNode;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.NameExpr;

import java.util.Collections;
import java.util.List;

import static org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.internal.Utils.*;

/**
 * @author Julio Vilmar Gesser
 */
public abstract class TypeDeclaration extends BodyDeclaration implements NamedNode, DocumentableNode {

	private NameExpr name;

	private int modifiers;

	private List<BodyDeclaration> members;

	public TypeDeclaration() {
	}

	public TypeDeclaration(int modifiers, String name) {
		setName(name);
		setModifiers(modifiers);
	}

	public TypeDeclaration(List<AnnotationExpr> annotations,
			int modifiers, String name,
			List<BodyDeclaration> members) {
		super(annotations);
		setName(name);
		setModifiers(modifiers);
		setMembers(members);
	}

	public TypeDeclaration(int beginLine, int beginColumn, int endLine,
			int endColumn, List<AnnotationExpr> annotations,
			int modifiers, String name,
			List<BodyDeclaration> members) {
		super(beginLine, beginColumn, endLine, endColumn, annotations);
		setName(name);
		setModifiers(modifiers);
		setMembers(members);
	}

	public final List<BodyDeclaration> getMembers() {
        members = ensureNotNull(members);
        return members;
	}

	/**
	 * Return the modifiers of this type declaration.
	 * 
	 * @see ModifierSet
	 * @return modifiers
	 */
	public final int getModifiers() {
		return modifiers;
	}

	@Override
	public final String getName() {
		return name.getName();
	}

	public void setMembers(List<BodyDeclaration> members) {
		this.members = members;
		setAsParentNodeOf(this.members);
	}

	public final void setModifiers(int modifiers) {
		this.modifiers = modifiers;
	}

	public final void setName(String name) {
		this.name = new NameExpr(name);
	}

    public final void setNameExpr(NameExpr nameExpr) {
      this.name = nameExpr;
    }

    public final NameExpr getNameExpr() {
      return name;
    }
}
