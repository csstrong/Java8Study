# 多线程协作的场景
- 生产者、消费者协作模式：生产者和消费者线程通过共享队列进行协作，生产者将数据或任务放到队列上，而消费者从队列上取数据或任务。
- 同时开始：要求多个线程同时开始。
- 等待结束：主从协作模式，主线程将任务分解为若干个子任务，为每个子任务创建一个线程，主线程在继续执行其他任务之前需要等待每个子任务执行完毕。
- 异步结果：将子线程的管理封装为异步调用，异步调用马上返回，但返回的不是最终的结果，而是一个一般称为Future的对象，通过它可以做唉随后获得最终的结果。
- 集合点：比如在并行迭代计算中，每个线程负责一部分计算，然后再集合点等待其他线程完成，所有线程到齐后，交换数据和计算结果，再进行下一次迭代。