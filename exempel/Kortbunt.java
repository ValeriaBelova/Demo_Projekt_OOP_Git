 import java.util.*;

 public class Kortbunt {
   private List<Kort> bunten = new LinkedList<Kort>();

   public void l?gg?verst(Kort k) {
     bunten.add(0, k);
   }

   public  Kort taBort?versta() {
     Kort k = bunten.get(0);
     bunten.remove(0);
     return k;
   }

   public int antalKort() {
     return bunten.size();
   }

   public void sl?ngKorten() {
     bunten.clear();
   }

   public Kort tittaP?(int nr) {
     return bunten.get(nr);
   }

   public Kort taBort(int nr) {
     Kort k = bunten.get(nr);
     bunten.remove(nr);
     return k;
   }

   public int s?k(KortFarg f, KortSlag s) {
     int i = 0;
     for (Kort k : bunten)
       if (k.f?rg()==f && k.slag()==s)
         return i;
       else
         i++;
     return -1;
   }

   public void nyKortlek() {
     bunten.clear();
     for (KortFarg f : KortFarg.values())
       for (KortSlag s : KortSlag.values())
         bunten.add(new Kort(f, s));
   } 

   public void blanda() {
     Collections.shuffle(bunten);
   }
 }

