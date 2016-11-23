/*******************************************************************************
 * Copyright (c) 2006, 2016 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CEA LIST - initial API and implementation
 *     
 *     
 *******************************************************************************/
/**
 */
package org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.papyrus.designer.languages.java.profile.PapyrusJava.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PapyrusJavaFactoryImpl extends EFactoryImpl implements PapyrusJavaFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PapyrusJavaFactory init() {
		try {
			PapyrusJavaFactory thePapyrusJavaFactory = (PapyrusJavaFactory)EPackage.Registry.INSTANCE.getEFactory(PapyrusJavaPackage.eNS_URI);
			if (thePapyrusJavaFactory != null) {
				return thePapyrusJavaFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PapyrusJavaFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PapyrusJavaFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PapyrusJavaPackage.ARRAY: return createArray();
			case PapyrusJavaPackage.EXTERNAL: return createExternal();
			case PapyrusJavaPackage.EXTERN_LIBRARY: return createExternLibrary();
			case PapyrusJavaPackage.IMPORT: return createImport();
			case PapyrusJavaPackage.NO_CODE_GEN: return createNoCodeGen();
			case PapyrusJavaPackage.TEMPLATE: return createTemplate();
			case PapyrusJavaPackage.TEMPLATE_BINDING: return createTemplateBinding();
			case PapyrusJavaPackage.TEMPLATE_PARAMETER: return createTemplateParameter();
			case PapyrusJavaPackage.MANUAL_GENERATION: return createManualGeneration();
			case PapyrusJavaPackage.VARIADIC: return createVariadic();
			case PapyrusJavaPackage.VOLATILE: return createVolatile();
			case PapyrusJavaPackage.TRANSIENT: return createTransient();
			case PapyrusJavaPackage.SYNCHRONIZED: return createSynchronized();
			case PapyrusJavaPackage.STRICTFP: return createStrictfp();
			case PapyrusJavaPackage.NATIVE: return createNative();
			case PapyrusJavaPackage.DEFAULT: return createDefault();
			case PapyrusJavaPackage.STATIC_CLASSIFIER: return createStaticClassifier();
			case PapyrusJavaPackage.FINAL: return createFinal();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Array createArray() {
		ArrayImpl array = new ArrayImpl();
		return array;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public External createExternal() {
		ExternalImpl external = new ExternalImpl();
		return external;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternLibrary createExternLibrary() {
		ExternLibraryImpl externLibrary = new ExternLibraryImpl();
		return externLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Import createImport() {
		ImportImpl import_ = new ImportImpl();
		return import_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoCodeGen createNoCodeGen() {
		NoCodeGenImpl noCodeGen = new NoCodeGenImpl();
		return noCodeGen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Template createTemplate() {
		TemplateImpl template = new TemplateImpl();
		return template;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemplateBinding createTemplateBinding() {
		TemplateBindingImpl templateBinding = new TemplateBindingImpl();
		return templateBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemplateParameter createTemplateParameter() {
		TemplateParameterImpl templateParameter = new TemplateParameterImpl();
		return templateParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManualGeneration createManualGeneration() {
		ManualGenerationImpl manualGeneration = new ManualGenerationImpl();
		return manualGeneration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variadic createVariadic() {
		VariadicImpl variadic = new VariadicImpl();
		return variadic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Volatile createVolatile() {
		VolatileImpl volatile_ = new VolatileImpl();
		return volatile_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transient createTransient() {
		TransientImpl transient_ = new TransientImpl();
		return transient_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Synchronized createSynchronized() {
		SynchronizedImpl synchronized_ = new SynchronizedImpl();
		return synchronized_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Strictfp createStrictfp() {
		StrictfpImpl strictfp_ = new StrictfpImpl();
		return strictfp_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Native createNative() {
		NativeImpl native_ = new NativeImpl();
		return native_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Default createDefault() {
		DefaultImpl default_ = new DefaultImpl();
		return default_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticClassifier createStaticClassifier() {
		StaticClassifierImpl staticClassifier = new StaticClassifierImpl();
		return staticClassifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Final createFinal() {
		FinalImpl final_ = new FinalImpl();
		return final_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PapyrusJavaPackage getPapyrusJavaPackage() {
		return (PapyrusJavaPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PapyrusJavaPackage getPackage() {
		return PapyrusJavaPackage.eINSTANCE;
	}

} //PapyrusJavaFactoryImpl
