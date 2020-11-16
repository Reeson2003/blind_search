package com.reeson2003.ai.puzzle;

public enum Action {
    UP("[ ^ ]") {
        @Override
        public State perform(State state) throws ActionNotAvailableException {
            Position position = state.positionOf(0);
            if (position.row > 0)
                return state.swap(position, new Position(position.row - 1, position.column));
            else throw new ActionNotAvailableException(state, this);
        }
    },
    DOWN("[ v ]") {
        @Override
        public State perform(State state) throws ActionNotAvailableException {
            Position position = state.positionOf(0);
            if (position.row < state.getHeight() - 1)
                return state.swap(position, new Position(position.row + 1, position.column));
            else throw new ActionNotAvailableException(state, this);
        }
    },
    LEFT("[ < ]") {
        @Override
        public State perform(State state) throws ActionNotAvailableException {
            Position position = state.positionOf(0);
            if (position.column > 0)
                return state.swap(position, new Position(position.row, position.column - 1));
            else throw new ActionNotAvailableException(state, this);
        }
    },
    RIGHT("[ > ]") {
        @Override
        public State perform(State state) throws ActionNotAvailableException {
            Position position = state.positionOf(0);
            if (position.column < state.getWidth() - 1)
                return state.swap(position, new Position(position.row, position.column + 1));
            else throw new ActionNotAvailableException(state, this);
        }
    };

    private final String symbol;

    Action(String symbol) {
        this.symbol = symbol;
    }

    public abstract State perform(State state) throws ActionNotAvailableException;

    @Override
    public String toString() {
        return symbol;
    }
}
