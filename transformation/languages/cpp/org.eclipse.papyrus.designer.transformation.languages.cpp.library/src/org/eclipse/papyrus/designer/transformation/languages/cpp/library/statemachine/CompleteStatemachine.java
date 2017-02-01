/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher  ansgar.radermacher@cea.fr
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.transformation.languages.cpp.library.statemachine;

import org.eclipse.papyrus.designer.languages.cpp.library.CppUriConstants;
import org.eclipse.papyrus.designer.transformation.base.utils.StdModelLibs;
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoElem;
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Type;

public class CompleteStatemachine implements IM2MTrafoElem {

	public static final String THREAD_FUNCTIONS = "ThreadFunctions"; //$NON-NLS-1$
	public static final String SYSTEM_STATE_ENUM_T = "SystemStateEnum_t"; //$NON-NLS-1$
    public static final String STRUCT_FOR_THREAD = "StructForThread_t"; //$NON-NLS-1$

	@Override
	public void transformElement(M2MTrafo trafo, Element element) {
		if (element instanceof Class) {
			Class tmClass = (Class) element;
			if (tmClass.getClassifierBehavior() instanceof StateMachine) {
				StateMachine sm = (StateMachine) tmClass.getClassifierBehavior();
				SM2ClassesTransformationCore trafoCore = new SM2ClassesTransformationCore(TransformationContext.current.copier, sm, tmClass);

				StdModelLibs.addResource(CppUriConstants.PTHREAD_LIB, TransformationContext.current.copier.source);

				Package smModel = (Package) trafo.getBase_Class().getPackage().getOwner();
				NamedElement stateEnum = smModel.getPackagedElement(SYSTEM_STATE_ENUM_T);
				NamedElement threadFuncEnum = smModel.getPackagedElement(THREAD_FUNCTIONS);
				Type structForThread = (Type) smModel.getPackagedElement(STRUCT_FOR_THREAD);
				trafoCore.setThreadStructType(structForThread);
				trafoCore.setSmPack(smModel);
				tmClass.createOwnedAttribute(SMCodeGeneratorConstants.SYSTEM_STATE_ATTR, (Type) stateEnum);
				tmClass.createDependency(threadFuncEnum);
				trafoCore.transform();
			}
		}
	}
}
