package com.ws.brainfuck.parser;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TokenParserTest {
    private static final String HELLO_WORLD_SCRIPT_WITH_LOOP = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
    private static final String HELLO_WORLD_SCRIPT_WITHOUT_LOOP = "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.+++++++++++++++++++++++++++++.+++++++..+++.-------------------------------------------------------------------------------.+++++++++++++++++++++++++++++++++++++++++++++++++++++++.++++++++++++++++++++++++.+++.------.--------.-------------------------------------------------------------------.-----------------------.";
    private static final String LARGE_NESTING_OF_LOOPS_SCRIPT = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
    private static final String INVALID_INPUT_SCRIPT_1 = "++++++++++[++++-]]";
    private static final String INVALID_INPUT_SCRIPT_2 = "]]]][[[[";
    private static final String INVALID_INPUT_SCRIPT_3 = "7316=!@#$";

    private TokenParser tokenParser;

    @Before
    public void setUp() {
        tokenParser = new TokenParser();
    }

    @Test
    public void checkParserForValidWorkWithOutLoopScript() {
        List<String> tokens = Arrays.asList(HELLO_WORLD_SCRIPT_WITHOUT_LOOP.split(""));
        assertEquals(HELLO_WORLD_SCRIPT_WITHOUT_LOOP, tokenParser.parse(tokens).toString());
    }

    @Test
    public void checkParserForValidWorkWithLoopScript() {
        List<String> tokens = Arrays.asList(HELLO_WORLD_SCRIPT_WITH_LOOP.split(""));
        assertEquals(HELLO_WORLD_SCRIPT_WITH_LOOP, tokenParser.parse(tokens).toString());
    }

    @Test
    public void checkParserForValidWorkWithLargeNestingOfLoops() {
        List<String> tokens = Arrays.asList(LARGE_NESTING_OF_LOOPS_SCRIPT.split(""));
        assertEquals(LARGE_NESTING_OF_LOOPS_SCRIPT, tokenParser.parse(tokens).toString());
    }

    @Test(expected = NullPointerException.class)
    public void test1() {
        List<String> tokens = Arrays.asList(INVALID_INPUT_SCRIPT_1.split(""));
        tokenParser.parse(tokens);
    }

    @Test(expected = NullPointerException.class)
    public void test2() {
        List<String> tokens = Arrays.asList(INVALID_INPUT_SCRIPT_2.split(""));
        tokenParser.parse(tokens);
    }

    @Test(expected = NullPointerException.class)
    public void test3() {
        List<String> tokens = Arrays.asList(INVALID_INPUT_SCRIPT_3.split(""));
        tokenParser.parse(tokens);
    }
}