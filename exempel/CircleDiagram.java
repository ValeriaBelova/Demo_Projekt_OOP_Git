 import java.awt.*; 
 import javax.swing.*; 

 public class CircleDiagram extends JPanel { 
   private int value, min, max;
 
   public CircleDiagram(int minimum, int maximum) { 
     if (min < max) { 
       min = minimum; max = maximum; value = min;
     } 
     else 
       System.out.println("Illegal minimum and maximum values"); 
     setBackground(Color.white); 
   } 

   public CircleDiagram() { 
     this(0, 100);         // s�tt intervallet till 0..100
   } 

   public void setValue(int v) { 
     if (v < min) 
       value = min;        // f�r litet v�rde, s�tt till minimum
     else if (v > max) 
       value = max;        // f�r stort v�rde, s�tt till maximum
     else 
       value = v;          // v�rdet OK, innanf�r gr�nserna
     repaint();
   } 

   public int getValue() { 
     return value; 
   } 

   @Override
   public void paintComponent(Graphics g) { 
     super.paintComponent(g);    // ritar bakgrund
     Insets i = getInsets();     // omgivande rams utbredning 
     int w = getWidth()-i.left-i.right;  // tillg�nglig bredd
     int h = getHeight()-i.top-i.bottom; // tillg�nglig h�jd 
     int diam = Math.min(h,w);    // cirkelns diameter 
     // ber�kna startposition f�r omgivande rektangel 
     int x = i.left + (w-diam)/2;  
     int y = i.top  + (h-diam)/2;  
     g.drawOval(x, y, diam, diam); // rita hela cirkel ofylld
     double part = (double) (value-min) / (max-min);  
     int partFilled  = (int)(part * 360 + 0.5);    
     g.fillArc(x, y, diam, diam, 90, -partFilled); //rita medurs 
   } 
 }

