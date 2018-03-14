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

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVES_CORDINATE = {7, 8, 9, 16};

    public Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super (piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calkulateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<> ();

        for (final int currentCandidateOffset : CANDIDATE_MOVES_CORDINATE) {
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance ().getDirection () * currentCandidateOffset);
            if (BoardUtils.isValidCoordinate (candidateDestinationCoordinate)) {
                continue;
            }
            if (currentCandidateOffset == 8 && !board.getTile (candidateDestinationCoordinate).isTileOccupied ()){
              // todo Promotions
                legalMoves.add(new Move.MajorMove (board, this, candidateDestinationCoordinate));
            } else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                    ((BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance ().isBlack ()) ||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance ().isWhite ()))){
                final int behindCandidateDestinationCoordinate = this.piecePosition + (8 * this.getPieceAlliance ().getDirection ());
                if (!board.getTile (behindCandidateDestinationCoordinate).isTileOccupied () &&
                        !board.getTile (candidateDestinationCoordinate).isTileOccupied ()){
                    legalMoves.add (new Move.MajorMove (board, this, candidateDestinationCoordinate));
                }
            } else if (currentCandidateOffset == 7 &&
                        ((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.getPieceAlliance ().isBlack()) ||
                        BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.getPieceAlliance ().isWhite ())){
                    if (board.getTile (candidateDestinationCoordinate).isTileOccupied ()) {
                        // todo Promotions
                        final Tile candidateDestinationTile = board.getTile (candidateDestinationCoordinate);
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece ();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance ();
                        if (this.pieceAlliance != pieceAlliance)
                            legalMoves.add (new Move.AtackMove (board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                } else if (currentCandidateOffset == 9 &&
                        ((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.getPieceAlliance ().isWhite ()) ||
                                BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.getPieceAlliance ().isBlack ())) {
                    if (board.getTile (candidateDestinationCoordinate).isTileOccupied ()) {
                        // todo Promotions
                        final Tile candidateDestinationTile = board.getTile (candidateDestinationCoordinate);
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece ();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance ();
                        if (this.pieceAlliance != pieceAlliance)
                            legalMoves.add (new Move.AtackMove (board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }
        return ImmutableList.copyOf (legalMoves);

    }
    }
}