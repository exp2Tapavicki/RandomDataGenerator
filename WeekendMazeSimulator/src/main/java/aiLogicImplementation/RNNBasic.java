package aiLogicImplementation;

import aiLogic.AILogicInterface;
import aiLogicModel.MoveValue;
import ambitious.HibertMaze;
import enumeration.Move;
import models.Configuration;
import models.Food;
import models.Player;
import models.Position;
import opertations.PositionMoving;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration.ListBuilder;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.distribution.UniformDistribution;
import org.deeplearning4j.nn.conf.layers.GravesLSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;

import java.util.ArrayList;

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
public class RNNBasic implements AILogicInterface {
    // RNN dimensions
    public static final int HIDDEN_LAYER_WIDTH = 50;
    public static final int HIDDEN_LAYER_CONT = 2;
    public static final int NUMBER_OF_FEATURE_INPUT = 14;
    public static final int NUMBER_OF_FEATURE_OUTPUT = 4;
    Configuration configuration;
    int[][] maze;
    private HibertMaze hibertMaze;
    private MultiLayerNetwork net;
    private INDArray input;
    private INDArray labels;
    private DataSet trainingData;
    private PositionMoving positionMoving;
    private INDArray output;
    private ArrayList<MoveValue> alMoveValues = new ArrayList<>();

    public RNNBasic(HibertMaze hibertMaze, Configuration configuration, int[][] maze) {
        this.hibertMaze = hibertMaze;
        this.configuration = configuration;
        this.maze = maze;
    }

    public void create() {
        NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder();
        builder.iterations(1000);
        builder.learningRate(0.001);
        builder.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT);
        builder.seed(123);
        builder.biasInit(0);
        builder.miniBatch(true);
        builder.updater(Updater.RMSPROP);
        builder.weightInit(WeightInit.XAVIER);

        ListBuilder listBuilder = builder.list();

        for (int i = 0; i < HIDDEN_LAYER_CONT; i++) {
            GravesLSTM.Builder hiddenLayerBuilder = new GravesLSTM.Builder();
            hiddenLayerBuilder.nIn(i == 0 ? NUMBER_OF_FEATURE_INPUT : HIDDEN_LAYER_WIDTH);
            hiddenLayerBuilder.nOut(HIDDEN_LAYER_WIDTH);
            if (i == 0) {
                hiddenLayerBuilder.activation(Activation.TANH);
            } else {
                hiddenLayerBuilder.activation(Activation.TANH);
            }
            listBuilder.layer(i, hiddenLayerBuilder.build());
        }

