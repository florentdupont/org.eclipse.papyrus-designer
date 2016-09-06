/*******************************************************************************
 * Copyright (c) 2006 - 2016 CEA LIST.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Shuai Li (CEA LIST) <shuai.li@cea.fr> - initial API and implementation
 *******************************************************************************/

package org.eclipse.papyrus.designer.languages.java.codegen.transformation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.compare.ResourceNode;
import org.eclipse.compare.contentmergeviewer.IDocumentRange;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.DocumentRangeNode;
import org.eclipse.compare.structuremergeviewer.IDiffElement;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IImportContainer;
import org.eclipse.jdt.core.IImportDeclaration;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageDeclaration;
import org.eclipse.jdt.core.ISourceManipulation;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.ISourceReference;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTMatcher;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NodeFinder;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.internal.ui.compare.JavaStructureCreator;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.papyrus.designer.languages.java.codegen.Activator;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

/**
 * Main class of Java code generator
 */
public class JavaMerger {

	private IJavaProject javaProject;

	private String sourceSrcFolder;

	private String targetSrcFolder; // For incremental code generation

	private IFile rightFile; // Currently compared right file

	private CompilationUnit rightAstRoot; // AST of right file

	private boolean astNeedsRefresh = false;

	public JavaMerger(IProject project, String sourceSrcFolder, String targetSrcFolder) {
		if (project != null) {
			javaProject = JavaCore.create(project);
		}

		this.sourceSrcFolder = sourceSrcFolder;
		this.targetSrcFolder = targetSrcFolder;
	}

	public void merge() {
		if (javaProject != null) {
			if (sourceSrcFolder != null && targetSrcFolder != null && !sourceSrcFolder.equals(targetSrcFolder)) {
				IFolder targetFolder = javaProject.getProject().getFolder(targetSrcFolder);

				if (targetFolder.exists()) {
					IFolder srcFolder = javaProject.getProject().getFolder(sourceSrcFolder);

					if (srcFolder.exists()) {
						try {
							Differencer d = new Differencer();
							ResourceNode left = new ResourceNode(srcFolder);
							ResourceNode right = new ResourceNode(targetFolder);

							DiffNode diffs = (DiffNode) d.findDifferences(false, null, null, null, left, right);

							handleDifferences(diffs);
						} catch (Exception e) {
							Activator.log.error(e);
						}

						/*try {
							srcFolder.delete(true, null);
						} catch (CoreException e) {
							Activator.log.error(e);
						}*/
					}
				}
			}
		}
	}

