package me.coding.rabbitmq.controller;

import lombok.extern.slf4j.Slf4j;
import me.coding.rabbitmq.dto.Msg;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Controller
@Slf4j
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    @ResponseBody
    public void send() {
        String seqId = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(seqId);

        Msg msg = new Msg();
        msg.setId(seqId);
        msg.setContent("Hello World");

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息发送到exchange成功,id: {}", correlationData.getId());
            } else {
                log.info("消息发送到exchange失败,原因: {}", cause);
            }
        });

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String cid = message.getMessageProperties().getCorrelationId();
            log.info("消息发送失败：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", message.getMessageProperties().getCorrelationIdString(), replyCode, replyText, exchange, routingKey);
        });

        rabbitTemplate.convertAndSend("exchange.test", "routingKey.test", msg, correlationId);
        log.info("发送消息:{}", msg);
    }

    @PostMapping("/test")
    @ResponseBody
    public String test(@RequestBody String content) {
        System.out.println(content);
        return "<Response retCode=\"000\" scoreID=\"1920011\" score=\"848\" reason=\"1001,1002,1003,1004\" />";
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String appKey = "52e3167d-2b66-4c5a-a868-d002b14a9a061";
        String appSecret = "00Us96KUOp3an37oGfUieVMpfdVzJdceBHF0Y62aomg6iPrNWG3vfhfdZmTxd3ga";
        String authorization = "Basic " + Base64Utils.encodeToString((appKey + ":" + appSecret).getBytes());
        System.out.println(authorization);
    }
}
