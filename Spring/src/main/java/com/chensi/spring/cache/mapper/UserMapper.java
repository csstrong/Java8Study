package com.chensi.spring.cache.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chensi.spring.cache.pojo.User;
import org.springframework.stereotype.Repository;

/*
 * @author  chensi
 * @date  2022/8/2
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户信息
     */
    User selectByIdUser(Long id);
}
