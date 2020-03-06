package org.kimuzzu.toby.testwebservice03;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootApplication
@RestController
public class Testwebservice03Application {
    @GetMapping("/")
    Mono<String> hello() {
        log.info("pos1");
        String msg = generateHello();
        Mono<String> m =  Mono.fromSupplier( () -> generateHello()).doOnNext(c->log.info(c)).log();
        /*
        Mono<String> m =  Mono.just(msg).doOnNext(c->log.info(c)).log();

        String msg2 =  m.block();

        log.info("pos2" + msg2);
        */
        return m;
    }

    private String generateHello() {
        log.info("method generateHello()");
        return "Hello Mono";
    }

    public static void main(String[] args) {
        SpringApplication.run(Testwebservice03Application.class, args);
    }

}
