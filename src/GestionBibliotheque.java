import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GestionBibliotheque extends JFrame implements ActionListener{
    static GestionBibliotheque gBibliotheque;
    private Bibliotheque biblio;
    static  String txtSortie="";
    private JTextArea sortie = new JTextArea(5,120);
    static JButton btnAjouter = new JButton("Ajouter");
    static JButton btnSuprimer = new JButton("Suprimer");
    static JButton btnQuitter = new JButton("Quitter");
    static JPanel paneAffichage = new JPanel();
    static JPanel panePrincipal = new JPanel(new GridBagLayout());
    GestionBibliotheque(Bibliotheque biblio){
        this.biblio = biblio;
        if(biblio instanceof BiblioTab){
            paneAffichage.remove(sortie);  
           
            // sortie.setText("");
            txtSortie = ((BiblioTab) biblio).toString();
            sortie.setText(txtSortie);
            System.out.println(txtSortie);
            setSortie(sortie);
            paneAffichage.add(sortie);
        }
        afficher();
    }
    public void afficher(){
        setTitle("Bibliotheque");
        setPreferredSize(new Dimension(800,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints c = new GridBagConstraints();
        
        paneAffichage.setBackground(Color.white);
        JPanel paneButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
    public void suprimerBilio(){
        int cond = 0;
        int cote = Integer.parseInt(JOptionPane.showInputDialog(null, 
        "Entrez le numero a suprimer "));
        while(cond==0){
            if(biblio.Rechercher(cote)){
                biblio.Suprimer(cote);
                txtSortie = ((BiblioTab) biblio).toString();
                sortie.setText(txtSortie);
                panePrincipal.repaint();
                cond=1;
    
            }else{
                cote = Integer.parseInt(JOptionPane.showInputDialog(null, 
                "Le numero " + cote + " n'existe pas \n Entrez un autre numero a suprimer "));
    
            }
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnQuitter){
            System.exit(0);
        }else if(e.getSource() == btnAjouter){
            
        }else if(e.getSource() == btnSuprimer){
            suprimerBilio();
        }
        
    }

    public static void main(String[] args) throws IOException {
        
        gBibliotheque= new GestionBibliotheque(new BiblioTab());
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