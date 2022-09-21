package com.chensi.spring.cache.controller;

import com.chensi.spring.cache.pojo.User;
import com.chensi.spring.cache.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author  chensi
 * @date  2022/8/2
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        // 查询数据库
        LOGGER.info("查询数据库");
        User selectById = userService.selectByIdUser(id);
        if (selectById != null) {
            return selectById;
        }
        return null;
    }
}
