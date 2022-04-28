package ru.specialist.demo.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.specialist.demo.controller.CounterController;
import ru.specialist.demo.entity.NumberCharacteristic;
import ru.specialist.demo.repository.NumberInMemoryCache;

import java.util.List;

@Service
public class NumberLogic {
    @Autowired
    private static NumberService numberService;

    private Log logger = LogFactory.getLog(NumberLogic.class);
    @Autowired
    private NumberInMemoryCache hashMap;

    public static NumberCharacteristic calculateResult(Integer requestParameters) throws IllegalArgumentException{
        /*NumberCharacteristic numberCharacteristic = new NumberCharacteristic(false, false);

        if(hashMap.isContain(requestParameters)){
           numberCharacteristic = hashMap.findByNumber(requestParameters);
           logger.info("Successfully got numbers from Map");
       }
        else {
            hashMap.addNumberCharacteristic(requestParameters,numberCharacteristic);
            logger.info("Successfully added number's characteristic");
        }
       return numberCharacteristic;*/
        return numberService.saveNumberCharacteristic(requestParameters);
    }

    public static Integer calculateSum(List<Integer> resultList){
        Integer sum =0;
        if(!resultList.isEmpty()){
            sum = resultList.stream().mapToInt(Integer::intValue).sum();
        }
        return sum;
    }
    public static Integer findMax(List<Integer> resultList){
        Integer max =0;
        if(!resultList.isEmpty()){
            max = resultList.stream().mapToInt(Integer::intValue).max().getAsInt();
        }
        return max;
    }
    public static Integer findMin(List<Integer> resultList){
        Integer min =0;
        if(!resultList.isEmpty()){
            min = resultList.stream().mapToInt(Integer::intValue).min().getAsInt();
        }
        return min;
    }


}
