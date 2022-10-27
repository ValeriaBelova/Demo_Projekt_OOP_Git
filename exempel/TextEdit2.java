 // Den version som visas i avsnitt 14.8

 import java.awt.*; 
 import java.awt.event.*; 
 import java.io.*; 
 import javax.swing.*;
 import javax.swing.text.*; 

 class TextEdit2 extends JFrame {
   private JTextArea area = new JTextArea(10,60);
   private JFileChooser dialog = 
               new JFileChooser(System.getProperty("user.dir"));
   private String aktuellFil = "namnlös";
   private boolean ändrad = false;

   // Konstruktor
   public TextEdit2() {
     // Lägg textarean i en JScrollPane
     area.setFont(new Font("Monospaced", Font.PLAIN, 12));
     JScrollPane scroll = new JScrollPane(area,
                          JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                          JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
     add(scroll,  BorderLayout.CENTER);
     // Menyraden
     JMenuBar mb = new JMenuBar();
     setJMenuBar(mb);
     JMenu arkiv = new JMenu("Arkiv");
     JMenu redi  = new JMenu("Redigera");
     mb.add(arkiv); mb.add(redi);
     // Arkiv-menyn
     arkiv.add(ny); arkiv.add(öppna); 
     arkiv.add(spara); arkiv.add(sparaSom);
     arkiv.addSeparator(); arkiv.add(avsluta);
     for (int i = 0; i<4; i++) 
       arkiv.getItem(i).setIcon(null);  // inga ikoner på menyn
     // Redigera-menyn
     redi.add(klipp); redi.add(kopiera); redi.add(klistra);
     redi.getItem(0).setText("Klipp ut");
     redi.getItem(1).setText("Kopiera");
     redi.getItem(2).setText("Klistra in");
     // Verktygsfältet
     JToolBar verktyg = new JToolBar();
     add(verktyg, BorderLayout.NORTH);
     verktyg.add(ny); verktyg.add(öppna); 
     verktyg.add(spara); verktyg.addSeparator();
     JButton cut=verktyg.add(klipp), cop=verktyg.add(kopiera),
             pas=verktyg.add(klistra);
     cut.setText(null); cut.setIcon(new ImageIcon("cut.gif"));
     cop.setText(null); cop.setIcon(new ImageIcon("copy.gif"));
     pas.setText(null); pas.setIcon(new ImageIcon("paste.gif"));
     spara.setEnabled(false); 
     sparaSom.setEnabled(false); 
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     pack();
     area.addKeyListener(kl); // för att märka om texten ändrats
     setTitle(aktuellFil);
     setVisible(true);
   }

   // Lyssnare för ändringar i textarean
   private KeyListener kl = new KeyAdapter() {
     // användaren har tryckt på någon tangent i textfönstret
     public void keyPressed(KeyEvent e) {
       ändrad = true;
       spara.setEnabled(true); 
       sparaSom.setEnabled(true);  
     }
   };

   // Definiera handlingarna
   Action ny = new AbstractAction("Ny",
                                  new ImageIcon("new.gif")) { 
     public void actionPerformed(ActionEvent e) {  
       sparaGammal();
       area.setText("");  // töm textarean
       aktuellFil = "namnlös";
       setTitle(aktuellFil);
       ändrad = false;
       spara.setEnabled(false); 
       sparaSom.setEnabled(false); 
     }
   };

   Action öppna = new AbstractAction("Öppna ...", 
                                     new ImageIcon("open.gif")){
     public void actionPerformed(ActionEvent e) {  
       sparaGammal();  // spara den redigerade texten först
       if (dialog.showOpenDialog(null)==
                                  JFileChooser.APPROVE_OPTION) { 
          läsInFil(dialog.getSelectedFile().getAbsolutePath());
       }
       sparaSom.setEnabled(true); 
     }
   };

   Action spara = new AbstractAction("Spara", 
                                     new ImageIcon("save.gif")){ 
     public void actionPerformed(ActionEvent e) {
       if (!aktuellFil.equals("namnlös")) 
         sparaFil(aktuellFil);
       else 
         sparaFilSom(); 
      }
   };

   Action sparaSom = new AbstractAction("Spara som ...") {
     public void actionPerformed(ActionEvent e) {  
       sparaFilSom();
     }
   };

   Action avsluta = new AbstractAction("Avsluta") {
     public void actionPerformed(ActionEvent e) {  
       sparaGammal();   // spara den redigerade texten först
       System.exit(0);       
     }
   };

   ActionMap m = area.getActionMap();
   Action klipp   = m.get(DefaultEditorKit.cutAction);
   Action kopiera = m.get(DefaultEditorKit.copyAction);
   Action klistra = m.get(DefaultEditorKit.pasteAction);
   // Interna hjälpmetoder
   private void sparaFilSom() {
     if (dialog.showSaveDialog(null)==
                                    JFileChooser.APPROVE_OPTION) 
       sparaFil(dialog.getSelectedFile().getAbsolutePath());   
   }

   private void sparaGammal() {  
     if (ändrad) {
       if (JOptionPane.showConfirmDialog
           (this, "Skall filen " + aktuellFil + " sparas?", "", 
           JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
         sparaFil(aktuellFil);
     }
   } 

   private void läsInFil(String filnamn) {
     try { 
        FileReader r = new FileReader(filnamn); 
        area.read(r, null); 
        r.close();
        aktuellFil = filnamn;
        setTitle(aktuellFil);
        ändrad = false;
     } 
     catch (IOException e) {
        Toolkit.getDefaultToolkit().beep();  // ge en ljudsignal
        JOptionPane.showMessageDialog
               (this, "Det går inte att hitta filen " + filnamn);
     }    
   }

   private void sparaFil(String filnamn) {
     try { 
       FileWriter w = new FileWriter(filnamn); 
       area.write(w); 
       w.close();
       aktuellFil = filnamn;
       setTitle(aktuellFil);
       ändrad = false;
       spara.setEnabled(false);
     }
     catch (IOException e) {}
   }

   public static void main (String[] arg) {
     new TextEdit();
   }  
 }


