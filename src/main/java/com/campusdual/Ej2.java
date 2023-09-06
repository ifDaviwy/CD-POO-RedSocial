package com.campusdual;

public class Ej2 {
    // Dado un radio 15 area de circulo y longirtud de circunferencia

    public static void main(String[] args) {
        int radio = 15;

        double a = Math.PI * Math.pow(radio, 2);
        double l = 2 * Math.PI * radio;

        System.out.println("Circle area: " + a);
        System.out.println("Circumference: " + l);
    }

}