	protected void handleDifferences(DiffNode diffNode) {
		if (diffNode != null) {
			if (diffNode.getChildren() == null || diffNode.getChildren().length == 0) {
				// Leaf difference node
				if (diffNode.getLeft() != null && diffNode.getRight() == null) {
					// Resource exists in source folder but not target folder ==> add it to target folder
					if (diffNode.getLeft() instanceof ResourceNode) {
						ResourceNode leftResourceNode = (ResourceNode) diffNode.getLeft();
						if (leftResourceNode.getResource() instanceof IContainer || leftResourceNode.getResource() instanceof IFile) {

							// Get right parent container
							if (diffNode.getParent() instanceof DiffNode) {
								DiffNode parentDiffNode = (DiffNode) diffNode.getParent();

								if (parentDiffNode.getRight() instanceof ResourceNode) {
									ResourceNode rightParentResourceNode = (ResourceNode) parentDiffNode.getRight();
									if (rightParentResourceNode.getResource() instanceof IContainer) {
										IContainer rightParentContainer = (IContainer) rightParentResourceNode.getResource();
										try {
											leftResourceNode.getResource().copy(new Path(rightParentContainer.getFullPath().toString() + "/" + leftResourceNode.getName()), true, null);
										} catch (CoreException e) {
											Activator.log.error(e);
										}
									}
								}
							}
						}
					} else if (diffNode.getLeft() instanceof DocumentRangeNode) {
						DiffNode parentDiffNode = (DiffNode) diffNode.getParent();
						if (parentDiffNode != null) {
							// Get left content
							DocumentRangeNode leftDocRangeNode = (DocumentRangeNode) diffNode.getLeft();
							String leftContent = null;
							try {
								leftContent = leftDocRangeNode.getDocument().get(leftDocRangeNode.getRange().offset, leftDocRangeNode.getRange().length);
							} catch (BadLocationException e) {
								Activator.log.error(e);
							}

							// Get right type or right compilation unit
							IJavaElement rightType = getRightType(parentDiffNode);

							if (leftContent != null && rightType != null) {
								// Check what is left content
								String leftId = leftDocRangeNode.getId();
								if (leftId != null) {
									ICompilationUnit cu = null;
									if (leftId.startsWith(JavaNodeId.FIELD)) {
										if (rightType instanceof IType) {
											try {
												// Enums are overwritten as a whole, so we don't handle its fine-grained differences here
												if (!((IType) rightType).isEnum()) {
													((IType) rightType).createField(leftContent, null, true, null);

													IField[] fields = ((IType) rightType).getFields();
													if (fields != null && fields.length > 0) {
														IField createdField = fields[fields.length - 1];
														for (IField field : fields) {
															if (field.getElementName().equals(createdField.getElementName())
																	&& !field.getTypeSignature().equals(createdField.getTypeSignature())) {
																field.delete(true, null);
															}
														}
													}

													cu = ((IType) rightType).getCompilationUnit();
												}
											} catch (JavaModelException e) {
												Activator.log.error(e);
											}
										}
									} else if (leftId.startsWith(JavaNodeId.METHOD)) {
										if (rightType instanceof IType) {
											try {
												((IType) rightType).createMethod(leftContent, null, true, null);
												cu = ((IType) rightType).getCompilationUnit();
											} catch (JavaModelException e) {
												Activator.log.error(e);
											}
										}
									} else if (leftId.startsWith(JavaNodeId.IMPORT_CONTAINER)) {
										if (rightType instanceof ICompilationUnit) {
											createImports((ICompilationUnit) rightType, leftContent);
											cu = (ICompilationUnit) rightType;
										}
									} else if (leftId.startsWith(JavaNodeId.IMPORTDECLARATION)) {
										if (rightType instanceof ICompilationUnit) {
											createImports((ICompilationUnit) rightType, leftContent);
											cu = (ICompilationUnit) rightType;
										}
									} else if (leftId.startsWith(JavaNodeId.PACKAGEDECLARATION)) {
										if (rightType instanceof ICompilationUnit) {
											createPackageDeclaration((ICompilationUnit) rightType, leftContent);
											cu = (ICompilationUnit) rightType;
										}
									} else if (leftId.startsWith(JavaNodeId.TYPE)) {
										if (rightType instanceof IType) {
											try {
												((IType) rightType).createType(leftContent, null, true, null);
												cu = ((IType) rightType).getCompilationUnit();
											} catch (JavaModelException e) {
												Activator.log.error(e);
											}
										} else if (rightType instanceof ICompilationUnit) {
											try {
												((ICompilationUnit) rightType).createType(leftContent, null, true, null);
												cu = (ICompilationUnit) rightType;
											} catch (JavaModelException e) {
												Activator.log.error(e);
											}
										}
									}

									if (cu!= null) {
										try {
											cu.save(null, true);
										} catch (JavaModelException e) {
											Activator.log.error(e);
										}
									}
								}
							}
						}
					}
				} else if (diffNode.getLeft() instanceof ResourceNode && diffNode.getRight() instanceof ResourceNode) {
					// Resource exists in both source and target folder ==> compare and merge
					ResourceNode leftResourceNode = (ResourceNode) diffNode.getLeft();
					ResourceNode rightResourceNode = (ResourceNode) diffNode.getRight();

					if (leftResourceNode.getResource() instanceof IFile && rightResourceNode.getResource() instanceof IFile) {
						rightFile = (IFile) rightResourceNode.getResource();
						rightAstRoot = null;

						JavaStructureCreator leftJavaStructureCreator = new JavaStructureCreator();
						IStructureComparator leftRootJavaNode = leftJavaStructureCreator.getStructure(leftResourceNode);

						JavaStructureCreator rightJavaStructureCreator = new JavaStructureCreator();
						IStructureComparator rightRootJavaNode = rightJavaStructureCreator.getStructure(rightResourceNode);

						Differencer d = new Differencer();
						DiffNode diffs = (DiffNode) d.findDifferences(false, null, null, null, leftRootJavaNode, rightRootJavaNode);
						if (diffs != null && diffs.getRight() instanceof DocumentRangeNode) {
							DocumentRangeNode rightNode = (DocumentRangeNode) diffs.getRight();
							IDocument rightDocument = rightNode.getDocument();
							rightDocument.addPositionUpdater(new DefaultPositionUpdater(IDocumentRange.RANGE_CATEGORY));
							rightDocument.addPositionCategory(IDocumentRange.RANGE_CATEGORY);
							//rightDocument.addDocumentListener(new JavaMergerDocumentListener(rightDocument));
						}
						handleDifferences(diffs);

						rightFile = null;
						rightAstRoot = null;
					}
				} else if (diffNode.getLeft() instanceof DocumentRangeNode && diffNode.getRight() instanceof DocumentRangeNode) {
					// Resource exists in both source and target folder ==> compare and merge
					DocumentRangeNode leftDocRangeNode = (DocumentRangeNode) diffNode.getLeft();

					// Get left content
					String leftContent = null;
					try {
						leftContent = leftDocRangeNode.getDocument().get(leftDocRangeNode.getRange().offset, leftDocRangeNode.getRange().length);
					} catch (BadLocationException e) {
						Activator.log.error(e);
					}

					// Get right type or right compilation unit
					IJavaElement rightType = getRightType(diffNode);
					IJavaElement rightElement = getRightElement(diffNode);

					if (leftContent != null) {
						// Check what is left content
						String leftId = leftDocRangeNode.getId();
						// Store rightElement's offset, length, and javadoc

						ICompilationUnit cu = null;

						if (leftId != null) {
							if (leftId.startsWith(JavaNodeId.FIELD)) {
								if (rightType instanceof IType &&  rightElement instanceof IField) {
									try {
										// Enums are overwritten as a whole so we don't handle its fine grained differences it here
										if (!((IType) rightType).isEnum()) {
											astNeedsRefresh = true;
											CompilationUnit astRoot = getRightAstRoot();
											ASTNode rightElementNode = getAstNode(rightElement, astRoot);

											// Get right javadoc and initializer
											Javadoc rightJavadoc = null;
											Expression rightInitializer = null;
											if (rightElementNode instanceof FieldDeclaration) {
												FieldDeclaration rightFieldDeclaration = (FieldDeclaration) rightElementNode;

												rightJavadoc = rightFieldDeclaration.getJavadoc();

												List fragments = rightFieldDeclaration.fragments();
												if (!fragments.isEmpty()) {
													VariableDeclarationFragment fragment = (VariableDeclarationFragment) fragments.get(0);
													rightInitializer = fragment.getInitializer();
												}
											}

											// Delete old field and create new field
											IField createdField = ((IType) rightType).createField(leftContent, getNextMember((IField) rightElement), true, null);
											((IField) rightElement).delete(true, null);
											cu = ((IField) rightElement).getCompilationUnit();
											cu.save(null, true);

											// Replace created field's javadoc with previous right field's javadoc if former is blank
											boolean needSave = false;
											astNeedsRefresh = true;
											CompilationUnit astRootUpdate = getRightAstRoot();
											ASTNode createdFieldNode = getAstNode(createdField, astRootUpdate);
											if (createdFieldNode instanceof FieldDeclaration) {
												if (rightInitializer != null) {
													List fragments = ((FieldDeclaration) createdFieldNode).fragments();
													if (!fragments.isEmpty()) {
														VariableDeclarationFragment fragment = (VariableDeclarationFragment) fragments.get(0);
														Expression leftInitializer = fragment.getInitializer();

														if (leftInitializer == null) {
															ASTNode copyOfRightInitializer = ASTNode.copySubtree(astRootUpdate.getAST(), rightInitializer);
															if (copyOfRightInitializer instanceof Expression) {
																fragment.setInitializer((Expression) copyOfRightInitializer);
																needSave = true;
															}
														}
													}
												}

												if (updateCreatedJavadoc(rightJavadoc, (FieldDeclaration) createdFieldNode, astRootUpdate)) {
													needSave = true;
												}

												if (needSave) {
													saveRightAstContent();
												}
											}
										}
									} catch (JavaModelException e) {
										Activator.log.error(e);
									}
								}
							} else if (leftId.startsWith(JavaNodeId.METHOD)) {
								if (rightType instanceof IType && rightElement instanceof IMethod) {
									try {
										String newLeftContent = replaceLeftBody(leftContent, (IMethod) rightElement);
										if (newLeftContent.isEmpty()) {
											newLeftContent = leftContent;
										}

										astNeedsRefresh = true;
										CompilationUnit astRoot = getRightAstRoot();
										ASTNode rightElementNode = getAstNode(rightElement, astRoot);

										// Get right javadoc
										Javadoc rightJavadoc = null;
										if (rightElementNode instanceof MethodDeclaration) {
											MethodDeclaration rightMethodDeclaration = (MethodDeclaration) rightElementNode;
											rightJavadoc = rightMethodDeclaration.getJavadoc();
										}

										// Delete old method and create new one
										IMethod createdMethod = ((IType) rightType).createMethod(newLeftContent, getNextMember((IMethod) rightElement), true, null);
										((IMethod) rightElement).delete(true, null);
										cu = ((IMethod) rightElement).getCompilationUnit();
										cu.save(null, true);

										// Replace body and javadoc if necessary
										boolean needSave = false;
										astNeedsRefresh = true;
										CompilationUnit astRootUpdate = getRightAstRoot();
										ASTNode createdMethodNode = getAstNode(createdMethod, astRootUpdate);
										if (createdMethodNode instanceof MethodDeclaration) {
											MethodDeclaration createdMethodDeclaration = (MethodDeclaration) createdMethodNode;

											// Replace created method's javadoc with previous right method's javadoc if former is blank
											if (updateCreatedJavadoc(rightJavadoc, createdMethodDeclaration, astRootUpdate)) {
												needSave = true;
											}

											// Save AST content to right file if necessary
											if (needSave) {
												saveRightAstContent();
											}
										}
									} catch (JavaModelException e) {
										Activator.log.error(e);
									}
								}
							} else if (leftId.startsWith(JavaNodeId.IMPORTDECLARATION)) {
								if (rightType instanceof ICompilationUnit) {
									try {
										if (rightElement instanceof IImportDeclaration) {
											((IImportDeclaration) rightElement).delete(true, null);
										}
										createImports((ICompilationUnit) rightType, leftContent);
										cu = (ICompilationUnit) rightType;
									} catch (JavaModelException e) {
										Activator.log.error(e);
									}
								}
							} else if (leftId.startsWith(JavaNodeId.PACKAGEDECLARATION)) {
								if (rightType instanceof ICompilationUnit) {
									createPackageDeclaration((ICompilationUnit) rightType, leftContent);
									cu = (ICompilationUnit) rightType;
								}
							} else if (leftId.startsWith(JavaNodeId.TYPE)) {
								if (rightType instanceof IType) { // rightType == rightElement normally
									try {
										if (((IType) rightType).isEnum()) {
											IJavaElement parent = ((IType) rightType).getParent();
											cu = ((IType) rightType).getCompilationUnit();
											((IType) rightType).delete(true, null); // TODO create type next to rightElement
											if (parent instanceof IType) {
												((IType) parent).createType(leftContent, null, true, null);
											} else if (parent instanceof ICompilationUnit) {
												((ICompilationUnit) parent).createType(leftContent, null, true, null);
											}
										} else {
											updateType(leftContent, (IType) rightType);
										}
									} catch (JavaModelException e) {
										Activator.log.error(e);
									}
								}
							}

							if (cu != null) {
								try {
									cu.save(null, true);
								} catch (JavaModelException e) {
									Activator.log.error(e);
								}
							}
						}
					}
				}
			} else {
				boolean handleChildren = true;

				if (diffNode.getLeft() instanceof DocumentRangeNode && diffNode.getRight() instanceof DocumentRangeNode) {
					// Resource exists in both source and target folder ==> compare and merge
					DocumentRangeNode leftDocRangeNode = (DocumentRangeNode) diffNode.getLeft();
					DocumentRangeNode rightDocRangeNode = (DocumentRangeNode) diffNode.getRight();

					if (leftDocRangeNode.getId().startsWith(JavaNodeId.TYPE) && rightDocRangeNode.getId().startsWith(JavaNodeId.TYPE)) {
						// Get left content
						String leftContent = null;
						try {
							leftContent = leftDocRangeNode.getDocument().get(leftDocRangeNode.getRange().offset, leftDocRangeNode.getRange().length);
						} catch (BadLocationException e) {
							Activator.log.error(e);
						}

						// Get right type or right compilation unit

						if (leftContent != null && !leftContent.isEmpty()) {
							IJavaElement rightElement = getRightType(diffNode);

							if (rightElement instanceof IType) {
								try {
									if (((IType) rightElement).isEnum()) {
										IJavaElement parent = ((IType) rightElement).getParent();
										((IType) rightElement).delete(true, null); // TODO create type next to rightElement
										if (parent instanceof IType) {
											((IType) parent).createType(leftContent, null, true, null);
											((IType) parent).getCompilationUnit().save(null, true);
										} else if (parent instanceof ICompilationUnit) {
											((ICompilationUnit) parent).createType(leftContent, null, true, null);
											((ICompilationUnit) parent).save(null, true);
										}
										handleChildren = false;
									} else {
										updateType(leftContent, (IType) rightElement);
									}
								} catch (JavaModelException e) {
									Activator.log.error(e);
								}
							}
						}
					}
				}

				if (handleChildren) {
					for (IDiffElement childDiffNode : diffNode.getChildren()) {
						if (childDiffNode instanceof DiffNode) {
							handleDifferences((DiffNode) childDiffNode);
						}
					}
				}
			}
		}
	}

