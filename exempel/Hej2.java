 import javax.swing.*;

 public class Hej2 {
   public static void main (String[] arg) {
     String namn;
     String h�lsning;
     namn = JOptionPane.showInputDialog("Vad heter du?");
     h�lsning = "V�lkommen " + namn;
     JOptionPane.showMessageDialog(null, h�lsning);
  	 }
 }
