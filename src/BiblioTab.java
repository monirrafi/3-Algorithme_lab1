import java.io.*;
import java.util.Arrays;
import javax.swing.*;



public class BiblioTab extends Bibliotheque{
    final static int MAX =20;
    final static String fichier = "src\\Biblio.txt";
    
    private Ouvrage[] tabBiblio = new Ouvrage[MAX];
    static BufferedReader tmpBiblio; 
    private int taille=0;
    public BiblioTab() {
        this.tabBiblio = charger();
    }
    public BiblioTab(Ouvrage[] tabBiblio) {
        this.setTabBiblio(tabBiblio);
    }
    
   
    public Ouvrage[] charger() {
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
        
        e.printStackTrace();
    }
    
    taille = r;
    return tabBiblio;

    }
    
    public void Lister(){
        JTextArea sortie = new JTextArea(20,70);
        sortie.append(toString());
        JOptionPane.showMessageDialog(null, sortie);
    }

    @Override
    public void Ajouter() {
        
        int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le numero "));

        int cond =0;
        while(cond==0){
            if(Rechercher(numero)){
                numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero existe \n Entrez un autre numero "));

            }else{
                Ouvrage[] tabTemp = new Ouvrage[taille+1]; 
                for(int i=0;i<taille;i++){
                    tabTemp[i]=tabBiblio[i];

                } 
                tabTemp[taille]= new CDisque(numero,"21/09/2022","father","Yousef Islam");
                this.setTabBiblio(tabTemp);
                this.setTaille(taille+1);
        
                Lister();
                cond=1;
            }
        }
        
    }

    @Override
    public void Suprimer(int cote) {
        Ouvrage[] tabTemp = new Ouvrage[taille-1];  
        for (int i = 0; i < taille-1; i++) {
            if(tabBiblio[i].getCote() ==cote){
                tabTemp = new Ouvrage[taille - 1];
                for(int index = 0; index < i; index++){
                    tabTemp[index] = tabBiblio[index];
                }
                for(int j = i; j < taille-1; j++){
                    tabTemp[j] = tabBiblio[j+1];
                }
                break;
            }
        }   
        this.setTabBiblio(tabTemp);
        this.setTaille(taille-1);

        
    }
    @Override
    public boolean Rechercher(int cote) {
        boolean cond =false;
        
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
             
        String retour= "  Le nombre total des ouvrages "+ taille +"\n";
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
        retour+= "\n  Les CD\n  Cote\tDate\t"+ Ouvrage.envollopeMot("Auteur",15)+ Ouvrage.envollopeMot("\ttitre",15)+"\n"+ strCD;
        return retour;
        
        //return Arrays.toString(tabBiblio);
    }


    public Ouvrage[] getTabBiblio() {
        return tabBiblio;
    }
    public void setTabBiblio(Ouvrage[] tabBiblio) {
        this.tabBiblio = tabBiblio;
    }
    public int getTaille() {
        return taille;
    }
    public void setTaille(int taille) {
        this.taille = taille;
    }
    
    
}