	/**
	 * Returns the type or compilation unit of the right node of this difference node
	 * E.g. returns the type if the right node points to a type. Returns the parent of an attribute or method
	 * if the right node points to an attribute or method. Returns a compilation unit if the right node points
	 * to the beginning of the compilation unit.
	 * 
	 * @param diffNode
	 *        The difference node that should contain a right node
	 * @return
	 *        The type of the right node (or containing it) or the compilation unit represented by the right node
	 */
	private IJavaElement getRightType(DiffNode diffNode) {
		if (diffNode == null || !(diffNode.getRight() instanceof DocumentRangeNode) || rightFile == null) {
			return null;
		}

		IJavaElement rightJavaFile = JavaCore.create(rightFile);
		DocumentRangeNode rightDocRangeNode = (DocumentRangeNode) diffNode.getRight();

		if (rightJavaFile instanceof ICompilationUnit) {
			ICompilationUnit rightCompilationUnit = (ICompilationUnit) rightJavaFile;

			if (rightDocRangeNode.getId() != null) {
				// Right node represents a compilation unit
				if (rightDocRangeNode.getId().startsWith(JavaNodeId.COMPILATIONUNIT)
						|| rightDocRangeNode.getId().startsWith(JavaNodeId.IMPORT_CONTAINER)
						|| rightDocRangeNode.getId().startsWith(JavaNodeId.PACKAGEDECLARATION)) {
					return rightCompilationUnit;
				}


				// Right node represents a type
				if (rightDocRangeNode.getId().startsWith(JavaNodeId.TYPE)) {
					String typeName = null;

					int rightOffset = rightDocRangeNode.getRange().getOffset();
					int rightLength = rightDocRangeNode.getRange().getLength();

					try {
						String typeContent = rightDocRangeNode.getDocument().get(rightOffset, rightLength);

						ASTNode cu = getASTNodeFromString("package default;" + typeContent, ASTParser.K_COMPILATION_UNIT);
						if (cu instanceof CompilationUnit) {
							List types = ((CompilationUnit) cu).types();
							if (!types.isEmpty()) {
								AbstractTypeDeclaration type = (AbstractTypeDeclaration) types.get(0);
								typeName = type.getName().toString();
							}
						}
					} catch (BadLocationException e) {
						Activator.log.error(e);
					}

					if (typeName != null && !typeName.isEmpty()) {
						IType[] types;
						try {
							types = rightCompilationUnit.getAllTypes();
							for (IType type : types) {
								if (type.getElementName().equals(typeName)) {
									return type;
								}
							}
						} catch (JavaModelException e) {
							Activator.log.error(e);
						}
					}

					// Old way of getting type (more exact but not usable if right document's positions are not updated)
					//					// Index is offset + length. If we only give the offset, trying to get an inner type gives the declaring type instead
					//					int index = rightDocRangeNode.getRange().offset + rightDocRangeNode.getRange().length;
					//					try {
					//						return rightCompilationUnit.getElementAt(index);
					//					} catch (JavaModelException e) {
					//						Activator.log.error(e);
					//					}
				}

				// Right node represents a member
//				if (rightDocRangeNode.getId().startsWith(JavaNodeId.FIELD)
//						|| rightDocRangeNode.getId().startsWith(JavaNodeId.METHOD)) {
//					// Nothing, recurse
//
//					// Old way of getting type (more exact but not usable if right document's positions are not updated)
//					//					int index = rightDocRangeNode.getRange().offset + rightDocRangeNode.getRange().length;
//					//					try {
//					//						IJavaElement javaElement = rightCompilationUnit.getElementAt(index);
//					//						if (javaElement instanceof IMember) {
//					//							return ((IMember) javaElement).getDeclaringType();
//					//						}
//					//					} catch (JavaModelException e) {
//					//						Activator.log.error(e);
//					//					}
//				}
			}

			// Recurse
			return getRightType((DiffNode) diffNode.getParent());
		}

		return null;
	}

