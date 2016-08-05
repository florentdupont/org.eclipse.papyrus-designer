package org.eclipse.papyrus.designer.transformation.library.statemachine;

import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Array;
import org.eclipse.papyrus.designer.transformation.core.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Type;

public class CompleteStatemachine implements IM2MTrafo {

	private static final String THREAD_FUNCTIONS = "ThreadFunctions"; //$NON-NLS-1$
	private static final String SYSTEM_STATE_ENUM_T = "SystemStateEnum_t"; //$NON-NLS-1$

	@Override
	public void transformElement(LazyCopier copier, M2MTrafo trafo, Element element) {
		if (element instanceof Class) {
			Class tmClass = (Class) element;
			if (tmClass.getClassifierBehavior() instanceof StateMachine) {
				StateMachine sm = (StateMachine) tmClass.getClassifierBehavior();
				SM2ClassesTransformationCore trafoCore = new SM2ClassesTransformationCore(copier, sm, tmClass);

			Package smModel = (Package) trafo.getBase_Class().getPackage().getOwner();
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
		}
	}
}
