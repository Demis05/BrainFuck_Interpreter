package com.ws.brainfuck;

import com.ws.brainfuck.compilator.PointExecutor;
import com.ws.brainfuck.node.BodyNode;
import com.ws.brainfuck.node.Node;
import com.ws.brainfuck.parser.TokenParser;
import com.ws.brainfuck.parser.TokenMaker;
import com.ws.brainfuck.parser.Tokens;
import com.ws.brainfuck.util.LangValidator;
import com.ws.brainfuck.util.ParseException;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 */
public class ApplicationContext {

    public static final String INVALID_INPUT_ERROR = "Invalid input! Please try again";
    public static final String BRAINFUCK_PATTERN_REGEX = "^[<.>+-\\]\\[]{1,}$";

    private final Scanner scanner;
    private final LangValidator inputValidator;
    private final TokenMaker tokenMaker;
    private final TokenParser tokenParser;
    private final PointExecutor pointExecutor;

    public ApplicationContext() {
        scanner = new Scanner(System.in);
        Pattern langPattern = Pattern.compile(BRAINFUCK_PATTERN_REGEX);
        inputValidator = new LangValidator(langPattern);
        tokenMaker = new TokenMaker();
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
    public void validate(String input) throws ParseException {
        if (!inputValidator.test(input)) throw new ParseException(INVALID_INPUT_ERROR);
    }

    /**
     * 1 STEP
     * from input script to list of instructions
     **/
    public List<Node> convertToModel(String inputScript) throws ParseException {
        List<Tokens> tokens = tokenMaker.stringToTokens(inputScript);
        BodyNode program = tokenParser.parse(tokens);
        return program.getNodes();
    }


    /**
     * 2 STEP (Compile)
     * from model of instructions to output string
     **/
    public void compileModel(List<Node> nodes) {
        for (Node node : nodes) {
            node.execute(pointExecutor);
        }
    }

    /**
     * 3 STEP
     * memory cleaning
     **/
    public void cleanMemory() {
        pointExecutor.cleanMemory();
    }


}
