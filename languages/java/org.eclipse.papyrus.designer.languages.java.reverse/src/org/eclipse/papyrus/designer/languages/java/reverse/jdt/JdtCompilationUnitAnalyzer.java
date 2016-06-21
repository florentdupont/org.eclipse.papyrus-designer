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
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.ClassifierCatalog;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.CreationPackageCatalog;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.ImportedTypeCatalog;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.UmlUtils;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.TypeAnalyserAndTranslator.TranslatedTypeData;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.TypeResolver;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.VisibilityKind;

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
	 * Type resolver used to find a uml::Classifier from its name declared in the java file.
	 */
	private TypeResolver typeResolver;

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

		typeResolver = new TypeResolver(classifierCatalog, importedTypes, creationPackageCatalog);
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
			return processTypeDeclaration((TypeDeclaration)abstractType, localContext);

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
		switch (abstractType.getNodeType()) {
		case ASTNode.TYPE_DECLARATION:
			// Class or interface
			processTypeReferences((TypeDeclaration)abstractType, localContext);
			break;

		case ASTNode.ANNOTATION_TYPE_DECLARATION:
			processAnnotationTypeReferences((AnnotationTypeDeclaration)abstractType, localContext);
			break;

		case ASTNode.ENUM_DECLARATION:
			processEnumReferences((EnumDeclaration)abstractType, localContext);
			break;

		default:
			break;
		}
	}

	/**
	 * Process TypeDeclaration, which can be Class or Interface.
	 * Check if the type already exists in the current namespace. Create it if it does not exist.
	 * 
	 * @param node The type declaration to found or create
	 * @param parentContext The current namespace.
	 */
	private Classifier processTypeDeclaration(TypeDeclaration node, LocalContext parentContext) {

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
			processTypeDeclaration(nestedTypes, localContext);
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
				processTypeDeclaration((TypeDeclaration)nestedBody, localContext);
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
	 * Process TypeDeclaration, which can be Class or Interface.
	 * Check if the type already exists in the current namespace. Create it if it does not exist.
	 * 
	 * @param node The type declaration to found or create
	 * @param parentContext The current namespace.
	 */
	
	private Classifier processTypeReferences(TypeDeclaration node, LocalContext parentContext) {

		// Create or get this type
		Classifier processedClass;
		if (node.isInterface()) {
			processedClass = lookupInterface(parentContext, node);
		} else {
			processedClass = lookupClass(parentContext, node);
		}

		LocalContext localContext = new LocalContext(processedClass, parentContext);

		// Process javadoc
		processJavadoc(node.getJavadoc(), processedClass);
		
		// Modifiers
		processModifiers(processedClass, node.modifiers());
		
		// extends and implements
		if( node.isInterface()) {
			// implements
			List<Type> implementedTypes = node.superInterfaceTypes();
			for( Type type : implementedTypes) {
				Interface generalization = getInterfaceForType( type, localContext);
				if (processedClass instanceof BehavioredClassifier) {
					UmlUtils.getInterfaceRealization((BehavioredClassifier) processedClass, (Interface) generalization);
				}
				else {
					// should not happen
					UmlUtils.getGeneralization(processedClass, generalization);
				}
			}
			
		} 
		else {
			// Class
			// Extends parameters
			// This only apply to classes
			Type superclassType = node.getSuperclassType();
			if (superclassType != null) {
				Classifier generalization = getClassForType( superclassType, localContext);
				// create the generalization
				UmlUtils.getGeneralization(processedClass, generalization);
			}
			// implements
			List<Type> implementedTypes = node.superInterfaceTypes();
			for( Type type : implementedTypes) {
				Classifier generalization = getInterfaceForType( type, localContext);
				if (processedClass instanceof BehavioredClassifier) {
					UmlUtils.getInterfaceRealization((BehavioredClassifier) processedClass, (Interface) generalization);
				}
				else {
					// should not happen
					UmlUtils.getGeneralization(processedClass, generalization);
				}
			}
		}
		
		
		// Now process the properties
		for( FieldDeclaration fieldDeclaration : node.getFields()) {
			processFieldDeclaration( processedClass, fieldDeclaration, localContext);
		}
		
		// Process methods
		for( MethodDeclaration methodDeclaration : node.getMethods()) {
			processMethodDeclaration( processedClass, methodDeclaration, localContext);
		}
		
		// Process nested classes
		for( TypeDeclaration nestedTypes : node.getTypes()) {
			processTypeReferences(nestedTypes, localContext);
		}
		
		return processedClass;
	}

	/**
	 * @param classifier
	 * @param methodDeclaration
	 * @param context
	 */
	private void processMethodDeclaration(Classifier classifier, MethodDeclaration methodDeclaration, LocalContext context) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param classifier
	 * @param fieldDeclaration
	 * @param context
	 */
	private void processFieldDeclaration(Classifier classifier, FieldDeclaration fieldDeclaration, LocalContext context) {
		// TODO Auto-generated method stub
		TypeReferenceDeclaration data = getTypeReferenceDeclaration( fieldDeclaration);
	
		Type umlType;
		
//		// Get data about the type
//		// Get the qualified name, and other info on type
//		TranslatedTypeData typeData = processType(n.getType());
//		// Get the uml element from the qualified name
//		Type umlType = getUmlType(typeData);

		Type t = fieldDeclaration.getType();
		
		// walk on variable declarations.
		PropertyDeclarationHelper helper = new PropertyDeclarationHelper(fieldDeclaration, typeResolver);
		List<VariableDeclarationFragment> variables = fieldDeclaration.fragments();
		
		for (VariableDeclarationFragment var : variables) {
			helper.setVariableDeclaration(var);
			updateProperty(classifier, helper, context);
		}

	}

	/**
	 * Create or update the property.
	 * 
	 * @param classifier The classifier parent of the property.
	 * @param helper The Property Helper containing data about the property.
	 * @param context 
	 */
	private void updateProperty(Classifier classifier, PropertyDeclarationHelper helper, LocalContext context) {
		if( helper.isSimpleProperty() ) {
			updateSimpleProperty( classifier, helper, context);
		} else if( helper.isArrayProperty() ) {
			updateArrayProperty( classifier, helper, context);
		}
		

		
	}

	/**
	 * Update or create the property as a simple property.
	 * 
	 * @param classifier
	 * @param helper
	 * @param context
	 */
	private void updateSimpleProperty(Classifier parent, PropertyDeclarationHelper helper, LocalContext context) {
		Property property = UmlUtils.createProperty(parent, helper.getPropertyType(context), helper.getPropertyName(), 0);
		System.err.println("Property created :" + property);
	}

	/**
	 * Update or create the property as an array property.
	 * 
	 * @param classifier
	 * @param helper
	 * @param context
	 */
	private void updateArrayProperty(Classifier parent, PropertyDeclarationHelper helper, LocalContext context) {
		Property property = UmlUtils.createProperty(parent, helper.getPropertyType(context), helper.getPropertyName(), 0);
		property.setLower(helper.getLower());
		property.setUpper(helper.getUpper());
	}


	/**
	 * Get the type data from the {@link FieldDeclaration}.
	 * 
	 * @param fieldDeclaration
	 * @return
	 */
	private TypeReferenceDeclaration getTypeReferenceDeclaration(FieldDeclaration fieldDeclaration) {
		// TODO Auto-generated method stub
		return new TypeReferenceDeclaration(fieldDeclaration);
	}

	/**
	 * Process the modifiers for a Classifier. 
	 * The modifiers are  in a list of {@link IExtendedModifier}.
	 * 
	 * @param processedClass
	 * @param modifiers
	 */
	private void processModifiers(Classifier processedClass, List<IExtendedModifier> modifiers) {
		
		for( IExtendedModifier modifier : modifiers) {
			if( modifier instanceof Modifier) {
				processModifier( processedClass, (Modifier)modifier);
			}
			else if( modifier instanceof Annotation) {
				processAnnotation( processedClass, (Annotation)modifier);				
			}
			else {
				// Can't happen in VLS8 because there is only 2 types.
			}
		}
	}

	/**
	 * Process Annotation modifiers for a {@link Classifier}.
	 * 
	 * @param processedClass
	 * @param modifier
	 */
	private void processAnnotation(Classifier processedClass, Annotation modifier) {
		// Use stereotype ?
		
	}

	/**
	 * Process simple modifiers for a Classifier.
	 * 
	 * @param processedClass
	 * @param modifier
	 */
	private void processModifier(Classifier c, Modifier modifier) {
		if (modifier.isPrivate()) {
			c.setVisibility(VisibilityKind.PRIVATE_LITERAL);
		}
		if (modifier.isProtected()) {
			c.setVisibility(VisibilityKind.PROTECTED_LITERAL);
		}
		if (modifier.isPublic()) {
			c.setVisibility(VisibilityKind.PUBLIC_LITERAL);
		}
		if (modifier.isAbstract()) {
			c.setIsAbstract(true);
		}
//		if (modifier.isStatic()) {
//			// Can't happen for a Classifier
//
//		}
		if (modifier.isFinal()) {
			c.setIsLeaf(true);
		}
//		if (modifier.isNative()) {
//			printer.print("native ");
//		}
//		if (modifier.isStrictfp()) {
//			printer.print("strictfp ");
//		}
//		if (modifier.isSynchronized()) {
//			printer.print("synchronized ");
//		}
//		if (modifier.isTransient()) {
//			printer.print("transient ");
//		}
//		if (modifier.isVolatile()) {
//			printer.print("volatile ");
//		}
	}

	/**
	 * 
	 * @param localContext The current namespace.
	 * @param typeDecl
	 */
	private Classifier processEnumReferences(EnumDeclaration node, LocalContext parentContext) {

		Classifier processedClass;

		processedClass = lookupEnumeration(parentContext, node);

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
				processTypeReferences((TypeDeclaration)nestedBody, localContext);
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
	private Classifier processAnnotationTypeReferences(AnnotationTypeDeclaration typeDecl, LocalContext localContext) {

		System.err.println("processAnnotationTypeReferences() - Not yet implemented !!");
		
		return null;
	}

	/**
	 * Process javadoc.
	 *
	 * @param javaDoc
	 * @param method
	 */
	private void processJavadoc(Javadoc javaDoc, Element umlElement) {
		if (javaDoc == null) {
			return;
		}

		Comment comment;
		// Check if a comment already exists.
		List<Comment> ownedComments = umlElement.getOwnedComments();
		if (ownedComments != null && ownedComments.size() > 0) {
			comment = ownedComments.get(0);
		} else { // Create a new comment
			comment = umlElement.createOwnedComment();
		}

		// Set the body
		String commentBody = javaDocToString( javaDoc );
		comment.setBody(commentBody);
	}

	private String javaDocToString(Javadoc javaDoc) {
		String result;
		// TODO
		
		return javaDoc.toString();
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
	 * Lookup {@link Class} in the specified context and return it.
	 * Return null if not found.
	 *
	 * @param enclosingParents
	 *            enclosing parent, Package included, in case of nested declaration.
	 * @param n
	 * @return
	 */
	protected Class lookupClass(LocalContext parentContext, TypeDeclaration node) {
		return UmlUtils.lookupClass(parentContext.getNamespace(), node.getName().getIdentifier());
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
	protected Enumeration lookupEnumeration(LocalContext parentContext, EnumDeclaration node) {
		return UmlUtils.lookupEnumeration(parentContext.getNamespace(), node.getName().getIdentifier());
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
	protected Interface lookupInterface(LocalContext parentContext, TypeDeclaration node) {
		return UmlUtils.lookupInterface(parentContext.getNamespace(), node.getName().getIdentifier());
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
		Package creationPackage = creationPackageCatalog.getCreationPackage(qualifiedName, null);
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
	
	/**
	 * Get a {@link Class} in the UML model that correspond to the specified Type in the reversed class.
	 * If the Classifier is not found in the UML model, create it 'a minima' (only the Class skeleton).
	 * 
	 * @param requestedType The type in java file for which a Classifier in java model is requested
	 * @param context The actual context
	 * @return The corresponding Classifier. Should never return null.
	 */
	private Class getClassForType(Type requestedType, LocalContext context) {
		return (Class) getClassifierForType(requestedType, context, UmlUtils.CLASS_TYPE);
	}

	/**
	 * Get a {@link Interface} in the UML model that correspond to the specified Type in the reversed class.
	 * If the Classifier is not found in the UML model, create it 'a minima' (only the Interface skeleton).
	 * 
	 * @param requestedType The type in java file for which a Classifier in java model is requested
	 * @param context The actual context
	 * @return The corresponding Classifier. Should never return null.
	 */
	private Interface getInterfaceForType(Type requestedType, LocalContext context) {
		return (Interface) getClassifierForType(requestedType, context, UmlUtils.INTERFACE_TYPE);
	}

	/**
	 * Get a {@link Classifier} in the UML model that correspond to the specified Type in the reversed class.
	 * If the Classifier is not found in the UML model, create it 'a minima' (only the Classifier skeleton).
	 * <br>
	 * This method is called when a UML Classifier is requested for a type found in a type reference (property, parameters, extends, implements).
	 * 
	 * @param requestedType The type in java file for which a Classifier in java model is requested
	 * @param context The actual context
	 * @param type The type of classifier to create. One of {@link UmlUtils#CLASS_TYPE} or {@link UmlUtils#INTERFACE_TYPE}
	 * 
	 * @return The corresponding Classifier. Should never return null.
	 */
	private Classifier getClassifierForType(Type requestedType, LocalContext context, EClass type) {

		Classifier result;
		
		// First, extract the name of the requested type
		String shortname = JdtAstUtils.getTypeShortname( requestedType );
		
		
		
		// Check if the shortname is declared in imports
		List<String> qualifiedName = importedTypes.getQualifiedName(shortname);
		if( qualifiedName.size() > 1) {
			// Found in import, try to get corresponding Classifier, or create it
			result =  classifierCatalog.getClassifier(qualifiedName);
			if( result != null) {
				return result;
			}
			
			// Classifier not found : create it
			Package creationPackage = creationPackageCatalog.getCreationPackage(qualifiedName, null);
			result = UmlUtils.getClassifier(creationPackage, qualifiedName, type);
			return result;
		}
		
		// Check if a corresponding type can be found in context.
		result = context.lookupClassifier(shortname, type);
		if( result != null) {
			return result;
		}
		
		// check in '*' imports
		// Only check if the requested classifier exist in UML model.
		for( List<String> parentPackageQName : importedTypes.getStarImports() ) {
			// Build a possible Fully qualified name
			List<String> fullQName = new ArrayList<String>(parentPackageQName);
			fullQName.add(shortname);
			
			result = classifierCatalog.getClassifier(fullQName);
			if( result != null) {
				return result;
			}
		}
		
		// Not found, create it in the current package
		Package parentPackage = context.getCurrentPackage();
		result = (Classifier) UmlUtils.createType(parentPackage, shortname, type);
		return result;
	}

	/**
	 * Get a {@link Classifier} in the UML model that correspond to the specified Type in the reversed class.
	 * If the Classifier is not found in the UML model, create it 'a minima' (only the Classifier skeleton).
	 * <br>
	 * This method is called when a UML Classifier is requested for a type found in a type reference (property, parameters, extends, implements).
	 * 
	 * @param requestedType The type in java file for which a Classifier in java model is requested
	 * @param context The actual context
	 * @param type The type of classifier to create. One of {@link UmlUtils#CLASS_TYPE} or {@link UmlUtils#INTERFACE_TYPE}
	 * 
	 * @return The corresponding Classifier. Should never return null.
	 */
	private Classifier getClassifierForTypeOld(Type requestedType, LocalContext context, EClass type) {

		Classifier result;
		
		// First, extract the name of the requested type
		String shortname = JdtAstUtils.getTypeShortname( requestedType );
		// Check if the shortname is declared in imports
		List<String> qualifiedName = importedTypes.getQualifiedName(shortname);
		if( qualifiedName.size() > 1) {
			// Found in import, try to get corresponding Classifier, or create it
			result =  classifierCatalog.getClassifier(qualifiedName);
			if( result != null) {
				return result;
			}
			
			// Classifier not found : create it
			Package creationPackage = creationPackageCatalog.getCreationPackage(qualifiedName, null);
			result = UmlUtils.getClassifier(creationPackage, qualifiedName, type);
			return result;
		}
		
		// Check if a corresponding type can be found in context.
		result = context.lookupClassifier(shortname, type);
		if( result != null) {
			return result;
		}
		
		// check in '*' imports
		// Only check if the requested classifier exist in UML model.
		for( List<String> parentPackageQName : importedTypes.getStarImports() ) {
			// Build a possible Fully qualified name
			List<String> fullQName = new ArrayList<String>(parentPackageQName);
			fullQName.add(shortname);
			
			result = classifierCatalog.getClassifier(fullQName);
			if( result != null) {
				return result;
			}
		}
		
		// Not found, create it in the current package
		Package parentPackage = context.getCurrentPackage();
		result = (Classifier) UmlUtils.createType(parentPackage, shortname, type);
		return result;
	}


}
