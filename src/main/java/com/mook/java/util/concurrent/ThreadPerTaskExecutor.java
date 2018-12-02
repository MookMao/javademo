package com.mook.java.util.concurrent;

import java.util.concurrent.Executor;

/**
 * @Author: maojunkai
 * @Date: 2018/11/30 上午12:31
 * @Description: 通过新建线程执行提交的任务：一个任务对应一个线程
 */
public class ThreadPerTaskExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
