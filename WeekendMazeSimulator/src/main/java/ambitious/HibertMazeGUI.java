package ambitious;

import enumeration.Move;
import models.Configuration;
import models.Food;
import models.Player;
import opertations.PositionMoving;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class HibertMazeGUI {
    private int[][] maze;
    private List<Player> alPlayers;
    private List<Food> alFoods;
    private Player selectedPlayer;
    private JTable table;
    private JPanel panel;
    private JFrame frame;
    private PositionMoving positionMoving;
    private Configuration configuration;

    public HibertMazeGUI(int[][] maze, List alPlayers, List alFoods, Configuration configuration) {
        this.maze = maze;
        this.alPlayers = alPlayers;
        this.alFoods = alFoods;
        this.positionMoving = new PositionMoving(maze);
        this.configuration = configuration;
    }

    public void createApplication() {
        frame = new JFrame("Weekend Maze Simulator");
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.black);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createTable(maze);
        panel = new JPanel();
        panel.add(table);
        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public void createTable(int[][] maze) {
        table = new JTable();

        DefaultTableModel tableModel = new DefaultTableModel(maze.length, maze.length) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setModel(tableModel);
        table.setShowGrid(false);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setCellSelectionEnabled(false);
//        ListSelectionModel cellSelectionModel = table.getSelectionModel();
//        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent e) {
//                int selectedRow = table.getSelectedRow();
//                int selectedColumn = table.getSelectedColumn();
//                for (Player player : alPlayers) {
//                    if (selectedRow == player.getPosition().getX() && selectedColumn == player.getPosition().getY()){
//                        selectedPlayer = player;
//                    }
//                }
//            }
//
//        });

        //disable table actions via 'none'
        table.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        table.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        table.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        ((InputMap) UIManager.get("Table.ancestorInputMap")).put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        table.getActionMap().put("moveDown", moveDown());

        table.getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveUp");
        table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("UP"), "moveUp");
        table.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("UP"), "moveUp");
        table.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "moveUp");
        ((InputMap) UIManager.get("Table.ancestorInputMap")).put(KeyStroke.getKeyStroke("UP"), "moveUp");
        table.getActionMap().put("moveUp", moveUp());


        table.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        table.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        table.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        ((InputMap) UIManager.get("Table.ancestorInputMap")).put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        table.getActionMap().put("moveLeft", moveLeft());


        table.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        table.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        table.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        ((InputMap) UIManager.get("Table.ancestorInputMap")).put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        table.getActionMap().put("moveRight", moveRight());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 1) {
                JTable target = (JTable) e.getSource();
                int selectedRow = target.getSelectedRow();
                int selectedColumn = target.getSelectedColumn();
                boolean selected = false;
                for (Player player : alPlayers) {
                    if (selectedRow == player.getPosition().getX() && selectedColumn == player.getPosition().getY()) {
                        selectedPlayer = player;
                        selected = true;
                    }
                }
                if (!selected) {
                    selectedPlayer = null;
                }
            }
