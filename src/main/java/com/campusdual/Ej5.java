package com.campusdual;

public class Ej5 {

    public static void main(String[] args) {
        // Comprobar si un numero es positivo o negativo.
        int n = -2;

        if (n > 0) {
            System.out.println(n + " is positive");
        } else {
            System.out.println(n + " is negative");
        }

        // Comprobar si un numero es multiplo de otro.
        int n1 = 12;
        int n2 = 6;

        if (n1 % n2 == 0) {
            System.out.println(n1 + " is multiple of " + n2);
        } else {
            System.out.println(n1 + " is not multiple of " + n2);
        }


        // Comprobar que un numero es menor que otro.
        int n3 = 8;
        int n4 = 10;

        if (n3 < n4) {
            System.out.println(n3 + " is less than " + n4);
        } else if (n3 > n4) {
            System.out.println(n3 + " is greater than " + n4);
        } else {
            System.out.println(n3 + " is equal to " + n4);
        }
    }

}

