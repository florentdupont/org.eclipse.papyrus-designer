package org.eclipse.papyrus.designer.transformation.core.transformations;

import org.eclipse.papyrus.designer.transformation.profile.Transformation.M2MTrafo;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;

public class ExecuteTransformation {
	public ExecuteTransformation(AbstractM2MTrafo trafo) {
		this.trafo = trafo;
	}
	
	public void executeTransformation(Package sourceModel) {
		for (PackageableElement pe : sourceModel.getPackagedElements()) {
			if (pe instanceof Classifier) {
				v;
			}
		}
	}
	
	AbstractM2MTrafo trafo;
	
	M2MTrafo trafoType;
}
