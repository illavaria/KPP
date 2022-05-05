package ru.specialist.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.specialist.demo.counter.RequestCounterThread;
import ru.specialist.demo.entity.NumberCharacteristic;
import ru.specialist.demo.repository.NumberInMemoryCache;

import java.util.Map;

@Service
public class NumberService {

    @Autowired
    private final NumberInMemoryCache repository;

    public NumberService(NumberInMemoryCache repository) {
        this.repository = repository;
    }

    public Map<Integer, NumberCharacteristic> getCache() {
        new RequestCounterThread(Thread.currentThread().getName()).start();
        return repository.getNumberMap();
    }

    public NumberCharacteristic saveNumberCharacteristic(int number) {
        new RequestCounterThread(Thread.currentThread().getName()).start();
        NumberCharacteristic existingNumberCharacteristic = repository.findByNumber(number);
        if (existingNumberCharacteristic != null) {
            return existingNumberCharacteristic;
        } else {
            return repository.addNumberCharacteristic(number, analyzeNumber(number));
        }
    }

    public NumberCharacteristic analyzeNumber(int number) {
        boolean isEven = number % 2 == 0;
        boolean isComposite = false;
        for (int i = 2; i < number; i++)
            if (number % i == 0) {
                isComposite = true;
                break;
            }
        return new NumberCharacteristic(isEven, isComposite);
    }
}
