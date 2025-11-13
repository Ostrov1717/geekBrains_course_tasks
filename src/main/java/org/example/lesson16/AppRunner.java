package org.example.lesson16;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppRunner implements CommandLineRunner {

    private final MyService myService;


    @Override
    public void run(String... args) throws Exception {
        log.info("====================================");
        log.info("=== ЗАПУСК 1: УСПЕШНЫЙ СЦЕНАРИЙ ===");
        log.info("====================================");
        myService.doSomething("успешный_ввод");
        log.info("\n");

        log.info("====================================");
        log.info("=== ЗАПУСК 2: СЦЕНАРИЙ С ОШИБКОЙ ===");
        log.info("====================================");
        try {
            myService.doSomething("error");

        } catch (IllegalArgumentException e) {
            log.warn("--- [AppRunner] Ошибка успешно поймана в 'run' методе ---");
        }
        myService.doOtherThing("user_input");
    }
}
