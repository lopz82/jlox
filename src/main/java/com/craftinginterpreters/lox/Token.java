package com.craftinginterpreters.lox;

import java.util.Objects;

public class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;

    public Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    public String toString() {
        return type + " " + lexeme + " " + literal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (line != token.line) return false;
        if (type != token.type) return false;
        if (!Objects.equals(lexeme, token.lexeme)) return false;
        return Objects.equals(literal, token.literal);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (lexeme != null ? lexeme.hashCode() : 0);
        result = 31 * result + (literal != null ? literal.hashCode() : 0);
        result = 31 * result + line;
        return result;
    }
}
