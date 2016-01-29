/*****************************************************************************
 * Copyright (c) 2012, 2016 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) Vincent.Lorenzo@cea.fr - Initial API and implementation
 *  Ansgar Radermacher (CEA LIST) ansgar.radermacher@cea.fr
 *  Christian W. Damus - bug 485220
 *
 *****************************************************************************/
package org.eclipse.papyrus.texteditor.cdt.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.papyrus.codegen.extensionpoints.ILangCodegen;
import org.eclipse.papyrus.codegen.extensionpoints.LanguageCodegen;
import org.eclipse.papyrus.commands.CheckedOperationHistory;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.ISashWindowsContentProvider;
import org.eclipse.papyrus.infra.core.sasheditor.di.contentprovider.DiSashModelManager;
import org.eclipse.papyrus.infra.core.sasheditor.editor.ISashWindowsContainer;
import org.eclipse.papyrus.infra.core.sashwindows.di.PageRef;
import org.eclipse.papyrus.infra.core.sashwindows.di.SashPanel;
import org.eclipse.papyrus.infra.core.sashwindows.di.TabFolder;
import org.eclipse.papyrus.infra.core.sashwindows.di.service.IPageManager;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.ui.util.ServiceUtilsForHandlers;
import org.eclipse.papyrus.texteditor.cdt.Activator;
import org.eclipse.papyrus.texteditor.cdt.TextEditorConstants;
import org.eclipse.papyrus.texteditor.cdt.editor.PapyrusCDTEditor;
import org.eclipse.papyrus.texteditor.cdt.modelresource.TextEditorModelSharedResource;
import org.eclipse.papyrus.texteditor.model.texteditormodel.TextEditorModel;
import org.eclipse.papyrus.texteditor.model.texteditormodel.TextEditorModelFactory;
import org.eclipse.papyrus.uml.diagram.common.handlers.CmdHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.UMLPackage;


/**
 * The handler creates a new CDT editor
 */
public class PapyrusCDTEditorHandler extends CmdHandler {


	public PapyrusCDTEditorHandler() {
	}

