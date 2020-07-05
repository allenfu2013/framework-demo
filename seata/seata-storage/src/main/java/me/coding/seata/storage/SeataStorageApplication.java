package me.coding.seata.storage;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class SeataStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataStorageApplication.class, args);
    }
}
