package ru.specialist.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.specialist.demo.entity.NumberCharacteristic;
import ru.specialist.demo.exception.ExceptionInfo;
import ru.specialist.demo.service.NumberService;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import java.util.Map;

@Validated
@RestController
public class NumberController {
    private Log logger = LogFactory.getLog(NumberController.class);

    @Autowired
    private final NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/numbers")
    public NumberCharacteristic getNumberCharacteristic(@RequestParam(value = "number",
            required = true) @Min(value = 0, message = "number has to be >1") int number) {
        logger.info("Successfully logged(GET)");
        return numberService.analyzeNumber(number);
    }

    @GetMapping("/cache")
    public Map<Integer, NumberCharacteristic> getCache() {
        logger.info("Successfully got numbers from Map");
        return numberService.getCache();
    }

    @PostMapping("/numbers")
    public NumberCharacteristic saveNumberCharacteristic(@RequestParam(value = "number", required = true)
                                                             @Min(value = 1, message = "number has to be >1") int number)  {
        logger.info("Successfully logged(POST)");
        return numberService.saveNumberCharacteristic(number);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        logger.error("Error 400 - number has to be >1", exception);
        return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        ExceptionInfo exceptionInfo = new ExceptionInfo("argument has to be integer", HttpStatus.BAD_REQUEST.value());
        logger.error("Error 400 - number has to be integer", exception);
        return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        logger.error("Error 400 - number is required in URL", exception);
        return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleInnerException(Exception exception){
        ExceptionInfo exceptionInfo = new ExceptionInfo(exception.getMessage(), 500);
        logger.error("Error 500", exception);
        return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }
}