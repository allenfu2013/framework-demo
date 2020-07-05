package me.coding.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import me.coding.rabbitmq.dto.Msg;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RabbitListener(queues = "queue.test", exclusive = true)
public class MsgListener {

    @RabbitHandler
    public void test(Msg msg, Channel channel, Message message) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("delivery tag: {}, receive msg: {}", deliveryTag,  msg);
//        throw new RuntimeException("test error");
        channel.basicAck(deliveryTag, false);
//        channel.basicNack(deliveryTag, false, false);
//        channel.basicReject(deliveryTag, false);
//        channel.basicRecover(false);
    }
}
