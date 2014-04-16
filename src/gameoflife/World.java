package gameoflife;

public class World {
  
  private final int size = 50;
  private final int virtualSize = 2*size;
  private boolean[][] field   = new boolean[virtualSize][virtualSize];
  
  public int getSize() {
    return size;
  }
  
  public int getVirtualSize() {
    return virtualSize;
  }
   
  public void WorldRegeneration() {
    for (int i = 0; i < virtualSize; i++) {
      for (int j = 0; j < virtualSize; j++) {
         field[i][j] = false;
      } //end for
    } //end for
  } // end WorldRegeneration method
 
  public boolean[][] getField() {
    return field;
  } // end getField method
  
  public void  setFieldSquare(boolean square, int i, int j) {
    field[i][j] = square;
  }
  
} // end World class