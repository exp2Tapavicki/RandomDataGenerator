package ambitious;

import aiLogic.AILogicInterface;
import aiLogicImplementation.RNNBasic;
import com.rdg.constants.Const;
import com.rdg.util.RandomUtil;
import enumeration.Move;
import models.Configuration;
import models.Player;

import javax.swing.*;

/*
* Copyright 2017 Radislav Tapavicki <radislavtt@gmail.com>
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
public class AILogic implements Runnable {
    private HibertMaze hibertMaze;
    private Configuration configuration;

    public AILogic(HibertMaze hibertMaze, Configuration configuration) {
        this.hibertMaze = hibertMaze;
        this.configuration = configuration;
    }

    @Override
    public void run() {
        AILogicInterface aiLogicInterface;
        switch (configuration.getAiLogicTypes()) {
            case RNN_BASIC:
                aiLogicInterface = new RNNBasic(hibertMaze, configuration, hibertMaze.getHibertMazeGUI().getMaze());
                System.out.println("Create neural network");
                aiLogicInterface.create();
                System.out.println("Train neural network");
                aiLogicInterface.train();
                break;
            case RANDOM:
                aiLogicInterface = null;
                break;
            default:
                aiLogicInterface = null;
                break;
        }
        int counter = 0;

        while (!hibertMaze.getHibertMazeGUI().checkWinner()) {
            for (Player player : hibertMaze.getHibertMazeGUI().getAlPlayers()) {
                if (!player.isDied()) {
                    hibertMaze.getHibertMazeGUI().setSelectedPlayer(player);
                    Move move;
                    switch (configuration.getAiLogicTypes()) {
                        case RNN_BASIC:
                            move = aiLogicInterface.move(player);
                            System.out.println("Move: " + move.toString());
                            hibertMaze.getHibertMazeGUI().moveSomewhere(move);
                            break;
                        case RANDOM:
                            move = getRandomMove(player);
                            System.out.println("Move: " + move.toString());
                            hibertMaze.getHibertMazeGUI().moveSomewhere(move);
                            break;
                    }
                }
            }
            counter++;
            System.out.println("Step: " + counter);
            try {
                Thread.sleep(configuration.getSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(hibertMaze.getHibertMazeGUI().getFrame(), "All players died.");
    }

    public Move getRandomMove(Player player) {
        boolean canMove = true;
        Move move = null;
        while (canMove) {
            switch ((Move) RandomUtil.getRandomEnum(Const.ZERO, Move.class.getEnumConstants().length - Const.ONE, null, Move.class)) {
                case NORTH:
                    if (hibertMaze.getHibertMazeGUI().getPositionMoving().moveNorth(player.getPosition())) {
                        canMove = false;
                        move = Move.NORTH;
                    }
                    break;
                case SOUTH:
                    if (hibertMaze.getHibertMazeGUI().getPositionMoving().moveSouth(player.getPosition())) {
                        canMove = false;
                        move = Move.SOUTH;
                    }
                    break;
                case EAST:
                    if (hibertMaze.getHibertMazeGUI().getPositionMoving().moveEast(player.getPosition())) {
                        canMove = false;
                        move = Move.EAST;
                    }
                    break;
                case WEST:
                    if (hibertMaze.getHibertMazeGUI().getPositionMoving().moveWest(player.getPosition())) {
                        canMove = false;
                        move = Move.WEST;
                    }
                    break;
            }
        }
        return move;
    }
}
