import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GestionBibliotheque extends JFrame implements actionEcouteur{
    static GestionBibliotheque gBibliotheque;
    private Bibliotheque biblio;
    private String txtSortie="";
    private JTextArea sortie = new JTextArea(5,120);

    //static JButton btnAjouter = new JButton("Ajouter");
    static JButton btnSuprimer;
    static JButton btnQuitter = new JButton("Quitter");
    static JButton btnMenu;
    static JButton btnBiblioTab = new JButton("Biblio tableau");
    static JButton btnBiblioLinked = new JButton("Biblio linked");
    static JButton btnBiblioPer = new JButton("Biblio Personnel");
    
    static JPanel paneAffichage = new JPanel();
    static JPanel panePrincipal = new JPanel(new GridBagLayout());
    /*
     * Creer un radio bouton
     * 
     */
    static JRadioButton vide;
    static JRadioButton livre;
    static JRadioButton cd;
    static JRadioButton periodique;
    JLabel lblChoix = new JLabel("      Choisir un ouvrage pour ajouter");
    
    
    GestionBibliotheque(){
        menu();
        cliquer();
    }

    GestionBibliotheque(Bibliotheque biblio){
        
        if(biblio instanceof BiblioTab){
            this.biblio = (BiblioTab) biblio;
        }else if(biblio instanceof BiblioLink){
            this.biblio = (BiblioLink) biblio;
        }
        txtSortie = biblio.toString();
        sortie.setText(txtSortie);
        setSortie(sortie);
        afficher();
        cliquer();
    }
    public void menu() {
        setTitle("Bibliotheque");
        setPreferredSize(new Dimension(800,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel paneMenu = new JPanel();
        btnBiblioTab = new JButton("Biblio tableau");
        btnBiblioLinked = new JButton("Biblio linked");
        btnBiblioPer = new JButton("Biblio Personnel");

        paneMenu.add(btnBiblioTab);
        paneMenu.add(btnBiblioLinked);
        paneMenu.add(btnBiblioPer);
        paneMenu.add(btnQuitter);
    
        add(paneMenu);
        pack();
        setVisible(true);
        
    }
    public void afficher(){
        setTitle("Bibliotheque");
        setPreferredSize(new Dimension(800,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints c = new GridBagConstraints();

        
        paneAffichage.setBackground(Color.white);
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
        c.ipadx = 800;      //make this component tall
        c.ipady = 50;      //make this component tall
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
                gBibliotheque = new GestionBibliotheque(new BiblioLink());
                txtSortie = biblio.toString();
                sortie.setText(txtSortie);
                panePrincipal.repaint();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else if(e.getSource() == btnBiblioPer){
//            ajouterBiblio("periodique");
        }else if(e.getSource() == btnBiblioTab){
            try {
                gBibliotheque = new GestionBibliotheque(new BiblioTab());
                txtSortie = biblio.toString();
                sortie.setText(txtSortie);
                panePrincipal.repaint();
                //gBibliotheque.repaint();
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
    public JTextArea getSortie() {
        return sortie;
    }
    public void setSortie(JTextArea sortie) {
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
        
    }

}