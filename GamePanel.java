import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class GamePanel
      implements MouseListener
{
   private JPanel panel;
   public static enum PanelState { NEUTRAL, X, O };
   private PanelState state = PanelState.NEUTRAL;
   private int panelSize;
   private int markRadius = 40;
   
   public final int xPos;
   public final int yPos;
   
   private static boolean panelsEnabled = true;
   private boolean isThisPanelEnabled = true;
      
   public GamePanel(int yPos, int xPos, int panelSize)
   {
      this.yPos = yPos;
      this.xPos = xPos;
      this.panelSize = panelSize;
   
      panel = new JPanel();
      panel.setPreferredSize(new Dimension(panelSize, panelSize));
      panel.setBackground(Color.white);
      panel.addMouseListener(this);
   }
   
   public JPanel getPanel()
   {
      return panel;
   }
   
   public PanelState getState()
   {
      return state;
   }
   
   public static void setAllActive (boolean setting)
   {
      panelsEnabled = setting;
   }
    
   public void markWithX()
   {
      Graphics g = panel.getGraphics();
       
      int panelCentre = panelSize / 2;
      int lineCompY = (int)Math.round(markRadius * Math.sin(Math.PI / 4));
      int lineCompX = (int)Math.round(markRadius * Math.cos(Math.PI / 4));
       
      g.drawLine(panelCentre, panelCentre, panelCentre + lineCompX, panelCentre 
           + lineCompY);
      g.drawLine(panelCentre, panelCentre, panelCentre + lineCompX, panelCentre 
           - lineCompY);
      g.drawLine(panelCentre, panelCentre, panelCentre - lineCompX, panelCentre 
           - lineCompY);
      g.drawLine(panelCentre, panelCentre, panelCentre - lineCompX, panelCentre 
           + lineCompY);   
     state = PanelState.X;
     isThisPanelEnabled = false;
   }
    
   public void markWithO()
   {
      Graphics g = panel.getGraphics();      
      int panelCentre = panelSize / 2;
      int diameter = 2 * markRadius;
       
      g.drawOval(panelCentre - markRadius, panelCentre - markRadius, diameter, diameter);
      state = PanelState.O;
      isThisPanelEnabled = false;
   }
   
   public void clearPanel()
   {
      Graphics g = panel.getGraphics();
      g.setColor(Color.white);
      g.fillRect(0, 0, panelSize, panelSize);
      g.setColor(Color.black);
      
      state = PanelState.NEUTRAL;
      isThisPanelEnabled = true;
   }
         
   public void mouseClicked(MouseEvent event) 
   {
       if (!panelsEnabled || !isThisPanelEnabled) { return; }   
       
       if (Exercise14_5.isPlayerTurn && !Exercise14_5.isDebugMode)
       {
          markWithX();
       }
       else
       {
          markWithO();
       }
       
       Exercise14_5.finishTurn(this);
   }
   
   public void mouseReleased(MouseEvent event) {} 
   public void mousePressed(MouseEvent event) {}   
   public void mouseExited(MouseEvent event) {}  
   public void mouseEntered(MouseEvent event) {}
}