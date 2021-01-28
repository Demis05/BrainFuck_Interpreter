package com.ws.brainfuck.compilator;

import com.ws.brainfuck.command.Command;
import com.ws.brainfuck.command.DecrementCommand;
import com.ws.brainfuck.command.IncrementCommand;
import com.ws.brainfuck.command.LoopCommand;
import com.ws.brainfuck.command.NextCommand;
import com.ws.brainfuck.command.PrevCommand;
import com.ws.brainfuck.command.ShowCommand;

public class CommandExecutor implements Visitor {

    private static final int LENGTH = 65535;
    private byte[] memory;
    private int cursor;

    public CommandExecutor() {
        memory = new byte[LENGTH];
        cursor = 0;
    }

    public void cleanMemory() {
        memory = new byte[LENGTH];
    }

    public void execute(NextCommand command) {
        cursor++;
    }

    public void execute(PrevCommand command) {
        cursor--;
    }

    public void execute(IncrementCommand command) {
        memory[cursor]++;
    }

    public void execute(DecrementCommand command) {
        memory[cursor]--;
    }

    public void execute(ShowCommand command) {
        System.out.print((char) memory[cursor]);
    }

    public void execute(LoopCommand command) {
        while (memory[cursor] != 0) {
            for (Command currCommand : command.getBodyList()) {
                currCommand.execute(this);
            }
        }
    }
}
