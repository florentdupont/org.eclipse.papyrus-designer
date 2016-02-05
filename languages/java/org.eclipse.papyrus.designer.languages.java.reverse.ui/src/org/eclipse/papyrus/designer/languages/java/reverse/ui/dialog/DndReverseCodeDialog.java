package org.eclipse.papyrus.designer.languages.java.reverse.ui.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.Activator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


public class DndReverseCodeDialog extends ReverseCodeDialog {

	private static final String DISPLAY_MODEL_UID = ":DISPLAY_MODEL"; //$NON-NLS-1$
	private static final String DISPLAY_PACKAGES_UID = ":DISPLAY_PACKAGES"; //$NON-NLS-1$
	private static final String DISPLAY_CU_UID = ":DISPLAY_CU"; //$NON-NLS-1$
	private Button displayModelCheckbox;
	private Button displayPackagesCheckbox;
	private Button displayCUCheckbox;

	private boolean displayModel;
	private boolean displayPackages;
	private boolean displayCU;

	public DndReverseCodeDialog(Shell parentShell, String modelUid, String initialValue, List<String> searchPathsInitialValues) {
		super(parentShell, modelUid, initialValue, searchPathsInitialValues);

	}

	@Override
	protected Control createDialogArea(Composite parent) {
		IDialogSettings settings = Activator.getDefault().getDialogSettings();
		Composite composite = (Composite) super.createDialogArea(parent);

		// Create button to display or not the model
		displayModelCheckbox = new Button(composite, SWT.CHECK);
		displayModelCheckbox.setText("Display reversed Model into diagram");
		displayModelCheckbox.setSelection(settings.getBoolean(MODEL_UID + DISPLAY_MODEL_UID));

		// Create button to display or not package
		displayPackagesCheckbox = new Button(composite, SWT.CHECK);
		displayPackagesCheckbox.setText("Display reversed UML::packages into diagram");
		displayPackagesCheckbox.setSelection(settings.getBoolean(MODEL_UID + DISPLAY_PACKAGES_UID));

		// Create button to display or not CompilationUnit
		displayCUCheckbox = new Button(composite, SWT.CHECK);
		displayCUCheckbox.setText("Display UML::classes, UML::interfaces, UML::enumerations into diagram");
		displayCUCheckbox.setSelection(settings.getBoolean(MODEL_UID + DISPLAY_CU_UID));

		return composite;
	}

	@Override
	protected void okPressed() {
		// add selection into fields to prevent dialog disposing
		displayModel = displayModelCheckbox.getSelection();
		displayPackages = displayPackagesCheckbox.getSelection();
		displayCU = displayCUCheckbox.getSelection();

		// save selection into settings
		IDialogSettings settings = Activator.getDefault().getDialogSettings();
		settings.put(MODEL_UID + DISPLAY_MODEL_UID, displayModel);
		settings.put(MODEL_UID + DISPLAY_PACKAGES_UID, displayPackages);
		settings.put(MODEL_UID + DISPLAY_CU_UID, displayCU);
		super.okPressed();
	}

	/**
	 *
	 * @return true if displayModelCheckbox is checked
	 */
	public boolean displayModel() {
		return displayModel;
	}

	/**
	 *
	 * @return true if displayPackagesCheckbox is checked
	 */
	public boolean displayPackages() {
		return displayPackages;
	}

	/**
	 *
	 * @return true if displayCUCheckbox is checked
	 */
	public boolean displayCU() {
		return displayCU;
	}
}