	/**
	 * Returns the java element represented by the right node of the difference node
	 * 
	 * @param diffNode
	 *        The difference node that should contain a right node
	 * @return
	 *        The java element represented by the right node
	 */
	private IJavaElement getRightElement(DiffNode diffNode) {
		if (diffNode == null || !(diffNode.getRight() instanceof DocumentRangeNode) || rightFile == null) {
			return null;
		}

		IJavaElement rightJavaFile = JavaCore.create(rightFile);
		DocumentRangeNode rightDocRangeNode = (DocumentRangeNode) diffNode.getRight();

		if (rightJavaFile instanceof ICompilationUnit) {
			ICompilationUnit rightCompilationUnit = (ICompilationUnit) rightJavaFile;

			try {
				Document currentDocument = new Document(rightCompilationUnit.getSource());
				IDocument rightDocument = rightDocRangeNode.getDocument();

				try {
					String rightContent = rightDocument.get(rightDocRangeNode.getRange().getOffset(), rightDocRangeNode.getRange().getLength());
					FindReplaceDocumentAdapter findReplace = new FindReplaceDocumentAdapter(currentDocument);
					IRegion found = findReplace.find(0, rightContent, true, true, false, false);
					if (found != null) {
						int length = found.getLength() / 2;
						if (found.getLength() == 1) {
							length = 1;
						}
						int index = found.getOffset() + length;

						try {
							return rightCompilationUnit.getElementAt(index);
						} catch (JavaModelException e) {
							Activator.log.error(e);
						}
					}
				} catch (BadLocationException e) {
					Activator.log.error(e);
				}
			} catch (JavaModelException e) {
				Activator.log.error(e);
			}
		}

		return null;
	}

