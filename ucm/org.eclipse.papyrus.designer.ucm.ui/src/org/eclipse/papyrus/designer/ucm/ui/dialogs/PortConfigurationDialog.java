/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and Thales
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

package org.eclipse.papyrus.designer.ucm.ui.dialogs;


import java.util.Arrays;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.designer.ucm.core.provider.UCMContentProvider;
import org.eclipse.papyrus.designer.ucm.core.provider.UCMContentProviderWithUndef;
import org.eclipse.papyrus.designer.ucm.core.provider.UCMLabelProvider;
import org.eclipse.papyrus.designer.ucm.core.utils.BindingUtils;
import org.eclipse.papyrus.designer.ucm.core.utils.PortUtils;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_components.PortTypeSpec;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.IPortType;
import org.eclipse.papyrus.designer.ucm.profile.UCMProfile.ucm_interactions.PortType;
import org.eclipse.papyrus.infra.widgets.editors.TreeSelectorDialog;
import org.eclipse.papyrus.infra.widgets.providers.EncapsulatedContentProvider;
import org.eclipse.papyrus.uml.tools.utils.PackageUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.AbstractElementListSelectionDialog;
import org.eclipse.ui.dialogs.FilteredList;
import org.eclipse.ui.dialogs.SelectionStatusDialog;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Manage a port type and the associated bindings.
 */
public class PortConfigurationDialog extends AbstractElementListSelectionDialog {

	public static final String SELECT_ACTUAL = "Select Actual"; //$NON-NLS-1$

	protected Port port;

	protected Type currentType;

	protected Text m_description;

	protected FilteredList fUpper;

	protected Group fLowerGroup;

	public PortConfigurationDialog(Shell parent, Port port) {
		super(parent, null /* new QNameLabelProvider() */);
		this.port = port;
	}

	@Override
	public Control createDialogArea(Composite parent) {
		Composite contents = (Composite) super.createDialogArea(parent);
		createMessageArea(contents);
		setTitle("UCM Port Configuration");
		UCMContentProvider contentProvider = new UCMContentProviderWithUndef(PackageUtil.getRootPackage(port), UMLPackage.eINSTANCE.getClass_(), PortType.class);

		Group fUpperGroup = new Group(contents, SWT.NONE);
		fUpperGroup.setLayout(new GridLayout());
		// fUpperGroup.setLayoutData(gridData);
		fUpperGroup.setText(" Port type "); //$NON-NLS-1$
		createFilterText(fUpperGroup);
		fUpper = createFilteredList(fUpperGroup);
		fUpper.setLabelProvider(new QNameLabelProvider());
		fUpper.setElements(contentProvider.getElements());

		PortTypeSpec portTypeSpec = getPortTypeSpec();
		if (portTypeSpec != null && portTypeSpec.getType() != null) {
			currentType = portTypeSpec.getType().getBase_Class();
		} else {
			currentType = UCMContentProviderWithUndef.getUndef();
		}
		fUpper.setSelection(new Object[] { currentType });

		fLowerGroup = new Group(contents, SWT.NONE);
		// three columns, size not equal
		fLowerGroup.setLayout(new GridLayout(3, false));
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.heightHint = 150;
		fLowerGroup.setLayoutData(gridData);
		fLowerGroup.setText(" Bindings "); //$NON-NLS-1$
		// createLabel(contents, lowerLabel);
		updateLower();

		return contents;
	}

