/*****************************************************************************
 * Copyright (c) 2016 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Mahmoud Hussein   mahmoud.hussein@cea.fr
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.java.jdt.project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Exclusion;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.ExcludedDependency;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenDependency;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.MavenProject;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.ParentArtifact;
import org.eclipse.papyrus.designer.languages.common.profile.Codegen.Property;

public class CustomizePOMFile {
	IProject genProject;
	MavenProject mavenProjectSettings;
	Model pomFileModel;
	boolean hasEnteryPoint;

	public CustomizePOMFile(IProject genProject, MavenProject mavenProjectSettings) {
		super();
		this.genProject = genProject;
		this.mavenProjectSettings = mavenProjectSettings;
		pomFileModel = new Model();
		hasEnteryPoint = false;
	}

	void execute() {
		// Generating the POM file based on the values specified into the
		// MavenProject element in the model
		locatePOMFile();
		addParent();
		addDependencies();
		addProperties();
		addBuildInfo();
		savePOMFile();
		try {
			genProject.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	void addProperties() {
		EList<Property> mavenProperties = mavenProjectSettings.getProperties();
		Properties properties = new Properties();
		for (Property property : mavenProperties) {
			properties.put(property.getName(), property.getValue());
			if ((property.getName()).equals("microservice.entrypoint")) {
				hasEnteryPoint = true;
			}
		}
		pomFileModel.setProperties(properties);
	}

	void savePOMFile() {
		String projectPath = ((genProject.getLocation()).toFile()).getAbsolutePath();
		try {
			File file = new File(projectPath + "/pom.xml");
			FileWriter pomFileWriter = new FileWriter(file);
			new MavenXpp3Writer().write(pomFileWriter, pomFileModel);
			pomFileWriter.close();
		} catch (IOException e) {
			// Activator.log.error(e);
			e.printStackTrace();
		}
	}

	void locatePOMFile() {
		String projectPath = ((genProject.getLocation()).toFile()).getAbsolutePath();
		try {
			File file = new File(projectPath + "/pom.xml");
			FileReader pomFileReader = new FileReader(file);
			MavenXpp3Reader pomreader = new MavenXpp3Reader();
			pomFileModel = pomreader.read(pomFileReader);
		} catch (IOException e) {
			// Activator.log.error(e);
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
	}

	void addBuildInfo() {
		Build build = new Build();
		ArrayList<Plugin> plugins = new ArrayList<Plugin>();

		Plugin pluginJar = new Plugin();
		pluginJar.setGroupId("org.apache.maven.plugins");
		pluginJar.setArtifactId("maven-jar-plugin");
		pluginJar.setVersion("2.1");
		pluginJar.getConfiguration();
		plugins.add(pluginJar);
		if (hasEnteryPoint) {
			addEntryPoint(pluginJar);
		}

		Plugin pluginRelease = new Plugin();
		pluginRelease.setGroupId("org.apache.maven.plugins");
		pluginRelease.setArtifactId("maven-release-plugin");
		pluginRelease.setVersion("2.5.3");
		pluginRelease.getConfiguration();
		plugins.add(pluginRelease);
		
		build.setFinalName(genProject.getName());
		build.setPlugins(plugins);
		pomFileModel.setBuild(build);
	}

	void addEntryPoint(Plugin pluginJar) {
		Xpp3Dom configDom = new Xpp3Dom("configuration");
		Xpp3Dom archiveDom = new Xpp3Dom("archive");
		Xpp3Dom manifestDom = new Xpp3Dom("manifestEntries");
		
		Xpp3Dom entryDom = new Xpp3Dom("Entry-Point");
		entryDom.setValue("${microservice.entrypoint}");
		Xpp3Dom jarKindDom = new Xpp3Dom("Jar-Kind");
		jarKindDom.setValue("vortex-microservice");
		Xpp3Dom impTitleDom = new Xpp3Dom("Implementation-Title");
		impTitleDom.setValue("${project.artifactId}");
		Xpp3Dom specTitleDom = new Xpp3Dom("Specification-Title");
		specTitleDom.setValue("${project.artifactId}");
		
		manifestDom.addChild(entryDom);
		manifestDom.addChild(jarKindDom);
		manifestDom.addChild(impTitleDom);
		manifestDom.addChild(specTitleDom);
		
		archiveDom.addChild(manifestDom);
		configDom.addChild(archiveDom);
		pluginJar.setConfiguration(configDom);
	}

	void addDependencies() {
		ArrayList<Dependency> dependencies = new ArrayList<Dependency>();
		EList<MavenDependency> mavenDependencies = mavenProjectSettings.getDependencies();
		for (MavenDependency mavenDependency : mavenDependencies) {
			Dependency dependency = new Dependency();
			dependency.setGroupId(mavenDependency.getGroupId());
			dependency.setArtifactId(mavenDependency.getArtifactId());
			dependency.setVersion(mavenDependency.getVersion());
			// Adding exclusions if any
			ArrayList<Exclusion> exclusions = new ArrayList<Exclusion>();
			EList<ExcludedDependency> excludedDependencies = mavenDependency.getExclusions();
			if (excludedDependencies != null) {
				for (ExcludedDependency exDependency : excludedDependencies) {
					Exclusion exclusion = new Exclusion();
					exclusion.setArtifactId(exDependency.getArtifactId());
					exclusion.setGroupId(exDependency.getGroupId());
					exclusions.add(exclusion);
				}
				dependency.setExclusions(exclusions);
			}
			dependencies.add(dependency);
		}
		pomFileModel.setDependencies(dependencies);
	}

	void addParent() {
		ParentArtifact parentArtifact = mavenProjectSettings.getParent();
		if (parentArtifact != null) {
			Parent parent = new Parent();
			parent.setGroupId(parentArtifact.getGroupId());
			parent.setArtifactId(parentArtifact.getArtifactId());
			parent.setVersion(parentArtifact.getVersion());
			pomFileModel.setParent(parent);
		}
	}
}
