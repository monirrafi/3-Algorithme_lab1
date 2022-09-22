import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GestionBibliotheque extends JFrame implements ActionListener{
    private Bibliotheque biblio;
    static JTextArea sortie = new JTextArea(5,120);
    static JButton btnAjouter = new JButton("Ajouter");
    static JButton btnSuprimer = new JButton("Suprimer");
    static JButton btnQuitter = new JButton("Quitter");
    static JPanel paneAffichage = new JPanel();
GestionBibliotheque(Bibliotheque biblio){
        this.biblio = biblio;
        if(biblio instanceof BiblioTab){
            sortie.append(((BiblioTab) biblio).toString());
        }
        setTitle("Bibliotheque");
        setPreferredSize(new Dimension(800,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);
        GridBagConstraints c = new GridBagConstraints();
        JPanel panePrincipal = new JPanel(new GridBagLayout());
        
        paneAffichage.setBackground(Color.white);
        paneAffichage.add(sortie);
        JPanel paneButton = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //JButton btnAjouter = new JButton("Ajouter");
        //JButton btnSuprimer = new JButton("Suprimer");
        //JButton btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(this);
        btnAjouter.addActionListener(this);
        btnSuprimer.addActionListener(this);
        paneButton.add(btnAjouter);
        paneButton.add(btnSuprimer);
        paneButton.add(btnQuitter);
        c.ipadx = 1500;      //make this component tall
        c.ipady = 150;      //make this component tall
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

    public static void main(String[] args) throws IOException {
        
        new GestionBibliotheque(new BiblioTab());
        /*

        BiblioTab biblioTab = new BiblioTab();
        biblioTab.Lister();
        int c = 2;
        if(biblioTab.Rechercher(c)){
            biblioTab.Suprimer(c);
            biblioTab.Lister();
        }else{
            System.out.println(c + " n existe pas");
        }
        biblioTab.Ajouter();
        new Affichage();*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnQuitter){
            System.exit(0);
        }else if(e.getSource() == btnAjouter){
            paneAffichage.setVisible(false);
            biblio.Suprimer(3);
            paneAffichage.setVisible(true);
        }else if(e.getSource() == btnSuprimer){
            System.exit(0);
        }
        
    }
}