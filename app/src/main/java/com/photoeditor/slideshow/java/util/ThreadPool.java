package com.photoeditor.slideshow.java.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {
    private ScheduledThreadPoolExecutor globleExecutor;
    private ExecutorService threadPool;

    private ThreadPool() {
        if (this.threadPool == null) {
            this.threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
            this.globleExecutor = new ScheduledThreadPoolExecutor(1, new ThreadPoolExecutor.DiscardPolicy());
        }
    }

    private static final class Holder {
        /* access modifiers changed from: private */
        public static final ThreadPool instance = new ThreadPool();

        private Holder() {
        }
    }

    public static final ThreadPool get() {
        return Holder.instance;
    }

    public static void run(Runnable runnable) {
        get().threadPool.execute(runnable);
    }

    public static <T> Future<T> submit(Callable<T> callable) {
        return get().threadPool.submit(callable);
    }

    public static final ScheduledThreadPoolExecutor globleExecutor() {
        return Holder.instance.globleExecutor;
    }

    public synchronized void shutDown() {
        get().threadPool.shutdownNow();
        get().globleExecutor.shutdownNow();
    }
}
