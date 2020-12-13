package com.reeson2003.ai.puzzle;

import java.util.Comparator;
import java.util.List;

public class GreedyPuzzleSolver extends DepthPuzzleSolver {

    protected final Comparator<State> comparator;

    public GreedyPuzzleSolver(Problem problem, boolean stepByStep, boolean print, Comparator<State> comparator) {
        super(problem, stepByStep, print);
        this.comparator = comparator;
    }

    @Override
    protected List<Node> composeNextNodes(List<Node> expanded, List<Node> visited) {
        List<Node> list = super.composeNextNodes(expanded, visited);
        list.sort(Comparator.comparing(node -> node.state, comparator));
        return list;
    }
}
