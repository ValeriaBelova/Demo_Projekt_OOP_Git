  import java.awt.*;
  import javax.swing.*;

  public class Direkt3 extends JApplet {

    @Override            // Denna rad b�r vara med
    public void init() { 
      JLabel l = new JLabel("V�lkommen till Java Direkt",
                            JLabel.CENTER);
      add(l);  // placera l i f�nstret
      l.setOpaque(true);        // ogenomskinlig bakgrund
      l.setBackground(Color.yellow);
      l.setForeground(Color.blue);
      l.setFont(new Font("SansSerif", Font.BOLD, 24));        
    }
  }
