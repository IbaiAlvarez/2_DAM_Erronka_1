package com.example.erronka1;

public class Mensaje {
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    String texto ;

    public Mensaje(){

    }
    public  Mensaje(String texto){
        this.texto = texto;
    }

}
