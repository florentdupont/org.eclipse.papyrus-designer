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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.ClassifierCatalog;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.CreationPackageCatalog;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.ImportedTypeCatalog;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.UmlUtils;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Package;

/**
 * This analyzer is used to create UML::element from JDT nodes.
 * 
 * @author cedric dumoulin
 *
 */
public class JdtCompilationUnitAnalyzer {

	public static String DEFAULT_GENERATION_PACKAGE_NAME = "generated";

	public static String DEFAULT_ROOT_PACKAGE_NAME = "model";

	private List<String> generationPackageQualifiedName;

	/**
	 * Root package of the generation. All generated elements will be added to this package, or one of
	 * its subpackages
	 */
	private Package defaultGenerationPackage;

	/**
	 * Catalog used to get the package needed for a creation.
	 */
	private CreationPackageCatalog creationPackageCatalog;

	/**
	 * Catalog used to lookup for a classifier. The catalog specifies a collection of models
	 * containing the java packages
	 */
	private ClassifierCatalog classifierCatalog;

	/**
	 * Catalog used to store the imports and to lookup for the qualified name of an element.
	 */
	private ImportedTypeCatalog importedTypes;


    /**
     * 
     * Constructor.
     *
     * @param modelRootPackage
     * @param generatePackageName
     * @param searchPaths
     */
	public JdtCompilationUnitAnalyzer(Package modelRootPackage, String generatePackageName, List<String> searchPaths) {
		initCompilationUnitAnalyser(modelRootPackage, generatePackageName, searchPaths);
	}

	/**
	 * Initialize the class. Called by constructors.
	 *
	 * @param modelRootPackage
	 * @param defaultGenerationPackageName
	 * @param searchPaths
	 */
	private void initCompilationUnitAnalyser(Package modelRootPackage, String defaultGenerationPackageName, List<String> searchPaths) {
		if (defaultGenerationPackageName == null) {
			defaultGenerationPackageName = DEFAULT_GENERATION_PACKAGE_NAME;
		}

		this.generationPackageQualifiedName = dirToQualifiedName(defaultGenerationPackageName);


		// Copy searchpath in order to be able to change it.
		if (searchPaths != null) {
			searchPaths = new ArrayList<String>(searchPaths);
		} else {
			searchPaths = new ArrayList<String>();
		}

		// Add generation package in search paths
		if (!searchPaths.contains(defaultGenerationPackageName)) {
			searchPaths.add(defaultGenerationPackageName);
		}

		System.out.println(this.getClass().getSimpleName() + ".CT(" + generationPackageQualifiedName + ", sarchpaths=" + searchPaths + ")");


		createDefaultGenerationPackage(modelRootPackage);

		classifierCatalog = new ClassifierCatalog(modelRootPackage, searchPaths);
		importedTypes = new ImportedTypeCatalog();
		creationPackageCatalog = new CreationPackageCatalog(modelRootPackage, defaultGenerationPackage, null);

//		typeAnalyser = new TypeAnalyserAndTranslator(importedTypes);
	}

	/**
	 * Get the qualified name from a directory like name.
	 * Name is splitted arround '/'
	 *
	 * @param name
	 * @return
	 */
	private List<String> dirToQualifiedName(String qname) {
		String[] splittedName = qname.split("/");
		return Arrays.asList(splittedName);
	}

	/**
	 * Add all the imports to the manager.
	 *
	 * @param importedTypes2
	 * @param imports
	 */
	private void addImports(ImportedTypeCatalog importedTypes, List<ImportDeclaration> imports) {


		for (ImportDeclaration decl : imports) {
			String qname = decl.getName().getFullyQualifiedName();
			List<String> qualifiedName = UmlUtils.toQualifiedName(qname);
			// Only register regular imports. Forget import of 'static'
			if (! decl.isStatic()) {
				if (decl.isOnDemand()) {
					importedTypes.addStarImport(qualifiedName);
				} else {
					importedTypes.addImport(qualifiedName);
				}
			}
		}

	}

	/**
	 * @return the classifierCatalog
	 */
	public ClassifierCatalog getClassifierCatalog() {
		return classifierCatalog;
	}

