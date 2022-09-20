//package com.chensi.spring.task;
//
///***********************************
// * @author chensi
// * @date 2022/5/20 8:43
// ***********************************/
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.concurrent.Executor;
//import java.util.concurrent.ThreadPoolExecutor;
//
///**
// * Spring Task底层是基于JDK的ScheduledThreadPoolExecutor线程池来实现的。
// * 直接通过Spring提供的@Scheduled注解可定义定时任务。
// * <p>
// * String cron() default "";  // 支持cron表达式
// * <p>
// * long fixedDelay() default -1;  // 在最后一次调用结束和下一次调用开始之间的时间间隔，以毫秒为单位
// * String fixedDelayString() default "";  // 同上，类似ScheduledExecutorService的scheduleWithFixedDelay
// * <p>
// * long fixedRate() default -1;  // 在调用之前的时间间隔，以毫秒为单位
// * String fixedRateString() default "";  // 同上，类似ScheduledExecutorService的scheduleAtFixedRate
// * <p>
// * long initialDelay() default -1;  // 在第一次执行fixedRate()或fixedDelay()任务之前要延迟的毫秒数
// * String initialDelayString() default "";  // 同上
// */
//
///**
// * @EnableAsync 看起来是线程复用的，而实际上不重用线程，应尽量实现一个线程池TaskExecutor，特别是用于执行大量短期任务。
// */
//
//@Component
////@EnableAsync //开启异步多线程
//@EnableScheduling
//public class SpringTaskTest {
//
//    //@Async
//    @Scheduled(fixedRate = 2000L)
//    public void task() {
//        System.out.println("当前线程：" + Thread.currentThread().getName() + " 当前时间" + LocalDateTime.now());
//    }
//
//    @Bean("taskExecutor")
//    public Executor taskExecutor() {
//        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//        taskExecutor.setCorePoolSize(10);
//        taskExecutor.setMaxPoolSize(50);
//        taskExecutor.setQueueCapacity(200);
//        taskExecutor.setKeepAliveSeconds(60);
//        taskExecutor.setThreadNamePrefix("自定义-");
//        taskExecutor.setAwaitTerminationSeconds(60);
//        return taskExecutor;
//    }
//}
