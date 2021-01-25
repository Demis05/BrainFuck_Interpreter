package com.ws.brainfuck.parser;

import com.ws.brainfuck.node.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * The parser implements the Composite pattern. A compositor is a
 * structural design pattern that allows you to group multiple
 * objects into a tree structure and then work with it as if it
 * were a single object. The parser reads the input sequence of
 * lines and composes them into a structure of sequential nodes.
 * The structure consists of simple nodes and composite nodes, which
 * contain the same nested structure of simple or composite nodes.
 * The parser cuts the parenthesized sequence of nodes and composes
 * it into a compound loop node.
 */
public class TokenParser {

    private static final String INVALID_INPUT_ERROR = "Invalid input script!";
    private final Map<String, Supplier<Node>> nodeMap;

    public TokenParser() {
        nodeMap = new HashMap<>();
    }

    public BodyNode parse(List<String> tokens) {
        BodyNode bodyNode = new BodyNode();
        parseBody(tokens, bodyNode);
        return bodyNode;
    }

    private void parseBody(List<String> tokens, BodyNode bodyNode) {
        List<Node> commands = bodyNode.getNodes();
        int current = 0;
        while (current < tokens.size()) {
            Node node = getNode(tokens, current);
            if (Objects.nonNull(node)) {
                commands.add(node);
                if (node instanceof LoopNode) {
                    current = getEndLoopIndex(tokens, current + 1);
                }
            }
            current++;
        }
    }

    private Node getNode(List<String> tokens, int index) {
        fillNodeMap(tokens, index);
        return nodeMap.get(tokens.get(index)).get();
    }

    private Node getLoop(List<String> tokens) {
        int startLoop = 1;
        int endLoop = getEndLoopIndex(tokens, startLoop);
        BodyNode bodyNode = new BodyNode();
        List<String> bodyLoopTokens = tokens.subList(startLoop, endLoop);
        parseBody(bodyLoopTokens, bodyNode);
        return new LoopNode(bodyNode);
    }

    private int getEndLoopIndex(List<String> tokens, int start) {
        int counter = 1;
        for (int i = start; i < tokens.size(); i++) {
            if ("[".equals(tokens.get(i))) {
                counter++;
            }
            if ("]".equals(tokens.get(i))) {
                counter--;
            }
            if (counter == 0) {
                return i;
            }
        }
        return -1;
    }

    private void fillNodeMap(List<String> tokens, int index) {
        nodeMap.put("<", PrevNode::new);
        nodeMap.put(">", NextNode::new);
        nodeMap.put("+", IncrementNode::new);
        nodeMap.put("-", DecrementNode::new);
        nodeMap.put(".", ShowNode::new);
        nodeMap.put("]", null);
        nodeMap.put("[", () -> getLoop(tokens.subList(index, tokens.size())));
    }
}
