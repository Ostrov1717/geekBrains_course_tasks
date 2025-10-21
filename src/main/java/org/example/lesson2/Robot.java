package org.example.lesson2;

public class Robot implements Movable {
   public static final int MAX_RUN_DISTANCE = 1000;
   public static final int MAX_JUMP_HEIGHT = 2;

   @Override
    public boolean run(int distance) {
         if (distance <= MAX_RUN_DISTANCE) {
              System.out.println("Robot successfully ran " + distance + " meters.");
                return true;
         } else {
              System.out.println("Robot failed to run " + distance + " meters.");
                return false;
         }
    }

    @Override
    public boolean jump(int height) {
         if (height <= MAX_JUMP_HEIGHT) {
              System.out.println("Robot successfully jumped " + height + " meters.");
                return true;
         } else {
              System.out.println("Robot failed to jump " + height + " meters.");
                return false;
         }
    }
}
