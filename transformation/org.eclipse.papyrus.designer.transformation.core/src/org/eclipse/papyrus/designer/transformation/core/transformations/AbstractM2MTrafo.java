package org.eclipse.papyrus.designer.transformation.core.transformations;

import org.eclipse.papyrus.designer.transformation.base.utils.TransformationException;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Package;

public abstract class AbstractM2MTrafo {

	/**
	 * apply a model-2-model transformation
	 *
	 * @param smContainerRule
	 *            An container rule
	 * @param smClass
	 *            a class in the source model
	 * @param tmClass
	 *            a class in the target model (i.e. the result of the M2M transformation application)
	 * @throws TransformationException
	 */
	public abstract void applyM2M(M2MTrafo trafo, Classifier smClass)
			throws TransformationException;

	/**
	 * initialize the transformation. Used by the customTransformation
	 *
	 * @param copier
	 * @param tmCDP
	 */
	public void initialize(LazyCopier copier, Package tmCDP) {
		this.copier = copier;
		this.tmCDP = tmCDP;
	}

	/**
	 * Copy class from source to target model
	 */
	protected LazyCopier copier;


	/**
	 * Is called after a set of rule applications.
	 * Does nothing by default
	 */
	@Override
	public void finalize() {
	}

	/**
	 * The created container implementation (prefixed with tm, since part of
	 * target model)
	 */
	protected Class tmClass;

	/**
	 * deployment plan within source model
	 */
	protected Package smCDP;

	/**
	 * deployment plan within target model
	 */
	protected Package tmCDP;
}
