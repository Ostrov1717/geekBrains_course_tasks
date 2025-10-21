package org.example.lesson2;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


public interface Runnable {
    boolean run(int distance);

//    default boolean run(int distance){
//        System.out.println("Running " + distance + " meters.");
//        return true;
//    }
}
