 import javax.swing.*;

 public class Hej2 {
   public static void main (String[] arg) {
     String namn;
     String hälsning;
     namn = JOptionPane.showInputDialog("Vad heter du?");
     hälsning = "Välkommen " + namn;
     JOptionPane.showMessageDialog(null, hälsning);
  	 }
 }
