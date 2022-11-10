import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

class Tetris implements World {
  static final int ROWS = 20; 
  static final int COLUMNS = 10; 
  Tetromino t; 
  SetOfBlocks blocks; 
  Tetris(Tetromino t, SetOfBlocks s) {
    this.t = t;
    this.blocks = s; 
  }
  public void draw(Graphics g) { // world->image
    // System.out.println("World being drawn."); 
    t.draw(g); 
    blocks.draw(g); 
    g.drawRect(0, 0, Tetris.COLUMNS * Block.SIZE, Tetris.ROWS * Block.SIZE); 
  } 
  public void update() { 
    // System.out.println("World getting older."); 
    if (this.landed(this.t))
      this.touchdown(); 
    else {
      this.t.move(0, 1); 
    }
  }
  public boolean hasEnded() { return false; } 
  public void keyPressed(KeyEvent e) { // world-key-move
    if (this.landed(this.t)) 
      this.touchdown(); 
    else if (e.getKeyCode() == KeyEvent.VK_LEFT ) { 
      this.t.move(-1,  0); 
    }
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
      this.t.move( 1,  0); 
    } 
    else if (e.getKeyChar() == ' ') { 
      this.jumpDown(this.t); 
    } 
    else if (e.getKeyChar() == 'r') {    // Rotate CW
      this.t.rotateCW();  
    } else this.t.move( 0, 0 );     
  } 
  public static void main(String[] args) {
    BigBang game = new BigBang(new Tetris(Tetromino.sQuare(), new SetOfBlocks())); 
    JFrame frame = new JFrame("Tetris"); 
    frame.getContentPane().add( game ); 
    frame.addKeyListener( game ); 
    frame.setVisible(true); 
    frame.setSize(Tetris.COLUMNS * Block.SIZE + 20, Tetris.ROWS * Block.SIZE + 40); 
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
    game.start(); 
  }
  void touchdown() {
    this.blocks = this.blocks.union(this.t.blocks);
    this.blocks.eliminateFullRows(); 
    this.t = Tetromino.pickRandom(); 
  }
  
  void jumpDown(Tetromino t) {  // [12] 
    if (! this.landed(t)) { 
      t.move(0, 1); 
      this.jumpDown(t); 
    }      
  }
  boolean landedOnBlocks(Tetromino t) { // [12] 
    t.move(0, 1); 
    if (t.overlapsBlocks(this.blocks)) { 
      t.move(0, -1); 
      return true; 
    } else {
      t.move(0, -1); 
      return false; 
    }
  }
  boolean landedOnFloor(Tetromino t) { // [12] 
    return t.blocks.maxY() == Tetris.ROWS - 1; 
  }
  boolean landed(Tetromino t) { // [12] 
    return this.landedOnFloor(t) || this.landedOnBlocks(t);  
  }
}
