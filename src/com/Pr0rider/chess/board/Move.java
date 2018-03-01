package com.Pr0rider.chess.board;

import com.Pr0rider.chess.pieces.Piece;

public abstract class Move {

    final Board board;
    final Piece movedPiece;
    final int destionationCoordinate;

    public Move(final Board board,
                final Piece movedPiece,
                final int destionationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destionationCoordinate = destionationCoordinate;
    }

    public static class MajorMove extends Move {

        public MajorMove(final Board board,
                         final Piece movedPiece,
                         final int destionationCoordinate) {
            super (board, movedPiece, destionationCoordinate);
        }
    }

    public static class AtackMove extends Move {

        final Piece atackedPiece;

        public AtackMove(final Board board,
                         final Piece movedPiece,
                         final int destionationCoordinate,
                         final Piece atackedPiece) {
            super (board, movedPiece, destionationCoordinate);
            this.atackedPiece = atackedPiece;
        }
    }
}