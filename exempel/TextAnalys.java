 import java.util.*;
 import java.text.*;
 import java.io.*;

 public class TextAnalys {
   public static void main(String[] arg) throws IOException {
     Collator co = Collator.getInstance();  // j�mf�r texter
     co.setStrength(Collator.PRIMARY);      
     NavigableSet<String> m = new TreeSet<String>(co);
     // koppla en scanner till filen
     Scanner sc = new Scanner(new File(arg[0]));
     // l�s ett ord i taget och addera till m�ngden
     while(sc.hasNext())
       m.add(sc.next());
     // skriv ut alla orden
     for (String ord : m) 
       System.out.println(ord);
   }
 }

