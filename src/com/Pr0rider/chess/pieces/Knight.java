package com.Pr0rider.chess.pieces;

import com.Pr0rider.chess.Alliance;
import com.Pr0rider.chess.board.Board;
import com.Pr0rider.chess.board.BoardUtils;
import com.Pr0rider.chess.board.Move;
import com.Pr0rider.chess.board.Move.MajorMove;
import com.Pr0rider.chess.board.Move.AtackMove;
import com.Pr0rider.chess.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Knight extends Piece{

    private final static int[] CANDIDATE_MOVES_CORDINATE = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calkulateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVES_CORDINATE) {
          final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if(BoardUtils.isValidCoordinate(candidateDestinationCoordinate)){
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset)
                        || isSecondColumnExclusion (this.piecePosition, currentCandidateOffset)
                        || isSeventhColumnExclusion (this.piecePosition, currentCandidateOffset)
                        || isEighthColumnExclusion (this.piecePosition, currentCandidateOffset)) {
                    continue;
                }
                final Tile candidateDestinationTile = board.getTile(this.piecePosition);
                if (candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new MajorMove (board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAlliance)
                        legalMoves.add (new AtackMove (board, this, candidateDestinationCoordinate, pieceAtDestination));
                }
            }
        }
        //return unmodifiableList(legalMoves)﻿; Error:(49, 56) java: illegal character: '\ufeff'
        return ImmutableList.copyOf (legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOfSet){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOfSet == -17 || candidateOfSet == -10 ||
                candidateOfSet == 6 || candidateOfSet == 15);
    }
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOfSet){
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOfSet == -10 || candidateOfSet == 6);
    }
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOfSet){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOfSet == -6 || candidateOfSet == 10);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOfSet){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOfSet == -15 || candidateOfSet == -6 ||
                candidateOfSet == 10 || candidateOfSet == 17);
    }

}
