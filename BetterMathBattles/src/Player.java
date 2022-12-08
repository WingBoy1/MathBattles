
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;



public class Player extends Rectangle{
    boolean up, down, left, right;
    Image img;
    int speed = 10;
    public Player(int x1, int y1, Image img1){
        super(x1,y1, 30, 50);
        up=down=left=right=false;
        img=img1;
        
        
    }
    public void move(){
        if(up)
            moveUp();
        if(down)
            moveDown();
        if(left)
            moveLeft();
        if(right)
            moveRight();
    }
    public void move(ArrayList<Wall> walls){
        if(up)      {
            moveUp();
            for (Wall wall : walls) {
                while(intersects(wall))
                    moveDown();
            }
        }
        if(down)    {
            moveDown();
            for (Wall wall : walls) {
                while(intersects(wall))
                    moveUp();
            }
        }
        if(right)  {
            moveRight();
            for (Wall wall : walls) {
                while(intersects(wall))
                    moveLeft();
            }
        }
        if(left)    {
            moveLeft();
            for (Wall wall : walls) {
                while(intersects(wall))
                    moveRight();
            }
        }
    }
    
    public void moveUp(){
        y--;
    }
    public void moveDown(){
        y++;
    }
    public void moveRight(){
        x++;
    }
    public void moveLeft(){
        x--;
    }
    
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.drawImage(img, x, y, width, height, null);
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
    
}
