package com.ws.brainfuck.compilator;

import com.ws.brainfuck.node.*;

/**
 * The Visitor interface provides an opportunity to implement a tool
 * for executing the functionality required for a specific type of
 * input parameter. The interface is an implementation of the Visitor
 * pattern for separating the algorithm from the classes with which it
 * works.
 * The visitor describes a common interface for all types of visitors.
 * It declares a set of methods, differing in the type of the input parameter,
 * that are needed to start an operation for all types of specific elements.
 * Using method overloading, these methods can have the same name, but the
 * types of their parameters must be different.Visitor is adding a new action
 * by simply providing a new
 * Visitor implementation. The visitor is independent of the component
 * interfaces and can aggregate data based on the subject it passes through.
 * The pattern uses the Open / Closed principle, since we will not change
 * the code, but we can still extend the functionality by providing a new
 * Visitor implementation.
 */
public interface Visitor {

    void execute(NextNode node);

    void execute(PrevNode node);

    void execute(IncrementNode node);

    void execute(DecrementNode node);

    void execute(LoopNode node);
}