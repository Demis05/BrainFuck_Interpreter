package com.ws.brainfuck.node;

import com.ws.brainfuck.compilator.PointExecutor;

/**
 * The interface provides the ability to implement a new syntax object
 * for storing a script in a stored model from sequential instructions.
 * The node describes the method of accepting a visitor. This interface
 * must have a method with single parameter declared with the visitor
 * interface type. The specific elements of this interface implement the
 * visitor acceptance methods.
 * The purpose of this method is to call the visit method that
 * matches the type of this element. This way the visitor will know which
 * element he is working with.
 *
 * A leaf (all nodes except the LoopNode) is a simple component of a tree that has no branches.
 */
public interface Node {

    void execute(PointExecutor pointer);
}