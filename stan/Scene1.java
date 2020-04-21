package application;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Scene1 extends JPanel {
    private JButton jcomp1;
    private JButton jcomp2;
    private JButton jcomp3;
    private JLabel jcomp4;

    public Scene1() {
        //construct components
        jcomp1 = new JButton ("File");
        jcomp2 = new JButton ("New");
        jcomp3 = new JButton ("Returning");
        jcomp4 = new JLabel ("Welcome! Returning or new customer?");

        //adjust size and set layout
        setPreferredSize (new Dimension (944, 557));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (100, 165, 140, 30);
        jcomp2.setBounds (100, 115, 140, 30);
        jcomp3.setBounds (100, 70, 140, 30);
        jcomp4.setBounds (70, 5, 230, 55);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Scene1());
        frame.pack();
        frame.setVisible (true);
    }
}
