package application;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Scene2 extends JPanel {
    private JLabel welcomeMessage;
    private JTextField nameBox;
    private JLabel sizeInst;
    private JComboBox sizeChoice;
    private JLabel Shirt;
    private JLabel sleevesCoat;
    private JLabel shirtCollar;
    private JLabel shirtFit;
    private JTextField sleevesBox;
    private JTextField collarBox;
    private JTextField fitBox;
    private JLabel pants;
    private JLabel pantsWaist;
    private JLabel pantsInstep;
    private JLabel dress;
    private JLabel dressSize;
    private JLabel shoes;
    private JLabel shoesSize;
    private JLabel coat;
    private JLabel shirtSleeves;
    private JLabel fitCoat;
    private JLabel belt;
    private JLabel widthBelt;
    private JLabel lengthBelt;
    private JTextField waistBox;
    private JTextField sizeBoxDress;
    private JTextField instepBox;
    private JTextField sleevesBoxCoat;
    private JTextField sizeBoxShoes;
    private JTextField fitBoxCoat;
    private JTextField widthBoxBelt;
    private JTextField lengthBoxBelt;

    public Scene2() {
        //construct preComponents
        String[] sizeChoiceItems = {"S", "M", "L", "XL", "XXL"};

        //construct components
        welcomeMessage = new JLabel ("New customer? Please enter your name:");
        nameBox = new JTextField (5);
        sizeInst = new JLabel ("Enter your size here:");
        sizeChoice = new JComboBox (sizeChoiceItems);
        Shirt = new JLabel ("Shirt:");
        sleevesCoat = new JLabel ("Sleeves:");
        shirtCollar = new JLabel ("Collar:");
        shirtFit = new JLabel ("Fit:");
        sleevesBox = new JTextField (5);
        collarBox = new JTextField (5);
        fitBox = new JTextField (5);
        pants = new JLabel ("Pants:");
        pantsWaist = new JLabel ("Waist:");
        pantsInstep = new JLabel ("Instep:");
        dress = new JLabel ("Dress:");
        dressSize = new JLabel ("Size:");
        shoes = new JLabel ("Shoes:");
        shoesSize = new JLabel ("Size:");
        coat = new JLabel ("Coat:");
        shirtSleeves = new JLabel ("Sleeves:");
        fitCoat = new JLabel ("Fit:");
        belt = new JLabel ("Belt:");
        widthBelt = new JLabel ("Width:");
        lengthBelt = new JLabel ("Length:");
        waistBox = new JTextField (5);
        sizeBoxDress = new JTextField (5);
        instepBox = new JTextField (5);
        sleevesBoxCoat = new JTextField (5);
        sizeBoxShoes = new JTextField (5);
        fitBoxCoat = new JTextField (5);
        widthBoxBelt = new JTextField (5);
        lengthBoxBelt = new JTextField (5);

        //adjust size and set layout
        setPreferredSize (new Dimension (944, 557));
        setLayout (null);

        //add components
        add (welcomeMessage);
        add (nameBox);
        add (sizeInst);
        add (sizeChoice);
        add (Shirt);
        add (sleevesCoat);
        add (shirtCollar);
        add (shirtFit);
        add (sleevesBox);
        add (collarBox);
        add (fitBox);
        add (pants);
        add (pantsWaist);
        add (pantsInstep);
        add (dress);
        add (dressSize);
        add (shoes);
        add (shoesSize);
        add (coat);
        add (shirtSleeves);
        add (fitCoat);
        add (belt);
        add (widthBelt);
        add (lengthBelt);
        add (waistBox);
        add (sizeBoxDress);
        add (instepBox);
        add (sleevesBoxCoat);
        add (sizeBoxShoes);
        add (fitBoxCoat);
        add (widthBoxBelt);
        add (lengthBoxBelt);

        //set component bounds (only needed by Absolute Positioning)
        welcomeMessage.setBounds (30, 15, 305, 25);
        nameBox.setBounds (275, 15, 70, 25);
        sizeInst.setBounds (30, 55, 150, 25);
        sizeChoice.setBounds (160, 55, 45, 25);
        Shirt.setBounds (30, 100, 100, 25);
        sleevesCoat.setBounds (275, 255, 100, 25);
        shirtCollar.setBounds (45, 175, 100, 25);
        shirtFit.setBounds (45, 210, 100, 25);
        sleevesBox.setBounds (120, 140, 35, 25);
        collarBox.setBounds (120, 175, 35, 25);
        fitBox.setBounds (120, 210, 35, 25);
        pants.setBounds (30, 355, 100, 25);
        pantsWaist.setBounds (45, 395, 100, 25);
        pantsInstep.setBounds (45, 435, 100, 25);
        dress.setBounds (30, 260, 100, 25);
        dressSize.setBounds (45, 295, 100, 25);
        shoes.setBounds (260, 95, 100, 25);
        shoesSize.setBounds (265, 135, 100, 25);
        coat.setBounds (260, 210, 100, 25);
        shirtSleeves.setBounds (45, 140, 100, 25);
        fitCoat.setBounds (275, 290, 100, 25);
        belt.setBounds (265, 355, 100, 25);
        widthBelt.setBounds (280, 440, 100, 25);
        lengthBelt.setBounds (280, 395, 100, 25);
        waistBox.setBounds (120, 395, 35, 25);
        sizeBoxDress.setBounds (120, 290, 35, 25);
        instepBox.setBounds (120, 440, 35, 25);
        sleevesBoxCoat.setBounds (360, 255, 35, 25);
        sizeBoxShoes.setBounds (330, 135, 40, 25);
        fitBoxCoat.setBounds (360, 290, 35, 25);
        widthBoxBelt.setBounds (345, 435, 35, 25);
        lengthBoxBelt.setBounds (345, 395, 35, 25);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Scene2());
        frame.pack();
        frame.setVisible (true);
    }
}
