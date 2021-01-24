package com.ws.brainfuck.node;

import com.ws.brainfuck.compilator.PointExecutor;

public interface Node {

    void execute(PointExecutor pointer);
}