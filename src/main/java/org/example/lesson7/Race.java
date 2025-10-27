package org.example.lesson7;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class Race {

    private final ArrayList<Stage> stages;

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
