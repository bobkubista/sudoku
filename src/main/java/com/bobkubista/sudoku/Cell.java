package com.bobkubista.sudoku;

public class Cell {

    private int solution;

    public Cell(int i) {
        this.solution = i;
    }

    public boolean isSolved() {
        return solution != 0;
    }

    public int getValue() {
        return solution;
    }

    public void setValue(int solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "Cell [solutions=" + solution + "]";
    }

}
