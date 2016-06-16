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

package org.eclipse.papyrus.designer.languages.idl.codegen;

import org.eclipse.uml2.uml.Port
import org.eclipse.uml2.uml.Class

class ComponentCCD {

	public static def generateCCD(Class x) '''
		<?xml version="1.0" encoding="UTF-8"?>
		
		<!-- Generated CCD for component «x.name» -->
		
		<ComponentInterfaceDescription>
			<label>«x.name»</label>
			<UUID>«x.name»</UUID>
			<specificType>«x.name»</specificType>
			<supportedType></supportedType>
			<idlFile>«x.name».idl3</idlFile>
			<configProperty></configProperty>

		«FOR port : x.ownedPorts»
			«port.generatePort»
		«ENDFOR»
		</ComponentInterfaceDescription>
	'''

	/**
	 * Generate a CCD definition for a port. Assumes that port either provides or requires
	 * a single interface (strong restriction)
 	 */
	public static def generatePort(Port port) '''
		<port>
		«FOR forVar : port.provideds»
			<name>«port.name»</name>
			<specificType>«forVar.name»</specificType>
			<supportedType></supportedType>
			<provider>true</provider>
			<exclusiveProvider>false</exclusiveProvider>
			<exclusiveUser>false</exclusiveUser>
			<optional>true</optional>
			<kind>Facet</kind>
		«ENDFOR»
		«FOR forVar : port.requireds»
			<name>«port.name»</name>
			<specificType>«forVar.name»</specificType>
			<supportedType></supportedType>
			<provider>false</provider>
			<exclusiveProvider>false</exclusiveProvider>
			<exclusiveUser>false</exclusiveUser>
			<optional>true</optional>
			<kind>Receptacle</kind>
		«ENDFOR»
	   	</port>
	'''
}
