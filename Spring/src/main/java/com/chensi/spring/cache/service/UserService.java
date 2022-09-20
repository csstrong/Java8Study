package com.chensi.spring.cache.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chensi.spring.cache.pojo.User;

/*
 * @author  chensi
 * @date  2022/8/2
 */
public interface UserService extends IService<User> {
    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户信息
     */
    User selectByIdUser(Long id);
}
