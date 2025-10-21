package org.example.lesson2;

public class Person implements Movable{
    public final static int MAX_LENGTH=100;
    public final static int MAX_HEIGHT=10;
    @Override
    public boolean run(int distance) {
        if (distance>MAX_LENGTH){
            System.out.println("Person could not run this distance");
            return false;
        } else {
            System.out.println("Person is running");
            return true;
        }


    }
    @Override
    public boolean jump(int height) {
        if (height>MAX_HEIGHT){
            System.out.println("Person could not jump this height");
            return false;
        } else {
            System.out.println("Person is jumping");
            return true;
        }
    }
}
