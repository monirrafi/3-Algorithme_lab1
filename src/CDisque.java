public class CDisque extends Ouvrage{
    private String titreCD, auteurCD;

    public CDisque(int cote,String date, String titreCD, String auteurCD) {
        super(cote,date);
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

        return  super.toString() +  "\t" + super.envollopeMot(auteurCD,15) + "\t" + super.envollopeMot(titreCD,15) + "\n";
    }

}
