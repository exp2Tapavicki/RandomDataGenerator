# WeekendMazeSimulator
Java, Maven, RandomDataGenerator.

WeekendMazeSimulator project is used for dynamical simulation/generation of Hibert Maze with players and food energy. 
It is a simulator for machine learning. Goal is to have different machine learning models whose purpose is to evaluation and testing for this specific dynamic cases. 
Game goal is for every player to survive as long as possible.
    
Everything is dynamically configurable through Configuration class.
    
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

At the moment I didn't found out suitable model to apply. 
Limits for everything is resources you have(Processor and memory).
It would be good to increase Heap Space for the VM with -Xms and -Xmx. Like in example.
 
-Xms1g -Xmx8g 

# Getting Started

Clone or download project. Import project as Maven Project. 
Check out WeekendMazeSimulator class, main method and Configuration.
Run it.

# Weekend Maze Simulator Screenshots

* Size of maze 5, cell size 15.
![](images/WeekendMazeSimulator1.png)

* Size of maze 7, cell size 4.
![](images/WeekendMazeSimulator2.png)

# Video
https://youtu.be/Sz74KC70j1o