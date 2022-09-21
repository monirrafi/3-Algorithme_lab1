import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;

public class Affichage extends JFrame {
    Affichage(){
        setTitle("Bibliotheque");
        setPreferredSize(new Dimension(800,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        pack();
        setVisible(true);
        add(new JButton("btn1"));

        add(new JButton("btn2"));
        add(new JButton("btn3"));
        add(new JButton("btn4"));
        add(new JButton("btn5"));


    }
    
    
}