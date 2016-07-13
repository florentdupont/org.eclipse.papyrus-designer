/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
 * Created on 09/06/2008
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.BlockComment;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.CompilationUnit;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.ImportDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.LineComment;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.Node;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.PackageDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.TypeParameter;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.AnnotationDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.AnnotationMemberDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.BodyDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.ClassOrInterfaceDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.ConstructorDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.EmptyMemberDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.EmptyTypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.EnumConstantDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.EnumDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.FieldDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.InitializerDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.JavadocComment;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.MethodDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.Parameter;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.TypeDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.VariableDeclarator;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.VariableDeclaratorId;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.ArrayAccessExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.ArrayCreationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.ArrayInitializerExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.AssignExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.BinaryExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.BooleanLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.CastExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.CharLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.ClassExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.ConditionalExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.DoubleLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.EnclosedExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.FieldAccessExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.InstanceOfExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.IntegerLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.IntegerLiteralMinValueExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.LongLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.LongLiteralMinValueExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.MarkerAnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.MemberValuePair;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.MethodCallExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.NameExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.NormalAnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.NullLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.ObjectCreationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.QualifiedNameExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.SingleMemberAnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.StringLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.SuperExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.ThisExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.UnaryExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.expr.VariableDeclarationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.AssertStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.BlockStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.BreakStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.CatchClause;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.ContinueStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.DoStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.EmptyStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.ExplicitConstructorInvocationStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.ExpressionStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.ForStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.ForeachStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.IfStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.LabeledStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.ReturnStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.Statement;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.SwitchEntryStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.SwitchStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.SynchronizedStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.ThrowStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.TryStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.TypeDeclarationStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.stmt.WhileStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.ClassOrInterfaceType;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.PrimitiveType;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.ReferenceType;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.Type;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.VoidType;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.WildcardType;

/**
 * @author Julio Vilmar Gesser
 */
public class VoidVisitorAdapter<A> implements VoidVisitor<A> {

