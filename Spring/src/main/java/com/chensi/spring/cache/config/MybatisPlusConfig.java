package com.chensi.spring.cache.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * @author  chensi
 * @date  2022/8/2
 */
@MapperScan("com.chensi.spring.cache.mapper")
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    //注册乐观锁插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    // 分页插件
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        return new MybatisPlusInterceptor();
    }

    //// 逻辑删除组件！
    //@Bean
    //public ISqlInjector sqlInjector() {
    //    return new LogicSqlInjector();
    //}
    //
    ///**
    // * SQL执行效率插件
    // */
    //@Bean
    //@Profile({"dev","test"})// 设置 dev test 环境开启，保证我们的效率
    //public PerformanceInterceptor performanceInterceptor() {
    //    PerformanceInterceptor performanceInterceptor = new
    //        PerformanceInterceptor();
    //    performanceInterceptor.setMaxTime(100); // ms设置sql执行的最大时间，如果超过了则不执行
    //    performanceInterceptor.setFormat(true); // 是否格式化代码
    //    return performanceInterceptor;
    //}


}
