package com.Pr0rider.chess.pieces;

import com.Pr0rider.chess.Alliance;
import com.Pr0rider.chess.board.Board;
import com.Pr0rider.chess.board.BoardUtils;
import com.Pr0rider.chess.board.Move;
import com.Pr0rider.chess.board.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece{

    private final static int[] CANDIDATE_MOVES_CORDINATE = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calkulateLegalMoves(Board board) {

        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();

        for final int currentCandidateOffset : CANDIDATE_MOVES_CORDINATE {
            candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if(BoardUtils.isValidCoordinate(candidateDestinationCoordinate)){
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset)){
                    continue;
                }
                final Tile candidateDestinationTile = board.getTile;

                if (candidateDestinationTile.isTileOccupied(){
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if (this.pieceAlliance != pieceAlliance){
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves)﻿;
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOfSet){

       return BoardUtils.FIRST_COLUMN(currentPosition) && ((candidateOfSet == -17) || (candidateOfSet == -10) ||
                (candidateOfSet == 6) || (candidateOfSet == 15));

    }

}
