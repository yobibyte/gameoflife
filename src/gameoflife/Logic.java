package gameoflife;

public class Logic {
    
  public void changeState(World world)  { // Следующий такт
    
    boolean [][] buf = new boolean[world.getVirtualSize()][world.getVirtualSize()];
    
    for (int i = 0; i<world.getVirtualSize(); i++) {
      for (int j = 0;j<world.getVirtualSize(); j++) {
        buf[i][j] = world.getField()[i][j];
      }
    }
        
    for (int i = 0; i < world.getVirtualSize(); i++) {
      for (int j = 0; j < world.getVirtualSize(); j++) {
          
         // Проверяем соседей, я уже не человек, я зверь, нахуй.
         
          /*
           a b c
           d * e
           f  g h
           */      
          
          boolean a = false;
          boolean aExists;
          boolean b = false;
          boolean bExists;
          boolean c = false;
          boolean cExists;
          boolean d = false;
          boolean dExists;
          boolean e = false;
          boolean eExists;
          boolean f = false;
          boolean fExists;
          boolean g  = false;
          boolean gExists;
          boolean h = false;
          boolean hExists;
          boolean point = buf[i][j];
          
          // Проверили a
          if (i!=0 && j!=0) {
            aExists = true;
            a = buf[i-1][j-1]; 
          }                                            
          else {
            aExists = false;
          }

          // Проверили b  
          if (i!=0) {
            bExists = true;
            b = buf[i-1][j];
          }
          else {
            bExists = false;
          }
          
          // Проверили c
          if (i!=0 && j!=world.getVirtualSize()-1) {
            cExists = true;
            c = buf[i-1][j+1]; 
          }
          else {
            cExists = false;
          }
          
          // Проверили d
          if (j!=0) {
            dExists = true;
            d = buf[i][j-1]; 
          }
          else {
            dExists = false;
          }
          
          // Проверили e
          if (j!=world.getVirtualSize()-1) {
            eExists = true;
            e = buf[i][j+1]; 
          }
          else {
            eExists = false;
          }
          
          // Проверили f
          if (i!=world.getVirtualSize()-1 && j!=0) {
            fExists = true;
            f = buf[i+1][j-1]; 
          }
          else {
            fExists = false;
          }
          
          // Проверили g
          if (i!=world.getVirtualSize()-1) {
            gExists = true;
            g = buf[i+1][j]; 
          }
          else {
            gExists = false;
          }
          
          // Проверили h
          if (i!=(world.getVirtualSize()-1) && j!=(world.getVirtualSize()-1)) {
            hExists = true;
            h = buf[i+1][j+1]; 
            //System.out.println(h);
          }
          else {
            hExists = false;
          }
          
          int  counter = 0;        
          
          if (aExists && a) {counter ++;}
          if (bExists && b) {counter ++;}
          if (cExists && c)  {counter ++;}
          if (dExists && d) {counter ++;}
          if (eExists && e) {counter ++;}
          if (fExists && f)   {counter ++;}
          if (gExists && g) {counter ++;}
          if (hExists && h) {counter ++;}
          
           //в мёртвой клетке, рядом с которой ровно три живые клетки, зарождается жизнь;
          if (!point && counter == 3) {world.setFieldSquare(true, i, j);} 
                    
          if (point) {
            //    если у живой клетки меньше двух или больше трех соседей, то она погибает; 
            if (counter < 2 || counter > 3) {
              world.setFieldSquare(false, i, j);
            } // end if
          } //end if
          
      } // end for J
    } // end for i
    
  }  // end changeState method
} // end Logic class