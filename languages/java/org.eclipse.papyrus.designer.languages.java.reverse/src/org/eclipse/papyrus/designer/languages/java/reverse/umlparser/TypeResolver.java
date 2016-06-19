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

package org.eclipse.papyrus.designer.languages.java.reverse.umlparser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.papyrus.designer.languages.java.reverse.exception.ClassifierNeedCreationException;
import org.eclipse.papyrus.designer.languages.java.reverse.exception.ClassifierNotFoundException;
import org.eclipse.papyrus.designer.languages.java.reverse.exception.ImportNotFoundException;
import org.eclipse.papyrus.designer.languages.java.reverse.exception.NestedClassifierNeedCreationException;
import org.eclipse.papyrus.designer.languages.java.reverse.exception.NotFoundException;
import org.eclipse.papyrus.designer.languages.java.reverse.jdt.LocalContext;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Type;

/**
 * Instance of this class is used to resolve uml::Type.
 * This class allows to find a Type qualified name from its Name (simple or qualified) used in the Java file.
 * 
 * 
 * @author cedric dumoulin
 *
 * @since 0.7.2
 */
public class TypeResolver {

	protected ClassifierCatalog classifierCatalog;
	protected ImportedTypeCatalog importedTypeCatalog;
	protected CreationPackageCatalog creationPackageCatalog;
	
	/**
	 * Constructor.
	 *
	 * @param classifierCatalog
	 * @param importedTypeCatalog
	 */
	public TypeResolver(ClassifierCatalog classifierCatalog, ImportedTypeCatalog importedTypeCatalog, CreationPackageCatalog creationPackageCatalog) {
		this.classifierCatalog = classifierCatalog;
		this.importedTypeCatalog = importedTypeCatalog;
		this.creationPackageCatalog = creationPackageCatalog;
	}

	/**
	 * Lookup for the {@link Classifier} denoted byt the provided name. The name is either a simple name (no dot) or a 
	 * qualified name (with dot).
	 * If found, the Classifier is returned. If not found, appropriate exception is thrown :
	 * <ul>
	 *   <li></li>
	 *   <li></li>
	 *   <li></li>
	 * </ul>
	 * 
	 * @param name Name of the classifier to search for. Can be a simple name or a qualified name.
	 * @param context
	 * @return the corresponding Classifer.
	 * 
	 * @throws ClassifierNotFoundException 
	 * @throws NestedClassifierNeedCreationException 
	 * @throws ClassifierNeedCreationException 
	 */
	public Classifier lookupClassifier( String name, LocalContext context ) throws ClassifierNotFoundException, ClassifierNeedCreationException, NestedClassifierNeedCreationException {
		
		// Search locally
		Classifier result = lookupInImportsAndContext( name, context );
		return result;
	}

	/**
	 * Search for a Classifier by its Name. 
	 * Search is done 'locally' : using the 'import', and the context.
	 * 
	 * 
	 * @param name Name of the classifier to search for
	 * @return The requested Classifier.
	 * @throws ClassifierNotFoundException 
	 * @throws ClassifierNeedCreationException 
	 * @throws NestedClassifierNeedCreationException 
	 */
	private Classifier lookupInImportsAndContext(String name, LocalContext context) throws ClassifierNotFoundException, ClassifierNeedCreationException, NestedClassifierNeedCreationException {

		if( isSimpleName(name) ) {
			return lookupClassifierFromSimpleName( name, context );
		}
		else {
			List<String> qualifiedName = UmlUtils.toQualifiedName(name);

			return lookupClassifierFromTypename(qualifiedName, context );
		}
	}

	/**
	 * Lookup for the classifier from a simple name.
	 * <ul>
	 *   <li></li>
	 *   <li></li>
	 *   <li></li>
	 * </ul>
	 * 
	 * @param sname
	 * @param context
	 * @return The requested {@link Classifier}
	 * @throws ClassifierNotFoundException A valid qualified name is found (in imports), but no classifier is found in classifier catalog. The exception contains the qname.
	 * @throws ClassifierNeedCreationException A qualified name is found for the sname, but the corresponding Classifier is not found in the classifierCatalog.
	 * @throws NotFoundException No Classifier can be found for the sname.
	 */
	private Classifier lookupClassifierFromSimpleName(String sname, LocalContext context) throws ClassifierNotFoundException, ClassifierNeedCreationException {
		
		Classifier result;
		// First, check if the sname is declared in imports
		try {
			List<String> qualifiedName = importedTypeCatalog.getImportQualifiedNameChecked(sname);
			result =  classifierCatalog.getClassifier(qualifiedName);
			if( result != null ) {
				return result;
			}
			// QName found, but the classifier not exist in catalog
			throw new ClassifierNeedCreationException(qualifiedName);
		} catch (ImportNotFoundException e) {
			// silently fails;
		}
		
		// check in '*' imports
		// Only check if the requested classifier exist in UML model.
		for( List<String> parentPackageQName : importedTypeCatalog.getStarImports() ) {
			// Build a possible Fully qualified name
			List<String> fullQName = new ArrayList<String>(parentPackageQName);
			fullQName.add(sname);
			
			result = classifierCatalog.getClassifier(fullQName);
			if( result != null) {
				return result;
			}
		}
		
		// Still not found
		// Check in context
		// Check if a corresponding type can be found in context.
		result = context.lookupClassifier(sname, UmlUtils.CLASSIFIER_TYPE);
		if( result != null) {
			return result;
		}
		
		// Check in classifier catalog : maybe the sname is a Class in root packages ?
		result = classifierCatalog.getClassifier(sname);
		if( result != null) {
			return result;
		}
		
		// Still not found
		throw new ClassifierNotFoundException(sname);
		
	}

