/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author selim
 */
public class DataPlaying implements Serializable {

    private final int currentPlayer;
    private final int lastmove;
    private final boolean lastMovefilledGrid;
    private final boolean finished;
    private final int result;

    public DataPlaying(int currentPlayer, int lastmove, boolean lastMovefilledGrid, boolean finished, int result) {
        this.currentPlayer = currentPlayer;
        this.lastmove = lastmove;
        this.lastMovefilledGrid = lastMovefilledGrid;
        this.finished = finished;
        this.result = result;

    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getLastmove() {
        return lastmove;
    }

    public boolean isLastMovefilledGrid() {
        return lastMovefilledGrid;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getResult() {
        return result;
    }

}
