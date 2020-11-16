package com.reeson2003.ai.puzzle;

public class ActionNotAvailableException extends Exception {
    public final State state;
    public final Action action;

    public ActionNotAvailableException(State state, Action action) {
        super("Action is not available");
        this.state = state;
        this.action = action;
    }
}
