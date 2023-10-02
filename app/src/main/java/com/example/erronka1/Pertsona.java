package com.example.erronka1;

public class Pertsona {

    protected String izena;
    protected String abizena;
    protected String email;
    protected String erabiltzailea;
    protected  String erabiltzaile_mota;

    public Pertsona(){

    }

    public Pertsona(String izena,String abizena,String email,String erabiltzailea,String erabiltzaile_mota){
        this.izena=izena;
        this.abizena=abizena;
        this.email=email;
        this.erabiltzailea=erabiltzailea;
        this.erabiltzaile_mota = erabiltzaile_mota;
    }

    //Setters
    public void setIzena(String izena) {
        this.izena = izena;
    }
    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setErabiltzailea(String erabiltzailea) {
        this.erabiltzailea = erabiltzailea;
    }
    public void setErabiltzaile_mota(String erabiltzaile_mota){this.erabiltzaile_mota=erabiltzaile_mota;}

    //Getters
    public String getIzena() {
        return izena;
    }
    public String getAbizena() {
        return abizena;
    }
    public String getEmail() {
        return email;
    }
    public String getErabiltzailea() {
        return erabiltzailea;
    }
    public String getErabiltzaile_mota(){return erabiltzaile_mota;}
}
