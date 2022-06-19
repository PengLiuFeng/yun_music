/**
 * OPay Inc.
 * Copyright (c) 2016-2022 All Rights Reserved.
 */
package com.plf.yunmusicserver.config.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;

/**
 * @author liufeng.peng
 * @version $Id: ForkJoinPoolExtend.java, v 0.1 2022-06-17 14:53 liufeng.peng Exp $$
 */
@Slf4j
public class ForkJoinPoolExtend extends ForkJoinPool {

    public ForkJoinPoolExtend() {
    }

    public ForkJoinPoolExtend(int parallelism) {
        super(parallelism);
    }

    public ForkJoinPoolExtend(int parallelism, ForkJoinWorkerThreadFactory factory, Thread.UncaughtExceptionHandler handler, boolean asyncMode) {
        super(parallelism, factory, handler, asyncMode);
    }

    @Override
    public void execute(Runnable task) {
        super.execute(task);
        log.info(super.toString());
    }
}