	private void removeOldElement(IJavaElement createdElement, IJavaElement rightElement, int rightLength) {
		if (createdElement instanceof ISourceReference & rightElement != null) {
			ICompilationUnit cu = null;

			if (rightElement instanceof IMember) {
				cu = ((IMember) rightElement).getCompilationUnit();
			} else if (rightElement instanceof IImportDeclaration) {
				IJavaElement parent = ((IImportDeclaration) rightElement).getParent();
				if (parent instanceof IImportContainer) {
					IJavaElement containerParent = parent.getParent();
					if (containerParent instanceof ICompilationUnit) {
						cu = (ICompilationUnit) containerParent;
					}
				}
			} else if (rightElement instanceof IPackageDeclaration) {
				IJavaElement parent = ((IPackageDeclaration) rightElement).getParent();
				if (parent instanceof ICompilationUnit) {
					cu = (ICompilationUnit) parent;
				}
			} else if (rightElement instanceof IImportContainer)  {
				IJavaElement parent = ((IImportContainer) rightElement).getParent();
				if (parent instanceof ICompilationUnit) {
					cu = (ICompilationUnit) parent;
				}
			}

			if (cu != null) {
				// createdElement.offset + createdElement.length + rightELement.length is where rightElement is now
				// Thus We get the javaElement at this position and we delete it
				try {
					int index = ((ISourceReference) createdElement).getSourceRange().getOffset() + ((ISourceReference) createdElement).getSourceRange().getLength() + rightLength;
					IJavaElement javaElement = cu.getElementAt(index);
					if ((javaElement instanceof IMember && rightElement instanceof IMember)
							|| (javaElement instanceof IImportDeclaration && rightElement instanceof IImportDeclaration)
							|| (javaElement instanceof IPackageDeclaration && rightElement instanceof IPackageDeclaration)
							|| (javaElement instanceof IImportContainer && rightElement instanceof IImportContainer)) {
						((ISourceManipulation) javaElement).delete(true, null);
					}
				} catch (JavaModelException e) {
					Activator.log.error(e);
				} 
			}
		}
	}

	private IMember getNextMember(IMember member) {
		IType declaringType = member.getDeclaringType();

		if (declaringType != null) {
			IMember[] members = null;

			if (member instanceof IField) {
				try {
					members = declaringType.getFields();
				} catch (JavaModelException e) {
					Activator.log.error(e);
				}


			} else if (member instanceof IMethod) {
				try {
					members = declaringType.getMethods();
				} catch (JavaModelException e) {
					Activator.log.error(e);
				}
			}

			if (members != null) {
				for (int i = 0; i < members.length; i++) {
					if (members[i].getElementName().equals(member.getElementName())) {
						if (i < members.length - 1) {
							return members[i+1];
						}
					}
				}
			}
		}

		return null;
	}

	private CompilationUnit getRightAstRoot() {
		if (rightAstRoot == null || astNeedsRefresh) {
			if (rightFile != null) {
				IJavaElement rightJavaFile = JavaCore.create(rightFile);
				if (rightJavaFile instanceof ICompilationUnit) {
					//Get the compilation unit for traversing AST
					final ASTParser parser = ASTParser.newParser(AST.JLS8);
					parser.setSource((ICompilationUnit) rightJavaFile);
					parser.setResolveBindings(true);

					rightAstRoot = (CompilationUnit) parser.createAST(null);
					rightAstRoot.recordModifications();
					astNeedsRefresh= false;
				}
			}
		}

		return rightAstRoot;
	}

