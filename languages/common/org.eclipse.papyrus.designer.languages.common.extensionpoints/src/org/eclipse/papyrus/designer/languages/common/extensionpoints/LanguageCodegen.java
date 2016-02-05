/*******************************************************************************
 * Copyright (c) 2015 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - ansgar.radermacher@cea.fr   initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.common.extensionpoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Classifier;

/**
 * Common interface to generate code. Supports for multiple target languages via
 * the Eclipse extension mechanism
 */
public class LanguageCodegen {

	private static final String CLASS = "class"; //$NON-NLS-1$

	private static final String LANGUAGE = "language";//$NON-NLS-1$

	private static final String ID = "id";//$NON-NLS-1$

	public static final String ILANG_SUPPORT_ID = Activator.PLUGIN_ID + ".languageCodegen"; //$NON-NLS-1$

	/**
	 * Interactively choose a generator for a given set of languages (pattern).
	 * If only one generator is available, it returns this generator
	 * immediately.
	 * 
	 * @param languagePattern
	 * @param classifier
	 * @return
	 */
	public static ILangCodegen chooseGenerator(Pattern languagePattern, Classifier classifier) {
		List<ILangCodegen> eligibleGenerators = getEligibleGeneratorList(languagePattern, classifier);

		if (eligibleGenerators.size() == 1) {
			return eligibleGenerators.get(0);
		}
		Shell shell = Display.getDefault().getActiveShell();
		if (eligibleGenerators.size() > 1) {
			GeneratorSelectionDialog dialog = new GeneratorSelectionDialog(shell, eligibleGenerators);
			if (dialog.open() == IDialogConstants.OK_ID) {
				return (ILangCodegen) dialog.getResult()[0];
			}
		}
		else {
			// echo language pattern in a more readible way: without escape characters that are used for
			// the "+" in C++
			String pattern = languagePattern.pattern().replace("\\", ""); //$NON-NLS-1$ //$NON-NLS-2$
			MessageDialog.openError(shell, Messages.LanguageCodegen_NoGeneratorsFound,
					String.format(Messages.LanguageCodegen_NoGeneratorsFoundLong, pattern));			
		}
			
		return null;
	}

	/**
	 * Get a code generator via language and ID.
	 * 
	 * @param languagePattern
	 *            a pattern containing a list of supported languages
	 * @return a code generator
	 */
	public static ILangCodegen getGenerator(String language) {
		// compile language into a pattern, escape "+"
		return getGenerator(Pattern.compile(language.replace("+", "\\+")), null);  //$NON-NLS-1$//$NON-NLS-2$
	}
	
	/**
	 * Get a code generator via language and ID.
	 * 
	 * @param languagePattern
	 *            a pattern containing a list of supported languages
	 * @return a code generator
	 */
	public static ILangCodegen getGenerator(String language, String id) {
		return getGenerator(Pattern.compile(language), id);
	}

	/**
	 * Get a code generator via language and ID.
	 * 
	 * @param languagePattern
	 *            a pattern containing a list of supported languages
	 * @param id
	 *            a string determining the id of the code generator. If null,
	 *            the first available generator is returned.
	 * @return a code generator
	 */
	public static ILangCodegen getGenerator(Pattern languagePattern, String id) {
		List<ILangCodegen> generators = getCodegenList(languagePattern);
		for (ILangCodegen generator : generators) {
			if ((id == null) || generatorIDs.get(generator).equals(id)) {
				return generator;
			}
		}
		throw new RuntimeException(String.format(Messages.LanguageSupport_LanguageNotSupported, languagePattern));
	}

	/**
	 * Get a list of generators that conform a language patterns and are
	 * eligible for a passe classifier
	 * 
	 * @param languagePattern
	 *            a language pattern
	 * @param classifier
	 *            a classifier
	 * @return
	 */
	public static List<ILangCodegen> getEligibleGeneratorList(Pattern languagePattern, Classifier classifier) {
		List<ILangCodegen> eligibleGenerators = new ArrayList<ILangCodegen>();
		for (ILangCodegen generator : getCodegenList(languagePattern)) {
			if (generator instanceof ILangCodegen2) {
				if (((ILangCodegen2) generator).isEligible(classifier)) {
					eligibleGenerators.add(generator);
				}
			}
			else {
				eligibleGenerators.add(generator);
			}
		}
		return eligibleGenerators;
	}

	/**
	 * retrieve a list of code generators for a given language. The language may
	 * be a regular expression
	 * 
	 * @param language
	 * @return
	 */
	public static List<ILangCodegen> getCodegenList(Pattern languagePattern) {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = reg.getConfigurationElementsFor(ILANG_SUPPORT_ID);
		List<ILangCodegen> generators = new ArrayList<ILangCodegen>();

		for (IConfigurationElement configElement : configElements) {
			try {
				final String id = configElement.getAttribute(ID);
				final String extLanguage = configElement.getAttribute(LANGUAGE);
				Matcher m = languagePattern.matcher(extLanguage);
				if (m.matches()) {
					final Object obj = configElement.createExecutableExtension(CLASS);
					if (obj instanceof ILangCodegen) {
						ILangCodegen generator = (ILangCodegen) obj;
						if (!generatorIDs.containsKey(generator)) {
							generatorIDs.put(generator, id);
						}
						generators.add((ILangCodegen) obj);
					}
				}
			} catch (CoreException exception) {
				exception.printStackTrace();
			}
		}
		return generators;
	}
	
	/**
	 * Get a code generator for a given language
	 * @param language
	 * @return
	 * @deprecated This method is deprecated and kept for compatibility, since there could be more than one generator
	 *    for a given programming language.  
	 */
	@Deprecated
	public static ILangCodegen getCodegen(String language)
	{
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] configElements = reg.getConfigurationElementsFor(ILANG_SUPPORT_ID);
		for (IConfigurationElement configElement : configElements) {
			try {
				final String extLanguage = configElement.getAttribute(LANGUAGE);
				if (extLanguage.equals(language)) {
					// TODO: cache returned instance (avoid creating a new instance each time => more efficient, no need for static attributes)
					final Object obj = configElement.createExecutableExtension("class"); //$NON-NLS-1$
					if (obj instanceof ILangCodegen) {
						return (ILangCodegen) obj;
					}
				}
			} catch (CoreException exception) {
				exception.printStackTrace();
			}
		}
		throw new RuntimeException(String.format(Messages.LanguageSupport_LanguageNotSupported, language));
	}
	
	public static String getID(ILangCodegen generator) {
		return generatorIDs.get(generator);
	}
	
	static Map<ILangCodegen, String> generatorIDs = new HashMap<ILangCodegen, String>();
}
