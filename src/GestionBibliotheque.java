import java.io.IOException;

public class GestionBibliotheque {
    public static void main(String[] args) throws IOException {
        BiblioTab biblioTab = new BiblioTab();
        biblioTab.charger();
        //biblioTab.Lister();
        biblioTab.Suprimer(15);
    }
}
