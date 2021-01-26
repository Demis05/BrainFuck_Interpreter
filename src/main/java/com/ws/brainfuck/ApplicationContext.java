package com.ws.brainfuck;

import com.ws.brainfuck.compilator.PointExecutor;
import com.ws.brainfuck.exception.ParseException;
import com.ws.brainfuck.node.Node;
import com.ws.brainfuck.parser.TokenParser;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationContext {

    private static final String INVALID_INPUT_ERROR = "Invalid input script! Please try again";
    private static final String BRAINFUCK_PATTERN_REGEX = "^[<.>+-\\]\\[]{1,}$";

    private final Scanner scanner;
    private final TokenParser tokenParser;
    private final PointExecutor pointExecutor;
    private final Pattern langPattern = Pattern.compile(BRAINFUCK_PATTERN_REGEX);

    public ApplicationContext() {
        scanner = new Scanner(System.in);
        tokenParser = new TokenParser();
        pointExecutor = new PointExecutor();
    }

    public Scanner getScanner() {
        return scanner;
    }

    /**
     * 0 STEP
     * Validation
     **/
    public void validate(String input) {
        Matcher matcher = langPattern.matcher(input);
        if (!matcher.find() || !checkBracketsForValidity(input)) {
            throw new ParseException(INVALID_INPUT_ERROR);
        }
    }

    /**
     * 1 STEP
     * from input script to list of instructions
     **/
    public Node convertToModel(String inputScript) {
        List<String> tokens = Arrays.asList(inputScript.split(""));
        return tokenParser.parse(tokens);
    }

    /**
     * 2 STEP (Compile)
     * from model of instructions to output string
     **/
    public void compileModel(Node node) {
        node.execute(pointExecutor);
        pointExecutor.cleanMemory();
    }

    private boolean checkBracketsForValidity(String input) {
        int counter = 0;
        String[] tokens = input.split("");
        for (String symbol : tokens) {
            if ("[".equals(symbol)) {
                counter++;
            } else if ("]".equals(symbol)) {
                counter--;
            }
        }
        return counter == 0;
    }
}
