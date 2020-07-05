package me.coding.statemachine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

import javax.annotation.Resource;

@SpringBootApplication
public class StateMachineApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(StateMachineApplication.class, args);
    }

    @Resource
    StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
        stateMachine.sendEvent(Events.ONLINE);
        stateMachine.sendEvent(Events.PUBLISH);
        stateMachine.sendEvent(Events.ROLLBACK);
    }
}
