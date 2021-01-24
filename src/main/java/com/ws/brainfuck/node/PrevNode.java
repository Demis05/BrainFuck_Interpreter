package com.ws.brainfuck.node;

import com.ws.brainfuck.compilator.PointExecutor;

public class PrevNode implements Node {

    @Override
    public void execute(PointExecutor pointer) {
        pointer.execute(this);
    }

    @Override
    public String toString() {
        return "<";
    }
}
