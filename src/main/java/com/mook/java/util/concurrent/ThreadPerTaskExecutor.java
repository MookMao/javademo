package com.mook.java.util.concurrent;

import java.util.concurrent.Executor;

/**
 * @Author: maojunkai
 * @Date: 2018/11/30 上午12:31
 * @Description:
 */
public class ThreadPerTaskExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
