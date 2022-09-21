public class Livre extends Ouvrage{
    private String auteur, titre, editeur ;

    public Livre(String date, int cote, String auteur, String titre, String editeur) {
        super(date, cote);
        this.auteur = auteur;
        this.titre = titre;
        this.editeur = editeur;
    }

    public Livre() {
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }
    
    
}
