import javax.swing.*;
import java.awt.*;
public class Window {
   public Window()
   {
      javax.swing.JFrame frame = new javax.swing.JFrame("Letter");
      frame.setSize(400,200);
      frame.add(new Drawing());
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public static void main (String[] args)
   {
      new Window();
   }
class Drawing extends javax.swing.JComponent
   {
      public void paint (Graphics g)
      {
         Font newFont = new Font("Serif",Font.PLAIN,30);
         g.setFont(newFont);
         g.drawString("Radin Ahari",110,50);
         g.drawString("20 Tillplain Road",80,100);
      }
   }
}