	/**
	 * Explore the CompilationUnit and create appropriate UML elements.
	 * This is the main entrance of the analyzer.
	 *
	 * @param cu The compilation unit to analyze.
	 */
	public void processCompilationUnit(CompilationUnit cu) {

		// First, create the current context.
		Package currentCompilationUnitPackage = getCuPackage(cu.getPackage());
		LocalContext localContext = new LocalContext(currentCompilationUnitPackage);

		classifierCatalog.setCurrentCompilationUnitPackage(currentCompilationUnitPackage);

		// Add imports
		importedTypes.clear();
		if (cu.imports() != null) {
			addImports(importedTypes, cu.imports());
		}

		// Now, create the tree of NamedElement corresponding to declaration.
		// Do not set the references to the types
		List<AbstractTypeDeclaration> types = cu.types();
		for( AbstractTypeDeclaration abstractType : types ) {
			Classifier createdElement = processNamedElementDeclaration(abstractType, localContext);
		}

		// Now, explore the tree of NamedElements and set the types.
		for( AbstractTypeDeclaration abstractType : types ) {
			processNamedElementReferences(abstractType, localContext);
		}

	}

	/**
	 * Process all NamedElement declaration. Walk the tree of declaration in depth first.
	 * For each NamedElement declaration, check if it exists, or create it with its name. Do not set or change the references to
	 * other types.
	 * 
	 * @param abstractType
	 * @param localContext The current namespace.
	 */
	private Classifier processNamedElementDeclaration(AbstractTypeDeclaration abstractType, LocalContext localContext) {
		switch (abstractType.getNodeType()) {
		case ASTNode.TYPE_DECLARATION:
			// Class or interface
			return processTypedeclaration((TypeDeclaration)abstractType, localContext);

		case ASTNode.ANNOTATION_TYPE_DECLARATION:
			return processAnnotationTypeDeclaration((AnnotationTypeDeclaration)abstractType, localContext);

		case ASTNode.ENUM_DECLARATION:
			return processEnumDeclaration((EnumDeclaration)abstractType, localContext);

		default:
			return null;
		}
	}

	/**
	 * Process all NamedElement declaration. Walk the tree of declaration in depth first.
	 * For each NamedElement declaration, check if it exists, or create it with its name. Do not set or change the references to
	 * other types.
	 * 
	 * @param abstractType
	 * @param localContext The current namespace.
	 */
	private void processNamedElementReferences(AbstractTypeDeclaration abstractType, LocalContext localContext) {
//		switch (abstractType.getNodeType()) {
//		case ASTNode.TYPE_DECLARATION:
//			// Class or interface
//			processTypeReferences((TypeDeclaration)abstractType, localContext);
//			break;
//
//		case ASTNode.ANNOTATION_TYPE_DECLARATION:
//			processAnnotationTypeReferences((AnnotationTypeDeclaration)abstractType, localContext);
//			break;
//
//		case ASTNode.ENUM_DECLARATION:
//			processEnumReferences((EnumDeclaration)abstractType, localContext);
//			break;
//
//		default:
//			break;
//		}
	}

	/**
	 * Process TypeDeclaration, which can be Class or Interface.
	 * Check if the type already exists in the current namespace. Create it if it does not exist.
	 * 
	 * @param node The type declaration to found or create
	 * @param parentContext The current namespace.
	 */
	private Classifier processTypedeclaration(TypeDeclaration node, LocalContext parentContext) {

		// Create or get this type
		Classifier processedClass;
		if (node.isInterface()) {
			processedClass = createInterface(parentContext, node);
		} else {
			processedClass = createClass(parentContext, node);
		}

		LocalContext localContext = new LocalContext(processedClass, parentContext);

		// Now process the properties
		
		// Process methods
		
		// Process nested classes
		for( TypeDeclaration nestedTypes : node.getTypes()) {
			processTypedeclaration(nestedTypes, localContext);
		}
		
		return processedClass;
	}

	/**
	 * 
	 * @param localContext The current namespace.
	 * @param typeDecl
	 */
	private Classifier processEnumDeclaration(EnumDeclaration node, LocalContext parentContext) {

		Classifier processedClass;

		processedClass = createEnumeration(parentContext, node);

		LocalContext localContext = new LocalContext(processedClass, parentContext);
		// Now process the properties
		
		// Process methods
		
		// Process nested classes
		List<BodyDeclaration> bodies = node.bodyDeclarations();
		for( BodyDeclaration nestedBody : bodies) {
			switch( nestedBody.getNodeType()) {
			case ASTNode.TYPE_DECLARATION:
			case ASTNode.ANNOTATION_TYPE_DECLARATION:
			case ASTNode.ENUM_DECLARATION:
				processTypedeclaration((TypeDeclaration)nestedBody, localContext);
				break;
			}
			
		}
		return processedClass;
	}

