package com.reeson2003.ai.puzzle;

import java.util.*;

public class PuzzleSolver {
    private final Problem problem;
    private final boolean stepByStep;
    private final boolean print;
    private Solution solution;
    private long nodes = 0;
    private long steps = 0;

    public PuzzleSolver(Problem problem, boolean stepByStep, boolean print) {
        this.problem = problem;
        this.stepByStep = stepByStep;
        this.print = print;
    }

    public Solution solve() throws SolutionNotAvailableException {
        if (solution != null) {
            return solution;
        }
        Node node = new Node(null, problem.initialState, null, 0, 0);
        Node solve = null;
        int depth = 1;
        while (solve == null) {
            try {
                if (print)
                    System.out.println("Solving with max depth: " + depth);
                pause();
                solve = solve(node, depth++, Set.of(node.state));
            } catch (SolutionNotAvailableException ignored) {
                nodes = 0;
            }
        }
        solution = createSolution(solve, depth);
        return solution;
    }

    private Node solve(Node parent, int maxDepth, Set<State> previousStates) throws SolutionNotAvailableException {
        steps++;
        nodes++;
        boolean goalReached = problem.testGoal(parent.state);
        List<Node> toExpand = new ArrayList<>();
        List<State> visited = new ArrayList<>();
        for (Action action : Action.values()) {
            try {
                State newState = action.perform(parent.state);
                if (previousStates.contains(newState)) {
                    visited.add(newState);
                } else {
                    toExpand.add(new Node(parent,
                            newState,
                            action,
                            parent.depth + 1,
                            parent.pathCost + 1));
                }
            } catch (ActionNotAvailableException ignored) {
            }
        }
        printStep(goalReached, parent, toExpand, visited);
        pause();
        if (goalReached) {
            return parent;
        } else if (maxDepth == 0) {
            throw new SolutionNotAvailableException();
        } else {
            for (Node node : toExpand) {
                HashSet<State> newPreviousStates = new HashSet<>(previousStates);
                newPreviousStates.add(node.state);
                try {
                    return solve(node, maxDepth - 1, newPreviousStates);
                } catch (SolutionNotAvailableException ignored) {
                }
            }
            throw new SolutionNotAvailableException();
        }
    }

    private Solution createSolution(Node node, int depth) {
        Node parent = node;
        LinkedList<Action> actions = new LinkedList<>();
        while (parent.action != null) {
            actions.addFirst(parent.action);
            parent = parent.parentNode;
        }
        return new Solution(actions, nodes, steps, depth);
    }

    private void pause() {
        if (stepByStep) {
            new java.util.Scanner(System.in).nextLine();
        }
    }

    private void printStep(boolean goalReached, Node parent, List<Node> toExpand, List<State> visited) {
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
            if (visited.size() > 0) {
                sb.append("Visited nodes:")
                        .append("\n");
                visited.forEach(state -> sb.append(state).append("\n=======\n"));
                sb.append("\n");
            }
            System.out.println(sb);
        }
    }
}
