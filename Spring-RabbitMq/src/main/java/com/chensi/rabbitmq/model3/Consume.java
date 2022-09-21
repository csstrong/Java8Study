package com.chensi.rabbitmq.model3;

import com.chensi.rabbitmq.config.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/*
 * @author  chensi
 * @date  2022/9/20
 */
public class Consume {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("logs_direct", "direct");

        //创建一个临时队列
        String queueName = channel.queueDeclare().getQueue();

        //基于routekey绑定队列和交换机
        channel.queueBind(queueName, "logs_direct", "info");

        //获取消费的消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("我是路由模式消费者2号" + new String(body));
            }
        });
    }
}
