import java.io.*;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

public class GestionBibliotheque extends JFrame implements actionEcouteur{
    static GestionBibliotheque gBibliotheque;
    private Bibliotheque biblio;
    private String txtSortie="";
    private String txtSortieMenu="";
    //private JTextArea sortie = new JTextArea(5,120);
    private JTextPane sortie = new JTextPane();
    private JTextArea sortieMenu = new JTextArea();

    //static JButton btnAjouter = new JButton("Ajouter");
    static JButton btnSuprimer= new JButton("Suprimer");
    static JButton btnQuitter = new JButton("Quitter");
    static JButton btnMenu= new JButton("Retour au menu");
    static JButton btnBiblioTab = new JButton("Biblio tableau");
    static JButton btnBiblioLinked = new JButton("Biblio linked");
    static JButton btnBiblioPer = new JButton("Biblio Personnel");
    static JButton btnMAJ = new JButton("Mise a jour ");
    static JTextPane paneStatistiques;
    
    static JLabel lblTimerTab = new JLabel("le temps de tableau est  0");
    static JLabel lblTimerLink = new JLabel("le temps de link est  0");
    
    static JPanel paneAffichage = new JPanel();
    static JPanel panePrincipal = new JPanel();
    /*
     * Creer un radio bouton
     * 
     */
    static JRadioButton vide = new JRadioButton();
    static JRadioButton livre = new JRadioButton();
    static JRadioButton cd=new JRadioButton();
    static JRadioButton periodique = new JRadioButton();
    JLabel lblChoix = new JLabel("      Choisir un ouvrage pour ajouter");
    
    
    GestionBibliotheque() {
        menu();
        cliquer();
    }
    private void changerEntete(JTextPane tp, String msg, Color c)
        {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Background, c);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
        }
    public void changerSortie(){
        txtSortie = "";
        txtSortie = biblio.toString();
        sortie = new JTextPane();
        sortie.setSize(new Dimension(5,120));
        changerEntete(sortie, txtSortie.substring(0,txtSortie.indexOf("\n")), Color.YELLOW);
        changerEntete(sortie, txtSortie.substring(txtSortie.indexOf("\n")), Color.WHITE);

    }
    GestionBibliotheque(Bibliotheque biblio){
        
        if(biblio instanceof BiblioTab){
            this.biblio = (BiblioTab) biblio;
            //k=((BiblioTab) biblio).getTime();
        }else if(biblio instanceof BiblioLink){
            this.biblio = (BiblioLink) biblio;
        }
        changerSortie();
        sortie.setText(txtSortie);
        afficher();
        cliquer();
    }
    public void menu(){
        try {
            Bibliotheque.chargerMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Bibliotheque");
        setPreferredSize(new Dimension(1500,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panePrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        paneAffichage = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paneAffichage.setBackground(Color.white);
        //sortieMenu = new JTextArea();
        //txtSortieMenu = "";
        txtSortieMenu = sortieMenu.getText();
        sortieMenu = new JTextArea();
        sortieMenu.setSize(new Dimension(5,300));
        sortieMenu.append(Bibliotheque.cadrerMot("suprime_Tableau",20)+Bibliotheque.cadrerMot("ajout_Tableau",20)
            +Bibliotheque.cadrerMot("recherche_Tableau",20)+Bibliotheque.cadrerMot("suprime_Linked",20)+
            Bibliotheque.cadrerMot("ajout_Linked",20)+Bibliotheque.cadrerMot("recherche_Linked",20) + "\n");
        sortieMenu.append(txtSortieMenu);
        sortieMenu.append(Bibliotheque.afficherStatistique());
        paneAffichage.add(sortieMenu);

        JPanel paneButton = new JPanel(new FlowLayout(FlowLayout.CENTER,15,35));
        paneButton.setBackground(new Color(51,104,255));
        btnBiblioTab = new JButton("Biblio tableau");
        btnBiblioLinked = new JButton("Biblio linked");
        btnBiblioPer = new JButton("Biblio Personnel");

        paneButton.add(btnMAJ);
        paneButton.add(btnBiblioTab);
        paneButton.add(btnBiblioLinked);
        paneButton.add(btnBiblioPer);
        paneButton.add(btnQuitter);
        //dispaly des panels
        gc.ipadx = 1700;      
        gc.ipady = 450;      
        gc.weightx = 0.0;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth=1;
        panePrincipal.add(paneAffichage,gc);

        //c.fill = GridBagConstraints.HORIZONTAL;
        gc.ipadx = 1500;      
        gc.ipady = 150;      
        gc.weightx = 0.0;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth=2;
        panePrincipal.add(paneButton,gc);
    
        add(panePrincipal);
        pack();
        setVisible(true);
        
    }
    public void afficher(){
        setTitle("Bibliotheque");
        setPreferredSize(new Dimension(800,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints c = new GridBagConstraints();

        panePrincipal = new JPanel(new GridBagLayout());
        paneAffichage = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paneAffichage.setBackground(Color.white);
        changerSortie();
        paneAffichage.add(sortie);


        JPanel paneRadio = new JPanel(new GridLayout(2,1));
        paneRadio.setBackground(Color.lightGray);
        JPanel paneElementradio = new JPanel();
        paneElementradio.setBackground(Color.lightGray);
        vide = new JRadioButton("");
        livre = new JRadioButton("Livre");
        cd = new JRadioButton("CD");
        periodique = new JRadioButton("Periodique");
    
        vide.setBackground(Color.lightGray);
        livre.setBackground(Color.lightGray);
        cd.setBackground(Color.lightGray);
        periodique.setBackground(Color.lightGray);
        

        ButtonGroup groupeWeb = new ButtonGroup();
        groupeWeb.add(vide);
        groupeWeb.add(livre);
        groupeWeb.add(cd);
        groupeWeb.add(periodique);
        vide.setSelected(true);
        paneElementradio.add(vide);
        paneElementradio.add(livre);
        paneElementradio.add(cd);
        paneElementradio.add(periodique);

        paneRadio.add(lblChoix);
        paneRadio.add(paneElementradio);

        JPanel paneButton = new JPanel(new FlowLayout(FlowLayout.LEFT,40,20));
        paneButton.setBackground(new Color(51,204,255));

        btnSuprimer = new JButton("Suprimer");
        btnMenu = new JButton("Retour au Menu");   
        JPanel paneTimer = new JPanel(new GridLayout(2,1));
        paneTimer.setBackground(new Color(51,204,255));
        paneTimer.add(lblTimerTab);     
        paneTimer.add(lblTimerLink);     
        paneButton.add(paneTimer);
        paneButton.add(paneRadio);
        paneButton.add(btnSuprimer);
        paneButton.add(btnMenu);

        //dispaly des panels
        c.ipadx = 1500;      
        c.ipady = 150;      
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth=1;
        panePrincipal.add(paneAffichage,c);

        //c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 800;      
        c.ipady = 50;      
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth=2;
        panePrincipal.add(paneButton,c);
        add(panePrincipal);
        pack();
        setVisible(true);

    }
    public void suprimerBilio(){
        int cote=0,cond = 0;
        
        String strCote = JOptionPane.showInputDialog(null, 
        "Entrez le numero a suprimer ");
 
        if(strCote==null || strCote.equals(" ")){
            cote=0;
        }else{
            cote = Integer.parseInt(strCote);
        }
        while(cond==0){
                if(cote==0){
                    cond=1;
                }else if(biblio.Rechercher(cote)){
                    biblio.Suprimer(cote);
                    txtSortie = biblio.toString();
                    //changerSortie();
                    sortie.setText(txtSortie);
                    panePrincipal.repaint();
                    cond=1;
        
                }else{
                    strCote = JOptionPane.showInputDialog(null, 
                    "Le numero " + cote + " n'existe pas \n Entrez un autre numero a suprimer ");
            
                    if(strCote==null || strCote.equals(" ")){
                        cond=1;
                    }else{
                        cote = Integer.parseInt(strCote);
                    }
        
                }
            }
     }
    public void ajouterBiblio(String typeOuvrage) {
        
            biblio.Ajouter(typeOuvrage);
            txtSortie = biblio.toString();
            sortie.setText(txtSortie);
            panePrincipal.repaint();
            vide.setSelected(true);
    }
        
    
    public void actionBouton(ActionEvent e) {
        if(e.getSource() == btnQuitter){
                System.exit(0);
        }else if(e.getSource() == btnSuprimer){   
            suprimerBilio();
           
        }else if(e.getSource() == livre){
            ajouterBiblio("livre");
        }else if(e.getSource() == cd){
            ajouterBiblio("cd");
        }else if(e.getSource() == periodique){
            ajouterBiblio("periodique");
        }else if(e.getSource() == btnBiblioLinked){
            try {
                biblio = new BiblioLink();
                gBibliotheque = new GestionBibliotheque(biblio);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(e.getSource() == btnBiblioPer){
//            ajouterBiblio("periodique");
        }else if(e.getSource() == btnBiblioTab){
            try {
                biblio = new BiblioTab();
                gBibliotheque = new GestionBibliotheque(biblio);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }else if(e.getSource() == btnMenu){
            try {
                biblio.sauvegarder();
                gBibliotheque = new GestionBibliotheque();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }else if(e.getSource() == btnMAJ){
            if(biblio instanceof BiblioTab){
                ((BiblioTab) biblio).remplirMap();

            }else if(biblio instanceof BiblioLink){
                ((BiblioLink) biblio).remplirMap();

            }
            txtSortieMenu = sortieMenu.getText();
            sortieMenu = new JTextArea();
            sortieMenu.setSize(new Dimension(5,300));
            sortieMenu.append(Bibliotheque.cadrerMot("suprime_Tableau",20)+Bibliotheque.cadrerMot("ajout_Tableau",20)
                +Bibliotheque.cadrerMot("recherche_Tableau",20)+Bibliotheque.cadrerMot("suprime_Linked",20)+
                Bibliotheque.cadrerMot("ajout_Linked",20)+Bibliotheque.cadrerMot("recherche_Linked",20) + "\n");
            sortieMenu.append(txtSortieMenu);
            String str = Bibliotheque.afficherStatistique();
            sortieMenu.append(str);
            panePrincipal.repaint();
    
        }    
        
    }

    public static void main(String[] args) throws Exception {
        
        gBibliotheque = new GestionBibliotheque();
    }
    public Bibliotheque getBiblio() {
        return biblio;
    }
    public void setBiblio(Bibliotheque biblio) {
        this.biblio = biblio;
    }
    public JTextPane getSortie() {
        return sortie;
    }
    public void setSortie(JTextPane sortie) {
        this.sortie = sortie;
    }

    @Override
    public void cliquer() {
        btnBiblioTab.addActionListener(this::actionBouton);
        btnBiblioLinked.addActionListener(this::actionBouton);
        btnBiblioPer.addActionListener(this::actionBouton);
        btnQuitter.addActionListener(this::actionBouton);
        livre.addActionListener(this::actionBouton);
        cd.addActionListener(this::actionBouton);
        periodique.addActionListener(this::actionBouton);
        btnMenu.addActionListener(this::actionBouton);
        btnSuprimer.addActionListener(this::actionBouton);
        btnMAJ.addActionListener(this::actionBouton);
        
        
    }

}