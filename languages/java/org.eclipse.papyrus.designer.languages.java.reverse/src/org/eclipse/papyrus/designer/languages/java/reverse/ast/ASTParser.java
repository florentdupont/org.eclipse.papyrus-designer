/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
  */
/*
 * Copyright (C) 2008 J�lio Vilmar Gesser.
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
package org.eclipse.papyrus.designer.languages.java.reverse.ast;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

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
 * <p>
 * This class was generated automatically by javacc, do not edit.
 * </p>
 *
 * @author J�lio Vilmar Gesser
 */
final class ASTParser implements ASTParserConstants {

	void reset(InputStream in, String encoding) {
		ReInit(in, encoding);
		token_source.clearComments();
	}

	private List add(List list, Object obj) {
		if (list == null) {
			list = new LinkedList();
		}
		list.add(obj);
		return list;
	}

	private List add(int pos, List list, Object obj) {
		if (list == null) {
			list = new LinkedList();
		}
		list.add(pos, obj);
		return list;
	}

	private class Modifier {

		final int modifiers;

		final List annotations;

		final int beginLine;

		final int beginColumn;

		public Modifier(int beginLine, int beginColumn, int modifiers, List annotations) {
			this.beginLine = beginLine;
			this.beginColumn = beginColumn;
			this.modifiers = modifiers;
			this.annotations = annotations;
		}
	}

	public int addModifier(int modifiers, int mod, Token token) throws ParseException {
		if ((ModifierSet.hasModifier(modifiers, mod))) {
			throwParseException(token, "Duplicated modifier");
		}
		return ModifierSet.addModifier(modifiers, mod);
	}

	private void pushJavadoc() {
		token_source.pushJavadoc();
	}

	private JavadocComment popJavadoc() {
		return token_source.popJavadoc();
	}

	private List<Comment> getComments() {
		return token_source.getComments();
	}

	private void throwParseException(Token token, String message) throws ParseException {
		StringBuilder buf = new StringBuilder();
		buf.append(message);
		buf.append(": \"");
		buf.append(token.image);
		buf.append("\" at line ");
		buf.append(token.beginLine);
		buf.append(", column ");
		buf.append(token.beginColumn);
		ParseException e = new ParseException(buf.toString());
		e.currentToken = token;
		throw e;
	}

	static final class GTToken extends Token {

		int realKind = ASTParserConstants.GT;

		GTToken(int kind, String image) {
			this.kind = kind;
			this.image = image;
		}

		public static Token newToken(int kind, String image) {
			return new GTToken(kind, image);
		}
	}

	/*****************************************
	 * THE JAVA LANGUAGE GRAMMAR STARTS HERE *
	 *****************************************/

