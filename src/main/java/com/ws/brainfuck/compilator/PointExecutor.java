package com.ws.brainfuck.compilator;

import com.ws.brainfuck.node.DecrementNode;
import com.ws.brainfuck.node.IncrementNode;
import com.ws.brainfuck.node.LoopNode;
import com.ws.brainfuck.node.NextNode;
import com.ws.brainfuck.node.Node;
import com.ws.brainfuck.node.PrevNode;
import com.ws.brainfuck.node.ShowNode;

public class PointExecutor implements Visitor {

    private CommandTape commandTape;

    public PointExecutor() {
        commandTape = new CommandTape();
    }

    public void cleanMemory() {
        commandTape = new CommandTape();
    }

    public void execute(NextNode node) {
        commandTape.nextCell();
    }

    public void execute(PrevNode node) {
        commandTape.prevCell();
    }

    public void execute(IncrementNode node) {
        commandTape.incrementValue();
    }

    public void execute(DecrementNode node) {
        commandTape.decrementValue();
    }

    public void execute(ShowNode node) {
        commandTape.showValue();
    }

    public void execute(LoopNode node) {
        while (commandTape.getValue() != 0) {
            for (Node currNode : node.getBodyList()) {
                currNode.execute(this);
            }
        }
    }
}
