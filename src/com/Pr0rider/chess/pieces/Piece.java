package com.Pr0rider.chess.pieces;

import com.Pr0rider.chess.Alliance;
import com.Pr0rider.chess.board.Board;
import com.Pr0rider.chess.board.Move;

import java.util.List;

public class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;

    public Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }

    public abstract List<Move> calkulateLegalMoves (final Board board);

    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }
}
