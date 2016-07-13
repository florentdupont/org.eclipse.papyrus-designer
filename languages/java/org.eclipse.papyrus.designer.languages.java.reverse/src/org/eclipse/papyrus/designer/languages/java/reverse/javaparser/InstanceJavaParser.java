/**
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.papyrus.designer.languages.java.reverse.javaparser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.CompilationUnit;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.ImportDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.body.BodyDeclaration;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.AnnotationExpr;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.expr.Expression;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.BlockStmt;
import org.eclipse.papyrus.designer.languages.java.reverse.javaparser.ast.stmt.Statement;

/**
 * @author Sebastian Kuerten
 */
public class InstanceJavaParser
{

    private ASTParser astParser;

    public InstanceJavaParser(InputStream input)
    {
        astParser = new ASTParser(input);
    }

    public InstanceJavaParser(InputStream input, String encoding)
    {
        astParser = new ASTParser(input, encoding);
    }

    public InstanceJavaParser(File file) throws FileNotFoundException
    {
        InputStream input = new BufferedInputStream(new FileInputStream(file));
        astParser = new ASTParser(input);
    }

    public InstanceJavaParser(File file, String encoding)
            throws FileNotFoundException
    {
        InputStream input = new BufferedInputStream(new FileInputStream(file));
        astParser = new ASTParser(input, encoding);
    }

    public InstanceJavaParser(Reader reader)
    {
        astParser = new ASTParser(reader);
    }

    /**
     * Return the list of tokens that have been encountered while parsing code
     * using this parser.
     *
     * @return a list of tokens
     */
    public List<Token> getTokens() {
        return astParser.getTokens();
    }

    /**
     * Parses the Java code and returns a {@link CompilationUnit} that
     * represents it.
     *
     * @return CompilationUnit representing the Java source code
     * @throws ParseException
     *             if the source code has parser errors
     */
    public CompilationUnit parse() throws ParseException {
        return astParser.CompilationUnit();
    }

    /**
     * Parses the Java block and returns a {@link BlockStmt} that represents it.
     *
     * @return BlockStmt representing the Java block
     * @throws ParseException
     *             if the source code has parser errors
     */
    public BlockStmt parseBlock() throws ParseException {
        return astParser.Block();
    }

    /**
     * Parses the Java block and returns a {@link List} of {@link Statement}s
     * that represents it.
     *
     * @return Statement representing the Java statement
     * @throws ParseException
     *             if the source code has parser errors
     */
    public List<?> parseStatements() throws ParseException {
        return astParser.Statements();
    }

    /**
     * Parses the Java statement and returns a {@link Statement} that represents
     * it.
     *
     * @return Statement representing the Java statement
     * @throws ParseException
     *             if the source code has parser errors
     */
    public Statement parseStatement() throws ParseException {
        return astParser.Statement();
    }

    /**
     * Parses the Java import and returns a {@link ImportDeclaration} that
     * represents it.
     *
     * @return ImportDeclaration representing the Java import declaration
     * @throws ParseException
     *             if the source code has parser errors
     */
    public ImportDeclaration parseImport() throws ParseException {
        return astParser.ImportDeclaration();
    }

    /**
     * Parses the Java expression and returns a {@link Expression} that
     * represents it.
     *
     * @return Expression representing the Java expression
     * @throws ParseException
     *             if the source code has parser errors
     */
    public Expression parseExpression() throws ParseException {
        return astParser.Expression();
    }

    /**
     * Parses the Java annotation and returns a {@link AnnotationExpr} that
     * represents it.
     *
     * @return AnnotationExpr representing the Java annotation
     * @throws ParseException
     *             if the source code has parser errors
     */
    public AnnotationExpr parseAnnotation() throws ParseException {
        return astParser.Annotation();
    }

    /**
     * Parses the Java body declaration(e.g fields or methods) and returns a
     * {@link BodyDeclaration} that represents it.
     *
     * @return BodyDeclaration representing the Java body
     * @throws ParseException
     *             if the source code has parser errors
     */
    public BodyDeclaration parseBodyDeclaration() throws ParseException {
        return astParser.AnnotationBodyDeclaration();
    }

    /**
     * Parses the Java body declaration(e.g fields or methods) and returns a
     * {@link BodyDeclaration} that represents it.
     *
     * @param isInterface
     *            whether the parsed source code is an interface.
     *
     * @return BodyDeclaration representing the Java body
     * @throws ParseException
     *             if the source code has parser errors
     */
    public BodyDeclaration parseClassOrInterfaceBodyDeclaration(
            boolean isInterface) throws ParseException {
        return astParser.ClassOrInterfaceBodyDeclaration(isInterface);
    }

}
