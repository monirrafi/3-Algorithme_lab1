public class CDisque extends Ouvrage{
    private String titreCD, auteurCD;

    public CDisque(String date, int cote, String titreCD, String auteurCD) {
        super(date, cote);
        this.titreCD = titreCD;
        this.auteurCD = auteurCD;
    }

    public CDisque() {
    }

    public String getTitreCD() {
        return titreCD;
    }

    public void setTitreCD(String titreCD) {
        this.titreCD = titreCD;
    }

    public String getAuteurCD() {
        return auteurCD;
    }

    public void setAuteurCD(String auteurCD) {
        this.auteurCD = auteurCD;
    }

    @Override
    public String toString() {

        return  super.toString() +  "\t" + auteurCD + "\t" + titreCD + "\n";
    }

}
