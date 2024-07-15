package com.cristi.oms;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemoryDaemon implements Runnable {
    private static final Logger logger = Logger.getLogger(MemoryDaemon.class.getName());
    private volatile boolean running = true;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public void run() {
        scheduler.scheduleAtFixedRate(() -> {
            if (running) {
                Runtime runtime = Runtime.getRuntime();
                long totalMemory = runtime.totalMemory();
                long freeMemory = runtime.freeMemory();
                long usedMemory = totalMemory - freeMemory;

                logger.info("Memory Usage: " + usedMemory + " bytes");
            } else {
                scheduler.shutdown();
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    public void stop() {
        running = false;
        scheduler.shutdownNow();
    }
}
