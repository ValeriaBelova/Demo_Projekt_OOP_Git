 import java.awt.*;
 import java.awt.event.*;  // innehåller klasser för lyssnare
 import javax.swing.*;

 public class JButtonDemo extends JFrame 
                          implements ActionListener {
   private JLabel  lab = new JLabel("Welcome!", JLabel.CENTER);
   private JButton exi = new JButton("Cancel");
   private JButton eng = new JButton("In English, please!", 
                                new ImageIcon("flag_eng.gif"));
   private JButton swe = new JButton("På svenska, tack!",   
                                new ImageIcon("flag_swe.gif"));

   public JButtonDemo() { // konstruktor för klassen JButtonDemo
      setLayout(new GridLayout(2,2)); // 2 rader, 2 kolumner
      add(lab); add(exi); add(eng); add(swe);
      getContentPane().setBackground(Color.white); 
      lab.setFont(new Font("SansSerif", Font.ITALIC, 20));
      exi.setFont(new Font("SansSerif", Font.BOLD, 14));
      // koppla lyssnare till knapparna
      exi.addActionListener(this);
      eng.addActionListener(this);
      swe.addActionListener(this);
      eng.setEnabled(false);    // engelska texter visas redan
      setSize(350,100);
      setVisible(true); 
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   // lyssnarmetod
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == exi)
        // användaren klickade på knappen Avsluta (Cancel)
        System.exit(0);
      else if (e.getSource() == eng) {
        // användaren klickade på knappen In English, please!
        lab.setText("Welcome!");
        exi.setText("Cancel");
        eng.setEnabled(false);
        swe.setEnabled(true);
      }
      else if (e.getSource() == swe) {
        // användaren klickad på knappen På svenska, tack!
        lab.setText("Välkommen!");
        exi.setText("Avsluta");
        swe.setEnabled(false);
        eng.setEnabled(true);
      }
   }

   public static void main (String[] arg) {
      JButtonDemo j = new JButtonDemo();
   }
 }
