
import java.io.IOException;
import java.text.DecimalFormat;
import java.awt.Dimension;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;



public abstract class Bibliotheque implements Serializable{
    static ObjectOutputStream tmpWriteObj;
    static ObjectInputStream tmpReadObj;
    final static String FICHIER_STATISTIQUE_OBJ = "src\\statistiques.obj";

    static HashMap<String,Double> statistiqueMap = new HashMap<>();
    static JTextArea pane = new JTextArea();
    
    public abstract void Ajouter(String typeListe);
    public abstract void Suprimer(int cote);
    public abstract boolean Rechercher(int cote);
    public abstract String toString();
    public abstract void sauvegarder() throws IOException;
    
    public static double supMoyenne(ArrayList<Long> liste){
        double moy=0;
        int taille=liste.size();
        if(taille !=0){
            for(Long temps:liste){
                moy +=temps;
            }
            moy = (moy/taille)/1000;
       }
        return moy;
    }

    public static JTextArea afficherStatistique() {
        String pattern1="###,###.00";    
 
        DecimalFormat df=new DecimalFormat(pattern1);  
        String txtStatistique="";
        
            for(String str:statistiqueMap.keySet()){
                txtStatistique += cadrerMot(String.valueOf(df.format(statistiqueMap.get(str))),32);
            }
            txtStatistique +=   "\n";
        pane.append(txtStatistique);    
        return pane;
    }
    public static String cadrerMot(String mot,int max) {
        String retour;
        int lng = mot.length();
        if(lng>=max){
            retour = mot.substring(0, max);
        }else{
            retour = mot;
            for(int i=0;i<max-lng;i++){
                retour += " ";
            }
        }
        return retour;        
    }
    public static void chargerMap() throws Exception {
        if(statistiqueMap.size()==0){
            pane.setSize(new Dimension(5,300));
            pane.append(cadrerMot("ajout_Tableau",20)+cadrerMot("suprime_Linked",20)
            +cadrerMot("recherche_Linked",20)+cadrerMot("suprime_Tableau",20)+
            cadrerMot("ajout_Linked",20)+cadrerMot("recherche_Tableau",20) + "\n");
        }else{        
        
		try {
			tmpReadObj = new ObjectInputStream (new FileInputStream (FICHIER_STATISTIQUE_OBJ));
			statistiqueMap = (HashMap<String,Double>) tmpReadObj.readObject();
 		}catch(FileNotFoundException e)
		{
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		}catch(IOException e)
		{
			System.out.println("Un probléme est arrivé lors de la manipulation du fichier. V�rifiez vos donn�es.");
		}catch(Exception e)
		{
			System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		}finally
		{// Exécuté si erreur ou pas
			tmpReadObj.close();
		}
    }
        //return statistiquesMap;
	}
    public static HashMap<String, Double> getStatistiqueMap() {
        return statistiqueMap;
    }
    public static void setStatistiqueMap(HashMap<String, Double> statistiquesMap) {
        for(String str:statistiquesMap.keySet()){
            statistiqueMap.put(str,statistiquesMap.get(str));

        }
 //       statistiqueMap = statistiquesMap;
    }
    
    
    
}
