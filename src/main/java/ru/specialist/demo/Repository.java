package ru.specialist.demo;

//import sun.jvm.hotspot.types.CIntegerType;

import java.util.Hashtable;
import java.util.Map;

public class Repository {

	static Map<Integer, Number> numberMap = new Hashtable<>();

	static {
		numberMap.put(35, new Number("Нечётное","Составное", 35));
		numberMap.put(13, new Number("Нечётное","Простое", 13));
		numberMap.put(8, new Number("Чётное","Составное", 8));

	}

	public Number numberByNum(int number){return numberMap.get(number);}

	public Number addNumber(Number p) {
		numberMap.put(p.getNumber(), p);
		return p;
	}
}
