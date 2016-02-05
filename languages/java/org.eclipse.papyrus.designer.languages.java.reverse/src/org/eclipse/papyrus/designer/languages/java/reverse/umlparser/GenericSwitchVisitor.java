/**
 *
 */
package org.eclipse.papyrus.designer.languages.java.reverse.umlparser;

import org.eclipse.papyrus.designer.languages.java.reverse.ast.BlockComment;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.CompilationUnit;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.ImportDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.LineComment;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.Node;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.PackageDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.TypeParameter;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.AnnotationDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.AnnotationMemberDeclaration;
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
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.VariableDeclarator;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.VariableDeclaratorId;
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
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.VoidType;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.type.WildcardType;
import org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor.GenericVisitor;

/**
 * A base class used to do switching.
 * The class must be subclassed, and methods corresponding to needed cases should be subclassed.
 *
 * @author dumoulin
 *
 */
public class GenericSwitchVisitor<R, A> implements GenericVisitor<R, A> {

	/**
	 * Do switching according to the real type of the node.
	 *
	 * @param n
	 * @param arg
	 * @return
	 */
	public R doSwitch(Node n, A arg) {
		return n.accept(this, arg);
	}

	@Override
	public R visit(Node n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R visit(CompilationUnit n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R visit(PackageDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ImportDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(TypeParameter n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(LineComment n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(BlockComment n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ClassOrInterfaceDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(EnumDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(EmptyTypeDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(EnumConstantDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(AnnotationDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(AnnotationMemberDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(FieldDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(VariableDeclarator n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(VariableDeclaratorId n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ConstructorDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(MethodDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(Parameter n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(EmptyMemberDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(InitializerDeclaration n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(JavadocComment n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ClassOrInterfaceType n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(PrimitiveType n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ReferenceType n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(VoidType n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(WildcardType n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ArrayAccessExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ArrayCreationExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ArrayInitializerExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(AssignExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(BinaryExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(CastExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ClassExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ConditionalExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(EnclosedExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(FieldAccessExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(InstanceOfExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(StringLiteralExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(IntegerLiteralExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(LongLiteralExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(IntegerLiteralMinValueExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(LongLiteralMinValueExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(CharLiteralExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(DoubleLiteralExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(BooleanLiteralExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(NullLiteralExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(MethodCallExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(NameExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ObjectCreationExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(QualifiedNameExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ThisExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(SuperExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(UnaryExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(VariableDeclarationExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(MarkerAnnotationExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(SingleMemberAnnotationExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(NormalAnnotationExpr n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(MemberValuePair n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ExplicitConstructorInvocationStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(TypeDeclarationStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(AssertStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(BlockStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(LabeledStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(EmptyStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ExpressionStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(SwitchStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(SwitchEntryStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(BreakStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ReturnStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(IfStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(WhileStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ContinueStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(DoStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ForeachStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ForStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(ThrowStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(SynchronizedStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(TryStmt n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public R visit(CatchClause n, A arg) {
		// TODO Auto-generated method stub
		return null;
	}

}
