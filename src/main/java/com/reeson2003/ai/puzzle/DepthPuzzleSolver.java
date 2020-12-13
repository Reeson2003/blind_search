package com.reeson2003.ai.puzzle;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DepthPuzzleSolver extends PuzzleSolver {
    public DepthPuzzleSolver(Problem problem, boolean stepByStep, boolean print) {
        super(problem, stepByStep, print);
    }

    @Override
    public Solution solve() throws SolutionNotAvailableException {
        Node parent = new Node(null, problem.initialState, null, 0, 0);
        return createSolution(solve(parent, Integer.MAX_VALUE, List.of(parent)));
    }

    @Override
    protected List<Node> composeNextNodes(List<Node> expanded, List<Node> visited) {
        Set<State> visitedStates = visited.stream().map(node -> node.state).collect(Collectors.toSet());
        return expanded.stream()
                .filter(node -> !isContains(visited, node))
                .collect(Collectors.toList());
    }

    private boolean isContains(List<Node> visited, Node node) {
        return visited.stream()
                .map(n -> n.state)
                .anyMatch(state -> state.equals(node.state));
    }

}
