package com.bobkubista.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardTest {

	private int[][] board = { { 8, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
			{ 0, 7, 0, 0, 9, 0, 2, 0, 0 }, { 0, 5, 0, 0, 0, 7, 0, 0, 0 }, { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 3, 0 }, { 0, 0, 1, 0, 0, 0, 0, 6, 8 }, { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
			{ 0, 9, 0, 0, 0, 0, 4, 0, 0 } };
	int[][] singleValueBoard = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 3, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	int[][] solvedBoard = { { 8, 1, 2, 7, 5, 3, 6, 4, 9 }, { 9, 4, 3, 6, 8, 2, 1, 7, 5 }, { 6, 7, 5, 4, 9, 1, 2, 8, 3 },
			{ 1, 5, 4, 2, 3, 7, 8, 9, 6 }, { 3, 6, 9, 8, 4, 5, 7, 2, 1 }, { 2, 8, 7, 1, 6, 9, 5, 3, 4 },
			{ 5, 2, 1, 9, 7, 4, 3, 6, 8 }, { 4, 3, 8, 5, 2, 6, 9, 1, 7 }, { 7, 9, 6, 3, 1, 8, 4, 5, 2 } };

	@Test
	void testInRow() {
		// In Row
		Assertions.assertTrue(Board.initBoard(singleValueBoard).isValueAlreadyUsed(8, 2, 3));
		Assertions.assertFalse(Board.initBoard(singleValueBoard).isValueAlreadyUsed(2, 8, 3));
	}

	@Test
	void testInColumn() {
		// In Column
		Assertions.assertTrue(Board.initBoard(singleValueBoard).isValueAlreadyUsed(1, 2, 3));
		Assertions.assertFalse(Board.initBoard(singleValueBoard).isValueAlreadyUsed(8, 4, 3));
	}

	@Test
	void testInBlock() {
		// In Column
		Assertions.assertTrue(Board.initBoard(singleValueBoard).isValueAlreadyUsed(3, 3, 3));
		Assertions.assertFalse(Board.initBoard(singleValueBoard).isValueAlreadyUsed(8, 8, 3));
	}

	@Test
	void testIsValueAlreadyUsed() {
		// In Row
		Assertions.assertTrue(Board.initBoard(board).isValueAlreadyUsed(0, 0, 8));
		Assertions.assertTrue(Board.initBoard(board).isValueAlreadyUsed(3, 1, 7));
		Assertions.assertFalse(Board.initBoard(board).isValueAlreadyUsed(0, 0, 9));
		Assertions.assertFalse(Board.initBoard(board).isValueAlreadyUsed(3, 1, 6));
		Assertions.assertTrue(Board.initBoard(board).isValueAlreadyUsed(2, 0, 2));
		Assertions.assertFalse(Board.initBoard(board).isValueAlreadyUsed(0, 0, 9));

		// In Column
		Assertions.assertTrue(Board.initBoard(board).isValueAlreadyUsed(0, 0, 8));
		Assertions.assertTrue(Board.initBoard(board).isValueAlreadyUsed(2, 0, 2));
		Assertions.assertTrue(Board.initBoard(board).isValueAlreadyUsed(6, 2, 1));
		Assertions.assertFalse(Board.initBoard(board).isValueAlreadyUsed(0, 0, 9));

		// In Block
		Assertions.assertTrue(Board.initBoard(board).isValueAlreadyUsed(1, 1, 8));
		Assertions.assertTrue(Board.initBoard(board).isValueAlreadyUsed(5, 5, 4));
		Assertions.assertFalse(Board.initBoard(board).isValueAlreadyUsed(1, 2, 9));
		Assertions.assertFalse(Board.initBoard(board).isValueAlreadyUsed(5, 5, 2));
	}

	@Test
	void testIsSolved() {
		Assertions.assertTrue(Board.initBoard(solvedBoard).isSolved());
	}

	@Test
	void testToString() {
		Assertions
				.assertEquals(
						"Board [cells=\n" + "0,0,0,0,0,0,0,0,0\n" + "0,0,0,0,0,0,0,0,0\n" + "0,0,0,0,0,0,0,0,0\n"
								+ "0,0,3,0,0,0,0,0,0\n" + "0,0,0,0,0,0,0,0,0\n" + "0,0,0,0,0,0,0,0,0\n"
								+ "0,0,0,0,0,0,0,0,0\n" + "0,0,0,0,0,0,0,0,0\n" + "0,0,0,0,0,0,0,0,0]",
						Board.initBoard(singleValueBoard).toString());
	}
}
