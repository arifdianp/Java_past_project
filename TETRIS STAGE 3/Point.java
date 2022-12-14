class Point { // represents position of a square (block) 
  int x, y;
  Point(Point p) {   // [2] 
    this(p.x, p.y);  // [2] 
  }                  // [2] 
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  void move(int dx, int dy) {
    this.x += dx; 
    this.y += dy; 
  }
  
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";  
  }
}