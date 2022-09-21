package com.chensi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/*
 * @author  chensi
 * @date  2022/9/20
 */
@SpringBootTest(classes = RabbitMqApplication.class)
@RunWith(SpringRunner.class)
@ContextConfiguration("/application.yml")
public class provider {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void prodMessaes() {
        rabbitTemplate.convertAndSend("hello", "world");
    }
}
