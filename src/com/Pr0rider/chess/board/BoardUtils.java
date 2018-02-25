package com.Pr0rider.chess.board;

public class BoardUtils{

    public static final boolean[] FIRST_COLUMN = null;

    public BoardUtils() {
        throw new RuntimeException("You cannot instantiate me!");
    }

    public static boolean isValidCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate < 64;
    }
}
