package com.example.erronka1;


import java.util.List;

public class Kirola {
    protected  String kirol_mota;
    protected List<Zelaia> zelaiak;

    public Kirola(){
    }

    public Kirola(String kirol_mota, List<Zelaia> zelaiak){
        this.kirol_mota=kirol_mota;
        this.zelaiak=zelaiak;
    }

    //Setters
    public void setKirol_mota(String kirol_mota) {
        this.kirol_mota = kirol_mota;
    }
    public void setZelaiak(List<Zelaia> zelaiak) {
        this.zelaiak = zelaiak;
    }

    //Getters
    public String getKirol_mota() {
        return kirol_mota;
    }
    public List<Zelaia> getZelaiak() {
        return zelaiak;
    }
}
