package com.reeson2003.ai.puzzle;

public class Problem {
    public final State initialState;
    public final State goalState;

    public Problem(State initialState, State goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
    }

    public boolean testGoal(State state) {
        return goalState.equals(state);
    }
}
