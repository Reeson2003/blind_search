package com.reeson2003.ai.puzzle;

import java.util.Comparator;

public class ManhattanDistanceComparator implements Comparator<State> {

    private final State target;

    public ManhattanDistanceComparator(State target) {
        this.target = target;
    }

    @Override
    public int compare(State o1, State o2) {
        int manhattanDistance1 = 0;
        int manhattanDistance2 = 0;
        for (int i = 0; i < target.getWidth(); i++) {
            for (int j = 0; j < target.getHeight(); j++) {
                int element1 = o1.field[i][j];
                manhattanDistance1 += getManhattanDistance(i, j, target.positionOf(element1));
                int element2 = o2.field[i][j];
                manhattanDistance2 += getManhattanDistance(i, j, target.positionOf(element2));
            }
        }
        return Integer.compare(manhattanDistance1, manhattanDistance2);
    }

    private int getManhattanDistance(int i, int j, Position position) {
        return Math.abs(position.row - i) + Math.abs(position.column - j);
    }
}
