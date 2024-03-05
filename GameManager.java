import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class GameManager extends JFrame 
    implements ActionListener
{
    private JButton newGameButton;
    
    private JPanel panel;
    final int panelWidth = 500, panelHeight = 300;

    public static void main(String[] args) 
    {
        GameManager frame = new GameManager();
        frame.setSize(600, 500);
        frame.createGUI();
        frame.setVisible(true);
    }

    private void createGUI() 
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
  
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        panel.setBackground(Color.black);
        
        window.add(panel);
        window.add(newGameButton);
        
    }
    
    public void actionPerformed(ActionEvent event) 
    {   
    
    }  

}