 public class XThread extends Thread {
   public static boolean delay(long millis) {
     if (interrupted()) 
       return false;
     try {
         sleep(millis);
     }
     catch (InterruptedException e) {
         return false;
     }
     return true;  // tråden har inte blivit avbruten
   }
 }
