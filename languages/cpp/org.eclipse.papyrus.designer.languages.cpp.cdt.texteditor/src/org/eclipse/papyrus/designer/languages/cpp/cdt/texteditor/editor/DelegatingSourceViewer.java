/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Ansgar Radermacher (CEA LIST) Ansgar.Radermacher@cea.fr - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.cpp.cdt.texteditor.editor;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.jface.text.IAutoIndentStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IEventConsumer;
import org.eclipse.jface.text.IFindReplaceTarget;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.IUndoManager;
import org.eclipse.jface.text.IViewportListener;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.uml2.uml.Classifier;

/**
 * Unsuccessful attempt to create a delegating source viewer that changes the implementation of
 * the operation getSelectionProvider of a source viewer. This does not work in case of CDT since the
 * source viewer is cast to CSourceViewer later.
 *
 * CURRENTLY NOT USED
 */
@Deprecated
public class DelegatingSourceViewer implements ISourceViewer {

	public DelegatingSourceViewer(ISourceViewer delegationTarget, Classifier classifier) {
		// super(null, null, null, false, 0, null);
		this.dt = delegationTarget;
		this.cl = classifier;

		sp = new ISelectionProvider() {

			@Override
			public void setSelection(ISelection selection) {
			}

			@Override
			public void removeSelectionChangedListener(
					ISelectionChangedListener listener) {
				System.err.println("removeSelectionChangedListener");
			}

			@Override
			public ISelection getSelection() {
				return new IStructuredSelection() {

					@Override
					public boolean isEmpty() {
						return false;
					}

					@Override
					public List toList() {
						List l = new BasicEList<Object>();
						l.add(cl);
						return l;
					}

					@Override
					public Object[] toArray() {
						return new Object[] { cl };
					}

					@Override
					public int size() {
						// TODO Auto-generated method stub
						return 1;
					}

					@Override
					public Iterator iterator() {
						return null;
					}

					@Override
					public Object getFirstElement() {
						return cl;
					}
				};
			}

			@Override
			public void addSelectionChangedListener(ISelectionChangedListener listener) {
				// TODO Auto-generated method stub
				System.err.println("add SelectionChangedListener");

			}
		};
	}

	ISourceViewer dt;

	Classifier cl;

	ISelectionProvider sp;

	@Override
	public StyledText getTextWidget() {
		return dt.getTextWidget();
	}

	@Override
	public void setUndoManager(IUndoManager undoManager) {
		dt.setUndoManager(undoManager);
	}

	@Override
	public void setTextDoubleClickStrategy(
			ITextDoubleClickStrategy strategy, String contentType) {
		dt.setTextDoubleClickStrategy(strategy, contentType);
	}

	@Override
	public void setAutoIndentStrategy(IAutoIndentStrategy strategy,
			String contentType) {
		dt.setAutoIndentStrategy(strategy, contentType);
	}

	@Override
	public void setTextHover(ITextHover textViewerHover,
			String contentType) {
		dt.setTextHover(textViewerHover, contentType);
	}

	@Override
	public void activatePlugins() {
		dt.activatePlugins();
	}

	@Override
	public void resetPlugins() {
		dt.resetPlugins();
	}

	@Override
	public void addViewportListener(IViewportListener listener) {
		dt.addViewportListener(listener);
	}

	@Override
	public void removeViewportListener(IViewportListener listener) {
		dt.removeViewportListener(listener);
	}

	@Override
	public void addTextListener(ITextListener listener) {
		dt.addTextListener(listener);
	}

	@Override
	public void removeTextListener(ITextListener listener) {
		dt.removeTextListener(listener);
	}

	@Override
	public void addTextInputListener(ITextInputListener listener) {
		dt.addTextInputListener(listener);
	}

	@Override
	public void removeTextInputListener(ITextInputListener listener) {
		dt.removeTextInputListener(listener);
	}

	@Override
	public void setDocument(IDocument document) {
		dt.setDocument(document);
	}

	@Override
	public IDocument getDocument() {
		return dt.getDocument();
	}

	@Override
	public void setEventConsumer(IEventConsumer consumer) {
		dt.setEventConsumer(consumer);

	}

	@Override
	public void setEditable(boolean editable) {
		dt.setEditable(editable);
	}

