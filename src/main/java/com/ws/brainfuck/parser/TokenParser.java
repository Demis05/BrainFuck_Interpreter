package com.ws.brainfuck.parser;


import com.ws.brainfuck.node.*;
import com.ws.brainfuck.util.ParseException;

import java.util.List;
import java.util.Objects;

public class TokenParser {

    public BodyNode parse(List<Tokens> tokens) throws ParseException {
        BodyNode bodyNode = new BodyNode();
        parseBody(tokens, bodyNode);
        return bodyNode;
    }

    private void parseBody(List<Tokens> tokens, BodyNode bodyNode) throws ParseException {
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

    private Node getNode(List<Tokens> tokens, int index) throws ParseException {
        switch (tokens.get(index)) {
            case PREV:
                return new PrevNode();
            case NEXT:
                return new NextNode();
            case INCREMENT:
                return new IncrementNode();
            case DECREMENT:
                return new DecrementNode();
            case PRINT:
                return new ShowNode();
            case LOOP_START:
                return getLoop(tokens.subList(index, tokens.size()));
            case LOOP_END:
                return null;
            default:
                throw new ParseException("");
        }
    }

    private Node getLoop(List<Tokens> tokens) throws ParseException {
        int startLoop = 1;
        int endLoop = getEndLoopIndex(tokens, startLoop);
        List<Tokens> bodyLoopTokens = tokens.subList(startLoop, endLoop);
        BodyNode bodyNode = new BodyNode();

        parseBody(bodyLoopTokens, bodyNode);
        return new LoopNode(bodyNode);
    }

    private int getEndLoopIndex(List<Tokens> tokens, int start) {
        int counter = 1;
        for (int i = start; i < tokens.size(); i++) {
            if (Tokens.LOOP_START.equals(tokens.get(i))) {
                counter++;
            }
            if (Tokens.LOOP_END.equals(tokens.get(i))) {
                counter--;
            }
            if (counter == 0) {
                return i;
            }
        }
        return -1;
    }
}
