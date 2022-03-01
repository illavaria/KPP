package ru.specialist.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberController {

	Repository rep = new Repository();

	//@RequestMapping("/person") //задаётся адрес URL, запускающий метод
	@GetMapping("/numbers")
	public Number getNumber(@RequestParam(value = "number", required = true) int number) {
		return rep.numberByNum(number);
	}

	@PostMapping("/numbers")
	public Number numberUpdate(

			@RequestParam(value = "number", required = true) int number) {

		Number p = rep.numberByNum(number);
		if (p != null) {
			return p;
		} else {
			String parity;
			if (number % 2 == 0)
				parity = "Чётное";
			else parity = "Нечётное";
			String simplicity = "Простое";
			for (int i = 2; i < number; i++)
				if (number % i == 0) {
					simplicity = "Составное";
					break;
				}
			return rep.addNumber(new Number(parity, simplicity, number));
		}
	}

}
