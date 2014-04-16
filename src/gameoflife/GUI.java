package gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
 
public class GUI {
  
    JFrame MainFrame = new JFrame("Conway's Game of life");
    JPanel Panel = new JPanel();
    JToolBar Menu = new JToolBar();
    JButton[][] allButtons;  
    boolean isMusicOn = false;
    boolean isLifeOn    = false;
    
    public  void CreateAndShowGUI( final World world, final Logic logic, final MP3Player sound) {   
    
    MainFrame.setSize(600,600);
    MainFrame.add(Menu,  BorderLayout.NORTH);
    MainFrame.add(Panel);
    Panel.setLayout(new GridLayout(world.getSize(),world.getSize()));
    Menu.setLayout(new GridLayout(1,3));
    MainFrame.setResizable(false);
    allButtons = new JButton[world.getSize()][world.getSize()];
    final JButton ChangeBtn = new JButton("Запустить");
    Menu.add(ChangeBtn);
    JButton ClrBtn = new JButton("Убить всех");
    Menu.add(ClrBtn);
    final JButton PlayMusicBtn = new JButton("Включить музыку");
    Menu.add(PlayMusicBtn);
    final Timer timer = new Timer(); 
    
    
    ChangeBtn.addActionListener(new ActionListener() {
      
      Scheduler aScheduler = new Scheduler(world, logic);   

       @Override
       public void actionPerformed(ActionEvent e) {
         
         if(isLifeOn) {
           aScheduler.cancel();
           aScheduler = new Scheduler(world, logic);
           isLifeOn = false;
           ChangeBtn.setLabel("Запустить");
         } else {
            timer.schedule(aScheduler, 0, 100);  //subsequent rate    
            isLifeOn = true;
            ChangeBtn.setLabel("Остановить");
         }
      } // end actionPerformed
     }); // end ChangeBtn.addActionListener
    
    ClrBtn.addActionListener(new ActionListener() { 
      @Override
      public void actionPerformed(ActionEvent e) {
        world.WorldRegeneration();
        Repaint(world);
      }
    }); // end ClrBtn.addActionListener

    PlayMusicBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!isMusicOn) {
          sound.play();
          isMusicOn = true;
          PlayMusicBtn.setLabel("Выключить музыку");
        } else {
          sound.stop();
          isMusicOn = false;
          PlayMusicBtn.setLabel("Включить музыку");
        }
      }
     }); // end ClrBtn.addActionListener
    
    for (int i = 0; i < world.getSize(); i++) {
      for (int j = 0; j < world.getSize(); j++) {
      
        allButtons[i][j] = new JButton();
               
        if (world.getField()[i][j]) {
           (allButtons[i][j]).setBackground(Color.GREEN);
        } else {
           (allButtons[i][j]).setBackground(Color.BLACK);
        }
        Panel.add(allButtons[i][j]);
        createMouseListener(allButtons[i][j], world, i,j);
       
      } //end for j
    } //end for i
    
    MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    MainFrame.setVisible(true);
  
  } // end CreateAndShowGUI method
    
  public void Repaint(World world) {
   
    for (int i = 0; i < world.getSize(); i++) {
      for (int j = 0; j < world.getSize(); j++) {
        if (world.getField()[i][j]) {
           (allButtons[i][j]).setBackground(Color.GREEN);
         } else {
           (allButtons[i][j]).setBackground(Color.BLACK);
         } // end else
      } // end for j
    } // end for i
  } // end Repaint method

  public  void createMouseListener(JButton b, final World world, final int i, final int j) {
      
    MouseListener mouseListener = new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent mouseEvent) {
        if (world.getField()[i][j]) {
          world.setFieldSquare(false, i, j);
          (allButtons[i][j]).setBackground(Color.BLACK);
        } else {
          world.setFieldSquare(true, i, j);
          (allButtons[i][j]).setBackground(Color.GREEN);
        }          
        Repaint(world);
      } // end public void mousePressed  
    }; // end mouseListener 
    b.addMouseListener(mouseListener);
  
  } // end createMouseListener  

  class Scheduler extends TimerTask implements Runnable {

  World aWorld; 
  Logic aLogic;
  GUI aGUI;
  
  public Scheduler (World world, Logic logic) {
    aWorld = world;
    aLogic = logic;
  }
  
  @Override
  public void run() {
    aLogic.changeState(aWorld);
    Repaint(aWorld);
    Panel.validate();     
  }
  
}  // end Scheduler interface

} // end GUI class