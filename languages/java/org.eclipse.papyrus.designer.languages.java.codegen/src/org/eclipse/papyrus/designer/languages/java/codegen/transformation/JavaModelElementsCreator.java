/*******************************************************************************
 * Copyright (c) 2006 - 2016 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Shuai Li (CEA LIST) <shuai.li@cea.fr> - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.java.codegen.transformation;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.papyrus.designer.languages.common.base.GenUtils;
import org.eclipse.papyrus.designer.languages.common.base.IPFileSystemAccess;
import org.eclipse.papyrus.designer.languages.common.base.ModelElementsCreator;
import org.eclipse.papyrus.designer.languages.common.base.ProjectBasedFileAccess;
import org.eclipse.papyrus.designer.languages.java.codegen.xtend.JavaClassifierGenerator;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ExternLibrary;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.External;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Import;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.ManualGeneration;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.NoCodeGen;
import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.Template;
import org.eclipse.papyrus.designer.languages.java.codegen.Activator;
import org.eclipse.papyrus.designer.languages.java.codegen.Constants;
import org.eclipse.papyrus.designer.languages.java.codegen.Messages;
import org.eclipse.papyrus.designer.languages.java.codegen.preferences.JavaCodeGenUtils;
import org.eclipse.papyrus.designer.languages.java.codegen.utils.LocateJavaProject;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Main class of Java code generator
 */
public class JavaModelElementsCreator extends ModelElementsCreator {

	private String sourceFolder;
	
	private String prefix;
	
	/**
	 * Constructor.
	 * 
	 * @param project
	 *            the project in which the generated code should be placed
	 */
	public JavaModelElementsCreator(IProject project, PackageableElement packageableElement) {
		this(new ProjectBasedFileAccess(project), null);
		sourceFolder = LocateJavaProject.getTargetSourceFolder(packageableElement, project);
		prefix = LocateJavaProject.getTargetPrefix(packageableElement);
		if (prefix != null) {
			sourceFolder = sourceFolder + prefix.replaceAll("\\.", "/");
		} else {
			prefix = "";
		}
	}

	/**
	 * Constructor, allows for non-standard commentHeader
	 * 
	 * @param project
	 *            the project in which the generated code should be placed
	 * @param commentHeader
	 *            Custom prefix for each generated file
	 */
	public JavaModelElementsCreator(IProject project, String commentHeader, PackageableElement packageableElement) {
		this(new ProjectBasedFileAccess(project), commentHeader);
		sourceFolder = LocateJavaProject.getTargetSourceFolder(packageableElement, project);
		prefix = LocateJavaProject.getTargetPrefix(packageableElement);
		if (prefix != null) {
			sourceFolder = sourceFolder + prefix.replaceAll("\\.", "/");
		} else {
			prefix = "";
		}
	}

	/**
	 * Constructor. Pass caller defined file system access and commentHeader
	 * 
	 * @param project
	 *            the project in which the generated code should be placed
	 * @param commentHeader
	 *            commentHeader. If null, take from preferences
	 */
	public JavaModelElementsCreator(IPFileSystemAccess fileSystemAccess, String commentHeader) {
		super(fileSystemAccess, new JavaLocationStrategy());
		this.commentHeader = (commentHeader != null) ?
				commentHeader :
				JavaCodeGenUtils.getCommentHeader();
		javaExt = JavaCodeGenUtils.getHeaderSuffix();
	}
	
	protected String javaExt;

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
		if ((element instanceof PrimitiveType) || (element instanceof Usage)) {
			// do nothing
		} else if (element instanceof Classifier) {
			generateClassifier((Classifier) element, prefix);
		} else if (element instanceof Relationship) {
			// no code generation for relationships
		} else if (element instanceof Signal) {
			// TODO: not supported, but do nothing
		} else if (element instanceof SignalEvent) {
			// TODO: not supported, but do nothing
		} else {
			Activator.log.debug("Java code generator: unsupported model element " + element); //$NON-NLS-1$
		}
	}

	protected void generateClassifier(Classifier classifier, String prefix) {
		// treat case of manual code generation
		if (GenUtils.hasStereotype(classifier, ManualGeneration.class)) {
			final ManualGeneration mg = UMLUtil.getStereotypeApplication(classifier, ManualGeneration.class);
			final Import javaImport = UMLUtil.getStereotypeApplication(classifier, Import.class);
			String includes = "";
			if (javaImport != null) {
				includes = javaImport.getManualImports();
			}
			final String fileContentH = commentHeader + includes;

			// Generate file
			final String fileNameH = sourceFolder + locStrategy.getFileName(classifier) + Constants.DOT + javaExt;
			generateFile(fileNameH, fileContentH);
		} else if ((!noCodeGen(classifier)) && (!GenUtils.hasStereotype(classifier, Template.class)) &&
				(!(classifier instanceof Association))) {
			// Only generate when no NoCodeGen stereotype is applied to the class
			
			// Generate file
			final String classHeaderFileName = sourceFolder + locStrategy.getFileName(classifier) + Constants.DOT + javaExt;
			generateFile(classHeaderFileName, commentHeader + JavaClassifierGenerator.generateClassCode(classifier, prefix));
		}
	}
	
	protected void generateFile(String fileName, String content) {
		fileSystemAccess.generateFile(fileName, format(content));
	}

	/**
	 * Apply the user's currently selected formatting options to the input content. Return the
	 * input String in case of error.
	 */
	private static String format(String content) {

		// do nothing if the JDT plugin is not loaded
		if (Platform.getBundle(JavaCore.PLUGIN_ID) == null)
			return content;

		CodeFormatter codeFormatter = ToolFactory.createCodeFormatter(null);
		IDocument doc = new Document(content);

		TextEdit edit = codeFormatter.format(CodeFormatter.K_COMPILATION_UNIT, doc.get(), 0, doc.get().length(), 0, null);

		if (edit == null) {
			Activator.log.debug(Messages.JavaModelElementsCreator_CannotFormatContent);
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
