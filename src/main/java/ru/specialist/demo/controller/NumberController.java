package ru.specialist.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.specialist.demo.entity.NumberCharacteristic;
import ru.specialist.demo.service.NumberService;

import java.util.Map;

@RestController
public class NumberController {

    @Autowired
    private final NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/numbers")
    public NumberCharacteristic getNumberCharacteristic(@RequestParam(value = "number", required = true) int number) {
        return numberService.analyzeNumber(number);
    }

    @GetMapping("/cache")
    public Map<Integer, NumberCharacteristic> getCache() {
        return numberService.getCache();
    }

    @PostMapping("/numbers")
    public NumberCharacteristic saveNumberCharacteristic(@RequestParam(value = "number", required = true) int number) {
        return numberService.saveNumberCharacteristic(number);
    }
}
