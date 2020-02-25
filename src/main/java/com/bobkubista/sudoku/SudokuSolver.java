package com.bobkubista.sudoku;

public class SudokuSolver {

    public int[][] solve(int[][] board) {
        Board gameBoard = initBoard(board);
        runThroughBoard(gameBoard);
        System.out.println("board: \n" + gameBoard);
        return board;
    }

    private boolean runThroughBoard(Board gameBoard) {
        Cell[][] cells = gameBoard.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                if (!cells[row][column].isSolved()) {
                    for (int solution = 1; solution < 9; solution++) {
                        if (!gameBoard.isValueAlreadyUsed(row, column, solution)) {
                            cells[row][column].setValue(solution);
                            System.out.println("board: \n" + gameBoard);
                            if (runThroughBoard(gameBoard)) {
                                return true;
                            }
                        } else {
                            cells[row][column].setValue(0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private Board initBoard(int[][] board) {
        Cell[][] cells = new Cell[9][9];
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[0].length; column++) {
                if (board[row][column] != 0) {
                    cells[row][column] = new Cell(board[row][column]);
                } else {
                    cells[row][column] = new Cell(0);
                }
            }
        }
        return new Board(cells);
    }
}
