package com.campusdual;

public class Ej7 {

    public static void first15() {
        System.out.println("First 15 numbers");
        for (int i = 1; i <= 15; i++) {
            System.out.println(i);
        }
    }

    public static void sumFirst100() {
        System.out.println("Sum first 100 numbers");
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
            System.out.println(sum);
        }
    }
    public static void main(String[] args) {
        first15();
        sumFirst100();
    }
}
