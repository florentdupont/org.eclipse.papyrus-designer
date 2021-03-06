/*******************************************************************************
 * Copyright (c) 2006 - 2012 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.common.base;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.ILangCodegen;
import org.eclipse.papyrus.designer.languages.common.extensionpoints.LanguageCodegen;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.GeneratorHint;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.NoCodeGen;
import org.eclipse.papyrus.infra.tools.file.IPFileSystemAccess;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.util.UMLUtil;


/**
 * Main class of code generator. This is an abstract class that is supposed to be overriden by language specific code generators
 */
abstract public class ModelElementsCreator {

	/**
	 * optional reference to a project (is used in case of handling multiple code generators)
	 */
	protected IProject project;
	
	protected ILocationStrategy locStrategy;

	protected IPFileSystemAccess fileSystemAccess;

	protected String generatorLanguage;
	
	/**
	 *
	 * Constructor.
	 *
	 * @param project
	 *            the project in which the generated code should be placed
	 * @param fileSystemAccess
	 *            a file system access implementation
	 * @param locStrategy
	 *            a strategy that chooses suitable file names for generated code.
	 */
	public ModelElementsCreator(IPFileSystemAccess fileSystemAccess, ILocationStrategy locStrategy, String generatorLanguage) {
		this.fileSystemAccess = fileSystemAccess;
		this.locStrategy = locStrategy;
		this.generatorLanguage = generatorLanguage;
	}

	/**
	 * Obtain fileName of file(s) generated for a named element. Delegates to location strategy.
	 * Caller must eventually add extensions.
	 * 
	 * @param element
	 *            a named element.
	 * @return
	 */
	public String getFileName(NamedElement element) {
		return locStrategy.getFileName(element);
	}

	/**
	 * Creates the file(s) corresponding to a packageable element. This class needs to be
	 * implemented by language specific model elements
	 *
	 * @param folder
	 * @param classifier
	 * @throws CoreException
	 */
	abstract protected void createPackageableElementFile(PackageableElement classifier, IProgressMonitor monitor);

	/**
	 * Return true, if no code should be generated for a certain element. May be overloaded by
	 * specific code generators, but they should delegate to this method (super.noCodeGen) in
	 * case of a non language-specific behavior.
	 *
	 * @param element
	 * @return
	 */
	protected boolean noCodeGen(Element element) {
		return GenUtils.hasStereotype(element, NoCodeGen.class);
	}


	/**
	 * Main function for user calls. Creates code for a packageable element. In case of a passed package, the contained elements
	 * are generated recursively.
	 *
	 * @param monitor
	 *            a progress monitor
	 * @param element
	 *            the element for which code should be generated
	 * @throws CoreException
	 */
	public void createPackageableElement(PackageableElement element, IProgressMonitor monitor) {
		createPackageableElement(element, monitor, true);
	}

	/**
	 * Variant of main function: user may supply explicit container (also used by internal function to avoid
	 * re-calculating the entry container for each element).
	 *
	 * @param element
	 *            An element for which code should be created
	 * @param monitor
	 *            a progress monitor
	 * @param recursive
	 *            if true, step into packages
	 * @throws CoreException
	 */
	public void createPackageableElement(PackageableElement element, IProgressMonitor monitor, boolean recursive)
	{
		if (noCodeGen(element)) {
			return;
		}
		GeneratorHint hint = UMLUtil.getStereotypeApplication(element, GeneratorHint.class);
		if (hint != null && hint.getLanguage() != null) {
			String language = hint.getLanguage().getBase_Class().getName();
			if ((language != null) && (!language.equals(generatorLanguage))) {
				// try to generate code with a new model elements creator for the requested language
				ILangCodegen newGenerator = LanguageCodegen.getGenerator(language);
				// generate code (use same target project. Determine project automatically, if null)
				newGenerator.generateCode(project, element, monitor);
				return;
			}
		}
			
		if (element instanceof Package) {
			Package pkg = (Package) element;
			if (monitor != null) {
				monitor.subTask("generate package " + element.getQualifiedName()); //$NON-NLS-1$
			}

			createPackageableElementFile(element, monitor);

			if (recursive) {
				// Continue generation parsing package contents
				for (PackageableElement subElement : pkg.getPackagedElements()) {
					createPackageableElement(subElement, monitor, recursive);
				}
			}
		}
		else {
			createPackageableElementFile(element, monitor);
		}
	}

	/**
	 * Remove elements (used for differential code generation)
	 *
	 * @param monitor
	 * @param element
	 * @throws CoreException
	 */
	public void removePackageableElement(PackageableElement element, IProgressMonitor monitor) {
		String fileName = getFileName(element);
		fileSystemAccess.deleteFile(fileName); // need recursion support?
	}
}
