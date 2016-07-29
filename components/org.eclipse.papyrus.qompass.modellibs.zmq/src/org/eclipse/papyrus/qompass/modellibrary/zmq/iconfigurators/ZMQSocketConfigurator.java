package org.eclipse.papyrus.qompass.modellibrary.zmq.iconfigurators;

import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.components.transformation.core.deployment.AllocUtils;
import org.eclipse.papyrus.designer.components.transformation.core.deployment.DepPlanUtils;
import org.eclipse.papyrus.designer.components.transformation.core.deployment.DepUtils;
import org.eclipse.papyrus.designer.components.transformation.core.extensions.IInstanceConfigurator;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;

/**
 * Instance configurator for socket. It is responsible for allocating the (server) nodeID
 * property of the client stub.
 */
public class ZMQSocketConfigurator implements IInstanceConfigurator {

	private static final String CLIENT_STUB = "clientStub"; //$NON-NLS-1$

	public static final String clientName = "clientFragment"; //$NON-NLS-1$
	
	public static final String serverName = "serverFragment"; //$NON-NLS-1$

	public static final String NODE_ID = "m_nodeID"; //$NON-NLS-1$
	
	public void configureInstance(InstanceSpecification instance,
			Property componentPart, InstanceSpecification parentInstance) {
		Classifier socketCl = DepUtils.getClassifier(instance);
		if (socketCl instanceof Class) {
			Class socket = (Class) socketCl;
			Property clientFragment = socket.getOwnedAttribute(clientName, null);
			Property serverFragment = socket.getOwnedAttribute(serverName, null);
			Property client = ((Classifier) clientFragment.getType()).getAttribute(CLIENT_STUB, null);
					
			// obtain reference to client and server fragment instances
			Slot clientFragSlot = DepUtils.getSlot(instance, clientFragment);
			Slot serverFragSlot = DepUtils.getSlot(instance, serverFragment);
			
			InstanceSpecification serverAlloc = AllocUtils.getNode(DepUtils.getInstance(serverFragSlot));
			
			Package cdp = instance.getNearestPackage();
			EList<InstanceSpecification> allAllocs = AllocUtils.getAllNodes(DepUtils.getMainInstance(cdp));
			int nodeID = allAllocs.indexOf(serverAlloc);
			
			InstanceSpecification clientFragInstance = DepUtils.getInstance(clientFragSlot);
			Slot clientSlot = DepUtils.getSlot(clientFragInstance, client);
			InstanceSpecification clientInstance = DepUtils.getInstance(clientSlot);
			
			DepPlanUtils.configureProperty(clientInstance, NODE_ID, nodeID);
		}
	}
}
