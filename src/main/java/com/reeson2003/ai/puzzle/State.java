package com.reeson2003.ai.puzzle;

import java.util.Arrays;

public class State {
    public final int[][] field;

    public State(int[][] field) {
        if (!checkRectangle(field))
            throw new IllegalArgumentException("Array is not rectangle");
        this.field = field;
    }

    private boolean checkRectangle(int[][] field) {
        if (field.length <= 0) {
            return false;
        } else {
            int length = field[0].length;
            for (int i = 1; i < field.length; i++) {
                if (field[i].length != length)
                    return false;
            }
            return true;
        }
    }

    public static State make3x3(int _11, int _12, int _13, int _21, int _22, int _23, int _31, int _32, int _33) {
        return new State(new int[][]{{_11, _12, _13}, {_21, _22, _23}, {_31, _32, _33}});
    }

    public int getHeight() {
        return field.length;
    }

    public int getWidth() {
        return field[0].length;
    }

    public Position positionOf(int element) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == element)
                    return new Position(i, j);
            }
        }
        throw new IllegalArgumentException("Element not found");
    }

    public State swap(Position one, Position two) {
        int[][] newField = cloneField();
        try {
            int buf = newField[one.row][one.column];
            newField[one.row][one.column] = newField[two.row][two.column];
            newField[two.row][two.column] = buf;
            return new State(newField);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error on swap elements", e);
        }
    }

    private int[][] cloneField() {
        int[][] newField = new int[field.length][];
        for (int i = 0; i < field.length; i++) {
            newField[i] = new int[field[i].length];
            System.arraycopy(field[i], 0, newField[i], 0, field[i].length);
        }
        return newField;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : field) {
            sb.append('|');
            for (int next : ints) {
                sb.append(next).append('|');
            }
            sb.append('\n');
        }
        return sb.deleteCharAt(sb.length() - 1)
                .toString();
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(field);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof State && Arrays.deepEquals(field, ((State) obj).field));
    }
}
