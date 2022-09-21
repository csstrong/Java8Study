package com.chensi.rabbitmq.model2;

/*
 * @author  chensi
 * @date  2022/9/20
 */

import com.chensi.rabbitmq.config.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * Fanout 广播模型
 * 1.可以有多个消费者
 * 2.每个消费者都有自己的队列
 * 3.每个队列都要绑定Exchange交换机
 * 4.生产者发送消息，只能发送到交换机，交换机来决定要发到哪个队列，生产者无法决定
 * 5.交换机把消息发给绑定的所有队列
 * 6.队列的消费者都能拿到消费。实现一条消息被被多个消费者消费
 */
public class Produce {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        //将通道绑定交换机
        channel.exchangeDeclare("wangExChange", "fanout");

        /*真正发布消息
        参数1：交换机名称，因为简单直连模式没用交换机，所以未空
        参数2：队列名称
        参数3：传递消息的额外设置，设置为null,当rabbit重启时，未消费的消息会丢失,
            当设置为MessageProperties.PERSISTENT_TEXT_PLAIN，消息会持久化，即使rabbitmq重启也不会影响
        参数4：消息体
        注意：消息发布时通道绑定的队列属性必须和消息消费时通道绑定的队列属性一致，不可能说发消息时队列是持久化的，
            而消费时不是持久化的。
        */
        channel.basicPublish("wangExChange", "", null, "这里是广播模式".getBytes());

        //释放资源
        RabbitMqUtil.closeConnectionAndChannel(channel, connection);
    }
}
