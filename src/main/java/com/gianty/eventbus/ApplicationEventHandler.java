package com.gianty.eventbus;

public class ApplicationEventHandler extends SystemEventBus {

	private static String SystemName = "system-event-bus";
	private static int poolSize = 5;

	private static ApplicationEventHandler instance = new ApplicationEventHandler();

	public static ApplicationEventHandler getInstance() {
		return instance;
	}

	public ApplicationEventHandler() {
		super(SystemName, poolSize);
	}

}
