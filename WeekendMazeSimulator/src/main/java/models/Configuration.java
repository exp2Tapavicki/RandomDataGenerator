package models;

import enumeration.AILogicTypes;

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
public class Configuration {
    private int numberOfEpoch;
    private int sizeOfMaze;
    private int numberOfPlayers;
    private int playersEnergy;
    private int sizeOfCell;
    private int numberOfFood;
    private int foodEnergy;
    private int sleepTime;
    private AILogicTypes aiLogicTypes;
    private int listenerFrequency;

    public int getNumberOfEpoch() {
        return numberOfEpoch;
    }

    public void setNumberOfEpoch(int numberOfEpoch) {
        this.numberOfEpoch = numberOfEpoch;
    }

    public int getSizeOfMaze() {
        return sizeOfMaze;
    }

    public void setSizeOfMaze(int sizeOfMaze) {
        this.sizeOfMaze = sizeOfMaze;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getPlayersEnergy() {
        return playersEnergy;
    }

    public void setPlayersEnergy(int playersEnergy) {
        this.playersEnergy = playersEnergy;
    }

    public int getSizeOfCell() {
        return sizeOfCell;
    }

    public void setSizeOfCell(int sizeOfCell) {
        this.sizeOfCell = sizeOfCell;
    }

    public int getNumberOfFood() {
        return numberOfFood;
    }

    public void setNumberOfFood(int numberOfFood) {
        this.numberOfFood = numberOfFood;
    }

    public int getFoodEnergy() {
        return foodEnergy;
    }

    public void setFoodEnergy(int foodEnergy) {
        this.foodEnergy = foodEnergy;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public AILogicTypes getAiLogicTypes() {
        return aiLogicTypes;
    }

    public void setAiLogicTypes(AILogicTypes aiLogicTypes) {
        this.aiLogicTypes = aiLogicTypes;
    }

    public int getListenerFrequency() {
        return listenerFrequency;
    }

    public void setListenerFrequency(int listenerFrequency) {
        this.listenerFrequency = listenerFrequency;
    }
}
