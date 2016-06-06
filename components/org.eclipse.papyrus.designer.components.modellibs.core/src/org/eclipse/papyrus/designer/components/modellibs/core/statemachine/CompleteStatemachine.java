package org.eclipse.papyrus.designer.components.modellibs.core.statemachine;

import org.eclipse.papyrus.designer.components.FCM.ContainerRule;
import org.eclipse.papyrus.designer.components.transformation.core.extensions.AbstractContainerTrafo;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.TransformationException;
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Array;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Type;

public class CompleteStatemachine extends AbstractContainerTrafo {

	private static final String THREAD_FUNCTIONS = "ThreadFunctions"; //$NON-NLS-1$
	private static final String SYSTEM_STATE_ENUM_T = "SystemStateEnum_t"; //$NON-NLS-1$

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
		NamedElement stateStruct = smModel.getPackagedElement(SMCodeGeneratorConstants.STRUCT_FOR_THREAD_SHORTN);
		tmClass.createOwnedAttribute(SMCodeGeneratorConstants.SYSTEM_STATE_ATTR, (Type) stateEnum);
		Property threadStruct = tmClass.createOwnedAttribute(SMCodeGeneratorConstants.THREAD_STRUCTS, (Type) stateStruct);
		Array array = StereotypeUtil.applyApp(threadStruct, Array.class);
		array.setDefinition("[" + SMCodeGeneratorConstants.STATE_MAX + "]"); //$NON-NLS-1$ //$NON-NLS-2$
		tmClass.createDependency(threadFuncEnum);
		trafoCore.threadStructType = (Type) stateStruct;
		trafoCore.transform();
	}
	
	SM2ClassesTransformationCore trafoCore;
}
