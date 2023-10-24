package Model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Zelaia implements Serializable {
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


    @Override
    public boolean equals(Object o) {
        Zelaia zelai = (Zelaia) o;
        if (this.zelai_izena.equals(zelai.zelai_izena)){
            if(zelai.erreserbak==null && this.erreserbak==null){
                return true;
            }
           else if(zelai.erreserbak.equals(this.erreserbak)){
                if(this.orduak.equals(zelai.orduak)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zelai_izena, erreserbak, orduak);
    }
}
