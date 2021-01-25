package com.ws.brainfuck.compilator;

import com.ws.brainfuck.node.Node;
import com.ws.brainfuck.parser.TokenParser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointExecutorTest {

    private static final String HELLO_WORLD_SCRIPT_WITH_LOOP = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";

    private PointExecutor pointExecutor;
    private TokenParser tokenParser;

    @Before
    public void setUp() {
        pointExecutor = new PointExecutor();
        tokenParser = new TokenParser();
    }

    @Test
    public void testExecute() {
        List<String> tokens = Arrays.asList(HELLO_WORLD_SCRIPT_WITH_LOOP.split(""));
        Node node = tokenParser.parse(tokens);
        node.execute(pointExecutor);
    }

    @Test
    public void testExecute2() {
        List<String> tokens = new ArrayList<>();
        tokens.add("+");
        tokens.add("+");
        tokens.add("+");
        tokens.add(">");
        Node node = tokenParser.parse(tokens);
        node.execute(pointExecutor);
        pointExecutor.cleanMemory();
    }
}