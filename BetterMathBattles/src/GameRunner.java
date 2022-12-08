
/**
 *
 * @author NAME
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.lang.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameRunner implements ActionListener, KeyListener {

    javax.swing.Timer timer;
    JFrame frame;
    JPanel display;
    Player player1, player2;
    ArrayList<Wall> walls;
    ArrayList<Spike> spikes;
    int[] randNums;
    Random randy;
    final int ANS_CHOICES=5;
    
    
    boolean answer = true;
    
    int speed = 10;
    
    int Health1= 100;
    int Health2= 100; 
    
    
    public static void main(String[] args) throws Exception {
        new GameRunner();
    }

    public GameRunner() {
        frame = new JFrame("Insert Title Here");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display = new DisplayPanel();
        frame.add(display);
        randNums=new int[ANS_CHOICES];
        randy=new Random();
        for (int i = 0; i < ANS_CHOICES; i++) {
            randNums[i]=randy.nextInt(27*Wall.WALL_SIZE);
        }
        int goodNum=randy.nextInt(ANS_CHOICES);
        //put constructor code here
        player1 = new Player(350, 625, new ImageIcon("p1.png").getImage());
        player2 = new Player(775, 625, new ImageIcon("p2.png").getImage());
        walls = new ArrayList<Wall>();
        spikes = new ArrayList<Spike>();
        for (int i = 0; i < ANS_CHOICES; i++) {
            Spike a=new Spike(11*Wall.WALL_SIZE+randNums[i],10*Wall.WALL_SIZE+2*Wall.WALL_SIZE*i);
            a.setBadSpike(goodNum==i);
            spikes.add(a);
            
            
            spikes.add(new Spike(40*Wall.WALL_SIZE+randNums[i],10*Wall.WALL_SIZE+6*Wall.WALL_SIZE*i));
            
        }
        
        
        
   
        try {
            Scanner scan = new Scanner(new File("map.txt"));
            int r = 0;
            while (scan.hasNext()) {
                String temp = scan.next();
                for (int c = 0; c < temp.length(); c++) {
                    if (temp.charAt(c) == 'x') {
                        walls.add(new Wall(r, c));
                    }
                }
                r++;
            }
        } catch (Exception e) {
            System.out.println("Error in reading in file");
        }

        //end your constructor code
        timer = new javax.swing.Timer(1/speed, this);
        timer.start();
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //type what needs to be performed every time the timer ticks
        player1.move(walls);
        player2.move(walls);
        
        
        
       
        
    
        
        
        
        for(Spike  spike: spikes){
            if(player1.intersects(spike)){
                Health1 = Health1-10;
                if (Health1==0){
                    
                    JOptionPane.showMessageDialog(null, "Player 2 wins!");
                    System.exit(1);
                }
            }
        }
        for(Spike spike: spikes){
            if(player2.intersects(spike)){
                

                Health2 = Health2-10;
                if (Health2==0){
                    
                    JOptionPane.showMessageDialog(null, "Player 1 wins!");
                    System.exit(1);
                }
            }
        }
         for(int i = 0; i <spikes.size(); i++){
        Spike spike = spikes.get(i);
        
        if (player1.intersects(spike) || player2.intersects(spike)){
            spikes.remove(i);
            i--;
        }
    }
        //end your code for timer tick code
        display.repaint();
    }
    

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player1.setUp(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player1.setDown(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            player1.setLeft(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player1.setRight(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player2.setUp(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player2.setDown(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player2.setLeft(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player2.setRight(true);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player1.setUp(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player1.setDown(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            player1.setLeft(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player1.setRight(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player2.setUp(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player2.setDown(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player2.setLeft(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player2.setRight(false);
        }
    } 
 
 
    
    class DisplayPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //draw your graphics here
            player1.draw(g);
            player2.draw(g);
            for (Wall wall : walls) {
                wall.draw(g);
            }
            for (Spike spike : spikes) {
                spike.draw(g);
            }
            
            g.setColor(Color.green);
            g.fillRect(100, 50, Health1, 10);
            
            g.fillRect(1000, 50, Health2, 10);
            
            g.setColor(Color.BLACK);
            g.drawString("0/0", 550, 50);
            
            
        }
    }
}
