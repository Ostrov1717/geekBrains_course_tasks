package org.example.lesson3;

public class App {
    static int sumOfArrayElements(String[][] array) {
        int sum = 0;
        if(array.length != 4){
            throw new MyArraySizeException(array.length);
        }
        for (int i = 0; i < 4; i++) {
            if(array[i].length !=4){
                throw new MyArraySizeException(i, array[i].length);
            }
            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.valueOf(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, array[i][j]);
                }
            }
        }
        return sum;
    }

    public static void main (String[]args){
        String[][] validArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
            };
        String[][] invalidSizeArray = {
                {"1", "2", "3", "4", "5"},
                {"5", "6", "7", "8", "9"},
                {"4", "5", "6", "7", "8"},
                {"7", "8", "9", "10", "11"}
            };
        String[][] invalidDataArray = {
                {"1", "2", "3", "4"},
                {"5",  "7", "8","10"},
                {"9", "six", "11", "12"},
                {"13", "14", "15", "16"}
            };
        try {
            System.out.println("Sum of validArray: " + sumOfArrayElements(validArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Sum of invalidSizeArray: " + sumOfArrayElements(invalidSizeArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Sum of invalidDataArray: " + sumOfArrayElements(invalidDataArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}
