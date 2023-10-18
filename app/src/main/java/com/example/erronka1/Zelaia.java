package com.example.erronka1;

import java.util.List;

public class Zelaia {
    protected String zelai_izena;
    protected List<Erreserba> erreserbak;
    protected List<String> orduak;

    public Zelaia(){
    }

    public Zelaia(String zelai_izena,List<Erreserba> erreserbak, List<String > orduak){
        this.zelai_izena = zelai_izena;
        this.erreserbak = erreserbak;
        this.orduak=orduak;
    }

    //Setters
    public void setZelai_izena(String zelai_izena) {
        this.zelai_izena = zelai_izena;
    }
    public void setErreserbak(List<Erreserba> erreserbak) {
        this.erreserbak = erreserbak;
    }
    public void setOrduak(List<String> orduak) {
        this.orduak = orduak;
    }

    //Getters
    public String getZelai_izena() {
        return zelai_izena;
    }
    public List<Erreserba> getErreserbak() {
        return erreserbak;
    }
    public List<String> getOrduak() {
        return orduak;
    }
}
