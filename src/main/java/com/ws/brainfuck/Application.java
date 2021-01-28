package com.ws.brainfuck;

import com.ws.brainfuck.command.Command;
import com.ws.brainfuck.compilator.CommandExecutor;
import com.ws.brainfuck.exception.ParseException;
import com.ws.brainfuck.parser.TokenParser;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    private static final String INVALID_INPUT_ERROR = "Invalid input script! Please try again";
    private static final String BRAINFUCK_PATTERN_REGEX = "^[<.>+-\\]\\[]{1,}$";
    private static final String PLEASE_INPUT_TEXT = "Please input your BrainFuck code. Print 'exit' to stop program.";
    private static final String EXIT = "exit";
    private static final Scanner scanner = new Scanner(System.in);
    private static final TokenParser tokenParser = new TokenParser();
    private static final CommandExecutor commandExecutor = new CommandExecutor();
    private static final Pattern langPattern = Pattern.compile(BRAINFUCK_PATTERN_REGEX);

    public static void main(String[] args) {
        String input;
        System.out.println(PLEASE_INPUT_TEXT);
        while (scanner.hasNext()) {
            input = scanner.nextLine();
            if (EXIT.equals(input)) {
                break;
            }
            try {
                Matcher matcher = langPattern.matcher(input);
                if (!matcher.find() || !checkBracketsForValidity(input)) {
                    throw new ParseException(INVALID_INPUT_ERROR);
                }
                List<String> tokens = Arrays.asList(input.split(""));
                Command script = tokenParser.parse(tokens);
                script.execute(commandExecutor);
                commandExecutor.cleanMemory();
            } catch (ParseException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static boolean checkBracketsForValidity(String input) {
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