	protected void updateLower() {
		for (Control control : fLowerGroup.getChildren()) {
			control.dispose();
		}
		final EList<Type> abstractTypes = getAbstractTypes();
		if (abstractTypes != null) {
			for (final Type formal : abstractTypes) {
				createLabel(fLowerGroup, String.format("Binding for %s:", formal.getName()));

				Type actual = BindingUtils.getActual(getPortTypeSpec(), formal);
				final Label actualLabel = createLabel(fLowerGroup, actual != null ? actual.getName() : "<undefined>");

				Button b = new Button(fLowerGroup, SWT.NONE);
				b.setText(SELECT_ACTUAL);
				b.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent arg0) {
						UCMContentProvider uc = new UCMContentProviderWithUndef(port.getModel(), formal.eClass());
						TreeSelectorDialog dialog = new TreeSelectorDialog(Display.getDefault().getActiveShell());
						dialog.setLabelProvider(new UCMLabelProvider());
						dialog.setContentProvider(new EncapsulatedContentProvider(uc));
						dialog.setTitle(SELECT_ACTUAL);
						int code = dialog.open();
						if (code == Window.OK) {
							// TreeSelectorDialog returns an array.
							Type result = (Type) dialog.getResult()[0];
							if (result != UCMContentProviderWithUndef.getUndef()) {
								BindingUtils.setActual(getPortTypeSpec(), formal, result);
							} else {
								BindingUtils.resetActual(getPortTypeSpec(), formal);
							}
							actualLabel.setText(result.getName());
						}
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
					}
				});
			}
		}
		fLowerGroup.pack();
	}

	/**
	 * @return portTypeSpec for current port, or null, if it does not exist
	 */
	protected PortTypeSpec getPortTypeSpec() {
		Type typeSpec = port.getType();
		if (typeSpec != null) {
			return UMLUtil.getStereotypeApplication(typeSpec, PortTypeSpec.class);
		}
		return null;
	}

	/**
	 * Update the enablement of the OK button based on whether or not there
	 * is a selection.
	 */
	@Override
	protected void updateOkState() {
		super.updateOkState();
	}


	protected Label createLabel(Composite parent, String name) {
		if (name == null) {
			return null;
		}
		Label label = new Label(parent, SWT.NONE);
		label.setText(name);
		label.setFont(parent.getFont());
		return label;
	}

	/**
	 * Creates a label if name was not <code>null</code>.
	 *
	 * @param parent
	 *            the parent composite.
	 * @param name
	 *            the name of the label.
	 * @return returns a label if a name was given, <code>null</code> otherwise.
	 */


	/**
	 * @see SelectionStatusDialog#computeResult()
	 */
	@Override
	protected void computeResult() {
		Object[] result = new Object[] { getSelectedElement() };
		setResult(Arrays.asList(result));
	}

	/**
	 * @see AbstractElementListSelectionDialog#handleDefaultSelected()
	 */
	@Override
	protected void handleDefaultSelected() {
		if (validateCurrentSelection() && (getSelectedElement() != null)) {
			buttonPressed(IDialogConstants.OK_ID);
		}
	}

	/**
	 * @see AbstractElementListSelectionDialog#handleSelectionChanged()
	 */
	@Override
	protected void handleSelectionChanged() {
		if (fUpper.getSelection().length > 0 && fUpper.getSelection()[0] != currentType) {
			currentType = (Type) fUpper.getSelection()[0];
			if (currentType != null) {
				if (currentType != UCMContentProviderWithUndef.getUndef()) {
					PortTypeSpec portTypeSpec = getPortTypeSpec();
					if (portTypeSpec == null) {
						// portTypeSpec does not exist, create
						portTypeSpec = PortUtils.createPortTypeSpec(port);
						port.setType(portTypeSpec.getBase_Class());
					}
					IPortType portType = UMLUtil.getStereotypeApplication(currentType, IPortType.class);
					if (portType != portTypeSpec.getType()) {
						portTypeSpec.setType(portType);
						// port type has changed => reset existing bindings 
						portTypeSpec.getBase_Class().getOwnedAttributes().clear();
					}
				} else {
					PortTypeSpec portTypeSpec = getPortTypeSpec();
					if (portTypeSpec != null) {
						portTypeSpec.getBase_Class().destroy();
					}
					port.setType(null);
				}
				updateLower();
			}
		}
		super.handleSelectionChanged();
	}

	/**
	 * Returns the selected element from the upper or lower pane.
	 *
	 * @return Object
	 */
	protected Object getSelectedElement() {
		return fFilteredList.getSelection();
	}

	protected EList<Type> getAbstractTypes() {
		if (getPortTypeSpec() != null) {
			return BindingUtils.getAbstractTypes(getPortTypeSpec().getType());
		}
		return null;
	}
}
