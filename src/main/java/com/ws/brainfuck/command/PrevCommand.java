package com.ws.brainfuck.command;

import com.ws.brainfuck.compilator.CommandExecutor;

public class PrevCommand implements Command {

    @Override
    public void execute(CommandExecutor executor) {
        executor.execute(this);
    }

    @Override
    public String toString() {
        return "<";
    }
}