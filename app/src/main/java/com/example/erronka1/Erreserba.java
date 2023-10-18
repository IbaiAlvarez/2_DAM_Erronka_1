package com.example.erronka1;

import java.io.Serializable;

public class Erreserba implements Serializable {

    protected String data;
    protected String ordua;
    protected String erabiltzailea;

    public Erreserba(){
    }

    public Erreserba(String data, String ordua, String erabiltzailea){
        this.data=data;
        this.ordua=ordua;
        this.erabiltzailea=erabiltzailea;
    }

    //Setters
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
