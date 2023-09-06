package com.campusdual;

public class Ej9 {

    public int actualFuel = 10;

    public void showDetails(){
        System.out.println("La capacidad acutal es de: "+ this.actualFuel + " litros");
    }

    public void setActualFuel(int actualFuel){
        if(actualFuel < 0){
            System.out.println("No se puede poner un valor negativo");
        }else{
            this.actualFuel = actualFuel;
        }
    }

    public static void main(String[] args) {
        Ej9 cO = new Ej9();
        cO.showDetails();
        System.out.println("Actualizacion de capacidad");
        cO.setActualFuel(-8);
        cO.showDetails();
    }

}
