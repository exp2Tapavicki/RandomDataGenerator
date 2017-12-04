import ambitious.AILogic;
import ambitious.HibertMaze;
import enumeration.AILogicTypes;
import models.Configuration;

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
public class WeekendMazeSimulator {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setNumberOfFood(50);
        configuration.setFoodEnergy(100);
        configuration.setNumberOfPlayers(50);
        configuration.setPlayersEnergy(100);
        configuration.setSizeOfMaze(5);
        configuration.setSizeOfCell(15);
        configuration.setSleepTime(500);
        configuration.setAiLogicTypes(AILogicTypes.RANDOM);
        configuration.setNumberOfEpoch(6);
        HibertMaze hibertMaze = new HibertMaze();
        hibertMaze.create(configuration);
        AILogic aILogic = new AILogic(hibertMaze, configuration);
        aILogic.run();
    }
}
