 import java.awt.*;
 import java.applet.*;

 public class BollDemo extends Applet {
   private Boll b;

   @Override
   public void init() {
     String s = getParameter("radie");
     int r = Integer.parseInt(s);
     s = getParameter("hast");
     int h = Integer.parseInt(s);
     b = new Boll(r, h, h);
     setLayout(new GridLayout(1,1));
     add(b, BorderLayout.CENTER);
   }

   @Override
   public void start() {
     b.start();
   }

   @Override 
   public void stop() {
     b.stop();
   }
 }
