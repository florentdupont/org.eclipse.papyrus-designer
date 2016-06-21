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

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.PrimitiveType.Code;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.exception.NotFoundException;

/**
 * A set of utilities to analyze JDT AST nodes.
 * 
 * @author cedric dumoulin
 *
 * @since 0.7.2
 */
public class JdtAstUtils {

	/**
	 * Get the short name of the underlying type
	 * @param requestedType
	 * @return
	 */
	static public String getTypeShortname(Type requestedType) {
		
		switch( requestedType.getNodeType()) {
		case ASTNode.SIMPLE_TYPE :
			return ((SimpleType)requestedType).getName().getFullyQualifiedName();
			
		case ASTNode.PARAMETERIZED_TYPE : 
			return getTypeShortname( ((ParameterizedType)requestedType).getType() );
			
		case ASTNode.ARRAY_TYPE : 
			return getTypeShortname( ((ArrayType)requestedType).getElementType() );

		case ASTNode.PRIMITIVE_TYPE : 
			return ((PrimitiveType)requestedType).getPrimitiveTypeCode().toString();
			
		}
		return null;
	}

	/**
	 * Get a field by its name.
	 * 
	 * @param typeDeclaration
	 * @param string
	 * @return
	 * @throws NotFoundException 
	 */
	static public FieldDeclaration getField(TypeDeclaration typeDeclaration, String fieldName) throws NotFoundException {
		
		FieldDeclaration[] fields = typeDeclaration.getFields();
		// Walk fields
		for( FieldDeclaration field : fields ) {
			// Walk variables declarations
			List<VariableDeclaration> varDecls = field.fragments();
			for( VariableDeclaration decl : varDecls) {
				if( fieldName.equals( decl.getName().getIdentifier() )) {
					return field;
				}
			}
			
		}
		throw new NotFoundException("Name " + fieldName + "' is not found in Field");
	}


}
