 import static javax.swing.JOptionPane.*; 
 import java.util.*;

 public class BilHyra2 { 
   public static void main (String[] arg) {
     String indata = showInputDialog
                ("Ange antal dagar, pris per dag samt bilmodell");
     Scanner sc = new Scanner(indata);
     int antalDagar  = sc.nextInt(); 
     double dagsPris = sc.nextDouble();
     String bil = sc.next();
     double totPris = dagsPris * antalDagar;
     String s = String.format("Totalt pris f�r %s: %.2f", 
                              bil, totPris);
     showMessageDialog(null, s);
   } 
 } 
