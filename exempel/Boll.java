 import java.awt.*;

 public class Boll extends Panel implements Runnable {
   private Thread aktivitet;
   private int r, x0, y0;      // bollens radie och mittpunkt
   private int xSteg, ySteg;   // förflyttning

   public Boll(int radie, int xHast, int yHast) {
     r = radie; xSteg = xHast; ySteg = yHast;
     x0 = r; y0 = r;
   }

   public void start() {
     if (aktivitet == null) {
       aktivitet = new Thread(this);
       aktivitet.start();
     }
   }

   public void stop() {
     if (aktivitet != null) {
       aktivitet.interrupt();
       aktivitet = null;
     }
   }

   public void run() {
     while (XThread.delay(100)) {
       if (x0-r+xSteg < 0 || x0+r+xSteg > getSize().width)
         xSteg = -xSteg;  // i kanten, byt riktning
       x0 += xSteg;
       if (y0-r+ySteg < 0 || y0+r+ySteg > getSize().height)
         ySteg = -ySteg;  // i kanten, byt riktning
       y0 += ySteg; 
       repaint();
     }
   }

   @Override
   public void paint(Graphics g) {
     g.fillOval(x0-r, y0-r, 2*r, 2*r);
   }
 }

