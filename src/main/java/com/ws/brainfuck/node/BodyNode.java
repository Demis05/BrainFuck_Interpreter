package com.ws.brainfuck.node;

import com.ws.brainfuck.compilator.PointExecutor;

import java.util.ArrayList;
import java.util.List;

public class BodyNode implements Node {

    List<Node> nodes;

    public BodyNode() {
        nodes = new ArrayList<>();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node node : nodes) {
            builder.append(node);
        }
        return builder.toString();
    }

    @Override
    public void execute(PointExecutor pointer) {
        for (Node node: nodes) {
            node.execute(pointer);
        }
    }
}
