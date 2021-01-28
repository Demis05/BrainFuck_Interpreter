package com.ws.brainfuck.command;


import com.ws.brainfuck.compilator.CommandExecutor;

import java.util.List;

/**
 * LoopCommand allow a container (or composite) is a composite
 * tree component. It contains a set of child components, but
 * knows nothing about their types. These can be simple leaf
 * components or other container components. But this is not
 * a problem as long as all child components follow the same
 * interface. Container methods redirect the main work to their
 * child components, although they can add something of their
 * own to the result.
 */
public class LoopCommand implements Command {

    private final BodyCommand body;

    @Override
    public void execute(CommandExecutor executor) {
        executor.execute(this);
    }

    public LoopCommand(BodyCommand body) {
        this.body = body;
    }

    public List<Command> getBodyList() {
        return body.getCommands();
    }

    @Override
    public String toString() {
        return "[" + body + "]";
    }
}