	private void saveRightAstContent() {
		if (rightFile != null) {
			IJavaElement rightJavaFile = JavaCore.create(rightFile);
			if (rightJavaFile instanceof ICompilationUnit) {
				ICompilationUnit rightCu = (ICompilationUnit) rightJavaFile;
				CompilationUnit astRoot = getRightAstRoot();

				String source = null;
				try {
					source = rightCu.getSource();
				} catch (JavaModelException e) {
					Activator.log.error(e);
					return;
				}

				Document document= new Document(source);
				TextEdit edits = astRoot.rewrite(document, rightCu.getJavaProject().getOptions(true));

				try {
					edits.apply(document);
				} catch (MalformedTreeException e) {
					Activator.log.error(e);
					return;
				} catch (BadLocationException e) {
					Activator.log.error(e);
					return;
				}

				String newSource = document.get();

				try {
					rightCu.getBuffer().setContents(newSource);
					rightCu.save(null, true);
					astNeedsRefresh = true;
				} catch (JavaModelException e) {
					Activator.log.error(e);
					return;
				}
			}
		}
	}

	private ASTNode getAstNode(IJavaElement javaElement, CompilationUnit astRoot) {
		ASTNode node = null;
		
		if (astRoot != null) {
			if (javaElement instanceof IType) {
				node = astRoot.findDeclaringNode(((IType) javaElement).getKey());
			} else if (javaElement instanceof IMethod) {
				node = astRoot.findDeclaringNode(((IMethod) javaElement).getKey());
			} else if (javaElement instanceof IField) {
				node = astRoot.findDeclaringNode(((IField) javaElement).getKey());
			}

			if (node == null && javaElement instanceof ISourceReference) {
				try {
					node = NodeFinder.perform(astRoot, ((ISourceReference) javaElement).getSourceRange());
					if (javaElement instanceof IType) {
						if (node instanceof TypeDeclaration) {
							return node;
						} else {
							// Usually we cannot get the first type (e.g. the declaring class) of a compilation unit
							int offset = ((ISourceReference) javaElement).getNameRange().getOffset();
							int length = ((ISourceReference) javaElement).getNameRange().getLength() + 1;
							node = NodeFinder.perform(astRoot, offset, length);
						}
					}
				} catch (JavaModelException e) {
					Activator.log.error(e);
				}
			}
		}

		return node;
	}

	private ASTNode getASTNodeFromString(String source, int kind) {
		try {
			final ASTParser parser = ASTParser.newParser(AST.JLS8);
			parser.setKind(kind);
			parser.setSource(source.toCharArray());
			//parser.setResolveBindings(true);
			return parser.createAST(null);
		} catch (Exception e) {
			Activator.log.error(e);
		}

		return null;
	}

