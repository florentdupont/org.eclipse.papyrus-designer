package org.eclipse.papyrus.designer.components.modellibs.tracing;

import org.eclipse.papyrus.designer.components.transformation.core.PortUtils;
import org.eclipse.papyrus.designer.components.transformation.core.deployment.DepPlanUtils;
import org.eclipse.papyrus.designer.components.transformation.core.extensions.IInstanceConfigurator;
import org.eclipse.papyrus.designer.components.transformation.core.transformations.container.ContainerTrafo;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;


public class IConfiguratorOTF implements IInstanceConfigurator {

	static final String PROP_PORT_NAME = "portName"; //$NON-NLS-1$

	static final String PROP_INSTANCE_NAME = "instanceName"; //$NON-NLS-1$

	/**
	 * Configure the passed trace instance
	 *
	 * @see org.eclipse.papyrus.designer.components.transformation.gentools.core.extensions.IInstanceConfigurator
	 */
	@Override
	public void configureInstance(InstanceSpecification instance, Property componentPart, InstanceSpecification parentInstance) {
		// The tracing code needs informations about the component instance and port.

		DepPlanUtils.configureProperty(instance, PROP_INSTANCE_NAME, StringConstants.QUOTE + instance.getName() + StringConstants.QUOTE);

		// port in context => interception of port => provide information about port and interface
		ContainerTrafo containerTrafo = ContainerTrafo.getContainerTrafo(parentInstance);
		if (containerTrafo == null) {
			return;
		}
		Port port = containerTrafo.getInterceptedPort(componentPart);
		if (port != null) {
			// obtain required or provided interface (TODO: will fail, if both are provided!)
			Interface intf = PortUtils.getProvided(port);
			if (intf == null) {
				intf = PortUtils.getRequired(port);
			}

			DepPlanUtils.configureProperty(instance, PROP_PORT_NAME, StringConstants.QUOTE + port.getName() + StringConstants.QUOTE);
			if (intf != null) {
				// this is specific for OTF:
				// each container contains an attribute (id_<name>) for each operation. This is configured here,
				// since we add instance information to the trace (is that useful??, seems like a hack)
				// TODO: originally, we used executorIS *in source model*
				for (Operation op : intf.getOperations()) {
					String id = PackageUtil.getRootPackage(instance).getName() + "::Tracing::Trace::ID_" + //$NON-NLS-1$
							instance.getName().replace(".", "_") + "_" + op.getName(); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
					DepPlanUtils.configureProperty(instance, "id_" + op.getName(), id); //$NON-NLS-1$
				}
			}
		}
	}
}
