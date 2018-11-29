package com.mook.java.util.concurrent;

import java.util.concurrent.Executor;

/**
 * @Author: maojunkai
 * @Date: 2018/11/30 上午12:26
 * @Description: 在主线程中立刻运行提交给执行器的任务
 */
public class DirectExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
