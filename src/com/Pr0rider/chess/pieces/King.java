package com.Pr0rider.chess.pieces;

import com.Pr0rider.chess.Alliance;
import com.Pr0rider.chess.board.Board;
import com.Pr0rider.chess.board.BoardUtils;
import com.Pr0rider.chess.board.Move;
import com.Pr0rider.chess.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    private final static int[] CANDIDATE_MOVES_CORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(int piecePosition, Alliance pieceAlliance) {
        super (piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calkulateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<> ();
        for (final int currentCandidateOffset : CANDIDATE_MOVES_CORDINATE) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset)||
                    isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)){
                continue;
            }

            if (BoardUtils.isValidCoordinate (candidateDestinationCoordinate)){
                final Tile candidateDestinationTile = board.getTile (candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new Move.MajorMove (board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAlliance)
                        legalMoves.add (new Move.AtackMove (board, this, candidateDestinationCoordinate, pieceAtDestination));
                }
            }

        }
        return ImmutableList.copyOf (legalMoves);
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOfSet){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOfSet == -9 || candidateOfSet == -1 ||
                candidateOfSet == 7);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOfSet){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOfSet == -7 || candidateOfSet == 1 ||
                candidateOfSet == 9);
}
