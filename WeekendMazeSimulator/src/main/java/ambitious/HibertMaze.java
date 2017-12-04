package ambitious;

import com.rdg.util.RandomUtil;
import models.*;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
public class HibertMaze {
    private HibertMazeGUI hibertMazeGUI;

    public static Player addNewPlayer(int[][] maze, List alPlayers, List alFoods, Configuration configuration, int index) {
        Player player = new Player();
        boolean coordinateOk = true;
        while (coordinateOk) {
            int xCoordinate = RandomUtil.getRandomPrimitiveInt(0, maze.length - 1);
            int yCoordinate = RandomUtil.getRandomPrimitiveInt(0, maze.length - 1);
            if (maze[xCoordinate][yCoordinate] == 0 && checkAvailableCordinate(xCoordinate, yCoordinate, alPlayers)) {
                coordinateOk = false;
                Position position = new Position();
                position.setX(xCoordinate);
                position.setY(yCoordinate);
                player.setPosition(position);
            }
        }
        player.setName("Player: " + index);
        player.setPlayerNumber(index);
        boolean colorsOk = true;
        while (colorsOk) {
            int rNumber = RandomUtil.getRandomPrimitiveInt(0, 255);
            int gNumber = RandomUtil.getRandomPrimitiveInt(0, 255);
            int bNumber = RandomUtil.getRandomPrimitiveInt(0, 255);
            if (checkAvailableColor(rNumber, gNumber, bNumber, alPlayers)) {
                player.setColor(new Color(rNumber, gNumber, bNumber));
                colorsOk = false;
            }
        }
        player.setDied(false);
        player.setMoveCount(configuration.getPlayersEnergy());
        return player;
    }

    public static Food addRandomFood(int[][] maze, List alPlayers, List alFoods, Configuration configuration, int index) {
        Food food = new Food();
        food.getPosition().setExitColor(Color.RED);
        boolean checkFood = true;
        while (checkFood) {
            int xCoordinate = RandomUtil.getRandomPrimitiveInt(0, maze.length - 1);
            int yCoordinate = RandomUtil.getRandomPrimitiveInt(0, maze.length - 1);
            if (maze[xCoordinate][yCoordinate] == 0 && checkAvailableCordinate(xCoordinate, yCoordinate, alPlayers) && checkAvailableCordinate(xCoordinate, yCoordinate, alFoods)) {
                checkFood = false;
                food.getPosition().setX(xCoordinate);
                food.getPosition().setY(yCoordinate);
                food.setFoodEnergy(configuration.getFoodEnergy());
                food.setName("Food: " + index);
                food.setFoodNumber(index);
                food.setUsed(false);
            }
        }
        return food;
    }

    public static boolean checkAvailableCordinate(int x, int y, List<Object> al) {
        for (Object obj : al) {
            if (((Base) obj).getPosition().getX() == x && ((Base) obj).getPosition().getY() == y) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkAvailableColor(int r, int g, int b, List<Object> alPlayers) {
        if ((r == 255 && g == 255 && b == 255)
                || (r == 0 && g == 0 && b == 0)) {
            return false;
        }
        for (Object player : alPlayers) {
            if (((Player) player).getColor().getRed() == r && ((Player) player).getColor().getGreen() == g && ((Player) player).getColor().getBlue() == b) {
                return false;
            }
        }
        return true;
    }

    public void create(Configuration configuration) {
        MazeGenerator mazeBasic = new MazeGenerator();
        int[][] maze = mazeBasic.generateMaze(configuration.getSizeOfMaze());
        List alPlayers = new CopyOnWriteArrayList();
        List alFoods = new CopyOnWriteArrayList();
        for (int i = 0; i < configuration.getNumberOfPlayers(); i++) {
            alPlayers.add(addNewPlayer(maze, alPlayers, alFoods, configuration, i));
        }
        for (int j = 0; j < configuration.getNumberOfFood(); j++) {
            alFoods.add(addRandomFood(maze, alPlayers, alFoods, configuration, j));
        }
        hibertMazeGUI = new HibertMazeGUI(maze, alPlayers, alFoods, configuration);
        hibertMazeGUI.createApplication();
    }

    public HibertMazeGUI getHibertMazeGUI() {
        return hibertMazeGUI;
    }

    public void setHibertMazeGUI(HibertMazeGUI hibertMazeGUI) {
        this.hibertMazeGUI = hibertMazeGUI;
    }
}
