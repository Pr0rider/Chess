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

public class Queen extends Piece{
    private final static int[] CANDIDATE_VECTOR_CORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};

    public Queen(int piecePosition, Alliance pieceAlliance) {
        super (piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calkulateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<> ();
        for (final int currentCandidateOffset : CANDIDATE_VECTOR_CORDINATE) {
            int candidateDestinationCoordinate = this.piecePosition;

            while (BoardUtils.isValidCoordinate (candidateDestinationCoordinate)) {
                candidateDestinationCoordinate += currentCandidateOffset;

                if (BoardUtils.isValidCoordinate (candidateDestinationCoordinate)) {
                    if (isFirstColumnExclusion(candidateDestinationCoordinate, currentCandidateOffset) || isEighthColumnExclusion (candidateDestinationCoordinate, currentCandidateOffset)){
                        break;
                    }
                }
                final Tile candidateDestinationTile = board.getTile (candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied ()) {
                    legalMoves.add (new Move.MajorMove (board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece ();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance ();
                    if (this.pieceAlliance != pieceAlliance)
                        legalMoves.add (new Move.AtackMove (board, this, candidateDestinationCoordinate, pieceAtDestination));
                }
                break;
            }
        }
        //return unmodifiableList(legalMoves)﻿
        return ImmutableList.copyOf (legalMoves);
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOfSet) {
        return (BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOfSet == -9 || candidateOfSet == -1 || candidateOfSet == 7));
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOfSet){
        return (BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOfSet == -7 || candidateOfSet == 1 || candidateOfSet == 9));
    }
}
