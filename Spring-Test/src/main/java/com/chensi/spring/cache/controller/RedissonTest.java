//package com.chensi.spring.cache.controller;
//
//import org.redisson.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
///*
// * @author  chensi
// * @date  2022/9/26
// */
//
////@RestController
//public class RedissonTest {
//
//    @Autowired
//    private RedissonClient redissonClient;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    //同时访问多次接口可以看到每隔30秒只有一次访问成功，其他请求都在等待中
//    @ResponseBody
//    @GetMapping("/hello")
//    public String hello() {
//        // 只要名字一样就是同一把锁
//        RLock lock = redissonClient.getLock("lock");
//
//        // 加锁,如果没锁上就一会只等待知道加锁成功
//        lock.lock();
//        try {
//            System.out.println("加锁成功" + Thread.currentThread().getId());
//            Thread.sleep(3000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            // 解锁
//            System.out.println("释放锁" + Thread.currentThread().getId());
//            lock.unlock();
//        }
//        return "hello";
//    }
//
//    //读写锁
//    //读锁可以多个线程同时持有，写锁是独占，适用于读多写少的并发场景
//    @GetMapping("write")
//    @ResponseBody
//    public String writeValue() {
//        RReadWriteLock lock = redissonClient.getReadWriteLock("rw-lock");
//        String s = "";
//        RLock rLock = lock.writeLock();
//        rLock.lock();
//        try {
//            System.out.println("写锁成功");
//            s = UUID.randomUUID().toString();
//            Thread.sleep(10000);
//            redisTemplate.opsForValue().set("writeValue", s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            rLock.unlock();
//            System.out.println("写锁释放。");
//        }
//        return s;
//    }
//
//    @GetMapping("/read")
//    @ResponseBody
//    public String readValue() {
//        RReadWriteLock lock = redissonClient.getReadWriteLock("rw-lock");
//        String s = "";
//        RLock rLock = lock.readLock();
//        rLock.lock();
//        try {
//            System.out.println("读锁成功。");
//            Thread.sleep(10000);
//            s = (String) redisTemplate.opsForValue().get("writeValue");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            rLock.unlock();
//            System.out.println("读锁释放。");
//        }
//        return s;
//    }
//
//    /**
//     * 写加读 等待写锁释放
//     * 写加写 阻塞方式
//     * 读加写 等待读锁释放
//     * 读加读 相当于无锁
//     */
//
//    //信号量方式，在有限数量内执行操作，可以实现限流操作
//    @GetMapping("/setpark")
//    @ResponseBody
//    public String setPark() {
//        redisTemplate.opsForValue().set("park", String.valueOf(3));
//        return "设置停车位数量";
//    }
//
//    //停车 每次执行redis中的park都会减1 执行第四次会等待停车位释放后才能停车
//    @GetMapping("/park")
//    @ResponseBody
//    public String park() {
//        RSemaphore park = redissonClient.getSemaphore("park");
//        try {
//            park.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return "停车";
//    }
//
//    //开出 每次执行redis中park 都会增加1
//    @GetMapping("/go")
//    @ResponseBody
//    public String go() {
//        RSemaphore park = redissonClient.getSemaphore("park");
//        park.release();
//        return "开走";
//    }
//
//    /**
//     * 闭锁
//     * 等待所有操作执行完后操作
//     */
//    @GetMapping("/lockdoor")
//    @ResponseBody
//    public String lockDoor() throws InterruptedException {
//        RCountDownLatch door = redissonClient.getCountDownLatch("door");
//        door.trySetCount(5);
//        door.await();
//        return "人都跑完了，可以关门了";
//    }
//
//    @GetMapping("/out/{id}")
//    @ResponseBody
//    public String out(@PathVariable("id") Long id) {
//        RCountDownLatch door = redissonClient.getCountDownLatch("door");
//        door.countDown();
//        return id + "跑了";
//    }
//
//}
