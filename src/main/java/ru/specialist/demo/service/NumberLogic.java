package ru.specialist.demo.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.specialist.demo.controller.CounterController;
import ru.specialist.demo.entity.NumberCharacteristic;
import ru.specialist.demo.repository.NumberInMemoryCache;

import java.util.List;
import java.util.OptionalInt;

@Service
public class NumberLogic {

    public static Integer calculateSum(List<Integer> parameterList){
        return parameterList.stream().mapToInt(Integer::intValue).sum();
    }

    public static Integer findMin(List<Integer> parameterList) {
        OptionalInt min = parameterList.stream().mapToInt(Integer::intValue).min();
        if(min.isPresent()){
            return min.getAsInt();
        }
        else{
            return null;
        }
    }

    public static Integer findMax(List<Integer> parameterList) {
        OptionalInt max = parameterList.stream().mapToInt(Integer::intValue).max();
        if(max.isPresent()){
            return max.getAsInt();
        }
        else{
            return null;
        }
    }
}
