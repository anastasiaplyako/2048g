import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Game {
    private Cell[][] cells;
    private int score;

    public Game() {
        cells = new Cell[4][4];
        startGame();
    }

    public void startGame() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = new Cell(i, j,0);
            }
        }

        score = 0;

        for (int i = 0; i < 2; i++) {
            createRandomCell();
        }
    }

    private void createRandomCell() {
        int j;
        Cell tempCell = null;
        for (int i = 0; i < cells.length; i++) {
            for (j = 0; j < cells[0].length; j++) {
                tempCell = cells[i][j];
                if (tempCell.getValue() == 0) {
                    break;
                }
                if (i == cells.length - 1 && j == cells[0].length - 1) {
                    return;
                }
            }
            if (tempCell.getValue() == 0) {
                break;
            }
        }

        Cell randomCell = cells[(int) (Math.random() * 4)][(int) (Math.random() * 4)];

        while (randomCell.getValue() != 0) {
            randomCell = cells[(int) (Math.random() * 4)][(int) (Math.random() * 4)];
        }

        if ((int) (Math.random() * 2) == 0) {
            randomCell.setValue(2);
        } else {
            randomCell.setValue(4);
        }
    }

    public void move(int key) {
        final Cell[][] oldCells = createCellsCopy();
        switch (key) {
            case KeyEvent.VK_LEFT: {
                leftMove(cells);
                break;
            }
            case KeyEvent.VK_RIGHT: {
                rightMove(cells);
                break;
            }
            case KeyEvent.VK_UP: {
                upDown(cells);
                break;
            }
            case KeyEvent.VK_DOWN: {
                downMove(cells);
                break;
            }
        }

        if (isEnd()) {
            end();
        } else if (!Arrays.deepEquals(cells, oldCells)) {
            createRandomCell();
        }
    }

    private Cell[][] createCellsCopy() {
        final Cell[][] newCopyCells = new Cell[4][4];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                newCopyCells[i][j] = new Cell(cells[i][j]);
            }
        }
        return newCopyCells;
    }

    private void rightMove(Cell[][] cells) {
        final int cellsHeight = cells.length;
        final int cellsWidth = cells[0].length;

        for (int i = 0; i < cellsHeight; i++) {

            for (int j = cellsWidth - 2; j >= 0; j--) {
                final Cell jCell = cells[i][j];
                final int jValue = jCell.getValue();

                if (jValue != 0) {
                    for (int n = j + 1; n <= cellsWidth; n++) {
                        if (n == cellsWidth) {
                            jCell.setValue(0);
                            cells[i][cellsWidth - 1].setValue(jValue);
                            break;
                        }

                        final Cell nCell = cells[i][n];
                        final int nValue = nCell.getValue();
                        if (nValue != 0) {
                            if (jValue == nValue && !nCell.isMixed()) {
                                jCell.setValue(0);
                                nCell.setValue(2 * jValue);
                                score += 2 * jValue;
                                nCell.setMix(true);
                            } else {
                                nCell.setMix(false);
                                jCell.setValue(0);
                                cells[i][n - 1].setValue(jValue);
                            }
                            break;
                        }
                    }
                }
            }
        }

        unmixedAllCells(cells);
    }

    private void downMove(Cell[][] cells) {
        final int cellsHeight = cells.length;
        final int cellsWidth = cells[0].length;

        for (int i = 0; i < cellsWidth; i++) {

            for (int j = cellsHeight - 2; j >= 0; j--) {
                final Cell jCell = cells[j][i];
                final int jValue = jCell.getValue();

                if (jValue != 0) {
                    for (int n = j + 1; n <= cellsHeight; n++) {
                        if (n == cellsHeight) {
                            jCell.setValue(0);
                            cells[cellsHeight - 1][i].setValue(jValue);
                            break;
                        }

                        final Cell nCell = cells[n][i];
                        final int nValue = nCell.getValue();
                        if (nValue != 0) {
                            if (jValue == nValue && !nCell.isMixed()) {
                                jCell.setValue(0);
                                nCell.setValue(2 * jValue);
                                score += 2 * jValue;
                                nCell.setMix(true);
                            } else {
                                nCell.setMix(false);
                                jCell.setValue(0);
                                cells[n - 1][i].setValue(jValue);
                            }
                            break;
                        }
                    }
                }
            }
        }

        unmixedAllCells(cells);
    }

    private void leftMove(Cell[][] cells) {
        final int cellsHeight = cells.length;
        final int cellsWidth = cells[0].length;

        for (int i = 0; i < cellsHeight; i++) {

            for (int j = 1; j < cellsWidth; j++) {
                final Cell jCell = cells[i][j];
                final int jValue = jCell.getValue();

                if (jValue != 0) {
                    for (int n = j - 1; n >= -1; n--) {
                        if (n == -1) {
                            jCell.setValue(0);
                            cells[i][0].setValue(jValue);
                            break;
                        }

                        final Cell nCell = cells[i][n];
                        final int nValue = nCell.getValue();
                        if (nValue != 0) {
                            if (jValue == nValue && !nCell.isMixed()) {
                                jCell.setValue(0);
                                nCell.setValue(2 * jValue);
                                score += 2 * jValue;
                                nCell.setMix(true);
                            } else {
                                nCell.setMix(false);
                                jCell.setValue(0);
                                cells[i][n + 1].setValue(jValue);
                            }
                            break;
                        }
                    }
                }
            }
        }

        unmixedAllCells(cells);
    }

    private void upDown(Cell[][] cells) {
        final int cellsHeight = cells.length;
        final int cellsWidth = cells[0].length;

        for (int i = 0; i < cellsWidth; i++) {

            for (int j = 1; j < cellsHeight; j++) {
                final Cell jCell = cells[j][i];
                final int jValue = jCell.getValue();

                if (jValue != 0) {
                    for (int n = j - 1; n >= -1; n--) {
                        if (n == -1) {
                            jCell.setValue(0);
                            cells[0][i].setValue(jValue);
                            break;
                        }

                        final Cell nCell = cells[n][i];
                        final int nValue = nCell.getValue();
                        if (nValue != 0) {
                            if (jValue == nValue && !nCell.isMixed()) {
                                jCell.setValue(0);
                                nCell.setValue(2 * jValue);
                                score += 2 * jValue;
                                nCell.setMix(true);
                            } else {
                                nCell.setMix(false);
                                jCell.setValue(0);
                                cells[n + 1][i].setValue(jValue);
                            }
                            break;
                        }
                    }
                }
            }
        }

        unmixedAllCells(cells);
    }

    private void unmixedAllCells(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j].setMix(false);
            }
        }
    }

    public boolean isEnd() {
        final Cell[][] newCells = createCellsCopy();
        rightMove(newCells);
        downMove(newCells);
        leftMove(newCells);
        upDown(newCells);
        if (Arrays.deepEquals(cells, newCells)) {
            return true;
        }
        return false;
    }

    private void end() {
        final String[] choice = {"«Заново", "выход"};
        final int result = JOptionPane.showOptionDialog(null,
                "ты проиграли. ваш счет: " + score,
                "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, choice, "«Заново");
        if (result == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            startGame();
        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}

