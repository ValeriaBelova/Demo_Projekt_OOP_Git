 public class Tidpunkt {

   // instansvariabler
   private int t, m, s;
   private boolean visaSek = true;

   // konstruktorer
   public Tidpunkt() {}

   public Tidpunkt(int tim, int min, int sek) {
     sätt(tim, min, sek);
   }

   public Tidpunkt(int tim, int min) {
     this(tim, min, 0);
		 visaSek = false; 
   }

   public Tidpunkt(Tidpunkt tp) {   // kopieringskonstruktor
     t=tp.t; m = tp.m; s=tp.s; visaSek=tp.visaSek;
   }

   // metoder
   public void sätt (int tim, int min, int sek) {
     // kontrollera att tiden är OK
     if (tim>=0 && tim<24 && 
         min>=0 && min<60 && sek>=0 && sek<60) {
       t=tim; m=min; s=sek;
     }
     else 
       System.out.println("Felaktig tidpunkt");
   }

   public void sättVisaSek(boolean visa) {
     visaSek = visa;
   }

   public int avläsTim () {
     return t;
   }

   public int avläsMin () {
     return m;
   }

   public int avläsSek () {
     return s;
   }

   @Override
   public String toString () { // ger en text med formen tt:mm:ss
     String tid = t + ":" + m; // eller tt:mm
     if (visaSek)
       tid = tid  + ":" +  s;
     return tid;
   }

   public void ticka() {        // stegar fram tiden en sekund
     s = s+1;
     if (s==60) {
       s = 0;
       m = m+1;
     }
     if (m==60) {
       m = 0;
       t = t+1;
     }
     if (t==24) 
       t=0;
   }

   public void ticka(int antalSek) {
     while (antalSek>0) {
       ticka();
       antalSek = antalSek - 1;
     }
   }
 }

