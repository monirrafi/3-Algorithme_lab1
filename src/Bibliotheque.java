
import java.io.IOException;
import java.io.Serializable;



public abstract class Bibliotheque implements Serializable{
    protected long exucuteTime;
    public abstract void Ajouter(String typeListe);
    public abstract void Suprimer(int cote);
    public abstract boolean Rechercher(int cote);
    public abstract String toString();
    public abstract void sauvegarder() throws IOException;
    
    public long getExucuteTime() {
        return exucuteTime;
    }
    public void setExucuteTime(long exucuteTime) {
        this.exucuteTime = exucuteTime;
    }
    
    
}
