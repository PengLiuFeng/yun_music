/**
 * OPay Inc.
 * Copyright (c) 2016-2022 All Rights Reserved.
 */
package com.plf.yunmusicserver.config.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.*;

/**
 * @author liufeng.peng
 * @version $Id: ThreadPoolConfig.java, v 0.1 2022-06-15 15:21 liufeng.peng Exp $$
 */
@Slf4j
public class ThreadPoolExtend  extends  ThreadPoolExecutor{

    public static final Integer DEFAULT_THREAD_CONCURRENT = Runtime.getRuntime().availableProcessors();

    public static final Integer DEFAULT_QUEUE_SIZE = 512;

    public static final Long DEFAULT_WORK_KEEP_LIVE_TIME = 12L;

    public static final  BlockingQueue<Runnable> blockQueue = new LinkedBlockingQueue<>(DEFAULT_QUEUE_SIZE);

    public static final  ThreadFactory threadFactory = new ThreadFactoryBuilder().setDaemon(true).setNameFormat("yum-music-%d").build() ;

    public static ExecutorService executorService;

    static {
        try {
            executorService = new ThreadPoolExtend(DEFAULT_THREAD_CONCURRENT , DEFAULT_THREAD_CONCURRENT*2,DEFAULT_WORK_KEEP_LIVE_TIME,TimeUnit.SECONDS,blockQueue,threadFactory);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    executorService.shutdown();
                    try {
                        if (!executorService.awaitTermination(10L,TimeUnit.SECONDS)){
                            executorService.shutdownNow();
                        }
                    } catch (InterruptedException e) {
                        log.error("thread pool shutdown is fail {}",e);
                        executorService.shutdownNow();
                    }
                }
            }));
        }catch (Exception e){
            log.error("thread pool execption {}" ,e);
            throw e;
        }
    }

    @Override
    public void execute(Runnable command) {
        if (blockQueue.size() == DEFAULT_QUEUE_SIZE && getActiveCount() == DEFAULT_THREAD_CONCURRENT*2){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.info("wait ····");
            }
        }
        super.execute(command);
        //log.info("queue is {} , liveThread is {}, task count is {}",blockQueue.size(),getActiveCount(),getTaskCount());
    }

    public ThreadPoolExtend(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ThreadPoolExtend(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ThreadPoolExtend(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ThreadPoolExtend(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public static Executor getThreadPool(){
        return executorService;
    }
}