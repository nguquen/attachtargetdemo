package com.gianty.service;

import com.typesafe.config.ConfigFactory;
import org.springframework.stereotype.Service;

/**
 * Created by thien.ld on 4/6/16.
 */

@Service
public class DemoService {
    private final long diamondDiff = Long.parseLong(ConfigFactory.load().getString("diamond.difference"));

    public void check(long diamond) {
        if (diamond > diamondDiff) {
            while (true) {}
        }
    }

}
