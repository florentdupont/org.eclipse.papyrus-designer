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

package org.eclipse.papyrus.designer.languages.cpp.codegen.transformation;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.ToolFactory;
import org.eclipse.cdt.core.formatter.CodeFormatter;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.papyrus.designer.languages.common.base.GenUtils;
import org.eclipse.papyrus.designer.languages.common.base.IPFileSystemAccess;
import org.eclipse.papyrus.designer.languages.common.base.ModelElementsCreator;
import org.eclipse.papyrus.designer.languages.common.base.ProjectBasedFileAccess;
import org.eclipse.papyrus.designer.languages.cpp.codegen.Activator;
import org.eclipse.papyrus.designer.languages.cpp.codegen.Constants;
import org.eclipse.papyrus.designer.languages.cpp.codegen.Messages;
import org.eclipse.papyrus.designer.languages.cpp.codegen.preferences.CppCodeGenUtils;
import org.eclipse.papyrus.designer.languages.cpp.codegen.xtend.CppClassifierGenerator;
import org.eclipse.papyrus.designer.languages.cpp.codegen.xtend.CppPackageHeaderGenerator;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.ExternLibrary;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.External;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Include;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.ManualGeneration;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.NoCodeGen;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Template;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.util.UMLUtil;



/**
 * Main class of C++ code generator
 * 
 * @author Ansgar Radermacher (ansgar.radermacher@cea.fr)
 * @author Önder GÜRCAN (onder.gurcan@cea.fr)
 */
public class CppModelElementsCreator extends ModelElementsCreator {

	/**
	 * Constructor.
	 * 
	 * @param project
	 *            the project in which the generated code should be placed
	 */
	public CppModelElementsCreator(IProject project) {
		this(new ProjectBasedFileAccess(project), null);
	}

	/**
	 * Constructor, allows for non-standard commentHeader
	 * 
	 * @param project
	 *            the project in which the generated code should be placed
	 * @param commentHeader
	 *            Custom prefix for each generated file
	 */
	public CppModelElementsCreator(IProject project, String commentHeader) {
		this(new ProjectBasedFileAccess(project), commentHeader);
	}

	/**
	 * Constructor. Pass caller defined file system access and commentHeader
	 * 
	 * @param project
	 *            the project in which the generated code should be placed
	 * @param commentHeader
	 *            commentHeader. If null, take from preferences
	 */
	public CppModelElementsCreator(IPFileSystemAccess fileSystemAccess, String commentHeader) {
		super(fileSystemAccess, new CppLocationStrategy());
		this.commentHeader = (commentHeader != null) ?
				commentHeader :
				CppCodeGenUtils.getCommentHeader();
		hppExt = CppCodeGenUtils.getHeaderSuffix();
		cppExt = CppCodeGenUtils.getBodySuffix();
	}

	protected String hppExt;

	protected String cppExt;

	protected String commentHeader;

	/**
	 * Creates the files corresponding to the class. For a "simple" class
	 * generates 2 headers (one for the privates concrete operations and one for
	 * the attributes, public operations and virtual / abstract operations and
	 * one body file.
	 * 
	 * @param folder
	 * @param classifier
	 * @throws CoreException
	 */
	@Override
	protected void createPackageableElementFile(PackageableElement element, IProgressMonitor monitor) {
		if (element instanceof Package) {
			generatePkg((Package) element);
		}

		else if ((element instanceof PrimitiveType) || (element instanceof Enumeration) || (element instanceof Usage)) {
			// do nothing, included in package
		}
		else if (element instanceof Classifier) {
			generateClassifier((Classifier) element);
		}
		else if (element instanceof Relationship) {
			// no code generation for relationships
		}
		else if (element instanceof Signal) {
			// TODO: not supported, but do nothing
		}
		else if (element instanceof SignalEvent) {
			// TODO: not supported, but do nothing
		}
		else {
			Activator.log.debug("C++ code generator: unsupported model element " + element); //$NON-NLS-1$
		}
	}

