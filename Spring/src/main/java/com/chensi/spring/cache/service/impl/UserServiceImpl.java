package com.chensi.spring.cache.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chensi.spring.cache.controller.UserController;
import com.chensi.spring.cache.mapper.UserMapper;
import com.chensi.spring.cache.pojo.User;
import com.chensi.spring.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;

/*
 * @author  chensi
 * @date  2022/8/2
 */
@Service
@CacheConfig(cacheNames = "user", keyGenerator = "keyGenerator")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    // 日志
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据ID查询用户
     * 这里的@Cacheable注解不需要添加key属性了，因为已经在全局制定过了key的生成策略
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Cacheable(value = "user")
    @Override
    public User selectByIdUser(Long id) {
        LOGGER.info("根据ID查询用户");
        return userMapper.selectByIdUser(id);
    }
}
