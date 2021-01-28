package com.ws.brainfuck.command;

import com.ws.brainfuck.compilator.CommandExecutor;

import java.util.ArrayList;
import java.util.List;

public class BodyCommand implements Command {

    private final List<Command> commands;

    public BodyCommand() {
        commands = new ArrayList<>();
    }

    public List<Command> getCommands() {
        return commands;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Command command : commands) {
            builder.append(command);
        }
        return builder.toString();
    }

    @Override
    public void execute(CommandExecutor executor) {
        for (Command command : commands) {
            command.execute(executor);
        }
    }
}