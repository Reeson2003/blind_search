package com.reeson2003.ai.puzzle;

import java.util.List;

public class Solution {
    public final List<Action> actions;
    public final long nodes;
    public final long steps;
    public final int depth;

    public Solution(List<Action> actions, long nodes, long steps, int depth) {
        this.actions = actions;
        this.nodes = nodes;
        this.steps = steps;
        this.depth = depth;
    }

    public void print(State initial, boolean printSteps) {
        try {
            System.out.println("======FOUND SOLUTION=======");
            State state = initial;
            if (printSteps) {
                for (Action action : actions) {
                    System.out.println(state);
                    System.out.println(action);
                    state = action.perform(state);
                }
                System.out.println(state);
            }
            System.out.println("Total nodes: " + nodes);
            System.out.println("Total steps: " + steps);
            System.out.println("Total depth: " + depth);
        } catch (ActionNotAvailableException e) {
            System.err.println("Can't print solution");
            e.printStackTrace();
        }
    }
}
