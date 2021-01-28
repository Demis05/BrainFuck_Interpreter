package com.ws.brainfuck.compilator;

import com.ws.brainfuck.command.Command;
import com.ws.brainfuck.parser.TokenParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CommandExecutorTest {

    private static final String HELLO_WORLD_SCRIPT_WITH_LOOP = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
    private static final String HELLO_WORLD_SCRIPT_WITHOUT_LOOP = "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.+++++++++++++++++++++++++++++.+++++++..+++.-------------------------------------------------------------------------------.+++++++++++++++++++++++++++++++++++++++++++++++++++++++.++++++++++++++++++++++++.+++.------.--------.-------------------------------------------------------------------.-----------------------.";
    private static final String COPY_COPY_SH_SCRIPT_WITHOUT_LOOP = "--[----->+<]>---.++++++++++++.+.+++++++++.+[-->+<]>+++.++[-->+++<]>.++++++++++++.+.+++++++++.-[-->+++++<]>++.[--->++<]>-.-----------.";
    private CommandExecutor commandExecutor;
    private TokenParser tokenParser;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        commandExecutor = new CommandExecutor();
        tokenParser = new TokenParser();
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @After
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testPointExecutorForValidOutput_1() {
        List<String> tokens = Arrays.asList(HELLO_WORLD_SCRIPT_WITH_LOOP.split(""));
        Command command = tokenParser.parse(tokens);
        command.execute(commandExecutor);
        assertEquals("Hello World!", out.toString().trim());
    }

    @Test
    public void testPointExecutorForValidOutput_2() {
        List<String> tokens = Arrays.asList(HELLO_WORLD_SCRIPT_WITHOUT_LOOP.split(""));
        Command command = tokenParser.parse(tokens);
        command.execute(commandExecutor);
        assertEquals("Hello World!", out.toString().trim());
    }


    @Test
    public void testPointExecutorForValidOutput_3() {
        List<String> tokens = Arrays.asList(COPY_COPY_SH_SCRIPT_WITHOUT_LOOP.split(""));
        Command command = tokenParser.parse(tokens);
        command.execute(commandExecutor);
        assertEquals("copy@copy.sh", out.toString().trim());
    }
}