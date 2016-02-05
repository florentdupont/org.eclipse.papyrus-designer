/*
 * Copyright (C) 2007 J�lio Vilmar Gesser.
 *
 * This file is part of Java 1.5 parser and Abstract Syntax Tree.
 *
 * Java 1.5 parser and Abstract Syntax Tree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Java 1.5 parser and Abstract Syntax Tree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Java 1.5 parser and Abstract Syntax Tree.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on 05/10/2006
 */
package org.eclipse.papyrus.designer.languages.java.reverse.ast.visitor;

import java.util.Iterator;
import java.util.List;

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
import org.eclipse.papyrus.designer.languages.java.reverse.ast.body.ModifierSet;
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

public final class DumpVisitor implements VoidVisitor<Object> {

	private class SourcePrinter {

		private int level = 0;

		private boolean indented = false;

		private final StringBuilder buf = new StringBuilder();

		public void indent() {
			level++;
		}

		public void unindent() {
			level--;
		}

		private void makeIndent() {
			for (int i = 0; i < level; i++) {
				buf.append("    ");
			}
		}

		public void print(String arg) {
			if (!indented) {
				makeIndent();
				indented = true;
			}
			buf.append(arg);
		}

		public void printLn(String arg) {
			print(arg);
			printLn();
		}

		public void printLn() {
			buf.append("\n");
			indented = false;
		}

		public String getSource() {
			return buf.toString();
		}

		@Override
		public String toString() {
			return getSource();
		}
	}

	private final SourcePrinter printer = new SourcePrinter();

	public String getSource() {
		return printer.getSource();
	}

	private void printModifiers(int modifiers) {
		if (ModifierSet.isPrivate(modifiers)) {
			printer.print("private ");
		}
		if (ModifierSet.isProtected(modifiers)) {
			printer.print("protected ");
		}
		if (ModifierSet.isPublic(modifiers)) {
			printer.print("public ");
		}
		if (ModifierSet.isAbstract(modifiers)) {
			printer.print("abstract ");
		}
		if (ModifierSet.isStatic(modifiers)) {
			printer.print("static ");
		}
		if (ModifierSet.isFinal(modifiers)) {
			printer.print("final ");
		}
		if (ModifierSet.isNative(modifiers)) {
			printer.print("native ");
		}
		if (ModifierSet.isStrictfp(modifiers)) {
			printer.print("strictfp ");
		}
		if (ModifierSet.isSynchronized(modifiers)) {
			printer.print("synchronized ");
		}
		if (ModifierSet.isTransient(modifiers)) {
			printer.print("transient ");
		}
		if (ModifierSet.isVolatile(modifiers)) {
			printer.print("volatile ");
		}
	}

	private void printMembers(List<BodyDeclaration> members, Object arg) {
		for (BodyDeclaration member : members) {
			printer.printLn();
			member.accept(this, arg);
			printer.printLn();
		}
	}

	private void printMemberAnnotations(List<AnnotationExpr> annotations, Object arg) {
		if (annotations != null) {
			for (AnnotationExpr a : annotations) {
				a.accept(this, arg);
				printer.printLn();
			}
		}
	}

	private void printAnnotations(List<AnnotationExpr> annotations, Object arg) {
		if (annotations != null) {
			for (AnnotationExpr a : annotations) {
				a.accept(this, arg);
				printer.print(" ");
			}
		}
	}