	@Override
	public boolean isEditable() {
		return dt.isEditable();
	}

	@Override
	public void setDocument(IDocument document, int modelRangeOffset,
			int modelRangeLength) {
		dt.setDocument(document, modelRangeOffset, modelRangeLength);
	}

	@Override
	public void setVisibleRegion(int offset, int length) {
		dt.setVisibleRegion(offset, length);
	}

	@Override
	public void resetVisibleRegion() {
		dt.resetVisibleRegion();
	}

	@Override
	public IRegion getVisibleRegion() {
		return dt.getVisibleRegion();
	}

	@Override
	public boolean overlapsWithVisibleRegion(int offset, int length) {
		return dt.overlapsWithVisibleRegion(offset, length);
	}

	@Override
	public void changeTextPresentation(TextPresentation presentation,
			boolean controlRedraw) {
		dt.changeTextPresentation(presentation, controlRedraw);
	}

	@Override
	public void invalidateTextPresentation() {
		dt.invalidateTextPresentation();

	}

	@Override
	public void setTextColor(Color color) {
		dt.setTextColor(color);
	}

	@Override
	public void setTextColor(Color color, int offset, int length,
			boolean controlRedraw) {
		dt.setTextColor(color, offset, length, controlRedraw);
	}

	@Override
	public ITextOperationTarget getTextOperationTarget() {
		return dt.getTextOperationTarget();
	}

	@Override
	public IFindReplaceTarget getFindReplaceTarget() {
		return dt.getFindReplaceTarget();
	}

	@Override
	public void setDefaultPrefixes(String[] defaultPrefixes,
			String contentType) {
		dt.setDefaultPrefixes(defaultPrefixes, contentType);

	}

	@Override
	public void setIndentPrefixes(String[] indentPrefixes,
			String contentType) {
		dt.setIndentPrefixes(indentPrefixes, contentType);
	}

	@Override
	public void setSelectedRange(int offset, int length) {
		dt.setSelectedRange(offset, length);
	}

	@Override
	public Point getSelectedRange() {
		return dt.getSelectedRange();
	}

	@Override
	public ISelectionProvider getSelectionProvider() {
		return dt.getSelectionProvider();
	}

	@Override
	public void revealRange(int offset, int length) {
		dt.revealRange(offset, length);
	}

	@Override
	public void setTopIndex(int index) {
		dt.setTopIndex(index);
	}

	@Override
	public int getTopIndex() {
		return dt.getTopIndex();
	}

	@Override
	public int getTopIndexStartOffset() {
		return dt.getTopIndexStartOffset();
	}

	@Override
	public int getBottomIndex() {
		return dt.getBottomIndex();
	}

	@Override
	public int getBottomIndexEndOffset() {
		return dt.getBottomIndexEndOffset();
	}

	@Override
	public int getTopInset() {
		return dt.getTopInset();
	}

	@Override
	public void configure(SourceViewerConfiguration configuration) {
		dt.configure(configuration);
	}

	@Override
	public void setAnnotationHover(IAnnotationHover annotationHover) {
		dt.setAnnotationHover(annotationHover);
	}

	@Override
	public void setDocument(IDocument document,
			IAnnotationModel annotationModel) {
		dt.setDocument(document, annotationModel);
	}

	@Override
	public void setDocument(IDocument document,
			IAnnotationModel annotationModel, int modelRangeOffset,
			int modelRangeLength) {
		dt.setDocument(document, annotationModel, modelRangeOffset, modelRangeLength);

	}

	@Override
	public IAnnotationModel getAnnotationModel() {
		return dt.getAnnotationModel();
	}

	@Override
	public void setRangeIndicator(Annotation rangeIndicator) {
		dt.setRangeIndicator(rangeIndicator);

	}

	@Override
	public void setRangeIndication(int offset, int length,
			boolean moveCursor) {
		dt.setRangeIndication(offset, length, moveCursor);
	}

	@Override
	public IRegion getRangeIndication() {
		return dt.getRangeIndication();
	}

	@Override
	public void removeRangeIndication() {
		dt.removeRangeIndication();
	}

	@Override
	public void showAnnotations(boolean show) {
		dt.showAnnotations(show);
	}
}
