package org.example.lesson4;

import java.util.Arrays;
import java.util.List;

public class App<T> {

    public T[] changeElements(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        return array;
    };

    public List<T> toList(T[] array) {
        return Arrays.asList(array);
    }

    public static void main(String[] args) {
        App<String> appString = new App<>();
        String[] stringArray = {"A", "B", "C", "D"};
        String[] modifiedStringArray = appString.changeElements(stringArray, 1, 3);
        System.out.println(Arrays.toString(stringArray));
        System.out.println(appString.toList(stringArray).getClass().getSimpleName());

        App<Integer> appInteger = new App<>();
        Integer[] integerArray = {1, 2, 3, 4, 5};
        Integer[] modifiedIntegerArray = appInteger.changeElements(integerArray, 0, 4);
        System.out.println(Arrays.toString(integerArray));
        System.out.println(appInteger.toList(integerArray).getClass().getSimpleName());
    }
}
