package com.bobkubista.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardTest {

    private int[][] board = {
            { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
            { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
            { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
            { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
            { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
            { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
    };
    int[][] singleValueBoard = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 3, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };
    int[][] solvedBoard = {
            { 8, 1, 2, 7, 5, 3, 6, 4, 9 },
            { 9, 4, 3, 6, 8, 2, 1, 7, 5 },
            { 6, 7, 5, 4, 9, 1, 2, 8, 3 },
            { 1, 5, 4, 2, 3, 7, 8, 9, 6 },
            { 3, 6, 9, 8, 4, 5, 7, 2, 1 },
            { 2, 8, 7, 1, 6, 9, 5, 3, 4 },
            { 5, 2, 1, 9, 7, 4, 3, 6, 8 },
            { 4, 3, 8, 5, 2, 6, 9, 1, 7 },
            { 7, 9, 6, 3, 1, 8, 4, 5, 2 } };

    @Test
    void testInRow() {
        Cell[][] cells = new Cell[9][9];
        initBoard(cells, singleValueBoard);

        // In Row
        Assertions.assertTrue(new Board(cells).isValueAlreadyUsed(8, 2, 3));
        Assertions.assertFalse(new Board(cells).isValueAlreadyUsed(2, 8, 3));
    }

    @Test
    void testInColumn() {
        Cell[][] cells = new Cell[9][9];
        initBoard(cells, singleValueBoard);

        // In Column
        Assertions.assertTrue(new Board(cells).isValueAlreadyUsed(1, 2, 3));
        Assertions.assertFalse(new Board(cells).isValueAlreadyUsed(8, 4, 3));
    }

    @Test
    void testInBlock() {
        Cell[][] cells = new Cell[9][9];
        initBoard(cells, singleValueBoard);

        // In Column
        Assertions.assertTrue(new Board(cells).isValueAlreadyUsed(3, 3, 3));
        Assertions.assertFalse(new Board(cells).isValueAlreadyUsed(8, 8, 3));
    }

    @Test
    void testIsValueAlreadyUsed() {
        Cell[][] cells = new Cell[9][9];
        initBoard(cells, board);

        // In Row
        Assertions.assertTrue(new Board(cells).isValueAlreadyUsed(0, 0, 8));
        Assertions.assertTrue(new Board(cells).isValueAlreadyUsed(3, 1, 7));
        Assertions.assertFalse(new Board(cells).isValueAlreadyUsed(0, 0, 9));
        Assertions.assertFalse(new Board(cells).isValueAlreadyUsed(3, 1, 6));
        Assertions.assertTrue(new Board(cells).isValueAlreadyUsed(2, 0, 2));
        Assertions.assertFalse(new Board(cells).isValueAlreadyUsed(0, 0, 9));

        // In Column
        Assertions.assertTrue(new Board(cells).isValueAlreadyUsed(0, 0, 8));
        Assertions.assertTrue(new Board(cells).isValueAlreadyUsed(2, 0, 2));
        Assertions.assertTrue(new Board(cells).isValueAlreadyUsed(6, 2, 1));
        Assertions.assertFalse(new Board(cells).isValueAlreadyUsed(0, 0, 9));

        // In Block
        Assertions.assertTrue(new Board(cells).isValueAlreadyUsed(1, 1, 8));
        Assertions.assertTrue(new Board(cells).isValueAlreadyUsed(5, 5, 4));
        Assertions.assertFalse(new Board(cells).isValueAlreadyUsed(1, 2, 9));
        Assertions.assertFalse(new Board(cells).isValueAlreadyUsed(5, 5, 2));
    }

    @Test
    void testIsSolved() {
        Cell[][] cells = new Cell[9][9];
        initBoard(cells, solvedBoard);
        Assertions.assertTrue(new Board(cells).isSolved());
    }

    @Test
    void testToString() {
        Cell[][] cells = new Cell[2][9];
        initBoard(cells, board);
        Assertions.assertEquals("Board [cells=8,0,0,0,0,0,0,0,0\n" +
                "0,0,3,6,0,0,0,0,0]", new Board(cells).toString());
    }

    private void initBoard(Cell[][] cells, int[][] board) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (board[i][j] != 0) {
                    cells[i][j] = new Cell(board[i][j]);
                } else {
                    cells[i][j] = new Cell(0);
                }
            }
        }
    }
}
