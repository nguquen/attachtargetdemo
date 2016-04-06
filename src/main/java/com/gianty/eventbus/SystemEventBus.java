package com.gianty.eventbus;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

public class SystemEventBus {

	private EventBus eventBus;
	private LinkedBlockingQueue<Runnable> queue;

	public SystemEventBus(String name, int poolSize) {
		queue = new LinkedBlockingQueue<Runnable>();
		eventBus = new AsyncEventBus(name, new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, queue));
	}

	public void postEvent(Object event) {
		eventBus.post(event);
	}

	public void register(Object object) {
		eventBus.register(object);
	}

	public void unregister(Object object) {
		eventBus.unregister(object);
	}

	public LinkedBlockingQueue<Runnable> getQueue() {
		return queue;
	}

}
