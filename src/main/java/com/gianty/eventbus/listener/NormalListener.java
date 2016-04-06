package com.gianty.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import com.gianty.eventbus.annotation.EventListener;
import com.gianty.eventbus.event.NormalEvent;

/**
 * Created by thien.ld on 4/4/16.
 */
@EventListener
public class NormalListener {

    @Subscribe
    public void onApplicationEvent(NormalEvent evt) {
        System.out.println(">>> normal event on " + Thread.currentThread().getName());
    }
}
