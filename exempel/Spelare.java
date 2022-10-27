 public abstract class Spelare {
   protected Kortbunt leken;
   protected Kortbunt hand = new Kortbunt();
   protected int p;            // aktuell po‰ng
   protected String symboler;  // kortens symboler

   public Spelare(Kortbunt k) {
     leken = k;
   }

   public abstract void spela();

   public void nyttSpel() {
     hand.sl‰ngKorten();
     p = 0;
     symboler = "";
   }

   public Kort nyttKort() {
     Kort k = leken.taBort÷versta();
     hand.l‰gg÷verst(k);
     symboler += k.toSymbol() + "  ";
     // Ber‰kna ny po‰ng
     int antalEss = 0;
     p = 0;
     for (int i=0; i<hand.antalKort(); i++) {
       KortSlag s = hand.tittaPÂ(i).slag();
       p += s.valˆr;
       if (s == KortSlag.ESS)
         antalEss++;        
     }
     for (int j=1; j<=antalEss && p>21; j++)
       p -= 13;  // r‰kna ett ess som 1
     return k;   // returnera det nya kortet
   }

   public int po‰ng() { 
     return p;
   }
 }

