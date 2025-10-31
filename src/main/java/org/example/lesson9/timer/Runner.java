package org.example.lesson9.timer;

import java.lang.reflect.Method;

public class Runner {

    public static void main(String[] args) throws Exception {
        AppService service = new AppService();

        // получаем все методы класса
        for (Method method : AppService.class.getMethods()) {

            // проверяем, есть ли аннотация
            if (method.isAnnotationPresent(LogExecutionTime.class)) {

                long start = System.currentTimeMillis();

                // вызов метода (invoke)
                method.invoke(service);

                long end = System.currentTimeMillis();

                System.out.println("[LOG] " + method.getName() +
                        " executed in " + (end - start) + "ms");
            }
        }
    }
}
