package com.reeson2003.ai.puzzle;

public class Node {
    public final Node parentNode;
    public final State state;
    public final Action action;
    public final int depth;
    public final int pathCost;

    public Node(Node parentNode, State state, Action action, int depth, int pathCost) {
        this.parentNode = parentNode;
        this.state = state;
        this.action = action;
        this.depth = depth;
        this.pathCost = pathCost;
    }
}
