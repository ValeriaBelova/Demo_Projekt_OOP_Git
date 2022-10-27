 import java.awt.*; 
 import java.awt.event.*; 
 import javax.swing.*; 

 public class Tennis extends JFrame implements ActionListener {
   private Spelplan plan = new Spelplan(); 
   private JLabel poäng1 = new JLabel("0", JLabel.CENTER); 
   private JLabel poäng2 = new JLabel("0", JLabel.CENTER);
   private JPanel pan    = new JPanel(); 
   private JButton[] b   = new JButton[4]; 
   private String[] s ={"Nytt spel","Paus","Fortsätt","Avsluta"};

   public Tennis() { 
     setTitle("Tennis"); 
     plan.setPreferredSize(new Dimension(350,250));
     plan.setBackground(Color.white); 
     poäng1.setFont(new Font("SansSerif", Font.BOLD, 24)); 
     poäng2.setFont(new Font("SansSerif", Font.BOLD, 24)); 
     pan.setLayout(new FlowLayout()); 
     for (int i=0; i<b.length; i++) { 
       b[i] = new JButton(); 
       b[i].setText(s[i]);  
       b[i].addActionListener(this); 
       pan.add(b[i]); 
     } 
     add(plan, BorderLayout.CENTER); 
     add(poäng1, BorderLayout.WEST); 
     add(poäng2, BorderLayout.EAST); 
     add(pan, BorderLayout.SOUTH);
     pack(); 
     plan.init(poäng1, poäng2); 
     setVisible(true); 
   } 

   public void actionPerformed(ActionEvent e) { 
     plan.requestFocus(); 
     if (e.getSource() == b[0]) 
       plan.nyttSpel(); 
     else if (e.getSource() == b[1])   
       plan.stoppaSpel(); 
     else if (e.getSource() == b[2]) 
       plan.startaSpel(); 
     else if (e.getSource() == b[3]) 
       System.exit(0); 
   } 

   public static void main(String[] arg) { 
     Tennis s = new Tennis(); 
   } 
 }

 class Spelplan extends JPanel implements ActionListener { 
   private Timer tim = new Timer(100, this); 
   private JLabel poäng1, poäng2; // för att visa poäng 
   private int p1, p2;            // aktuella poäng 
   private int xMax, yMax;        // högsta x- och y-koordinat 
   private int r, x0, y0;         // bollens radie och mittpunkt 
   private int xSteg, ySteg;      // bollens steglängd 
   private int v, v0 = 5;         // bollens hastighet 
   private int rVä, rHö,          // rackets övre kant
               rL, rSteg;         // rackets längd och steglängd

   public void init(JLabel l1, JLabel l2) { 
     poäng1 = l1; poäng2 = l2; 
     xMax = getSize().width-1; 
     yMax = getSize().height-1; 
     r  = yMax/20;   // beräkna bollens radie 
     rL = 3*r;       // beräkna rackets längd 
     rSteg = r;      // beräkna rackets steglängd 
     addKeyListener(kl);        // lyssna på tangentbordet och 
     addComponentListener(cl);  // ändringar av planens storlek 
     nollställ();  
   } 

   private void nollställ() { 
     p1 = p2 = 0;  // nollställ poäng 
     poäng1.setText(" 0 "); poäng2.setText(" 0 "); 
     xSteg = ySteg = v = v0 = 5; // utgångshastighet 
     x0 = r + 1;                 // sätt bollen i vänsterkanten 
     y0 = yMax/2;                // i mitten av kortsidan 
     rVä = rHö = yMax/2-rL/2;    // placera racketarna i mitten 
   } 

   public void startaSpel() { 
     tim.start(); 
   } 

   public void stoppaSpel() { 
     tim.stop(); 
   } 

   public void nyttSpel() { 
     stoppaSpel(); nollställ(); startaSpel(); 
   } 

   public void actionPerformed(ActionEvent e) { 
     // hit kommer man var 100:e millisekund, anropas av timern
     if (x0-r <= 0)  {            // är bollen i vänsterkanten? 
       if (y0 < rVä || y0 > rVä+rL) { // miss? 
         Toolkit.getDefaultToolkit().beep();   // plinga 
         poäng2.setText(" " + String.valueOf(++p2) + " "); 
         if (p2 == 10) 
           stoppaSpel();    
         v = v0;  // återgå till utgångshastighet 
       } 
       else       // träff 
         v++;     // öka hastigheten 
       xSteg = v; // flytta åt höger nästa gång 
     }
     else if (x0+r >= xMax) {     // är bollen i högerkanten? 
       if (y0 < rHö || y0 > rHö+rL) { // miss? 
         Toolkit.getDefaultToolkit().beep();   // plinga
         poäng1.setText(" " + String.valueOf(++p1) + " ");
         if (p1 == 10) 
           stoppaSpel();    
         v = v0;  // återgå till utgångshastighet 
       } 
       else        // träff 
         v++;      // öka hastigheten  
       xSteg = -v; // flytta åt vänster nästa gång 
     }
     if (y0-r<=0 || y0+r>=yMax) // i över- eller underkanten? 
         ySteg = -ySteg;          // byt vertikal riktning 
     x0 += xSteg;  // flytta bollen horisontellt 
     y0 += ySteg;  // flytta bollen vertikalt 
     if (x0 < r)   // hamnade bollen för långt åt vänster? 
       x0 = r; 
     else if (x0 > xMax-r) // hamnade bollen för långt åt höger? 
       x0 = xMax-r+1;
     if (y0 < r)            // hamnade bollen för långt upp? 
       y0 = r; 
     else if (y0 > yMax-r)  // hamnade bollen för långt ner? 
       y0 = yMax-r+1; 
     repaint(); 
   } 

   @Override
   public void paintComponent(Graphics g) { 
     super.paintComponent(g); 
     g.setColor(Color.red); 
     g.fillOval(x0-r, y0-r, 2*r, 2*r); // rita bollen  
     g.setColor(Color.black); 
     g.fillRect(0, rVä, 2, rL);        // rita vänster racket 
     g.fillRect(xMax-1, rHö, 2, rL);   // rita höger racket 
   } 

   KeyListener kl = new KeyAdapter() {
     @Override
     public void keyPressed(KeyEvent e) { 
       // någon tangent har tryckts ner 
       if (e.getKeyCode() == KeyEvent.VK_A)       // vänster upp 
         rVä = Math.max(0, rVä-rSteg);         
       else if (e.getKeyCode() == KeyEvent.VK_Z)  // vänster ner 
         rVä = Math.min(yMax-rL, rVä+rSteg); 
       if (e.getKeyCode() == KeyEvent.VK_UP)        // höger upp 
         rHö = Math.max(0, rHö-rSteg); 
       else if (e.getKeyCode() == KeyEvent.VK_DOWN) // höger ner 
         rHö = Math.min(yMax-rL, rHö+rSteg);  
     } 
   }; 

   ComponentListener cl = new ComponentAdapter() {
     @Override
     public void componentResized(ComponentEvent e) { 
       // spelplanens storlek har ändrats 
       xMax = e.getComponent().getSize().width-1; 
       yMax = e.getComponent().getSize().height-1; 
       e.getComponent().requestFocus(); 
       repaint(); 
     } 
   };     
 }



