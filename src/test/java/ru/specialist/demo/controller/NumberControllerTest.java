package ru.specialist.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.specialist.demo.entity.NumberCharacteristic;
import ru.specialist.demo.exception.ExceptionInfo;
import ru.specialist.demo.repository.NumberInMemoryCache;
import ru.specialist.demo.service.NumberService;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
//@WebMvcTest(NumberController.class)
public class NumberControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
   public void PositiveTest(){
        NumberService numberService = new NumberService(new NumberInMemoryCache());
        NumberController numberController = new NumberController(numberService);
        NumberCharacteristic actual = numberController.getNumberCharacteristic(23);
        boolean[] actualArr = new boolean[]{actual.isEven(), actual.isComposite()};
        boolean[] expectedArr = new boolean[]{false,false};
        assertArrayEquals(expectedArr,actualArr);
    }

    @Test
    public void handleConstraintViolationException() throws ConstraintViolationException {
        String actual =restTemplate.getForObject("http://localhost:8080/numbers?number=-1",String.class);
        String expected = "{\"message\":\"getNumberCharacteristic.number: number has to be >1\",\"code\":400}";
        assertEquals(expected,actual);
    }

    @Test
    public void handleMethodArgumentTypeMismatchException() throws MethodArgumentTypeMismatchException {
     String actual =restTemplate.getForObject("http://localhost:8080/numbers?number=8fsjk",String.class);
     String expected = "{\"message\":\"argument has to be integer\",\"code\":400}";
       assertEquals(expected,actual);
    }

    @Test
    public void handleMissingServletRequestParameterException() throws MissingServletRequestParameterException {
        String actual =restTemplate.getForObject("http://localhost:8080/numbers?",String.class);
        String expected = "{\"message\":\"Required request parameter 'number' for method parameter type int is not present\",\"code\":400}";
        assertEquals(expected,actual);
    }

}