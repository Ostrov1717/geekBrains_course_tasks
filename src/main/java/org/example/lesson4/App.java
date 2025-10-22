package org.example.lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static <T> T[] changeElements(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        return array;
    }

    public static <T> List<T> toList(T[] array) {
        return new ArrayList<>(List.of(array));
    }

    public static void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        String[] stringArray = {"A", "B", "C", "D"};
        String[] modifiedStringArray = changeElements(stringArray, 1, 3);
        System.out.println(Arrays.toString(stringArray));
        System.out.println(toList(stringArray).getClass().getSimpleName());
        System.out.println(Arrays.toString(modifiedStringArray));

        swap(stringArray, 0, 2);
        System.out.println(Arrays.toString(stringArray));


        Integer[] integerArray = {1, 2, 3, 4, 5};
        Integer[] modifiedIntegerArray = changeElements(integerArray, 0, 4);
        System.out.println(Arrays.toString(integerArray));
        System.out.println(toList(integerArray).getClass().getSimpleName());
        System.out.println(Arrays.toString(modifiedIntegerArray));
    }
}
