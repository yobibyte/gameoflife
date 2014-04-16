package gameoflife;

/**
 * @author loe
 * February 25, 2013 ... today
 */

import java.awt.EventQueue;
import java.util.TimerTask;

public abstract class GameOfLife extends TimerTask {
  
  public static void main(String[] args) {
    
    EventQueue.invokeLater(new Runnable() {
        
      @Override
      public void run() {
        
        World aWorld = new World();
        GUI aGUI = new GUI();
        Logic aLogic = new Logic();
        aWorld.WorldRegeneration();
        MP3Player soundToPlay = new MP3Player("Contra.mp3");
        aGUI.CreateAndShowGUI(aWorld, aLogic, soundToPlay);
        
      } // end run method
    }); // end invokelate
  } // end main method
}// end GameOfLife class