package com.chensi.rabbitmq.model2;

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
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        //绑定交换机
        channel.exchangeDeclare("wangExChange", "fanout");
        //获取临时队列的名字
        String queueName = channel.queueDeclare().getQueue();
        //绑定交换机和队列 参数1：临时队列的名称 参数2：交换机名称 参数3：路由，这里暂时用不到
        channel.queueBind(queueName, "wangExChange", "");

        //消费消息 参数1：对垒名称 参数2：是否自动确认
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQBasicProperties properties,
                                       byte[] body) throws IOException {
                System.out.println("我是广播模型消费者1号" + new String(body));
            }
        });
    }
}
