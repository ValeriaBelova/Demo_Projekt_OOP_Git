 import javax.swing.*;
 import java.awt.*;
 import java.net.*;

 public class DynamicImageIcon extends ImageIcon {
   private JComponent p;     // den komponent ikonen ritas p�

   public DynamicImageIcon(String filename) { 
     super(filename);
   }

   public DynamicImageIcon(URL location) { 
     super(location); 
   }

   public DynamicImageIcon(Image image) { 
     super(image); 
   }

   public void setParent(JComponent parent) {
     p = parent;
   }

   public Dimension getIconSize() {
     // r�kna ut bildens ursprungsstorlek
     int iw = getImage().getWidth(component),
         ih = getImage().getHeight(component);
     if (p == null)
       return new Dimension(iw, ih); // ingen f�r�lder angiven
     else {
       // r�kna ut f�r�lderkomponentens storlek
       int pw, ph;
       pw=p.getWidth() -p.getInsets().left-p.getInsets().right;
       ph=p.getHeight()-p.getInsets().top -p.getInsets().bottom;
       if (pw/(double)ph < iw/(double)ih) 
         return new Dimension(pw, pw*ih/iw); // bredden avg�r
       else
         return new Dimension(ph*iw/ih, ph); // h�jden avg�r
     }
   }

   public int getIconWidth() {
     return getIconSize().width;
   }

   public int getIconHeight() {
     return getIconSize().height;
   }

   public void paintIcon(Component c, Graphics g, int x, int y) {
     g.drawImage(getImage(), x, y, getIconWidth(),
                 getIconHeight(), component);
   }
 }

