package com.bobkubista.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CellTest {

    @Test
    void testIsSolved() {
        Cell cell = new Cell(1);
        Assertions.assertTrue(cell.isSolved());
    }

    @Test
    void testIsNotSolved() {
        Cell cell = new Cell(0);
        Assertions.assertFalse(cell.isSolved());
    }

    @Test
    void testGetValue() {
        Cell cell = new Cell(1);
        Assertions.assertEquals(1, cell.getValue());
    }

    @Test
    void testGetValueNoSolutionYet() {
        Cell cell = new Cell(0);
        Assertions.assertEquals(0, cell.getValue());
    }
}
