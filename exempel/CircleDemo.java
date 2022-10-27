 import java.awt.*;  
 import javax.swing.*; 
 import javax.swing.border.*; 

 public class CircleDemo extends JFrame { 
   public CircleDemo() { 
     CircleDiagram d1 = new CircleDiagram(); 
     CircleDiagram d2 = new CircleDiagram(-100, 100); 
     CircleDiagram d3 = new CircleDiagram(0, 100000);
     d2.setBorder(new EtchedBorder()); 
     d3.setBorder(new LineBorder(Color.lightGray, 5)); 
     d1.setValue(45); 
     d2.setValue(50); 
     d3.setValue(80000); 
     add(d1); add(d2); add(d3);     
     setLayout(new GridLayout(1, 3)); 
     setSize(450,155); 
     setVisible(true); 
     setDefaultCloseOperation(EXIT_ON_CLOSE); 
   }
 
   public static void main(String[] arg) { 
     CircleDemo demo = new CircleDemo(); 
   } 
 }
