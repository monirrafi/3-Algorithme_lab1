import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GestionBibliotheque extends JFrame implements ActionListener{
    //static GestionBibliotheque gBibliotheque;
    private Bibliotheque biblio;
    private String txtSortie="";
    private JTextArea sortie = new JTextArea(5,120);
    
    static JButton btnAjouter = new JButton("Ajouter");
    static JButton btnSuprimer = new JButton("Suprimer");
    static JButton btnQuitter = new JButton("Quitter");
    static JPanel paneAffichage = new JPanel();
    static JPanel panePrincipal = new JPanel(new GridBagLayout());
    /*
     * Creer un radio bouton
     * 
     */
    static JRadioButton vide = new JRadioButton("");
    static JRadioButton livre = new JRadioButton("Livre");
    static JRadioButton cd = new JRadioButton("CD");
    static JRadioButton periodique = new JRadioButton("Periodique");
    JLabel lblChoix = new JLabel("      Choisir un ouvrage pour ajouter");
    
    


    GestionBibliotheque(Bibliotheque biblio){
        
        if(biblio instanceof BiblioTab){
            this.biblio = (BiblioTab) biblio;
        }
        txtSortie = biblio.toString();
        sortie.setText(txtSortie);
        setSortie(sortie);
        afficher();
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
        livre.addActionListener(this);
        cd.addActionListener(this);
        periodique.addActionListener(this);

        paneRadio.add(lblChoix);
        paneRadio.add(paneElementradio);

        JPanel paneButton = new JPanel(new FlowLayout(FlowLayout.LEFT,40,20));
        paneButton.setBackground(new Color(51,204,255));

        btnQuitter.addActionListener(this);
        //btnAjouter.addActionListener(this);
        btnSuprimer.addActionListener(this);
        paneButton.add(paneRadio);
        //paneButton.add(btnAjouter);
        paneButton.add(btnSuprimer);
        paneButton.add(btnQuitter);

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
        int cond = 0;
        int cote = Integer.parseInt(JOptionPane.showInputDialog(null, 
        "Entrez le numero a suprimer "));
        while(cond==0){
            if(biblio.Rechercher(cote)){
                biblio.Suprimer(cote);
                txtSortie = biblio.toString();
                sortie.setText(txtSortie);
                panePrincipal.repaint();
                cond=1;
    
            }else{
                cote = Integer.parseInt(JOptionPane.showInputDialog(null, 
                "Le numero " + cote + " n'existe pas \n Entrez un autre numero a suprimer "));
    
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
        
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnQuitter){
            try {
                biblio.sauvegarder();
                System.exit(0);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else if(e.getSource() == btnSuprimer){
            suprimerBilio();
        }else if(e.getSource() == livre){
            ajouterBiblio("livre");
        }else if(e.getSource() == cd){
            ajouterBiblio("cd");
        }else if(e.getSource() == periodique){
            ajouterBiblio("periodique");
        }
        
    }

    public static void main(String[] args) throws Exception {
        
    new GestionBibliotheque(new BiblioTab());
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

}