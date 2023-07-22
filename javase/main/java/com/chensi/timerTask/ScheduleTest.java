package com.chensi.timerTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
/***********************************
 * @author chensi
 * @date 2022/5/19 17:40
 ***********************************/
public class ScheduleTest {
    public static void main(String[] args) {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("当前线程：" + Thread.currentThread().getName() + " 当前时间" + (LocalDateTime.now().format(formatter)));
            }
        };
        // 在指定延迟0毫秒后开始，随后地执行以2000毫秒间隔执行timerTask
        new Timer().schedule(timerTask, 0L, 2000L);
        System.out.println("当前线程：" + Thread.currentThread().getName() + " 当前时间" + LocalDateTime.now());
    }
}
