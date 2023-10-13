package com.example.erronka1;

public class Erreserba {

    protected String kirola;
    protected String zelaia;
    protected String data;
    protected String ordua;
    protected String erabiltzailea;

    public Erreserba(){
    }

    public Erreserba(String kirola, String zelaia, String data, String ordua, String erabiltzailea){
        this.kirola=kirola;
        this.zelaia=zelaia;
        this.data=data;
        this.ordua=ordua;
        this.erabiltzailea=erabiltzailea;
    }

    //Setters
    public void setKirola(String kirola) {
        this.kirola = kirola;
    }
    public void setZelaia(String zelaia) {
        this.zelaia = zelaia;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setOrdua(String ordua) {
        this.ordua = ordua;
    }
    public void setErabiltzailea(String erabiltzailea) {
        this.erabiltzailea = erabiltzailea;
    }

    //Getters

    public String getKirola() {
        return kirola;
    }
    public String getZelaia() {
        return zelaia;
    }
    public String getData() {
        return data;
    }
    public String getOrdua() {
        return ordua;
    }
    public String getErabiltzailea() {
        return erabiltzailea;
    }
}
