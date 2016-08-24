package org.eclipse.papyrus.designer.transformation.core.transformations;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.papyrus.designer.deployment.tools.AllocUtils;
import org.eclipse.papyrus.designer.deployment.tools.ConfigUtils;
import org.eclipse.papyrus.designer.deployment.tools.DepUtils;
import org.eclipse.papyrus.designer.transformation.core.Activator;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;

public class TransformationUtil {
	public static void applyInstanceConfigurators(InstanceSpecification instance) {
		for (Slot slot : instance.getSlots()) {
			InstanceSpecification subInstance = DepUtils.getInstance(slot);
			if (!DepUtils.isShared(slot) && (subInstance != null)) {
				if (slot.getDefiningFeature() instanceof Property) {
					ConfigUtils.configureInstance(subInstance, (Property) slot.getDefiningFeature(), instance);
				}
				applyInstanceConfigurators(subInstance);
			}
		}
	}

	public static void propagateAllocation(InstanceSpecification instance) {
		propagateAllocation(instance, new UniqueEList<InstanceSpecification>());
	}

	public static void propagateAllocation(InstanceSpecification instance, EList<InstanceSpecification> nodes) {
		// create copy of node (otherwise, more and more nodes get aggregated.
		UniqueEList<InstanceSpecification> nodesCopy = new UniqueEList<InstanceSpecification>();
		nodesCopy.addAll(nodes);
		nodesCopy.addAll(AllocUtils.getNodes(instance));
		for (Slot slot : instance.getSlots()) {
			InstanceSpecification subInstance = DepUtils.getInstance(slot);
			if (subInstance != null) {
				if (DepUtils.isShared(slot)) {
					for (InstanceSpecification node : nodesCopy) {
						Activator.log.info(String.format("Propagate node allocation: %s to %s", subInstance.getName(), node.getName())); //$NON-NLS-1$
						AllocUtils.allocate(subInstance, node);
					}
				}
				else {
					propagateAllocation(subInstance, nodesCopy);
				}
			}
		}
	}
}
