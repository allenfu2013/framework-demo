package me.coding.sample.eventdriven.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class NotifyPublisher implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx= applicationContext;
    }

    public void publishEvent(int status, String msg) {
        if (status == 0) {
            ctx.publishEvent(new NotifyEvent(this, msg));
        } else {
            ctx.publishEvent(new NotifyEvent(this, msg)) ;
        }
    }
}
