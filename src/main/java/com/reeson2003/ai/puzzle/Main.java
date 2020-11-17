package com.reeson2003.ai.puzzle;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> params = Set.of(args);
        boolean stepByStep = params.contains("-steps");
        boolean iterative = params.contains("-iterative");
        State initial = new State(new int[][]{{0, 4, 3}, {6, 2, 1}, {7, 5, 8}});
        State target = new State(new int[][]{{1, 2, 3}, {4, 0, 5}, {6, 7, 8}});
        Problem problem = new Problem(initial, target);
        try {
            new PuzzleSolver(problem, stepByStep, stepByStep).solve(iterative).print(initial);
        } catch (SolutionNotAvailableException e) {
            System.out.println("Solution is not available");
            e.printStackTrace();
        }
        new java.util.Scanner(System.in).nextLine();
    }
}
