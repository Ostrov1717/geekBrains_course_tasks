package org.example.lesson2;

public class Cat implements Movable{
    public static final int MAX_RUN_DISTANCE = 20;
    public static final int MAX_JUMP_HEIGHT = 5;

    @Override
    public boolean run(int distance) {
        if (distance <= MAX_RUN_DISTANCE) {
            System.out.println("Cat ran " + distance + " meters.");
            return true;
        } else {
            System.out.println("Cat couldn't run " + distance + " meters. Maximum is " + MAX_RUN_DISTANCE + " meters.");
            return false;
        }
    }
    @Override
    public boolean jump(int height) {
        if (height <= MAX_JUMP_HEIGHT) {
            System.out.println("Cat jumped " + height + " meters high.");
            return true;
        } else {
            System.out.println("Cat couldn't jump " + height + " meters high. Maximum is " + MAX_JUMP_HEIGHT + " meters.");
            return false;
        }
    }

}
