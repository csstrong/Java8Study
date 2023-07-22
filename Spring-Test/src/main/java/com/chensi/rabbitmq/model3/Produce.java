package com.chensi.rabbitmq.model3;

/*
 * @author  chensi
 * @date  2022/9/20
 */

import com.chensi.rabbitmq.config.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 在Fanout模式中，一条消息，会被所有的订阅的队列所消费，但是，在某些场景下，希望不同的消息被不同的队列消费，
 * 这时就要用到Direct类型的Exchange.
 * 在Direct模型下，队列与交换机的绑定，不能是任意绑定了，而是要指定一个RoutingKey（路由Key）.消息的发送在
 * Exchange发送消息时，也必须指定消息的RoutingKey
 * Exchange不再把消息交给每一个绑定的队列，而是根据消息的RoutingKey进行判断。只有队列的RoutingKey与消息的RoutingKey
 * 完全一致，才会接受到消息
 */
public class Produce {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        //将通道声明指定交换机 参数1：交换机名称 参数2: direct 路由模式
        channel.exchangeDeclare("logs_direct", "direct");

        //发送消息
        String routingKey = "info";

        channel.basicPublish("logs_direct", routingKey, null,
            "现在是路由模式".getBytes());

        //关闭消息
        RabbitMqUtil.closeConnectionAndChannel(channel, connection);
    }
}
