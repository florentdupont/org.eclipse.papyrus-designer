/**
 * 
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ui.command;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.papyrus.designer.languages.java.reverse.ui.JavaCodeReverse;
import org.eclipse.uml2.uml.Model;

/**
 * A recording command that do Java code reverse of provided elements from a selection
 * .
 * 
 * @author dumoulin
 *
 */
public class JavaCodeReverseRecordingCommand extends RecordingCommand implements RunnableWithIProgressMonitor {

	private TransactionalEditingDomain domain; 
	private Model modelRoot;
	private JavaCodeReverseOptions options;
	private TreeSelection selection;
	protected IProgressMonitor monitor=null;
	
	

	public JavaCodeReverseRecordingCommand(TransactionalEditingDomain domain, TreeSelection selection, Model modelRoot, JavaCodeReverseOptions options) {
		super(domain);
		this.domain = domain;
		this.modelRoot = modelRoot;
		this.options = options;
		this.selection = selection;
	}


	/**
	 * Do reverse of Java code.
	 * This method is called when the command is executed.
	 * 
	 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
	 *
	 */
	@Override
	protected void doExecute() {

		String packageName = options.getPackageName();
		List<String> searchPaths = options.getSearchPaths();
		
//		JavaCodeReverseWithIProgress reverse = new JavaCodeReverseWithIProgress(modelRoot, packageName, searchPaths, monitor);
		JavaCodeReverse reverse = new JavaCodeReverse(modelRoot, packageName, searchPaths);
		reverse.executeCodeReverse(selection);

	}

	/**
	 * @return the monitor
	 */
	public IProgressMonitor getProgressMonitor() {
		return monitor;
	}


	/**
	 * @param monitor the monitor to set
	 */
	public void setProgressMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}


}
