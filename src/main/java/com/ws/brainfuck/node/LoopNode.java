package com.ws.brainfuck.node;


import com.ws.brainfuck.compilator.PointExecutor;

import java.util.List;

/**
 * LoopNode allow a container (or composite) is a composite
 * tree component. It contains a set of child components, but
 * knows nothing about their types. These can be simple leaf
 * components or other container components. But this is not
 * a problem as long as all child components follow the same
 * interface. Container methods redirect the main work to their
 * child components, although they can add something of their
 * own to the result.
 */
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