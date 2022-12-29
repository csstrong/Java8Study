package com.chensi.rabbitmq.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
 * @author  chensi
 * @date  2022/9/20
 */
public class RabbitMqUtil {

    private static final ConnectionFactory connectionFactory;

    private static final String Host = "110.40.151.182";

    private static final int Port = 1003;

    private static final String VirtualHost = "/";

    private static final String UserName = "guest";

    private static final String Password = "guest";

    static {
        //先创建连接server的连接
        connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq的IP和端口号
        connectionFactory.setHost(Host);
        connectionFactory.setPort(Port);
        connectionFactory.setVirtualHost(VirtualHost);
        connectionFactory.setUsername(UserName);
        connectionFactory.setPassword(Password);
    }

    public static Connection getConnection() {
        try {
            //创建通道连接虚拟主机的对象
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和连接工具方法
    public static void closeConnectionAndChannel(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
