package com.bobkubista.sudoku;

public class SudokuSolver {

    public Board solve(int[][] board) {
        Board gameBoard = Board.initBoard(board);
        gameBoard.solve();
        return gameBoard;
    }

}
