package com.reeson2003.ai.puzzle;

import java.util.Objects;

public class Node {
    public Node parentNode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return state.equals(node.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
