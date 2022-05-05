package ru.specialist.demo.repository;

import ru.specialist.demo.entity.NumberCharacteristic;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class NumberInMemoryCache {

    final private static boolean IS_EVEN = true; //чётное
    final private static boolean IS_COMPOSITE = true; //составное

    Map<Integer, NumberCharacteristic> numberMap = new LinkedHashMap<>();

    {
        numberMap.put(35, new NumberCharacteristic(!IS_EVEN, IS_COMPOSITE));
        numberMap.put(13, new NumberCharacteristic(!IS_EVEN, !IS_COMPOSITE));
        numberMap.put(8, new NumberCharacteristic(IS_EVEN, IS_COMPOSITE));
    }

    public boolean isContain(Integer key) {
        return numberMap.containsKey(key);
    }


    public NumberCharacteristic findByNumber(int number) {
        return numberMap.get(number);
    }

    public NumberCharacteristic addNumberCharacteristic(int number, NumberCharacteristic numberCharacteristic) {
        numberMap.put(number, numberCharacteristic);
        return numberCharacteristic;
    }

    public Map<Integer, NumberCharacteristic> getNumberMap() {
        return numberMap;
    }

}
