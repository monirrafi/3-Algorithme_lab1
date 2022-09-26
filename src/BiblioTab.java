import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class BiblioTab extends Bibliotheque{
/*============================================================================================================ */
/*=========================================== Declaration ===================================================== */
/*============================================================================================================ */

    final static int MAX =20;
    final static String FICHIER_TXT = "src\\Biblio.txt";
    final static String FICHIER_TAB_OBJ = "src\\BiblioTab.obj";
    final static String FICHIER_STATISTIQUE_OBJ = "src\\statistiques.obj";
    static BufferedReader tmpBiblio;
    static ObjectOutputStream tmpWriteObj;
    static ObjectInputStream tmpReadObj;
    static long exucuteTime;
/*============================================================================================================ */
/*=========================================== Constructeur ===================================================== */
/*============================================================================================================ */
    
    private Ouvrage[] tabBiblio = new Ouvrage[MAX];
    private int taille=0;
    private ArrayList<Long> suprimeTime = new ArrayList<>();
    private ArrayList<Long> ajoutTime =new ArrayList<>();
    private ArrayList<Long> rechercheTime = new ArrayList<>();

    public BiblioTab() throws Exception {
        this.tabBiblio = charger();
    }
    public BiblioTab(Ouvrage[] tabBiblio) {
        this.setTabBiblio(tabBiblio);
    }
/*============================================================================================================ */
/*=========================================== Chargement ===================================================== */
/*============================================================================================================ */
public Ouvrage[] chargerObj() throws Exception {
		try {
			tmpReadObj = new ObjectInputStream (new FileInputStream (FICHIER_TAB_OBJ));
			tabBiblio = (Ouvrage[]) tmpReadObj.readObject();
            this.setTaille(tabBiblio.length);
    
		}catch(FileNotFoundException e)
		{
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		}catch(IOException e)
		{
			System.out.println("Un probléme est arrivé lors de la manipulation du fichier. V�rifiez vos donn�es.");
            System.out.println(e.getMessage());
		}catch(Exception e)
		{
			System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		}finally
		{// Exécuté si erreur ou pas
			tmpReadObj.close();
		}
        return tabBiblio;
	}
    
    public Ouvrage[] chargerTxt() {
        int r=0;
        try {
            tmpBiblio = new BufferedReader(new FileReader(FICHIER_TXT));
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

    public Ouvrage[] charger() throws Exception {
        
        File file = new File(FICHIER_TAB_OBJ);
        if(file.exists()){
            return chargerObj();
        }else{
            return chargerTxt();
        }

    }
/*============================================================================================================ */
/*=========================================== Sauvegarde ===================================================== */
/*============================================================================================================ */

    public void sauvegarder() throws IOException {
		try {
			tmpWriteObj = new ObjectOutputStream(new FileOutputStream(FICHIER_TAB_OBJ));
			tmpWriteObj.writeObject(tabBiblio);
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		} catch (IOException e) {
			System.out.println("Un probléme est arrivé lors de la manipulation du fichier. V�rifiez vos donn�es.");
		} catch (Exception e) {
			System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		} finally {// Exécuté si erreur ou pas
			tmpWriteObj.close();
		}
 		try {
            HashMap<String,Double> statistiqueMap = new HashMap<>();
            double moySuprime = supMoyenne(suprimeTime);
            double moyAjout = supMoyenne(ajoutTime);
            double moyRecherche = supMoyenne(rechercheTime);
            statistiqueMap.put("suprime_Tableau", moySuprime);
            statistiqueMap.put("ajout_Tableau", moyAjout);
            statistiqueMap.put("recherche_Tableau", moyRecherche);
            statistiqueMap.put("suprime_Linked", 0.0);
            statistiqueMap.put("ajout_Linked", 0.0);
            statistiqueMap.put("recherche_Linked", 0.0);

            super.setStatistiqueMap(statistiqueMap);
            for(String str:super.getStatistiqueMap().keySet()){
                System.out.println(str+"\t"+super.getStatistiqueMap().get(str));
            }
			tmpWriteObj = new ObjectOutputStream(new FileOutputStream(FICHIER_STATISTIQUE_OBJ));
			tmpWriteObj.writeObject(super.getStatistiqueMap());
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		} catch (IOException e) {
			System.out.println("Un probléme est arrivé lors de la manipulation du fichier. V�rifiez vos donn�es.");
		} catch (Exception e) {
			System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		} finally {// Exécuté si erreur ou pas
			tmpWriteObj.close();
		}
	}
/*============================================================================================================ */
/*=========================================== SAR SAR SAR ===================================================== */
/*============================================================================================================ */
    @Override
    public boolean Rechercher(int cote) {
        long startTime = System.nanoTime();

        boolean cond =false;
            for(int i=0;i<taille;i++){
                if(tabBiblio[i].getCote()==cote){
                    cond =true;
                    break;
                }

            }
        long stopTime = System.nanoTime();
        rechercheTime.add(stopTime-startTime);
            return cond;
    }

    @Override
    public void Ajouter(String typeListe) {
        long startTime = System.nanoTime();
        int cote=0;
        int cond =0;
        String strCote = JOptionPane.showInputDialog(null, "Entrez le numero cote");
        if(strCote==null || strCote.equals(" ")){
            cote=0;
        }else{
            cote = Integer.parseInt(strCote);
        }
        while(cond==0){

            if(Rechercher(cote)){
                strCote = JOptionPane.showInputDialog(null, "Le cote " + cote + " existe deja \n Entrez un autre numero cote");
                if(strCote==null || strCote.equals(" ")){
                    cote=0;
                }else{
                    cote = Integer.parseInt(strCote);
                }
                    }else{
                cond=1;
            }  
        }

        if(cote != 0){   
            String date="";
            String  titre="", auteur="",editeur="";
            int numero=0, periodicite=0;
    
            Dimension d =new Dimension(150,20);
            ArrayList<JTextField> listeJtxt = new ArrayList<>();
            ArrayList<String> listeChamps= new ArrayList<String>();
           
            if(typeListe.equals("livre")){
                listeChamps = new ArrayList<String>(){{add("Date");add("Auteur");add("Titre");add("Editeur");}};
            }else if(typeListe.equals("cd")){
                listeChamps = new ArrayList<String>(){{add("Date");add("Auteur");add("Titre");}};
            }else if(typeListe.equals("periodique")){
                listeChamps = new ArrayList<String>(){{add("Date");add("Nom");add("Numero");add("Periodicite");}};
            }    
            
            JPanel gPane = new JPanel(new GridLayout(listeChamps.size(),1));

            for(String str:listeChamps){
                JPanel pane = new JPanel();
                JTextField jtxt = new JTextField();
                jtxt.setPreferredSize(d);
                JLabel lbl = new JLabel(str);
                lbl.setPreferredSize(d);
                lbl.setLabelFor(jtxt);
                listeJtxt.add(jtxt);
                pane.add(lbl);
                pane.add(jtxt);
                gPane.add(pane);

            }
            int res = JOptionPane.showConfirmDialog(null,gPane);
            if(res == JOptionPane.YES_OPTION){
        //        cote = Integer.parseInt(listeJtxt.get(0).getText());
                date = listeJtxt.get(0).getText();
                auteur = listeJtxt.get(1).getText();

                if(typeListe.equals("livre")){
                    titre = listeJtxt.get(2).getText();
                    editeur =listeJtxt.get(3).getText();
                }else if(typeListe.equals("cd")){
                    titre = listeJtxt.get(2).getText();
                }else if(typeListe.equals("periodique")){
                    numero = Integer.parseInt(listeJtxt.get(2).getText());
                    periodicite = Integer.parseInt(listeJtxt.get(3).getText());
                }    
            
                Ouvrage[] tabTemp = new Ouvrage[taille+1]; 
                for(int i=0;i<taille;i++){
                    tabTemp[i]=tabBiblio[i];

                } 
                if(typeListe.equals("livre")){
                    tabTemp[taille]= new Livre(cote,date,auteur,titre,editeur);
                }else if(typeListe.equals("cd")){
                    tabTemp[taille]= new CDisque(cote,date,auteur,titre);
                }else if(typeListe.equals("periodique")){
                    tabTemp[taille]= new Periodique(cote,date,auteur,numero,periodicite);
                }    
                        
                this.setTabBiblio(tabTemp);
                this.setTaille(taille+1);
                        
            }
            
        }
        long stopTime = System.nanoTime();
        ajoutTime.add(stopTime-startTime);
    }
    @Override
    public void Suprimer(int cote) {
        long startTime = System.nanoTime();
        Ouvrage[] tabTemp = new Ouvrage[taille-1];  

            if(cote >= taille){
                for(int index = 0; index < taille-1; index++){
                    tabTemp[index] = tabBiblio[index];
                }
            }
            for (int i = 0; i < taille-1; i++) {
                //taille = taille-1;
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
        long stopTime =System.nanoTime();
        suprimeTime.add(stopTime-startTime);
        this.setTabBiblio(tabTemp);
        this.setTaille(taille-1);

        //Lister();
    }
    @Override
    public String toString() {
        long startTime = System.nanoTime();
        String strLivre="";
        String strPeriodique="";
        String strCD="";
             
        String retour= "";
        retour= "  Le nombre total des ouvrages "+ taille +" par tableau\n";
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
        retour+= "\n  Les CD\n  Cote\tDate\t"+ Ouvrage.envollopeMot("Titre",15)+ Ouvrage.envollopeMot("\tAuteur",15)+"\n"+ strCD;
        //long stopTime =System.nanoTime();
        //super.setExucuteTime(stopTime-startTime);

        return retour;
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
    public long getTime() {
        return exucuteTime;
        
    }

}
