package com.ws.brainfuck;

import com.ws.brainfuck.exception.ParseException;
import com.ws.brainfuck.node.Node;

public class Demo {

    private static final String PLEASE_INPUT_TEXT = "Please input your BrainFuck code. Print 'exit' to stop program.";
    private static final String EXIT = "exit";

    public static void main(String[] args) {
        ApplicationContext application = new ApplicationContext();
        String input;
        System.out.println(PLEASE_INPUT_TEXT);
        while (application.getScanner().hasNext()) {
            input = application.getScanner().nextLine();
            if (EXIT.equals(input)) {
                break;
            }
            try {
                application.validate(input);
                Node body = application.convertToModel(input);
                application.compileModel(body);
            } catch (ParseException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}