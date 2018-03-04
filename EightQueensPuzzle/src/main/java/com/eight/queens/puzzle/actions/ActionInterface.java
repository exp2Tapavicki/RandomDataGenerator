package com.eight.queens.puzzle.actions;

public interface ActionInterface {
    int[][] setRandomPlaces(int sizeOfTable, int numberOfQuesnts);

    boolean findSolution(int[][] queenPostions);

    void testSolution();
}
