package org.example.lesson9;

import org.example.lesson9.annotations.MarkingAnnotation;

import java.lang.reflect.*;

public class App {
    static class ClassWithPrivateField {
        private static int field;
        private String anotherField;

        public ClassWithPrivateField(int field) {
            this.field = field;
        }
        public ClassWithPrivateField(String anotherField, int field) {
            this.anotherField = anotherField;
            this.field = field;
        }

        @MarkingAnnotation
        public static void info(int number) {
            System.out.println("field: " + field);
        }
    }


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ClassWithPrivateField obj = new ClassWithPrivateField(10);
        Method[] methods = ClassWithPrivateField.class.getMethods();
        for (Method o : methods) {
            System.out.println(o.getName());
            }


    }
}



