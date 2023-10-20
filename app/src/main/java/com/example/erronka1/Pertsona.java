package com.example.erronka1;

import java.io.Serializable;

public class Pertsona implements Serializable {

    protected String izena;
    protected String abizena;
    protected String email;
    protected String erabiltzaile_nick;
    protected  String erabiltzaile_mota;

    public Pertsona(){
    }

    public Pertsona(String izena,String abizena,String email,String erabiltzaile_nick,String erabiltzaile_mota){
        this.izena=izena;
        this.abizena=abizena;
        this.email=email;
        this.erabiltzaile_nick=erabiltzaile_nick;
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

    public void setErabiltzaile_nick(String erabiltzaile_nick) {
        this.erabiltzaile_nick = erabiltzaile_nick;
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

    public String getErabiltzaile_nick() {
        return erabiltzaile_nick;
    }

    public String getErabiltzaile_mota(){return erabiltzaile_mota;}
}
