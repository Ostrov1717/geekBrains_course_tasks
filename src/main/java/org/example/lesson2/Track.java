package org.example.lesson2;

public class Track extends Obstacle{
    private final int length;

    public Track(int length) {
        this.length = length;
    }
    public int getHeight() {
        return length;
    }
}
