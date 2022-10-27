 public class Flight {
   private String no, destination, comment = "";
   private Tidpunkt dep, arr;  // avg�ngs- och ankomsttid

   // konstruktor
   public Flight (String flightNo, String dest,
                  int depHour, int depMin,
                  int arrHour, int arrMin) {
     no = flightNo;
     destination = dest;
     dep = new Tidpunkt(depHour, depMin);
     arr = new Tidpunkt(arrHour, arrMin);
   }

   // metoder
   public void setComment(String com) {
     comment = com;
   }

   public String getNumber() {
     return no;
   }

   public String getDestination() {
     return destination;
   }

   public Tidpunkt getDep() {
     return new Tidpunkt(dep.avl�sTim(), dep.avl�sMin());
   }

   public Tidpunkt getArr() {
     return new Tidpunkt(arr.avl�sTim(), arr.avl�sMin());
   }

   public void delay (int min) {
     dep.ticka(min*60);
     arr.ticka(min*60);
     setComment("Delayed");
   }

   @Override
   public String toString() {
     return no + " " + destination + " " + dep + " " + comment;
   }
 }