//            }
        });


        table.setRowHeight(configuration.getSizeOfCell());
        table.setFillsViewportHeight(true);
        table.setTableHeader(null);
        table.setEnabled(true);
        for (int i = 0; i < maze.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new CustomRenderer());
            table.getColumnModel().getColumn(i).setMinWidth(2);
            table.getColumnModel().getColumn(i).setMaxWidth(configuration.getSizeOfCell());
            table.getColumnModel().getColumn(i).setPreferredWidth(configuration.getSizeOfCell());

        }
    }

    public boolean checkWinner() {
//        if (selectedPlayer != null && selectedPlayer.getPosition().getX() == exit.getX() && selectedPlayer.getPosition().getY() == exit.getY()){
//
//            JOptionPane jOptionPane = new JOptionPane(selectedPlayer.getName() + " have found exit", JOptionPane.INFORMATION_MESSAGE);
//            jOptionPane.setOpaque(true);
//            jOptionPane.setBackground(selectedPlayer.getColor());
//            jOptionPane.createDialog(frame, "Exit found").setVisible(true);
//
//            alPlayers.remove(selectedPlayer);
//            selectedPlayer = null;
//
//            if(alPlayers.isEmpty()){
//                JOptionPane.showMessageDialog(frame, "All players found exit.");
//            }
//            return true;
//        }
        int count = 0;
        for (Player player : alPlayers) {
            if (player.isDied()) {
                count++;
                continue;
            }
            for (Food food : alFoods) {
                if (player.getPosition().getX() == food.getPosition().getX() && player.getPosition().getY() == food.getPosition().getY()) {

//                    if (player.isFoundExit()) {
//                        JOptionPane jOptionPane = new JOptionPane(player.getName() + " have found exit", JOptionPane.INFORMATION_MESSAGE);
//                        jOptionPane.setOpaque(true);
//                        jOptionPane.setBackground(player.getColor());
//                        jOptionPane.createDialog(frame, "Exit found").setVisible(true);
//                        alPlayers.remove(player);
//                    }
//                    player.setFoundExit(true);
//                    if (player.isFoundExit()) {
//                        count += 1;
//                    }
                    if (food.isUsed()) {
                        break;
                    } else {
                        player.setMoveCount(player.getMoveCount() + food.getFoodEnergy());
                        Food tmpFood = HibertMaze.addRandomFood(maze, alPlayers, alFoods, configuration, food.getFoodNumber());
                        food.setUsed(true);
                        alFoods.remove(food);
                        alFoods.add(tmpFood);
                        break;
                    }
                }
            }
            player.setMoveCount(player.getMoveCount() - 1);
            if (player.getMoveCount() - 1 <= 0) {
                player.setDied(true);
                count += 1;
            }
        }

        if (count == alPlayers.size()) {
            return true;
        }
        return false;
    }

    AbstractAction moveDown() {
        AbstractAction moveDown = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveSomewhere(Move.SOUTH);
            }
        };
        return moveDown;
    }

    AbstractAction moveUp() {
        AbstractAction moveUp = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveSomewhere(Move.NORTH);
            }
        };
        return moveUp;
    }

    AbstractAction moveLeft() {
        AbstractAction moveLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveSomewhere(Move.WEST);
            }
        };
        return moveLeft;
    }

    AbstractAction moveRight() {
        AbstractAction moveRight = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                moveSomewhere(Move.EAST);
            }
        };
        return moveRight;
    }

    public void moveSomewhere(Move move) {
        switch (move) {
            case NORTH:
                if (selectedPlayer != null) {
                    if (positionMoving.moveNorth(selectedPlayer.getPosition())) {
                        selectedPlayer.getPosition().setX(selectedPlayer.getPosition().getX() - 1);
                        selectedPlayer.setLastMove(Move.NORTH);
                        table.changeSelection(selectedPlayer.getPosition().getX(), selectedPlayer.getPosition().getY(), false, false);
                    }
                }
//                else {
//                    Position position = new Position(table.getSelectedRow(), table.getSelectedColumn());
//                    if(positionMoving.moveNorth(position)) {
//                        table.changeSelection(table.getSelectedRow() - 1, table.getSelectedColumn(), false, false);
//                    }
//                }
                break;
            case SOUTH:
                if (selectedPlayer != null) {
                    if (positionMoving.moveSouth(selectedPlayer.getPosition())) {
                        selectedPlayer.getPosition().setX(selectedPlayer.getPosition().getX() + 1);
                        selectedPlayer.setLastMove(Move.SOUTH);
                        table.changeSelection(selectedPlayer.getPosition().getX(), selectedPlayer.getPosition().getY(), false, false);
                    }
                }
//                else {
//                    Position position = new Position(table.getSelectedRow(), table.getSelectedColumn());
//                    if(positionMoving.moveSouth(position)) {
//                        table.changeSelection(table.getSelectedRow() + 1, table.getSelectedColumn(), false, false);
//                    }
//                }
                break;
            case EAST:
                if (selectedPlayer != null) {
                    if (positionMoving.moveEast(selectedPlayer.getPosition())) {
                        selectedPlayer.getPosition().setY(selectedPlayer.getPosition().getY() + 1);
                        selectedPlayer.setLastMove(Move.EAST);
                        table.changeSelection(selectedPlayer.getPosition().getX(), selectedPlayer.getPosition().getY(), false, false);
                    }
                }
//                else {
//                    Position position = new Position(table.getSelectedRow(), table.getSelectedColumn());
//                    if(positionMoving.moveEast(position)) {
//                        table.changeSelection(table.getSelectedRow(), table.getSelectedColumn() + 1, false, false);
//                    }
//                }
                break;
            case WEST:
                if (selectedPlayer != null) {
                    if (positionMoving.moveWest(selectedPlayer.getPosition())) {
                        selectedPlayer.getPosition().setY(selectedPlayer.getPosition().getY() - 1);
                        selectedPlayer.setLastMove(Move.WEST);
                        table.changeSelection(selectedPlayer.getPosition().getX(), selectedPlayer.getPosition().getY(), false, false);
                    }
                }
//                else {
//                    Position position = new Position(table.getSelectedRow(), table.getSelectedColumn());
//                    if(positionMoving.moveWest(position)) {
//                        table.changeSelection(table.getSelectedRow(), table.getSelectedColumn() - 1, false, false);
//                    }
//                }
                break;
        }
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public List<Player> getAlPlayers() {
        return alPlayers;
    }

    public void setAlPlayers(List<Player> alPlayers) {
        this.alPlayers = alPlayers;
    }

    public List<Food> getAlFoods() {
        return alFoods;
    }

    public void setAlFoods(List<Food> alFoods) {
        this.alFoods = alFoods;
    }

    public Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public PositionMoving getPositionMoving() {
        return positionMoving;
    }

    public void setPositionMoving(PositionMoving positionMoving) {
        this.positionMoving = positionMoving;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public class CustomRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 6703872492730589499L;

        @Override
        public int getHorizontalAlignment() {
            return SwingConstants.CENTER;
        }

        @Override
        public Font getFont() {
//            super.getFont()
            Font font = new Font("Verdana", Font.BOLD, configuration.getSizeOfCell() / 2);
            return font;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JComponent cellComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (maze[row][column] == 1) {
                cellComponent.setBackground(Color.BLACK);
            } else {
                cellComponent.setBackground(Color.WHITE);
                for (Player player : alPlayers) {
                    if (row == player.getPosition().getX() && column == player.getPosition().getY() && !player.isDied()) {
                        cellComponent.setBackground(player.getColor());
                        table.getModel().setValueAt(player.getPlayerNumber(), row, column);
                    }
                }
                for (Food food : alFoods) {
                    if (row == food.getPosition().getX() && column == food.getPosition().getY() && !food.isUsed()) {
                        cellComponent.setBackground(food.getPosition().getExitColor());
                        table.getModel().setValueAt("X", row, column);
                    }
                }
            }
            cellComponent.setForeground(Color.WHITE);
//            if (hasFocus) {
//                setBackground(Color.cyan);
//            } else if (isSelected) {
//                setBackground(table.getSelectionBackground());
//            }
            return cellComponent;
        }
    }
}
