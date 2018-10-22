package com.sugar.leke.thread.pool;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public final class ThreadPoolScheduler {

	private static ThreadPoolScheduler manager = new ThreadPoolScheduler();
	private ThreadPoolExecutor pool;

	private ThreadPoolScheduler() {
		pool = new ThreadPoolExecutor(100, 160, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100), new ThreadPoolExecutor.CallerRunsPolicy());
	}

	public static boolean addTask(Runnable run) {
		manager.pool.execute(run);
		return true;
	}

	public static void stopTask(String mobile) {
		BlockingQueue<Runnable> queue = manager.pool.getQueue();
		Iterator<Runnable> iterator = queue.iterator();
		while (iterator.hasNext()) {
			Runnable runnable = iterator.next();
			Thread thread = new Thread(runnable);
			String threadName = thread.getName();
			if (threadName.equals(mobile)) {
				thread.interrupt();
				System.out.println("线程" + threadName + "已停止");
			}
		}
	}
}