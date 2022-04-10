package ru.specialist.demo.counter;

public class RequestCounter {
    private static int counter = -1;

    public static void increment(){
        counter++;
    }

    public static int getCounter() {
        return counter;
    }
}
