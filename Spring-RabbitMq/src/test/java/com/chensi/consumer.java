package com.chensi;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/*
 * @author  chensi
 * @date  2022/9/20
 */
//不设置默认为持久化，非独占，非自动删除队列
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello", durable = "true", autoDelete = "false"))
public class consumer {

    @RabbitHandler
    public void consumeerMessage(String message) {
        System.out.println("我是简单模型消费者消费的消息为:" + message);
    }

}
