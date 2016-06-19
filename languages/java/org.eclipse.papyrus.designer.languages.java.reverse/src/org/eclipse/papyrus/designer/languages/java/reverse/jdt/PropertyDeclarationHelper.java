/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.java.reverse.jdt;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.TypeResolver;
import org.eclipse.uml2.uml.Type;

/**
 * A class used to help to get data needed for UML Attribute declaration.
 * 
 * @author cedric dumoulin
 *
 */
public class PropertyDeclarationHelper {

	private FieldDeclaration fieldDeclaration;
	private TypeResolver typeResolver;
	private VariableDeclarationFragment variableDeclaration;
	/**
	 * Constructor.
	 *
	 * @param fieldDeclaration
	 * @param typeResolver
	 */
	public PropertyDeclarationHelper(FieldDeclaration fieldDeclaration, TypeResolver typeResolver) {
		this.fieldDeclaration = fieldDeclaration;
		this.typeResolver = typeResolver;
	}

	/**
	 * @param var
	 */
	public void setVariableDeclaration(VariableDeclarationFragment varDecl) {
		this.variableDeclaration = varDecl;
		
	}

	/**
	 * Return the name of the Property.
	 * 
	 * @return
	 */
	public String getPropertyName() {
		return variableDeclaration.getName().getIdentifier();
	}

	/**
	 * Return the type of the Property, as declared in {@link FieldDeclaration}.
	 * @param context 
	 * 
	 * @return
	 */
	public Type getPropertyType(LocalContext context) {
		String typename = JdtAstUtils.getTypeShortname(fieldDeclaration.getType());
		
		Type result = typeResolver.getType( typename, context );
		return result;
	}

	/**
	 * Return true if the property is a simple property (not an array neither a Collection)
	 * 
	 * @return
	 */
	public boolean isSimpleProperty() {
//		org.eclipse.jdt.core.dom.Type type = fieldDeclaration.getType();
//		return (type.isSimpleType() || type.isPrimitiveType());
		return !isMultiple();
	}

	/**
	 * Return true if the variable should be declared as an array.
	 * In Java, array can be declared with the type, the var, or both (cumulative dimension).
	 *  
	 * @return true if the variable should be declared as an array.
	 */
	public boolean isArrayProperty() {
		
		if( fieldDeclaration.getType().isArrayType() || variableDeclaration.getExtraDimensions()>0 ) {
			return true;
		}
		
		return false;
	}

	/**
	 * return true if the Java variable is an Array, or if the type is a collection.. 
	 * 
	 * @return
	 */
	public boolean isMultiple() {
		
		return (isArrayProperty() || isCollectionProperty() );
	}

	/**
	 * Return true if the type denote a Collection
	 * @return
	 */
	private boolean isCollectionProperty() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Return the UML lower value usable for UML.
	 * @return
	 */
	public int getLower() {
		
		return 0;
	}

	/**
	 * Return the UML upper value usable for UML.
	 * @return
	 */
	public int getUpper() {
		if( isMultiple() ) {
			return -1;
		}
		// Single
		return 1;
	}

}
