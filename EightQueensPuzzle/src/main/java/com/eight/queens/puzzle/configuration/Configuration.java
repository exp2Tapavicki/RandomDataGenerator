package com.eight.queens.puzzle.configuration;

public class Configuration {
    private int sizeOfCell;
    private int sizeOfTable;
    private int[][] queenPositions;
    private boolean consoleApplication;
    private int numberOfQueens;

    public int getSizeOfCell() {
        return sizeOfCell;
    }

    public void setSizeOfCell(int sizeOfCell) {
        this.sizeOfCell = sizeOfCell;
    }

    public int getSizeOfTable() {
        return sizeOfTable;
    }

    public void setSizeOfTable(int sizeOfTable) {
        this.sizeOfTable = sizeOfTable;
    }

    public int[][] getQueenPositions() {
        return queenPositions;
    }

    public void setQueenPositions(int[][] queenPositions) {
        this.queenPositions = queenPositions;
    }

    public boolean isConsoleApplication() {
        return consoleApplication;
    }

    public void setConsoleApplication(boolean consoleApplication) {
        this.consoleApplication = consoleApplication;
    }

    public int getNumberOfQueens() {
        return numberOfQueens;
    }

    public void setNumberOfQueens(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
    }
}
