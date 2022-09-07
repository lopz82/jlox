package com.craftinginterpreters.lox;

import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScannerTest {

    Token createNumber(int line) {
        return new Token(TokenType.NUMBER, "2", 2.0, line);
    }

    Token createEOF(int line) {
        return new Token(TokenType.EOF, "", null, line);
    }

    @Test
    public void testNumber() {
        List<Token> tokens = new Scanner("2").scanTokens();
        List<Token> expected = new ArrayList<>();

        expected.add(createNumber(1));
        expected.add(createEOF(1));

        assertIterableEquals(tokens, expected);
    }

    @Test
    public void testVariableAssignment() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println("var x = 2;");
        printWriter.println("var y = x + 2;");
        String code = stringWriter.toString();

        List<Token> tokens = new Scanner(code).scanTokens();
        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenType.VAR, "var", null, 1));
        expected.add(new Token(TokenType.IDENTIFIER, "x", null, 1));
        expected.add(new Token(TokenType.EQUAL, "=", null, 1));
        expected.add(createNumber(1));
        expected.add(new Token(TokenType.SEMICOLON, ";", null, 1));

        expected.add(new Token(TokenType.VAR, "var", null, 2));
        expected.add(new Token(TokenType.IDENTIFIER, "y", null, 2));
        expected.add(new Token(TokenType.EQUAL, "=", null, 2));
        expected.add(new Token(TokenType.IDENTIFIER, "x", null, 2));
        expected.add(new Token(TokenType.PLUS, "+", null, 2));
        expected.add(createNumber(2));
        expected.add(new Token(TokenType.SEMICOLON, ";", null, 2));

        expected.add(createEOF(3));

        assertIterableEquals(tokens, expected);
    }
}
