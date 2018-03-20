package com.Pr0rider.chess.pieces;

import com.Pr0rider.chess.Alliance;
import com.Pr0rider.chess.board.Board;
import com.Pr0rider.chess.board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    public Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        // ----------
        this.isFirstMove = false;
    }

    public abstract Collection<Move> calkulateLegalMoves (final Board board);

    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }

    public int getPiecePosition() {
        return piecePosition;
    }

    public boolean isFirstMove(){
        return this.isFirstMove;
    }
}
