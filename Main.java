import processing.core.PApplet;

/**
 * Main class to execute sketch
 * @author 
 *
 */
class Main {
  public static void main(String[] args) {
    
    String[] processingArgs = {"MySketch"};
	  Sketch mySketch = new Sketch();  
	  // Sketch1 mySketch = new Sketch1();  
	  // Sketch2 mySketch = new Sketch2();  
	  
	  PApplet.runSketch(processingArgs, mySketch);
  }
  
}
