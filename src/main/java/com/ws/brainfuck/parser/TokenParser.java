package com.ws.brainfuck.parser;

import com.ws.brainfuck.command.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * The parser implements the Composite pattern. A compositor is a
 * structural design pattern that allows you to group multiple
 * objects into a tree structure and then work with it as if it
 * were a single object. The parser reads the input sequence of
 * lines and composes them into a structure of sequential commands.
 * The structure consists of simple commands and composite commands, which
 * contain the same nested structure of simple or composite commands.
 * The parser cuts the parenthesized sequence of commands and composes
 * it into a compound loop command.
 */
public class TokenParser {

    private final Map<String, Supplier<Command>> commandMap;

    public TokenParser() {
        commandMap = new HashMap<>();
    }

    public BodyCommand parse(List<String> tokens) {
        BodyCommand bodyCommand = new BodyCommand();
        parseBody(tokens, bodyCommand);
        return bodyCommand;
    }

    private void parseBody(List<String> tokens, BodyCommand bodyCommand) {
        List<Command> commands = bodyCommand.getCommands();
        int current = 0;
        while (current < tokens.size()) {
            Command command = getCommand(tokens, current);
            if (Objects.nonNull(command)) {
                commands.add(command);
                if (command instanceof LoopCommand) {
                    current = getEndLoopIndex(tokens, current + 1);
                }
            }
            current++;
        }
    }

    private Command getCommand(List<String> tokens, int index) {
        fillCommandMap(tokens, index);
        return commandMap.get(tokens.get(index)).get();
    }

    private Command getLoop(List<String> tokens) {
        int startLoop = 1;
        int endLoop = getEndLoopIndex(tokens, startLoop);
        BodyCommand bodyCommand = new BodyCommand();
        List<String> bodyLoopTokens = tokens.subList(startLoop, endLoop);
        parseBody(bodyLoopTokens, bodyCommand);
        return new LoopCommand(bodyCommand);
    }

    private int getEndLoopIndex(List<String> tokens, int start) {
        int counter = 1;
        for (int i = start; i < tokens.size(); i++) {
            if ("[".equals(tokens.get(i))) {
                counter++;
            }
            if ("]".equals(tokens.get(i))) {
                counter--;
            }
            if (counter == 0) {
                return i;
            }
        }
        return -1;
    }

    private void fillCommandMap(List<String> tokens, int index) {
        commandMap.put("<", PrevCommand::new);
        commandMap.put(">", NextCommand::new);
        commandMap.put("+", IncrementCommand::new);
        commandMap.put("-", DecrementCommand::new);
        commandMap.put(".", ShowCommand::new);
        commandMap.put("]", null);
        commandMap.put("[", () -> getLoop(tokens.subList(index, tokens.size())));
    }
}
