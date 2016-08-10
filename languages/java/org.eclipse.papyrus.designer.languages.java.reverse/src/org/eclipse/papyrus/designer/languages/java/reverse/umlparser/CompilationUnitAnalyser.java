/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.umlparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.External;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Final;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Native;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.NoCodeGen;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.StaticClassifier;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Strictfp;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Synchronized;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Transient;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Variadic;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Volatile;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.CompilationUnit;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.ImportDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.Node;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.PackageDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.BodyDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.ConstructorDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.EnumConstantDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.EnumDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.FieldDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.MethodDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.ModifierSet;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.Parameter;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.TypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.VariableDeclarator;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.NameExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.QualifiedNameExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.BlockStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.ClassOrInterfaceType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.PrimitiveType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.ReferenceType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.VoidType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.WildcardType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitorAdapter;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.TypeAnalyserAndTranslator.TranslatedTypeData;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.profile.standard.Create;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * A visitor filling the provided UML model
 *
 * @author dumoulin
 *
 */
public class CompilationUnitAnalyser {


	public static String DEFAULT_GENERATION_PACKAGE_NAME = "generated";

	public static String DEFAULT_ROOT_PACKAGE_NAME = "model";

	private List<String> generationPackageQualifiedName;

	private Model rootModel;
	
	/**
	 * Root package of the generation. All generated elements will be added to this package, or one of
	 * its subpackages
	 */
	private org.eclipse.uml2.uml.Package defaultGenerationPackage;

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
	 * The Package containing the current compilation unit.
	 * Initialized at the beginning of exploreCompilationUnit()
	 */
	private Package currentCompilationUnitPackage;

	/**
	 * Enclosing namespaces of the currently processed class.
	 * The first element is always the current package.
	 */
	private List<Namespace> enclosingNamespaces = new ArrayList<Namespace>();

	/**
	 * Visitor used to parse qualified names.
	 */
	private QualifiedNameParser qualifiedNameParser = new QualifiedNameParser();

	private TypeAnalyserAndTranslator typeAnalyser;

	private List<String> qualifiedNamesInProjects;
	
	/** Model where all element are created */

	/**
	 * Constructor.
	 */
	public CompilationUnitAnalyser(Resource model) {

		this(model, DEFAULT_ROOT_PACKAGE_NAME, DEFAULT_GENERATION_PACKAGE_NAME, null, null);
	}

	/**
	 *
	 * Constructor.
	 */
	public CompilationUnitAnalyser(Resource model, String generatePackageName, List<String> searchPaths, List<String> creationPaths) {

		this(model, DEFAULT_ROOT_PACKAGE_NAME, generatePackageName, searchPaths, creationPaths);
	}

	/**
	 *
	 * Constructor.
	 */
	public CompilationUnitAnalyser(Resource model, String modelRootPackageName, String generatePackageName, List<String> searchPaths, List<String> creationPaths) {
		if (model instanceof Model) {
			rootModel = (Model) model;
		}
		Package modelRootPackage = UmlUtils.getPackage(model, dirToQualifiedName(modelRootPackageName));
		initCompilationUnitAnalyser(modelRootPackage, generatePackageName, searchPaths, creationPaths, null);
	}

	/**
	 *
	 * Constructor.
	 * All provided paths and searchpaths are relative to the specified modelRootPackage.
	 */
	public CompilationUnitAnalyser(Package modelRootPackage, String generatePackageName, List<String> searchPaths, List<String> creationPaths, List<String> qualifiedNamesInProjects) {
		if (modelRootPackage instanceof Model) {
			rootModel = (Model) modelRootPackage;
		}
		initCompilationUnitAnalyser(modelRootPackage, generatePackageName, searchPaths, creationPaths, qualifiedNamesInProjects);
	}

	/**
	 * The {@link ClassifierCatalog} can be used to get a UML::NamedElement by its qualified name.
	 * 
	 * @return the classifierCatalog
	 */
	public ClassifierCatalog getClassifierCatalog() {
		return classifierCatalog;
	}
	
	public Model getRootModel() {
		return rootModel;
	}

