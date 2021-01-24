package com.ws.brainfuck.parser;

import com.ws.brainfuck.util.ParseException;

import java.util.ArrayList;
import java.util.List;

public class TokenMaker {

    private static final String INVALID_INPUT_DATA_ERROR = "Invalid input data error!";

    public List<Tokens> stringToTokens(String input) throws ParseException {
        List<Tokens> tokens = new ArrayList<>();
        for (char symbol : input.toCharArray()) {
            tokens.add(getToken(symbol));
        }
        return tokens;
    }

    private Tokens getToken(char symbol) throws ParseException {
        switch (symbol) {
            case '>':
                return Tokens.NEXT;
            case '<':
                return Tokens.PREV;
            case '+':
                return Tokens.INCREMENT;
            case '-':
                return Tokens.DECREMENT;
            case '[':
                return Tokens.LOOP_START;
            case ']':
                return Tokens.LOOP_END;
            case '.':
                return Tokens.PRINT;
            default:
                throw new ParseException(INVALID_INPUT_DATA_ERROR);
        }
    }

}
