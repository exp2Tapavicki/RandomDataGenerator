package models;

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
public class Movement {
    private Position currentPosition;
    private Position northPosition;
    private Position southPosition;
    private Position eastPosition;
    private Position westPosition;
    private List<Movement> movements;
    private boolean moveNorth;
    private boolean moveSouth;
    private boolean moveEast;
    private boolean moveWest;

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Position getNorthPosition() {
        return northPosition;
    }

    public void setNorthPosition(Position northPosition) {
        this.northPosition = northPosition;
    }

    public Position getSouthPosition() {
        return southPosition;
    }

    public void setSouthPosition(Position southPosition) {
        this.southPosition = southPosition;
    }

    public Position getEastPosition() {
        return eastPosition;
    }

    public void setEastPosition(Position eastPosition) {
        this.eastPosition = eastPosition;
    }

    public Position getWestPosition() {
        return westPosition;
    }

    public void setWestPosition(Position westPosition) {
        this.westPosition = westPosition;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

    public boolean isMoveNorth() {
        return moveNorth;
    }

    public void setMoveNorth(boolean moveNorth) {
        this.moveNorth = moveNorth;
    }

    public boolean isMoveSouth() {
        return moveSouth;
    }

    public void setMoveSouth(boolean moveSouth) {
        this.moveSouth = moveSouth;
    }

    public boolean isMoveEast() {
        return moveEast;
    }

    public void setMoveEast(boolean moveEast) {
        this.moveEast = moveEast;
    }

    public boolean isMoveWest() {
        return moveWest;
    }

    public void setMoveWest(boolean moveWest) {
        this.moveWest = moveWest;
    }
}
