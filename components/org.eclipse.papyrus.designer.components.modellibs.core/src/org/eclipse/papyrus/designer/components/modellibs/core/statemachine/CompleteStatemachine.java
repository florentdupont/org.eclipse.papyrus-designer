/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.components.modellibs.core.statemachine;

import org.eclipse.papyrus.designer.components.FCM.ContainerRule;
import org.eclipse.papyrus.designer.components.transformation.core.extensions.AbstractContainerTrafo;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.TransformationException;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Type;

public class CompleteStatemachine extends AbstractContainerTrafo {

	private static final String THREAD_FUNCTIONS = "ThreadFunctions"; //$NON-NLS-1$
	private static final String SYSTEM_STATE_ENUM_T = "SystemStateEnum_t"; //$NON-NLS-1$
	public static String STRUCT_FOR_THREAD = "StructForThread_t";

	@Override
	public void createContainer(Class smClass, Class tmClass) throws TransformationException {
		if (smClass.getClassifierBehavior() instanceof StateMachine) {
			StateMachine sm = (StateMachine) smClass.getClassifierBehavior();
			trafoCore = new SM2ClassesTransformationCore(copier, smClass, sm, tmClass);
		}
	}
 
	@Override
	public InstanceSpecification createContainerInstance(Class tmClass, InstanceSpecification tmExecutorIS) throws TransformationException {
		// no configuration is done
		return tmExecutorIS;
	}

	@Override
	public void applyRule(ContainerRule smContainerRule, Class smClass, Class tmClass) throws TransformationException {
		Package smModel = (Package) smContainerRule.getBase_Class().getPackage().getOwner();
		NamedElement stateEnum = smModel.getPackagedElement(SYSTEM_STATE_ENUM_T);
		NamedElement threadFuncEnum = smModel.getPackagedElement(THREAD_FUNCTIONS);
		Type structForThread = (Type) smModel.getPackagedElement(STRUCT_FOR_THREAD);
		trafoCore.setThreadStructType(structForThread);
		trafoCore.setSmPack(smModel);
		tmClass.createOwnedAttribute(SMCodeGeneratorConstants.SYSTEM_STATE_ATTR, (Type) stateEnum);
		tmClass.createDependency(threadFuncEnum);
		trafoCore.transform();
	}
	
	SM2ClassesTransformationCore trafoCore;
}
