package com.ws.brainfuck;

import com.ws.brainfuck.node.Node;
import com.ws.brainfuck.util.ParseException;

import java.util.List;

public class Demo {

    public static final String PLEASE_INPUT_TEXT = "Please input your BrainFuck code";
    public static final String EXIT = "exit";

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
                List<Node> script = application.convertToModel(input);
                application.compileModel(script);
                application.cleanMemory();
            } catch (ParseException exc) {
                System.out.println(exc.getMessage());
            }
        }
    }
}