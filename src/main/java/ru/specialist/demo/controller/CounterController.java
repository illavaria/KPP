package ru.specialist.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.specialist.demo.counter.RequestCounter;

@RestController
public class CounterController {
    private Log logger = LogFactory.getLog(CounterController.class);

    @GetMapping("/counter")
    public String getCounter() {
        logger.info("Successfully got counter");
        return RequestCounter.getCounter() + " requests were completed";
    }
}
