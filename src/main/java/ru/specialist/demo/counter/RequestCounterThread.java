package ru.specialist.demo.counter;

import ru.specialist.demo.controller.CounterController;

import java.util.concurrent.Semaphore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestCounterThread extends Thread {
    private Log logger = LogFactory.getLog(RequestCounterThread.class);
    private static final Semaphore semaphore = new Semaphore(1, true);

    private final String nameOfParentThread;

    public RequestCounterThread(String nameOfParentThread) {
        this.nameOfParentThread = nameOfParentThread;
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println("Start: Available number of permits for " + threadName + " (parent " + nameOfParentThread + ") is: " + semaphore.availablePermits());
            logger.info(threadName + " is waiting for resolution");
            semaphore.acquire();
            System.out.println(threadName + " acquired permit");

            try {
                RequestCounter.increment();
            } finally {
                logger.info("Counter after increment " + RequestCounter.getCounter());
                semaphore.release();
                logger.info(threadName + " is released");
                System.out.println("End: Available number of permits for " + threadName + " (parent " + nameOfParentThread + ") is: " + semaphore.availablePermits());
            }
        } catch (InterruptedException e) {
            logger.error("Thread was interrupted");
        }
        System.out.println("Thread " + threadName + " (parent " + nameOfParentThread + ")" + " is terminating.");
    }
}
