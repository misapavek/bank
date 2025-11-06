package org.example.accounts.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.logger.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Singleton
public class InterestCronService {

    @Inject
    private InterestProcessingFacade interestProcessingFacade;

    @Inject
    private Logger logger;

    private ScheduledExecutorService scheduler;
    private boolean isRunning = false;

    public void start() {
        if (isRunning) {
            logger.log("Interest cron service is already running");
            return;
        }

        logger.log("Starting interest cron service - will run every second");

        scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(
                () -> {
                    try {
                        logger.log("=== Interest cron job triggered ===");
                        interestProcessingFacade.processAllSavingAccounts();
                    } catch (Exception e) {
                        logger.log("Error in interest cron job: " + e.getMessage());
                        e.printStackTrace();
                    }
                },
                0,              // start immediately
                1,              // run every 1 second (for testing)
                TimeUnit.SECONDS
        );

        isRunning = true;
        logger.log("Interest cron service started successfully");
    }

    public void stop() {
        if (!isRunning) {
            logger.log("Interest cron service is not running");
            return;
        }

        logger.log("Stopping interest cron service");

        if (scheduler != null) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        isRunning = false;
        logger.log("Interest cron service stopped");
    }

    public void runManually() {
        logger.log("Manual interest processing triggered");
        interestProcessingFacade.processAllSavingAccounts();
    }

    public boolean isRunning() {
        return isRunning;
    }
}