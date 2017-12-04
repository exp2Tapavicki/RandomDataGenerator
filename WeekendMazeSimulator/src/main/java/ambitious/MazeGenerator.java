package ambitious;

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
public class MazeGenerator {
    public int[][] basicMaze = {{0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0}};
    private int size = 5;

    public int[][] generateMaze(int sizeOfMaze) {
        return generateMaze(basicMaze, sizeOfMaze, 1);
    }

    public int[][] generateMaze(int[][] maze, int sizeOfMaze, int counter) {
        if (counter == sizeOfMaze || sizeOfMaze == 1) {
            return maze;
        }
        size = ((int) Math.pow(2, counter + 2)) + 1;
        int[][] newMaze = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j >= maze.length && i >= maze.length) {
                    int iBasic = i - maze.length + 1;
                    int jBasic = j - maze.length + 1;
                    newMaze[i][j] = maze[jBasic][maze.length - iBasic - 1];
                } else if (j >= maze.length && i < maze.length - 1) {
                    newMaze[i][j] = maze[i][j - maze.length + 1];
                } else if (j < maze.length - 1 && i >= maze.length) {
                    int iBasic = i - maze.length + 1;
                    newMaze[i][j] = maze[maze.length - j - 1][iBasic];
                } else if (j < maze.length - 1 && i < maze.length - 1) {
                    newMaze[i][j] = maze[i][j];
                } else {
                    if ((i == maze.length - 1 && j == 1)
                            || (i == maze.length - 1 && j == size - 2)
                            || (i == maze.length - 2 && j == maze.length - 1)) {
                        newMaze[i][j] = 1;
                    } else {
                        newMaze[i][j] = 0;
                    }
                }
            }
        }
        return generateMaze(newMaze, sizeOfMaze, counter + 1);
    }
}
