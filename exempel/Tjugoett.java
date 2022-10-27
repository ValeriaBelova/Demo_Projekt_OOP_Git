 import static javax.swing.JOptionPane.*;

 public class Tjugoett {
   public static void main(String[] arg) {
     Kortbunt lek = new Kortbunt();
     Manniska du  = new Manniska(lek);
     Dator    jag = new Dator(lek, du);
     while (true) {
       lek.nyKortlek();
       lek.blanda();
       du.spela();
       String s = "";
       if (du.poäng() > 21)
         s = "Du förlorade!";
       else if (du.poäng() == 21)
         s = "Du vann!";
       else { // datorn måste spela
         jag.spela();
         if (jag.poäng() <= 21 && jag.poäng() >= du.poäng())
           s = "Du förlorade!";
         else
           s = "Du vann!";
       }
       int svar = showConfirmDialog(null, s + "\nNytt parti? ");
       if (svar != 0)
         break;
     }
   }
 }

