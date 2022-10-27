 import static javax.swing.JOptionPane.*;

 public class Manniska extends Spelare {
   public Manniska(Kortbunt k) {
     super(k);
   }

   @Override
   public void spela() {
     nyttSpel();
     while (p < 21) {
       nyttKort();
       if (p < 21) {
         int svar = showConfirmDialog(null, 
                      "Du har korten: " + symboler +
                      "\noch har " + p + " poäng." +
                      "\nVill du ha ett kort till?");
         if (svar == 1)      // nej
           break;
         else if (svar == 2) // avbryt
           System.exit(0);
       }
       else 
         showMessageDialog(null, 
           "Du har korten: " + symboler +
           "\noch har " + p + " poäng.");
     }
   }
 }