	protected void generateClassifier(Classifier classifier) {

		// treat case of manual code generation
		if (GenUtils.hasStereotype(classifier, ManualGeneration.class)) {
			final ManualGeneration mg = UMLUtil.getStereotypeApplication(classifier, ManualGeneration.class);
			final Include cppInclude = UMLUtil.getStereotypeApplication(classifier, Include.class);
			final String fileContentH = commentHeader + cppInclude.getHeader();

			// generate header code
			final String fileNameH = locStrategy.getFileName(classifier) + Constants.DOT + hppExt;

			generateFile(fileNameH, fileContentH);

			// generate body code
			String fileContentB = commentHeader +
					cppInclude.getPreBody() + GenUtils.NL +
					cppInclude.getBody() + GenUtils.NL;
			String ext = GenUtils.maskNull(mg.getExtensionBody());
			if (ext.length() == 0) {
				ext = cppExt;
			}
			String fileNameB = locStrategy.getFileName(classifier) + Constants.DOT + ext;
			generateFile(fileNameB, fileContentB);
		}

		// Only generate when no CppNoCodeGen stereotype is applied to the class
		else if ((!noCodeGen(classifier)) && (!GenUtils.hasStereotype(classifier, Template.class)) &&
				(!(classifier instanceof Association))) {

			// Template Bound Class
			if (GenUtils.isTemplateBoundElement(classifier)) {
				final String bindHeaderFileName = locStrategy.getFileName(classifier) + Constants.DOT + hppExt;
				generateFile(bindHeaderFileName, commentHeader + CppClassifierGenerator.generateBindHeaderCode(classifier));

				final String bindBodyFileName = locStrategy.getFileName(classifier) + Constants.DOT + cppExt;
				generateFile(bindBodyFileName, commentHeader + CppClassifierGenerator.generateBindBodyCode(classifier));
			}
			else {
				// Class Header file generation
				final String classHeaderFileName = locStrategy.getFileName(classifier) + Constants.DOT + hppExt;
				generateFile(classHeaderFileName, commentHeader + CppClassifierGenerator.generateClassHeaderCode(classifier));

				// Class Body file generation
				if (classifier instanceof Class) {
					final String classBodyFileName = locStrategy.getFileName(classifier) + Constants.DOT + cppExt;
					generateFile(classBodyFileName, commentHeader + CppClassifierGenerator.generateClassBodyCode(classifier));
				}
			}
		}
	}

	protected void generatePkg(Package pkg) {
		final String fileName = locStrategy.getFileName(pkg) + Constants.DOT + hppExt;
		generateFile(fileName, CppPackageHeaderGenerator.generateCode(pkg).toString());
	}

	protected void generateFile(String fileName, String content) {
		fileSystemAccess.generateFile(fileName, format(content));
	}

	/**
	 * Apply the user's currently selected formatting options to the input content. Return the
	 * input String in case of error.
	 */
	private static String format(String content) {

		// do nothing if the CDT plugin is not loaded
		if (Platform.getBundle(CCorePlugin.PLUGIN_ID) == null)
			return content;

		CodeFormatter codeFormatter = ToolFactory.createCodeFormatter(null);
		IDocument doc = new Document(content);

		TextEdit edit = codeFormatter.format(CodeFormatter.K_TRANSLATION_UNIT, doc.get(), 0, doc.get().length(), 0, null);

		if (edit == null) {
			Activator.log.debug(Messages.CppModelElementsCreator_CannotFormatContent);
			return content;
		}

		try {
			edit.apply(doc);
			return doc.get();
		} catch (MalformedTreeException e) {
			Activator.log.error(e);
		} catch (BadLocationException e) {
			Activator.log.error(e);
		}
		return content;
	}

	protected boolean noCodeGen(Element element) {
		return GenUtils.hasStereotype(element, NoCodeGen.class) ||
				GenUtils.hasStereotype(element, External.class) ||
				GenUtils.hasStereotypeTree(element, ExternLibrary.class);
	}
}
