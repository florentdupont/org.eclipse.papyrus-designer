/**
 *
 */
package org.eclipse.papyrus.designer.languages.java.reverse.umlparser;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.comments.BlockComment;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.CompilationUnit;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.ImportDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.comments.LineComment;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.Node;
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
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.ArrayAccessExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.ArrayCreationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.ArrayInitializerExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.AssignExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.BinaryExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.BooleanLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.CastExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.CharLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.ClassExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.ConditionalExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.DoubleLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.EnclosedExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.FieldAccessExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.InstanceOfExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.IntegerLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.IntegerLiteralMinValueExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.LambdaExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.LongLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.LongLiteralMinValueExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.MarkerAnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.MemberValuePair;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.MethodCallExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.MethodReferenceExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.NameExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.NormalAnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.NullLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.ObjectCreationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.QualifiedNameExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.SingleMemberAnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.StringLiteralExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.SuperExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.ThisExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.TypeExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.UnaryExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.VariableDeclarationExpr;
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
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.ClassOrInterfaceType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.IntersectionType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.PrimitiveType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.ReferenceType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.UnionType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.UnknownType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.VoidType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.WildcardType;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor;

/**
 * A base class used to do switching.
 * The class must be subclassed, and methods corresponding to needed cases should be subclassed.
 *
 * @author dumoulin
 *
 */
public class SwitchVisitor<A> implements VoidVisitor<A> {

	public void doSwitch(Node n, A arg) {
		n.accept(this, arg);
	}


//	@Override
//	public void visit(Node n, A arg) {
//		// TODO Auto-generated method stub
//
//	}


	@Override
	public void visit(CompilationUnit n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(PackageDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ImportDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(TypeParameter n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(LineComment n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(BlockComment n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ClassOrInterfaceDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(EnumDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(EmptyTypeDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(EnumConstantDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(AnnotationDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(AnnotationMemberDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(FieldDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(VariableDeclarator n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(VariableDeclaratorId n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ConstructorDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(MethodDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(Parameter n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(EmptyMemberDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(InitializerDeclaration n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(JavadocComment n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ClassOrInterfaceType n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(PrimitiveType n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ReferenceType n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(VoidType n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(WildcardType n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ArrayAccessExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ArrayCreationExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ArrayInitializerExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(AssignExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(BinaryExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(CastExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ClassExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ConditionalExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(EnclosedExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(FieldAccessExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(InstanceOfExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(StringLiteralExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(IntegerLiteralExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(LongLiteralExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(IntegerLiteralMinValueExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(LongLiteralMinValueExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(CharLiteralExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(DoubleLiteralExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(BooleanLiteralExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(NullLiteralExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(MethodCallExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(NameExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ObjectCreationExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(QualifiedNameExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ThisExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(SuperExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(UnaryExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(VariableDeclarationExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(MarkerAnnotationExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(SingleMemberAnnotationExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(NormalAnnotationExpr n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(MemberValuePair n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ExplicitConstructorInvocationStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(TypeDeclarationStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(AssertStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(BlockStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(LabeledStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(EmptyStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ExpressionStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(SwitchStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(SwitchEntryStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(BreakStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ReturnStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(IfStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(WhileStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ContinueStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(DoStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ForeachStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ForStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(ThrowStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(SynchronizedStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(TryStmt n, A arg) {
		// TODO Auto-generated method stub

	}


	@Override
	public void visit(CatchClause n, A arg) {
		// TODO Auto-generated method stub

	}


	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor#visit(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.MultiTypeParameter, java.lang.Object)
	 *
	 * @param n
	 * @param arg
	 */
	@Override
	public void visit(MultiTypeParameter n, A arg) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor#visit(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.IntersectionType, java.lang.Object)
	 *
	 * @param n
	 * @param arg
	 */
	@Override
	public void visit(IntersectionType n, A arg) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor#visit(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.UnionType, java.lang.Object)
	 *
	 * @param n
	 * @param arg
	 */
	@Override
	public void visit(UnionType n, A arg) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor#visit(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.type.UnknownType, java.lang.Object)
	 *
	 * @param n
	 * @param arg
	 */
	@Override
	public void visit(UnknownType n, A arg) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor#visit(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.LambdaExpr, java.lang.Object)
	 *
	 * @param n
	 * @param arg
	 */
	@Override
	public void visit(LambdaExpr n, A arg) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor#visit(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.MethodReferenceExpr, java.lang.Object)
	 *
	 * @param n
	 * @param arg
	 */
	@Override
	public void visit(MethodReferenceExpr n, A arg) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * @see org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.visitor.VoidVisitor#visit(org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.TypeExpr, java.lang.Object)
	 *
	 * @param n
	 * @param arg
	 */
	@Override
	public void visit(TypeExpr n, A arg) {
		// TODO Auto-generated method stub
		
	}

}
