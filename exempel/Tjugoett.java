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
       if (du.po�ng() > 21)
         s = "Du f�rlorade!";
       else if (du.po�ng() == 21)
         s = "Du vann!";
       else { // datorn m�ste spela
         jag.spela();
         if (jag.po�ng() <= 21 && jag.po�ng() >= du.po�ng())
           s = "Du f�rlorade!";
         else
           s = "Du vann!";
       }
       int svar = showConfirmDialog(null, s + "\nNytt parti? ");
       if (svar != 0)
         break;
     }
   }
 }

