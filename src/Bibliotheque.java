
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
    static JTextPane pane = new JTextPane();
    
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

    public static JTextPane afficherStatistique() {
        pane.setSize(new Dimension(5,150));
        String pattern1="###,###.00";    // pattern according to which number will be format
 
        DecimalFormat df=new DecimalFormat(pattern1);  
        String txtStatistique="";
        //pane.setText(txtStatistique);
//        if(statistiqueMap.size()==0){
//            txtStatistique="ajout_Tableau\tsuprime_Tableau\trecherche_Tableau\tsuprime_Linked\tajout_Linked\t\trecherche_Linked\n";
//        }else{    
//            for(String str:statistiqueMap.keySet()){
//                txtStatistique += str + "\t";
//            }
            txtStatistique +=   "\n";
            for(String str:statistiqueMap.keySet()){
                txtStatistique += String.valueOf(df.format(statistiqueMap.get(str))) + "\t\t";
            }
            txtStatistique +=   "\n";
//        }
        Document doc = pane.getDocument();
        try {
            doc.insertString(doc.getLength(), txtStatistique, null);
        } catch (BadLocationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //pane.setText(pane.getText() + txtStatistique);
        return pane;
    }
    public static void chargerMap() throws Exception {
        statistiqueMap.put("suprime_Tableau", 0.0);
        statistiqueMap.put("ajout_Tableau", 0.0);
        statistiqueMap.put("recherche_Tableau", 0.0);
        statistiqueMap.put("suprime_Linked", 0.0);
        statistiqueMap.put("ajout_Linked", 0.0);
        statistiqueMap.put("recherche_Linked", 0.0);

        
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
