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
        //1.创建连接工厂 2.设置连接属性 3.从连接工厂获取连接
        Connection connection = RabbitMqUtil.getConnection();
        //4.获取通道对象
        Channel channel = connection.createChannel();
        //5.通道绑定队列
        /*
         *  如果队列不存在，则会创建
         *  Rabbitmq不允许创建两个相同的队列名称，否则会报错。
         *
         *  @params1： queue 队列的名称
         *  @params2： durable 队列是否持久化
         *  @params3： exclusive 是否排他，即是否私有的，如果为true,会对当前队列加锁，其他的通道不能访问，并且连接自动关闭
         *  @params4： autoDelete 是否自动删除，当最后一个消费者断开连接之后是否自动删除消息。
         *  @params5： arguments 可以设置队列附加参数，设置队列的有效期，消息的最大长度，队列的消息生命周期等等。
         * */
        channel.queueDeclare("cs_queue", true, false, false, null);

        //6.7.发布消息，这里模拟一次性发布多条消息
        // @params1: 交换机exchange
        // @params2: 队列名称/routing
        // @params3: 属性配置
        // @params4: 发送消息的内容
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "cs_queue",
                MessageProperties.PERSISTENT_TEXT_PLAIN, (i + "我是工作模型队列").getBytes());
        }

        RabbitMqUtil.closeConnectionAndChannel(channel, connection);
    }
}
