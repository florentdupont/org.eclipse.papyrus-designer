/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 05/10/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.type;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.VoidVisitor;

/**
 * @author Julio Vilmar Gesser
 */
public final class WildcardType extends Type {

	private ReferenceType ext;

	private ReferenceType sup;

	public WildcardType() {
	}

	public WildcardType(int beginLine, int beginColumn, int endLine, int endColumn, ReferenceType ext, ReferenceType sup) {
		super(beginLine, beginColumn, endLine, endColumn);
		assert ext == null || sup == null;
		this.ext = ext;
		this.sup = sup;
	}

	@Override
	public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

	public ReferenceType getExtends() {
		return ext;
	}

	public ReferenceType getSuper() {
		return sup;
	}

	public void setExtends(ReferenceType ext) {
		this.ext = ext;
	}

	public void setSuper(ReferenceType sup) {
		this.sup = sup;
	}

}
