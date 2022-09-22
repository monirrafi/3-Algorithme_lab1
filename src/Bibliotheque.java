import java.util.ArrayList;

public abstract class Bibliotheque {
    protected ArrayList<Ouvrage> biblio;
    
    public abstract void Ajouter();
    public abstract void Suprimer(int cote);
    public abstract boolean Rechercher(int cote);
    public abstract String toString();
    
}
