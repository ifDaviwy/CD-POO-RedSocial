package com.campusdual;
public class Coche {
    private String marca;
    private String modelo;
    private int velocidadMaxima;
    private String tipoCombustible;
    private int velocimetro;
    private int tacometro;

    public Coche(String marca, String modelo, int velocidadMaxima, String tipoCombustible) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidadMaxima = velocidadMaxima;
        this.tipoCombustible = tipoCombustible;
        this.velocimetro = 0;
        this.tacometro = 0;
    }

    public void arrancar() {
        System.out.println("El coche ha arrancado");
    }

    public void apagar() {
        System.out.println("El coche se ha apagado");
    }

    public void acelerar() {
        System.out.println("Acelerando");
    }

    public void frenar() {
        System.out.println("Frenando");
    }

    public void girarVolante() {
        System.out.println("Girando el volante");
    }

    public void marchaAtras() {
        System.out.println("Marcha atrás activada");
    }

    public static void main(String[] args) {
        Coche coche = new Coche("Subaru", "BRZ", 220, "Gasolina");

        coche.arrancar();
        coche.acelerar();
        coche.girarVolante();
        coche.marchaAtras();
        coche.frenar();
        coche.apagar();

    }
}
