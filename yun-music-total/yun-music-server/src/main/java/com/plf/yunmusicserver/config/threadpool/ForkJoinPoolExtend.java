/**
 * OPay Inc.
 * Copyright (c) 2016-2022 All Rights Reserved.
 */
package com.plf.yunmusicserver.config.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author liufeng.peng
 * @version $Id: ForkJoinPoolExtend.java, v 0.1 2022-06-17 14:53 liufeng.peng Exp $$
 */
@Slf4j
public class ForkJoinPoolExtend {

    public static ExecutorService executorService;

    static {
        executorService = ForkJoinPool.commonPool();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                executorService.shutdown();
                try {
                    if (!executorService.awaitTermination(10L, TimeUnit.SECONDS)) {
                        executorService.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    executorService.shutdownNow();
                }
            }
        }));
    }

    public static ExecutorService getInstance(){
        return executorService;
    }

}