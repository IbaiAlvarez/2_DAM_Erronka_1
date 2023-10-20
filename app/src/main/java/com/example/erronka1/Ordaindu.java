package com.example.erronka1;

import java.util.List;
public class Ordaindu {
    protected String Izen_Abizen;
    protected int txartel_zbk;
    protected int mes;
    protected int año;
    protected  int cvv;

    public String getIzen_Abizen() {
        return Izen_Abizen;
    }

    public int getTxartel_zbk() {
        return txartel_zbk;
    }

    public int getMes() {
        return mes;
    }

    public int getAño() {
        return año;
    }

    public int getCvv() {
        return cvv;
    }

    public void setIzen_Abizen(String izen_Abizen) {
        Izen_Abizen = izen_Abizen;
    }

    public void setTxartel_zbk(int txartel_zbk) {
        this.txartel_zbk = txartel_zbk;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Ordaindu(String izen_Abizen, int txartel_zbk, int mes, int año, int cvv) {
        Izen_Abizen = izen_Abizen;
        this.txartel_zbk = txartel_zbk;
        this.mes = mes;
        this.año = año;
        this.cvv = cvv;
    }
    public Ordaindu(){}
}