	private void updateType(String leftContent, IType rightType) {
		try {
			ASTNode leftNode = getASTNodeFromString(leftContent, ASTParser.K_CLASS_BODY_DECLARATIONS);

			if (leftNode instanceof TypeDeclaration && ((TypeDeclaration) leftNode).bodyDeclarations().size() > 0) {
				TypeDeclaration leftTypeDeclaration = (TypeDeclaration) ((TypeDeclaration) leftNode).bodyDeclarations().get(0);

				if (leftTypeDeclaration != null) {
					astNeedsRefresh = true;
					CompilationUnit astRoot = getRightAstRoot();
					ASTNode rightNode = getAstNode(rightType, astRoot);

					if (rightNode instanceof TypeDeclaration) {
						boolean needSave = false;
						TypeDeclaration rightTypeDeclaration = (TypeDeclaration) rightNode;
						Javadoc rightJavadoc = rightTypeDeclaration.getJavadoc();

						// Modifiers
						if (leftTypeDeclaration.getModifiers() != rightTypeDeclaration.getModifiers()) {
							rightTypeDeclaration.modifiers().clear();
							List newModifiers = astRoot.getAST().newModifiers(leftTypeDeclaration.getModifiers());
							for (Object object : newModifiers) {
								IExtendedModifier modifier = (IExtendedModifier) object;
								rightTypeDeclaration.modifiers().add(modifier);
								needSave = true;
							}
						}

						ASTMatcher matcher = new ASTMatcher();

						// Extends
						if (leftTypeDeclaration.getSuperclassType() != null) {
							boolean differentSuperclass = rightTypeDeclaration.getSuperclassType() == null;

							if (rightTypeDeclaration.getSuperclassType() != null) {
								if (!rightTypeDeclaration.getSuperclassType().subtreeMatch(matcher, leftTypeDeclaration.getSuperclassType())) {
									rightTypeDeclaration.getSuperclassType().delete();
									differentSuperclass = true;
								}
							}

							if (differentSuperclass) {
								ASTNode copyOfLeftSuperClass = ASTNode.copySubtree(astRoot.getAST(), leftTypeDeclaration.getSuperclassType());
								if (copyOfLeftSuperClass instanceof Type) {
									rightTypeDeclaration.setSuperclassType((Type) copyOfLeftSuperClass);
									needSave = true;
								}
							}
						} else if (rightTypeDeclaration.getSuperclassType() != null) {
							rightTypeDeclaration.getSuperclassType().delete();
							rightTypeDeclaration.setSuperclassType(null);
							needSave = true;
						}

						// Implements
						if (!leftTypeDeclaration.isInterface() && !rightTypeDeclaration.isInterface()) {
							List leftSuperInterfaceTypes = leftTypeDeclaration.superInterfaceTypes();
							List rightSuperInterfaceTypes = rightTypeDeclaration.superInterfaceTypes();

							boolean differentInterfaces = leftSuperInterfaceTypes.size() != rightSuperInterfaceTypes.size();

							if (!differentInterfaces) { // Sizes are the same but interface types may be different
								for (int i = 0; i < leftSuperInterfaceTypes.size(); i++) {
									Type leftInterfaceType = (Type) leftSuperInterfaceTypes.get(i);
									Type rightInterfaceType = (Type) rightSuperInterfaceTypes.get(i);
									if (!leftInterfaceType.subtreeMatch(matcher, rightInterfaceType)) {
										differentInterfaces = true;
										break;
									}
								}
							}

							if (differentInterfaces) {
								rightTypeDeclaration.superInterfaceTypes().clear();
								List copyOfLeftSuperInterfaceTypes = ASTNode.copySubtrees(astRoot.getAST(), leftTypeDeclaration.superInterfaceTypes());
								if (copyOfLeftSuperInterfaceTypes != null) {
									for (Object copyOfLeftSuperInterfaceType : copyOfLeftSuperInterfaceTypes) {
										if (copyOfLeftSuperInterfaceType instanceof Type) {
											rightTypeDeclaration.superInterfaceTypes().add(copyOfLeftSuperInterfaceType);
											needSave = true;
										}
									}
								}
							}
						}
						
						// Generics
						List leftTypeParameters = leftTypeDeclaration.typeParameters();
						List rightTypeParameters = rightTypeDeclaration.typeParameters();
						
						boolean differentTypeParameters = leftTypeParameters.size() != rightTypeParameters.size();
						
						if (!differentTypeParameters) {
							for (int i = 0; i < leftTypeParameters.size(); i++) {
								TypeParameter leftTypeParameter = (TypeParameter) leftTypeParameters.get(i);
								TypeParameter rightTypeParameter = (TypeParameter) rightTypeParameters.get(i);
								if (!leftTypeParameter.subtreeMatch(matcher, rightTypeParameter)) {
									differentTypeParameters = true;
									break;
								}
							}
						}
						
						if (differentTypeParameters) {
							rightTypeDeclaration.typeParameters().clear();
							List copyOfLeftTypeParameters = ASTNode.copySubtrees(astRoot.getAST(), leftTypeDeclaration.typeParameters());
							if (copyOfLeftTypeParameters != null) {
								for (Object copyOfLeftTypeParameter : copyOfLeftTypeParameters) {
									if (copyOfLeftTypeParameter instanceof TypeParameter) {
										rightTypeDeclaration.typeParameters().add(copyOfLeftTypeParameter);
										needSave = true;
									}
								}
							}
						}

						// Javadoc
						if (!createdJavadocNeedsUpdate(rightJavadoc, leftTypeDeclaration.getJavadoc())) {
							// This means we should keep the potentially created javadoc, i.e. the leftTypeDeclaration's javadoc
							// But it has not actually been created yet so we need to create it.
							if (leftTypeDeclaration.getJavadoc() != null) {
								String leftJavadocString = leftTypeDeclaration.getJavadoc().toString().trim();
								if (leftJavadocString.length() > 1) {
									leftJavadocString = leftJavadocString.substring(1, leftJavadocString.length() - 1);
									leftJavadocString = leftJavadocString.replaceAll(Pattern.quote("*"), "");
									leftJavadocString = leftJavadocString.replaceAll("\\r\\n|\\r|\\n", "").replaceAll("\\s+","");
								}

								if (!isBlank(leftJavadocString)) {
									ASTNode replacementJavadoc = ASTNode.copySubtree(astRoot.getAST(), leftTypeDeclaration.getJavadoc());
									if (replacementJavadoc instanceof Javadoc) {
										try {
											if (rightTypeDeclaration.getJavadoc() != null) {
												rightTypeDeclaration.getJavadoc().delete();
											}
											rightTypeDeclaration.setJavadoc((Javadoc) replacementJavadoc);
										} catch (Exception e) {
											Activator.log.error(e);
										}
										needSave = true;
									}
								}
							}
						}

						// Save modified AST content to right file
						if (needSave) {
							saveRightAstContent();
						}
					}
				}
			}
		} catch (Exception e) {
			Activator.log.error(e);
		}
	}

	private boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	private boolean updateCreatedJavadoc(Javadoc oldJavadoc, BodyDeclaration bodyDeclaration, CompilationUnit astRoot) {
		if (bodyDeclaration != null && astRoot != null) {
			Javadoc createdJavadoc = bodyDeclaration.getJavadoc();
			return updateCreatedJavadoc(oldJavadoc, createdJavadoc, bodyDeclaration, astRoot);
		}

		return false;
	}

	private boolean updateCreatedJavadoc(Javadoc oldJavadoc, Javadoc createdJavadoc, BodyDeclaration bodyDeclaration, CompilationUnit astRoot) {
		if (bodyDeclaration != null && astRoot != null) {
			if (createdJavadocNeedsUpdate(oldJavadoc, createdJavadoc)) {

				ASTNode replacementJavadoc = ASTNode.copySubtree(astRoot.getAST(), oldJavadoc);
				if (replacementJavadoc instanceof Javadoc) {
					try {
						if (createdJavadoc != null) {
							createdJavadoc.delete();
						}
						bodyDeclaration.setJavadoc((Javadoc) replacementJavadoc);
					} catch (Exception e) {
						Activator.log.error(e);
					}
					return true;
				}
			}
		}

		return false;
	}

