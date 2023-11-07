package Model;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Kirola implements Serializable {
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


    @Override
    public boolean equals(Object o) {
        Kirola kirol = (Kirola) o;
        if(this.kirol_mota.equals(kirol.kirol_mota)){
            if (this.zelaiak.equals(kirol.zelaiak)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kirol_mota, zelaiak);
    }
}
