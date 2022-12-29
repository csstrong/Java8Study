package com.chensi.rabbitmq.model1;

import com.chensi.rabbitmq.config.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.impl.AMQBasicProperties;

import java.io.IOException;

/*
 * @author  chensi
 * @date  2022/9/20
 */
public class Consume {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMqUtil.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();
        //通道绑定队列
        channel.queueDeclare("cs_queue", true, false, false, null);

        /**
         * 消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true代表的自动应答 false代表手动应答
         * 3.消费者未成功消费的回调
         * 4.消费者取消消费的回调
         */
        channel.basicConsume("cs_queue", true, new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQBasicProperties properties, byte[] body) {
                System.out.println("未成功消费，消费者1号 = " + new String(body));
            }
        });
    }
}
