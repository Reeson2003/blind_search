package com.reeson2003.ai.puzzle;

import java.util.List;

public class IterativeDepthPuzzleSolver extends DepthPuzzleSolver {
    public IterativeDepthPuzzleSolver(Problem problem, boolean stepByStep, boolean print) {
        super(problem, stepByStep, print);
    }

    @Override
    public Solution solve() throws SolutionNotAvailableException {
        Node parent = new Node(null, problem.initialState, null, 0, 0);
        Node solve = null;
        int depth = 1;
        while (solve == null) {
            try {
                if (print)
                    System.out.println("Solving with max depth: " + depth);
                pause();
                solve = solve(parent, depth++, List.of(parent));
            } catch (SolutionNotAvailableException e) {
                if (depth >= 100)
                    throw e;
                nodes = 0;
            }
        }
        return createSolution(solve);
    }
}
