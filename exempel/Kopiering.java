 import java.io.*;

 public class Kopiering {
   public static void main (String[] arg) 
                     throws IOException, FileNotFoundException {
     Console cons = System.console();

     // öppna infilen
     String namn = cons.readLine("Infilens namn? ");
     BufferedReader inFil = new BufferedReader
                           (new FileReader(namn));
     // öppna utfilen
     namn = cons.readLine("Utfilens namn? ");
     PrintWriter utFil = new PrintWriter(new BufferedWriter
                                        (new FileWriter(namn)));
     // kopiera
     int n=0;
     while (true) {
       String rad = inFil.readLine();
       if (rad == null)
         break;
       utFil.println(rad);
       n++;
     }
     utFil.close();
     cons.printf("%d rader kopierade", n);
   }
 }

