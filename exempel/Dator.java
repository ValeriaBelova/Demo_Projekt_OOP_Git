 import static javax.swing.JOptionPane.*;

 public class Dator extends Spelare {
   private Spelare motspelare;

   public Dator(Kortbunt k, Spelare mot) {
     super(k);
     motspelare = mot;
   }

   @Override 
   public void spela() {
     nyttSpel();
     while (p < 21 && p < motspelare.poäng())
       nyttKort();
     showMessageDialog(null, "Datorn fick korten: " + symboler + 
                             "\noch har " + p + " poäng");
   }
 }
