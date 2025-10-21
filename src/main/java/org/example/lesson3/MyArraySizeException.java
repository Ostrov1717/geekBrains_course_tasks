package org.example.lesson3;

public class MyArraySizeException extends MyException {
    int row;
    int column;

    public MyArraySizeException(int row) {
        super("Array size is not 4x4. Invalid rows array size, row: " + row);
        this.row = row;
    }

    public MyArraySizeException(int row, int column) {
        super("Array size is not 4x4. Invalid column array size,row: " + row + " column: " + column);
        this.row = row;
        this.column = column;
    }
}
