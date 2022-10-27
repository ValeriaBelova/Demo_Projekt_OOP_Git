 import java.awt.*; 
 import java.awt.event.*; 
 import javax.swing.*; 

 public class Tennis extends JFrame implements ActionListener {
   private Spelplan plan = new Spelplan(); 
   private JLabel po�ng1 = new JLabel("0", JLabel.CENTER); 
   private JLabel po�ng2 = new JLabel("0", JLabel.CENTER);
   private JPanel pan    = new JPanel(); 
   private JButton[] b   = new JButton[4]; 
   private String[] s ={"Nytt spel","Paus","Forts�tt","Avsluta"};

   public Tennis() { 
     setTitle("Tennis"); 
     plan.setPreferredSize(new Dimension(350,250));
     plan.setBackground(Color.white); 
     po�ng1.setFont(new Font("SansSerif", Font.BOLD, 24)); 
     po�ng2.setFont(new Font("SansSerif", Font.BOLD, 24)); 
     pan.setLayout(new FlowLayout()); 
     for (int i=0; i<b.length; i++) { 
       b[i] = new JButton(); 
       b[i].setText(s[i]);  
       b[i].addActionListener(this); 
       pan.add(b[i]); 
     } 
     add(plan, BorderLayout.CENTER); 
     add(po�ng1, BorderLayout.WEST); 
     add(po�ng2, BorderLayout.EAST); 
     add(pan, BorderLayout.SOUTH);
     pack(); 
     plan.init(po�ng1, po�ng2); 
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
   private JLabel po�ng1, po�ng2; // f�r att visa po�ng 
   private int p1, p2;            // aktuella po�ng 
   private int xMax, yMax;        // h�gsta x- och y-koordinat 
   private int r, x0, y0;         // bollens radie och mittpunkt 
   private int xSteg, ySteg;      // bollens stegl�ngd 
   private int v, v0 = 5;         // bollens hastighet 
   private int rV�, rH�,          // rackets �vre kant
               rL, rSteg;         // rackets l�ngd och stegl�ngd

   public void init(JLabel l1, JLabel l2) { 
     po�ng1 = l1; po�ng2 = l2; 
     xMax = getSize().width-1; 
     yMax = getSize().height-1; 
     r  = yMax/20;   // ber�kna bollens radie 
     rL = 3*r;       // ber�kna rackets l�ngd 
     rSteg = r;      // ber�kna rackets stegl�ngd 
     addKeyListener(kl);        // lyssna p� tangentbordet och 
     addComponentListener(cl);  // �ndringar av planens storlek 
     nollst�ll();  
   } 

   private void nollst�ll() { 
     p1 = p2 = 0;  // nollst�ll po�ng 
     po�ng1.setText(" 0 "); po�ng2.setText(" 0 "); 
     xSteg = ySteg = v = v0 = 5; // utg�ngshastighet 
     x0 = r + 1;                 // s�tt bollen i v�nsterkanten 
     y0 = yMax/2;                // i mitten av kortsidan 
     rV� = rH� = yMax/2-rL/2;    // placera racketarna i mitten 
   } 

   public void startaSpel() { 
     tim.start(); 
   } 

   public void stoppaSpel() { 
     tim.stop(); 
   } 

   public void nyttSpel() { 
     stoppaSpel(); nollst�ll(); startaSpel(); 
   } 

   public void actionPerformed(ActionEvent e) { 
     // hit kommer man var 100:e millisekund, anropas av timern
     if (x0-r <= 0)  {            // �r bollen i v�nsterkanten? 
       if (y0 < rV� || y0 > rV�+rL) { // miss? 
         Toolkit.getDefaultToolkit().beep();   // plinga 
         po�ng2.setText(" " + String.valueOf(++p2) + " "); 
         if (p2 == 10) 
           stoppaSpel();    
         v = v0;  // �terg� till utg�ngshastighet 
       } 
       else       // tr�ff 
         v++;     // �ka hastigheten 
       xSteg = v; // flytta �t h�ger n�sta g�ng 
     }
     else if (x0+r >= xMax) {     // �r bollen i h�gerkanten? 
       if (y0 < rH� || y0 > rH�+rL) { // miss? 
         Toolkit.getDefaultToolkit().beep();   // plinga
         po�ng1.setText(" " + String.valueOf(++p1) + " ");
         if (p1 == 10) 
           stoppaSpel();    
         v = v0;  // �terg� till utg�ngshastighet 
       } 
       else        // tr�ff 
         v++;      // �ka hastigheten  
       xSteg = -v; // flytta �t v�nster n�sta g�ng 
     }
     if (y0-r<=0 || y0+r>=yMax) // i �ver- eller underkanten? 
         ySteg = -ySteg;          // byt vertikal riktning 
     x0 += xSteg;  // flytta bollen horisontellt 
     y0 += ySteg;  // flytta bollen vertikalt 
     if (x0 < r)   // hamnade bollen f�r l�ngt �t v�nster? 
       x0 = r; 
     else if (x0 > xMax-r) // hamnade bollen f�r l�ngt �t h�ger? 
       x0 = xMax-r+1;
     if (y0 < r)            // hamnade bollen f�r l�ngt upp? 
       y0 = r; 
     else if (y0 > yMax-r)  // hamnade bollen f�r l�ngt ner? 
       y0 = yMax-r+1; 
     repaint(); 
   } 

   @Override
   public void paintComponent(Graphics g) { 
     super.paintComponent(g); 
     g.setColor(Color.red); 
     g.fillOval(x0-r, y0-r, 2*r, 2*r); // rita bollen  
     g.setColor(Color.black); 
     g.fillRect(0, rV�, 2, rL);        // rita v�nster racket 
     g.fillRect(xMax-1, rH�, 2, rL);   // rita h�ger racket 
   } 

   KeyListener kl = new KeyAdapter() {
     @Override
     public void keyPressed(KeyEvent e) { 
       // n�gon tangent har tryckts ner 
       if (e.getKeyCode() == KeyEvent.VK_A)       // v�nster upp 
         rV� = Math.max(0, rV�-rSteg);         
       else if (e.getKeyCode() == KeyEvent.VK_Z)  // v�nster ner 
         rV� = Math.min(yMax-rL, rV�+rSteg); 
       if (e.getKeyCode() == KeyEvent.VK_UP)        // h�ger upp 
         rH� = Math.max(0, rH�-rSteg); 
       else if (e.getKeyCode() == KeyEvent.VK_DOWN) // h�ger ner 
         rH� = Math.min(yMax-rL, rH�+rSteg);  
     } 
   }; 

   ComponentListener cl = new ComponentAdapter() {
     @Override
     public void componentResized(ComponentEvent e) { 
       // spelplanens storlek har �ndrats 
       xMax = e.getComponent().getSize().width-1; 
       yMax = e.getComponent().getSize().height-1; 
       e.getComponent().requestFocus(); 
       repaint(); 
     } 
   };     
 }



