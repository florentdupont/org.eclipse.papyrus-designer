package org.eclipse.papyrus.designer.components.transformation.container;

import org.eclipse.papyrus.designer.components.FCM.ContainerRule;
import org.eclipse.papyrus.designer.components.transformation.extensions.AbstractContainerTrafo;
import org.eclipse.papyrus.designer.components.transformation.extensions.CustomTrafo;
import org.eclipse.papyrus.designer.components.transformation.transformations.LazyCopier;
import org.eclipse.papyrus.designer.components.transformation.transformations.TransformationException;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Package;

public class CustomTrafoDelegate extends AbstractContainerTrafo {

	/**
	 * Constructor
	 *
	 * @param copier
	 *        copier from source to target model
	 * @param tmCDP
	 *        deployment plan within target model
	 */
	public CustomTrafoDelegate(LazyCopier copier, Package tmCDP, String transformationID) {
		this.copier = copier;
		this.tmCDP = tmCDP;
		customTrafo = CustomTrafo.getCustomTrafo(transformationID);
		customTrafo.initialize(copier, tmCDP);
	}
	
	@Override
	public void createContainer(Class smClass, Class tmClass) throws TransformationException {
		customTrafo.createContainer(smClass, tmClass);
	}

	@Override
	public InstanceSpecification createContainerInstance(Class tmComponent, InstanceSpecification tmExecutorIS) throws TransformationException {
		return customTrafo.createContainerInstance(tmComponent, tmExecutorIS);
	}

	@Override
	public void applyRule(ContainerRule smContainerRule, Class smComponent, Class tmComponent) throws TransformationException {
		customTrafo.applyRule(smContainerRule, smComponent, tmComponent);
	}
	
	Class smClass;
	Class tmClass;
	
	AbstractContainerTrafo customTrafo;
}
