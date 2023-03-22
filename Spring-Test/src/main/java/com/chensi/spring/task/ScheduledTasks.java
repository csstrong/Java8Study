package com.chensi.spring.task;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @author  chensi
 * @date  2023/1/7
 */

/**
 * Scheduled()注解以及cron表达式详解
 * Scheduled注解：任务自动化调度
 * 1.@Scheduled(fixedDelay=5000)
 * 延迟执行。任务在上个任务完成后达到设置的延时时间就执行
 * 2.@Scheduled(fixRate=5000)
 * 定时执行。任务间隔规定时间即执行
 * 3.@Scheduled(cron="0 0 2 * * ?")
 */
@Component
public class ScheduledTasks {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ScheduledTasks.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(cron = "0/2 * * * * ?")
	public void reportCurrentTime() {
		log.info("现在时间：" + dateFormat.format(new Date()));
	}
}
