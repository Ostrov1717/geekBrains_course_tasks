package org.example.lesson3;

public class MyArrayDataException extends MyException{
    int i;
    int j;
    String invalidValue;

    public MyArrayDataException(int i, int j, String invalidValue) {
        super("Invalid data in array element [" + i + "," + j + "]: " + invalidValue);
        this.i = i;
        this.j = j;
        this.invalidValue = invalidValue;
    }
}
