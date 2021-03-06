/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.umlparser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.ClassOrInterfaceType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.PrimitiveType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.ReferenceType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.VoidType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.WildcardType;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.TypeAnalyserAndTranslator.TranslatedTypeData;


/**
 * Data structure to return data about a Declared type.
 *
 * @author dumoulin
 *
 */
public class TypeAnalyser {

	/**
	 * Data structure returned after type analyse
	 *
	 * @author dumoulin
	 *
	 */
	public class TypeData {

		List<String> qualifiedName;

		/** template parameters */
		protected List<TypeData> genericData;

		boolean isInterface = false;

		/** PrimitiveType */
		boolean isPrimitive = false;

		/** ReferenceType */
		protected int arrayCount;

		/** For Wildcard */
		protected boolean isWildcard = false;

		protected TypeData extends_;

		protected TypeData super_;

		/** VoidType */
		protected boolean isVoid = false;

	}

	/** Catalog of imported types. Used to translate short name to QualifiedNames */
	private ImportedTypeCatalog importedTypes;

	/**
	 * Constructor.
	 *
	 * @param importedType
	 */
	public TypeAnalyser(ImportedTypeCatalog importedType) {
		this.importedTypes = importedType;
	}

	/**
	 * Get the Type from an ast.Type node.
	 *
	 * @param astType
	 * @return
	 */
	public TypeData getTypeData(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.Type astType) {

		TypeData res = createTypeData();

		new SwitchVisitor<TypeData>() {

			/**
			 * Get type from a class.
			 */
			@Override
			public void visit(ClassOrInterfaceType n, TypeData data) {
				List<String> qnameBefore = getQualifiedName(n);
				List<String> qname = importedTypes.getQualifiedName(qnameBefore);

				data.qualifiedName = qname;
				// Check for generic parameters
				if (n.getTypeArgs() != null) {
					data.genericData = new ArrayList<TypeData>();
					for (org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.Type arg : n.getTypeArgs()) {
						TypeData argData = createTypeData();
						arg.accept(this, argData);
						data.genericData.add(argData);
					}
				}
			}

			@Override
			public void visit(ReferenceType n, TypeData data) {
				// skip the reference node and process the child.
				// Should not appear in an type use (in Attribute or method)
				data.arrayCount = n.getArrayCount();
				n.getType().accept(this, data);
			}

			@Override
			public void visit(PrimitiveType n, TypeData data) {
				String qnameBefore = getPrimitiveName(n);
				List<String> qname = importedTypes.getQualifiedName(qnameBefore);

				data.qualifiedName = qname;
				data.isPrimitive = true;
			}

			@Override
			public void visit(WildcardType n, TypeData data) {
				data.isWildcard = true;
				if (n.getExtends() != null) {
					data.extends_ = createTypeData();
					n.getExtends().accept(this, data.extends_);
				}
				if (n.getSuper() != null) {
					data.super_ = createTypeData();
					n.getSuper().accept(this, data.super_);
				}

			}

			@Override
			public void visit(VoidType n, TypeData data) {
				// TODO Auto-generated method stub
				data.isVoid = true;
			}

		}.doSwitch(astType, res);

		return res;
	}

	/**
	 * Create a new instance.
	 * Can be subclassed to use another type.
	 *
	 * @return
	 */
	protected TypeData createTypeData() {
		return new TypeData();
	}

	/**
	 * Get the qualified name from the provided type.
	 *
	 * @param n
	 * @return
	 */
	private List<String> getQualifiedName(ClassOrInterfaceType n) {
		List<String> qname = new ArrayList<String>();

		getQualifiedName(n, qname);
		return qname;
	}

	/**
	 * Walk each part of the name and put it in the resulting List.
	 *
	 * @param n
	 * @param qname
	 */
	private void getQualifiedName(ClassOrInterfaceType n, List<String> qname) {
		if (n.getScope() != null) {
			getQualifiedName(n.getScope(), qname);
		}
		qname.add(n.getName());
	}

	/**
	 * Create the uml2.primitivetype denoted by the specified PrimitiveType
	 *
	 * @param n
	 * @return
	 */
	private String getPrimitiveName(PrimitiveType n) {
		String typeName = null;

		switch (n.getType()) {
		case Boolean:
			typeName = "Boolean";
			break;
		case Byte:
			typeName = "Byte";
			break;
		case Char:
			typeName = "Char";
			break;
		case Double:
			typeName = "Double";
			break;
		case Float:
			typeName = "Float";
			break;
		case Int:
			typeName = "Integer";
			break;
		case Long:
			typeName = "Long";
			break;
		case Short:
			typeName = "Short";
			break;
		}

		return typeName;
	}
	
	public static String wrapperToPrimitive(TypeData data) {
		List<String> qualifiedName = data.qualifiedName;
		
		if (qualifiedName != null && !qualifiedName.isEmpty()) {
			String wrapper = qualifiedName.get(qualifiedName.size() - 1);
			switch (wrapper) {
			case "boolean":
			case "Boolean":
				return "boolean";
			case "byte":
			case "Byte":
				return "byte";
			case "char":
			case "Char":
				return "char";
			case "double":
			case "Double":
				return "double";
			case "float":
			case "Float":
				return "float";
			case "integer":
			case "int":
			case "Integer":
				return "int";
			case "long":
			case "Long":
				return "long";
			case "short":
			case "Short":
				return "short";
			}
		}
		
		return "";
	}
}
