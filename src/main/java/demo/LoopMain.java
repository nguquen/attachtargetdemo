package demo;

import com.gianty.eventbus.ApplicationEventHandler;
import com.gianty.eventbus.annotation.EventListener;
import com.gianty.eventbus.event.LoopEvent;
import com.gianty.eventbus.event.NormalEvent;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.gianty.spring.SpringClassScanner;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by thien.ld on 4/4/16.
 */
public class LoopMain {
    private static long count = 0;

    private static void startQueueMonitor() {
        ScheduledExecutorService scheduledExec = Executors.newSingleThreadScheduledExecutor();
        scheduledExec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(">>> queue size: " + ApplicationEventHandler.getInstance().getQueue().size());
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    private static void registerListener(ApplicationContext ctx) {
        SpringClassScanner scanner = new SpringClassScanner();
        scanner.withAnnotationFilter(EventListener.class);
        Set<Class<?>> classes = scanner.findClasses("com.gianty");
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(EventListener.class)) {
                Object bean = ctx.getBean(clazz);
                ApplicationEventHandler.getInstance().register(bean);
            }
        }
    }

    private static void sendEvents() {
        ScheduledExecutorService scheduledExec = Executors.newSingleThreadScheduledExecutor();
        scheduledExec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ApplicationEventHandler.getInstance().postEvent(new NormalEvent());
                if (++count % 5 == 0) {
                    ApplicationEventHandler.getInstance().postEvent(new LoopEvent());
                }
            }
        }, 0, 1, TimeUnit.SECONDS);

    }

    public static void main(String[] args) {
        // init application context
        System.setProperty("config.file", "conf/application.conf");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        // register listener
        registerListener(ctx);
        // start monitor thread
         startQueueMonitor();
        // send events
        sendEvents();
    }
}
