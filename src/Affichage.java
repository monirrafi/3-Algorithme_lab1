import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Affichage extends JFrame {
    Affichage(){
        setTitle("Bibliotheque");
        setPreferredSize(new Dimension(800,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2));
        //GridBagConstraints grille = new GridBagConstraints();
        JPanel paneDispaly = new JPanel();
        paneDispaly.add(new JButton("text"));
        paneDispaly.setBackground(Color.GREEN);
        JPanel paneButton = new JPanel(new GridLayout(5,1));
        paneButton.setBorder(new EmptyBorder(120, 130, 120, 130));
//        grille.anchor = GridBagConstraints.CENTER;
 //       grille.insets = new Insets(10, 10, 10, 10);
        for(int i=0;i<5;i++){
            JButton btn = new JButton("btn");
            btn.setSize(120,20);
            paneButton.add(btn);

        }
/*
//        grille.gridx = 2;
 //       grille.gridy = 1;
        paneButton.add(new JButton("btn2"));
//        grille.gridy = 2;
//        grille.gridx = 2;
        paneButton.add(new JButton("btn3"));
//        grille.gridy = 3;
//        grille.gridx = 1;
paneButton.add(new JButton("btn4"));
//        grille.gridy = 4;
 //       grille.gridx = 1;
 paneButton.add(new JButton("btn5"));
//        grille.gridy = 5;
//        grille.gridx = 1;*/
        add(paneButton);
        add(paneDispaly);
        pack();
        setVisible(true);

    }
    
    
}