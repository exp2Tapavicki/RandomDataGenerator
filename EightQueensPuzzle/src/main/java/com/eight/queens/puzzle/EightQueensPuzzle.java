package com.eight.queens.puzzle;

import com.eight.queens.puzzle.configuration.Configuration;
import com.eight.queens.puzzle.gui.EightQueensPuzzleGUI;
import com.eight.queens.puzzle.util.SolutionUtil;

import java.awt.*;

public class EightQueensPuzzle {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                SolutionUtil solutionUtil = new SolutionUtil();
                Configuration configuration = new Configuration();
                configuration.setSizeOfCell(50);
                configuration.setSizeOfTable(8);
                configuration.setConsoleApplication(false);
                configuration.setNumberOfQueens(5);
                if (!configuration.isConsoleApplication()) {
                    EightQueensPuzzleGUI eightQueensPuzzleGUI = new EightQueensPuzzleGUI(configuration, solutionUtil);
                    eightQueensPuzzleGUI.createApplication();
                } else {
                    configuration.setQueenPositions(solutionUtil.setRandomPlaces(configuration.getSizeOfTable(), configuration.getNumberOfQueens()));
                    solutionUtil.findSolution(configuration.getQueenPositions());
                    solutionUtil.testSolution();
                }
            }
        });
    }
}
