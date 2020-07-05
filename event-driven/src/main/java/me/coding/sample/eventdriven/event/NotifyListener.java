package me.coding.sample.eventdriven.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotifyListener {

    @EventListener
    public void sayHello(NotifyEvent notifyEvent){
        log.info("收到事件:{}", notifyEvent.getMsg());
    }
}