	/**
	 *
	 * @see org.eclipse.infra.core.commands.AbstractHandler#isEnabled()
	 *
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		updateSelectedEObject();
		// Filter Classes (including Behaviors, since Behavior inherits from Class), Operation and Transition
		if (selectedEObject instanceof Class ||
				selectedEObject instanceof Operation ||
				selectedEObject instanceof Transition) {
			URI uri = selectedEObject.eResource().getURI();

			// URIConverter uriConverter = resource.getResourceSet().getURIConverter();
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			if (uri.segmentCount() < 2) {
				return false;
			}
			IProject modelProject = root.getProject(uri.segment(1));
			return modelProject.exists();
		}
		return false;
	}

	/**
	 * @see org.eclipse.infra.core.commands.IHandler#execute(org.eclipse.infra.core.commands.ExecutionEvent)
	 *
	 * @param event
	 * @return
	 * @throws ExecutionException
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		try {
			final ServicesRegistry serviceRegistry = ServiceUtilsForHandlers.getInstance().getServiceRegistry(event);
			TransactionalEditingDomain domain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);

			// Create the transactional command
			AbstractEMFOperation command = new AbstractEMFOperation(domain, "Create CDT editor") {

				@Override
				protected IStatus doExecute(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
					try {
						PapyrusCDTEditorHandler.this.doExecute(serviceRegistry);
					} catch (ServiceException e) {
						Activator.log.error(e);
						return Status.CANCEL_STATUS;
					} catch (NotFoundException e) {
						Activator.log.error(e);
						return Status.CANCEL_STATUS;
					}
					return Status.OK_STATUS;
				}
			};

			// Execute the command
			CheckedOperationHistory.getInstance().execute(command, new NullProgressMonitor(), null);
		} catch (ExecutionException e) {
			Activator.log.error("Can't create a CDT editor", e);
		} catch (ServiceException e) {
			Activator.log.error("Service exception during creation of CDT editor", e);
		}
		return null;
	}


	/**
	 * Do the execution of the command.
	 *
	 * @param serviceRegistry
	 * @throws ServiceException
	 * @throws NotFoundException
	 */
	public void doExecute(final ServicesRegistry serviceRegistry) throws ServiceException, NotFoundException {
		// Get the page manager allowing to add/open an editor.
		final IPageManager pageMngr = ServiceUtils.getInstance().getService(IPageManager.class, serviceRegistry);

		Classifier classifierToEdit = getClassifierToEdit();
		TextEditorModel editorModel = getEditorModel(serviceRegistry, classifierToEdit);
		if (editorModel == null) {
			// no editor exist for the given file => create
			editorModel = createEditorModel(serviceRegistry, classifierToEdit);
			if (editorModel == null) {
				return;
			}
		}
		ILangCodegen codegen = LanguageCodegen.getGenerator(TextEditorConstants.CPP, editorModel.getGeneratorID());

		if (codegen.getTargetProject(classifierToEdit, true) == null) {
			return;
		}

		if (selectedEObject instanceof Transition) {
			Transition transition = (Transition) selectedEObject;
			if (transition.getEffect() == null) {
				Behavior effect = transition.createEffect("effectOf" + transition.getName(), UMLPackage.eINSTANCE.getOpaqueBehavior()); //$NON-NLS-1$
			}
		}
		// add the new editor model to the sash.
		editorModel.setSelectedObject(selectedEObject);

		final TextEditorModel editorModelFinal = editorModel;
		// open asynchronously to prevent handler cycles, see bug 434484
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				if (pageMngr.isOpen(editorModelFinal)) {
					// select existing editor
					pageMngr.selectPage(editorModelFinal);
				} else {
					pageMngr.openPage(editorModelFinal);
				}
				try {
					// move page to the RIGHT
					DiSashModelManager modelMngr = ServiceUtils.getInstance().getService(DiSashModelManager.class, serviceRegistry);
					ISashWindowsContentProvider sashContentProvider = modelMngr.getISashWindowsContentProvider();
					Object rootModel = sashContentProvider.getRootModel();

					if (rootModel instanceof TabFolder) {
						// root = tabFolder, i.e. there is a single folder
						ISashWindowsContainer sashContainer = ServiceUtils.getInstance().getService(ISashWindowsContainer.class, serviceRegistry);
						int index = lookupIndex((TabFolder) rootModel, editorModelFinal);
						if (index != -1) {
							sashContentProvider.createFolder(sashContainer.getSelectedTabFolderModel(), index, sashContainer.getSelectedTabFolderModel(), SWT.RIGHT);
						}
					} else if (rootModel instanceof SashPanel) {
						// multiple tab-folders exist. Find existing one and move editorModel to other
						// TODO
						// ISashWindowsContainer sashContainer = ServiceUtils.getInstance().getISashWindowsContainer(serviceRegistry);
						// sashContentProvider.movePage(sashContainer.getSelectedTabFolderModel(), lookupIndex(sourceTab, editorModel), targetTabModel, -1);
					}
				} catch (ServiceException e) {

				}
			}

		});

	}

	/**
	 * Create a model identifying the editor. This model will be saved with the sash
	 *
	 * @return
	 * @throws ServiceException
	 * @throws NotFoundException
	 *             The model where to save the TableInstance is not found.
	 */
	protected TextEditorModel createEditorModel(final ServicesRegistry serviceRegistry, Classifier classifierToEdit) throws ServiceException, NotFoundException {
		TextEditorModel editorModel = TextEditorModelFactory.eINSTANCE.createTextEditorModel();

		editorModel.setEditedObject(classifierToEdit);
		editorModel.setType(PapyrusCDTEditor.EDITOR_TYPE);
		editorModel.setName("CDT " + classifierToEdit.getName()); //$NON-NLS-1$
		ILangCodegen codegen = LanguageCodegen.chooseGenerator(TextEditorConstants.CPP, classifierToEdit);
		if (codegen == null) {
			return null;
		}
		editorModel.setGeneratorID(LanguageCodegen.getID(codegen));
		TextEditorModelSharedResource model = (TextEditorModelSharedResource) ServiceUtils.getInstance().getModelSet(serviceRegistry).getModelChecked(TextEditorModelSharedResource.MODEL_ID);
		model.addTextEditorModel(editorModel);

		return editorModel;
	}

	/**
	 * The classifier to edit - corresponding to the selected object.
	 *
	 * @return
	 */
	protected Classifier getClassifierToEdit() {
		if (selectedEObject instanceof Operation) {
			return ((Operation) selectedEObject).getFeaturingClassifiers().get(0);
		} else if (selectedEObject instanceof Transition) {
			return ((Transition) selectedEObject).getContainer().getStateMachine().getContext();
		} else if (selectedEObject instanceof Behavior) {
			Element owner = (Behavior) selectedEObject;
			while (owner != null) {
				owner = owner.getOwner();
				if ((owner instanceof Classifier) && !(owner instanceof Behavior)) {
					return (Classifier) owner;
				}
			}
			return null;
		} else if (selectedEObject instanceof Classifier) {
			// must be class or datatype
			return (Classifier) selectedEObject;
		}
		return null;
	}

	/**
	 * return the editor model corresponding to an EObject
	 *
	 * @param serviceRegistry
	 *            the service registry
	 * @param classifierToEdit
	 *            The classifier for which a CDT editor should be opened
	 * @return
	 * @throws ServiceException
	 * @throws NotFoundException
	 */
	protected TextEditorModel getEditorModel(final ServicesRegistry serviceRegistry, Classifier classifierToEdit) throws ServiceException, NotFoundException {
		TextEditorModelSharedResource model = (TextEditorModelSharedResource) ServiceUtils.getInstance().getModelSet(serviceRegistry).getModelChecked(TextEditorModelSharedResource.MODEL_ID);
		return model.getTextEditorModel(classifierToEdit);
	}

	/**
	 * Recursively search in sash models for a FolderModel.
	 * Return the first encountered folder.
	 *
	 * @param panelModel
	 * @return
	 */
	public static int lookupIndex(TabFolder folder, Object model) {

		int index = 0;
		for (PageRef pr : folder.getChildren()) {
			if (pr.getPageIdentifier() == model) {
				return index;
			}
			index++;
		}
		return -1;
	}
}
