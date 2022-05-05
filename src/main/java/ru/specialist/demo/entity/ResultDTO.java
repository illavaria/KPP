package ru.specialist.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultDTO {
    @JsonProperty
    private List<NumberCharacteristic> list;
    @JsonProperty
    private Integer maxNumber;
    @JsonProperty
    private Integer minNumber;
    @JsonProperty
    private Integer sumOfNumbers;

    public ResultDTO(List<NumberCharacteristic> list, Integer maxNumber, Integer minNumber, Integer sumOfNumbers) {
        this.list = list;
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
        this.sumOfNumbers = sumOfNumbers;
    }
}
