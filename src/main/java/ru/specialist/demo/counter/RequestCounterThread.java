package ru.specialist.demo.counter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.specialist.demo.controller.CounterController;

public class RequestCounterThread extends Thread{
    private Log logger = LogFactory.getLog(CounterController.class);

    public  RequestCounterThread(){
        super();
        start();
    }

    public void run(){
        int flag =1;
        while(flag==1){
            try {
                logger.info(Thread.currentThread().getName()+" is waiting");
                Synchronization.semaphore.acquire();
                RequestCounter.increment();
                logger.info("Counter was incremented. It's  value is: "+RequestCounter.getCounter());
                flag =0;
            } catch (InterruptedException exception){
                logger.error("Thread was interrupted");
            }
        }
    }
}
