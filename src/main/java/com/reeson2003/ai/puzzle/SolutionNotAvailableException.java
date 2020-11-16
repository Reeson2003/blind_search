package com.reeson2003.ai.puzzle;

public class SolutionNotAvailableException extends Exception {
    public SolutionNotAvailableException() {
        super("Solution is not available");
    }
}
