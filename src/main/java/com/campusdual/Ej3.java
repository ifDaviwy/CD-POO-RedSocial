package com.campusdual;

public class Ej3 {
    //Crear funcion, metodo y procedimiento
    public static void hello(){
        System.out.println("Hi");
    }

    public static String createMessage (String message){
        return "Hola " + message;
    }
    public static void main(String[] args) {
        String hello = createMessage("David");
        hello();
        System.out.println(hello);
    }

}
