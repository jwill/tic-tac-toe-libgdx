package com.udacity.gamedev.tictactoe;

/**
 * Created by jarrodparkes on 1/3/16.
 */
public class CellPosition {

    int r;
    int c;

    public CellPosition(CellPosition position) {
        this.r = position.r;
        this.c = position.c;
    }

    public CellPosition(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "r: " + r + ", c: " + c;
    }
}
