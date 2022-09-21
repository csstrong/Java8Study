package com.chensi.rabbitmq.model1;

import com.chensi.rabbitmq.config.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/*
 * @author  chensi
 * @date  2022/9/20
 */
public class Consume2 {
    public static void main(String[] args) throws IOException {
        //这里为了快捷我们就使用之前创建的工具类

        //获取连接对象
        Connection connection = RabbitMqUtil.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();
        //通道绑定队列
        channel.queueDeclare("wangRabbitMqWork", true, false, false, null);

        //每次只取一条，消费完再取，确保消息不丢失
        channel.basicQos(1);

        //消费消息 参数1:队列名称  参数2:是否自动确认  参数 接口回调执行
        //channel.basicConsume("wangRabbitMqWork", true, new DefaultConsumer(channel) {
        //    @Override
        //    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        //        System.out.println("消费者2号 = " + new String(body));
        //    }
        //});

        //消费消息 参数2 关闭消息自动确认，因为当它为true时，不管下面的代码是否正常执行，
        //它就向消息队列自动确认,这时队列会删除消息，这就会产生消息丢失问题
        channel.basicConsume("wangRabbitMqWork", false, new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2号 = " + new String(body));

                //参数一，确认消息队列中具体哪个消息确认，相当于一个标识
                //参数二，是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
