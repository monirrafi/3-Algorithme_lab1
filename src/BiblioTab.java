import java.io.*;
import javax.swing.*;



public class BiblioTab extends Bibliotheque{
    final static int MAX =20;
    final static String fichier = "src\\Biblio.txt";
    static Ouvrage[] tabBiblio = new Ouvrage[MAX];
    static BufferedReader tmpBiblio; 

    public int charger() {
        int r=0;
        try {
            tmpBiblio = new BufferedReader(new FileReader(new File(fichier)));
        String ligne = tmpBiblio.readLine();
        String[] elt = new String[6];
        int i=0;
        while(i<MAX && ligne != null){
            elt = ligne.split(";");
            if(elt[0].equalsIgnoreCase("L") ){
                tabBiblio[i]= new Livre(Integer.parseInt(elt[1]),elt[2],elt[3],elt[4],elt[5]);

            }else if(elt[0].equalsIgnoreCase("P") ){
                tabBiblio[i]= new Periodique(Integer.parseInt(elt[1]),elt[2],elt[3],Integer.parseInt(elt[4]),
                                             Integer.parseInt(elt[5]));
                
            }else if(elt[0].equalsIgnoreCase("C") ){
                tabBiblio[i]= new CDisque(Integer.parseInt(elt[1]),elt[2],elt[3],elt[4]);
                
            }
                ligne = tmpBiblio.readLine();
            i++;
            r=i;
        }
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return r;

    }
    public void Lister(){
        JTextArea sortie = new JTextArea(20,70);
        sortie.append(toString());
        JOptionPane.showMessageDialog(null, sortie);
    }

    @Override
    public void Ajouter() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void Suprimer(int cote) {
        

    }

    @Override
    public boolean Rechercher(int cote) {
        boolean cond =false;
        int taille = charger();
        if(cote<=taille){
            for(Ouvrage ouvrage:tabBiblio){
                if(ouvrage.getCote()==cote){
                    cond =true;
                    break;
                }

            }
        }
        return cond;
    }

    @Override
    public String toString() {
        String strLivre="";
        String strPeriodique="";
        String strCD="";
        String retour= "  Le nombre total des ouvrages "+ String.valueOf(charger())+"\n";
        for(Ouvrage ouvrage:tabBiblio){
            if(ouvrage instanceof Livre){
                strLivre += ((Livre) ouvrage).toString();
            }else if(ouvrage instanceof Periodique){
                strPeriodique += ((Periodique) ouvrage).toString();
            }else if(ouvrage instanceof CDisque){
                strCD += ((CDisque) ouvrage).toString();
            }
        }
        retour+= "\n  Les Livres\n  Cote\tDate\t"+ Ouvrage.envollopeMot("Auteur",15)+ Ouvrage.envollopeMot("\ttitre",15) + Ouvrage.envollopeMot("\tEditeur",15)+"\n"+ strLivre;
        retour+= "\n  Les periodiques\n  Cote\tDate\t"+ Ouvrage.envollopeMot("Nom",15)+"\tNumero\tPeriodicite\n"+ strPeriodique;
        retour+= "\n  Les CD\n  Cote\tDate\t"+ Ouvrage.envollopeMot("titre",15)+ Ouvrage.envollopeMot("\tAuteur",15)+"\n"+ strCD;
        return retour;
    }
    
    
}
