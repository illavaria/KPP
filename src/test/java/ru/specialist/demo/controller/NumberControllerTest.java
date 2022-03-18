package ru.specialist.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.specialist.demo.entity.NumberCharacteristic;
import ru.specialist.demo.repository.NumberInMemoryCache;
import ru.specialist.demo.service.NumberService;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class NumberControllerTest {

    @Autowired private TestRestTemplate restTemplate;

    @Test
    public void positiveTest() {
        NumberService numberService = new NumberService(new NumberInMemoryCache());
        NumberController numberController = new NumberController(numberService);
        NumberCharacteristic actual = numberController.getNumberCharacteristic(23);
        boolean[] actualArr = new boolean[] {actual.isEven(), actual.isComposite()};
        boolean[] expectedArr = new boolean[] {false, false};
        assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    public void handleConstraintViolationException() {
        String actual =
                restTemplate.getForObject("http://localhost:8080/numbers?number=-1", String.class);
        String expected =
                "{\"message\":\"getNumberCharacteristic.number: number has to be >1\",\"code\":400}";
        assertEquals(expected, actual);
    }

    @Test
    public void handleMethodArgumentTypeMismatchException() {
        String actual =
                restTemplate.getForObject("http://localhost:8080/numbers?number=8fsjk", String.class);
        String expected = "{\"message\":\"argument has to be integer\",\"code\":400}";
        assertEquals(expected, actual);
    }

    @Test
    public void handleMissingServletRequestParameterException() {
        String actual = restTemplate.getForObject("http://localhost:8080/numbers?", String.class);
        String expected =
                "{\"message\":\"Required request parameter 'number' for method parameter type int is not present\",\"code\":400}";
        assertEquals(expected, actual);
    }
}