	private void printTypeArgs(List<Type> args, Object arg) {
		if (args != null) {
			printer.print("<");
			for (Iterator<Type> i = args.iterator(); i.hasNext();) {
				Type t = i.next();
				t.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
			printer.print(">");
		}
	}

	private void printTypeParameters(List<TypeParameter> args, Object arg) {
		if (args != null) {
			printer.print("<");
			for (Iterator<TypeParameter> i = args.iterator(); i.hasNext();) {
				TypeParameter t = i.next();
				t.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
			printer.print(">");
		}
	}

	private void printArguments(List<Expression> args, Object arg) {
		printer.print("(");
		if (args != null) {
			for (Iterator<Expression> i = args.iterator(); i.hasNext();) {
				Expression e = i.next();
				e.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(")");
	}

	private void printJavadoc(JavadocComment javadoc, Object arg) {
		if (javadoc != null) {
			javadoc.accept(this, arg);
		}
	}

	@Override
	public void visit(CompilationUnit n, Object arg) {
		if (n.getPakage() != null) {
			n.getPakage().accept(this, arg);
		}
		if (n.getImports() != null) {
			for (ImportDeclaration i : n.getImports()) {
				i.accept(this, arg);
			}
			printer.printLn();
		}
		if (n.getTypes() != null) {
			for (Iterator<TypeDeclaration> i = n.getTypes().iterator(); i.hasNext();) {
				i.next().accept(this, arg);
				printer.printLn();
				if (i.hasNext()) {
					printer.printLn();
				}
			}
		}
	}

	@Override
	public void visit(PackageDeclaration n, Object arg) {
		printAnnotations(n.getAnnotations(), arg);
		printer.print("package ");
		n.getName().accept(this, arg);
		printer.printLn(";");
		printer.printLn();
	}

	@Override
	public void visit(NameExpr n, Object arg) {
		printer.print(n.getName());
	}

	@Override
	public void visit(QualifiedNameExpr n, Object arg) {
		n.getQualifier().accept(this, arg);
		printer.print(".");
		printer.print(n.getName());
	}

	@Override
	public void visit(ImportDeclaration n, Object arg) {
		printer.print("import ");
		if (n.isStatic()) {
			printer.print("static ");
		}
		n.getName().accept(this, arg);
		if (n.isAsterisk()) {
			printer.print(".*");
		}
		printer.printLn(";");
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration n, Object arg) {
		printJavadoc(n.getJavaDoc(), arg);
		printMemberAnnotations(n.getAnnotations(), arg);
		printModifiers(n.getModifiers());

		if (n.isInterface()) {
			printer.print("interface ");
		} else {
			printer.print("class ");
		}

		printer.print(n.getName());

		printTypeParameters(n.getTypeParameters(), arg);

		if (n.getExtends() != null) {
			printer.print(" extends ");
			for (Iterator<ClassOrInterfaceType> i = n.getExtends().iterator(); i.hasNext();) {
				ClassOrInterfaceType c = i.next();
				c.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}

		if (n.getImplements() != null) {
			printer.print(" implements ");
			for (Iterator<ClassOrInterfaceType> i = n.getImplements().iterator(); i.hasNext();) {
				ClassOrInterfaceType c = i.next();
				c.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}

		printer.printLn(" {");
		printer.indent();
		if (n.getMembers() != null) {
			printMembers(n.getMembers(), arg);
		}
		printer.unindent();
		printer.print("}");
	}

	@Override
	public void visit(EmptyTypeDeclaration n, Object arg) {
		printJavadoc(n.getJavaDoc(), arg);
		printer.print(";");
	}

	@Override
	public void visit(JavadocComment n, Object arg) {
		printer.print("/**");
		printer.print(n.getContent());
		printer.printLn("*/");
	}

	@Override
	public void visit(ClassOrInterfaceType n, Object arg) {
		if (n.getScope() != null) {
			n.getScope().accept(this, arg);
			printer.print(".");
		}
		printer.print(n.getName());
		printTypeArgs(n.getTypeArgs(), arg);
	}

	@Override
	public void visit(TypeParameter n, Object arg) {
		printer.print(n.getName());
		if (n.getTypeBound() != null) {
			printer.print(" extends ");
			for (Iterator<ClassOrInterfaceType> i = n.getTypeBound().iterator(); i.hasNext();) {
				ClassOrInterfaceType c = i.next();
				c.accept(this, arg);
				if (i.hasNext()) {
					printer.print(" & ");
				}
			}
		}
	}

	@Override
	public void visit(PrimitiveType n, Object arg) {
		switch (n.getType()) {
		case Boolean:
			printer.print("boolean");
			break;
		case Byte:
			printer.print("byte");
			break;
		case Char:
			printer.print("char");
			break;
		case Double:
			printer.print("double");
			break;
		case Float:
			printer.print("float");
			break;
		case Int:
			printer.print("int");
			break;
		case Long:
			printer.print("long");
			break;
		case Short:
			printer.print("short");
			break;
		}
	}

	@Override
	public void visit(ReferenceType n, Object arg) {
		n.getType().accept(this, arg);
		for (int i = 0; i < n.getArrayCount(); i++) {
			printer.print("[]");
		}
	}

	@Override
	public void visit(WildcardType n, Object arg) {
		printer.print("?");
		if (n.getExtends() != null) {
			printer.print(" extends ");
			n.getExtends().accept(this, arg);
		}
		if (n.getSuper() != null) {
			printer.print(" super ");
			n.getSuper().accept(this, arg);
		}
	}

	@Override
	public void visit(FieldDeclaration n, Object arg) {
		printJavadoc(n.getJavaDoc(), arg);
		printMemberAnnotations(n.getAnnotations(), arg);
		printModifiers(n.getModifiers());
		n.getType().accept(this, arg);

		printer.print(" ");
		for (Iterator<VariableDeclarator> i = n.getVariables().iterator(); i.hasNext();) {
			VariableDeclarator var = i.next();
			var.accept(this, arg);
			if (i.hasNext()) {
				printer.print(", ");
			}
		}

		printer.print(";");
	}

	@Override
	public void visit(VariableDeclarator n, Object arg) {
		n.getId().accept(this, arg);
		if (n.getInit() != null) {
			printer.print(" = ");
			n.getInit().accept(this, arg);
		}
	}

	@Override
	public void visit(VariableDeclaratorId n, Object arg) {
		printer.print(n.getName());
		for (int i = 0; i < n.getArrayCount(); i++) {
			printer.print("[]");
		}
	}

	@Override
	public void visit(ArrayInitializerExpr n, Object arg) {
		printer.print("{");
		if (n.getValues() != null) {
			printer.print(" ");
			for (Iterator<Expression> i = n.getValues().iterator(); i.hasNext();) {
				Expression expr = i.next();
				expr.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
			printer.print(" ");
		}
		printer.print("}");
	}

	@Override
	public void visit(VoidType n, Object arg) {
		printer.print("void");
	}

	@Override
	public void visit(ArrayAccessExpr n, Object arg) {
		n.getName().accept(this, arg);
		printer.print("[");
		n.getIndex().accept(this, arg);
		printer.print("]");
	}

	@Override
	public void visit(ArrayCreationExpr n, Object arg) {
		printer.print("new ");
		n.getType().accept(this, arg);

		if (n.getDimensions() != null) {
			for (Expression dim : n.getDimensions()) {
				printer.print("[");
				dim.accept(this, arg);
				printer.print("]");
			}
			for (int i = 0; i < n.getArrayCount(); i++) {
				printer.print("[]");
			}
		} else {
			for (int i = 0; i < n.getArrayCount(); i++) {
				printer.print("[]");
			}
			printer.print(" ");
			n.getInitializer().accept(this, arg);
		}
	}

	@Override
	public void visit(AssignExpr n, Object arg) {
		n.getTarget().accept(this, arg);
		printer.print(" ");
		switch (n.getOperator()) {
		case assign:
			printer.print("=");
			break;
		case and:
			printer.print("&=");
			break;
		case or:
			printer.print("|=");
			break;
		case xor:
			printer.print("^=");
			break;
		case plus:
			printer.print("+=");
			break;
		case minus:
			printer.print("-=");
			break;
		case rem:
			printer.print("%=");
			break;
		case slash:
			printer.print("/=");
			break;
		case star:
			printer.print("*=");
			break;
		case lShift:
			printer.print("<<=");
			break;
		case rSignedShift:
			printer.print(">>=");
			break;
		case rUnsignedShift:
			printer.print(">>>=");
			break;
		}
		printer.print(" ");
		n.getValue().accept(this, arg);
	}

	@Override
	public void visit(BinaryExpr n, Object arg) {
		n.getLeft().accept(this, arg);
		printer.print(" ");
		switch (n.getOperator()) {
		case or:
			printer.print("||");
			break;
		case and:
			printer.print("&&");
			break;
		case binOr:
			printer.print("|");
			break;
		case binAnd:
			printer.print("&");
			break;
		case xor:
			printer.print("^");
			break;
		case equals:
			printer.print("==");
			break;
		case notEquals:
			printer.print("!=");
			break;
		case less:
			printer.print("<");
			break;
		case greater:
			printer.print(">");
			break;
		case lessEquals:
			printer.print("<=");
			break;
		case greaterEquals:
			printer.print(">=");
			break;
		case lShift:
			printer.print("<<");
			break;
		case rSignedShift:
			printer.print(">>");
			break;
		case rUnsignedShift:
			printer.print(">>>");
			break;
		case plus:
			printer.print("+");
			break;
		case minus:
			printer.print("-");
			break;
		case times:
			printer.print("*");
			break;
		case divide:
			printer.print("/");
			break;
		case remainder:
			printer.print("%");
			break;
		}
		printer.print(" ");
		n.getRight().accept(this, arg);
	}

	@Override
	public void visit(CastExpr n, Object arg) {
		printer.print("(");
		n.getType().accept(this, arg);
		printer.print(") ");
		n.getExpr().accept(this, arg);
	}

	@Override
	public void visit(ClassExpr n, Object arg) {
		n.getType().accept(this, arg);
		printer.print(".class");
	}

	@Override
	public void visit(ConditionalExpr n, Object arg) {
		n.getCondition().accept(this, arg);
		printer.print(" ? ");
		n.getThenExpr().accept(this, arg);
		printer.print(" : ");
		n.getElseExpr().accept(this, arg);
	}

	@Override
	public void visit(EnclosedExpr n, Object arg) {
		printer.print("(");
		n.getInner().accept(this, arg);
		printer.print(")");
	}

	@Override
	public void visit(FieldAccessExpr n, Object arg) {
		n.getScope().accept(this, arg);
		printer.print(".");
		printer.print(n.getField());
	}

	@Override
	public void visit(InstanceOfExpr n, Object arg) {
		n.getExpr().accept(this, arg);
		printer.print(" instanceof ");
		n.getType().accept(this, arg);
	}

	@Override
	public void visit(CharLiteralExpr n, Object arg) {
		printer.print("'");
		printer.print(n.getValue());
		printer.print("'");
	}

	@Override
	public void visit(DoubleLiteralExpr n, Object arg) {
		printer.print(n.getValue());
	}

	@Override
	public void visit(IntegerLiteralExpr n, Object arg) {
		printer.print(n.getValue());
	}

	@Override
	public void visit(LongLiteralExpr n, Object arg) {
		printer.print(n.getValue());
	}

	@Override
	public void visit(IntegerLiteralMinValueExpr n, Object arg) {
		printer.print(n.getValue());
	}

	@Override
	public void visit(LongLiteralMinValueExpr n, Object arg) {
		printer.print(n.getValue());
	}

	@Override
	public void visit(StringLiteralExpr n, Object arg) {
		printer.print("\"");
		printer.print(n.getValue());
		printer.print("\"");
	}

	@Override
	public void visit(BooleanLiteralExpr n, Object arg) {
		printer.print(String.valueOf(n.getValue()));
	}

	@Override
	public void visit(NullLiteralExpr n, Object arg) {
		printer.print("null");
	}

	@Override
	public void visit(ThisExpr n, Object arg) {
		if (n.getClassExpr() != null) {
			n.getClassExpr().accept(this, arg);
			printer.print(".");
		}
		printer.print("this");
	}

	@Override
	public void visit(SuperExpr n, Object arg) {
		if (n.getClassExpr() != null) {
			n.getClassExpr().accept(this, arg);
			printer.print(".");
		}
		printer.print("super");
	}

	@Override
	public void visit(MethodCallExpr n, Object arg) {
		if (n.getScope() != null) {
			n.getScope().accept(this, arg);
			printer.print(".");
		}
		printTypeArgs(n.getTypeArgs(), arg);
		printer.print(n.getName());
		printArguments(n.getArgs(), arg);
	}

	@Override
	public void visit(ObjectCreationExpr n, Object arg) {
		if (n.getScope() != null) {
			n.getScope().accept(this, arg);
			printer.print(".");
		}

		printer.print("new ");

		printTypeArgs(n.getTypeArgs(), arg);
		n.getType().accept(this, arg);

		printArguments(n.getArgs(), arg);

		if (n.getAnonymousClassBody() != null) {
			printer.printLn(" {");
			printer.indent();
			printMembers(n.getAnonymousClassBody(), arg);
			printer.unindent();
			printer.print("}");
		}
	}

	@Override
	public void visit(UnaryExpr n, Object arg) {
		switch (n.getOperator()) {
		case positive:
			printer.print("+");
			break;
		case negative:
			printer.print("-");
			break;
		case inverse:
			printer.print("~");
			break;
		case not:
			printer.print("!");
			break;
		case preIncrement:
			printer.print("++");
			break;
		case preDecrement:
			printer.print("--");
			break;
		}

		n.getExpr().accept(this, arg);

		switch (n.getOperator()) {
		case posIncrement:
			printer.print("++");
			break;
		case posDecrement:
			printer.print("--");
			break;
		}
	}

	@Override
	public void visit(ConstructorDeclaration n, Object arg) {
		printJavadoc(n.getJavaDoc(), arg);
		printMemberAnnotations(n.getAnnotations(), arg);
		printModifiers(n.getModifiers());

		printTypeParameters(n.getTypeParameters(), arg);
		if (n.getTypeParameters() != null) {
			printer.print(" ");
		}
		printer.print(n.getName());

		printer.print("(");
		if (n.getParameters() != null) {
			for (Iterator<Parameter> i = n.getParameters().iterator(); i.hasNext();) {
				Parameter p = i.next();
				p.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(")");

		if (n.getThrows() != null) {
			printer.print(" throws ");
			for (Iterator<NameExpr> i = n.getThrows().iterator(); i.hasNext();) {
				NameExpr name = i.next();
				name.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(" ");
		n.getBlock().accept(this, arg);
	}

	@Override
	public void visit(MethodDeclaration n, Object arg) {
		printJavadoc(n.getJavaDoc(), arg);
		printMemberAnnotations(n.getAnnotations(), arg);
		printModifiers(n.getModifiers());

		printTypeParameters(n.getTypeParameters(), arg);
		if (n.getTypeParameters() != null) {
			printer.print(" ");
		}

		n.getType().accept(this, arg);
		printer.print(" ");
		printer.print(n.getName());

		printer.print("(");
		if (n.getParameters() != null) {
			for (Iterator<Parameter> i = n.getParameters().iterator(); i.hasNext();) {
				Parameter p = i.next();
				p.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(")");

		for (int i = 0; i < n.getArrayCount(); i++) {
			printer.print("[]");
		}

		if (n.getThrows() != null) {
			printer.print(" throws ");
			for (Iterator<NameExpr> i = n.getThrows().iterator(); i.hasNext();) {
				NameExpr name = i.next();
				name.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		if (n.getBody() == null) {
			printer.print(";");
		} else {
			printer.print(" ");
			n.getBody().accept(this, arg);
		}
	}

	@Override
	public void visit(Parameter n, Object arg) {
		printAnnotations(n.getAnnotations(), arg);
		printModifiers(n.getModifiers());

		n.getType().accept(this, arg);
		if (n.isVarArgs()) {
			printer.print("...");
		}
		printer.print(" ");
		n.getId().accept(this, arg);
	}

	@Override
	public void visit(ExplicitConstructorInvocationStmt n, Object arg) {
		if (n.isThis()) {
			printTypeArgs(n.getTypeArgs(), arg);
			printer.print("this");
		} else {
			if (n.getExpr() != null) {
				n.getExpr().accept(this, arg);
				printer.print(".");
			}
			printTypeArgs(n.getTypeArgs(), arg);
			printer.print("super");
		}
		printArguments(n.getArgs(), arg);
		printer.print(";");
	}

	@Override
	public void visit(VariableDeclarationExpr n, Object arg) {
		printAnnotations(n.getAnnotations(), arg);
		printModifiers(n.getModifiers());

		n.getType().accept(this, arg);
		printer.print(" ");

		for (Iterator<VariableDeclarator> i = n.getVars().iterator(); i.hasNext();) {
			VariableDeclarator v = i.next();
			v.accept(this, arg);
			if (i.hasNext()) {
				printer.print(", ");
			}
		}
	}

	@Override
	public void visit(TypeDeclarationStmt n, Object arg) {
		n.getTypeDeclaration().accept(this, arg);
	}

	@Override
	public void visit(AssertStmt n, Object arg) {
		printer.print("assert ");
		n.getCheck().accept(this, arg);
		if (n.getMessage() != null) {
			printer.print(" : ");
			n.getMessage().accept(this, arg);
		}
		printer.print(";");
	}

	@Override
	public void visit(BlockStmt n, Object arg) {
		printer.printLn("{");
		if (n.getStmts() != null) {
			printer.indent();
			for (Statement s : n.getStmts()) {
				s.accept(this, arg);
				printer.printLn();
			}
			printer.unindent();
		}
		printer.print("}");

	}

	@Override
	public void visit(LabeledStmt n, Object arg) {
		printer.print(n.getLabel());
		printer.print(": ");
		n.getStmt().accept(this, arg);
	}

	@Override
	public void visit(EmptyStmt n, Object arg) {
		printer.print(";");
	}

	@Override
	public void visit(ExpressionStmt n, Object arg) {
		n.getExpression().accept(this, arg);
		printer.print(";");
	}

	@Override
	public void visit(SwitchStmt n, Object arg) {
		printer.print("switch(");
		n.getSelector().accept(this, arg);
		printer.printLn(") {");
		if (n.getEntries() != null) {
			printer.indent();
			for (SwitchEntryStmt e : n.getEntries()) {
				e.accept(this, arg);
			}
			printer.unindent();
		}
		printer.print("}");

	}

	@Override
	public void visit(SwitchEntryStmt n, Object arg) {
		if (n.getLabel() != null) {
			printer.print("case ");
			n.getLabel().accept(this, arg);
			printer.print(":");
		} else {
			printer.print("default:");
		}
		printer.printLn();
		printer.indent();
		if (n.getStmts() != null) {
			for (Statement s : n.getStmts()) {
				s.accept(this, arg);
				printer.printLn();
			}
		}
		printer.unindent();
	}

	@Override
	public void visit(BreakStmt n, Object arg) {
		printer.print("break");
		if (n.getId() != null) {
			printer.print(" ");
			printer.print(n.getId());
		}
		printer.print(";");
	}

	@Override
	public void visit(ReturnStmt n, Object arg) {
		printer.print("return");
		if (n.getExpr() != null) {
			printer.print(" ");
			n.getExpr().accept(this, arg);
		}
		printer.print(";");
	}

	@Override
	public void visit(EnumDeclaration n, Object arg) {
		printJavadoc(n.getJavaDoc(), arg);
		printMemberAnnotations(n.getAnnotations(), arg);
		printModifiers(n.getModifiers());

		printer.print("enum ");
		printer.print(n.getName());

		if (n.getImplements() != null) {
			printer.print(" implements ");
			for (Iterator<ClassOrInterfaceType> i = n.getImplements().iterator(); i.hasNext();) {
				ClassOrInterfaceType c = i.next();
				c.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}

		printer.printLn(" {");
		printer.indent();
		if (n.getEntries() != null) {
			printer.printLn();
			for (Iterator<EnumConstantDeclaration> i = n.getEntries().iterator(); i.hasNext();) {
				EnumConstantDeclaration e = i.next();
				e.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		if (n.getMembers() != null) {
			printer.printLn(";");
			printMembers(n.getMembers(), arg);
		} else {
			if (n.getEntries() != null) {
				printer.printLn();
			}
		}
		printer.unindent();
		printer.print("}");
	}

	@Override
	public void visit(EnumConstantDeclaration n, Object arg) {
		printJavadoc(n.getJavaDoc(), arg);
		printMemberAnnotations(n.getAnnotations(), arg);
		printer.print(n.getName());

		if (n.getArgs() != null) {
			printArguments(n.getArgs(), arg);
		}

		if (n.getClassBody() != null) {
			printer.printLn(" {");
			printer.indent();
			printMembers(n.getClassBody(), arg);
			printer.unindent();
			printer.printLn("}");
		}
	}

	@Override
	public void visit(EmptyMemberDeclaration n, Object arg) {
		printJavadoc(n.getJavaDoc(), arg);
		printer.print(";");
	}

	@Override
	public void visit(InitializerDeclaration n, Object arg) {
		printJavadoc(n.getJavaDoc(), arg);
		if (n.isStatic()) {
			printer.print("static ");
		}
		n.getBlock().accept(this, arg);
	}

	@Override
	public void visit(IfStmt n, Object arg) {
		printer.print("if (");
		n.getCondition().accept(this, arg);
		printer.print(") ");
		n.getThenStmt().accept(this, arg);
		if (n.getElseStmt() != null) {
			printer.print(" else ");
			n.getElseStmt().accept(this, arg);
		}
	}

	@Override
	public void visit(WhileStmt n, Object arg) {
		printer.print("while (");
		n.getCondition().accept(this, arg);
		printer.print(") ");
		n.getBody().accept(this, arg);
	}

	@Override
	public void visit(ContinueStmt n, Object arg) {
		printer.print("continue");
		if (n.getId() != null) {
			printer.print(" ");
			printer.print(n.getId());
		}
		printer.print(";");
	}

	@Override
	public void visit(DoStmt n, Object arg) {
		printer.print("do ");
		n.getBody().accept(this, arg);
		printer.print(" while (");
		n.getCondition().accept(this, arg);
		printer.print(");");
	}

	@Override
	public void visit(ForeachStmt n, Object arg) {
		printer.print("for (");
		n.getVariable().accept(this, arg);
		printer.print(" : ");
		n.getIterable().accept(this, arg);
		printer.print(") ");
		n.getBody().accept(this, arg);
	}

	@Override
	public void visit(ForStmt n, Object arg) {
		printer.print("for (");
		if (n.getInit() != null) {
			for (Iterator<Expression> i = n.getInit().iterator(); i.hasNext();) {
				Expression e = i.next();
				e.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print("; ");
		if (n.getCompare() != null) {
			n.getCompare().accept(this, arg);
		}
		printer.print("; ");
		if (n.getUpdate() != null) {
			for (Iterator<Expression> i = n.getUpdate().iterator(); i.hasNext();) {
				Expression e = i.next();
				e.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(") ");
		n.getBody().accept(this, arg);
	}

	@Override
	public void visit(ThrowStmt n, Object arg) {
		printer.print("throw ");
		n.getExpr().accept(this, arg);
		printer.print(";");
	}

	@Override
	public void visit(SynchronizedStmt n, Object arg) {
		printer.print("synchronized (");
		n.getExpr().accept(this, arg);
		printer.print(") ");
		n.getBlock().accept(this, arg);
	}

	@Override
	public void visit(TryStmt n, Object arg) {
		printer.print("try ");
		n.getTryBlock().accept(this, arg);
		if (n.getCatchs() != null) {
			for (CatchClause c : n.getCatchs()) {
				c.accept(this, arg);
			}
		}
		if (n.getFinallyBlock() != null) {
			printer.print(" finally ");
			n.getFinallyBlock().accept(this, arg);
		}
	}

	@Override
	public void visit(CatchClause n, Object arg) {
		printer.print(" catch (");
		n.getExcept().accept(this, arg);
		printer.print(") ");
		n.getCatchBlock().accept(this, arg);

	}

	@Override
	public void visit(AnnotationDeclaration n, Object arg) {
		printJavadoc(n.getJavaDoc(), arg);
		printMemberAnnotations(n.getAnnotations(), arg);
		printModifiers(n.getModifiers());

		printer.print("@interface ");
		printer.print(n.getName());
		printer.printLn(" {");
		printer.indent();
		if (n.getMembers() != null) {
			printMembers(n.getMembers(), arg);
		}
		printer.unindent();
		printer.print("}");
	}

	@Override
	public void visit(AnnotationMemberDeclaration n, Object arg) {
		printJavadoc(n.getJavaDoc(), arg);
		printMemberAnnotations(n.getAnnotations(), arg);
		printModifiers(n.getModifiers());

		n.getType().accept(this, arg);
		printer.print(" ");
		printer.print(n.getName());
		printer.print("()");
		if (n.getDefaultValue() != null) {
			printer.print(" default ");
			n.getDefaultValue().accept(this, arg);
		}
		printer.print(";");
	}

	@Override
	public void visit(MarkerAnnotationExpr n, Object arg) {
		printer.print("@");
		n.getName().accept(this, arg);
	}

	@Override
	public void visit(SingleMemberAnnotationExpr n, Object arg) {
		printer.print("@");
		n.getName().accept(this, arg);
		printer.print("(");
		n.getMemberValue().accept(this, arg);
		printer.print(")");
	}

	@Override
	public void visit(NormalAnnotationExpr n, Object arg) {
		printer.print("@");
		n.getName().accept(this, arg);
		printer.print("(");
		if (n.getPairs() != null) {
			for (Iterator<MemberValuePair> i = n.getPairs().iterator(); i.hasNext();) {
				MemberValuePair m = i.next();
				m.accept(this, arg);
				if (i.hasNext()) {
					printer.print(", ");
				}
			}
		}
		printer.print(")");
	}

	@Override
	public void visit(MemberValuePair n, Object arg) {
		printer.print(n.getName());
		printer.print(" = ");
		n.getValue().accept(this, arg);
	}

	@Override
	public void visit(LineComment n, Object arg) {
		printer.print("//");
		printer.printLn(n.getContent());
	}

	@Override
	public void visit(BlockComment n, Object arg) {
		printer.print("/*");
		printer.print(n.getContent());
		printer.printLn("*/");
	}

	@Override
	public void visit(Node n, Object arg) {
		throw new IllegalStateException(n.getClass().getName());
	}

}
