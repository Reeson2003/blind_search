package com.reeson2003.ai.puzzle;

import java.util.Comparator;

public class MissPositionComparator implements Comparator<State> {

    private final State target;

    public MissPositionComparator(State target) {
        this.target = target;
    }

    @Override
    public int compare(State o1, State o2) {
        int missPositions1 = 0;
        int missPositions2 = 0;
        for (int i = 0; i < target.getWidth(); i++) {
            for (int j = 0; j < target.getHeight(); j++) {
                if (target.field[i][j] != o1.field[i][j])
                    missPositions1++;
                if (target.field[i][j] != o2.field[i][j])
                    missPositions2++;
            }
        }
        return Integer.compare(missPositions1, missPositions2);
    }
}
