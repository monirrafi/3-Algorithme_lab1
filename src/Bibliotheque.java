import java.io.IOException;
import java.io.Serializable;


public abstract class Bibliotheque implements Serializable{
    private static final long serialversionUID = 129348938L;
        
    public abstract void Ajouter(String typeListe);
    public abstract void Suprimer(int cote);
    public abstract boolean Rechercher(int cote);
    public abstract String toString();
    public abstract void sauvegarder() throws IOException;
    
}
