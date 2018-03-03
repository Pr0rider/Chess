package com.Pr0rider.chess.pieces;

import com.Pr0rider.chess.Alliance;
import com.Pr0rider.chess.board.Board;
import com.Pr0rider.chess.board.BoardUtils;
import com.Pr0rider.chess.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVES_CORDINATE = {7, 8, 9, 16};

    public Pawn(int piecePosition, Alliance pieceAlliance) {
        super (piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calkulateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<> ();

        for (final int currentCandidateOffset : CANDIDATE_MOVES_CORDINATE) {
            int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance ().getDirection () * currentCandidateOffset);
            if (BoardUtils.isValidCoordinate (candidateDestinationCoordinate)) {
                continue;
            }
            if (currentCandidateOffset == 8 && !board.getTile (candidateDestinationCoordinate).isTileOccupied ()){
                legalMoves.add(new Move.MajorMove (board, this, candidateDestinationCoordinate));
            }

            return legalMoves;
        }
    }
}