	private boolean createdJavadocNeedsUpdate(Javadoc oldJavadoc, Javadoc createdJavadoc) {
		if (oldJavadoc == null) {
			return false;
		}

		if (createdJavadoc == null) {
			return true;
		}

		String oldJavadocString = oldJavadoc.toString().trim();
		if (oldJavadocString.length() > 1) {
			oldJavadocString = oldJavadocString.substring(1, oldJavadocString.length() - 1);
			oldJavadocString = oldJavadocString.replaceAll(Pattern.quote("*"), "");
		}

		String createdJavadocString = createdJavadoc.toString().trim();
		if (createdJavadocString.length() > 1) {
			createdJavadocString = createdJavadocString.substring(1, createdJavadocString.length() - 1);
			createdJavadocString = createdJavadocString.replaceAll(Pattern.quote("*"), "");
		}

		if (oldJavadocString == null || isBlank(oldJavadocString)) {
			return false;
		}

		if (createdJavadocString == null || isBlank(createdJavadocString)) {
			return true;
		}

		oldJavadocString = oldJavadocString.replaceAll("\\r\\n|\\r|\\n", "").replaceAll("\\s+","");
		createdJavadocString = createdJavadocString.replaceAll("\\r\\n|\\r|\\n", "").replaceAll("\\s+","");

		if (oldJavadocString.contains(createdJavadocString) && oldJavadocString.length() != createdJavadocString.length()) {
			return true;
		}

		return false;
	}

	private void createImports(ICompilationUnit cu, String leftContent) {
		ASTNode cuRoot = getASTNodeFromString("package default;" + leftContent + "public class Default{}", ASTParser.K_COMPILATION_UNIT);
		if (cuRoot instanceof CompilationUnit) {
			for (Object cuImport : ((CompilationUnit) cuRoot).imports()) {
				Name importName = ((ImportDeclaration) cuImport).getName();
				if (importName != null) {
					try {
						((ICompilationUnit) cu).createImport(importName.toString(), null, null);
					} catch (JavaModelException e) {
						Activator.log.error(e);
					}
				}
			}
		}
	}

	private void createPackageDeclaration(ICompilationUnit cu, String leftContent) {
		ASTNode cuRoot = getASTNodeFromString(leftContent + "public class Default{}", ASTParser.K_COMPILATION_UNIT);
		if (cuRoot instanceof CompilationUnit) {
			PackageDeclaration packageDeclaration = ((CompilationUnit) cuRoot).getPackage();
			if (packageDeclaration != null) {
				try {
					((ICompilationUnit) cu).createPackageDeclaration(packageDeclaration.getName().toString(), null);
				} catch (JavaModelException e) {
					Activator.log.error(e);
				}
			}
		}
	}

	private String replaceLeftBody(String leftContent, IMethod rightMethod) {
		try {
			String originalLeftContent = new String (leftContent);

			int beginIndex = leftContent.indexOf("*/");
			String leftJavadoc = leftContent.substring(0, beginIndex + 2);
			leftContent = leftContent.substring(beginIndex + 2, leftContent.length());

			Pattern pattern = Pattern.compile("\\{(.*)\\}", Pattern.DOTALL);

			Matcher leftMatcher = pattern.matcher(leftContent);
			String leftBody = null;
			if (leftMatcher.find()) {
				leftBody = leftMatcher.group().trim();
				if (leftBody.length() <= 2) {
					return originalLeftContent;
				}
				leftBody = leftBody.substring(1, leftBody.length() - 1);
			}

			if (leftBody == null || !isBlank(leftBody)) { // null means we don't want a body anymore (method became abstract)
				return originalLeftContent;
			}

			// else leftBody is blank

			astNeedsRefresh = true;
			CompilationUnit astRoot = getRightAstRoot();
			ASTNode rightElementNode = getAstNode(rightMethod, astRoot);
			Block rightBlock = null;
			if (rightElementNode instanceof MethodDeclaration) {
				MethodDeclaration rightMethodDeclaration = (MethodDeclaration) rightElementNode;
				rightBlock = rightMethodDeclaration.getBody();
			}

			if (rightBlock == null) {
				return originalLeftContent;
			}

			int offset = -1;
			int length = -1;
			try {
				offset = rightMethod.getSourceRange().getOffset();
				length = rightMethod.getSourceRange().getLength();

				ISourceRange javadocRange = rightMethod.getJavadocRange();
				if (javadocRange != null) {
					offset = javadocRange.getOffset() + javadocRange.getLength();
					length = length - javadocRange.getLength();
				}
			} catch (JavaModelException e) {
				Activator.log.error(e);
			}

			if (offset > -1 && length > -1) {
				Document rightDocument = null;
				String methodSource  = null;
				try {
					rightDocument = new Document(rightMethod.getCompilationUnit().getSource());
					methodSource = rightDocument.get(offset, length).trim();
				} catch (JavaModelException e) {
					Activator.log.error(e);
				} catch (BadLocationException e) {
					Activator.log.error(e);
				}

				if (methodSource != null) {
					Matcher rightMatcher = pattern.matcher(methodSource);
					if (!rightMatcher.find()) {
						return originalLeftContent;
					} else {
						String rightBody = rightMatcher.group().trim();
						if (rightBody.length() <= 2) {
							return originalLeftContent;
						}
						rightBody = rightBody.substring(1, rightBody.length() - 1);

						if (isBlank(rightBody) ) {
							return originalLeftContent;
						}

						return leftJavadoc + leftMatcher.replaceAll(rightMatcher.group(0));
					}
				}
			}
		} catch (Exception e) {
			Activator.log.error(e);
		}


		return "";
	}
}
