package com.chensi.rabbitmq.model1;

/*
 * @author  chensi
 * @date  2022/9/20
 */

import com.chensi.rabbitmq.config.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

/**
 * 工作模型Work
 * Work queues工作队列也称为任务队列任务模型。当消息处理比较耗时时，可能生产消息的速度远远超过消息的消费速度，
 * 长期以往会导致消息堆积太多，无法及时处理。这时可使用Work queues
 */
public class Produce {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMqUtil.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();
        //通道绑定队列
        channel.queueDeclare("wangRabbitMqWork", true, false, false, null);

        //发布消息，这里模拟一次性发布多条消息
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "wangRabbitMqWork",
                MessageProperties.PERSISTENT_TEXT_PLAIN, (i + "我是工作模型队列").getBytes());
        }

        RabbitMqUtil.closeConnectionAndChannel(channel, connection);
    }
}
