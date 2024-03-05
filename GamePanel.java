import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
   //SCREEN SETTINGS
   final int originalTileSize = 16; //16*16 tile
   final int scale = 3;
   
   final int tileSize = originalTileSize * scale; //48*48 tile
   final int maxScreenCol = 16;
   final int maxScreenRow = 12;
   final int screenWidth = tileSize * maxScreenCol; //total pixels per column
   final int screenHeight = tileSize * maxScreenRow; //total pixels per row   
   
   Thread gameThread;

   public GamePanel()
   {
      this.setPreferredSize(new Dimension(screenWidth, screenHeight));
      this.setBackground(Color.black);
      this.setDoubleBuffered(true); 
   }
   
   public void startGameThread()
   {
      gameThread = new Thread(this);
      gameThread.start();
   }
   
   
   
   @Override
   public void run()
   {
      while(gameThread != null)
      {
         System.out.println("here");
      
      
      }
   
   
   }
}