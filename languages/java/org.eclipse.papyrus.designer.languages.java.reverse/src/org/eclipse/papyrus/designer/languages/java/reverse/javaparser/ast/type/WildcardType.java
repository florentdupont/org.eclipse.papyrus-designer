/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class WildcardType extends Type {

	private ReferenceType ext;

	private ReferenceType sup;

	public WildcardType() {
	}

	public WildcardType(final ReferenceType ext) {
		setExtends(ext);
	}

	public WildcardType(final ReferenceType ext, final ReferenceType sup) {
		setExtends(ext);
		setSuper(sup);
	}

	public WildcardType(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final ReferenceType ext, final ReferenceType sup) {
		super(beginLine, beginColumn, endLine, endColumn);
		setExtends(ext);
		setSuper(sup);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public ReferenceType getExtends() {
		return ext;
	}

	public ReferenceType getSuper() {
		return sup;
	}

	public void setExtends(final ReferenceType ext) {
		this.ext = ext;
		setAsParentNodeOf(this.ext);
	}

	public void setSuper(final ReferenceType sup) {
		this.sup = sup;
		setAsParentNodeOf(this.sup);
	}

}
