package com.Pr0rider.chess.board;

public class Board {

    private Board(Builder builder) {
    }

    public Tile getTile (final int tileCoordinate){
        return null;
    }

    public static class Builder {

        public Board build (){
            return new Board (this);
        }
    }
}
