/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
  */
/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2015 The JavaParser Team.
 *
 * This file is part of JavaParser.
 * 
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License 
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */

package org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.comments.BlockComment;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.CompilationUnit;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.ImportDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.comments.LineComment;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.PackageDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.TypeParameter;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.AnnotationDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.AnnotationMemberDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.ConstructorDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.EmptyMemberDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.EmptyTypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.EnumConstantDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.EnumDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.FieldDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.InitializerDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.comments.JavadocComment;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.MethodDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.MultiTypeParameter;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.Parameter;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.VariableDeclarator;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.VariableDeclaratorId;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.*;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.AssertStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.BlockStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.BreakStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.CatchClause;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.ContinueStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.DoStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.EmptyStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.ExpressionStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.ForStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.ForeachStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.IfStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.LabeledStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.ReturnStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.SwitchEntryStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.SwitchStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.SynchronizedStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.ThrowStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.TryStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.TypeDeclarationStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.WhileStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.*;

/**
 * A visitor that has a return value.
 * 
 * @author Julio Vilmar Gesser
 */
public interface GenericVisitor<R, A> {

	//- Compilation Unit ----------------------------------

	public R visit(CompilationUnit n, A arg);

	public R visit(PackageDeclaration n, A arg);

	public R visit(ImportDeclaration n, A arg);

	public R visit(TypeParameter n, A arg);

	public R visit(LineComment n, A arg);

	public R visit(BlockComment n, A arg);

	//- Body ----------------------------------------------

	public R visit(ClassOrInterfaceDeclaration n, A arg);

	public R visit(EnumDeclaration n, A arg);

	public R visit(EmptyTypeDeclaration n, A arg);

	public R visit(EnumConstantDeclaration n, A arg);

	public R visit(AnnotationDeclaration n, A arg);

	public R visit(AnnotationMemberDeclaration n, A arg);

	public R visit(FieldDeclaration n, A arg);

	public R visit(VariableDeclarator n, A arg);

	public R visit(VariableDeclaratorId n, A arg);

	public R visit(ConstructorDeclaration n, A arg);

	public R visit(MethodDeclaration n, A arg);

	public R visit(Parameter n, A arg);
	
	public R visit(MultiTypeParameter n, A arg);

	public R visit(EmptyMemberDeclaration n, A arg);

	public R visit(InitializerDeclaration n, A arg);

	public R visit(JavadocComment n, A arg);

	//- Type ----------------------------------------------

	public R visit(ClassOrInterfaceType n, A arg);

	public R visit(PrimitiveType n, A arg);

	public R visit(ReferenceType n, A arg);

    public R visit(IntersectionType n, A arg);

    public R visit(UnionType n, A arg);

	public R visit(VoidType n, A arg);

	public R visit(WildcardType n, A arg);

	public R visit(UnknownType n, A arg);

	//- Expression ----------------------------------------

	public R visit(ArrayAccessExpr n, A arg);

	public R visit(ArrayCreationExpr n, A arg);

	public R visit(ArrayInitializerExpr n, A arg);

	public R visit(AssignExpr n, A arg);

	public R visit(BinaryExpr n, A arg);

	public R visit(CastExpr n, A arg);

	public R visit(ClassExpr n, A arg);

	public R visit(ConditionalExpr n, A arg);

	public R visit(EnclosedExpr n, A arg);

	public R visit(FieldAccessExpr n, A arg);

	public R visit(InstanceOfExpr n, A arg);

	public R visit(StringLiteralExpr n, A arg);

	public R visit(IntegerLiteralExpr n, A arg);

	public R visit(LongLiteralExpr n, A arg);

	public R visit(IntegerLiteralMinValueExpr n, A arg);

	public R visit(LongLiteralMinValueExpr n, A arg);

	public R visit(CharLiteralExpr n, A arg);

	public R visit(DoubleLiteralExpr n, A arg);

	public R visit(BooleanLiteralExpr n, A arg);

	public R visit(NullLiteralExpr n, A arg);

	public R visit(MethodCallExpr n, A arg);

	public R visit(NameExpr n, A arg);

	public R visit(ObjectCreationExpr n, A arg);

	public R visit(QualifiedNameExpr n, A arg);

	public R visit(ThisExpr n, A arg);

	public R visit(SuperExpr n, A arg);

	public R visit(UnaryExpr n, A arg);

	public R visit(VariableDeclarationExpr n, A arg);

	public R visit(MarkerAnnotationExpr n, A arg);

	public R visit(SingleMemberAnnotationExpr n, A arg);

	public R visit(NormalAnnotationExpr n, A arg);

	public R visit(MemberValuePair n, A arg);

	//- Statements ----------------------------------------

	public R visit(ExplicitConstructorInvocationStmt n, A arg);

	public R visit(TypeDeclarationStmt n, A arg);

	public R visit(AssertStmt n, A arg);

	public R visit(BlockStmt n, A arg);

	public R visit(LabeledStmt n, A arg);

	public R visit(EmptyStmt n, A arg);

	public R visit(ExpressionStmt n, A arg);

	public R visit(SwitchStmt n, A arg);

	public R visit(SwitchEntryStmt n, A arg);

	public R visit(BreakStmt n, A arg);

	public R visit(ReturnStmt n, A arg);

	public R visit(IfStmt n, A arg);

	public R visit(WhileStmt n, A arg);

	public R visit(ContinueStmt n, A arg);

	public R visit(DoStmt n, A arg);

	public R visit(ForeachStmt n, A arg);

	public R visit(ForStmt n, A arg);

	public R visit(ThrowStmt n, A arg);

	public R visit(SynchronizedStmt n, A arg);

	public R visit(TryStmt n, A arg);

	public R visit(CatchClause n, A arg);

    public R visit(LambdaExpr n, A arg);

    public R visit(MethodReferenceExpr n, A arg);

    public R visit(TypeExpr n, A arg);
}
