package opertations;

import models.Food;
import models.Position;

import java.util.List;

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
public class PositionMoving {
    private int[][] maze;

    public PositionMoving(int[][] maze) {
        this.maze = maze;
    }

    public boolean moveNorth(Position position) {
        return position.getX() > 0 && maze[position.getX() - 1][position.getY()] == 0;
    }

    public boolean moveSouth(Position position) {
        return position.getX() < maze.length - 1 && maze[position.getX() + 1][position.getY()] == 0;
    }

    public boolean moveEast(Position position) {
        return position.getY() < maze.length - 1 && maze[position.getX()][position.getY() + 1] == 0;
    }

    public boolean moveWest(Position position) {
        return position.getY() > 0 && maze[position.getX()][position.getY() - 1] == 0;
    }

    public boolean moveNorthCheckFood(Position position, List<Food> alFood) {
        for (Food food : alFood) {
            if (position.getX() - 1 == food.getPosition().getX() && position.getY() == food.getPosition().getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean moveSouthCheckFood(Position position, List<Food> alFood) {
        for (Food food : alFood) {
            if (position.getX() + 1 == food.getPosition().getX() && position.getY() == food.getPosition().getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean moveEastCheckFood(Position position, List<Food> alFood) {
        for (Food food : alFood) {
            if (position.getX() == food.getPosition().getX() && position.getY() + 1 == food.getPosition().getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean moveWestCheckFood(Position position, List<Food> alFood) {
        for (Food food : alFood) {
            if (position.getX() == food.getPosition().getX() && position.getY() - 1 == food.getPosition().getY()) {
                return true;
            }
        }
        return false;
    }
}
