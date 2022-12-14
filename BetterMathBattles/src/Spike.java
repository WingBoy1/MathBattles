/**
 *
 * @author b.Goodin
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.*;

public class Spike extends Rectangle{
   String text="";
    public Spike(){
        super(0,0,20,20);
        Random randy=new Random();
        x=randy.nextInt(1000);
        y=randy.nextInt(500);
    }

    Spike(int x1, int y1) {
        super(x1,y1,Wall.WALL_SIZE,Wall.WALL_SIZE);
    }
    
    boolean badSpike=false;

    public boolean isBadSpike() {
        return badSpike;
    }

    public void setBadSpike(boolean badSpike) {
        this.badSpike = badSpike;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    
    
    
    
    public void draw(Graphics g){
        g.setColor(Color.orange);
        g.fillOval(x,y,width,height);
        g.drawString(text, x, y);
    }

}