package com.ws.brainfuck.compilator;

import com.ws.brainfuck.node.NextNode;
import com.ws.brainfuck.node.PrevNode;
import com.ws.brainfuck.node.IncrementNode;
import com.ws.brainfuck.node.DecrementNode;
import com.ws.brainfuck.node.LoopNode;

public interface Visitor {

    void execute(NextNode node);

    void execute(PrevNode node);

    void execute(IncrementNode node);

    void execute(DecrementNode node);

    void execute(LoopNode node);
}