	/**
	 * Lookup for the Classifier from a typename. A typename is a qualified name where the first name denote a type.
	 * (ex : Typename.A);
	 * Check if the first Typename (first part of the qname) can be found in import, '*' and then Context.
	 * If a corresponding Classifier is found, try to get the requested Classifier from this found Classifier.
	 * 
	 * @param qualifiedName
	 * @param context
	 * @throws NestedClassifierNeedCreationException The first segment of the qualifiedName denote a Classifier. This classifier, or its name has been found, but the nested classifier does not exist. 
	 * @throws ClassifierNotFoundException No Classifier found for the requested name.
	 */
	private Classifier lookupClassifierFromTypename(List<String> qualifiedName, LocalContext context) throws NestedClassifierNeedCreationException, ClassifierNotFoundException {
	
		String typeName = qualifiedName.get(0);
		Classifier result;
		Classifier typenameClassifier;
		
		// Search the Classifier corresponding to the Typename
		try {
			typenameClassifier = lookupInImportsAndContext(typeName, context);
			// Found. Now, try to get the requested Classifier from this typenameClassifier
			List<String> searchedQualifiedName = qualifiedName.subList(1, qualifiedName.size());
			result = UmlUtils.lookupNestedClassifier(typenameClassifier, qualifiedName, UmlUtils.CLASSIFIER_TYPE);
		} catch (ClassifierNeedCreationException e) {
			// We have the qualified name of the parent classifier, but this later need to be created.
			throw new NestedClassifierNeedCreationException(e.getQualifiedName(),  qualifiedName);
		} catch (ClassifierNotFoundException e) {
			// Find not typename for the qualified name.
			// Try to use the qname as a fully qualified name
			result = classifierCatalog.getClassifier(qualifiedName);
			if( result != null) {
				return result;
			}
			// Still not found
			throw new ClassifierNotFoundException( qualifiedName );
		} 
		
		// Classifier found ?
		if( result != null) {
			return result;
		}
		// Not exist !
		// We found the parentClassifier, but no requested classifier inside.
		throw new NestedClassifierNeedCreationException(typenameClassifier,  qualifiedName);

	}

	/**
	 * Return true if the name is a simple name (no dot), false otehrwise.
	 * 
	 * @param name The name to check
	 * @return true if the name is a simple name, false otherwise.
	 * 
	 */
	private boolean isSimpleName(String name) {
		return (name.indexOf('.')==-1);
	}

	/**
	 * Get the type corresponding to the type name. Lookup the type, and create it if not found.
	 * Lookup is done by using {@link #lookupClassifier(String, LocalContext)};
	 * Creation is done in the namespace provided by {@link CreationPackageCatalog}.
	 * 
	 * @param typename Name of the classifier to search for. Can be a simple name or a qualified name.
	 * @param context
	 * @return
	 */
	public Type getType(String typename, LocalContext context) {
		
		Type result;
		// First, try to found the type
		try {
			result = lookupClassifier(typename, context);
			return result;
		} catch (ClassifierNotFoundException e) {
			List<String> qualifiedName;
			// Try to create the classifier
			if( e.getRequestedSimpleName() != null) {
				// Simple name ==> create it in the current package from context
				qualifiedName = UmlUtils.toQualifiedName(e.getRequestedSimpleName());
				Package creationPackage = context.getCurrentPackage();
				EClass requestedType = creationPackageCatalog.guessBestType( qualifiedName );
				result = UmlUtils.getClassifier(creationPackage, qualifiedName, requestedType);
			}
			else {
				// Create using the qualified name.
				qualifiedName = e.getRequestedQualifiedName();
				Package creationPackage = creationPackageCatalog.getCreationPackage(qualifiedName);
				EClass requestedType = creationPackageCatalog.guessBestType( qualifiedName );
				result = UmlUtils.getClassifier(creationPackage, qualifiedName, requestedType);
			}
			
			return result;
			
		} catch (ClassifierNeedCreationException e) {
			// Try to create the classifier
			List<String> qualifiedName = e.getQualifiedName();
			Package creationPackage = creationPackageCatalog.getCreationPackage(qualifiedName);
			EClass requestedType = creationPackageCatalog.guessBestType( qualifiedName );
			result = UmlUtils.getClassifier(creationPackage, qualifiedName, requestedType);

			return result;
			
		} catch (NestedClassifierNeedCreationException e) {
			// Create the nested classifier
			EClass requestedType = creationPackageCatalog.guessBestType( e.getRequestedNestedClassifierRelativename() );
			if( e.getParentClassifier() != null) {
				// Parent is known, create the nested classifier inside it.
			   result = UmlUtils.getNestedClassifier(e.getParentClassifier(), e.getRequestedNestedClassifierRelativename(), requestedType);
			}
			else {
				// Create parent first
				List<String> parentQualifiedName = e.getParentQualifiedname();
				Package creationPackage = creationPackageCatalog.getCreationPackage(parentQualifiedName);
				EClass parentRequestedType = creationPackageCatalog.guessBestType( parentQualifiedName );
				Classifier parent = UmlUtils.getClassifier(creationPackage, parentQualifiedName, parentRequestedType);
				
			    result = UmlUtils.getNestedClassifier(parent, e.getRequestedNestedClassifierRelativename(), requestedType);
			}
			
			return result;
		}
	}
}
