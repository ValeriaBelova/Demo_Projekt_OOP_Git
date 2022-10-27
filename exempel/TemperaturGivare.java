 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.net.*;
 import java.io.*;
 import java.util.*;

 public class TemperaturGivare extends JFrame 
                               implements ActionListener {
   JTextField ort = new JTextField(10),
              tem = new JTextField();
   InetAddress    iadr;
   DatagramSocket socket;
   int port;

   public TemperaturGivare(String tillAdress, int portNr) 
                  throws UnknownHostException, SocketException {
     // skapa en socket f�r s�ndning och mottagning
     iadr = InetAddress.getByName(tillAdress);
     port = portNr;
     socket = new DatagramSocket();
     socket.setSoTimeout(10000);
     // ordna f�nstrets layout
     setTitle("Rapportera temperatur");
     setLayout(new GridLayout(2, 2));
     add(new JLabel("Ort ", JLabel.RIGHT)); add(ort);
     add(new JLabel("Temperatur ", JLabel.RIGHT)); add(tem);
     ort.addActionListener(this);
     tem.addActionListener(this);
     pack();
     setVisible(true);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   private void rapportera(String plats, double temp) {
     // redigera meddelandet 
     // 10 tecken f�r orten och 6 f�r temperaturen
     String medd = String.format("%-10s%6.1f", plats, temp);
     byte[] data = medd.getBytes();  // g�r om texten till bytes
     for (int i=1; i<=3; i++) {      // g�r tre s�ndningsf�rs�k
       // skapa ett datagram med meddelandet     
       DatagramPacket packet = new DatagramPacket(data,
                                       data.length, iadr, port);
     try {
         socket.send(packet);
         socket.receive(packet);  // v�nta tills svar kommit
         // g�r om mottagna bytes till en String
         String svar = new String(packet.getData(), 0,
                                  packet.getLength());
         if (svar.equals("OK"))
           return;   // s�ndningsf�rs�ket lyckades
       }
       catch(IOException ie) {} // timeout eller fel
     }
     // alla s�ndningsf�rs�ken misslyckades
     System.out.println("Kommunikationsfel"); 
   }

   public void actionPerformed(ActionEvent e) {
     // anv�ndaren har tryckt p� Enter-tangenten
     // avl�s plats och temperatur fr�n f�nstret
     if (e.getSource()==ort || e.getSource()==tem) {
       Scanner sc = new Scanner(tem.getText());
       rapportera(ort.getText(), sc.nextDouble());
     }
   }

   public static void main(String[] arg) 
                 throws UnknownHostException, SocketException {
     // h�mta internetadress och portnummer fr�n arg
     new TemperaturGivare(arg[0], Integer.parseInt(arg[1]));
   }
 }