	/*
	 * Program structuring syntax follows.
	 */
	final public CompilationUnit CompilationUnit() throws ParseException {
		PackageDeclaration pakage = null;
		List imports = null;
		ImportDeclaration in = null;
		List types = null;
		TypeDeclaration tn = null;
		int line = -1;
		int column = 0;
		if (jj_2_1(2147483647)) {
			pakage = PackageDeclaration();
			line = pakage.getBeginLine();
			column = pakage.getBeginColumn();
		} else {
			;
		}
		label_1: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case IMPORT:
				;
				break;
			default:
				jj_la1[0] = jj_gen;
				break label_1;
			}
			in = ImportDeclaration();
			if (line == -1) {
				line = in.getBeginLine();
				column = in.getBeginColumn();
			}
			imports = add(imports, in);
		}
		label_2: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case ABSTRACT:
			case CLASS:
			case ENUM:
			case FINAL:
			case INTERFACE:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOLATILE:
			case SEMICOLON:
			case AT:
				;
				break;
			default:
				jj_la1[1] = jj_gen;
				break label_2;
			}
			tn = TypeDeclaration();
			if (line == -1) {
				line = tn.getBeginLine();
				column = tn.getBeginColumn();
			}
			types = add(types, tn);
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case 0:
			jj_consume_token(0);
			break;
		case 128:
			jj_consume_token(128);
			break;
		default:
			jj_la1[2] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return new CompilationUnit(line == -1 ? 0 : line, column, token.endLine, token.endColumn, pakage, imports, types, getComments());
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public PackageDeclaration PackageDeclaration() throws ParseException {
		List annotations = null;
		AnnotationExpr ann;
		NameExpr name;
		int line;
		int column;
		label_3: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case AT:
				;
				break;
			default:
				jj_la1[3] = jj_gen;
				break label_3;
			}
			ann = Annotation();
			annotations = add(annotations, ann);
		}
		jj_consume_token(PACKAGE);
		line = token.beginLine;
		column = token.beginColumn;
		name = Name();
		jj_consume_token(SEMICOLON);
		{
			if (true) {
				return new PackageDeclaration(line, column, token.endLine, token.endColumn, annotations, name);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public ImportDeclaration ImportDeclaration() throws ParseException {
		NameExpr name;
		boolean isStatic = false;
		boolean isAsterisk = false;
		int line;
		int column;
		jj_consume_token(IMPORT);
		line = token.beginLine;
		column = token.beginColumn;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case STATIC:
			jj_consume_token(STATIC);
			isStatic = true;
			break;
		default:
			jj_la1[4] = jj_gen;
			;
		}
		name = Name();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case DOT:
			jj_consume_token(DOT);
			jj_consume_token(STAR);
			isAsterisk = true;
			break;
		default:
			jj_la1[5] = jj_gen;
			;
		}
		jj_consume_token(SEMICOLON);
		{
			if (true) {
				return new ImportDeclaration(line, column, token.endLine, token.endColumn, name, isStatic, isAsterisk);
			}
		}
		throw new Error("Missing return statement in function");
	}

	/*
	 * Modifiers. We match all modifiers in a single rule to reduce the chances of
	 * syntax errors for simple modifier mistakes. It will also enable us to give
	 * better error messages.
	 */
	final public Modifier Modifiers() throws ParseException {
		int beginLine = -1;
		int beginColumn = -1;
		int modifiers = 0;
		List annotations = null;
		AnnotationExpr ann;
		label_4: while (true) {
			if (jj_2_2(2)) {
				;
			} else {
				break label_4;
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case PUBLIC:
				jj_consume_token(PUBLIC);
				modifiers = addModifier(modifiers, ModifierSet.PUBLIC, token);
				if (beginLine == -1) {
					beginLine = token.beginLine;
					beginColumn = token.beginColumn;
				}
				break;
			case STATIC:
				jj_consume_token(STATIC);
				modifiers = addModifier(modifiers, ModifierSet.STATIC, token);
				if (beginLine == -1) {
					beginLine = token.beginLine;
					beginColumn = token.beginColumn;
				}
				break;
			case PROTECTED:
				jj_consume_token(PROTECTED);
				modifiers = addModifier(modifiers, ModifierSet.PROTECTED, token);
				if (beginLine == -1) {
					beginLine = token.beginLine;
					beginColumn = token.beginColumn;
				}
				break;
			case PRIVATE:
				jj_consume_token(PRIVATE);
				modifiers = addModifier(modifiers, ModifierSet.PRIVATE, token);
				if (beginLine == -1) {
					beginLine = token.beginLine;
					beginColumn = token.beginColumn;
				}
				break;
			case FINAL:
				jj_consume_token(FINAL);
				modifiers = addModifier(modifiers, ModifierSet.FINAL, token);
				if (beginLine == -1) {
					beginLine = token.beginLine;
					beginColumn = token.beginColumn;
				}
				break;
			case ABSTRACT:
				jj_consume_token(ABSTRACT);
				modifiers = addModifier(modifiers, ModifierSet.ABSTRACT, token);
				if (beginLine == -1) {
					beginLine = token.beginLine;
					beginColumn = token.beginColumn;
				}
				break;
			case SYNCHRONIZED:
				jj_consume_token(SYNCHRONIZED);
				modifiers = addModifier(modifiers, ModifierSet.SYNCHRONIZED, token);
				if (beginLine == -1) {
					beginLine = token.beginLine;
					beginColumn = token.beginColumn;
				}
				break;
			case NATIVE:
				jj_consume_token(NATIVE);
				modifiers = addModifier(modifiers, ModifierSet.NATIVE, token);
				if (beginLine == -1) {
					beginLine = token.beginLine;
					beginColumn = token.beginColumn;
				}
				break;
			case TRANSIENT:
				jj_consume_token(TRANSIENT);
				modifiers = addModifier(modifiers, ModifierSet.TRANSIENT, token);
				if (beginLine == -1) {
					beginLine = token.beginLine;
					beginColumn = token.beginColumn;
				}
				break;
			case VOLATILE:
				jj_consume_token(VOLATILE);
				modifiers = addModifier(modifiers, ModifierSet.VOLATILE, token);
				if (beginLine == -1) {
					beginLine = token.beginLine;
					beginColumn = token.beginColumn;
				}
				break;
			case STRICTFP:
				jj_consume_token(STRICTFP);
				modifiers = addModifier(modifiers, ModifierSet.STRICTFP, token);
				if (beginLine == -1) {
					beginLine = token.beginLine;
					beginColumn = token.beginColumn;
				}
				break;
			case AT:
				ann = Annotation();
				annotations = add(annotations, ann);
				if (beginLine == -1) {
					beginLine = ann.getBeginLine();
					beginColumn = ann.getBeginColumn();
				}
				break;
			default:
				jj_la1[6] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		{
			if (true) {
				return new Modifier(beginLine, beginColumn, modifiers, annotations);
			}
		}
		throw new Error("Missing return statement in function");
	}

	/*
	 * Declaration syntax follows.
	 */
	final public TypeDeclaration TypeDeclaration() throws ParseException {
		Modifier modifier;
		TypeDeclaration ret;
		pushJavadoc();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case SEMICOLON:
			jj_consume_token(SEMICOLON);
			ret = new EmptyTypeDeclaration(token.beginLine, token.beginColumn, token.endLine, token.endColumn, popJavadoc());
			break;
		case ABSTRACT:
		case CLASS:
		case ENUM:
		case FINAL:
		case INTERFACE:
		case NATIVE:
		case PRIVATE:
		case PROTECTED:
		case PUBLIC:
		case STATIC:
		case STRICTFP:
		case SYNCHRONIZED:
		case TRANSIENT:
		case VOLATILE:
		case AT:
			modifier = Modifiers();
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case CLASS:
			case INTERFACE:
				ret = ClassOrInterfaceDeclaration(modifier);
				break;
			case ENUM:
				ret = EnumDeclaration(modifier);
				break;
			case AT:
				ret = AnnotationTypeDeclaration(modifier);
				break;
			default:
				jj_la1[7] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			break;
		default:
			jj_la1[8] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public ClassOrInterfaceDeclaration ClassOrInterfaceDeclaration(Modifier modifier) throws ParseException {
		boolean isInterface = false;
		String name;
		List typePar = null;
		List extList = null;
		List impList = null;
		List members;
		int line = modifier.beginLine;
		int column = modifier.beginColumn;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case CLASS:
			jj_consume_token(CLASS);
			break;
		case INTERFACE:
			jj_consume_token(INTERFACE);
			isInterface = true;
			break;
		default:
			jj_la1[9] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		if (line == -1) {
			line = token.beginLine;
			column = token.beginColumn;
		}
		jj_consume_token(IDENTIFIER);
		name = token.image;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case LT:
			typePar = TypeParameters();
			typePar.remove(0);
			break;
		default:
			jj_la1[10] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case EXTENDS:
			extList = ExtendsList(isInterface);
			break;
		default:
			jj_la1[11] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case IMPLEMENTS:
			impList = ImplementsList(isInterface);
			break;
		default:
			jj_la1[12] = jj_gen;
			;
		}
		members = ClassOrInterfaceBody(isInterface);
		{
			if (true) {
				return new ClassOrInterfaceDeclaration(line, column, token.endLine, token.endColumn, popJavadoc(), modifier.modifiers, modifier.annotations, isInterface, name, typePar, extList, impList, members);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List ExtendsList(boolean isInterface) throws ParseException {
		boolean extendsMoreThanOne = false;
		List ret = new LinkedList();
		ClassOrInterfaceType cit;
		jj_consume_token(EXTENDS);
		cit = ClassOrInterfaceType();
		ret.add(cit);
		label_5: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case COMMA:
				;
				break;
			default:
				jj_la1[13] = jj_gen;
				break label_5;
			}
			jj_consume_token(COMMA);
			cit = ClassOrInterfaceType();
			ret.add(cit);
			extendsMoreThanOne = true;
		}
		if (extendsMoreThanOne && !isInterface) {
			throwParseException(token, "A class cannot extend more than one other class");
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List ImplementsList(boolean isInterface) throws ParseException {
		List ret = new LinkedList();
		ClassOrInterfaceType cit;
		jj_consume_token(IMPLEMENTS);
		cit = ClassOrInterfaceType();
		ret.add(cit);
		label_6: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case COMMA:
				;
				break;
			default:
				jj_la1[14] = jj_gen;
				break label_6;
			}
			jj_consume_token(COMMA);
			cit = ClassOrInterfaceType();
			ret.add(cit);
		}
		if (isInterface) {
			throwParseException(token, "An interface cannot implement other interfaces");
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public EnumDeclaration EnumDeclaration(Modifier modifier) throws ParseException {
		String name;
		List impList = null;
		EnumConstantDeclaration entry;
		List entries = null;
		BodyDeclaration member;
		List members = null;
		int line = modifier.beginLine;
		int column = modifier.beginColumn;
		jj_consume_token(ENUM);
		if (line == -1) {
			line = token.beginLine;
			column = token.beginColumn;
		}
		jj_consume_token(IDENTIFIER);
		name = token.image;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case IMPLEMENTS:
			impList = ImplementsList(false);
			break;
		default:
			jj_la1[15] = jj_gen;
			;
		}
		jj_consume_token(LBRACE);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case IDENTIFIER:
		case AT:
			entries = new LinkedList();
			entry = EnumConstantDeclaration();
			entries.add(entry);
			label_7: while (true) {
				if (jj_2_3(2)) {
					;
				} else {
					break label_7;
				}
				jj_consume_token(COMMA);
				entry = EnumConstantDeclaration();
				entries.add(entry);
			}
			break;
		default:
			jj_la1[16] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case COMMA:
			jj_consume_token(COMMA);
			break;
		default:
			jj_la1[17] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case SEMICOLON:
			jj_consume_token(SEMICOLON);
			label_8: while (true) {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case ABSTRACT:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case CLASS:
				case DOUBLE:
				case ENUM:
				case FINAL:
				case FLOAT:
				case INT:
				case INTERFACE:
				case LONG:
				case NATIVE:
				case PRIVATE:
				case PROTECTED:
				case PUBLIC:
				case SHORT:
				case STATIC:
				case STRICTFP:
				case SYNCHRONIZED:
				case TRANSIENT:
				case VOID:
				case VOLATILE:
				case IDENTIFIER:
				case LBRACE:
				case SEMICOLON:
				case AT:
				case LT:
					;
					break;
				default:
					jj_la1[18] = jj_gen;
					break label_8;
				}
				member = ClassOrInterfaceBodyDeclaration(false);
				members = add(members, member);
			}
			break;
		default:
			jj_la1[19] = jj_gen;
			;
		}
		jj_consume_token(RBRACE);
		{
			if (true) {
				return new EnumDeclaration(line, column, token.endLine, token.endColumn, popJavadoc(), modifier.modifiers, modifier.annotations, name, impList, entries, members);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public EnumConstantDeclaration EnumConstantDeclaration() throws ParseException {
		List annotations = null;
		AnnotationExpr ann;
		String name;
		List args = null;
		List classBody = null;
		int line = -1;
		int column = -1;
		pushJavadoc();
		label_9: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case AT:
				;
				break;
			default:
				jj_la1[20] = jj_gen;
				break label_9;
			}
			ann = Annotation();
			annotations = add(annotations, ann);
			if (line == -1) {
				line = ann.getBeginLine();
				column = ann.getBeginColumn();
			}
		}
		jj_consume_token(IDENTIFIER);
		name = token.image;
		if (line == -1) {
			line = token.beginLine;
			column = token.beginColumn;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case LPAREN:
			args = Arguments();
			break;
		default:
			jj_la1[21] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case LBRACE:
			classBody = ClassOrInterfaceBody(false);
			break;
		default:
			jj_la1[22] = jj_gen;
			;
		}
		{
			if (true) {
				return new EnumConstantDeclaration(line, column, token.endLine, token.endColumn, popJavadoc(), annotations, name, args, classBody);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List TypeParameters() throws ParseException {
		List ret = new LinkedList();
		TypeParameter tp;
		jj_consume_token(LT);
		ret.add(new int[] { token.beginLine, token.beginColumn });
		tp = TypeParameter();
		ret.add(tp);
		label_10: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case COMMA:
				;
				break;
			default:
				jj_la1[23] = jj_gen;
				break label_10;
			}
			jj_consume_token(COMMA);
			tp = TypeParameter();
			ret.add(tp);
		}
		jj_consume_token(GT);
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public TypeParameter TypeParameter() throws ParseException {
		String name;
		List typeBound = null;
		int line;
		int column;
		jj_consume_token(IDENTIFIER);
		name = token.image;
		line = token.beginLine;
		column = token.beginColumn;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case EXTENDS:
			typeBound = TypeBound();
			break;
		default:
			jj_la1[24] = jj_gen;
			;
		}
		{
			if (true) {
				return new TypeParameter(line, column, token.endLine, token.endColumn, name, typeBound);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List TypeBound() throws ParseException {
		List ret = new LinkedList();
		ClassOrInterfaceType cit;
		jj_consume_token(EXTENDS);
		cit = ClassOrInterfaceType();
		ret.add(cit);
		label_11: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case BIT_AND:
				;
				break;
			default:
				jj_la1[25] = jj_gen;
				break label_11;
			}
			jj_consume_token(BIT_AND);
			cit = ClassOrInterfaceType();
			ret.add(cit);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List ClassOrInterfaceBody(boolean isInterface) throws ParseException {
		List ret = new LinkedList();
		BodyDeclaration member;
		jj_consume_token(LBRACE);
		label_12: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DOUBLE:
			case ENUM:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOID:
			case VOLATILE:
			case IDENTIFIER:
			case LBRACE:
			case SEMICOLON:
			case AT:
			case LT:
				;
				break;
			default:
				jj_la1[26] = jj_gen;
				break label_12;
			}
			member = ClassOrInterfaceBodyDeclaration(isInterface);
			ret.add(member);
		}
		jj_consume_token(RBRACE);
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public BodyDeclaration ClassOrInterfaceBodyDeclaration(boolean isInterface) throws ParseException {
		boolean isNestedInterface = false;
		Modifier modifier;
		BodyDeclaration ret;
		pushJavadoc();
		if (jj_2_6(2)) {
			ret = InitializerDeclaration();
			if (isInterface) {
				throwParseException(token, "An interface cannot have initializers");
			}
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DOUBLE:
			case ENUM:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOID:
			case VOLATILE:
			case IDENTIFIER:
			case AT:
			case LT:
				modifier = Modifiers();
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case CLASS:
				case INTERFACE:
					ret = ClassOrInterfaceDeclaration(modifier);
					break;
				case ENUM:
					ret = EnumDeclaration(modifier);
					break;
				case AT:
					ret = AnnotationTypeDeclaration(modifier);
					break;
				default:
					jj_la1[27] = jj_gen;
					if (jj_2_4(2147483647)) {
						ret = ConstructorDeclaration(modifier);
					} else if (jj_2_5(2147483647)) {
						ret = FieldDeclaration(modifier);
					} else {
						switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
						case BOOLEAN:
						case BYTE:
						case CHAR:
						case DOUBLE:
						case FLOAT:
						case INT:
						case LONG:
						case SHORT:
						case VOID:
						case IDENTIFIER:
						case LT:
							ret = MethodDeclaration(modifier);
							break;
						default:
							jj_la1[28] = jj_gen;
							jj_consume_token(-1);
							throw new ParseException();
						}
					}
				}
				break;
			case SEMICOLON:
				jj_consume_token(SEMICOLON);
				ret = new EmptyMemberDeclaration(token.beginLine, token.beginColumn, token.endLine, token.endColumn, popJavadoc());
				break;
			default:
				jj_la1[29] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public FieldDeclaration FieldDeclaration(Modifier modifier) throws ParseException {
		Type type;
		List variables = new LinkedList();
		VariableDeclarator val;
		// Modifiers are already matched in the caller
		type = Type();
		val = VariableDeclarator();
		variables.add(val);
		label_13: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case COMMA:
				;
				break;
			default:
				jj_la1[30] = jj_gen;
				break label_13;
			}
			jj_consume_token(COMMA);
			val = VariableDeclarator();
			variables.add(val);
		}
		jj_consume_token(SEMICOLON);
		int line = modifier.beginLine;
		int column = modifier.beginColumn;
		if (line == -1) {
			line = type.getBeginLine();
			column = type.getBeginColumn();
		}
		{
			if (true) {
				return new FieldDeclaration(line, column, token.endLine, token.endColumn, popJavadoc(), modifier.modifiers, modifier.annotations, type, variables);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public VariableDeclarator VariableDeclarator() throws ParseException {
		VariableDeclaratorId id;
		Expression init = null;
		id = VariableDeclaratorId();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case ASSIGN:
			jj_consume_token(ASSIGN);
			init = VariableInitializer();
			break;
		default:
			jj_la1[31] = jj_gen;
			;
		}
		{
			if (true) {
				return new VariableDeclarator(id.getBeginLine(), id.getBeginColumn(), token.endLine, token.endColumn, id, init);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public VariableDeclaratorId VariableDeclaratorId() throws ParseException {
		String name;
		int arrayCount = 0;
		int line;
		int column;
		jj_consume_token(IDENTIFIER);
		name = token.image;
		line = token.beginLine;
		column = token.beginColumn;
		label_14: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case LBRACKET:
				;
				break;
			default:
				jj_la1[32] = jj_gen;
				break label_14;
			}
			jj_consume_token(LBRACKET);
			jj_consume_token(RBRACKET);
			arrayCount++;
		}
		{
			if (true) {
				return new VariableDeclaratorId(line, column, token.endLine, token.endColumn, name, arrayCount);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression VariableInitializer() throws ParseException {
		Expression ret;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case LBRACE:
			ret = ArrayInitializer();
			break;
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FALSE:
		case FLOAT:
		case INT:
		case LONG:
		case NEW:
		case NULL:
		case SHORT:
		case SUPER:
		case THIS:
		case TRUE:
		case VOID:
		case LONG_LITERAL:
		case INTEGER_LITERAL:
		case FLOATING_POINT_LITERAL:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
		case IDENTIFIER:
		case LPAREN:
		case BANG:
		case TILDE:
		case INCR:
		case DECR:
		case PLUS:
		case MINUS:
			ret = Expression();
			break;
		default:
			jj_la1[33] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public ArrayInitializerExpr ArrayInitializer() throws ParseException {
		List values = null;
		Expression val;
		int line;
		int column;
		jj_consume_token(LBRACE);
		line = token.beginLine;
		column = token.beginColumn;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FALSE:
		case FLOAT:
		case INT:
		case LONG:
		case NEW:
		case NULL:
		case SHORT:
		case SUPER:
		case THIS:
		case TRUE:
		case VOID:
		case LONG_LITERAL:
		case INTEGER_LITERAL:
		case FLOATING_POINT_LITERAL:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
		case IDENTIFIER:
		case LPAREN:
		case LBRACE:
		case BANG:
		case TILDE:
		case INCR:
		case DECR:
		case PLUS:
		case MINUS:
			val = VariableInitializer();
			values = add(values, val);
			label_15: while (true) {
				if (jj_2_7(2)) {
					;
				} else {
					break label_15;
				}
				jj_consume_token(COMMA);
				val = VariableInitializer();
				values = add(values, val);
			}
			break;
		default:
			jj_la1[34] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case COMMA:
			jj_consume_token(COMMA);
			break;
		default:
			jj_la1[35] = jj_gen;
			;
		}
		jj_consume_token(RBRACE);
		{
			if (true) {
				return new ArrayInitializerExpr(line, column, token.endLine, token.endColumn, values);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public MethodDeclaration MethodDeclaration(Modifier modifier) throws ParseException {
		List typeParameters = null;
		Type type;
		String name;
		List parameters;
		int arrayCount = 0;
		List throws_ = null;
		BlockStmt block = null;
		int line = modifier.beginLine;
		int column = modifier.beginColumn;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case LT:
			typeParameters = TypeParameters();
			int[] lineCol = (int[]) typeParameters.remove(0);
			if (line == -1) {
				line = lineCol[0];
				column = lineCol[1];
			}
			break;
		default:
			jj_la1[36] = jj_gen;
			;
		}
		type = ResultType();
		if (line == -1) {
			line = type.getBeginLine();
			column = type.getBeginColumn();
		}
		jj_consume_token(IDENTIFIER);
		name = token.image;
		parameters = FormalParameters();
		label_16: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case LBRACKET:
				;
				break;
			default:
				jj_la1[37] = jj_gen;
				break label_16;
			}
			jj_consume_token(LBRACKET);
			jj_consume_token(RBRACKET);
			arrayCount++;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case THROWS:
			jj_consume_token(THROWS);
			throws_ = NameList();
			break;
		default:
			jj_la1[38] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case LBRACE:
			block = Block();
			break;
		case SEMICOLON:
			jj_consume_token(SEMICOLON);
			break;
		default:
			jj_la1[39] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return new MethodDeclaration(line, column, token.endLine, token.endColumn, popJavadoc(), modifier.modifiers, modifier.annotations, typeParameters, type, name, parameters, arrayCount, throws_, block);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List FormalParameters() throws ParseException {
		List ret = null;
		Parameter par;
		jj_consume_token(LPAREN);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case ABSTRACT:
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FINAL:
		case FLOAT:
		case INT:
		case LONG:
		case NATIVE:
		case PRIVATE:
		case PROTECTED:
		case PUBLIC:
		case SHORT:
		case STATIC:
		case STRICTFP:
		case SYNCHRONIZED:
		case TRANSIENT:
		case VOLATILE:
		case IDENTIFIER:
		case AT:
			par = FormalParameter();
			ret = add(ret, par);
			label_17: while (true) {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case COMMA:
					;
					break;
				default:
					jj_la1[40] = jj_gen;
					break label_17;
				}
				jj_consume_token(COMMA);
				par = FormalParameter();
				ret = add(ret, par);
			}
			break;
		default:
			jj_la1[41] = jj_gen;
			;
		}
		jj_consume_token(RPAREN);
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Parameter FormalParameter() throws ParseException {
		Modifier modifier;
		Type type;
		boolean isVarArg = false;
		VariableDeclaratorId id;
		modifier = Modifiers();
		type = Type();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case ELLIPSIS:
			jj_consume_token(ELLIPSIS);
			isVarArg = true;
			break;
		default:
			jj_la1[42] = jj_gen;
			;
		}
		id = VariableDeclaratorId();
		int line = modifier.beginLine;
		int column = modifier.beginColumn;
		if (line == -1) {
			line = type.getBeginLine();
			column = type.getBeginColumn();
		}
		{
			if (true) {
				return new Parameter(line, column, token.endLine, token.endColumn, modifier.modifiers, modifier.annotations, type, isVarArg, id);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public ConstructorDeclaration ConstructorDeclaration(Modifier modifier) throws ParseException {
		List typeParameters = null;
		String name;
		List parameters;
		List throws_ = null;
		ExplicitConstructorInvocationStmt exConsInv = null;
		List stmts;
		int line = modifier.beginLine;
		int column = modifier.beginColumn;
		int bbLine = 0;
		int bbColumn = 0;
		int beLine = 0;
		int beColumn = 0;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case LT:
			typeParameters = TypeParameters();
			int[] lineCol = (int[]) typeParameters.remove(0);
			if (line == -1) {
				line = lineCol[0];
				column = lineCol[1];
			}
			break;
		default:
			jj_la1[43] = jj_gen;
			;
		}
		jj_consume_token(IDENTIFIER);
		name = token.image;
		if (line == -1) {
			line = token.beginLine;
			column = token.beginColumn;
		}
		parameters = FormalParameters();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case THROWS:
			jj_consume_token(THROWS);
			throws_ = NameList();
			break;
		default:
			jj_la1[44] = jj_gen;
			;
		}
		jj_consume_token(LBRACE);
		bbLine = token.beginLine;
		bbColumn = token.beginColumn;
		if (jj_2_8(2147483647)) {
			exConsInv = ExplicitConstructorInvocation();
		} else {
			;
		}
		stmts = Statements();
		jj_consume_token(RBRACE);
		if (exConsInv != null) {
			stmts = add(0, stmts, exConsInv);
		}
		{
			if (true) {
				return new ConstructorDeclaration(line, column, token.endLine, token.endColumn, popJavadoc(), modifier.modifiers, modifier.annotations, typeParameters, name, parameters, throws_, new BlockStmt(bbLine, bbColumn, token.endLine, token.endColumn,
						stmts));
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public ExplicitConstructorInvocationStmt ExplicitConstructorInvocation() throws ParseException {
		boolean isThis = false;
		List args;
		Expression expr = null;
		List typeArgs = null;
		int line = -1;
		int column = 0;
		if (jj_2_10(2147483647)) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case LT:
				typeArgs = TypeArguments();
				int[] lineCol = (int[]) typeArgs.remove(0);
				line = lineCol[0];
				column = lineCol[1];
				break;
			default:
				jj_la1[45] = jj_gen;
				;
			}
			jj_consume_token(THIS);
			if (line == -1) {
				line = token.beginLine;
				column = token.beginColumn;
			}
			isThis = true;
			args = Arguments();
			jj_consume_token(SEMICOLON);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FALSE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case NULL:
			case SHORT:
			case SUPER:
			case THIS:
			case TRUE:
			case VOID:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case LT:
				if (jj_2_9(2147483647)) {
					expr = PrimaryExpressionWithoutSuperSuffix();
					jj_consume_token(DOT);
					line = expr.getBeginLine();
					column = expr.getBeginColumn();
				} else {
					;
				}
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case LT:
					typeArgs = TypeArguments();
					int[] lineCol = (int[]) typeArgs.remove(0);
					if (line == -1) {
						line = lineCol[0];
						column = lineCol[1];
					}
					break;
				default:
					jj_la1[46] = jj_gen;
					;
				}
				jj_consume_token(SUPER);
				if (line == -1) {
					line = token.beginLine;
					column = token.beginColumn;
				}
				args = Arguments();
				jj_consume_token(SEMICOLON);
				break;
			default:
				jj_la1[47] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		{
			if (true) {
				return new ExplicitConstructorInvocationStmt(line, column, token.endLine, token.endColumn, typeArgs, isThis, expr, args);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List Statements() throws ParseException {
		List ret = null;
		Statement stmt;
		label_18: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case ABSTRACT:
			case ASSERT:
			case BOOLEAN:
			case BREAK:
			case BYTE:
			case CHAR:
			case CLASS:
			case CONTINUE:
			case DO:
			case DOUBLE:
			case FALSE:
			case FINAL:
			case FLOAT:
			case FOR:
			case IF:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case NEW:
			case NULL:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case RETURN:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SUPER:
			case SWITCH:
			case SYNCHRONIZED:
			case THIS:
			case THROW:
			case TRANSIENT:
			case TRUE:
			case TRY:
			case VOID:
			case VOLATILE:
			case WHILE:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case LBRACE:
			case SEMICOLON:
			case AT:
			case INCR:
			case DECR:
				;
				break;
			default:
				jj_la1[48] = jj_gen;
				break label_18;
			}
			stmt = BlockStatement();
			ret = add(ret, stmt);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public InitializerDeclaration InitializerDeclaration() throws ParseException {
		BlockStmt block;
		int line = -1;
		int column = 0;
		boolean isStatic = false;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case STATIC:
			jj_consume_token(STATIC);
			isStatic = true;
			line = token.beginLine;
			column = token.beginColumn;
			break;
		default:
			jj_la1[49] = jj_gen;
			;
		}
		block = Block();
		if (line == -1) {
			line = block.getBeginLine();
			column = block.getBeginColumn();
		}
		{
			if (true) {
				return new InitializerDeclaration(line, column, token.endLine, token.endColumn, popJavadoc(), isStatic, block);
			}
		}
		throw new Error("Missing return statement in function");
	}

	/*
	 * Type, name and expression syntax follows.
	 */
	final public Type Type() throws ParseException {
		Type ret;
		if (jj_2_11(2)) {
			ret = ReferenceType();
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				ret = PrimitiveType();
				break;
			default:
				jj_la1[50] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public ReferenceType ReferenceType() throws ParseException {
		Type type;
		int arrayCount = 0;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FLOAT:
		case INT:
		case LONG:
		case SHORT:
			type = PrimitiveType();
			label_19: while (true) {
				jj_consume_token(LBRACKET);
				jj_consume_token(RBRACKET);
				arrayCount++;
				if (jj_2_12(2)) {
					;
				} else {
					break label_19;
				}
			}
			break;
		case IDENTIFIER:
			type = ClassOrInterfaceType();
			label_20: while (true) {
				if (jj_2_13(2)) {
					;
				} else {
					break label_20;
				}
				jj_consume_token(LBRACKET);
				jj_consume_token(RBRACKET);
				arrayCount++;
			}
			break;
		default:
			jj_la1[51] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return new ReferenceType(type.getBeginLine(), type.getBeginColumn(), token.endLine, token.endColumn, type, arrayCount);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public ClassOrInterfaceType ClassOrInterfaceType() throws ParseException {
		ClassOrInterfaceType ret;
		String name;
		List typeArgs = null;
		int line;
		int column;
		jj_consume_token(IDENTIFIER);
		line = token.beginLine;
		column = token.beginColumn;
		name = token.image;
		if (jj_2_14(2)) {
			typeArgs = TypeArguments();
			typeArgs.remove(0);
		} else {
			;
		}
		ret = new ClassOrInterfaceType(line, column, token.endLine, token.endColumn, null, name, typeArgs);
		label_21: while (true) {
			if (jj_2_15(2)) {
				;
			} else {
				break label_21;
			}
			jj_consume_token(DOT);
			jj_consume_token(IDENTIFIER);
			name = token.image;
			if (jj_2_16(2)) {
				typeArgs = TypeArguments();
				typeArgs.remove(0);
			} else {
				;
			}
			ret = new ClassOrInterfaceType(line, column, token.endLine, token.endColumn, ret, name, typeArgs);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List TypeArguments() throws ParseException {
		List ret = new LinkedList();
		Type type;
		jj_consume_token(LT);
		ret.add(new int[] { token.beginLine, token.beginColumn });
		type = TypeArgument();
		ret.add(type);
		label_22: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case COMMA:
				;
				break;
			default:
				jj_la1[52] = jj_gen;
				break label_22;
			}
			jj_consume_token(COMMA);
			type = TypeArgument();
			ret.add(type);
		}
		jj_consume_token(GT);
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Type TypeArgument() throws ParseException {
		Type ret;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FLOAT:
		case INT:
		case LONG:
		case SHORT:
		case IDENTIFIER:
			ret = ReferenceType();
			break;
		case HOOK:
			ret = Wildcard();
			break;
		default:
			jj_la1[53] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public WildcardType Wildcard() throws ParseException {
		ReferenceType ext = null;
		ReferenceType sup = null;
		int line;
		int column;
		jj_consume_token(HOOK);
		line = token.beginLine;
		column = token.beginColumn;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case EXTENDS:
		case SUPER:
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case EXTENDS:
				jj_consume_token(EXTENDS);
				ext = ReferenceType();
				break;
			case SUPER:
				jj_consume_token(SUPER);
				sup = ReferenceType();
				break;
			default:
				jj_la1[54] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			break;
		default:
			jj_la1[55] = jj_gen;
			;
		}
		{
			if (true) {
				return new WildcardType(line, column, token.endLine, token.endColumn, ext, sup);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public PrimitiveType PrimitiveType() throws ParseException {
		PrimitiveType ret;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case BOOLEAN:
			jj_consume_token(BOOLEAN);
			ret = new PrimitiveType(token.beginLine, token.beginColumn, token.endLine, token.endColumn, PrimitiveType.Primitive.Boolean);
			break;
		case CHAR:
			jj_consume_token(CHAR);
			ret = new PrimitiveType(token.beginLine, token.beginColumn, token.endLine, token.endColumn, PrimitiveType.Primitive.Char);
			break;
		case BYTE:
			jj_consume_token(BYTE);
			ret = new PrimitiveType(token.beginLine, token.beginColumn, token.endLine, token.endColumn, PrimitiveType.Primitive.Byte);
			break;
		case SHORT:
			jj_consume_token(SHORT);
			ret = new PrimitiveType(token.beginLine, token.beginColumn, token.endLine, token.endColumn, PrimitiveType.Primitive.Short);
			break;
		case INT:
			jj_consume_token(INT);
			ret = new PrimitiveType(token.beginLine, token.beginColumn, token.endLine, token.endColumn, PrimitiveType.Primitive.Int);
			break;
		case LONG:
			jj_consume_token(LONG);
			ret = new PrimitiveType(token.beginLine, token.beginColumn, token.endLine, token.endColumn, PrimitiveType.Primitive.Long);
			break;
		case FLOAT:
			jj_consume_token(FLOAT);
			ret = new PrimitiveType(token.beginLine, token.beginColumn, token.endLine, token.endColumn, PrimitiveType.Primitive.Float);
			break;
		case DOUBLE:
			jj_consume_token(DOUBLE);
			ret = new PrimitiveType(token.beginLine, token.beginColumn, token.endLine, token.endColumn, PrimitiveType.Primitive.Double);
			break;
		default:
			jj_la1[56] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Type ResultType() throws ParseException {
		Type ret;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case VOID:
			jj_consume_token(VOID);
			ret = new VoidType(token.beginLine, token.beginColumn, token.endLine, token.endColumn);
			break;
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FLOAT:
		case INT:
		case LONG:
		case SHORT:
		case IDENTIFIER:
			ret = Type();
			break;
		default:
			jj_la1[57] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public NameExpr Name() throws ParseException {
		NameExpr ret;
		jj_consume_token(IDENTIFIER);
		ret = new NameExpr(token.beginLine, token.beginColumn, token.endLine, token.endColumn, token.image);
		label_23: while (true) {
			if (jj_2_17(2)) {
				;
			} else {
				break label_23;
			}
			jj_consume_token(DOT);
			jj_consume_token(IDENTIFIER);
			ret = new QualifiedNameExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, token.image);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List NameList() throws ParseException {
		List ret = new LinkedList();
		NameExpr name;
		name = Name();
		ret.add(name);
		label_24: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case COMMA:
				;
				break;
			default:
				jj_la1[58] = jj_gen;
				break label_24;
			}
			jj_consume_token(COMMA);
			name = Name();
			ret.add(name);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	/*
	 * Expression syntax follows.
	 */
	final public Expression Expression() throws ParseException {
		Expression ret;
		AssignExpr.Operator op;
		Expression value;
		ret = ConditionalExpression();
		if (jj_2_18(2)) {
			op = AssignmentOperator();
			value = Expression();
			ret = new AssignExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, value, op);
		} else {
			;
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public AssignExpr.Operator AssignmentOperator() throws ParseException {
		AssignExpr.Operator ret;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case ASSIGN:
			jj_consume_token(ASSIGN);
			ret = AssignExpr.Operator.assign;
			break;
		case STARASSIGN:
			jj_consume_token(STARASSIGN);
			ret = AssignExpr.Operator.star;
			break;
		case SLASHASSIGN:
			jj_consume_token(SLASHASSIGN);
			ret = AssignExpr.Operator.slash;
			break;
		case REMASSIGN:
			jj_consume_token(REMASSIGN);
			ret = AssignExpr.Operator.rem;
			break;
		case PLUSASSIGN:
			jj_consume_token(PLUSASSIGN);
			ret = AssignExpr.Operator.plus;
			break;
		case MINUSASSIGN:
			jj_consume_token(MINUSASSIGN);
			ret = AssignExpr.Operator.minus;
			break;
		case LSHIFTASSIGN:
			jj_consume_token(LSHIFTASSIGN);
			ret = AssignExpr.Operator.lShift;
			break;
		case RSIGNEDSHIFTASSIGN:
			jj_consume_token(RSIGNEDSHIFTASSIGN);
			ret = AssignExpr.Operator.rSignedShift;
			break;
		case RUNSIGNEDSHIFTASSIGN:
			jj_consume_token(RUNSIGNEDSHIFTASSIGN);
			ret = AssignExpr.Operator.rUnsignedShift;
			break;
		case ANDASSIGN:
			jj_consume_token(ANDASSIGN);
			ret = AssignExpr.Operator.and;
			break;
		case XORASSIGN:
			jj_consume_token(XORASSIGN);
			ret = AssignExpr.Operator.xor;
			break;
		case ORASSIGN:
			jj_consume_token(ORASSIGN);
			ret = AssignExpr.Operator.or;
			break;
		default:
			jj_la1[59] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression ConditionalExpression() throws ParseException {
		Expression ret;
		Expression left;
		Expression right;
		ret = ConditionalOrExpression();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case HOOK:
			jj_consume_token(HOOK);
			left = Expression();
			jj_consume_token(COLON);
			right = ConditionalExpression();
			ret = new ConditionalExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, left, right);
			break;
		default:
			jj_la1[60] = jj_gen;
			;
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression ConditionalOrExpression() throws ParseException {
		Expression ret;
		Expression right;
		ret = ConditionalAndExpression();
		label_25: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case SC_OR:
				;
				break;
			default:
				jj_la1[61] = jj_gen;
				break label_25;
			}
			jj_consume_token(SC_OR);
			right = ConditionalAndExpression();
			ret = new BinaryExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, right, BinaryExpr.Operator.or);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression ConditionalAndExpression() throws ParseException {
		Expression ret;
		Expression right;
		ret = InclusiveOrExpression();
		label_26: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case SC_AND:
				;
				break;
			default:
				jj_la1[62] = jj_gen;
				break label_26;
			}
			jj_consume_token(SC_AND);
			right = InclusiveOrExpression();
			ret = new BinaryExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, right, BinaryExpr.Operator.and);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression InclusiveOrExpression() throws ParseException {
		Expression ret;
		Expression right;
		ret = ExclusiveOrExpression();
		label_27: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case BIT_OR:
				;
				break;
			default:
				jj_la1[63] = jj_gen;
				break label_27;
			}
			jj_consume_token(BIT_OR);
			right = ExclusiveOrExpression();
			ret = new BinaryExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, right, BinaryExpr.Operator.binOr);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression ExclusiveOrExpression() throws ParseException {
		Expression ret;
		Expression right;
		ret = AndExpression();
		label_28: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case XOR:
				;
				break;
			default:
				jj_la1[64] = jj_gen;
				break label_28;
			}
			jj_consume_token(XOR);
			right = AndExpression();
			ret = new BinaryExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, right, BinaryExpr.Operator.xor);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression AndExpression() throws ParseException {
		Expression ret;
		Expression right;
		ret = EqualityExpression();
		label_29: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case BIT_AND:
				;
				break;
			default:
				jj_la1[65] = jj_gen;
				break label_29;
			}
			jj_consume_token(BIT_AND);
			right = EqualityExpression();
			ret = new BinaryExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, right, BinaryExpr.Operator.binAnd);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression EqualityExpression() throws ParseException {
		Expression ret;
		Expression right;
		BinaryExpr.Operator op;
		ret = InstanceOfExpression();
		label_30: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case EQ:
			case NE:
				;
				break;
			default:
				jj_la1[66] = jj_gen;
				break label_30;
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case EQ:
				jj_consume_token(EQ);
				op = BinaryExpr.Operator.equals;
				break;
			case NE:
				jj_consume_token(NE);
				op = BinaryExpr.Operator.notEquals;
				break;
			default:
				jj_la1[67] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			right = InstanceOfExpression();
			ret = new BinaryExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, right, op);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression InstanceOfExpression() throws ParseException {
		Expression ret;
		Type type;
		ret = RelationalExpression();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case INSTANCEOF:
			jj_consume_token(INSTANCEOF);
			type = Type();
			ret = new InstanceOfExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, type);
			break;
		default:
			jj_la1[68] = jj_gen;
			;
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression RelationalExpression() throws ParseException {
		Expression ret;
		Expression right;
		BinaryExpr.Operator op;
		ret = ShiftExpression();
		label_31: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case LT:
			case LE:
			case GE:
			case GT:
				;
				break;
			default:
				jj_la1[69] = jj_gen;
				break label_31;
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case LT:
				jj_consume_token(LT);
				op = BinaryExpr.Operator.less;
				break;
			case GT:
				jj_consume_token(GT);
				op = BinaryExpr.Operator.greater;
				break;
			case LE:
				jj_consume_token(LE);
				op = BinaryExpr.Operator.lessEquals;
				break;
			case GE:
				jj_consume_token(GE);
				op = BinaryExpr.Operator.greaterEquals;
				break;
			default:
				jj_la1[70] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			right = ShiftExpression();
			ret = new BinaryExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, right, op);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression ShiftExpression() throws ParseException {
		Expression ret;
		Expression right;
		BinaryExpr.Operator op;
		ret = AdditiveExpression();
		label_32: while (true) {
			if (jj_2_19(1)) {
				;
			} else {
				break label_32;
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case LSHIFT:
				jj_consume_token(LSHIFT);
				op = BinaryExpr.Operator.lShift;
				break;
			default:
				jj_la1[71] = jj_gen;
				if (jj_2_20(1)) {
					RSIGNEDSHIFT();
					op = BinaryExpr.Operator.rSignedShift;
				} else if (jj_2_21(1)) {
					RUNSIGNEDSHIFT();
					op = BinaryExpr.Operator.rUnsignedShift;
				} else {
					jj_consume_token(-1);
					throw new ParseException();
				}
			}
			right = AdditiveExpression();
			ret = new BinaryExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, right, op);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression AdditiveExpression() throws ParseException {
		Expression ret;
		Expression right;
		BinaryExpr.Operator op;
		ret = MultiplicativeExpression();
		label_33: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case PLUS:
			case MINUS:
				;
				break;
			default:
				jj_la1[72] = jj_gen;
				break label_33;
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case PLUS:
				jj_consume_token(PLUS);
				op = BinaryExpr.Operator.plus;
				break;
			case MINUS:
				jj_consume_token(MINUS);
				op = BinaryExpr.Operator.minus;
				break;
			default:
				jj_la1[73] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			right = MultiplicativeExpression();
			ret = new BinaryExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, right, op);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression MultiplicativeExpression() throws ParseException {
		Expression ret;
		Expression right;
		BinaryExpr.Operator op;
		ret = UnaryExpression();
		label_34: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case STAR:
			case SLASH:
			case REM:
				;
				break;
			default:
				jj_la1[74] = jj_gen;
				break label_34;
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case STAR:
				jj_consume_token(STAR);
				op = BinaryExpr.Operator.times;
				break;
			case SLASH:
				jj_consume_token(SLASH);
				op = BinaryExpr.Operator.divide;
				break;
			case REM:
				jj_consume_token(REM);
				op = BinaryExpr.Operator.remainder;
				break;
			default:
				jj_la1[75] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			right = UnaryExpression();
			ret = new BinaryExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, right, op);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression UnaryExpression() throws ParseException {
		Expression ret;
		UnaryExpr.Operator op;
		int line = 0;
		int column = 0;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case PLUS:
		case MINUS:
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case PLUS:
				jj_consume_token(PLUS);
				op = UnaryExpr.Operator.positive;
				line = token.beginLine;
				column = token.beginColumn;
				break;
			case MINUS:
				jj_consume_token(MINUS);
				op = UnaryExpr.Operator.negative;
				line = token.beginLine;
				column = token.beginColumn;
				break;
			default:
				jj_la1[76] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			ret = UnaryExpression();
			if (op == UnaryExpr.Operator.negative) {
				if (ret instanceof IntegerLiteralExpr && ((IntegerLiteralExpr) ret).isMinValue()) {
					ret = new IntegerLiteralMinValueExpr(line, column, token.endLine, token.endColumn);
				} else if (ret instanceof LongLiteralExpr && ((LongLiteralExpr) ret).isMinValue()) {
					ret = new LongLiteralMinValueExpr(line, column, token.endLine, token.endColumn);
				} else {
					ret = new UnaryExpr(line, column, token.endLine, token.endColumn, ret, op);
				}
			} else {
				ret = new UnaryExpr(line, column, token.endLine, token.endColumn, ret, op);
			}
			break;
		case INCR:
			ret = PreIncrementExpression();
			break;
		case DECR:
			ret = PreDecrementExpression();
			break;
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FALSE:
		case FLOAT:
		case INT:
		case LONG:
		case NEW:
		case NULL:
		case SHORT:
		case SUPER:
		case THIS:
		case TRUE:
		case VOID:
		case LONG_LITERAL:
		case INTEGER_LITERAL:
		case FLOATING_POINT_LITERAL:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
		case IDENTIFIER:
		case LPAREN:
		case BANG:
		case TILDE:
			ret = UnaryExpressionNotPlusMinus();
			break;
		default:
			jj_la1[77] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression PreIncrementExpression() throws ParseException {
		Expression ret;
		int line;
		int column;
		jj_consume_token(INCR);
		line = token.beginLine;
		column = token.beginColumn;
		ret = PrimaryExpression();
		ret = new UnaryExpr(line, column, token.endLine, token.endColumn, ret, UnaryExpr.Operator.preIncrement);
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression PreDecrementExpression() throws ParseException {
		Expression ret;
		int line;
		int column;
		jj_consume_token(DECR);
		line = token.beginLine;
		column = token.beginColumn;
		ret = PrimaryExpression();
		ret = new UnaryExpr(line, column, token.endLine, token.endColumn, ret, UnaryExpr.Operator.preDecrement);
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression UnaryExpressionNotPlusMinus() throws ParseException {
		Expression ret;
		UnaryExpr.Operator op;
		int line = 0;
		int column = 0;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case BANG:
		case TILDE:
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case TILDE:
				jj_consume_token(TILDE);
				op = UnaryExpr.Operator.inverse;
				line = token.beginLine;
				column = token.beginColumn;
				break;
			case BANG:
				jj_consume_token(BANG);
				op = UnaryExpr.Operator.not;
				line = token.beginLine;
				column = token.beginColumn;
				break;
			default:
				jj_la1[78] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			ret = UnaryExpression();
			ret = new UnaryExpr(line, column, token.endLine, token.endColumn, ret, op);
			break;
		default:
			jj_la1[79] = jj_gen;
			if (jj_2_22(2147483647)) {
				ret = CastExpression();
			} else {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FALSE:
				case FLOAT:
				case INT:
				case LONG:
				case NEW:
				case NULL:
				case SHORT:
				case SUPER:
				case THIS:
				case TRUE:
				case VOID:
				case LONG_LITERAL:
				case INTEGER_LITERAL:
				case FLOATING_POINT_LITERAL:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
				case LPAREN:
					ret = PostfixExpression();
					break;
				default:
					jj_la1[80] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
				}
			}
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	// This production is to determine lookahead only. The LOOKAHEAD specifications
	// below are not used, but they are there just to indicate that we know about
	// this.
	final public void CastLookahead() throws ParseException {
		if (jj_2_23(2147483647)) {
			jj_consume_token(LPAREN);
			Type();
			jj_consume_token(LBRACKET);
			jj_consume_token(RBRACKET);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case LPAREN:
				jj_consume_token(LPAREN);
				Type();
				jj_consume_token(RPAREN);
				UnaryExpression();
				break;
			default:
				jj_la1[81] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
	}

	final public Expression PostfixExpression() throws ParseException {
		Expression ret;
		UnaryExpr.Operator op;
		ret = PrimaryExpression();
		if (jj_2_24(2)) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case INCR:
				jj_consume_token(INCR);
				op = UnaryExpr.Operator.posIncrement;
				break;
			case DECR:
				jj_consume_token(DECR);
				op = UnaryExpr.Operator.posDecrement;
				break;
			default:
				jj_la1[82] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			ret = new UnaryExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, op);
		} else {
			;
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression CastExpression() throws ParseException {
		Expression ret;
		Type type;
		int line;
		int column;
		jj_consume_token(LPAREN);
		line = token.beginLine;
		column = token.beginColumn;
		type = Type();
		jj_consume_token(RPAREN);
		ret = UnaryExpression();
		ret = new CastExpr(line, column, token.endLine, token.endColumn, type, ret);
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression PrimaryExpression() throws ParseException {
		Expression ret;
		Expression inner;
		ret = PrimaryPrefix();
		label_35: while (true) {
			if (jj_2_25(2)) {
				;
			} else {
				break label_35;
			}
			ret = PrimarySuffix(ret);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression PrimaryExpressionWithoutSuperSuffix() throws ParseException {
		Expression ret;
		Expression inner;
		ret = PrimaryPrefix();
		label_36: while (true) {
			if (jj_2_26(2147483647)) {
				;
			} else {
				break label_36;
			}
			ret = PrimarySuffixWithoutSuper(ret);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression PrimaryPrefix() throws ParseException {
		Expression ret;
		String name;
		List typeArgs = null;
		List args = null;
		boolean hasArgs = false;
		Type type;
		int line;
		int column;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case FALSE:
		case NULL:
		case TRUE:
		case LONG_LITERAL:
		case INTEGER_LITERAL:
		case FLOATING_POINT_LITERAL:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
			ret = Literal();
			break;
		case THIS:
			jj_consume_token(THIS);
			ret = new ThisExpr(token.beginLine, token.beginColumn, token.endLine, token.endColumn, null);
			break;
		case SUPER:
			jj_consume_token(SUPER);
			ret = new SuperExpr(token.beginLine, token.beginColumn, token.endLine, token.endColumn, null);
			jj_consume_token(DOT);
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case LT:
				typeArgs = TypeArguments();
				typeArgs.remove(0);
				break;
			default:
				jj_la1[83] = jj_gen;
				;
			}
			jj_consume_token(IDENTIFIER);
			name = token.image;
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case LPAREN:
				args = Arguments();
				hasArgs = true;
				break;
			default:
				jj_la1[84] = jj_gen;
				;
			}
			ret = hasArgs
					? new MethodCallExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, typeArgs, name, args)
					: new FieldAccessExpr(ret.getBeginLine(), ret.getBeginColumn(), token.endLine, token.endColumn, ret, null, name);
			break;
		case LPAREN:
			jj_consume_token(LPAREN);
			line = token.beginLine;
			column = token.beginColumn;
			ret = Expression();
			jj_consume_token(RPAREN);
			ret = new EnclosedExpr(line, column, token.endLine, token.endColumn, ret);
			break;
		case NEW:
			ret = AllocationExpression(null);
			break;
		default:
			jj_la1[86] = jj_gen;
			if (jj_2_27(2147483647)) {
				type = ResultType();
				jj_consume_token(DOT);
				jj_consume_token(CLASS);
				ret = new ClassExpr(type.getBeginLine(), type.getBeginColumn(), token.endLine, token.endColumn, type);
			} else {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case IDENTIFIER:
					jj_consume_token(IDENTIFIER);
					name = token.image;
					line = token.beginLine;
					column = token.beginColumn;
					switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
					case LPAREN:
						args = Arguments();
						hasArgs = true;
						break;
					default:
						jj_la1[85] = jj_gen;
						;
					}
					ret = hasArgs
							? new MethodCallExpr(line, column, token.endLine, token.endColumn, null, null, name, args)
							: new NameExpr(line, column, token.endLine, token.endColumn, name);
					break;
				default:
					jj_la1[87] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
				}
			}
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression PrimarySuffix(Expression scope) throws ParseException {
		Expression ret;
		if (jj_2_28(2)) {
			ret = PrimarySuffixWithoutSuper(scope);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case DOT:
				jj_consume_token(DOT);
				jj_consume_token(SUPER);
				ret = new SuperExpr(scope.getBeginLine(), scope.getBeginColumn(), token.endLine, token.endColumn, scope);
				break;
			default:
				jj_la1[88] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression PrimarySuffixWithoutSuper(Expression scope) throws ParseException {
		Expression ret;
		List typeArgs = null;
		List args = null;
		boolean hasArgs = false;
		String name;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case DOT:
			jj_consume_token(DOT);
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case THIS:
				jj_consume_token(THIS);
				ret = new ThisExpr(scope.getBeginLine(), scope.getBeginColumn(), token.endLine, token.endColumn, scope);
				break;
			case NEW:
				ret = AllocationExpression(scope);
				break;
			default:
				jj_la1[91] = jj_gen;
				if (jj_2_29(2147483647)) {
					switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
					case LT:
						typeArgs = TypeArguments();
						typeArgs.remove(0);
						break;
					default:
						jj_la1[89] = jj_gen;
						;
					}
					jj_consume_token(IDENTIFIER);
					name = token.image;
					switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
					case LPAREN:
						args = Arguments();
						hasArgs = true;
						break;
					default:
						jj_la1[90] = jj_gen;
						;
					}
					ret = hasArgs
							? new MethodCallExpr(scope.getBeginLine(), scope.getBeginColumn(), token.endLine, token.endColumn, scope, typeArgs, name, args)
							: new FieldAccessExpr(scope.getBeginLine(), scope.getBeginColumn(), token.endLine, token.endColumn, scope, typeArgs, name);
				} else {
					jj_consume_token(-1);
					throw new ParseException();
				}
			}
			break;
		case LBRACKET:
			jj_consume_token(LBRACKET);
			ret = Expression();
			jj_consume_token(RBRACKET);
			ret = new ArrayAccessExpr(scope.getBeginLine(), scope.getBeginColumn(), token.endLine, token.endColumn, scope, ret);
			break;
		default:
			jj_la1[92] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression Literal() throws ParseException {
		Expression ret;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case INTEGER_LITERAL:
			jj_consume_token(INTEGER_LITERAL);
			ret = new IntegerLiteralExpr(token.beginLine, token.beginColumn, token.endLine, token.endColumn, token.image);
			break;
		case LONG_LITERAL:
			jj_consume_token(LONG_LITERAL);
			ret = new LongLiteralExpr(token.beginLine, token.beginColumn, token.endLine, token.endColumn, token.image);
			break;
		case FLOATING_POINT_LITERAL:
			jj_consume_token(FLOATING_POINT_LITERAL);
			ret = new DoubleLiteralExpr(token.beginLine, token.beginColumn, token.endLine, token.endColumn, token.image);
			break;
		case CHARACTER_LITERAL:
			jj_consume_token(CHARACTER_LITERAL);
			ret = new CharLiteralExpr(token.beginLine, token.beginColumn, token.endLine, token.endColumn, token.image.substring(1, token.image.length() - 1));
			break;
		case STRING_LITERAL:
			jj_consume_token(STRING_LITERAL);
			ret = new StringLiteralExpr(token.beginLine, token.beginColumn, token.endLine, token.endColumn, token.image.substring(1, token.image.length() - 1));
			break;
		case FALSE:
		case TRUE:
			ret = BooleanLiteral();
			break;
		case NULL:
			ret = NullLiteral();
			break;
		default:
			jj_la1[93] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression BooleanLiteral() throws ParseException {
		Expression ret;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case TRUE:
			jj_consume_token(TRUE);
			ret = new BooleanLiteralExpr(token.beginLine, token.beginColumn, token.endLine, token.endColumn, true);
			break;
		case FALSE:
			jj_consume_token(FALSE);
			ret = new BooleanLiteralExpr(token.beginLine, token.beginColumn, token.endLine, token.endColumn, false);
			break;
		default:
			jj_la1[94] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression NullLiteral() throws ParseException {
		jj_consume_token(NULL);
		{
			if (true) {
				return new NullLiteralExpr(token.beginLine, token.beginColumn, token.endLine, token.endColumn);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List Arguments() throws ParseException {
		List ret = null;
		jj_consume_token(LPAREN);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FALSE:
		case FLOAT:
		case INT:
		case LONG:
		case NEW:
		case NULL:
		case SHORT:
		case SUPER:
		case THIS:
		case TRUE:
		case VOID:
		case LONG_LITERAL:
		case INTEGER_LITERAL:
		case FLOATING_POINT_LITERAL:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
		case IDENTIFIER:
		case LPAREN:
		case BANG:
		case TILDE:
		case INCR:
		case DECR:
		case PLUS:
		case MINUS:
			ret = ArgumentList();
			break;
		default:
			jj_la1[95] = jj_gen;
			;
		}
		jj_consume_token(RPAREN);
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List ArgumentList() throws ParseException {
		List ret = new LinkedList();
		Expression expr;
		expr = Expression();
		ret.add(expr);
		label_37: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case COMMA:
				;
				break;
			default:
				jj_la1[96] = jj_gen;
				break label_37;
			}
			jj_consume_token(COMMA);
			expr = Expression();
			ret.add(expr);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression AllocationExpression(Expression scope) throws ParseException {
		Expression ret;
		Type type;
		Object[] arr = null;
		List typeArgs = null;
		List anonymousBody = null;
		List args;
		int line;
		int column;
		jj_consume_token(NEW);
		if (scope == null) {
			line = token.beginLine;
			column = token.beginColumn;
		} else {
			line = scope.getBeginLine();
			column = scope.getBeginColumn();
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FLOAT:
		case INT:
		case LONG:
		case SHORT:
			type = PrimitiveType();
			arr = ArrayDimsAndInits();
			if (arr[0] instanceof Integer) {
				ret = new ArrayCreationExpr(line, column, token.endLine, token.endColumn, type, ((Integer) arr[0]).intValue(), (ArrayInitializerExpr) arr[1]);
			} else {
				ret = new ArrayCreationExpr(line, column, token.endLine, token.endColumn, type, (List) arr[0], ((Integer) arr[1]).intValue());
			}
			break;
		default:
			jj_la1[98] = jj_gen;
			if (jj_2_31(2147483647)) {
				type = ClassOrInterfaceType();
				arr = ArrayDimsAndInits();
				if (arr[0] instanceof Integer) {
					ret = new ArrayCreationExpr(line, column, token.endLine, token.endColumn, type, ((Integer) arr[0]).intValue(), (ArrayInitializerExpr) arr[1]);
				} else {
					ret = new ArrayCreationExpr(line, column, token.endLine, token.endColumn, type, (List) arr[0], ((Integer) arr[1]).intValue());
				}
			} else {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case IDENTIFIER:
				case LT:
					switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
					case LT:
						typeArgs = TypeArguments();
						typeArgs.remove(0);
						break;
					default:
						jj_la1[97] = jj_gen;
						;
					}
					type = ClassOrInterfaceType();
					args = Arguments();
					if (jj_2_30(2)) {
						anonymousBody = ClassOrInterfaceBody(false);
					} else {
						;
					}
					ret = new ObjectCreationExpr(line, column, token.endLine, token.endColumn, scope, (ClassOrInterfaceType) type, typeArgs, args, anonymousBody);
					break;
				default:
					jj_la1[99] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
				}
			}
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	/*
	 * The third LOOKAHEAD specification below is to parse to PrimarySuffix
	 * if there is an expression between the "[...]".
	 */
	final public Object[] ArrayDimsAndInits() throws ParseException {
		Object[] ret = new Object[2];
		Expression expr;
		List inits = null;
		int i = 0;
		if (jj_2_34(2)) {
			label_38: while (true) {
				jj_consume_token(LBRACKET);
				expr = Expression();
				inits = add(inits, expr);
				jj_consume_token(RBRACKET);
				if (jj_2_32(2)) {
					;
				} else {
					break label_38;
				}
			}
			label_39: while (true) {
				if (jj_2_33(2)) {
					;
				} else {
					break label_39;
				}
				jj_consume_token(LBRACKET);
				jj_consume_token(RBRACKET);
				i++;
			}
			ret[0] = inits;
			ret[1] = new Integer(i);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case LBRACKET:
				label_40: while (true) {
					jj_consume_token(LBRACKET);
					jj_consume_token(RBRACKET);
					i++;
					switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
					case LBRACKET:
						;
						break;
					default:
						jj_la1[100] = jj_gen;
						break label_40;
					}
				}
				expr = ArrayInitializer();
				ret[0] = new Integer(i);
				ret[1] = expr;
				break;
			default:
				jj_la1[101] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	/*
	 * Statement syntax follows.
	 */
	final public Statement Statement() throws ParseException {
		Statement ret;
		if (jj_2_35(2)) {
			ret = LabeledStatement();
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case ASSERT:
				ret = AssertStatement();
				break;
			case LBRACE:
				ret = Block();
				break;
			case SEMICOLON:
				ret = EmptyStatement();
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FALSE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case NULL:
			case SHORT:
			case SUPER:
			case THIS:
			case TRUE:
			case VOID:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case INCR:
			case DECR:
				ret = StatementExpression();
				break;
			case SWITCH:
				ret = SwitchStatement();
				break;
			case IF:
				ret = IfStatement();
				break;
			case WHILE:
				ret = WhileStatement();
				break;
			case DO:
				ret = DoStatement();
				break;
			case FOR:
				ret = ForStatement();
				break;
			case BREAK:
				ret = BreakStatement();
				break;
			case CONTINUE:
				ret = ContinueStatement();
				break;
			case RETURN:
				ret = ReturnStatement();
				break;
			case THROW:
				ret = ThrowStatement();
				break;
			case SYNCHRONIZED:
				ret = SynchronizedStatement();
				break;
			case TRY:
				ret = TryStatement();
				break;
			default:
				jj_la1[102] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public AssertStmt AssertStatement() throws ParseException {
		Expression check;
		Expression msg = null;
		int line;
		int column;
		jj_consume_token(ASSERT);
		line = token.beginLine;
		column = token.beginColumn;
		check = Expression();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case COLON:
			jj_consume_token(COLON);
			msg = Expression();
			break;
		default:
			jj_la1[103] = jj_gen;
			;
		}
		jj_consume_token(SEMICOLON);
		{
			if (true) {
				return new AssertStmt(line, column, token.endLine, token.endColumn, check, msg);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public LabeledStmt LabeledStatement() throws ParseException {
		String label;
		Statement stmt;
		int line;
		int column;
		jj_consume_token(IDENTIFIER);
		line = token.beginLine;
		column = token.beginColumn;
		label = token.image;
		jj_consume_token(COLON);
		stmt = Statement();
		{
			if (true) {
				return new LabeledStmt(line, column, token.endLine, token.endColumn, label, stmt);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public BlockStmt Block() throws ParseException {
		List stmts;
		int beginLine;
		int beginColumn;
		jj_consume_token(LBRACE);
		beginLine = token.beginLine;
		beginColumn = token.beginColumn;
		stmts = Statements();
		jj_consume_token(RBRACE);
		{
			if (true) {
				return new BlockStmt(beginLine, beginColumn, token.endLine, token.endColumn, stmts);
			}
		}
		throw new Error("Missing return statement in function");
	}

	/*
	 * Classes inside block stametents can only be abstract or final. The semantic must check it.
	 */
	final public Statement BlockStatement() throws ParseException {
		Statement ret;
		Expression expr;
		ClassOrInterfaceDeclaration typeDecl;
		Modifier modifier;
		if (jj_2_36(2147483647)) {
			pushJavadoc();
			modifier = Modifiers();
			typeDecl = ClassOrInterfaceDeclaration(modifier);
			ret = new TypeDeclarationStmt(typeDecl.getBeginLine(), typeDecl.getBeginColumn(), token.endLine, token.endColumn, typeDecl);
		} else if (jj_2_37(2147483647)) {
			expr = VariableDeclarationExpression();
			jj_consume_token(SEMICOLON);
			ret = new ExpressionStmt(expr.getBeginLine(), expr.getBeginColumn(), token.endLine, token.endColumn, expr);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case ASSERT:
			case BOOLEAN:
			case BREAK:
			case BYTE:
			case CHAR:
			case CONTINUE:
			case DO:
			case DOUBLE:
			case FALSE:
			case FLOAT:
			case FOR:
			case IF:
			case INT:
			case LONG:
			case NEW:
			case NULL:
			case RETURN:
			case SHORT:
			case SUPER:
			case SWITCH:
			case SYNCHRONIZED:
			case THIS:
			case THROW:
			case TRUE:
			case TRY:
			case VOID:
			case WHILE:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case LBRACE:
			case SEMICOLON:
			case INCR:
			case DECR:
				ret = Statement();
				break;
			default:
				jj_la1[104] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public VariableDeclarationExpr VariableDeclarationExpression() throws ParseException {
		Modifier modifier;
		Type type;
		List vars = new LinkedList();
		VariableDeclarator var;
		modifier = Modifiers();
		type = Type();
		var = VariableDeclarator();
		vars.add(var);
		label_41: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case COMMA:
				;
				break;
			default:
				jj_la1[105] = jj_gen;
				break label_41;
			}
			jj_consume_token(COMMA);
			var = VariableDeclarator();
			vars.add(var);
		}
		int line = modifier.beginLine;
		int column = modifier.beginColumn;
		if (line == -1) {
			line = type.getBeginLine();
			column = type.getBeginColumn();
		}
		{
			if (true) {
				return new VariableDeclarationExpr(line, column, token.endLine, token.endColumn, modifier.modifiers, modifier.annotations, type, vars);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public EmptyStmt EmptyStatement() throws ParseException {
		jj_consume_token(SEMICOLON);
		{
			if (true) {
				return new EmptyStmt(token.beginLine, token.beginColumn, token.endLine, token.endColumn);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public ExpressionStmt StatementExpression() throws ParseException {
		Expression expr;
		AssignExpr.Operator op;
		Expression value;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case INCR:
			expr = PreIncrementExpression();
			break;
		case DECR:
			expr = PreDecrementExpression();
			break;
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FALSE:
		case FLOAT:
		case INT:
		case LONG:
		case NEW:
		case NULL:
		case SHORT:
		case SUPER:
		case THIS:
		case TRUE:
		case VOID:
		case LONG_LITERAL:
		case INTEGER_LITERAL:
		case FLOATING_POINT_LITERAL:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
		case IDENTIFIER:
		case LPAREN:
			expr = PrimaryExpression();
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case ASSIGN:
			case INCR:
			case DECR:
			case PLUSASSIGN:
			case MINUSASSIGN:
			case STARASSIGN:
			case SLASHASSIGN:
			case ANDASSIGN:
			case ORASSIGN:
			case XORASSIGN:
			case REMASSIGN:
			case LSHIFTASSIGN:
			case RSIGNEDSHIFTASSIGN:
			case RUNSIGNEDSHIFTASSIGN:
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case INCR:
					jj_consume_token(INCR);
					expr = new UnaryExpr(expr.getBeginLine(), expr.getBeginColumn(), token.endLine, token.endColumn, expr, UnaryExpr.Operator.posIncrement);
					break;
				case DECR:
					jj_consume_token(DECR);
					expr = new UnaryExpr(expr.getBeginLine(), expr.getBeginColumn(), token.endLine, token.endColumn, expr, UnaryExpr.Operator.posDecrement);
					break;
				case ASSIGN:
				case PLUSASSIGN:
				case MINUSASSIGN:
				case STARASSIGN:
				case SLASHASSIGN:
				case ANDASSIGN:
				case ORASSIGN:
				case XORASSIGN:
				case REMASSIGN:
				case LSHIFTASSIGN:
				case RSIGNEDSHIFTASSIGN:
				case RUNSIGNEDSHIFTASSIGN:
					op = AssignmentOperator();
					value = Expression();
					expr = new AssignExpr(expr.getBeginLine(), expr.getBeginColumn(), token.endLine, token.endColumn, expr, value, op);
					break;
				default:
					jj_la1[106] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
				}
				break;
			default:
				jj_la1[107] = jj_gen;
				;
			}
			break;
		default:
			jj_la1[108] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		jj_consume_token(SEMICOLON);
		{
			if (true) {
				return new ExpressionStmt(expr.getBeginLine(), expr.getBeginColumn(), token.endLine, token.endColumn, expr);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public SwitchStmt SwitchStatement() throws ParseException {
		Expression selector;
		SwitchEntryStmt entry;
		List entries = null;
		int line;
		int column;
		jj_consume_token(SWITCH);
		line = token.beginLine;
		column = token.beginColumn;
		jj_consume_token(LPAREN);
		selector = Expression();
		jj_consume_token(RPAREN);
		jj_consume_token(LBRACE);
		label_42: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case CASE:
			case _DEFAULT:
				;
				break;
			default:
				jj_la1[109] = jj_gen;
				break label_42;
			}
			entry = SwitchEntry();
			entries = add(entries, entry);
		}
		jj_consume_token(RBRACE);
		{
			if (true) {
				return new SwitchStmt(line, column, token.endLine, token.endColumn, selector, entries);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public SwitchEntryStmt SwitchEntry() throws ParseException {
		Expression label = null;
		List stmts;
		int line;
		int column;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case CASE:
			jj_consume_token(CASE);
			line = token.beginLine;
			column = token.beginColumn;
			label = Expression();
			break;
		case _DEFAULT:
			jj_consume_token(_DEFAULT);
			line = token.beginLine;
			column = token.beginColumn;
			break;
		default:
			jj_la1[110] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		jj_consume_token(COLON);
		stmts = Statements();
		{
			if (true) {
				return new SwitchEntryStmt(line, column, token.endLine, token.endColumn, label, stmts);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public IfStmt IfStatement() throws ParseException {
		Expression condition;
		Statement thenStmt;
		Statement elseStmt = null;
		int line;
		int column;
		jj_consume_token(IF);
		line = token.beginLine;
		column = token.beginColumn;
		jj_consume_token(LPAREN);
		condition = Expression();
		jj_consume_token(RPAREN);
		thenStmt = Statement();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case ELSE:
			jj_consume_token(ELSE);
			elseStmt = Statement();
			break;
		default:
			jj_la1[111] = jj_gen;
			;
		}
		{
			if (true) {
				return new IfStmt(line, column, token.endLine, token.endColumn, condition, thenStmt, elseStmt);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public WhileStmt WhileStatement() throws ParseException {
		Expression condition;
		Statement body;
		int line;
		int column;
		jj_consume_token(WHILE);
		line = token.beginLine;
		column = token.beginColumn;
		jj_consume_token(LPAREN);
		condition = Expression();
		jj_consume_token(RPAREN);
		body = Statement();
		{
			if (true) {
				return new WhileStmt(line, column, token.endLine, token.endColumn, condition, body);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public DoStmt DoStatement() throws ParseException {
		Expression condition;
		Statement body;
		int line;
		int column;
		jj_consume_token(DO);
		line = token.beginLine;
		column = token.beginColumn;
		body = Statement();
		jj_consume_token(WHILE);
		jj_consume_token(LPAREN);
		condition = Expression();
		jj_consume_token(RPAREN);
		jj_consume_token(SEMICOLON);
		{
			if (true) {
				return new DoStmt(line, column, token.endLine, token.endColumn, body, condition);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Statement ForStatement() throws ParseException {
		String id = null;
		VariableDeclarationExpr varExpr = null;
		Expression expr = null;
		List init = null;
		List update = null;
		Statement body;
		int line;
		int column;
		jj_consume_token(FOR);
		line = token.beginLine;
		column = token.beginColumn;
		jj_consume_token(LPAREN);
		if (jj_2_38(2147483647)) {
			varExpr = VariableDeclarationExpression();
			jj_consume_token(COLON);
			expr = Expression();
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FALSE:
			case FINAL:
			case FLOAT:
			case INT:
			case LONG:
			case NATIVE:
			case NEW:
			case NULL:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SUPER:
			case SYNCHRONIZED:
			case THIS:
			case TRANSIENT:
			case TRUE:
			case VOID:
			case VOLATILE:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case SEMICOLON:
			case AT:
			case BANG:
			case TILDE:
			case INCR:
			case DECR:
			case PLUS:
			case MINUS:
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case ABSTRACT:
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FALSE:
				case FINAL:
				case FLOAT:
				case INT:
				case LONG:
				case NATIVE:
				case NEW:
				case NULL:
				case PRIVATE:
				case PROTECTED:
				case PUBLIC:
				case SHORT:
				case STATIC:
				case STRICTFP:
				case SUPER:
				case SYNCHRONIZED:
				case THIS:
				case TRANSIENT:
				case TRUE:
				case VOID:
				case VOLATILE:
				case LONG_LITERAL:
				case INTEGER_LITERAL:
				case FLOATING_POINT_LITERAL:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
				case LPAREN:
				case AT:
				case BANG:
				case TILDE:
				case INCR:
				case DECR:
				case PLUS:
				case MINUS:
					init = ForInit();
					break;
				default:
					jj_la1[112] = jj_gen;
					;
				}
				jj_consume_token(SEMICOLON);
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FALSE:
				case FLOAT:
				case INT:
				case LONG:
				case NEW:
				case NULL:
				case SHORT:
				case SUPER:
				case THIS:
				case TRUE:
				case VOID:
				case LONG_LITERAL:
				case INTEGER_LITERAL:
				case FLOATING_POINT_LITERAL:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
				case LPAREN:
				case BANG:
				case TILDE:
				case INCR:
				case DECR:
				case PLUS:
				case MINUS:
					expr = Expression();
					break;
				default:
					jj_la1[113] = jj_gen;
					;
				}
				jj_consume_token(SEMICOLON);
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FALSE:
				case FLOAT:
				case INT:
				case LONG:
				case NEW:
				case NULL:
				case SHORT:
				case SUPER:
				case THIS:
				case TRUE:
				case VOID:
				case LONG_LITERAL:
				case INTEGER_LITERAL:
				case FLOATING_POINT_LITERAL:
				case CHARACTER_LITERAL:
				case STRING_LITERAL:
				case IDENTIFIER:
				case LPAREN:
				case BANG:
				case TILDE:
				case INCR:
				case DECR:
				case PLUS:
				case MINUS:
					update = ForUpdate();
					break;
				default:
					jj_la1[114] = jj_gen;
					;
				}
				break;
			default:
				jj_la1[115] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		jj_consume_token(RPAREN);
		body = Statement();
		if (varExpr != null) {
			{
				if (true) {
					return new ForeachStmt(line, column, token.endLine, token.endColumn, varExpr, expr, body);
				}
			}
		}
		{
			if (true) {
				return new ForStmt(line, column, token.endLine, token.endColumn, init, expr, update, body);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List ForInit() throws ParseException {
		List ret;
		Expression expr;
		if (jj_2_39(2147483647)) {
			expr = VariableDeclarationExpression();
			ret = new LinkedList();
			ret.add(expr);
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FALSE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case NULL:
			case SHORT:
			case SUPER:
			case THIS:
			case TRUE:
			case VOID:
			case LONG_LITERAL:
			case INTEGER_LITERAL:
			case FLOATING_POINT_LITERAL:
			case CHARACTER_LITERAL:
			case STRING_LITERAL:
			case IDENTIFIER:
			case LPAREN:
			case BANG:
			case TILDE:
			case INCR:
			case DECR:
			case PLUS:
			case MINUS:
				ret = ExpressionList();
				break;
			default:
				jj_la1[116] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List ExpressionList() throws ParseException {
		List ret = new LinkedList();
		Expression expr;
		expr = Expression();
		ret.add(expr);
		label_43: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case COMMA:
				;
				break;
			default:
				jj_la1[117] = jj_gen;
				break label_43;
			}
			jj_consume_token(COMMA);
			expr = Expression();
			ret.add(expr);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List ForUpdate() throws ParseException {
		List ret;
		ret = ExpressionList();
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public BreakStmt BreakStatement() throws ParseException {
		String id = null;
		int line;
		int column;
		jj_consume_token(BREAK);
		line = token.beginLine;
		column = token.beginColumn;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case IDENTIFIER:
			jj_consume_token(IDENTIFIER);
			id = token.image;
			break;
		default:
			jj_la1[118] = jj_gen;
			;
		}
		jj_consume_token(SEMICOLON);
		{
			if (true) {
				return new BreakStmt(line, column, token.endLine, token.endColumn, id);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public ContinueStmt ContinueStatement() throws ParseException {
		String id = null;
		int line;
		int column;
		jj_consume_token(CONTINUE);
		line = token.beginLine;
		column = token.beginColumn;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case IDENTIFIER:
			jj_consume_token(IDENTIFIER);
			id = token.image;
			break;
		default:
			jj_la1[119] = jj_gen;
			;
		}
		jj_consume_token(SEMICOLON);
		{
			if (true) {
				return new ContinueStmt(line, column, token.endLine, token.endColumn, id);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public ReturnStmt ReturnStatement() throws ParseException {
		Expression expr = null;
		int line;
		int column;
		jj_consume_token(RETURN);
		line = token.beginLine;
		column = token.beginColumn;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FALSE:
		case FLOAT:
		case INT:
		case LONG:
		case NEW:
		case NULL:
		case SHORT:
		case SUPER:
		case THIS:
		case TRUE:
		case VOID:
		case LONG_LITERAL:
		case INTEGER_LITERAL:
		case FLOATING_POINT_LITERAL:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
		case IDENTIFIER:
		case LPAREN:
		case BANG:
		case TILDE:
		case INCR:
		case DECR:
		case PLUS:
		case MINUS:
			expr = Expression();
			break;
		default:
			jj_la1[120] = jj_gen;
			;
		}
		jj_consume_token(SEMICOLON);
		{
			if (true) {
				return new ReturnStmt(line, column, token.endLine, token.endColumn, expr);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public ThrowStmt ThrowStatement() throws ParseException {
		Expression expr;
		int line;
		int column;
		jj_consume_token(THROW);
		line = token.beginLine;
		column = token.beginColumn;
		expr = Expression();
		jj_consume_token(SEMICOLON);
		{
			if (true) {
				return new ThrowStmt(line, column, token.endLine, token.endColumn, expr);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public SynchronizedStmt SynchronizedStatement() throws ParseException {
		Expression expr;
		BlockStmt block;
		int line;
		int column;
		jj_consume_token(SYNCHRONIZED);
		line = token.beginLine;
		column = token.beginColumn;
		jj_consume_token(LPAREN);
		expr = Expression();
		jj_consume_token(RPAREN);
		block = Block();
		{
			if (true) {
				return new SynchronizedStmt(line, column, token.endLine, token.endColumn, expr, block);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public TryStmt TryStatement() throws ParseException {
		BlockStmt tryBlock;
		BlockStmt finallyBlock = null;
		List catchs = null;
		Parameter except;
		BlockStmt catchBlock;
		int line;
		int column;
		int cLine;
		int cColumn;
		jj_consume_token(TRY);
		line = token.beginLine;
		column = token.beginColumn;
		tryBlock = Block();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case CATCH:
			label_44: while (true) {
				jj_consume_token(CATCH);
				cLine = token.beginLine;
				cColumn = token.beginColumn;
				jj_consume_token(LPAREN);
				except = FormalParameter();
				jj_consume_token(RPAREN);
				catchBlock = Block();
				catchs = add(catchs, new CatchClause(cLine, cColumn, token.endLine, token.endColumn, except, catchBlock));
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case CATCH:
					;
					break;
				default:
					jj_la1[121] = jj_gen;
					break label_44;
				}
			}
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case FINALLY:
				jj_consume_token(FINALLY);
				finallyBlock = Block();
				break;
			default:
				jj_la1[122] = jj_gen;
				;
			}
			break;
		case FINALLY:
			jj_consume_token(FINALLY);
			finallyBlock = Block();
			break;
		default:
			jj_la1[123] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return new TryStmt(line, column, token.endLine, token.endColumn, tryBlock, catchs, finallyBlock);
			}
		}
		throw new Error("Missing return statement in function");
	}

	/*
	 * We use productions to match >>>, >> and > so that we can keep the
	 * type declaration syntax with generics clean
	 */
	final public void RUNSIGNEDSHIFT() throws ParseException {
		if (getToken(1).kind == GT &&
				((GTToken) getToken(1)).realKind == RUNSIGNEDSHIFT) {

		} else {
			jj_consume_token(-1);
			throw new ParseException();
		}
		jj_consume_token(GT);
		jj_consume_token(GT);
		jj_consume_token(GT);
	}

	final public void RSIGNEDSHIFT() throws ParseException {
		if (getToken(1).kind == GT &&
				((GTToken) getToken(1)).realKind == RSIGNEDSHIFT) {

		} else {
			jj_consume_token(-1);
			throw new ParseException();
		}
		jj_consume_token(GT);
		jj_consume_token(GT);
	}

	/* Annotation syntax follows. */
	final public AnnotationExpr Annotation() throws ParseException {
		AnnotationExpr ret;
		if (jj_2_40(2147483647)) {
			ret = NormalAnnotation();
		} else if (jj_2_41(2147483647)) {
			ret = SingleMemberAnnotation();
		} else {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case AT:
				ret = MarkerAnnotation();
				break;
			default:
				jj_la1[124] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public NormalAnnotationExpr NormalAnnotation() throws ParseException {
		NameExpr name;
		List pairs = null;
		int line;
		int column;
		jj_consume_token(AT);
		line = token.beginLine;
		column = token.beginColumn;
		name = Name();
		jj_consume_token(LPAREN);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case IDENTIFIER:
			pairs = MemberValuePairs();
			break;
		default:
			jj_la1[125] = jj_gen;
			;
		}
		jj_consume_token(RPAREN);
		{
			if (true) {
				return new NormalAnnotationExpr(line, column, token.endLine, token.endColumn, name, pairs);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public MarkerAnnotationExpr MarkerAnnotation() throws ParseException {
		NameExpr name;
		int line;
		int column;
		jj_consume_token(AT);
		line = token.beginLine;
		column = token.beginColumn;
		name = Name();
		{
			if (true) {
				return new MarkerAnnotationExpr(line, column, token.endLine, token.endColumn, name);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public SingleMemberAnnotationExpr SingleMemberAnnotation() throws ParseException {
		NameExpr name;
		Expression memberVal;
		int line;
		int column;
		jj_consume_token(AT);
		line = token.beginLine;
		column = token.beginColumn;
		name = Name();
		jj_consume_token(LPAREN);
		memberVal = MemberValue();
		jj_consume_token(RPAREN);
		{
			if (true) {
				return new SingleMemberAnnotationExpr(line, column, token.endLine, token.endColumn, name, memberVal);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List MemberValuePairs() throws ParseException {
		List ret = new LinkedList();
		MemberValuePair pair;
		pair = MemberValuePair();
		ret.add(pair);
		label_45: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case COMMA:
				;
				break;
			default:
				jj_la1[126] = jj_gen;
				break label_45;
			}
			jj_consume_token(COMMA);
			pair = MemberValuePair();
			ret.add(pair);
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public MemberValuePair MemberValuePair() throws ParseException {
		String name;
		Expression value;
		int line;
		int column;
		jj_consume_token(IDENTIFIER);
		name = token.image;
		line = token.beginLine;
		column = token.beginColumn;
		jj_consume_token(ASSIGN);
		value = MemberValue();
		{
			if (true) {
				return new MemberValuePair(line, column, token.endLine, token.endColumn, name, value);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression MemberValue() throws ParseException {
		Expression ret;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case AT:
			ret = Annotation();
			break;
		case LBRACE:
			ret = MemberValueArrayInitializer();
			break;
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FALSE:
		case FLOAT:
		case INT:
		case LONG:
		case NEW:
		case NULL:
		case SHORT:
		case SUPER:
		case THIS:
		case TRUE:
		case VOID:
		case LONG_LITERAL:
		case INTEGER_LITERAL:
		case FLOATING_POINT_LITERAL:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
		case IDENTIFIER:
		case LPAREN:
		case BANG:
		case TILDE:
		case INCR:
		case DECR:
		case PLUS:
		case MINUS:
			ret = ConditionalExpression();
			break;
		default:
			jj_la1[127] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression MemberValueArrayInitializer() throws ParseException {
		List ret = new LinkedList();
		Expression member;
		int line;
		int column;
		jj_consume_token(LBRACE);
		line = token.beginLine;
		column = token.beginColumn;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case DOUBLE:
		case FALSE:
		case FLOAT:
		case INT:
		case LONG:
		case NEW:
		case NULL:
		case SHORT:
		case SUPER:
		case THIS:
		case TRUE:
		case VOID:
		case LONG_LITERAL:
		case INTEGER_LITERAL:
		case FLOATING_POINT_LITERAL:
		case CHARACTER_LITERAL:
		case STRING_LITERAL:
		case IDENTIFIER:
		case LPAREN:
		case LBRACE:
		case AT:
		case BANG:
		case TILDE:
		case INCR:
		case DECR:
		case PLUS:
		case MINUS:
			member = MemberValue();
			ret.add(member);
			label_46: while (true) {
				if (jj_2_42(2)) {
					;
				} else {
					break label_46;
				}
				jj_consume_token(COMMA);
				member = MemberValue();
				ret.add(member);
			}
			break;
		default:
			jj_la1[128] = jj_gen;
			;
		}
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case COMMA:
			jj_consume_token(COMMA);
			break;
		default:
			jj_la1[129] = jj_gen;
			;
		}
		jj_consume_token(RBRACE);
		{
			if (true) {
				return new ArrayInitializerExpr(line, column, token.endLine, token.endColumn, ret);
			}
		}
		throw new Error("Missing return statement in function");
	}

	/* Annotation Types. */
	final public AnnotationDeclaration AnnotationTypeDeclaration(Modifier modifier) throws ParseException {
		String name;
		List members;
		int line = modifier.beginLine;
		int column = modifier.beginColumn;
		jj_consume_token(AT);
		if (line == -1) {
			line = token.beginLine;
			column = token.beginColumn;
		}
		jj_consume_token(INTERFACE);
		jj_consume_token(IDENTIFIER);
		name = token.image;
		members = AnnotationTypeBody();
		{
			if (true) {
				return new AnnotationDeclaration(line, column, token.endLine, token.endColumn, popJavadoc(), modifier.modifiers, modifier.annotations, name, members);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public List AnnotationTypeBody() throws ParseException {
		List ret = null;
		BodyDeclaration member;
		jj_consume_token(LBRACE);
		label_47: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DOUBLE:
			case ENUM:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOLATILE:
			case IDENTIFIER:
			case SEMICOLON:
			case AT:
				;
				break;
			default:
				jj_la1[130] = jj_gen;
				break label_47;
			}
			member = AnnotationBodyDeclaration();
			ret = add(ret, member);
		}
		jj_consume_token(RBRACE);
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public BodyDeclaration AnnotationBodyDeclaration() throws ParseException {
		Modifier modifier;
		BodyDeclaration ret;
		pushJavadoc();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case SEMICOLON:
			jj_consume_token(SEMICOLON);
			ret = new EmptyTypeDeclaration(token.beginLine, token.beginColumn, token.endLine, token.endColumn, popJavadoc());
			break;
		case ABSTRACT:
		case BOOLEAN:
		case BYTE:
		case CHAR:
		case CLASS:
		case DOUBLE:
		case ENUM:
		case FINAL:
		case FLOAT:
		case INT:
		case INTERFACE:
		case LONG:
		case NATIVE:
		case PRIVATE:
		case PROTECTED:
		case PUBLIC:
		case SHORT:
		case STATIC:
		case STRICTFP:
		case SYNCHRONIZED:
		case TRANSIENT:
		case VOLATILE:
		case IDENTIFIER:
		case AT:
			modifier = Modifiers();
			if (jj_2_43(2147483647)) {
				ret = AnnotationTypeMemberDeclaration(modifier);
			} else {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case CLASS:
				case INTERFACE:
					ret = ClassOrInterfaceDeclaration(modifier);
					break;
				case ENUM:
					ret = EnumDeclaration(modifier);
					break;
				case AT:
					ret = AnnotationTypeDeclaration(modifier);
					break;
				case BOOLEAN:
				case BYTE:
				case CHAR:
				case DOUBLE:
				case FLOAT:
				case INT:
				case LONG:
				case SHORT:
				case IDENTIFIER:
					ret = FieldDeclaration(modifier);
					break;
				default:
					jj_la1[131] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
				}
			}
			break;
		default:
			jj_la1[132] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public AnnotationMemberDeclaration AnnotationTypeMemberDeclaration(Modifier modifier) throws ParseException {
		Type type;
		String name;
		Expression defaultVal = null;
		type = Type();
		jj_consume_token(IDENTIFIER);
		name = token.image;
		jj_consume_token(LPAREN);
		jj_consume_token(RPAREN);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case _DEFAULT:
			defaultVal = DefaultValue();
			break;
		default:
			jj_la1[133] = jj_gen;
			;
		}
		jj_consume_token(SEMICOLON);
		int line = modifier.beginLine;
		int column = modifier.beginColumn;
		{
			if (line == -1) {
				line = type.getBeginLine();
				column = type.getBeginColumn();
			}
		}
		{
			if (true) {
				return new AnnotationMemberDeclaration(line, column, token.endLine, token.endColumn, popJavadoc(), modifier.modifiers, modifier.annotations, type, name, defaultVal);
			}
		}
		throw new Error("Missing return statement in function");
	}

	final public Expression DefaultValue() throws ParseException {
		Expression ret;
		jj_consume_token(_DEFAULT);
		ret = MemberValue();
		{
			if (true) {
				return ret;
			}
		}
		throw new Error("Missing return statement in function");
	}

	private boolean jj_2_1(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_1();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(0, xla);
		}
	}

	private boolean jj_2_2(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_2();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(1, xla);
		}
	}

	private boolean jj_2_3(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_3();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(2, xla);
		}
	}

	private boolean jj_2_4(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_4();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(3, xla);
		}
	}

	private boolean jj_2_5(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_5();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(4, xla);
		}
	}

	private boolean jj_2_6(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_6();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(5, xla);
		}
	}

	private boolean jj_2_7(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_7();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(6, xla);
		}
	}

	private boolean jj_2_8(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_8();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(7, xla);
		}
	}

	private boolean jj_2_9(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_9();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(8, xla);
		}
	}

	private boolean jj_2_10(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_10();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(9, xla);
		}
	}

	private boolean jj_2_11(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_11();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(10, xla);
		}
	}

	private boolean jj_2_12(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_12();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(11, xla);
		}
	}

	private boolean jj_2_13(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_13();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(12, xla);
		}
	}

	private boolean jj_2_14(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_14();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(13, xla);
		}
	}

	private boolean jj_2_15(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_15();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(14, xla);
		}
	}

	private boolean jj_2_16(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_16();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(15, xla);
		}
	}

	private boolean jj_2_17(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_17();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(16, xla);
		}
	}

	private boolean jj_2_18(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_18();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(17, xla);
		}
	}

	private boolean jj_2_19(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_19();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(18, xla);
		}
	}

	private boolean jj_2_20(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_20();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(19, xla);
		}
	}

	private boolean jj_2_21(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_21();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(20, xla);
		}
	}

	private boolean jj_2_22(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_22();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(21, xla);
		}
	}

	private boolean jj_2_23(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_23();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(22, xla);
		}
	}

	private boolean jj_2_24(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_24();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(23, xla);
		}
	}

	private boolean jj_2_25(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_25();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(24, xla);
		}
	}

	private boolean jj_2_26(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_26();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(25, xla);
		}
	}

	private boolean jj_2_27(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_27();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(26, xla);
		}
	}

	private boolean jj_2_28(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_28();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(27, xla);
		}
	}

	private boolean jj_2_29(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_29();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(28, xla);
		}
	}

	private boolean jj_2_30(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_30();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(29, xla);
		}
	}

	private boolean jj_2_31(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_31();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(30, xla);
		}
	}

	private boolean jj_2_32(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_32();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(31, xla);
		}
	}

	private boolean jj_2_33(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_33();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(32, xla);
		}
	}

	private boolean jj_2_34(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_34();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(33, xla);
		}
	}

	private boolean jj_2_35(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_35();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(34, xla);
		}
	}

	private boolean jj_2_36(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_36();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(35, xla);
		}
	}

	private boolean jj_2_37(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_37();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(36, xla);
		}
	}

	private boolean jj_2_38(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_38();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(37, xla);
		}
	}

	private boolean jj_2_39(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_39();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(38, xla);
		}
	}

	private boolean jj_2_40(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_40();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(39, xla);
		}
	}

	private boolean jj_2_41(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_41();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(40, xla);
		}
	}

	private boolean jj_2_42(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_42();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(41, xla);
		}
	}

	private boolean jj_2_43(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_43();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(42, xla);
		}
	}

	private boolean jj_3R_100() {
		if (jj_3R_145()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_168() {
		if (jj_scan_token(ASSIGN)) {
			return true;
		}
		if (jj_3R_66()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_207() {
		if (jj_scan_token(CHARACTER_LITERAL)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_206() {
		if (jj_scan_token(FLOATING_POINT_LITERAL)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_66() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_100()) {
			jj_scanpos = xsp;
			if (jj_3R_101()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_205() {
		if (jj_scan_token(LONG_LITERAL)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_361() {
		if (jj_3R_372()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_204() {
		if (jj_scan_token(INTEGER_LITERAL)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_83() {
		if (jj_3R_71()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_167() {
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_200()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3_29() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_83()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_64() {
		if (jj_scan_token(LBRACKET)) {
			return true;
		}
		if (jj_scan_token(RBRACKET)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_184() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_204()) {
			jj_scanpos = xsp;
			if (jj_3R_205()) {
				jj_scanpos = xsp;
				if (jj_3R_206()) {
					jj_scanpos = xsp;
					if (jj_3R_207()) {
						jj_scanpos = xsp;
						if (jj_3R_208()) {
							jj_scanpos = xsp;
							if (jj_3R_209()) {
								jj_scanpos = xsp;
								if (jj_3R_210()) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_128() {
		if (jj_scan_token(LBRACKET)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		if (jj_scan_token(RBRACKET)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_196() {
		if (jj_3R_147()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_195() {
		if (jj_3R_71()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_133() {
		if (jj_3R_167()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_168()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_164() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_195()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		xsp = jj_scanpos;
		if (jj_3R_196()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_163() {
		if (jj_3R_187()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_173() {
		if (jj_3R_201()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_162() {
		if (jj_scan_token(THIS)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_321() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_133()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_5() {
		if (jj_3R_63()) {
			return true;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_64()) {
				jj_scanpos = xsp;
				break;
			}
		}
		xsp = jj_scanpos;
		if (jj_scan_token(87)) {
			jj_scanpos = xsp;
			if (jj_scan_token(90)) {
				jj_scanpos = xsp;
				if (jj_scan_token(86)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean jj_3R_62() {
		if (jj_3R_96()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_127() {
		if (jj_scan_token(DOT)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_162()) {
			jj_scanpos = xsp;
			if (jj_3R_163()) {
				jj_scanpos = xsp;
				if (jj_3R_164()) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean jj_3_4() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_62()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_247() {
		if (jj_3R_63()) {
			return true;
		}
		if (jj_3R_133()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_321()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_226() {
		if (jj_3R_248()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_81() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_127()) {
			jj_scanpos = xsp;
			if (jj_3R_128()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_198() {
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_225() {
		if (jj_3R_247()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_224() {
		if (jj_3R_246()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_372() {
		if (jj_scan_token(_DEFAULT)) {
			return true;
		}
		if (jj_3R_92()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_223() {
		if (jj_3R_245()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_126() {
		if (jj_scan_token(DOT)) {
			return true;
		}
		if (jj_scan_token(SUPER)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_222() {
		if (jj_3R_244()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_28() {
		if (jj_3R_81()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_221() {
		if (jj_3R_243()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_188() {
		if (jj_3R_147()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_80() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_28()) {
			jj_scanpos = xsp;
			if (jj_3R_126()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3_27() {
		if (jj_3R_82()) {
			return true;
		}
		if (jj_scan_token(DOT)) {
			return true;
		}
		if (jj_scan_token(CLASS)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_197() {
		if (jj_3R_88()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_221()) {
			jj_scanpos = xsp;
			if (jj_3R_222()) {
				jj_scanpos = xsp;
				if (jj_3R_223()) {
					jj_scanpos = xsp;
					if (jj_3R_224()) {
						jj_scanpos = xsp;
						if (jj_3R_225()) {
							jj_scanpos = xsp;
							if (jj_3R_226()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_156() {
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_188()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_360() {
		if (jj_3R_63()) {
			return true;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_361()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3_43() {
		if (jj_3R_63()) {
			return true;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_356() {
		if (jj_3R_247()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_6() {
		if (jj_3R_65()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_355() {
		if (jj_3R_245()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_155() {
		if (jj_3R_82()) {
			return true;
		}
		if (jj_scan_token(DOT)) {
			return true;
		}
		if (jj_scan_token(CLASS)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_354() {
		if (jj_3R_244()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_154() {
		if (jj_3R_187()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_353() {
		if (jj_3R_243()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_153() {
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_352() {
		if (jj_3R_360()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_165() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_6()) {
			jj_scanpos = xsp;
			if (jj_3R_197()) {
				jj_scanpos = xsp;
				if (jj_3R_198()) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean jj_3_42() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_92()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_26() {
		if (jj_3R_81()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_186() {
		if (jj_3R_147()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_185() {
		if (jj_3R_71()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_131() {
		if (jj_3R_165()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_346() {
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_347() {
		if (jj_3R_88()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_352()) {
			jj_scanpos = xsp;
			if (jj_3R_353()) {
				jj_scanpos = xsp;
				if (jj_3R_354()) {
					jj_scanpos = xsp;
					if (jj_3R_355()) {
						jj_scanpos = xsp;
						if (jj_3R_356()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_152() {
		if (jj_scan_token(SUPER)) {
			return true;
		}
		if (jj_scan_token(DOT)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_185()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		xsp = jj_scanpos;
		if (jj_3R_186()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_151() {
		if (jj_scan_token(THIS)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_84() {
		if (jj_scan_token(LBRACE)) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_131()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(RBRACE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_150() {
		if (jj_3R_184()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_105() {
		if (jj_3R_81()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_341() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_346()) {
			jj_scanpos = xsp;
			if (jj_3R_347()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3_3() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_61()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_228() {
		if (jj_scan_token(BIT_AND)) {
			return true;
		}
		if (jj_3R_85()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_330() {
		if (jj_3R_341()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_201() {
		if (jj_scan_token(EXTENDS)) {
			return true;
		}
		if (jj_3R_85()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_228()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_104() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_150()) {
			jj_scanpos = xsp;
			if (jj_3R_151()) {
				jj_scanpos = xsp;
				if (jj_3R_152()) {
					jj_scanpos = xsp;
					if (jj_3R_153()) {
						jj_scanpos = xsp;
						if (jj_3R_154()) {
							jj_scanpos = xsp;
							if (jj_3R_155()) {
								jj_scanpos = xsp;
								if (jj_3R_156()) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3_25() {
		if (jj_3R_80()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_317() {
		if (jj_scan_token(LBRACE)) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_330()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(RBRACE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_141() {
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_173()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_340() {
		if (jj_3R_84()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_68() {
		if (jj_3R_104()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_105()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_245() {
		if (jj_scan_token(AT)) {
			return true;
		}
		if (jj_scan_token(INTERFACE)) {
			return true;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		if (jj_3R_317()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_142() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_141()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_96() {
		if (jj_scan_token(LT)) {
			return true;
		}
		if (jj_3R_141()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_142()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(GT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_265() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_264()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_238() {
		if (jj_3R_104()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_25()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_283() {
		if (jj_3R_92()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_42()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_227() {
		if (jj_3R_249()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_169() {
		if (jj_scan_token(LBRACE)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_283()) {
			jj_scanpos = xsp;
		}
		xsp = jj_scanpos;
		if (jj_scan_token(87)) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(RBRACE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_339() {
		if (jj_3R_147()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_329() {
		if (jj_3R_165()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_95() {
		if (jj_3R_94()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_258() {
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_3R_63()) {
			return true;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		if (jj_3R_161()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_137() {
		if (jj_3R_121()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_61() {
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_95()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		xsp = jj_scanpos;
		if (jj_3R_339()) {
			jj_scanpos = xsp;
		}
		xsp = jj_scanpos;
		if (jj_3R_340()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_136() {
		if (jj_3R_169()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_135() {
		if (jj_3R_94()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_316() {
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_329()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_79() {
		if (jj_scan_token(DECR)) {
			return true;
		}
		return false;
	}

	private boolean jj_3_24() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_78()) {
			jj_scanpos = xsp;
			if (jj_3R_79()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_78() {
		if (jj_scan_token(INCR)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_92() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_135()) {
			jj_scanpos = xsp;
			if (jj_3R_136()) {
				jj_scanpos = xsp;
				if (jj_3R_137()) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean jj_3R_259() {
		if (jj_3R_238()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_24()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3_23() {
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_3R_63()) {
			return true;
		}
		if (jj_scan_token(LBRACKET)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_315() {
		if (jj_3R_61()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_3()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_264() {
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		if (jj_scan_token(ASSIGN)) {
			return true;
		}
		if (jj_3R_92()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_22() {
		if (jj_3R_77()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_125() {
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_3R_63()) {
			return true;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		if (jj_3R_161()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_314() {
		if (jj_3R_328()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_124() {
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_3R_63()) {
			return true;
		}
		if (jj_scan_token(LBRACKET)) {
			return true;
		}
		if (jj_scan_token(RBRACKET)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_77() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_124()) {
			jj_scanpos = xsp;
			if (jj_3R_125()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_244() {
		if (jj_scan_token(ENUM)) {
			return true;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_314()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(LBRACE)) {
			return true;
		}
		xsp = jj_scanpos;
		if (jj_3R_315()) {
			jj_scanpos = xsp;
		}
		xsp = jj_scanpos;
		if (jj_scan_token(87)) {
			jj_scanpos = xsp;
		}
		xsp = jj_scanpos;
		if (jj_3R_316()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(RBRACE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_241() {
		if (jj_3R_259()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_249() {
		if (jj_3R_264()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_265()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_240() {
		if (jj_3R_258()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_257() {
		if (jj_scan_token(BANG)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_256() {
		if (jj_scan_token(TILDE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_239() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_256()) {
			jj_scanpos = xsp;
			if (jj_3R_257()) {
				return true;
			}
		}
		if (jj_3R_161()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_171() {
		if (jj_scan_token(AT)) {
			return true;
		}
		if (jj_3R_90()) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_3R_92()) {
			return true;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_338() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_85()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_219() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_239()) {
			jj_scanpos = xsp;
			if (jj_3R_240()) {
				jj_scanpos = xsp;
				if (jj_3R_241()) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean jj_3R_91() {
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		if (jj_scan_token(ASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_328() {
		if (jj_scan_token(IMPLEMENTS)) {
			return true;
		}
		if (jj_3R_85()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_338()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_172() {
		if (jj_scan_token(AT)) {
			return true;
		}
		if (jj_3R_90()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_218() {
		if (jj_scan_token(DECR)) {
			return true;
		}
		if (jj_3R_238()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_337() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_85()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_41() {
		if (jj_scan_token(AT)) {
			return true;
		}
		if (jj_3R_90()) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_327() {
		if (jj_scan_token(EXTENDS)) {
			return true;
		}
		if (jj_3R_85()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_337()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3_40() {
		if (jj_scan_token(AT)) {
			return true;
		}
		if (jj_3R_90()) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_91()) {
			jj_scanpos = xsp;
			if (jj_scan_token(81)) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_170() {
		if (jj_scan_token(AT)) {
			return true;
		}
		if (jj_3R_90()) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_227()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_217() {
		if (jj_scan_token(INCR)) {
			return true;
		}
		if (jj_3R_238()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_261() {
		if (jj_scan_token(INTERFACE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_140() {
		if (jj_3R_172()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_194() {
		if (jj_3R_219()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_139() {
		if (jj_3R_171()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_193() {
		if (jj_3R_218()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_313() {
		if (jj_3R_328()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_138() {
		if (jj_3R_170()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_312() {
		if (jj_3R_327()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_192() {
		if (jj_3R_217()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_311() {
		if (jj_3R_96()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_243() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(20)) {
			jj_scanpos = xsp;
			if (jj_3R_261()) {
				return true;
			}
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		xsp = jj_scanpos;
		if (jj_3R_311()) {
			jj_scanpos = xsp;
		}
		xsp = jj_scanpos;
		if (jj_3R_312()) {
			jj_scanpos = xsp;
		}
		xsp = jj_scanpos;
		if (jj_3R_313()) {
			jj_scanpos = xsp;
		}
		if (jj_3R_84()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_94() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_138()) {
			jj_scanpos = xsp;
			if (jj_3R_139()) {
				jj_scanpos = xsp;
				if (jj_3R_140()) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean jj_3R_122() {
		return false;
	}

	private boolean jj_3R_216() {
		if (jj_scan_token(MINUS)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_215() {
		if (jj_scan_token(PLUS)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_191() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_215()) {
			jj_scanpos = xsp;
			if (jj_3R_216()) {
				return true;
			}
		}
		if (jj_3R_161()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_123() {
		return false;
	}

	private boolean jj_3R_75() {
		jj_lookingAhead = true;
		jj_semLA = getToken(1).kind == GT &&
				((GTToken) getToken(1)).realKind == RSIGNEDSHIFT;
		jj_lookingAhead = false;
		if (!jj_semLA || jj_3R_122()) {
			return true;
		}
		if (jj_scan_token(GT)) {
			return true;
		}
		if (jj_scan_token(GT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_161() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_191()) {
			jj_scanpos = xsp;
			if (jj_3R_192()) {
				jj_scanpos = xsp;
				if (jj_3R_193()) {
					jj_scanpos = xsp;
					if (jj_3R_194()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_378() {
		if (jj_scan_token(CATCH)) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_3R_342()) {
			return true;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		if (jj_3R_99()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_379() {
		if (jj_scan_token(FINALLY)) {
			return true;
		}
		if (jj_3R_99()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_371() {
		if (jj_scan_token(FINALLY)) {
			return true;
		}
		if (jj_3R_99()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_76() {
		jj_lookingAhead = true;
		jj_semLA = getToken(1).kind == GT &&
				((GTToken) getToken(1)).realKind == RUNSIGNEDSHIFT;
		jj_lookingAhead = false;
		if (!jj_semLA || jj_3R_123()) {
			return true;
		}
		if (jj_scan_token(GT)) {
			return true;
		}
		if (jj_scan_token(GT)) {
			return true;
		}
		if (jj_scan_token(GT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_359() {
		if (jj_scan_token(REM)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_358() {
		if (jj_scan_token(SLASH)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_357() {
		if (jj_scan_token(STAR)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_349() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_357()) {
			jj_scanpos = xsp;
			if (jj_3R_358()) {
				jj_scanpos = xsp;
				if (jj_3R_359()) {
					return true;
				}
			}
		}
		if (jj_3R_161()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_369() {
		if (jj_3R_73()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_370() {
		Token xsp;
		if (jj_3R_378()) {
			return true;
		}
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_378()) {
				jj_scanpos = xsp;
				break;
			}
		}
		xsp = jj_scanpos;
		if (jj_3R_379()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_325() {
		if (jj_3R_161()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_349()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_368() {
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_351() {
		if (jj_scan_token(MINUS)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_350() {
		if (jj_scan_token(PLUS)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_60() {
		if (jj_3R_94()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_345() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_350()) {
			jj_scanpos = xsp;
			if (jj_3R_351()) {
				return true;
			}
		}
		if (jj_3R_325()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_59() {
		if (jj_scan_token(STRICTFP)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_297() {
		if (jj_scan_token(TRY)) {
			return true;
		}
		if (jj_3R_99()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_370()) {
			jj_scanpos = xsp;
			if (jj_3R_371()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_58() {
		if (jj_scan_token(VOLATILE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_57() {
		if (jj_scan_token(TRANSIENT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_56() {
		if (jj_scan_token(NATIVE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_367() {
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_307() {
		if (jj_3R_325()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_345()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_55() {
		if (jj_scan_token(SYNCHRONIZED)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_54() {
		if (jj_scan_token(ABSTRACT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_53() {
		if (jj_scan_token(FINAL)) {
			return true;
		}
		return false;
	}

	private boolean jj_3_21() {
		if (jj_3R_76()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_20() {
		if (jj_3R_75()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_52() {
		if (jj_scan_token(PRIVATE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_74() {
		if (jj_scan_token(LSHIFT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_51() {
		if (jj_scan_token(PROTECTED)) {
			return true;
		}
		return false;
	}

	private boolean jj_3_19() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_74()) {
			jj_scanpos = xsp;
			if (jj_3_20()) {
				jj_scanpos = xsp;
				if (jj_3_21()) {
					return true;
				}
			}
		}
		if (jj_3R_307()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_50() {
		if (jj_scan_token(STATIC)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_49() {
		if (jj_scan_token(PUBLIC)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_308() {
		if (jj_scan_token(INSTANCEOF)) {
			return true;
		}
		if (jj_3R_63()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_296() {
		if (jj_scan_token(SYNCHRONIZED)) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		if (jj_3R_99()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_2() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_49()) {
			jj_scanpos = xsp;
			if (jj_3R_50()) {
				jj_scanpos = xsp;
				if (jj_3R_51()) {
					jj_scanpos = xsp;
					if (jj_3R_52()) {
						jj_scanpos = xsp;
						if (jj_3R_53()) {
							jj_scanpos = xsp;
							if (jj_3R_54()) {
								jj_scanpos = xsp;
								if (jj_3R_55()) {
									jj_scanpos = xsp;
									if (jj_3R_56()) {
										jj_scanpos = xsp;
										if (jj_3R_57()) {
											jj_scanpos = xsp;
											if (jj_3R_58()) {
												jj_scanpos = xsp;
												if (jj_3R_59()) {
													jj_scanpos = xsp;
													if (jj_3R_60()) {
														return true;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_305() {
		if (jj_3R_307()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_19()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_88() {
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_2()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_336() {
		if (jj_scan_token(GE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_335() {
		if (jj_scan_token(LE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_334() {
		if (jj_scan_token(GT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_333() {
		if (jj_scan_token(LT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_326() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_333()) {
			jj_scanpos = xsp;
			if (jj_3R_334()) {
				jj_scanpos = xsp;
				if (jj_3R_335()) {
					jj_scanpos = xsp;
					if (jj_3R_336()) {
						return true;
					}
				}
			}
		}
		if (jj_3R_305()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_295() {
		if (jj_scan_token(THROW)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_303() {
		if (jj_3R_305()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_326()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_294() {
		if (jj_scan_token(RETURN)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_369()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_390() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_304() {
		if (jj_scan_token(BIT_AND)) {
			return true;
		}
		if (jj_3R_281()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_298() {
		if (jj_3R_303()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_308()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_364() {
		if (jj_scan_token(ELSE)) {
			return true;
		}
		if (jj_3R_250()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_293() {
		if (jj_scan_token(CONTINUE)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_368()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_93() {
		if (jj_3R_94()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_48() {
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_93()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(PACKAGE)) {
			return true;
		}
		if (jj_3R_90()) {
			return true;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_310() {
		if (jj_scan_token(NE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3_1() {
		if (jj_3R_48()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_309() {
		if (jj_scan_token(EQ)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_306() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_309()) {
			jj_scanpos = xsp;
			if (jj_3R_310()) {
				return true;
			}
		}
		if (jj_3R_298()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_377() {
		if (jj_3R_386()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_299() {
		if (jj_scan_token(XOR)) {
			return true;
		}
		if (jj_3R_255()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_292() {
		if (jj_scan_token(BREAK)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_367()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_282() {
		if (jj_scan_token(BIT_OR)) {
			return true;
		}
		if (jj_3R_237()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_281() {
		if (jj_3R_298()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_306()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_260() {
		if (jj_scan_token(SC_AND)) {
			return true;
		}
		if (jj_3R_214()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_386() {
		if (jj_3R_389()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_255() {
		if (jj_3R_281()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_304()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_242() {
		if (jj_scan_token(SC_OR)) {
			return true;
		}
		if (jj_3R_190()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_39() {
		if (jj_3R_88()) {
			return true;
		}
		if (jj_3R_63()) {
			return true;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_389() {
		if (jj_3R_73()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_390()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_237() {
		if (jj_3R_255()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_299()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_388() {
		if (jj_3R_389()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_376() {
		if (jj_3R_73()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_387() {
		if (jj_3R_89()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_214() {
		if (jj_3R_237()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_282()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_385() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_387()) {
			jj_scanpos = xsp;
			if (jj_3R_388()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_190() {
		if (jj_3R_214()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_260()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3_38() {
		if (jj_3R_89()) {
			return true;
		}
		if (jj_scan_token(COLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_375() {
		if (jj_3R_385()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_160() {
		if (jj_3R_190()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_242()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_366() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_375()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		xsp = jj_scanpos;
		if (jj_3R_376()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		xsp = jj_scanpos;
		if (jj_3R_377()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_365() {
		if (jj_3R_89()) {
			return true;
		}
		if (jj_scan_token(COLON)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_220() {
		if (jj_scan_token(HOOK)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		if (jj_scan_token(COLON)) {
			return true;
		}
		if (jj_3R_121()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_291() {
		if (jj_scan_token(FOR)) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_365()) {
			jj_scanpos = xsp;
			if (jj_3R_366()) {
				return true;
			}
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		if (jj_3R_250()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_121() {
		if (jj_3R_160()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_220()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_120() {
		if (jj_scan_token(ORASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_119() {
		if (jj_scan_token(XORASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_118() {
		if (jj_scan_token(ANDASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_117() {
		if (jj_scan_token(RUNSIGNEDSHIFTASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_116() {
		if (jj_scan_token(RSIGNEDSHIFTASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_115() {
		if (jj_scan_token(LSHIFTASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_114() {
		if (jj_scan_token(MINUSASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_113() {
		if (jj_scan_token(PLUSASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_290() {
		if (jj_scan_token(DO)) {
			return true;
		}
		if (jj_3R_250()) {
			return true;
		}
		if (jj_scan_token(WHILE)) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_112() {
		if (jj_scan_token(REMASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_111() {
		if (jj_scan_token(SLASHASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_110() {
		if (jj_scan_token(STARASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_109() {
		if (jj_scan_token(ASSIGN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_72() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_109()) {
			jj_scanpos = xsp;
			if (jj_3R_110()) {
				jj_scanpos = xsp;
				if (jj_3R_111()) {
					jj_scanpos = xsp;
					if (jj_3R_112()) {
						jj_scanpos = xsp;
						if (jj_3R_113()) {
							jj_scanpos = xsp;
							if (jj_3R_114()) {
								jj_scanpos = xsp;
								if (jj_3R_115()) {
									jj_scanpos = xsp;
									if (jj_3R_116()) {
										jj_scanpos = xsp;
										if (jj_3R_117()) {
											jj_scanpos = xsp;
											if (jj_3R_118()) {
												jj_scanpos = xsp;
												if (jj_3R_119()) {
													jj_scanpos = xsp;
													if (jj_3R_120()) {
														return true;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_344() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_90()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_289() {
		if (jj_scan_token(WHILE)) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		if (jj_3R_250()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_18() {
		if (jj_3R_72()) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_73() {
		if (jj_3R_121()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_18()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_288() {
		if (jj_scan_token(IF)) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		if (jj_3R_250()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_364()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_134() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_133()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_384() {
		if (jj_scan_token(_DEFAULT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_383() {
		if (jj_scan_token(CASE)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_374() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_383()) {
			jj_scanpos = xsp;
			if (jj_3R_384()) {
				return true;
			}
		}
		if (jj_scan_token(COLON)) {
			return true;
		}
		if (jj_3R_144()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_332() {
		if (jj_3R_90()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_344()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3_17() {
		if (jj_scan_token(DOT)) {
			return true;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_363() {
		if (jj_3R_374()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_90() {
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_17()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_287() {
		if (jj_scan_token(SWITCH)) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		if (jj_scan_token(LBRACE)) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_363()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(RBRACE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_130() {
		if (jj_3R_63()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_129() {
		if (jj_scan_token(VOID)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_382() {
		if (jj_3R_72()) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_381() {
		if (jj_scan_token(DECR)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_380() {
		if (jj_scan_token(INCR)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_373() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_380()) {
			jj_scanpos = xsp;
			if (jj_3R_381()) {
				jj_scanpos = xsp;
				if (jj_3R_382()) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean jj_3R_82() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_129()) {
			jj_scanpos = xsp;
			if (jj_3R_130()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_302() {
		if (jj_3R_238()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_373()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3R_301() {
		if (jj_3R_218()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_300() {
		if (jj_3R_217()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_181() {
		if (jj_scan_token(DOUBLE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_180() {
		if (jj_scan_token(FLOAT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_179() {
		if (jj_scan_token(LONG)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_286() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_300()) {
			jj_scanpos = xsp;
			if (jj_3R_301()) {
				jj_scanpos = xsp;
				if (jj_3R_302()) {
					return true;
				}
			}
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_178() {
		if (jj_scan_token(INT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_177() {
		if (jj_scan_token(SHORT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_176() {
		if (jj_scan_token(BYTE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_157() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_108()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_254() {
		if (jj_scan_token(SUPER)) {
			return true;
		}
		if (jj_3R_70()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_175() {
		if (jj_scan_token(CHAR)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_362() {
		if (jj_scan_token(COLON)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_236() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_253()) {
			jj_scanpos = xsp;
			if (jj_3R_254()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_253() {
		if (jj_scan_token(EXTENDS)) {
			return true;
		}
		if (jj_3R_70()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_174() {
		if (jj_scan_token(BOOLEAN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_143() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_174()) {
			jj_scanpos = xsp;
			if (jj_3R_175()) {
				jj_scanpos = xsp;
				if (jj_3R_176()) {
					jj_scanpos = xsp;
					if (jj_3R_177()) {
						jj_scanpos = xsp;
						if (jj_3R_178()) {
							jj_scanpos = xsp;
							if (jj_3R_179()) {
								jj_scanpos = xsp;
								if (jj_3R_180()) {
									jj_scanpos = xsp;
									if (jj_3R_181()) {
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_285() {
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_189() {
		if (jj_scan_token(HOOK)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_236()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3_37() {
		if (jj_3R_89()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_89() {
		if (jj_3R_88()) {
			return true;
		}
		if (jj_3R_63()) {
			return true;
		}
		if (jj_3R_133()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_134()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3_36() {
		if (jj_3R_88()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_scan_token(20)) {
			jj_scanpos = xsp;
			if (jj_scan_token(40)) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_231() {
		if (jj_3R_250()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_159() {
		if (jj_3R_189()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_230() {
		if (jj_3R_89()) {
			return true;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_158() {
		if (jj_3R_70()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_319() {
		if (jj_scan_token(THROWS)) {
			return true;
		}
		if (jj_3R_332()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_108() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_158()) {
			jj_scanpos = xsp;
			if (jj_3R_159()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_229() {
		if (jj_3R_88()) {
			return true;
		}
		if (jj_3R_243()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_13() {
		if (jj_scan_token(LBRACKET)) {
			return true;
		}
		if (jj_scan_token(RBRACKET)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_71() {
		if (jj_scan_token(LT)) {
			return true;
		}
		if (jj_3R_108()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_157()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_scan_token(GT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_202() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_229()) {
			jj_scanpos = xsp;
			if (jj_3R_230()) {
				jj_scanpos = xsp;
				if (jj_3R_231()) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean jj_3_16() {
		if (jj_3R_71()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_15() {
		if (jj_scan_token(DOT)) {
			return true;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_16()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3_12() {
		if (jj_scan_token(LBRACKET)) {
			return true;
		}
		if (jj_scan_token(RBRACKET)) {
			return true;
		}
		return false;
	}

	private boolean jj_3_14() {
		if (jj_3R_71()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_33() {
		if (jj_scan_token(LBRACKET)) {
			return true;
		}
		if (jj_scan_token(RBRACKET)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_85() {
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_14()) {
			jj_scanpos = xsp;
		}
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_15()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_99() {
		if (jj_scan_token(LBRACE)) {
			return true;
		}
		if (jj_3R_144()) {
			return true;
		}
		if (jj_scan_token(RBRACE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_87() {
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		if (jj_scan_token(COLON)) {
			return true;
		}
		if (jj_3R_250()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_107() {
		if (jj_3R_85()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_13()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_106() {
		if (jj_3R_143()) {
			return true;
		}
		Token xsp;
		if (jj_3_12()) {
			return true;
		}
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_12()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_70() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_106()) {
			jj_scanpos = xsp;
			if (jj_3R_107()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_284() {
		if (jj_scan_token(ASSERT)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_362()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_97() {
		if (jj_3R_143()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_280() {
		if (jj_3R_297()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_11() {
		if (jj_3R_70()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_279() {
		if (jj_3R_296()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_63() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_11()) {
			jj_scanpos = xsp;
			if (jj_3R_97()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_278() {
		if (jj_3R_295()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_277() {
		if (jj_3R_294()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_276() {
		if (jj_3R_293()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_275() {
		if (jj_3R_292()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_274() {
		if (jj_3R_291()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_273() {
		if (jj_3R_290()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_98() {
		if (jj_scan_token(STATIC)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_272() {
		if (jj_3R_289()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_65() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_98()) {
			jj_scanpos = xsp;
		}
		if (jj_3R_99()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_271() {
		if (jj_3R_288()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_270() {
		if (jj_3R_287()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_182() {
		if (jj_3R_202()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_269() {
		if (jj_3R_286()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_144() {
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_182()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_268() {
		if (jj_3R_285()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_9() {
		if (jj_3R_68()) {
			return true;
		}
		if (jj_scan_token(DOT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_267() {
		if (jj_3R_99()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_266() {
		if (jj_3R_284()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_35() {
		if (jj_3R_87()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_69() {
		if (jj_3R_71()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_149() {
		if (jj_3R_71()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_7() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_66()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_10() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_69()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(THIS)) {
			return true;
		}
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_148() {
		if (jj_3R_68()) {
			return true;
		}
		if (jj_scan_token(DOT)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_103() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_148()) {
			jj_scanpos = xsp;
		}
		xsp = jj_scanpos;
		if (jj_3R_149()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(SUPER)) {
			return true;
		}
		if (jj_3R_147()) {
			return true;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_250() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_35()) {
			jj_scanpos = xsp;
			if (jj_3R_266()) {
				jj_scanpos = xsp;
				if (jj_3R_267()) {
					jj_scanpos = xsp;
					if (jj_3R_268()) {
						jj_scanpos = xsp;
						if (jj_3R_269()) {
							jj_scanpos = xsp;
							if (jj_3R_270()) {
								jj_scanpos = xsp;
								if (jj_3R_271()) {
									jj_scanpos = xsp;
									if (jj_3R_272()) {
										jj_scanpos = xsp;
										if (jj_3R_273()) {
											jj_scanpos = xsp;
											if (jj_3R_274()) {
												jj_scanpos = xsp;
												if (jj_3R_275()) {
													jj_scanpos = xsp;
													if (jj_3R_276()) {
														jj_scanpos = xsp;
														if (jj_3R_277()) {
															jj_scanpos = xsp;
															if (jj_3R_278()) {
																jj_scanpos = xsp;
																if (jj_3R_279()) {
																	jj_scanpos = xsp;
																	if (jj_3R_280()) {
																		return true;
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean jj_3R_146() {
		if (jj_3R_71()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_102() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_146()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(THIS)) {
			return true;
		}
		if (jj_3R_147()) {
			return true;
		}
		if (jj_scan_token(SEMICOLON)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_166() {
		if (jj_scan_token(LBRACKET)) {
			return true;
		}
		if (jj_scan_token(RBRACKET)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_132() {
		Token xsp;
		if (jj_3R_166()) {
			return true;
		}
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_166()) {
				jj_scanpos = xsp;
				break;
			}
		}
		if (jj_3R_145()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_32() {
		if (jj_scan_token(LBRACKET)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		if (jj_scan_token(RBRACKET)) {
			return true;
		}
		return false;
	}

	private boolean jj_3_34() {
		Token xsp;
		if (jj_3_32()) {
			return true;
		}
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_32()) {
				jj_scanpos = xsp;
				break;
			}
		}
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_33()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3_30() {
		if (jj_3R_84()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_67() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_102()) {
			jj_scanpos = xsp;
			if (jj_3R_103()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_86() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_34()) {
			jj_scanpos = xsp;
			if (jj_3R_132()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_322() {
		if (jj_scan_token(LBRACKET)) {
			return true;
		}
		if (jj_scan_token(RBRACKET)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_343() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_342()) {
			return true;
		}
		return false;
	}

	private boolean jj_3_8() {
		if (jj_3R_67()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_348() {
		if (jj_scan_token(ELLIPSIS)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_235() {
		if (jj_3R_71()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_320() {
		if (jj_3R_67()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_213() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_235()) {
			jj_scanpos = xsp;
		}
		if (jj_3R_85()) {
			return true;
		}
		if (jj_3R_147()) {
			return true;
		}
		xsp = jj_scanpos;
		if (jj_3_30()) {
			jj_scanpos = xsp;
		}
		return false;
	}

	private boolean jj_3_31() {
		if (jj_3R_85()) {
			return true;
		}
		if (jj_3R_86()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_262() {
		if (jj_3R_96()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_232() {
		if (jj_scan_token(COMMA)) {
			return true;
		}
		if (jj_3R_73()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_246() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_262()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		if (jj_3R_318()) {
			return true;
		}
		xsp = jj_scanpos;
		if (jj_3R_319()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(LBRACE)) {
			return true;
		}
		xsp = jj_scanpos;
		if (jj_3R_320()) {
			jj_scanpos = xsp;
		}
		if (jj_3R_144()) {
			return true;
		}
		if (jj_scan_token(RBRACE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_212() {
		if (jj_3R_85()) {
			return true;
		}
		if (jj_3R_86()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_200() {
		if (jj_scan_token(LBRACKET)) {
			return true;
		}
		if (jj_scan_token(RBRACKET)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_211() {
		if (jj_3R_143()) {
			return true;
		}
		if (jj_3R_86()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_199() {
		if (jj_3R_66()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3_7()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_187() {
		if (jj_scan_token(NEW)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_211()) {
			jj_scanpos = xsp;
			if (jj_3R_212()) {
				jj_scanpos = xsp;
				if (jj_3R_213()) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean jj_3R_342() {
		if (jj_3R_88()) {
			return true;
		}
		if (jj_3R_63()) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_348()) {
			jj_scanpos = xsp;
		}
		if (jj_3R_167()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_331() {
		if (jj_3R_342()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_343()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_318() {
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_331()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_203() {
		if (jj_3R_73()) {
			return true;
		}
		Token xsp;
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_232()) {
				jj_scanpos = xsp;
				break;
			}
		}
		return false;
	}

	private boolean jj_3R_183() {
		if (jj_3R_203()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_324() {
		if (jj_3R_99()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_323() {
		if (jj_scan_token(THROWS)) {
			return true;
		}
		if (jj_3R_332()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_147() {
		if (jj_scan_token(LPAREN)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_183()) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(RPAREN)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_263() {
		if (jj_3R_96()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_248() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_263()) {
			jj_scanpos = xsp;
		}
		if (jj_3R_82()) {
			return true;
		}
		if (jj_scan_token(IDENTIFIER)) {
			return true;
		}
		if (jj_3R_318()) {
			return true;
		}
		while (true) {
			xsp = jj_scanpos;
			if (jj_3R_322()) {
				jj_scanpos = xsp;
				break;
			}
		}
		xsp = jj_scanpos;
		if (jj_3R_323()) {
			jj_scanpos = xsp;
		}
		xsp = jj_scanpos;
		if (jj_3R_324()) {
			jj_scanpos = xsp;
			if (jj_scan_token(86)) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_234() {
		if (jj_scan_token(NULL)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_252() {
		if (jj_scan_token(FALSE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_251() {
		if (jj_scan_token(TRUE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_233() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_251()) {
			jj_scanpos = xsp;
			if (jj_3R_252()) {
				return true;
			}
		}
		return false;
	}

	private boolean jj_3R_210() {
		if (jj_3R_234()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_145() {
		if (jj_scan_token(LBRACE)) {
			return true;
		}
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_199()) {
			jj_scanpos = xsp;
		}
		xsp = jj_scanpos;
		if (jj_scan_token(87)) {
			jj_scanpos = xsp;
		}
		if (jj_scan_token(RBRACE)) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_209() {
		if (jj_3R_233()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_101() {
		if (jj_3R_73()) {
			return true;
		}
		return false;
	}

	private boolean jj_3R_208() {
		if (jj_scan_token(STRING_LITERAL)) {
			return true;
		}
		return false;
	}

	/** Generated Token Manager. */
	public ASTParserTokenManager token_source;

	JavaCharStream jj_input_stream;

	/** Current token. */
	public Token token;

	/** Next token. */
	public Token jj_nt;

	private int jj_ntk;

	private Token jj_scanpos, jj_lastpos;

	private int jj_la;

	/** Whether we are looking ahead. */
	private boolean jj_lookingAhead = false;

	private boolean jj_semLA;

	private int jj_gen;

	final private int[] jj_la1 = new int[134];

	static private int[] jj_la1_0;

	static private int[] jj_la1_1;

	static private int[] jj_la1_2;

	static private int[] jj_la1_3;

	static private int[] jj_la1_4;
	static {
		jj_la1_init_0();
		jj_la1_init_1();
		jj_la1_init_2();
		jj_la1_init_3();
		jj_la1_init_4();
	}

	private static void jj_la1_init_0() {
		jj_la1_0 = new int[] { 0x0, 0x48101000, 0x1, 0x0, 0x0, 0x0, 0x40001000, 0x8100000, 0x48101000, 0x100000, 0x0, 0x10000000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x4a195000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x10000000, 0x0, 0x4a195000, 0x8100000, 0x2094000, 0x4a195000,
				0x0, 0x0, 0x0, 0x22094000, 0x22094000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x42095000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x22094000, 0x6359f000, 0x0, 0x2094000, 0x2094000, 0x0, 0x2094000, 0x10000000, 0x10000000, 0x2094000, 0x2094000, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x22094000, 0x0, 0x0, 0x22094000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x20000000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x20000000, 0x20000000, 0x22094000, 0x0, 0x0, 0x2094000, 0x0, 0x0, 0x0,
				0x2349e000, 0x0, 0x2349e000, 0x0, 0x0, 0x0, 0x22094000, 0x820000, 0x820000, 0x4000000, 0x62095000, 0x22094000, 0x22094000, 0x62095000, 0x22094000, 0x0, 0x0, 0x0, 0x22094000, 0x40000, 0x80000000, 0x80040000, 0x0, 0x0, 0x0, 0x22094000,
				0x22094000, 0x0, 0x4a195000, 0xa194000, 0x4a195000, 0x800000, };
	}

	private static void jj_la1_init_1() {
		jj_la1_1 = new int[] { 0x20, 0x8899c500, 0x0, 0x0, 0x80000, 0x0, 0x8899c400, 0x100, 0x8899c500, 0x100, 0x0, 0x0, 0x10, 0x0, 0x0, 0x10, 0x0, 0x0, 0xc89dc781, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xc89dc781, 0x100, 0x40040281, 0xc89dc781, 0x0, 0x0, 0x0,
				0x51241a81, 0x51241a81, 0x0, 0x0, 0x0, 0x4000000, 0x0, 0x0, 0x889dc681, 0x0, 0x0, 0x4000000, 0x0, 0x0, 0x51241a81, 0xfbffdf8b, 0x80000, 0x40281, 0x40281, 0x0, 0x40281, 0x200000, 0x200000, 0x40281, 0x40040281, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x40, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x51241a81, 0x0, 0x0, 0x51241a81, 0x0, 0x0, 0x0, 0x0, 0x0, 0x11201800, 0x0, 0x0, 0x0, 0x0, 0x1000800, 0x0, 0x10001000, 0x10000000, 0x51241a81, 0x0, 0x0, 0x40281, 0x0, 0x0, 0x0,
				0x73e61a8b, 0x0, 0x73e61a8b, 0x0, 0x0, 0x0, 0x51241a81, 0x0, 0x0, 0x0, 0xd9bdde81, 0x51241a81, 0x51241a81, 0xd9bdde81, 0x51241a81, 0x0, 0x0, 0x0, 0x51241a81, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x51241a81, 0x51241a81, 0x0, 0x889dc781, 0x40381,
				0x889dc781, 0x0, };
	}

	private static void jj_la1_init_2() {
		jj_la1_2 = new int[] { 0x0, 0x2400000, 0x0, 0x2000000, 0x0, 0x1000000, 0x2000000, 0x2000000, 0x2400000, 0x0, 0x8000000, 0x0, 0x0, 0x800000, 0x800000, 0x0, 0x2002000, 0x800000, 0xa442000, 0x400000, 0x2000000, 0x10000, 0x40000, 0x800000, 0x0, 0x0,
				0xa442000, 0x2000000, 0x8002000, 0xa402000, 0x800000, 0x4000000, 0x100000, 0x30053846, 0x30053846, 0x800000, 0x8000000, 0x100000, 0x0, 0x440000, 0x800000, 0x2002000, 0x0, 0x8000000, 0x0, 0x8000000, 0x8000000, 0x8013846, 0x2453847, 0x0, 0x0,
				0x2000, 0x800000, 0x40002000, 0x0, 0x0, 0x0, 0x2000, 0x800000, 0x4000000, 0x40000000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x8000000, 0x8000000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x30013846, 0x30000000, 0x30000000, 0x13846, 0x10000, 0x0,
				0x8000000, 0x10000, 0x10000, 0x11846, 0x2000, 0x1000000, 0x8000000, 0x10000, 0x0, 0x1100000, 0x1846, 0x0, 0x30013846, 0x800000, 0x8000000, 0x0, 0x8002000, 0x100000, 0x100000, 0x453847, 0x80000000, 0x453847, 0x800000, 0x4000000, 0x4000000,
				0x13846, 0x0, 0x0, 0x0, 0x32013846, 0x30013846, 0x30013846, 0x32413846, 0x30013846, 0x800000, 0x2000, 0x2000, 0x30013846, 0x0, 0x0, 0x0, 0x2000000, 0x2000, 0x800000, 0x32053846, 0x32053846, 0x800000, 0x2402000, 0x2002000, 0x2402000, 0x0, };
	}

	private static void jj_la1_init_3() {
		jj_la1_3 = new int[] { 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1000, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x3c0, 0x3c0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x10000000,
				0x0, 0x0, 0x0, 0x0, 0x0, 0xc0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xffe0000, 0x0, 0x10, 0x20, 0x2000, 0x4000, 0x1000, 0x9, 0x9, 0x0, 0x80000006, 0x80000006, 0x10000, 0x300, 0x300, 0x8c00, 0x8c00, 0x300, 0x3c0, 0x0, 0x0, 0x0,
				0x0, 0xc0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x3c0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0xc0, 0x0, 0xc0, 0x0, 0xffe00c0, 0xffe00c0, 0xc0, 0x0, 0x0, 0x0, 0x3c0, 0x3c0, 0x3c0, 0x3c0, 0x3c0, 0x0, 0x0, 0x0, 0x3c0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x3c0, 0x3c0, 0x0, 0x0, 0x0, 0x0, 0x0, };
	}

	private static void jj_la1_init_4() {
		jj_la1_4 = new int[] { 0x0, 0x0, 0x1, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
				0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, };
	}

	final private JJCalls[] jj_2_rtns = new JJCalls[43];

	private boolean jj_rescan = false;

	private int jj_gc = 0;

	/** Constructor with InputStream. */
	public ASTParser(java.io.InputStream stream) {
		this(stream, null);
	}

	/** Constructor with InputStream and supplied encoding */
	public ASTParser(java.io.InputStream stream, String encoding) {
		try {
			jj_input_stream = new JavaCharStream(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source = new ASTParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 134; i++) {
			jj_la1[i] = -1;
		}
		for (int i = 0; i < jj_2_rtns.length; i++) {
			jj_2_rtns[i] = new JJCalls();
		}
	}

	/** Reinitialise. */
	public void ReInit(java.io.InputStream stream) {
		ReInit(stream, null);
	}

	/** Reinitialise. */
	public void ReInit(java.io.InputStream stream, String encoding) {
		try {
			jj_input_stream.ReInit(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 134; i++) {
			jj_la1[i] = -1;
		}
		for (int i = 0; i < jj_2_rtns.length; i++) {
			jj_2_rtns[i] = new JJCalls();
		}
	}

	/** Constructor. */
	public ASTParser(java.io.Reader stream) {
		jj_input_stream = new JavaCharStream(stream, 1, 1);
		token_source = new ASTParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 134; i++) {
			jj_la1[i] = -1;
		}
		for (int i = 0; i < jj_2_rtns.length; i++) {
			jj_2_rtns[i] = new JJCalls();
		}
	}

	/** Reinitialise. */
	public void ReInit(java.io.Reader stream) {
		jj_input_stream.ReInit(stream, 1, 1);
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 134; i++) {
			jj_la1[i] = -1;
		}
		for (int i = 0; i < jj_2_rtns.length; i++) {
			jj_2_rtns[i] = new JJCalls();
		}
	}

	/** Constructor with generated Token Manager. */
	public ASTParser(ASTParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 134; i++) {
			jj_la1[i] = -1;
		}
		for (int i = 0; i < jj_2_rtns.length; i++) {
			jj_2_rtns[i] = new JJCalls();
		}
	}

	/** Reinitialise. */
	public void ReInit(ASTParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 134; i++) {
			jj_la1[i] = -1;
		}
		for (int i = 0; i < jj_2_rtns.length; i++) {
			jj_2_rtns[i] = new JJCalls();
		}
	}

	private Token jj_consume_token(int kind) throws ParseException {
		Token oldToken;
		if ((oldToken = token).next != null) {
			token = token.next;
		} else {
			token = token.next = token_source.getNextToken();
		}
		jj_ntk = -1;
		if (token.kind == kind) {
			jj_gen++;
			if (++jj_gc > 100) {
				jj_gc = 0;
				for (int i = 0; i < jj_2_rtns.length; i++) {
					JJCalls c = jj_2_rtns[i];
					while (c != null) {
						if (c.gen < jj_gen) {
							c.first = null;
						}
						c = c.next;
					}
				}
			}
			return token;
		}
		token = oldToken;
		jj_kind = kind;
		throw generateParseException();
	}

	static private final class LookaheadSuccess extends java.lang.Error {
	}

	final private LookaheadSuccess jj_ls = new LookaheadSuccess();

	private boolean jj_scan_token(int kind) {
		if (jj_scanpos == jj_lastpos) {
			jj_la--;
			if (jj_scanpos.next == null) {
				jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
			} else {
				jj_lastpos = jj_scanpos = jj_scanpos.next;
			}
		} else {
			jj_scanpos = jj_scanpos.next;
		}
		if (jj_rescan) {
			int i = 0;
			Token tok = token;
			while (tok != null && tok != jj_scanpos) {
				i++;
				tok = tok.next;
			}
			if (tok != null) {
				jj_add_error_token(kind, i);
			}
		}
		if (jj_scanpos.kind != kind) {
			return true;
		}
		if (jj_la == 0 && jj_scanpos == jj_lastpos) {
			throw jj_ls;
		}
		return false;
	}


	/** Get the next Token. */
	final public Token getNextToken() {
		if (token.next != null) {
			token = token.next;
		} else {
			token = token.next = token_source.getNextToken();
		}
		jj_ntk = -1;
		jj_gen++;
		return token;
	}

	/** Get the specific Token. */
	final public Token getToken(int index) {
		Token t = jj_lookingAhead ? jj_scanpos : token;
		for (int i = 0; i < index; i++) {
			if (t.next != null) {
				t = t.next;
			} else {
				t = t.next = token_source.getNextToken();
			}
		}
		return t;
	}

	private int jj_ntk() {
		if ((jj_nt = token.next) == null) {
			return (jj_ntk = (token.next = token_source.getNextToken()).kind);
		} else {
			return (jj_ntk = jj_nt.kind);
		}
	}

	private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();

	private int[] jj_expentry;

	private int jj_kind = -1;

	private int[] jj_lasttokens = new int[100];

	private int jj_endpos;

	private void jj_add_error_token(int kind, int pos) {
		if (pos >= 100) {
			return;
		}
		if (pos == jj_endpos + 1) {
			jj_lasttokens[jj_endpos++] = kind;
		} else if (jj_endpos != 0) {
			jj_expentry = new int[jj_endpos];
			for (int i = 0; i < jj_endpos; i++) {
				jj_expentry[i] = jj_lasttokens[i];
			}
			jj_entries_loop: for (java.util.Iterator it = jj_expentries.iterator(); it.hasNext();) {
				int[] oldentry = (int[]) (it.next());
				if (oldentry.length == jj_expentry.length) {
					for (int i = 0; i < jj_expentry.length; i++) {
						if (oldentry[i] != jj_expentry[i]) {
							continue jj_entries_loop;
						}
					}
					jj_expentries.add(jj_expentry);
					break jj_entries_loop;
				}
			}
			if (pos != 0) {
				jj_lasttokens[(jj_endpos = pos) - 1] = kind;
			}
		}
	}

	/** Generate ParseException. */
	public ParseException generateParseException() {
		jj_expentries.clear();
		boolean[] la1tokens = new boolean[129];
		if (jj_kind >= 0) {
			la1tokens[jj_kind] = true;
			jj_kind = -1;
		}
		for (int i = 0; i < 134; i++) {
			if (jj_la1[i] == jj_gen) {
				for (int j = 0; j < 32; j++) {
					if ((jj_la1_0[i] & (1 << j)) != 0) {
						la1tokens[j] = true;
					}
					if ((jj_la1_1[i] & (1 << j)) != 0) {
						la1tokens[32 + j] = true;
					}
					if ((jj_la1_2[i] & (1 << j)) != 0) {
						la1tokens[64 + j] = true;
					}
					if ((jj_la1_3[i] & (1 << j)) != 0) {
						la1tokens[96 + j] = true;
					}
					if ((jj_la1_4[i] & (1 << j)) != 0) {
						la1tokens[128 + j] = true;
					}
				}
			}
		}
		for (int i = 0; i < 129; i++) {
			if (la1tokens[i]) {
				jj_expentry = new int[1];
				jj_expentry[0] = i;
				jj_expentries.add(jj_expentry);
			}
		}
		jj_endpos = 0;
		jj_rescan_token();
		jj_add_error_token(0, 0);
		int[][] exptokseq = new int[jj_expentries.size()][];
		for (int i = 0; i < jj_expentries.size(); i++) {
			exptokseq[i] = jj_expentries.get(i);
		}
		return new ParseException(token, exptokseq, tokenImage);
	}

	/** Enable tracing. */
	final public void enable_tracing() {
	}

	/** Disable tracing. */
	final public void disable_tracing() {
	}

	private void jj_rescan_token() {
		jj_rescan = true;
		for (int i = 0; i < 43; i++) {
			try {
				JJCalls p = jj_2_rtns[i];
				do {
					if (p.gen > jj_gen) {
						jj_la = p.arg;
						jj_lastpos = jj_scanpos = p.first;
						switch (i) {
						case 0:
							jj_3_1();
							break;
						case 1:
							jj_3_2();
							break;
						case 2:
							jj_3_3();
							break;
						case 3:
							jj_3_4();
							break;
						case 4:
							jj_3_5();
							break;
						case 5:
							jj_3_6();
							break;
						case 6:
							jj_3_7();
							break;
						case 7:
							jj_3_8();
							break;
						case 8:
							jj_3_9();
							break;
						case 9:
							jj_3_10();
							break;
						case 10:
							jj_3_11();
							break;
						case 11:
							jj_3_12();
							break;
						case 12:
							jj_3_13();
							break;
						case 13:
							jj_3_14();
							break;
						case 14:
							jj_3_15();
							break;
						case 15:
							jj_3_16();
							break;
						case 16:
							jj_3_17();
							break;
						case 17:
							jj_3_18();
							break;
						case 18:
							jj_3_19();
							break;
						case 19:
							jj_3_20();
							break;
						case 20:
							jj_3_21();
							break;
						case 21:
							jj_3_22();
							break;
						case 22:
							jj_3_23();
							break;
						case 23:
							jj_3_24();
							break;
						case 24:
							jj_3_25();
							break;
						case 25:
							jj_3_26();
							break;
						case 26:
							jj_3_27();
							break;
						case 27:
							jj_3_28();
							break;
						case 28:
							jj_3_29();
							break;
						case 29:
							jj_3_30();
							break;
						case 30:
							jj_3_31();
							break;
						case 31:
							jj_3_32();
							break;
						case 32:
							jj_3_33();
							break;
						case 33:
							jj_3_34();
							break;
						case 34:
							jj_3_35();
							break;
						case 35:
							jj_3_36();
							break;
						case 36:
							jj_3_37();
							break;
						case 37:
							jj_3_38();
							break;
						case 38:
							jj_3_39();
							break;
						case 39:
							jj_3_40();
							break;
						case 40:
							jj_3_41();
							break;
						case 41:
							jj_3_42();
							break;
						case 42:
							jj_3_43();
							break;
						}
					}
					p = p.next;
				} while (p != null);
			} catch (LookaheadSuccess ls) {
			}
		}
		jj_rescan = false;
	}

	private void jj_save(int index, int xla) {
		JJCalls p = jj_2_rtns[index];
		while (p.gen > jj_gen) {
			if (p.next == null) {
				p = p.next = new JJCalls();
				break;
			}
			p = p.next;
		}
		p.gen = jj_gen + xla - jj_la;
		p.first = token;
		p.arg = xla;
	}

	static final class JJCalls {

		int gen;

		Token first;

		int arg;

		JJCalls next;
	}

}
