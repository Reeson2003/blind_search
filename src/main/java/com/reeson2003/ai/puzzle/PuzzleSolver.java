package com.reeson2003.ai.puzzle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class PuzzleSolver {
    protected final Problem problem;
    protected final boolean stepByStep;
    protected final boolean print;
    protected long nodes = 0;
    protected long steps = 0;

    public PuzzleSolver(Problem problem, boolean stepByStep, boolean print) {
        this.problem = problem;
        this.stepByStep = stepByStep;
        this.print = print;
    }

    public abstract Solution solve() throws SolutionNotAvailableException;

    protected Node solve(Node parent, int maxDepth, List<Node> visitedNodes) throws SolutionNotAvailableException {
        steps++;
        nodes++;
        boolean goalReached = problem.testGoal(parent.state);
        List<Node> toExpand = expandNodes(parent, visitedNodes);
        printStep(goalReached, parent, toExpand);
        pause();
        if (goalReached) {
            return parent;
        } else if (maxDepth == 0) {
            throw new SolutionNotAvailableException();
        } else {
            for (Node node : toExpand) {
//                List<Node> newVisitedNodes = new ArrayList<>(visitedNodes);
                visitedNodes.add(node);
                try {
                    return solve(node, maxDepth - 1, visitedNodes);
                } catch (SolutionNotAvailableException ignored) {
                }
            }
            throw new SolutionNotAvailableException();
        }
    }

    private List<Node> expandNodes(Node parent, List<Node> visited) {
        List<Node> expanded = new ArrayList<>();
        for (Action action : Action.values()) {
            try {
                State newState = action.perform(parent.state);
                expanded.add(new Node(parent,
                        newState,
                        action,
                        parent.depth + 1,
                        parent.pathCost + 1));
            } catch (ActionNotAvailableException ignored) {
            }
        }
        return composeNextNodes(expanded, visited);
    }

    protected abstract List<Node> composeNextNodes(List<Node> expanded, List<Node> visited);

    protected Solution createSolution(Node node) {
        Node parent = node;
        LinkedList<Action> actions = new LinkedList<>();
        while (parent.action != null) {
            actions.addFirst(parent.action);
            parent = parent.parentNode;
        }
        return new Solution(actions, nodes, steps, actions.size());
    }

    protected void pause() {
        if (stepByStep) {
            new java.util.Scanner(System.in).nextLine();
        }
    }

    private void printStep(boolean goalReached, Node parent, List<Node> toExpand) {
        if (print) {
            StringBuilder sb = new StringBuilder();
            sb.append("Current state: ")
                    .append("\n")
                    .append(parent.state)
                    .append("\n")
                    .append("Goal reached: ")
                    .append(goalReached)
                    .append("\n")
                    .append("Total steps: ")
                    .append(steps)
                    .append("\n")
                    .append("Total nodes: ")
                    .append(nodes)
                    .append("\n");
            if (toExpand.size() > 0) {
                sb.append("Nodes to expand:")
                        .append("\n");
                toExpand.stream().map(node -> node.state)
                        .forEach(state -> sb.append(state).append("\n=======\n"));
                sb.append("\n");
            }
            System.out.println(sb);
        }
    }
}