        RnnOutputLayer.Builder outputLayerBuilder = new RnnOutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD);
        outputLayerBuilder.activation(Activation.SOFTMAX);
        outputLayerBuilder.nIn(HIDDEN_LAYER_WIDTH);
        outputLayerBuilder.nOut(NUMBER_OF_FEATURE_OUTPUT);
        outputLayerBuilder.weightInit(WeightInit.DISTRIBUTION);
        outputLayerBuilder.dist(new UniformDistribution(0, 1));
        listBuilder.layer(HIDDEN_LAYER_CONT, outputLayerBuilder.build());

        listBuilder.pretrain(false);
        listBuilder.backprop(true);

        // create network
        MultiLayerConfiguration conf = listBuilder.build();
        net = new MultiLayerNetwork(conf);
        net.init();
        net.setListeners(new ScoreIterationListener(1000));

		/*
         * CREATE OUR TRAINING DATA
		 */
        input = Nd4j.zeros(maze.length * maze.length * hibertMaze.getHibertMazeGUI().getAlPlayers().size() * hibertMaze.getHibertMazeGUI().getAlFoods().size(), NUMBER_OF_FEATURE_INPUT);
        labels = Nd4j.zeros(maze.length * maze.length * hibertMaze.getHibertMazeGUI().getAlPlayers().size() * hibertMaze.getHibertMazeGUI().getAlFoods().size(), NUMBER_OF_FEATURE_OUTPUT);
        positionMoving = new PositionMoving(maze);
        int counter = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                int mazeFieldValue = maze[i][j];
                // input neuron for current-char is 1 at "samplePos"
                for (Player player : hibertMaze.getHibertMazeGUI().getAlPlayers()) {
                    for (Food food : hibertMaze.getHibertMazeGUI().getAlFoods()) {
                        Position position = new Position(i, j);
                        input.putScalar(counter, 0, i);
                        input.putScalar(counter, 1, j);
                        input.putScalar(counter, 2, mazeFieldValue);
                        input.putScalar(counter, 3, player.getPlayerNumber());
                        input.putScalar(counter, 4, i);
                        input.putScalar(counter, 5, j);
                        input.putScalar(counter, 6, food.getFoodNumber());
                        input.putScalar(counter, 7, food.getPosition().getX());
                        input.putScalar(counter, 8, food.getPosition().getY());
                        input.putScalar(counter, 9, positionMoving.moveNorth(position) ? 1.00d : 0.00d);
                        input.putScalar(counter, 10, positionMoving.moveSouth(position) ? 1.00d : 0.00d);
                        input.putScalar(counter, 11, positionMoving.moveEast(position) ? 1.00d : 0.00d);
                        input.putScalar(counter, 12, positionMoving.moveWest(position) ? 1.00d : 0.00d);
                        input.putScalar(counter, 13, player.getPosition().getX() == food.getPosition().getX() && player.getPosition().getY() == food.getPosition().getY() ? 1.00d : 0.00d);

                        labels.putScalar(counter, 0, positionMoving.moveNorth(position) ? 1.00d : 0.00d);
                        labels.putScalar(counter, 1, positionMoving.moveSouth(position) ? 1.00d : 0.00d);
                        labels.putScalar(counter, 2, positionMoving.moveEast(position) ? 1.00d : 0.00d);
                        labels.putScalar(counter, 3, positionMoving.moveWest(position) ? 1.00d : 0.00d);
                        counter = counter + 1;
                    }
                }
            }
        }
        trainingData = new DataSet(input, labels);
    }

    public void train() {
        for (int i = 0; i < configuration.getNumberOfEpoch(); i++) {
            System.out.println("Epoch: " + i);
            DataSet trainingDataEpoh = new DataSet(input, labels);

            net.fit(trainingDataEpoh);
            System.out.println("Fit finish");
        }
        INDArray testInit = Nd4j.zeros(NUMBER_OF_FEATURE_INPUT);
        Player player = hibertMaze.getHibertMazeGUI().getAlPlayers().get(0);
        Food food = hibertMaze.getHibertMazeGUI().getAlFoods().get(0);
        testInit.putScalar(0, player.getPosition().getX());
        testInit.putScalar(1, player.getPosition().getY());
        testInit.putScalar(2, maze[player.getPosition().getX()][player.getPosition().getY()]);
        testInit.putScalar(3, player.getPlayerNumber());
        testInit.putScalar(4, player.getPosition().getX());
        testInit.putScalar(5, player.getPosition().getY());
        testInit.putScalar(6, food.getFoodNumber());
        testInit.putScalar(7, food.getPosition().getX());
        testInit.putScalar(8, food.getPosition().getY());
        testInit.putScalar(9, positionMoving.moveNorth(player.getPosition()) ? 1.00d : 0.00d);
        testInit.putScalar(10, positionMoving.moveSouth(player.getPosition()) ? 1.00d : 0.00d);
        testInit.putScalar(11, positionMoving.moveEast(player.getPosition()) ? 1.00d : 0.00d);
        testInit.putScalar(12, positionMoving.moveWest(player.getPosition()) ? 1.00d : 0.00d);
        testInit.putScalar(13, player.getPosition().getX() == food.getPosition().getX() && player.getPosition().getY() == food.getPosition().getY() ? 1.00d : 0.00d);

        output = net.rnnTimeStep(testInit);
    }

    public Move move(Player player) {
        alMoveValues.clear();
        MoveValue moveValue;
        for (Food food : hibertMaze.getHibertMazeGUI().getAlFoods()) {
            double[] outputProbDistribution = new double[NUMBER_OF_FEATURE_OUTPUT];
            for (int k = 0; k < outputProbDistribution.length; k++) {
                outputProbDistribution[k] = output.getDouble(k);
            }
            moveValue = findIndexOfHighestValue(outputProbDistribution);
            alMoveValues.add(moveValue);
            INDArray nextInput = Nd4j.zeros(NUMBER_OF_FEATURE_INPUT);
            nextInput.putScalar(0, player.getPosition().getX());
            nextInput.putScalar(1, player.getPosition().getY());
            nextInput.putScalar(2, maze[player.getPosition().getX()][player.getPosition().getY()]);
            nextInput.putScalar(3, player.getPlayerNumber());
            nextInput.putScalar(4, player.getPosition().getX());
            nextInput.putScalar(5, player.getPosition().getY());
            nextInput.putScalar(6, food.getFoodNumber());
            nextInput.putScalar(7, food.getPosition().getX());
            nextInput.putScalar(8, food.getPosition().getY());
            nextInput.putScalar(9, positionMoving.moveNorth(player.getPosition()) ? 1.00d : 0.00d);
            nextInput.putScalar(10, positionMoving.moveSouth(player.getPosition()) ? 1.00d : 0.00d);
            nextInput.putScalar(11, positionMoving.moveEast(player.getPosition()) ? 1.00d : 0.00d);
            nextInput.putScalar(12, positionMoving.moveWest(player.getPosition()) ? 1.00d : 0.00d);
            nextInput.putScalar(13, player.getPosition().getX() == food.getPosition().getX() && player.getPosition().getY() == food.getPosition().getY() ? 1.00d : 0.00d);
            output = net.rnnTimeStep(nextInput);
        }
        return findBestMove(alMoveValues);
    }

    private Move findBestMove(ArrayList<MoveValue> alMoveValues) {
        Move move = alMoveValues.get(0).getMove();
        double maxValue = alMoveValues.get(0).getValue();
        for (MoveValue alMoveValue : alMoveValues) {
            if (maxValue < alMoveValue.getValue()) {
                maxValue = alMoveValue.getValue();
                move = alMoveValue.getMove();
            }
        }
        return move;
    }

    private MoveValue findIndexOfHighestValue(double[] distribution) {
        MoveValue moveValue = new MoveValue();
        Move move = null;
        double maxValue = 0;
        for (int i = 0; i < distribution.length; i++) {
            try {
                System.out.println("Move:" + Class.forName(Move.class.getName()).getEnumConstants()[i] + " value:" + distribution[i]);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (maxValue < distribution[i]) {
                maxValue = distribution[i];
                try {
                    move = (Move) Class.forName(Move.class.getName()).getEnumConstants()[i];
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        moveValue.setMove(move);
        moveValue.setValue(maxValue);
        System.out.println(moveValue.toString());
        return moveValue;
    }
}