	/**
	 * Initialize the class. Called by constructors.
	 *
	 * @param modelRootPackage
	 * @param generatePackageName
	 * @param searchPaths
	 */
	private void initCompilationUnitAnalyser(Package modelRootPackage, String generatePackageName, List<String> searchPaths, List<String> creationPaths, List<String> qualifiedNamesInProjects) {
		this.qualifiedNamesInProjects = qualifiedNamesInProjects;
		
		if (generatePackageName == null) {
			generatePackageName = DEFAULT_GENERATION_PACKAGE_NAME;
		}

		this.generationPackageQualifiedName = dirToQualifiedName(generatePackageName);


		// Copy searchpath in order to be able to change it.
		if (searchPaths != null) {
			searchPaths = new ArrayList<String>(searchPaths);
		} else {
			searchPaths = new ArrayList<String>();
		}
		
		if (creationPaths != null) {
			creationPaths = new ArrayList<String>(creationPaths);
		} else {
			creationPaths = new ArrayList<String>();
		}

		// Add generation package in search paths
		if (!searchPaths.contains(generatePackageName)) {
			searchPaths.add(generatePackageName);
		}

		System.out.println(this.getClass().getSimpleName() + ".CT(" + generationPackageQualifiedName + ", sarchpaths=" + searchPaths + ")");


		createDefaultGenerationPackage(modelRootPackage);

		classifierCatalog = new ClassifierCatalog(modelRootPackage, searchPaths);
		importedTypes = new ImportedTypeCatalog();
		creationPackageCatalog = new CreationPackageCatalog(modelRootPackage, defaultGenerationPackage, creationPaths);

		typeAnalyser = new TypeAnalyserAndTranslator(importedTypes);
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
	 * Create the root element in which all element will be created.
	 */
	private void createDefaultGenerationPackage(Resource model) {
		// TODO to update (see below)
		Package p = UmlUtils.getPackage(model, generationPackageQualifiedName);
		defaultGenerationPackage = p;
	}

	/**
	 * Create the root element in which all element will be created.
	 */
	private void createDefaultGenerationPackage(Package rootModelElement) {

		if (generationPackageQualifiedName.size() == 1 && generationPackageQualifiedName.get(0).isEmpty()) {
			defaultGenerationPackage = rootModelElement;
		} else {
			// Package p = UmlUtils.getPackage(rootModelElement, generationPackageQualifiedName);
			Package p = UmlUtils.getPackage(rootModelElement, generationPackageQualifiedName);
			defaultGenerationPackage = p;
		}
		
	}

	/**
	 * Explore the CU and create the declared types
	 *
	 * @param cu
	 */
	public void processCompilationUnit(CompilationUnit cu) {

		// First, find the parent Package
		currentCompilationUnitPackage = getCuPackage(cu.getPackage());
		;
		classifierCatalog.setCurrentCompilationUnitPackage(currentCompilationUnitPackage);

		// Add imports
		importedTypes.clear();
		if (cu.getImports() != null) {
			addImports(importedTypes, cu.getImports());
		}

		// Now, explore each type declared type in the cu
		if (cu.getTypes() != null) {
			// for (Iterator<TypeDeclaration> i = cu.getTypes().iterator();
			// i.hasNext();)
			// Process all types
			for (TypeDeclaration typeDecl : cu.getTypes()) {
				// Build the enclosing namespaces. Add the package as parent
				enclosingNamespaces.add(currentCompilationUnitPackage);
				processTypedeclaration(enclosingNamespaces, typeDecl);
				// Reset the list of enclosing parents, in case of errors
				enclosingNamespaces.clear();
			} // end loop
		} // end if

	}

	/**
	 * Process Typedeclaration 2 Classifier.
	 * Process common parts : modifiers, members, ...
	 *
	 * @param enclosingParents
	 *            The package, followed by nested classes if any.
	 * @param typeDecl
	 */
	private void processTypedeclaration(final List<Namespace> enclosingParents, TypeDeclaration typeDecl) {
		Classifier classifier;

		// Process right type
		// Create the corresponding type.
		classifier = new GenericSwitchVisitor<Classifier, List<Namespace>>() {

			@Override
			public Classifier visit(ClassOrInterfaceDeclaration n, List<Namespace> enclosingParents) {
				return processClassOrInterfaceDeclaration(n, enclosingParents);
			}
			
			public Classifier visit(EnumDeclaration n, List<Namespace> enclosingParents) {
				return processEnumDeclaration(n, enclosingParents);
			};

			// TODO Other kind of types
		}.doSwitch(typeDecl, enclosingParents);

		// Check result
		// Bug : Classifier is null in case of Enumeration
		if( classifier == null) {
			return;
		}
		// Set Visibility
		createModifiers(classifier, typeDecl.getModifiers());

		// Template parameters
		// TODO

		// Explore members
		if (typeDecl.getMembers() != null) {
			if (typeDecl instanceof EnumDeclaration) {
				for (EnumConstantDeclaration enumConstant : ((EnumDeclaration) typeDecl).getEntries()) {
					new SwitchVisitor<Type>() {
						@Override
						public void visit(EnumConstantDeclaration n, Type classifier) {
							processEnumConstant(n, classifier);
						}
					}.doSwitch(enumConstant, classifier);
				}
			}
			
			for (BodyDeclaration member : typeDecl.getMembers()) {
				new SwitchVisitor<Type>() {

					@Override
					public void visit(FieldDeclaration n, Type classifier) {
						createAttributes(n, classifier);
					}

					@Override
					public void visit(MethodDeclaration n, Type classifier) {
						processMethod(n, (Classifier) classifier);
					}
					
					@Override
					public void visit(ConstructorDeclaration n, Type classifier) {
						processConstructor(n, (Classifier) classifier);
					}

					/**
					 * Inner classes ?
					 */
					@Override
					public void visit(ClassOrInterfaceDeclaration n, Type classifier) {
						System.out.println("Found nested class (ClassOrInterface)'" + n.getName() + "'");

						// Process nested classifier, and create it in its nested namespace
						// So, increase the enclosing parents.
						enclosingParents.add((Classifier) classifier);
						processTypedeclaration(enclosingParents, n);
						enclosingParents.remove(classifier);
						// processClassOrInterfaceDeclaration(n, parent)
						super.visit(n, classifier);
					}
					
					/**
					 * Inner enums?
					 */
					@Override
					public void visit(EnumDeclaration n, Type classifier) {
						enclosingParents.add((Classifier) classifier);
						processTypedeclaration(enclosingParents, n);
						enclosingParents.remove(classifier);
						super.visit(n, classifier);
					}
					
					// TODO Other kind of members
				}.doSwitch(member, classifier);
			}

		}
	}

	/**
	 * Add all the imports to the manager.
	 *
	 * @param importedTypes2
	 * @param imports
	 */
	private void addImports(ImportedTypeCatalog importedTypes, List<ImportDeclaration> imports) {

		for (ImportDeclaration decl : imports) {
			List<String> qualifiedName = qualifiedNameParser.getImportQualifiedName(decl);
			if (decl.isAsterisk()) {
				// qualifiedName.add("*");
				importedTypes.addStarImport(qualifiedName);
			} 
			else {
				importedTypes.addImport(qualifiedName);
			}
		}

	}

	/**
	 * Get (lookup or create) the specified classifier.
	 * Translate the qualifiedName according to imports, and lookup in classifierCatalog .
	 * If nothing is found and provided name is not qualified, create/get in parent, otherwise create/get in root
	 * using the qualified name.
	 *
	 * @param qualifiedName
	 * @param isInterface
	 *            What should be created if nothing is found ? An interface or a Class ?
	 * @return
	 */
	private Classifier getUmlClassifier(List<String> qualifiedName, boolean isInterface) {
		// Get the corresponding type
		// TODO specify requested type
		// Lookup if it exist in the catalog (under one of the specified path)
		Classifier foundClass = classifierCatalog.getClassifier(qualifiedName);

		// If nothing found, create it
		if (foundClass == null) {// Not yet created.
			EClass expectedType;
			if (isInterface) {
				expectedType = UMLPackage.eINSTANCE.getInterface();
			} else {
				expectedType = UMLPackage.eINSTANCE.getClass_();
			}

			// Check where to create
			if (importedTypes.isImportedType(qualifiedName) || qualifiedName.size() > 1) {
				// This is an imported type or a type with full qualified name => create it in its dedicated model
				// First get the package where to create it, according to its name
				Package creationPackage = creationPackageCatalog.getCreationPackage(qualifiedName, qualifiedNamesInProjects);
				// Now, create it.
				foundClass = UmlUtils.getClassifier(creationPackage, qualifiedName, expectedType);
			} else {
				// If the qualified name is a short name, it might be relative to one of the enclosing namespace.
				// Check if it exist, or create it.
				foundClass = UmlUtils.getGuessedClassifier(enclosingNamespaces, qualifiedName, expectedType);
			}



			// if(qualifiedName.size() == 1 && currentCompilationUnitPackage != null) {
			// // No scope in qname, create it in current namespace.
			// // TODO use current namespace instead of current package.
			//
			// // TODO change to create (avoid second lookup)
			// // foundClass = UmlUtils.getClassifier(currentCompilationUnitPackage, qualifiedName, expectedType);
			// foundClass = UmlUtils.getGuessedClassifier(enclosingNamespaces, qualifiedName.get(0), expectedType);
			// } else {
			// //create with specified qname
			// // First get the package where to create it, according to its name
			// Package creationPackage = creationPackageCatalog.getCreationPackage(qualifiedName);
			// // Now, create it.
			// foundClass = UmlUtils.getClassifier(creationPackage, qualifiedName, expectedType);
			// }
		}
		
		// Apply stereotypes if found class is an external type (not found in any selected project)
		handleExternalType(qualifiedName, foundClass);
		
		return foundClass;
	}
	
	private void handleExternalType(List<String> qualifiedName, Classifier foundClass) {
		if (qualifiedName != null && foundClass != null) {
			if (qualifiedName.size() > 1) {
				String flatQualifiedName = creationPackageCatalog.toFlatQualifiedName(qualifiedName);
				if (qualifiedNamesInProjects != null && !qualifiedNamesInProjects.contains(flatQualifiedName)) {
					// Apply NoCodeGen on packages like java, osgi, etc...
					/*Package creationPackage = creationPackageCatalog.getCreationPackage(qualifiedName, qualifiedNamesInProjects);
					if (creationPackage != null) {
						if (creationPackageCatalog.getDefaultPackage() instanceof Model && !creationPackageCatalog.getDefaultPackage().equals(creationPackage)) {
							// Case of java, osgi, etc... packages that are created for types in packages following a specific pattern
							if (UMLUtil.getStereotypeApplication(creationPackage, NoCodeGen.class) == null) {
								StereotypeUtil.apply(creationPackage, NoCodeGen.class);
							}
						}
					}*/
					
					// Apply External on found class
					applyExternalStereotypes(foundClass, qualifiedName);
				}
			} else if (qualifiedName.size() == 1) {
				if (!shortNameInQualifiedNamesOfProjects(qualifiedName)) {
					// Apply External on found class
					applyExternalStereotypes(foundClass, qualifiedName);
				}
			}
			
		}
	}
	
	private boolean shortNameInQualifiedNamesOfProjects(List<String> qualifiedName) {
		if (qualifiedNamesInProjects == null || qualifiedName == null) {
			return false;
		}
		
		String flatQualifiedName = creationPackageCatalog.toFlatQualifiedName(qualifiedName);
		if (qualifiedName.size() > 0) {
			for (String qualifiedNameInProjects : qualifiedNamesInProjects) {
				if (qualifiedNameInProjects.endsWith(flatQualifiedName)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void applyExternalStereotypes(Classifier foundClass, List<String> qualifiedName) {
		External externalStereotype = UMLUtil.getStereotypeApplication(foundClass, External.class);
		if (externalStereotype == null) {
			StereotypeUtil.apply(foundClass, External.class);
			externalStereotype = UMLUtil.getStereotypeApplication(foundClass, External.class);
		}
		externalStereotype.setName(creationPackageCatalog.toFlatQualifiedName(qualifiedName));
	}

	/**
	 * Get the type qualified name from its ast description
	 *
	 * @param astType
	 * @return
	 */
	private List<String> getQualifiedName(ClassOrInterfaceType astType) {
		QualifiedNameParser visitor = new QualifiedNameParser();

		List<String> res = visitor.getClassOrInterfaceQualifiedName(astType);
		return res;
	}

	/**
	 * Create all attributes and add it to the parent.
	 *
	 * @param n
	 * @param parent
	 */
	protected void createAttributes(FieldDeclaration n, Type parent) {

		System.out.println("getAttributeType( from:" + n.getType().getClass().getName() + ")");

		// Get data about the type
		// Get the qualified name, and other info on type
		TranslatedTypeData typeData = processType(n.getType());
		// Get the uml element from the qualified name
		Type umlType = getUmlType(typeData);

		// walk on variable declarations.
		for (VariableDeclarator var : n.getVariables()) {
			if (parent instanceof Classifier) {
				createAttribute(n, var, (Classifier) parent, umlType, typeData);
			}
		}
	}
	
	/**
	 * Create all enum constants and add it to the parent.
	 *
	 * @param n
	 * @param parent
	 */
	protected void processEnumConstant(EnumConstantDeclaration n, Type parent) {
		if (parent instanceof Enumeration) {
			EnumerationLiteral enumLiteral = (EnumerationLiteral) ((Enumeration) parent).getMember(n.getName(), false, UMLPackage.eINSTANCE.getEnumerationLiteral());
			if (enumLiteral == null) {
				enumLiteral = ((Enumeration) parent).createOwnedLiteral(n.getName());
			}
			
			if (enumLiteral != null) {
				if (n.getArgs() != null && n.getArgs().size() > 0) {
					String arguments = "";
					for (int i = 0; i < n.getArgs().size() - 1; i++) {
						arguments += n.getArgs().get(i) + ", ";
					}
					arguments += n.getArgs().get(n.getArgs().size() - 1);

					if (!arguments.isEmpty()) {
						ValueSpecification specification = enumLiteral.getSpecification();
						if (!(specification instanceof OpaqueExpression)) {
							if (specification != null) {
								enumLiteral.setSpecification(null);
							}
							specification = enumLiteral.createSpecification("specification", null, UMLPackage.eINSTANCE.getOpaqueExpression());
						}
						
						OpaqueExpression opaqueExpression = (OpaqueExpression) specification;
						addLanguageBody("JAVA", arguments, opaqueExpression.getLanguages(), opaqueExpression.getBodies());
					}
				}
				
			}
		}
	}

	/**
	 * Get the qualified name, and other info on type.
	 * Lookup in imports to resolve names.
	 *
	 * @param n
	 * @return
	 */
	private TranslatedTypeData processType(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.Type astType) {
		TranslatedTypeData data = typeAnalyser.getTranslatedTypeData(astType);

		return data;
	}

	/**
	 * Get the uml type from the translated type.
	 *
	 * @param data
	 * @return
	 */
	private Type getUmlType(TranslatedTypeData data) {
		// Get the uml type
		Type umlType = null;
		if (data.isPrimitive) {
			Package javaPrimitivesModel = UmlUtils.importOrGetModel(rootModel, ImportedTypeCatalog.JAVA_LIBRARY_URI);
			String primitiveName = TypeAnalyser.wrapperToPrimitive(data);
			if (primitiveName != null && !primitiveName.isEmpty()) {
				umlType = UmlUtils.getImportedPrimitive(javaPrimitivesModel, primitiveName);
			} else {
				umlType = UmlUtils.getImportedPrimitive(javaPrimitivesModel, data.qualifiedName.get(data.qualifiedName.size() - 1));
			}
			
			if (umlType == null) {
				umlType = classifierCatalog.getClassifier(data.qualifiedName);
				if (umlType == null) {
					Package creationPackage = creationPackageCatalog.getCreationPackage(data.qualifiedName, qualifiedNamesInProjects);
					umlType = UmlUtils.getPrimitive(creationPackage, data.qualifiedName);
				}
			}
		} else if (data.isVoid || data.isWildcard) {
			return null;
		} else {
			// Look for existing classifier of any type.
			// Take the first letter of the name as an hint. IXxxx
			List<String> qualifiedName = data.getTranslatedQualifiedName();
			boolean isInterface = false;
			String shortName = qualifiedName.get(qualifiedName.size() - 1);
			boolean isPrimitiveWrapper = false;
			
			switch (shortName) {
			case "Boolean":
			case "Byte":
			case "Char":
			case "Double":
			case "Float":
			case "Integer":
			case "Long":
			case "Short":
				isPrimitiveWrapper = true;
				break;
			default:
				isPrimitiveWrapper = false;
			}
			
			if (isPrimitiveWrapper) {
				List<String> potentialQualifiedName = importedTypes.getQualifiedName(shortName);
				if (potentialQualifiedName != null
						&& potentialQualifiedName.size() == qualifiedName.size()) {				
					for (int i = 0; i < potentialQualifiedName.size(); i++) {
						if (!potentialQualifiedName.get(i).equals(qualifiedName.get(i))) {
							isPrimitiveWrapper = false;
							break;
						}
					}
					
					if (isPrimitiveWrapper) {
						Package javaPrimitivesModel = UmlUtils.importOrGetModel(rootModel, ImportedTypeCatalog.JAVA_LIBRARY_URI);
						umlType = UmlUtils.getImportedPrimitive(javaPrimitivesModel, shortName);
						if (umlType == null) {
							isPrimitiveWrapper = false;
						}
					}
				}
			}
			
			
			if (!isPrimitiveWrapper) {
				// Try to guess if it is an interface.
				if (shortName.length() > 2 && shortName.startsWith("I") && Character.isUpperCase(shortName.charAt(1))) {
					isInterface = true;
				}

				// Get or create type.
				umlType = getUmlClassifier(qualifiedName, isInterface);
			}
		}
		return umlType;
	}

	/**
	 * Get the Type from an ast.Type node.
	 *
	 * @param astType
	 * @return
	 */
	protected TypeData getAttributeType(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.Type astType) {

		TypeData res = new TypeData();

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
						TypeData argData = new TypeData();
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
					data.extends_ = new TypeData();
					n.getExtends().accept(this, data.extends_);
				}
				if (n.getSuper() != null) {
					data.super_ = new TypeData();
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
	 * Data structure to return data about a Declared type.
	 *
	 * @author dumoulin
	 *
	 */
	protected class TypeData {

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

	/**
	 * Create the uml2.primitivetype denoted by the specified PrimitiveType
	 *
	 * @param n
	 * @return
	 */
	protected String getPrimitiveName(PrimitiveType n) {
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

	/**
	 * Create an attribute and add it to the parent.
	 *
	 * @param n
	 *            node declaring type and modifiers
	 * @param var
	 * @param parent
	 * @param type
	 */
	protected void createAttribute(FieldDeclaration n, VariableDeclarator var, Classifier parent, Type type, TranslatedTypeData typeData) {
		// Get with no type, and then update type.
		Property property = UmlUtils.createProperty(parent, null, var.getId().getName(), var.getId().getArrayCount());
		
		// Type, javadoc, modifiers, upper/lower bounds
		property.setType(type);
		processJavadoc(n.getJavaDoc(), property);
		processModifiers(n.getModifiers(), property);
		if (typeData.isCollection()) {
			property.setLower(typeData.getTranslatedLower());
			property.setUpper(typeData.getTranslatedUpper());
		}
		
		// Default value
		Expression init = var.getInit();
		if (init != null) {
			ValueSpecification defaultValue = property.getDefaultValue();
			if (!(defaultValue instanceof OpaqueExpression)) {
				if (defaultValue != null) {
					property.setDefaultValue(null);
				}
				defaultValue = property.createDefaultValue("defaultValue", null, UMLPackage.eINSTANCE.getOpaqueExpression());
			}
			
			OpaqueExpression opaqueExpression = (OpaqueExpression) defaultValue;
			addLanguageBody("JAVA", init.toString(), opaqueExpression.getLanguages(), opaqueExpression.getBodies());
		}
	}

	/**
	 * Process modifiers for Operation
	 *
	 * @param modifiers
	 * @param property
	 */
	private void processModifiers(int modifiers, Operation property) {
		if (ModifierSet.isAbstract(modifiers)) {
			property.setIsAbstract(true);
		}
		if (ModifierSet.isNative(modifiers)) {
			if (UMLUtil.getStereotypeApplication(property, Native.class) == null) {
				StereotypeUtil.apply(property, Native.class);
			}
		}
		if (ModifierSet.isStrictfp(modifiers)) {
			if (UMLUtil.getStereotypeApplication(property, Strictfp.class) == null) {
				StereotypeUtil.apply(property, Strictfp.class);
			}
		}
		if (ModifierSet.isSynchronized(modifiers)) {
			if (UMLUtil.getStereotypeApplication(property, Synchronized.class) == null) {
				StereotypeUtil.apply(property, Synchronized.class);
			}
		}
		processModifiers(modifiers, (Feature) property);
	}

	/**
	 * Process modifiers
	 *
	 * @param modifiers
	 * @param property
	 */
	private void processModifiers(int modifiers, Feature property) {
		property.setVisibility(VisibilityKind.PRIVATE_LITERAL);
		if (ModifierSet.isPrivate(modifiers)) {
			property.setVisibility(VisibilityKind.PRIVATE_LITERAL);
		}
		if (ModifierSet.isProtected(modifiers)) {
			property.setVisibility(VisibilityKind.PROTECTED_LITERAL);
		}
		if (ModifierSet.isPublic(modifiers)) {
			property.setVisibility(VisibilityKind.PUBLIC_LITERAL);
		}
		if (ModifierSet.isStatic(modifiers)) {
			property.setIsStatic(true);
		}
		if (ModifierSet.isFinal(modifiers)) {
			property.setIsLeaf(true);
		}
		if (property instanceof Property) {
			if (ModifierSet.isTransient(modifiers)) {
				if (UMLUtil.getStereotypeApplication(property, Transient.class) == null) {
					StereotypeUtil.apply(property, Transient.class);
				}
			}
			if (ModifierSet.isVolatile(modifiers)) {
				if (UMLUtil.getStereotypeApplication(property, Volatile.class) == null) {
					StereotypeUtil.apply(property, Volatile.class);
				}
			}
		}
	}

	/**
	 * Process modifiers
	 *
	 * @param modifiers
	 * @param property
	 */
	private void processModifiers(int modifiers, org.eclipse.uml2.uml.Parameter property) {
		if (ModifierSet.isFinal(modifiers)) {
			//property.setDirection(ParameterDirectionKind.IN_LITERAL);
			if (UMLUtil.getStereotypeApplication(property, Final.class) == null) {
				StereotypeUtil.apply(property, Final.class);
			}
		}
	}
	
	private void addLanguageBody(String language, String body, List<String> languages, List<String> bodies) {
		int i = -1;
		
		for (int j = 0; j < languages.size(); j++) {
			if (languages.get(j).equalsIgnoreCase(language)) {
				i = j;
				break;
			}
		}
		
		if (i == -1) {
			languages.add(language);
			i = languages.indexOf(language);
		}
		
		if (bodies.size() >= languages.size()) {
			bodies.set(i, body);
		} else {
			bodies.add(body);
		}
	}

	private class MethodParameterData {

		TranslatedTypeData dataType;

		Type umlType;

		Parameter astParameter;
	}

	/**
	 * Process method to be added to the provided classifier.
	 *
	 * @param n
	 * @param classifier
	 */
	protected void processMethod(MethodDeclaration n, Classifier classifier) {

		// Get parameters to have a signature
		// Parameters
		List<Type> signature = new ArrayList<Type>();

		if (n.getParameters() != null) {
			List<MethodParameterData> paramDatas = new ArrayList<MethodParameterData>();

			for (Parameter param : n.getParameters()) {
				MethodParameterData data = new MethodParameterData();
				data.dataType = processType(param.getType());
				data.umlType = getUmlType(data.dataType);
				data.astParameter = param;
				paramDatas.add(data);
				signature.add(data.umlType);
			}
		}

		// Operation method = UmlUtils.getOperation(classifier, n.getName());
		Operation method = getUmlOperation(classifier, n.getName(), signature);

		processJavadoc(n.getComment(), method);
//		processJavadoc(n.getJavaDoc(), method);
		processAnnotation(n.getAnnotations(), method);
		processModifiers(n.getModifiers(), method);
		TranslatedTypeData typeData = processType(n.getType());
		Type methodType = getUmlType(typeData);

		if (methodType != null) {
			method.setType(methodType);
			if (typeData.isCollection()) {
				method.setLower(typeData.getTranslatedLower());
				method.setUpper(typeData.getTranslatedUpper());
			}
		}

		// Parameters
		if (n.getParameters() != null) {
			for (Parameter param : n.getParameters()) {
				processMethodParameter(param, method);
			}
		}
		
		// Thrown exceptions
		if (n.getThrows() != null) {
			for (ReferenceType thrown : n.getThrows()) {
				TranslatedTypeData exceptionTypeData = processType(thrown);
				Type exceptionUmlType = getUmlType(exceptionTypeData);
				
				if (exceptionUmlType != null && !method.getRaisedExceptions().contains(exceptionUmlType)) {
					method.getRaisedExceptions().add(exceptionUmlType);
				}
			}
		}
		
		// Body
		BlockStmt bodyBlock = n.getBody();
		if (bodyBlock != null) {
			// TODO get comments in body block
			String bodyString = bodyBlock.toString();
			if (bodyString.length() > 1) {
				bodyString = bodyString.substring(0, bodyString.length() - 1); // Remove trailing "}"
				bodyString = bodyString.replaceFirst("\\{", ""); // Remove leading "{"
			}
			
			boolean setBody = false;
			
			for (Behavior behavior : method.getMethods()) {
				if (behavior instanceof OpaqueBehavior) {
					OpaqueBehavior opaqueBehavior = (OpaqueBehavior) behavior;
					if (opaqueBehavior.getBodies().size() >= opaqueBehavior.getLanguages().size()) {
						for (int i = 0; i < opaqueBehavior.getLanguages().size(); i++) {
							if (opaqueBehavior.getLanguages().get(i).equalsIgnoreCase("JAVA")) {
								opaqueBehavior.getBodies().set(i, bodyString);
								setBody = true;
								break;
							}
						}
					}
				}
				
				if (setBody) {
					break;
				}
			}
			
			if (!setBody) {
				if (method.getOwner() instanceof BehavioredClassifier) {
					BehavioredClassifier clazz = (BehavioredClassifier) method.getOwner();
					OpaqueBehavior opaqueBehavior = (OpaqueBehavior) clazz.createOwnedBehavior(method.getName(), UMLPackage.eINSTANCE.getOpaqueBehavior());
					opaqueBehavior.getLanguages().add("JAVA");
					opaqueBehavior.getBodies().add(bodyString);
					opaqueBehavior.setSpecification(method);
				}
			}
		}
	}
	
	/**
	 * Process method to be added to the provided classifier.
	 *
	 * @param n
	 * @param classifier
	 */
	protected void processConstructor(ConstructorDeclaration n, Classifier classifier) {

		// Get parameters to have a signature
		// Parameters
		List<Type> signature = new ArrayList<Type>();

		if (n.getParameters() != null) {
			List<MethodParameterData> paramDatas = new ArrayList<MethodParameterData>();

			for (Parameter param : n.getParameters()) {
				MethodParameterData data = new MethodParameterData();
				data.dataType = processType(param.getType());
				data.umlType = getUmlType(data.dataType);
				data.astParameter = param;
				paramDatas.add(data);
				signature.add(data.umlType);
			}
		}
		
		Operation method = getUmlOperation(classifier, n.getName(), signature);
		
		if (UMLUtil.getStereotypeApplication(method, Create.class) == null) {
			StereotypeUtil.apply(method, Create.class);
		}

		processJavadoc(n.getComment(), method);
		processAnnotation(n.getAnnotations(), method);
		processModifiers(n.getModifiers(), method);
		
		// Parameters
		if (n.getParameters() != null) {
			for (Parameter param : n.getParameters()) {
				processMethodParameter(param, method);
			}
		}
		
		// Thrown exceptions
		if (n.getThrows() != null) {
			for (NameExpr thrown : n.getThrows()) {
				String thrownString = thrown.toString();
				String[] tokens = thrownString.split("\\.");
				List<String> qualifiedName = Arrays.asList(tokens);

				if (qualifiedName.size() == 1) { // Search for imports of the compilation unit, and registered standard java lang types
					qualifiedName = importedTypes.getQualifiedName(qualifiedName.get(0));
				}

				Classifier exceptionClass = getUmlClassifier(qualifiedName, false);
				if (!method.getRaisedExceptions().contains(exceptionClass)) {
					method.getRaisedExceptions().add(exceptionClass);
				}
			}
		}
		
		// Body
		BlockStmt bodyBlock = n.getBlock();
		if (bodyBlock != null) {
			// TODO get comments in body block
			String bodyString = bodyBlock.toString();
			if (bodyString.length() > 1) {
				bodyString = bodyString.substring(0, bodyString.length() - 1); // Remove trailing "}"
				bodyString = bodyString.replaceFirst("\\{", ""); // Remove leading "{"
			}
			
			boolean setBody = false;
			
			for (Behavior behavior : method.getMethods()) {
				if (behavior instanceof OpaqueBehavior) {
					OpaqueBehavior opaqueBehavior = (OpaqueBehavior) behavior;
					if (opaqueBehavior.getLanguages().size() == opaqueBehavior.getBodies().size()) {
						for (int i = 0; i < opaqueBehavior.getLanguages().size(); i++) {
							if (opaqueBehavior.getLanguages().get(i).equalsIgnoreCase("JAVA")) {
								opaqueBehavior.getBodies().set(i, bodyString);
								setBody = true;
								break;
							}
						}
					}
				}
				
				if (setBody) {
					break;
				}
			}
			
			if (!setBody) {
				if (method.getOwner() instanceof BehavioredClassifier) {
					BehavioredClassifier clazz = (BehavioredClassifier) method.getOwner();
					OpaqueBehavior opaqueBehavior = (OpaqueBehavior) clazz.createOwnedBehavior(method.getName(), UMLPackage.eINSTANCE.getOpaqueBehavior());
					opaqueBehavior.getLanguages().add("JAVA");
					opaqueBehavior.getBodies().add(bodyString);
					opaqueBehavior.setSpecification(method);
				}
			}
		}
	}

	/**
	 * Get an operation by its signature
	 *
	 * @param classifier
	 * @param name
	 * @param signature
	 * @return
	 */
	private Operation getUmlOperation(Classifier classifier, String name, List<Type> signature) {
		List<Operation> list = classifier.getOperations();

		// walk all operations with the same name.
		// Check signature matching
		for (Operation oper : list) {

			if (isSameOperation(oper, name, signature)) {
				return oper;
			}
		}
		// Not found, create a new one (without signature)
		return UmlUtils.createOperation(classifier, name);
	}

	/**
	 * @param oper
	 * @param name
	 * @param signature
	 */
	private boolean isSameOperation(Operation oper, String name, List<Type> signature) {

		// check name
		if (!name.equals(oper.getName())) {
			return false;
		}

		// Check parameters
		List<org.eclipse.uml2.uml.Parameter> umlParams = oper.getOwnedParameters();
		// Check signature
		if (signature == null) {
			return true;
		} else if (umlParams == null) {
			if (signature.size() == 0) {
				return true;
			} else {
				return false;
			}
		} else if ( /* signature!= null && */umlParams != null) {
			int umlParamIndex = 0;
			for (Type signatureType : signature) {
				try {
					// Compare type
					org.eclipse.uml2.uml.Parameter param = umlParams.get(umlParamIndex++);
					// skip return parameter
					while (param.getDirection() == ParameterDirectionKind.RETURN_LITERAL) {
						param = umlParams.get(umlParamIndex++);
					}

					// Check types, skip if existing type is null
					Type existingType = param.getType();
					if (existingType != null && !(existingType.getName().equals(signatureType.getName()))) {
						// umlParamIndex > umlParams.getSize()
						return false;
					}
				} catch (IndexOutOfBoundsException e) {

					return false;
				}
			}
			// Check if there still some type in umlParams
			// if index is the last one: ok
			// otherwise, check if remaining arguments are only returns.
			while (umlParamIndex < umlParams.size()) {
				if (umlParams.get(umlParamIndex++).getDirection() != ParameterDirectionKind.RETURN_LITERAL) {
					return false;
				}
			}
			// same
			return true;
		}

		return false;
	}

	/**
	 * Process a parameter.
	 *
	 * @param param
	 * @param method
	 */
	private void processMethodParameter(Parameter param, Operation method) {

		TranslatedTypeData typeData = processType(param.getType());
		Type type = getUmlType(typeData);

		System.out.println("getOwnedParameter(" + method.getName() + "= " + param.getId().getName() + ", " + type + " )");
		org.eclipse.uml2.uml.Parameter umlParameter = method.getOwnedParameter(param.getId().getName(), type, true, true);

		if (typeData.isCollection()) {
			umlParameter.setLower(typeData.getTranslatedLower());
			umlParameter.setUpper(typeData.getTranslatedUpper());
		}
		
		if (param.toString().contains("...")) {
			if (UMLUtil.getStereotypeApplication(umlParameter, Variadic.class) == null) {
				StereotypeUtil.apply(umlParameter, Variadic.class);
			}
		}
		
		processModifiers(param.getModifiers(), umlParameter);
		processAnnotation(param.getAnnotations(), method);

	}

	/**
	 * Process annotation.
	 *
	 * @param annotations
	 * @param method
	 */
	private void processAnnotation(List<AnnotationExpr> annotations, Element method) {
		// TODO Auto-generated method stub
		// Set annotation as stereotype ?
	}

	/**
	 * Process javadoc.
	 *
	 * @param javaDoc
	 * @param method
	 */
	private void processJavadoc(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.comments.Comment javaDoc, Element umlElement) {
		// TODO uncomment this
		/*if (javaDoc == null) {
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
		comment.setBody(javaDoc.getContent());*/
	}

	/**
	 * Create an interface and return it.
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
	protected Class createClass(List<Namespace> enclosingParents, ClassOrInterfaceDeclaration n) {
		System.out.println("getClass( " + n.getName() + " )");
		return UmlUtils.getClass(enclosingParents, n.getName());
	}

	/**
	 * Create an interface and return it.
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
	protected Enumeration createEnumeration(List<Namespace> enclosingParents, EnumDeclaration n) {
		return UmlUtils.getEnumeration(enclosingParents, n.getName());
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
	 * @param n
	 * @return
	 */
	protected Interface createInterface(List<Namespace> enclosingParents, ClassOrInterfaceDeclaration n) {
		System.out.println("getInterface( " + n.getName() + " )");
		return UmlUtils.getInterface(enclosingParents, n.getName());
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
			return defaultGenerationPackage;
		}

		// Get the name
		List<String> qualifiedName = qualifiedNameParser.getPackageQualifiedName(packageDecl);
		// Get the creation model
		Package creationPackage = creationPackageCatalog.getCreationPackage(qualifiedName, qualifiedNamesInProjects);
		// Get the current unit package (where the element are created)
		Package p = UmlUtils.getPackage(creationPackage, qualifiedName);

		return p;
		// question
		// How to let the creationPackageCatalog create the package for a specified name ?
		// CreatePackage visitor = new CreatePackage();
		// Package res = visitor.getPackage(packageDecl, defaultGenerationPackage);

		// return res;
	}

	private void createModifiers(Classifier c, int modifiers) {
		// TODO Auto-generated method stub
		if (ModifierSet.isPrivate(modifiers)) {
			c.setVisibility(VisibilityKind.PRIVATE_LITERAL);
		}
		if (ModifierSet.isProtected(modifiers)) {
			c.setVisibility(VisibilityKind.PROTECTED_LITERAL);
		}
		if (ModifierSet.isPublic(modifiers)) {
			c.setVisibility(VisibilityKind.PUBLIC_LITERAL);
		}
		if (ModifierSet.isAbstract(modifiers)) {
			c.setIsAbstract(true);
		}
		if (ModifierSet.isStatic(modifiers)) {
			if (UMLUtil.getStereotypeApplication(c, StaticClassifier.class) == null) {
				StereotypeUtil.apply(c, StaticClassifier.class);
			}
		}
		if (ModifierSet.isFinal(modifiers)) {
			c.setIsLeaf(true);
		}
		// if (ModifierSet.isNative(modifiers)) {
		// printer.print("native ");
		// }
		if (ModifierSet.isStrictfp(modifiers)) {
			if (UMLUtil.getStereotypeApplication(c, Strictfp.class) == null) {
				StereotypeUtil.apply(c, Strictfp.class);
			}
		}
		// if (ModifierSet.isSynchronized(modifiers)) {
		// printer.print("synchronized ");
		// }
		// if (ModifierSet.isTransient(modifiers)) {
		// printer.print("transient ");
		// }
		// if (ModifierSet.isVolatile(modifiers)) {
		// printer.print("volatile ");
		// }

	}



	/**
	 * Process Class or Interface declaration (only the head of the class, not the members).
	 *
	 * @param n
	 * @param parent
	 * @return
	 */
	private Classifier processClassOrInterfaceDeclaration(ClassOrInterfaceDeclaration n, List<Namespace> enclosingParents) {

		Classifier processedClass;
		if (n.isInterface()) {
			processedClass = createInterface(enclosingParents, n);
		} else {
			processedClass = createClass(enclosingParents, n);
		}

		// Comments
//		processJavadoc(n.getJavaDoc(), processedClass);
		processJavadoc(n.getComment(), processedClass);

		// Extends parameters
		if (n.getExtends() != null) {
			for (ClassOrInterfaceType type : n.getExtends()) { // Get the type
				List<String> qualifiedName = getQualifiedName(type);
				qualifiedName = importedTypes.getQualifiedName(qualifiedName);
				Classifier generalization = getUmlClassifier(qualifiedName, n.isInterface());
				// create the generalization
				UmlUtils.getGeneralization(processedClass, generalization);
			}
		}

		// implements parameters
		if (n.getImplements() != null) {
			for (ClassOrInterfaceType type : n.getImplements()) {
				List<String> qualifiedName = getQualifiedName(type);
				qualifiedName = importedTypes.getQualifiedName(qualifiedName);
				Classifier generalization = getUmlClassifier(qualifiedName, true);
				// create the generalization
				Package parentPackage = (Package) enclosingParents.get(0);
				// TODO use InterfaceRealization instead of Realization
				// Need to store the InterfaceRealization in the right parent
				// UmlUtils.getRealization(parentPackage, processedClass, generalization);
				if (generalization instanceof Interface && processedClass instanceof BehavioredClassifier) {
					UmlUtils.getInterfaceRealization((BehavioredClassifier) processedClass, (Interface) generalization);
				} else
				{
					// should not happen
					UmlUtils.getGeneralization(processedClass, generalization);
				}
			}
		}


		return processedClass;
	}

	/**
	 * Process Class or Interface declaration (only the head of the class, not the members).
	 *
	 * @param n
	 * @param parent
	 * @return
	 */
	private Enumeration processEnumDeclaration(EnumDeclaration n, List<Namespace> enclosingParents) {

		Enumeration processedClass = createEnumeration(enclosingParents, n);

		// Comments
		processJavadoc(n.getJavaDoc(), processedClass);

		return processedClass;
	}
	
	/**
	 * Visitor used to create Package from a qualified names
	 * Example : javagen.parser
	 * trace :
	 * - visitQualifiedName( parser )
	 * - visitName( javagen )
	 *
	 * @author dumoulin
	 *
	 */
	protected class CreatePackage extends VoidVisitorAdapter<List<String>> {


		/**
		 * Create or find the packages corresponding to the qualified name.
		 * This is the main method to be called
		 *
		 * @param decl
		 * @param parent
		 * @return
		 */
		public Package getPackage(PackageDeclaration decl, Package parent) {
			List<String> names = new ArrayList<String>();

			// Create a list of names in proper order.
			decl.accept(this, names);

			Package p = parent;
			// iterate on names
			for (String packageName : names) {
				p = UmlUtils.getPackage(p, packageName);
			}
			return p;
		}

		/**
		 * Create the package part
		 */
		@Override
		public void visit(NameExpr n, List<String> names) {
			// System.out.println("visitName( " + n.getName() + " )");
			// Add in head, because elements are found in reverse order.
			names.add(0, n.getName());
			super.visit(n, names);
		}

		/**
		 *
		 */
		@Override
		public void visit(QualifiedNameExpr n, List<String> names) {
			// System.out.println("visitQualifiedName( " + n.getName() + " )");
			// Add in head, because elements are found in reverse order.
			names.add(0, n.getName());
			super.visit(n, names);
		}
	}

	/**
	 * Visitor used to create a qualified name from ClassOrInterfaceType
	 *
	 * @author dumoulin
	 *
	 */
	protected class QualifiedNameParser extends VoidVisitorAdapter<List<String>> {

		/**
		 * Get the qualified name from the specified ast type
		 *
		 * @param n
		 * @return
		 */
		public List<String> getClassOrInterfaceQualifiedName(ClassOrInterfaceType n) {
			List<String> res = new ArrayList<String>();
			n.accept(this, res);
			return res;
		}

		/**
		 * Get the qualified name from the specified ast type
		 *
		 * @param n
		 * @return
		 */
		public List<String> getPackageQualifiedName(PackageDeclaration n) {
			List<String> res = new ArrayList<String>();
			n.accept(this, res);
			return res;
		}

		/**
		 * Get the qualified name from the specified ast type
		 *
		 * @param n
		 * @return
		 */
		public List<String> getImportQualifiedName(ImportDeclaration n) {
			// Imports are ordered from rootPackage to Type.
			// So, names should be added in before visiting childs.
			return getQualifiedName(n);
		}

		/**
		 * Get the qualified name from the specified ast type
		 *
		 * @param n
		 * @return
		 */
		private List<String> getQualifiedName(Node n) {
			List<String> res = new ArrayList<String>();
			n.accept(this, res);
			return res;
		}

		/**
		 * Create the package part
		 */
		@Override
		public void visit(ClassOrInterfaceType n, List<String> names) {
			// System.out.println("visitName( " + n.getName() + " )");
			// Add in head, because elements are found in reverse order.
			// super.visit(n, names);
			names.add(0, n.getName());
			if (n.getScope() != null) {
				n.getScope().accept(this, names);
			}
		}

		/**
		 * Create the package part
		 */
		@Override
		public void visit(NameExpr n, List<String> names) {
			// Add in head, because elements are found in reverse order.
			// System.out.println("addName( " + n.getName() + " )");
			names.add(0, n.getName());
			// super.visit(n, names);
		}

		/**
	 *
	 */
		@Override
		public void visit(QualifiedNameExpr n, List<String> names) {
			// Add in head, because elements are found in reverse order.
			// System.out.println("addQualifiedName( " + n.getName() + " )");
			names.add(0, n.getName());
			if (n.getQualifier() != null)
			{
				n.getQualifier().accept(this, names);
				// super.visit(n, names);
			}
		}
	}

}
