package com.ws.brainfuck.compilator;

public class CommandTape {

    private static final int LENGTH = 65535;

    private final byte[] memory;
    private int cursor;

    public CommandTape() {
        memory = new byte[LENGTH];
        cursor = 0;
    }

    public void nextCell() {
        cursor++;
    }

    public void prevCell() {
        cursor--;
    }

    public void incrementValue() {
        memory[cursor]++;
    }

    public void decrementValue() {
        memory[cursor]--;
    }

    public void showValue() {
        System.out.print((char) memory[cursor]);
    }

    public byte getValue() {
        return memory[cursor];
    }

}
