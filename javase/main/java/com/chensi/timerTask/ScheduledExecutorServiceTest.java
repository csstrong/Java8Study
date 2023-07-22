package com.chensi.timerTask;
import java.time.LocalDateTime;
import java.util.concurrent.*;

/***********************************
 * @author chensi
 * @date 2022/5/19 17:46
 ***********************************/
public class ScheduledExecutorServiceTest {
    public static void main(String[] args) {
        // 创建一个ScheduledThreadPoolExecutor线程池，核心线程数为5
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5);
        // 创建Runnable打印当前线程和当前时间
        Runnable r = () -> System.out.println("当前线程：" + Thread.currentThread().getName() + " 当前时间" + LocalDateTime.now());
        /**
         * schedule：只执行一次调度
         * scheduleAtFixedRate：一开始就计算间隔时间，如果任务超过间隔时间，那么就直接开始下一个任务
         * scheduleWithFixedDelay：任务无论执行多久，都要等待上一轮任务完成之后再间隔指定时间，然后才开始下一个任务
         */
        // 在指定1秒延迟后执行r,之后每两秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(r, 1, 2, TimeUnit.SECONDS);
    }
}
