/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.idl.codegen.opensplice.transformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

// import org.apache.maven.plugin.MojoExecutionException;
// import org.apache.maven.plugin.logging.Log;
/**
 * Generic processWrapper. Used by DDScompileIDL
 */
public class ProcessWrapper {

	/**
	 * Invoke the IDL compiler for the given IDL File
	 * 
	 * @param arguments
	 *        command line to be passed to the process builder
	 */
	protected void process(List<String> arguments) // throws MojoExecutionException
	{
		BufferedReader results = null, errors = null;
		try {
			ProcessBuilder pb = new ProcessBuilder(arguments);
			Process p = null;
			try {
				p = pb.start();
				results = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String s;
				boolean error = false;
				while((s = results.readLine()) != null) {
					// output may indicate an error, but this is not true in general
					System.err.println("result:" + s);
					error = true;
				}
				errors = new BufferedReader(new InputStreamReader(p.getErrorStream()));
				while((s = errors.readLine()) != null) {
					System.err.println("error:" + s);
					error = true;
				}
				try {
					p.waitFor();
				} catch (InterruptedException exp) {
					//do nothing
				}
				if(error) {
					throw new RuntimeException("error during execution of command");
				}
			} catch (IOException exp) {
				exp.printStackTrace();
				//Activator.log.println()
				//log.error(exp);
				// throw new MojoExecutionException("");
			}
		} finally {
			try {
				if(results != null)
					results.close();
				if(errors != null)
					errors.close();
			} catch (IOException exp) {
				//log.error(exp);
				//throw new MojoExecutionException("");
			}
		}
	}
}
