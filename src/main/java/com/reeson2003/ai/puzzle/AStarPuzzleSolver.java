package com.reeson2003.ai.puzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class AStarPuzzleSolver extends GreedyPuzzleSolver {

    private final Comparator<State> comparator;

    public AStarPuzzleSolver(Problem problem, boolean stepByStep, boolean print, Comparator<State> comparator) {
        super(problem, stepByStep, print, comparator);
        this.comparator = comparator;
    }

    @Override
    public Solution solve() throws SolutionNotAvailableException {
        Node current = new Node(null, problem.initialState, null, 0, 0);
        ArrayList<Node> visited = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(current);
        Node next;
        while (!problem.testGoal((next = queue.poll()).state)) {
            var expandedNodes = new ArrayList<Node>(4);
            for (Action action : Action.values()) {
                try {
                    State nextState = action.perform(next.state);
                    Node expanded = new Node(next, nextState, action, next.depth + 1, next.pathCost + 1);
                    visited.stream()
                            .filter(node -> node.equals(expanded))
                            .filter(node -> node.depth < expanded.depth)
                            .sorted(Comparator.comparingInt(node -> node.depth))
                            .findFirst()
                            .ifPresentOrElse(node -> expanded.parentNode = node.parentNode,
                                    () -> expandedNodes.add(expanded));
                } catch (ActionNotAvailableException ignored) {
                }
            }
            expandedNodes.stream()
                    .sorted(Comparator.comparing(node -> node.state, comparator))
                    .forEach(queue::offer);
            steps++;
            visited.add(next);
        }
        nodes = queue.size();
        return createSolution(next);
    }

}
