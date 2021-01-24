package com.ws.brainfuck.parser;

import com.ws.brainfuck.exception.ParseException;
import com.ws.brainfuck.node.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

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
        try {
            List<String> bodyLoopTokens = tokens.subList(startLoop, endLoop);
            parseBody(bodyLoopTokens, bodyNode);
        } catch (IllegalArgumentException exc) {
            throw new ParseException(INVALID_INPUT_ERROR);
        }
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
