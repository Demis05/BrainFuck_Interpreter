package com.ws.brainfuck.node;


import com.ws.brainfuck.compilator.PointExecutor;

import java.util.List;

public class LoopNode implements Node {

    private final BodyNode body;

    @Override
    public void execute(PointExecutor pointer) {
        pointer.execute(this);
    }

    public LoopNode(BodyNode body) {
        this.body = body;
    }

    public List<Node> getBodyList() {
        return body.getNodes();
    }

    @Override
    public String toString() {
        return "[" + body + "]";
    }
}