	/**
	 * 
	 * @param typeDecl
	 * @param localContext The current namespace.
	 */
	private Classifier processAnnotationTypeDeclaration(AnnotationTypeDeclaration typeDecl, LocalContext localContext) {

		System.err.println("processAnnotationTypeDeclaration() - Not yet implemented !!");
		
		return null;
	}

	/**
	 * Create an {@link Class} and return it.
	 * The Classifier is created exactly in the directly enclosing namespace.
	 * First, a lookup is done to check if it has been created elsewhere in the namespaces. If true, correct the location
	 * and maybe the type.
	 *
	 * Only need to create the object and fill it with data available at this level.
	 *
	 * @param enclosingParents
	 *            enclosing parent, Package included, in case of nested declaration.
	 * @param n
	 * @return
	 */
	protected Class createClass(LocalContext parentContext, TypeDeclaration node) {
		return UmlUtils.getExactClass(parentContext.getNamespace(), node.getName().getIdentifier());
	}

	/**
	 * Create an {@link Enumeration} and return it.
	 * The Classifier is created exactly in the directly enclosing namespace.
	 * First, a lookup is done to check if it has been created elsewhere in the namespaces. If true, correct the location
	 * and maybe the type.
	 *
	 * Only need to create the object and fill it with data available at this level.
	 *
	 * @param enclosingParents
	 *            enclosing parent, Package included, in case of nested declaration.
	 * @param n
	 * @return
	 */
	protected Enumeration createEnumeration(LocalContext parentContext, EnumDeclaration node) {
		return UmlUtils.getExactEnumeration(parentContext.getNamespace(), node.getName().getIdentifier());
	}

	/**
	 * Create an interface and return it.
	 * The Classifier is created exactly in the directly enclosing namespace.
	 * First, a lookup is done to check if it has been created elsewhere in the namespaces. If true, correct the location
	 * and maybe the type.
	 *
	 * Only need to create the object and fill it with data available at this level.
	 *
	 * @param parent
	 * @param parentContext
	 * @return
	 */
	protected Interface createInterface(LocalContext parentContext, TypeDeclaration node) {
		System.out.println("getInterface( " + node.getName() + " )");
		
		return UmlUtils.getExactInterface(parentContext.getNamespace(), node.getName().getIdentifier());
	}

	/**
	 * Create the root element in which all element will be created.
	 */
	private void createDefaultGenerationPackage(Package rootModelElement) {

		// Package p = UmlUtils.getPackage(rootModelElement, generationPackageQualifiedName);
		Package p = UmlUtils.getModel(rootModelElement, generationPackageQualifiedName);
		defaultGenerationPackage = p;
	}

	/**
	 * get (lookup or create) the packages containing the CU.
	 *
	 * @param cu
	 * @return
	 */
	private Package getCuPackage(PackageDeclaration packageDecl) {

		// If null, return the generation root
		if (packageDecl == null) {
			return getDefaultGenerationPackage();
		}

		// Get the name
		List<String> qualifiedName = UmlUtils.toQualifiedName(packageDecl.getName().getFullyQualifiedName()) ;
		// Get the creation model
		Package creationPackage = creationPackageCatalog.getCreationPackage(qualifiedName);
		// Get the current unit package (where the element are created)
		Package p = UmlUtils.getPackage(creationPackage, qualifiedName);

		return p;
		// question
		// How to let the creationPackageCatalog create the package for a specified name ?
		// CreatePackage visitor = new CreatePackage();
		// Package res = visitor.getPackage(packageDecl, defaultGenerationPackage);

		// return res;
	}


	/**
	 * Get the default generation package. Create it in UML model if needed.
	 * 
	 * @return
	 */
	private Package getDefaultGenerationPackage() {
//		if( defaultGenerationPackage == null) {
//			createDefaultGenerationPackage(rootModelElement);
//		}

		return defaultGenerationPackage;
	}
}
