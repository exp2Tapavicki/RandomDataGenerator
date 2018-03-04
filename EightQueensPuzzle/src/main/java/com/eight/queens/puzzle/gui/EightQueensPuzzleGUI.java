package com.eight.queens.puzzle.gui;

import com.eight.queens.puzzle.configuration.Configuration;
import com.eight.queens.puzzle.util.SolutionUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class EightQueensPuzzleGUI {

    public static Configuration configuration;
    private JTable table;
    private JPanel panel;
    private JButton randomPlaces;
    private JButton testSolution;
    private JButton findSolution;
    private JPanel buttonPanel;
    private JFrame frame;
    private SolutionUtil solutionUtil;

    public EightQueensPuzzleGUI(Configuration configuration, final SolutionUtil solutionUtil) {
        this.configuration = configuration;
        this.solutionUtil = solutionUtil;
        randomPlaces.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                EightQueensPuzzleGUI.configuration.setQueenPositions(solutionUtil.setRandomPlaces(EightQueensPuzzleGUI.configuration.getSizeOfTable(), EightQueensPuzzleGUI.configuration.getNumberOfQueens()));
                for (int i = 0; i < EightQueensPuzzleGUI.configuration.getSizeOfTable(); i++) {
                    table.getColumnModel().getColumn(i).setCellRenderer(new CustomRenderer(EightQueensPuzzleGUI.configuration.getQueenPositions()));
                    table.getColumnModel().getColumn(i).setMinWidth(2);
                    table.getColumnModel().getColumn(i).setMaxWidth(EightQueensPuzzleGUI.configuration.getSizeOfCell());
                    table.getColumnModel().getColumn(i).setPreferredWidth(EightQueensPuzzleGUI.configuration.getSizeOfCell());
                }
                table.repaint();
            }
        });
        testSolution.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                solutionUtil.testSolution();
            }
        });
        findSolution.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                solutionUtil.findSolution(EightQueensPuzzleGUI.configuration.getQueenPositions());
            }
        });
    }

    public void createApplication() {
        frame = new JFrame("Eight queens Puzzle");
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.black);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createTable(configuration.getSizeOfTable());
        panel = new JPanel();
        panel.add(table);
        panel.add(buttonPanel);
        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private void createTable(int sizeOfTable) {
        table = new JTable();

        DefaultTableModel tableModel = new DefaultTableModel(sizeOfTable, sizeOfTable) {
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

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                int selectedRow = target.getSelectedRow();
                int selectedColumn = target.getSelectedColumn();
                boolean selected = false;
                int[][] queensPostion = EightQueensPuzzleGUI.configuration.getQueenPositions();
                if (queensPostion[selectedRow][selectedColumn] == 0) {
                    queensPostion[selectedRow][selectedColumn] = 1;
                } else {
                    queensPostion[selectedRow][selectedColumn] = 0;
                }
                EightQueensPuzzleGUI.configuration.setQueenPositions(queensPostion);
                table.repaint();
            }
        });


        table.setRowHeight(configuration.getSizeOfCell());
        table.setTableHeader(null);
        table.setEnabled(true);
        for (int i = 0; i < configuration.getSizeOfTable(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new CustomRenderer(configuration.getQueenPositions()));
            table.getColumnModel().getColumn(i).setMinWidth(2);
            table.getColumnModel().getColumn(i).setMaxWidth(configuration.getSizeOfCell());
            table.getColumnModel().getColumn(i).setPreferredWidth(configuration.getSizeOfCell());
        }
    }

    public class CustomRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 6703872492730589499L;
        private ImageIcon queenIcon;
        private int[][] queenPositions;
        private Border border;

        private CustomRenderer(int[][] queenPositions) {
            try {
                this.queenIcon = new ImageIcon(ImageIO.read(new File("images/queen.png")).getScaledInstance(Math.round(configuration.getSizeOfCell() / 1.2f), Math.round(configuration.getSizeOfCell() / 1.2f), Image.SCALE_DEFAULT));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.queenPositions = queenPositions;
            border = BorderFactory.createCompoundBorder();
            border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
            border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK));
            border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
            border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));
        }

        @Override
        public int getHorizontalAlignment() {
            return SwingConstants.CENTER;
        }

        @Override
        public Font getFont() {
//            super.getFont()
            Font font = new Font("Verdana", Font.BOLD, configuration.getSizeOfCell() / 5);
            return font;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JComponent cellComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if ((column % 2 == 1 && row % 2 == 1)
                    || (column % 2 == 0 && row % 2 == 0)) {
                cellComponent.setBackground(new Color(255, 205, 106));
            } else {
                cellComponent.setBackground(new Color(209, 138, 78));
            }
            if (this.queenPositions != null && this.queenPositions[row][column] == 1) {
                setIcon(this.queenIcon);
            } else {
                setIcon(null);
                setText("[" + String.valueOf(row) + "," + String.valueOf(column) + "]");
            }

            if (solutionUtil.checkQueenPosition(this.queenPositions, row, column)) {
                setBorder(border);
            } else {
                setBorder(null);
            }


            cellComponent.setForeground(Color.WHITE);
            return cellComponent;
        }
    }
}
