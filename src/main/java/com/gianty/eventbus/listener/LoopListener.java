package com.gianty.eventbus.listener;

import com.gianty.service.DemoService;
import com.google.common.eventbus.Subscribe;
import com.gianty.eventbus.annotation.EventListener;
import com.gianty.eventbus.event.LoopEvent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by thien.ld on 4/4/16.
 */
@EventListener
public class LoopListener {
    @Autowired
    private DemoService demoService;

    @Subscribe
    public void onApplicationEvent(LoopEvent evt) {
        System.out.println(">>> loop event on " + Thread.currentThread().getName());
        demoService.check(1001);
    }
}
