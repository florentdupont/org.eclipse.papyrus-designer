/**
 *
 */
package org.eclipse.papyrus.java.reverse.ui;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.ui.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.infra.ui.util.EditorUtils;
import org.eclipse.papyrus.infra.ui.util.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.java.reverse.ui.dialog.ReverseCodeDialog;
import org.eclipse.papyrus.uml.tools.model.UmlUtils;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.uml2.uml.Package;


/**
 * @author dumoulin
 *
 */
public class ReverseCodeHandler extends AbstractHandler implements IHandler {

	private static String DefaultGenerationModeleName = "generated";

	/**
	 * Method called when button is pressed.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Try to find uml resource
		final Resource umlResource;
		try {
			umlResource = getUmlResource();
		} catch (NullPointerException e) {
			// No uml resource available. User must open a model. We open an error dialog with an explicit message to advice user.
			Shell shell = HandlerUtil.getActiveShell(event);
			Status errorStatus = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.ReverseCodeHandler_NoModelError_Title);
			ErrorDialog.openError(shell, "", Messages.ReverseCodeHandler_NoModelError_Message, errorStatus);

			// Stop the reverse execution.
			return null;
		}
		;

		String modelUid = getModelUid(umlResource);
		System.out.println("Model uid :" + modelUid);

		// Get reverse parameters from a dialog
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getShell();
		// ReverseCodeDialog dialog = new ReverseCodeDialog(shell, DefaultGenerationPackageName, Arrays.asList("generated") );
		final ReverseCodeDialog dialog = getDialog(shell, modelUid);

		int res = dialog.open();
		// System.out.println("dialog result =" + res);
		if (res == Window.CANCEL) {
			System.out.println("Canceled by user.");
			return null;
		}

		// Execute the reverse with provided parameters
		TransactionalEditingDomain editingDomain;
		try {
			editingDomain = getEditingDomain();
		} catch (ServiceException e) {
			// Can't get editing domain
			e.printStackTrace();
			throw new ExecutionException(e.getMessage());
		}

		RecordingCommand command = new RecordingCommand(editingDomain, "Reverse Java Code") {

			@Override
			protected void doExecute() {
				ReverseCodeHandler.this.doExecute(dialog);
			}

		};

		editingDomain.getCommandStack().execute(command);


		return null;
	}

	/**
	 * Find the modelUid name contains into umlResource taken in parameter
	 *
	 * @param umlResource
	 * @return the modelUid name
	 */
	private String getModelUid(Resource umlResource) {
		// Try to compute a uid identifying the model. Used to store user settings.
		String modelUid = umlResource.getURI().toPlatformString(true);
		if (modelUid == null) {
			System.err.println("Can't compute relatif model uid. Use absolute one");
			modelUid = umlResource.getURI().path();
		}
		return modelUid;
	}

	/**
	 * Command ran in a RecordingCommand, after the dialog
	 * Run the @link{JavaCodeReverse.executeCodeReverse}
	 * Shall be override to change command behavior
	 */
	protected void doExecute(ReverseCodeDialog dialog) {
		// Create searchpaths. Add the rootmodelname as prefix.
		final List<String> searchPaths = Arrays.asList(dialog.getSearchPath());
		Resource umlResource = getUmlResource();
		String packageName = getPackageName(dialog);
		JavaCodeReverse reverse = new JavaCodeReverse(getRootPackage(umlResource), packageName, searchPaths);
		reverse.executeCodeReverse(umlResource, packageName, searchPaths);
	}

	/**
	 * The dialog used for user.
	 *
	 * @param shell
	 * @param modelUid
	 * @return the dialog to show to user
	 */
	protected ReverseCodeDialog getDialog(Shell shell, String modelUid) {
		return new ReverseCodeDialog(shell, modelUid, null, null);
	}

	/**
	 * Find the name of the model provided by the dialog
	 *
	 * @param dialog
	 *            opened dialog to user
	 * @return the name of the model. If the user has changed this name, return the name provided by the user; return the default model name
	 *         otherwise.
	 */
	protected String getPackageName(ReverseCodeDialog dialog) {
		String generationPackageName = dialog.getValue();
		if (generationPackageName == null || generationPackageName.length() == 0) {
			generationPackageName = DefaultGenerationModeleName;
		}
		return generationPackageName;
	}

	/**
	 * Get the uml resource used by the model.
	 *
	 * @return the Uml Resource
	 */
	protected Resource getUmlResource() {
		Resource umlResource = UmlUtils.getUmlModel().getResource();
		return umlResource;
	}

	/**
	 * Get the name of the root model.
	 *
	 * @return
	 */
	protected Package getRootPackage(Resource umlResource) {
		Package rootPackage = (Package) umlResource.getContents().get(0);
		return rootPackage;
	}

	/**
	 * Get the current MultiDiagramEditor.
	 *
	 * @return
	 */
	protected IMultiDiagramEditor getMultiDiagramEditor() {
		return EditorUtils.getMultiDiagramEditor();
	}

	/**
	 * Get the main editing doamin.
	 *
	 * @return
	 * @throws ServiceException
	 */
	protected TransactionalEditingDomain getEditingDomain() throws ServiceException {
		return ServiceUtilsForActionHandlers.getInstance().getTransactionalEditingDomain();
	}

}
