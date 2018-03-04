package com.eight.queens.puzzle.util;

import com.eight.queens.puzzle.actions.ActionInterface;

public class SolutionUtil implements ActionInterface {

    public int[][] setRandomPlaces(int sizeOfTable, int numberOfQueens) {
        int[][] queenPostions = new int[sizeOfTable][sizeOfTable];
        int count = 0;
        for (int i = 0; count < numberOfQueens; i++) {
            int x = (int) (Math.random() * (sizeOfTable - 1 + 1));
            int y = (int) (Math.random() * (sizeOfTable - 1 + 1));
            if (queenPostions[x][y] != 1 && checkQueenPosition(queenPostions, x, y)) {
                queenPostions[x][y] = 1;
                count = count + 1;
            }
        }
        return queenPostions;
    }

    public boolean findSolution(int[][] queenPostions) {


        return true;
    }

    public void testSolution() {

    }

    public boolean checkQueenPosition(int[][] queenPostions, int x, int y) {
        if (queenPostions != null) {
            for (int i = 0; i < queenPostions.length; i++) {
                if (queenPostions[i][y] == 1 || queenPostions[x][i] == 1) {
                    return false;
                }
            }

            int xTmp = x;
            int yTmp = y;
            while (xTmp > 0 && yTmp > 0) {
                xTmp = xTmp - 1;
                yTmp = yTmp - 1;
                if (queenPostions[xTmp][yTmp] == 1) {
                    return false;
                }
            }

            xTmp = x;
            yTmp = y;
            while (xTmp < queenPostions.length - 1 && yTmp < queenPostions.length - 1) {
                xTmp = xTmp + 1;
                yTmp = yTmp + 1;
                if (queenPostions[xTmp][yTmp] == 1) {
                    return false;
                }
            }

            xTmp = x;
            yTmp = y;
            while (xTmp < queenPostions.length - 1 && yTmp > 0) {
                xTmp = xTmp + 1;
                yTmp = yTmp - 1;
                if (queenPostions[xTmp][yTmp] == 1) {
                    return false;
                }
            }

            xTmp = x;
            yTmp = y;
            while (xTmp > 0 && yTmp < queenPostions.length - 1) {
                xTmp = xTmp - 1;
                yTmp = yTmp + 1;
                if (queenPostions[xTmp][yTmp] == 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
