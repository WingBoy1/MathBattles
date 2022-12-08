/**
 *
 * @author J. Barrett
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends Rectangle{
    final static int WALL_SIZE=15;
    
    public Wall(int r, int c) {
        super(c*WALL_SIZE, r*WALL_SIZE, WALL_SIZE, WALL_SIZE);
    }
    
    public void draw(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }

}