package com.bobkubista.sudoku;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    private final Cell[][] cells;

    public Board(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean isValueAlreadyUsed(int row, int column, Integer solution) {
        return isInColumn(column, solution) || isInRow(row, solution)
                || isInBlock(row, column, solution);
    }

    private boolean isInRow(int row, int value) {
        boolean result = false;
        for (int column = 0; column < cells.length && !result; column++) {
            result = isValueInCellTheSame(value, row, column);
        }
        return result;
    }

    private boolean isInColumn(int column, int value) {
        boolean result = false;
        for (int row = 0; row < cells.length && !result; row++) {
            result = isValueInCellTheSame(value, row, column);
        }
        return result;
    }

    private boolean isInBlock(int row, int column, int value) {
        int blockRow = row / 3 * 3;
        int blockColumn = column / 3 * 3;
        for (int toCheckRow = blockRow; toCheckRow < blockRow + 3; toCheckRow++) {
            for (int toCheckColumn = blockColumn; toCheckColumn < blockColumn + 3; toCheckColumn++) {
                if (isValueInCellTheSame(value, toCheckRow, toCheckColumn)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValueInCellTheSame(int value, int toCheckRow, int toCheckColumn) {
        return cells[toCheckRow][toCheckColumn].getValue() == value;
    }

    public boolean isSolved() {
        return Stream.of(cells)
                .allMatch(row -> Stream.of(row)
                        .allMatch(Cell::isSolved));
    }

    @Override
    public String toString() {
        return "Board [cells=" + cellsArrayToString() + "]";
    }

    private String cellsArrayToString() {
        return Stream.of(cells)
                .map(row -> Stream.of(row)
                        .map(cell -> getStringValueForCell(cell))
                        .collect(Collectors.joining(",")))
                .collect(Collectors.joining("\n"));
    }

    private String getStringValueForCell(Cell cell) {
        return Integer.toString(cell.getValue());
    }
}
