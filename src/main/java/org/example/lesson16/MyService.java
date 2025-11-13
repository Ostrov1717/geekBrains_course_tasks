package org.example.lesson16;

import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.example.lesson16.time_measure_aspect.MeasureTime;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MyService {

    @MeasureTime
    public String doSomething(String input) throws InterruptedException {

        log.info("--- [MyService] НАЧАЛО БИЗНЕС-ЛОГИКИ ---");

        Thread.sleep(100);

        if ("error".equals(input)) {
            log.warn("--- [MyService] ГЕНЕРАЦИЯ ОШИБКИ ---");
            throw new IllegalArgumentException("Тестовая ошибка по запросу 'error'");
        }

        String result = "Результат для: " + input;
        log.info("--- [MyService] ЗАВЕРШЕНИЕ БИЗНЕС-ЛОГИКИ ---");
        return result;
    }

    @RolesAllowed("ROLE_ADMIN")
    public String doOtherThing(String input) throws InterruptedException {

        log.info("--- [MyService] НАЧАЛО ДРУГОЙ БИЗНЕС-ЛОГИКИ ---");

        Thread.sleep(200);

        String result = "Другой результат для: " + input;
        log.info("--- [MyService] ЗАВЕРШЕНИЕ ДРУГОЙ БИЗНЕС-ЛОГИКИ ---");
        return result;
    }
}
