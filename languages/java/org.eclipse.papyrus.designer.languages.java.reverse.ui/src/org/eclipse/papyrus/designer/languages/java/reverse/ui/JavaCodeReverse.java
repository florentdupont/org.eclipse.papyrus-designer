/**
 *
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ui;


import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.CompilationUnit;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.JavaParser;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.ParseException;
import org.eclipse.papyrus.designer.languages.java.reverse.umlparser.CompilationUnitAnalyser;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Package;

/**
 * @author dumoulin
 *
 */
public class JavaCodeReverse {

	CompilationUnitAnalyser javaAnalyser;

	/**
	 * Constructor.
	 *
	 * @param model
	 */
	public JavaCodeReverse(Resource model) {
		javaAnalyser = new CompilationUnitAnalyser(model);
	}

	/**
	 * Constructor.
	 *
	 * @param model
	 */
	public JavaCodeReverse(Package rootPackage, String generatedPackageName, List<String> searchPaths) {
		javaAnalyser = new CompilationUnitAnalyser(rootPackage, generatedPackageName, searchPaths);
	}


	/**
	 * Try to reverse a {@link IResource}. A Resource can be one of the following :
	 * <ul>
	 *   <Li> {@link IFile} Usually a Java file (.java).</li> 
	 *   <Li> {@link IFolder} A folder</li>   
	 *   <Li> {@link IProject} An Eclipse project.</li> 
	 * </ul>
	 * 
	 * @throws CoreException
	 * @throws ParseException
	 *
	 */
	public void reverseResource(IResource resource) throws ParseException, CoreException {
		System.out.println("reverseResource(" + resource.getName() + ")");

		if (resource instanceof IFile) {
			reverseFile((IFile) resource);
		} else if (resource instanceof IFolder) {
			reverseFolder((IFolder) resource);
		} else if (resource instanceof IProject) {
			reverseProject((IProject) resource);
		}

	}

	/**
	 * Walk throw each element in folder
	 *
	 * @param resource
	 * @param model
	 * @throws CoreException
	 * @throws ParseException
	 */
	private void reverseFolder(IFolder folder) throws CoreException, ParseException {

		for (IResource resource : folder.members()) {
			reverseResource(resource);
		}

	}

	/**
	 * Walk throw each element in project.
	 *
	 * @param resource
	 * @param model
	 * @throws CoreException
	 * @throws ParseException
	 */
	private void reverseProject(IProject folder) throws CoreException, ParseException {

		for (IResource resource : folder.members()) {
			reverseResource(resource);
		}

	}

	/**
	 * Reverse a file
	 *
	 * @param resource
	 * @throws CoreException
	 * @throws ParseException
	 */
	private void reverseFile(IFile file) throws ParseException, CoreException {
		System.out.println("try to reverseFile(" + file.getName() + ")");
		if (!"java".equals(file.getFileExtension())) {
			return;
		}

		System.out.println("reverseFile(" + file.getName() + ")");

		CompilationUnit cu = JavaParser.parse(file.getContents());
		javaAnalyser.processCompilationUnit(cu);
	}

	/**
	 * @throws CoreException
	 * @throws ParseException
	 *
	 */
	public void reverseJavaElement(IJavaElement element) throws ParseException, CoreException
	{
		System.out.println("reverseJavaElement(" + element.getElementName() + ")");

		if (element instanceof ITypeRoot)
		{
			reverseCompilationUnit((ITypeRoot) element);
		}
		else if (element instanceof IPackageFragment)
		{
			reversePackageFragment((IPackageFragment) element);
		}
		else if (element instanceof IPackageFragmentRoot)
		{
			reversePackageFragmentRoot((IPackageFragmentRoot) element);
		}
		else
		{
			System.err.println("Can't reverse Java Element " + element);
		}

	}

	/**
	 *
	 * @param element
	 * @throws ParseException
	 * @throws CoreException
	 */
	private void reversePackageFragmentRoot(IPackageFragmentRoot element) throws ParseException, CoreException {

		System.out.println("reversePackageFragmentRoot(" + element.getElementName() + ")");

		IJavaElement[] children = element.getChildren();
		for (IJavaElement child : children) {
			reverseJavaElement(child);
		}

	}

	/**
	 *
	 * @param element
	 * @throws ParseException
	 * @throws CoreException
	 */
	private void reversePackageFragment(IPackageFragment element) throws ParseException, CoreException {

		System.out.println("reversePackageFragment(" + element.getElementName() + ")");

		IJavaElement[] children = element.getChildren();
		for (IJavaElement child : children) {
			reverseJavaElement(child);
		}
	}

	/**
	 * Reverse a file
	 *
	 * @param resource
	 * @throws CoreException
	 * @throws ParseException
	 */
	private void reverseCompilationUnit(ITypeRoot unit) throws ParseException, CoreException {
		System.out.println("try to reverseUnit(" + unit.getElementName() + ")");
		System.out.println("file type=(" + unit.getElementType() + ")");

		String source = unit.getSource();
		if (source == null)
		{
			System.err.println("No source attached to unit '" + unit.getElementName() + "'");
			return;
		}


		CompilationUnit cu = JavaParser.parse(new StringReader(source));
		javaAnalyser.processCompilationUnit(cu);
	}

	/**
	 * Try to reverse elements found in the provided selection.
	 * 
	 * @param treeSelection
	 */
	public void executeCodeReverse( TreeSelection treeSelection ) {
		System.out.println("executeCodeReverse()");

		// String filename = treeSelection.
		@SuppressWarnings("rawtypes")
		Iterator iter = treeSelection.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			// Translate java ICompilationUnit to Iresource
			if (obj instanceof ICompilationUnit) {
				ICompilationUnit u = (ICompilationUnit) obj;
				try {
					obj = u.getCorrespondingResource();
				} catch (JavaModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (obj instanceof IPackageFragment)
			{
				IPackageFragment u = (IPackageFragment) obj;
				try {
					IResource res = u.getCorrespondingResource();
					if (res != null) {
						obj = res;
					}
				} catch (JavaModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// This happen when selection is an element from a jar
			if (obj instanceof IJavaElement)
			{
				IJavaElement u = (IJavaElement) obj;
				try {

					reverseJavaElement(u);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// This is a regular java file
			if (obj instanceof IResource)
			{
				try {
					reverseResource((IResource) obj);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		System.out.println("reverse done");

	}

	/**
	 * Get the selection from the Active page, and reverse reversible selected elements.
	 * It is preferable to use {@link #executeCodeReverse(TreeSelection)} instead of this method (selection can be null).
	 *
	 * @param generationPackageName Not used.
	 * @param searchPaths Not used.
	 * 
	 */
	public void executeCodeReverse() {

		// Get current selection
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = page.getSelection();

		if( ! (selection instanceof TreeSelection )) {
			return;
				}

		executeCodeReverse((TreeSelection)selection);
			}

	/**
	 * Get the selection from the Active page, and reverse reversible selected elements.
	 *
	 * @param generationPackageName Not used.
	 * @param searchPaths Not used.
	 * 
	 * @deprecated Use {@link #executeCodeReverse()} instead.
	 */
	public void executeCodeReverse(Resource umlResource, String generationPackageName, List<String> searchPaths) {
		executeCodeReverse();
	}

}
