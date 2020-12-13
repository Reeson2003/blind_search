package com.reeson2003.ai.puzzle;

import java.util.List;
import java.util.Set;

public class Main {

    public static final String DEPTH = "depth";
    public static final String ITERATIVE = "iterative";
    public static final String GREEDY_MISS_POSITION = "greedyMissPosition";
    public static final String GREEDY_MANHATTAN_DISTANCE = "greedyManhattanDistance";
    public static final String A_STAR_MISS_POSITION = "aStarMissPosition";
    public static final String A_STAR_MANHATTAN_DISTANCE = "aStarManhattanDistance";
    public static final List<String> SEARCHES = List.of(DEPTH, ITERATIVE, GREEDY_MISS_POSITION, GREEDY_MANHATTAN_DISTANCE, A_STAR_MISS_POSITION, A_STAR_MANHATTAN_DISTANCE);

    public static void main(String[] args) {
        Set<String> params = Set.of(args);
        boolean stepByStep = params.contains("-steps");
        State initial = new State(new int[][]{{0, 4, 3}, {6, 2, 1}, {7, 5, 8}});
        State target = new State(new int[][]{{1, 2, 3}, {4, 0, 5}, {6, 7, 8}});
        Problem problem = new Problem(initial, target);
        PuzzleSolver puzzleSolver;
        String searchType = args.length > 0 ? args[0] : "";
        switch (searchType) {
            case DEPTH:
                puzzleSolver = new DepthPuzzleSolver(problem, stepByStep, stepByStep);
                break;
            case ITERATIVE:
                puzzleSolver = new IterativeDepthPuzzleSolver(problem, stepByStep, stepByStep);
                break;
            case GREEDY_MISS_POSITION:
                puzzleSolver = new GreedyPuzzleSolver(problem, stepByStep, stepByStep, new MissPositionComparator(problem.goalState));
                break;
            case GREEDY_MANHATTAN_DISTANCE:
                puzzleSolver = new GreedyPuzzleSolver(problem, stepByStep, stepByStep, new ManhattanDistanceComparator(problem.goalState));
                break;
            case A_STAR_MISS_POSITION:
                puzzleSolver = new AStarPuzzleSolver(problem, stepByStep, stepByStep, new MissPositionComparator(problem.goalState));
                break;
            case A_STAR_MANHATTAN_DISTANCE:
                puzzleSolver = new AStarPuzzleSolver(problem, stepByStep, stepByStep, new ManhattanDistanceComparator(problem.goalState));
                break;
            default:
                System.err.println("First argument should be one of: " + SEARCHES);
                return;
        }
        try {
            puzzleSolver.solve().print(initial, true);
        } catch (SolutionNotAvailableException e) {
            System.out.println("Solution is not available");
            e.printStackTrace();
        }
        new java.util.Scanner(System.in).nextLine();
    }
}
