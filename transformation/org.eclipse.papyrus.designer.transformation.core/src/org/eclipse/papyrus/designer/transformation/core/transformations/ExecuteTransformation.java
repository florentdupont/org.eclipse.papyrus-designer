package org.eclipse.papyrus.designer.transformation.core.transformations;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.papyrus.designer.transformation.core.extensions.IEmptyM2MTrafo;
import org.eclipse.papyrus.designer.transformation.core.extensions.IM2MTrafo;
import org.eclipse.papyrus.designer.transformation.core.extensions.M2MTrafoExt;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.ApplyTransformation;
import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.util.UMLUtil;

public class ExecuteTransformation {
	public ExecuteTransformation(LazyCopier copier) {
		this.copier = copier;
	}
	
	public void executeTransformation(Package pkg) {
		EList<PackageableElement> elements = new BasicEList<PackageableElement>();
		elements.addAll(pkg.getPackagedElements());
		for (PackageableElement pe : elements) {
			if (pe instanceof Classifier) {
				ApplyTransformation applyTrafo =
						UMLUtil.getStereotypeApplication(pe, ApplyTransformation.class);
				if (applyTrafo != null) {
					// execute the set of transformations applied to the element
					for (M2MTrafo stTrafo : applyTrafo.getTrafo()) {
						IEmptyM2MTrafo trafo = M2MTrafoExt.getM2MTrafo(stTrafo);
						if (trafo instanceof IM2MTrafo) {
							((IM2MTrafo) trafo).transformElement(copier, stTrafo, pe);
						}
					}
				}
			}
			else if (pe instanceof Package) {
				// recurse
				executeTransformation((Package) pe);
			}
		}
	}
	
	protected LazyCopier copier;
}
