package ru.specialist.demo.counter;

public class RequestCounter {
    private static int counter;

    public static void increment(){
        ++counter;
    }

    public static int getCounter() {
        return counter;
    }
}
