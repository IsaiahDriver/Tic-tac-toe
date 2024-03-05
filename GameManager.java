import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class Exercise14_5 extends JFrame 
   implements ActionListener
{
    private JButton newGameButton;
    private static JLabel winnerLabel;
    
    private static GamePanel[][] gamePanels = new GamePanel[3][3];   
      
    private static int turnsLeft = 9;  
    public static boolean isPlayerTurn = true;
    private static boolean isDraw;
     
    public static final boolean isDebugMode = false;

    public static void main(String[] args) 
    {
        Exercise14_5 frame = new Exercise14_5();
        frame.setSize(350, 400);
        frame.createGUI();
        frame.setVisible(true);     
    }

    private void createGUI() 
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        
        winnerLabel = new JLabel("");
        
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        

        for (int y = 0; y < 3; y++)
        {
           for (int x = 0; x < 3; x++)
           {
              gamePanels[y][x] = new GamePanel(y, x, 100); 
              window.add(gamePanels[y][x].getPanel());                           
           }
        }

        window.add(newGameButton);
        window.add(winnerLabel);
    }
    
    public void actionPerformed(ActionEvent event) 
    {
       Object source = event.getSource();
       
       if (source == newGameButton)
       {
          restartGame();
       }
    }
    
    public static void finishTurn(GamePanel lastGamePanel)
    {
       turnsLeft--;
       if (checkForCompletion(lastGamePanel)) { return; }
       
       isPlayerTurn = !isPlayerTurn;
       if (!isPlayerTurn && !isDebugMode) { executeNonPlayerTurn(); }
         
    }
    
    private static void executeNonPlayerTurn()
    {
       if (turnsLeft == 0) 
       { 
          return; 
       }
       
       Random random = new Random(); 
       GamePanel[] neutralPanels = getNeutralPanels();

       GamePanel chosenPanel = neutralPanels[random.nextInt(neutralPanels.length - 1)];
       chosenPanel.markWithO();
       finishTurn(chosenPanel);  
    }
    
    private static GamePanel[] getNeutralPanels()
    {
       GamePanel[] temp = new GamePanel[9]; //Unfitted size
       int neutralCount = 0;
       for (int y = 0; y < 3; y++)
       {
          for (int x = 0; x < 3; x++)
          {
             if (gamePanels[y][x].getState() == GamePanel.PanelState.NEUTRAL)
             {
                temp[neutralCount++] = gamePanels[y][x];
             }                    
          }
       }
       
       GamePanel[] neutralPanels = new GamePanel[neutralCount]; //Fitted array without null elements
       for (int x = 0; x < neutralCount; x++)
       {
          neutralPanels[x] = temp[x]; 
       }
       return neutralPanels;
    }
    
    private static boolean checkForCompletion(GamePanel lastGamePanel)
    {
       int rowMatches = 0, colMatches = 0, obliqueMatches1 = 0, obliqueMatches2 = 0;
       
       for (GamePanel gamePanel : gamePanels[lastGamePanel.yPos])
       {
          if (gamePanel.getState() == lastGamePanel.getState())
          {
             rowMatches++; 
          }
       }
       
       for (int y = 0; y < 3; y++)
       {
          if (gamePanels[y][lastGamePanel.xPos].getState() == lastGamePanel.getState())
          {
             colMatches++;
          }
       }
       
       for (int x = 0; x < 3; x++)
       {
          if (gamePanels[x][x].getState() == lastGamePanel.getState())
          {
             obliqueMatches1++;
          }   
          if (gamePanels[2 - x][x].getState() == lastGamePanel.getState())
          {
             obliqueMatches2++;
          }
       }

       if (rowMatches == 3 || colMatches == 3 || obliqueMatches1 == 3
            || obliqueMatches2 == 3)
       {
          finishGame();
          return true;
       }
       else if (turnsLeft == 0)
       {
          isDraw = true;
          finishGame();
          return true;
       }
       return false; 
    }
    
    private static void finishGame()
    {
       GamePanel.setAllActive(false);
       
       if (isPlayerTurn && !isDraw)
       {
          winnerLabel.setText("Player won");
       
       }
       else if (!isPlayerTurn && !isDraw)
       {
          winnerLabel.setText("CPU won");
       }  
       else
       {
          winnerLabel.setText("DRAW");
       }
    }
    
    private void restartGame()
    {
       winnerLabel.setText(null);
       
       for (int y = 0; y < 3; y++)
       {
          for (int x = 0; x < 3; x++)
          {
             gamePanels[y][x].clearPanel();                    
          }
       }
       
       turnsLeft = 9;
       GamePanel.setAllActive(true);
       isPlayerTurn = true;
       isDraw = false;
    }
}