/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
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

package org.eclipse.papyrus.designer.transformation.languages.cpp.library;

import java.util.HashMap
import java.util.Stack
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.URI
import org.eclipse.papyrus.designer.deployment.tools.DepUtils
import org.eclipse.papyrus.designer.languages.cpp.profile.C_Cpp.Include
import org.eclipse.papyrus.designer.transformation.base.utils.ElementUtils
import org.eclipse.papyrus.designer.transformation.base.utils.LibraryUtils
import org.eclipse.papyrus.designer.transformation.base.utils.ModelManagement
import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException
import org.eclipse.papyrus.designer.transformation.core.Messages
import org.eclipse.papyrus.designer.transformation.core.m2minterfaces.IM2MTrafoCDP
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier
import org.eclipse.papyrus.designer.transformation.core.transformations.LazyCopier.CopyStatus
import org.eclipse.papyrus.designer.transformation.core.transformations.TransformationContext
import org.eclipse.papyrus.designer.transformation.library.transformations.AbstractBootLoaderGen
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil
import org.eclipse.uml2.uml.Class
import org.eclipse.uml2.uml.InstanceSpecification
import org.eclipse.uml2.uml.LiteralInteger
import org.eclipse.uml2.uml.Package
import org.eclipse.uml2.uml.Slot
import org.eclipse.uml2.uml.ValueSpecification

/**
 * Create a BootLoader for C++
 */
public class BootLoaderGenCpp extends AbstractBootLoaderGen implements IM2MTrafoCDP {

	public static final URI CPP_BOOTLOADER_URI = URI.createURI("pathmap://DML_TRAFOS_CPP/BootLoaderCpp.uml");

	public static final String CPP_BOOTLOADER_QNAME = "BootLoaderCpp::BootLoader"

	public static final String CPP_NODEINFO_QNAME = "BootLoaderCpp::NodeInfo"
	
	/**
	 * Create a new boot-loader in a specific package
	 * (which represents a node of the system).
	 *
	 * @param copier a lazy copier
	 * @param nodeIndex the index of the node
	 * @param numberOfNodes the number of nodes
	 * @throws TransformationException
	 */
	override def public void init(LazyCopier copier, int nodeIndex, int numberOfNodes)
			throws TransformationException {
		// Class composite = (Class) ut.getClassifier (mainInstance);
		// place in root (getModel()) to avoid the problem that the declaration of the bootLoader
		// instance is within a namespace (a static attribute on the model level would not solve the
		// problem as it must be accessed by function main).

		val root = TransformationContext.current.modelRoot;

		outputSizeof = false;
		m_copier = copier;
		
		val bootloader_ml = LibraryUtils.getContent(CPP_BOOTLOADER_URI, ModelManagement.resourceSet) as Package
		if (bootloader_ml == null) {
			throw new TransformationException(String.format(
					Messages.BootLoaderGen_CannotRetrieveTemplate, CPP_BOOTLOADER_URI));
		}
		val template = ElementUtils.getQualifiedElement(bootloader_ml, CPP_BOOTLOADER_QNAME) as Class
		if (template == null) {
			throw new TransformationException(String.format(
					Messages.BootLoaderGen_CannotRetrieveTemplate, CPP_BOOTLOADER_QNAME));
		}
		// copy bootloader (will be in its own top-level package)
		m_bootLoader = copier.getCopy(template)

		val cppInclude = StereotypeUtil.applyApp(m_bootLoader, Include);
		if (cppInclude == null) {
			throw new TransformationException(Messages.BootLoaderGen_CannotApplyCppInclude);
		}
		val existingBody = cppInclude.body
		var bodyStr = EMPTYSTR;

		if (outputSizeof) {
			bodyStr += '''
				#include <iostream>
				using namespace std
			'''
		}
		// bootloader is in root package, must be referenced via root.name package
		cppInclude.body = existingBody + bodyStr

		val nodeInfo = ElementUtils.getQualifiedElement(bootloader_ml, CPP_NODEINFO_QNAME) as Class
		val nodeIndexElem = nodeInfo.getAttribute("nodeIndex", null)
		val numberOfNodesElem = nodeInfo.getAttribute("numberOfNodes", null)
		// update values (assuming that the template already defines a LiteralInteger default value)
		(nodeIndexElem.defaultValue as LiteralInteger).setValue = nodeIndex + 0
		(numberOfNodesElem.defaultValue as LiteralInteger).setValue = numberOfNodes
		copier.getCopy(nodeInfo)
		
		m_initCode = EMPTYSTR;
		m_initCodeRun = EMPTYSTR;
		m_activation = new HashMap<Class, EList<String>>();
		m_initCodeCConnections = EMPTYSTR;
		m_initCodeCConfig = EMPTYSTR;

		if (outputSizeof) {
			// not supported by Java
			// m_initCode += '''cout << \"sizeof bootloader: \" << sizeof (bootloader) << endl;'''
		}
	}

	// create instance: nothing to do, since an attribute declaration (without pointer) creates the
	// instance already
	override def public String languageCreateInstance(InstanceSpecification is, Class implementation) '''
	'''

	override languageCreateConn(String varName) '''
		«varName».createConnections();
	'''
	
	
	override def public String 	languageRunStart(String varName, boolean useOO) '''
		«varName».get_start()->run();
	'''
	
	override def public String languageAssignRef(String accessName, String referenceVarName) '''
		«accessName» = &«referenceVarName»;
	'''
	
	override def public void instanceConfig(Stack<Slot> slotPath, InstanceSpecification instance) throws TransformationException {
		val slot = slotPath.peek();
		// String varName = getPath(slotPath, instance, false);
		val sf = slot.getDefiningFeature();
		if (sf == null) {
			throw new TransformationException(String.format("A slot for instance %s has no defining feature", instance.getName())); //$NON-NLS-1$
		}

		val varName = instance.getName() + "." + sf.getName(); //$NON-NLS-1$
		for (ValueSpecification value : slot.getValues()) {

			// only set value, if not null
			if (value.stringValue() != null) {
				m_initCodeCConfig += varName + " = " + value.stringValue() + EOL; //$NON-NLS-1$
			}
		}
	}

	def override void applyTrafo(M2MTrafo trafo, Package deploymentPlan) throws TransformationException {
		init(TransformationContext.current.copier, 0, 0);
	
		val slotPath = new Stack<Slot>();
		for (InstanceSpecification is : DepUtils.getTopLevelInstances(deploymentPlan))  {
			addInstance(is, slotPath);
		}
		addInit("C/Cpp");
	}

	def override public void languageInit() {
		// TODO Auto-generated method stub
		
	}

	def override public String languageActivation(Class[] activationKeys) '''
		// activation code
		«FOR implementation : activationKeys»
			«val varNameList = m_activation.get(implementation)»
			«FOR varName : varNameList»
				«varName».get_lc()->activate();
			«ENDFOR»
		«ENDFOR»
		return code;
	'''
		
	def override public String languageDeactivation(Class[] activationKeys) '''
		// deactivation code (reverse order)
		«FOR implementation : activationKeys.reverse»
			«val varNameList = m_activation.get(implementation)»
			«FOR varName : varNameList»
				«varName».get_lc()->deactivate();
			«ENDFOR»
		«ENDFOR»
		return code;
	'''

	def override public String languageDefaultExecCode() '''
		// no user entry point => enter endless loop
		for (;;) {
			usleep(100);
		}
	'''
}
