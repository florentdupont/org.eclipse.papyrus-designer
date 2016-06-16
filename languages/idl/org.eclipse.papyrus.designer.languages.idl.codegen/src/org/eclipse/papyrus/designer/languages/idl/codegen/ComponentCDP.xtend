/*******************************************************************************
 * Copyright (c) 2016 CEA LIST
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Ansgar Radermacher - Initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.idl.codegen

import org.eclipse.uml2.uml.InstanceSpecification
import org.eclipse.uml2.uml.Port
import org.eclipse.uml2.uml.Property
import org.eclipse.emf.common.util.EList
import org.eclipse.uml2.uml.Classifier
import org.eclipse.uml2.uml.Node

class ComponentCDP {

	public static def generateCDP(InstanceSpecification instance) '''
		<?xml version="1.0" encoding="UTF-8"?>
		<Deployment xmlns="http://www.omg.org/Deployment"
		    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		    xsi:schemaLocation="http://www.omg.org/Deployment">
		
		<!-- Generated file with eC3M GenTools, CDP for instance specification «instance.name» -->
		  
		<!--        ----------------- 
		                artifacts
		            ----------------- -->
		
		«FOR subInstance : instance.getContainedInstances»
			<!-- Artifact for «subInstance.classifier.name» -->
			<artifact id="artifact_«subInstance.classifier.name»">
				<name>«subInstance.getClassifier().name»</name>
				<location>./libdummy.so</location>
				<source></source>
				<execParameter>
					<name>home factory</name>
					<value>
						<type><kind>tk_string</kind></type>
						<value><string>create_home_for_«subInstance.classifier.name»</string></value>
					</value>
				</execParameter>
			</artifact>
		«ENDFOR»
		
		<!--        -----------------
		             implementations
		            ----------------- -->
		  
		«FOR subInstance : instance.getContainedInstances»
			<implementation id="«subInstance.classifier.name»">
				<name>«subInstance.getClassifier.name»</name>
				// assumption on ccd location
				<source>«subInstance.getClassifier.name».ccd</source>
				<artifact ref="artifact_«subInstance.getClassifier.name»"/>
			</implementation>
		«ENDFOR»
		
		<!--        -----------------
		                instances
		            ----------------- -->
		  
		«FOR subInstance : instance.getContainedInstances»
			<!-- component «subInstance.name» -->
			<instance ID="«subInstance.name»">
				<name>«subInstance.name»</name>
				<node>«subInstance.node.name»</node>
				<implementation ref="«subInstance.classifier.name»"/>
			</instance>
		«ENDFOR»
		
		<!--        -----------------
		               connections
		            ----------------- -->
		    
		// form of a loop, but in fact, there should be only one type         
		«FOR cl : instance.classifiers»
			«FOR connector : (cl as org.eclipse.uml2.uml.Class).ownedConnectors»
				<!-- connection «connector.name» -->
				<connection id="«connector.name»">
					<name>«connector.name»</name>
					<implementation ref="unknown"/>
				«FOR end : connector.ends»
					<internalEndpoint>
						<instance ref="«instance.getInstanceForPart(end.partWithPort).name»">
						<portName>«end.role.name»</portName>
						«IF (end.role as Port).provideds.size > 0»
							<provider>true</provider>
							<kind>Facet</kind>
						«ELSEIF (end.role as Port).requireds.size > 0»
							<provider>false</provider>
							<kind>SimplexReceptacle</kind>
						«ENDIF»
					</internalEndpoint>
				«ENDFOR»
				</connection>
			«ENDFOR»		
		«ENDFOR»
	'''
	
	/**
	 * TODO: dummy definitions, real implementations are in component part of designer, need to have a strategy for utils class
	 */
	public static def Classifier getClassifier(InstanceSpecification is) {
		return is.classifiers.get(0)
	}
	
	public static def EList<InstanceSpecification> getContainedInstances(InstanceSpecification is) {
		return null;
	}
	
	public static def InstanceSpecification getInstanceForPart(InstanceSpecification compositeIS, Property part) {
		return null;
	}
	
	public static def Node getNode(InstanceSpecification is) {
		return null;
	}
}
