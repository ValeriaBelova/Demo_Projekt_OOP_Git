 public class VisaBild extends JFrame implements ActionListener{ 
   private JLabel l1 = new JLabel(new ImageIcon("sara.jpg")); 
   private JLabel l2 = new JLabel(new ImageIcon("blommor.jpg")); 
   private JLabel l3 = new JLabel(new ImageIcon("flag_swe.gif")); 
   private JScrollPane scroll = new JScrollPane(l1); 
   private JPanel  p  = new JPanel(); 
   private JButton b1 = new JButton("Flicka"); 
   private JButton b2 = new JButton("Blommor"); 
   private JButton b3 = new JButton("Flagga"); 
   private JSplitPane split = new JSplitPane
                       (JSplitPane.HORIZONTAL_SPLIT, p, scroll);

   public VisaBild() { 
     add(split); 
     scroll.setMinimumSize(new Dimension(100, 100)); 
     p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS)); 
     p.add(b1); p.add(b2); p.add(b3); 
     b1.setBorderPainted(false); b1.addActionListener(this);
     b2.setBorderPainted(false); b2.addActionListener(this);
     b3.setBorderPainted(false); b3.addActionListener(this);
     setSize(350,250);    
     setVisible(true);  
     setDefaultCloseOperation(EXIT_ON_CLOSE);  
   }
 
   public void actionPerformed(ActionEvent e) { 
     if (e.getSource() == b1)  
       scroll.setViewportView(l1); 
     else if (e.getSource() == b2)  
       scroll.setViewportView(l2); 
     else if (e.getSource() == b3)  
       scroll.setViewportView(l3); 
   }
 
   public static void main (String[] arg) { 
     VisaBild v = new VisaBild(); 
   } 
 }