	@Override
	public void visit(AnnotationDeclaration n, A arg) {
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, arg);
		}
		if (n.getAnnotations() != null) {
			for (AnnotationExpr a : n.getAnnotations()) {
				a.accept(this, arg);
			}
		}
		if (n.getMembers() != null) {
			for (BodyDeclaration member : n.getMembers()) {
				member.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(AnnotationMemberDeclaration n, A arg) {
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, arg);
		}
		if (n.getAnnotations() != null) {
			for (AnnotationExpr a : n.getAnnotations()) {
				a.accept(this, arg);
			}
		}
		n.getType().accept(this, arg);
		if (n.getDefaultValue() != null) {
			n.getDefaultValue().accept(this, arg);
		}
	}

	@Override
	public void visit(ArrayAccessExpr n, A arg) {
		n.getName().accept(this, arg);
		n.getIndex().accept(this, arg);
	}

	@Override
	public void visit(ArrayCreationExpr n, A arg) {
		n.getType().accept(this, arg);
		if (n.getDimensions() != null) {
			for (Expression dim : n.getDimensions()) {
				dim.accept(this, arg);
			}
		} else {
			n.getInitializer().accept(this, arg);
		}
	}

	@Override
	public void visit(ArrayInitializerExpr n, A arg) {
		if (n.getValues() != null) {
			for (Expression expr : n.getValues()) {
				expr.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(AssertStmt n, A arg) {
		n.getCheck().accept(this, arg);
		if (n.getMessage() != null) {
			n.getMessage().accept(this, arg);
		}
	}

	@Override
	public void visit(AssignExpr n, A arg) {
		n.getTarget().accept(this, arg);
		n.getValue().accept(this, arg);
	}

	@Override
	public void visit(BinaryExpr n, A arg) {
		n.getLeft().accept(this, arg);
		n.getRight().accept(this, arg);
	}

	@Override
	public void visit(BlockComment n, A arg) {
	}

	@Override
	public void visit(BlockStmt n, A arg) {
		if (n.getStmts() != null) {
			for (Statement s : n.getStmts()) {
				s.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(BooleanLiteralExpr n, A arg) {
	}

	@Override
	public void visit(BreakStmt n, A arg) {
	}

	@Override
	public void visit(CastExpr n, A arg) {
		n.getType().accept(this, arg);
		n.getExpr().accept(this, arg);
	}

	@Override
	public void visit(CatchClause n, A arg) {
		n.getExcept().accept(this, arg);
		n.getCatchBlock().accept(this, arg);
	}

	@Override
	public void visit(CharLiteralExpr n, A arg) {
	}

	@Override
	public void visit(ClassExpr n, A arg) {
		n.getType().accept(this, arg);
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration n, A arg) {
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, arg);
		}
		if (n.getAnnotations() != null) {
			for (AnnotationExpr a : n.getAnnotations()) {
				a.accept(this, arg);
			}
		}
		if (n.getTypeParameters() != null) {
			for (TypeParameter t : n.getTypeParameters()) {
				t.accept(this, arg);
			}
		}
		if (n.getExtends() != null) {
			for (ClassOrInterfaceType c : n.getExtends()) {
				c.accept(this, arg);
			}
		}

		if (n.getImplements() != null) {
			for (ClassOrInterfaceType c : n.getImplements()) {
				c.accept(this, arg);
			}
		}
		if (n.getMembers() != null) {
			for (BodyDeclaration member : n.getMembers()) {
				member.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(ClassOrInterfaceType n, A arg) {
		if (n.getScope() != null) {
			n.getScope().accept(this, arg);
		}
		if (n.getTypeArgs() != null) {
			for (Type t : n.getTypeArgs()) {
				t.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(CompilationUnit n, A arg) {
		if (n.getPakage() != null) {
			n.getPakage().accept(this, arg);
		}
		if (n.getImports() != null) {
			for (ImportDeclaration i : n.getImports()) {
				i.accept(this, arg);
			}
		}
		if (n.getTypes() != null) {
			for (TypeDeclaration typeDeclaration : n.getTypes()) {
				typeDeclaration.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(ConditionalExpr n, A arg) {
		n.getCondition().accept(this, arg);
		n.getThenExpr().accept(this, arg);
		n.getElseExpr().accept(this, arg);
	}

	@Override
	public void visit(ConstructorDeclaration n, A arg) {
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, arg);
		}
		if (n.getAnnotations() != null) {
			for (AnnotationExpr a : n.getAnnotations()) {
				a.accept(this, arg);
			}
		}
		if (n.getTypeParameters() != null) {
			for (TypeParameter t : n.getTypeParameters()) {
				t.accept(this, arg);
			}
		}
		if (n.getParameters() != null) {
			for (Parameter p : n.getParameters()) {
				p.accept(this, arg);
			}
		}
		if (n.getThrows() != null) {
			for (NameExpr name : n.getThrows()) {
				name.accept(this, arg);
			}
		}
		n.getBlock().accept(this, arg);
	}

	@Override
	public void visit(ContinueStmt n, A arg) {
	}

	@Override
	public void visit(DoStmt n, A arg) {
		n.getBody().accept(this, arg);
		n.getCondition().accept(this, arg);
	}

	@Override
	public void visit(DoubleLiteralExpr n, A arg) {
	}

	@Override
	public void visit(EmptyMemberDeclaration n, A arg) {
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, arg);
		}
	}

	@Override
	public void visit(EmptyStmt n, A arg) {
	}

	@Override
	public void visit(EmptyTypeDeclaration n, A arg) {
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, arg);
		}
	}

	@Override
	public void visit(EnclosedExpr n, A arg) {
		n.getInner().accept(this, arg);
	}

	@Override
	public void visit(EnumConstantDeclaration n, A arg) {
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, arg);
		}
		if (n.getAnnotations() != null) {
			for (AnnotationExpr a : n.getAnnotations()) {
				a.accept(this, arg);
			}
		}
		if (n.getArgs() != null) {
			for (Expression e : n.getArgs()) {
				e.accept(this, arg);
			}
		}
		if (n.getClassBody() != null) {
			for (BodyDeclaration member : n.getClassBody()) {
				member.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(EnumDeclaration n, A arg) {
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, arg);
		}
		if (n.getAnnotations() != null) {
			for (AnnotationExpr a : n.getAnnotations()) {
				a.accept(this, arg);
			}
		}
		if (n.getImplements() != null) {
			for (ClassOrInterfaceType c : n.getImplements()) {
				c.accept(this, arg);
			}
		}
		if (n.getEntries() != null) {
			for (EnumConstantDeclaration e : n.getEntries()) {
				e.accept(this, arg);
			}
		}
		if (n.getMembers() != null) {
			for (BodyDeclaration member : n.getMembers()) {
				member.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(ExplicitConstructorInvocationStmt n, A arg) {
		if (!n.isThis()) {
			if (n.getExpr() != null) {
				n.getExpr().accept(this, arg);
			}
		}
		if (n.getTypeArgs() != null) {
			for (Type t : n.getTypeArgs()) {
				t.accept(this, arg);
			}
		}
		if (n.getArgs() != null) {
			for (Expression e : n.getArgs()) {
				e.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(ExpressionStmt n, A arg) {
		n.getExpression().accept(this, arg);
	}

	@Override
	public void visit(FieldAccessExpr n, A arg) {
		n.getScope().accept(this, arg);
	}

	@Override
	public void visit(FieldDeclaration n, A arg) {
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, arg);
		}
		if (n.getAnnotations() != null) {
			for (AnnotationExpr a : n.getAnnotations()) {
				a.accept(this, arg);
			}
		}
		n.getType().accept(this, arg);
		for (VariableDeclarator var : n.getVariables()) {
			var.accept(this, arg);
		}
	}

	@Override
	public void visit(ForeachStmt n, A arg) {
		n.getVariable().accept(this, arg);
		n.getIterable().accept(this, arg);
		n.getBody().accept(this, arg);
	}

	@Override
	public void visit(ForStmt n, A arg) {
		if (n.getInit() != null) {
			for (Expression e : n.getInit()) {
				e.accept(this, arg);
			}
		}
		if (n.getCompare() != null) {
			n.getCompare().accept(this, arg);
		}
		if (n.getUpdate() != null) {
			for (Expression e : n.getUpdate()) {
				e.accept(this, arg);
			}
		}
		n.getBody().accept(this, arg);
	}

	@Override
	public void visit(IfStmt n, A arg) {
		n.getCondition().accept(this, arg);
		n.getThenStmt().accept(this, arg);
		if (n.getElseStmt() != null) {
			n.getElseStmt().accept(this, arg);
		}
	}

	@Override
	public void visit(ImportDeclaration n, A arg) {
		n.getName().accept(this, arg);
	}

	@Override
	public void visit(InitializerDeclaration n, A arg) {
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, arg);
		}
		n.getBlock().accept(this, arg);
	}

	@Override
	public void visit(InstanceOfExpr n, A arg) {
		n.getExpr().accept(this, arg);
		n.getType().accept(this, arg);
	}

	@Override
	public void visit(IntegerLiteralExpr n, A arg) {
	}

	@Override
	public void visit(IntegerLiteralMinValueExpr n, A arg) {
	}

	@Override
	public void visit(JavadocComment n, A arg) {
	}

	@Override
	public void visit(LabeledStmt n, A arg) {
		n.getStmt().accept(this, arg);
	}

	@Override
	public void visit(LineComment n, A arg) {
	}

	@Override
	public void visit(LongLiteralExpr n, A arg) {
	}

	@Override
	public void visit(LongLiteralMinValueExpr n, A arg) {
	}

	@Override
	public void visit(MarkerAnnotationExpr n, A arg) {
		n.getName().accept(this, arg);
	}

	@Override
	public void visit(MemberValuePair n, A arg) {
		n.getValue().accept(this, arg);
	}

	@Override
	public void visit(MethodCallExpr n, A arg) {
		if (n.getScope() != null) {
			n.getScope().accept(this, arg);
		}
		if (n.getTypeArgs() != null) {
			for (Type t : n.getTypeArgs()) {
				t.accept(this, arg);
			}
		}
		if (n.getArgs() != null) {
			for (Expression e : n.getArgs()) {
				e.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(MethodDeclaration n, A arg) {
		if (n.getJavaDoc() != null) {
			n.getJavaDoc().accept(this, arg);
		}
		if (n.getAnnotations() != null) {
			for (AnnotationExpr a : n.getAnnotations()) {
				a.accept(this, arg);
			}
		}
		if (n.getTypeParameters() != null) {
			for (TypeParameter t : n.getTypeParameters()) {
				t.accept(this, arg);
			}
		}
		n.getType().accept(this, arg);
		if (n.getParameters() != null) {
			for (Parameter p : n.getParameters()) {
				p.accept(this, arg);
			}
		}
		if (n.getThrows() != null) {
			for (NameExpr name : n.getThrows()) {
				name.accept(this, arg);
			}
		}
		if (n.getBody() != null) {
			n.getBody().accept(this, arg);
		}
	}

	@Override
	public void visit(NameExpr n, A arg) {
	}

	@Override
	public void visit(Node n, A arg) {
		throw new IllegalStateException(n.getClass().getName());
	}

	@Override
	public void visit(NormalAnnotationExpr n, A arg) {
		n.getName().accept(this, arg);
		if (n.getPairs() != null) {
			for (MemberValuePair m : n.getPairs()) {
				m.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(NullLiteralExpr n, A arg) {
	}

	@Override
	public void visit(ObjectCreationExpr n, A arg) {
		if (n.getScope() != null) {
			n.getScope().accept(this, arg);
		}
		if (n.getTypeArgs() != null) {
			for (Type t : n.getTypeArgs()) {
				t.accept(this, arg);
			}
		}
		n.getType().accept(this, arg);
		if (n.getArgs() != null) {
			for (Expression e : n.getArgs()) {
				e.accept(this, arg);
			}
		}
		if (n.getAnonymousClassBody() != null) {
			for (BodyDeclaration member : n.getAnonymousClassBody()) {
				member.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(PackageDeclaration n, A arg) {
		if (n.getAnnotations() != null) {
			for (AnnotationExpr a : n.getAnnotations()) {
				a.accept(this, arg);
			}
		}
		n.getName().accept(this, arg);
	}

	@Override
	public void visit(Parameter n, A arg) {
		if (n.getAnnotations() != null) {
			for (AnnotationExpr a : n.getAnnotations()) {
				a.accept(this, arg);
			}
		}
		n.getType().accept(this, arg);
		n.getId().accept(this, arg);
	}

	@Override
	public void visit(PrimitiveType n, A arg) {
	}

	@Override
	public void visit(QualifiedNameExpr n, A arg) {
		n.getQualifier().accept(this, arg);
	}

	@Override
	public void visit(ReferenceType n, A arg) {
		n.getType().accept(this, arg);
	}

	@Override
	public void visit(ReturnStmt n, A arg) {
		if (n.getExpr() != null) {
			n.getExpr().accept(this, arg);
		}
	}

	@Override
	public void visit(SingleMemberAnnotationExpr n, A arg) {
		n.getName().accept(this, arg);
		n.getMemberValue().accept(this, arg);
	}

	@Override
	public void visit(StringLiteralExpr n, A arg) {
	}

	@Override
	public void visit(SuperExpr n, A arg) {
		if (n.getClassExpr() != null) {
			n.getClassExpr().accept(this, arg);
		}
	}

	@Override
	public void visit(SwitchEntryStmt n, A arg) {
		if (n.getLabel() != null) {
			n.getLabel().accept(this, arg);
		}
		if (n.getStmts() != null) {
			for (Statement s : n.getStmts()) {
				s.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(SwitchStmt n, A arg) {
		n.getSelector().accept(this, arg);
		if (n.getEntries() != null) {
			for (SwitchEntryStmt e : n.getEntries()) {
				e.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(SynchronizedStmt n, A arg) {
		n.getExpr().accept(this, arg);
		n.getBlock().accept(this, arg);

	}

	@Override
	public void visit(ThisExpr n, A arg) {
		if (n.getClassExpr() != null) {
			n.getClassExpr().accept(this, arg);
		}
	}

	@Override
	public void visit(ThrowStmt n, A arg) {
		n.getExpr().accept(this, arg);
	}

	@Override
	public void visit(TryStmt n, A arg) {
		n.getTryBlock().accept(this, arg);
		if (n.getCatchs() != null) {
			for (CatchClause c : n.getCatchs()) {
				c.accept(this, arg);
			}
		}
		if (n.getFinallyBlock() != null) {
			n.getFinallyBlock().accept(this, arg);
		}
	}

	@Override
	public void visit(TypeDeclarationStmt n, A arg) {
		n.getTypeDeclaration().accept(this, arg);
	}

	@Override
	public void visit(TypeParameter n, A arg) {
		if (n.getTypeBound() != null) {
			for (ClassOrInterfaceType c : n.getTypeBound()) {
				c.accept(this, arg);
			}
		}
	}

	@Override
	public void visit(UnaryExpr n, A arg) {
		n.getExpr().accept(this, arg);
	}

	@Override
	public void visit(VariableDeclarationExpr n, A arg) {
		if (n.getAnnotations() != null) {
			for (AnnotationExpr a : n.getAnnotations()) {
				a.accept(this, arg);
			}
		}
		n.getType().accept(this, arg);
		for (VariableDeclarator v : n.getVars()) {
			v.accept(this, arg);
		}
	}

	@Override
	public void visit(VariableDeclarator n, A arg) {
		n.getId().accept(this, arg);
		if (n.getInit() != null) {
			n.getInit().accept(this, arg);
		}
	}

	@Override
	public void visit(VariableDeclaratorId n, A arg) {
	}

	@Override
	public void visit(VoidType n, A arg) {
	}

	@Override
	public void visit(WhileStmt n, A arg) {
		n.getCondition().accept(this, arg);
		n.getBody().accept(this, arg);
	}

	@Override
	public void visit(WildcardType n, A arg) {
		if (n.getExtends() != null) {
			n.getExtends().accept(this, arg);
		}
		if (n.getSuper() != null) {
			n.getSuper().accept(this, arg);
		}
	}
}
