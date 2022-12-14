
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
import java.util.Arrays;
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
    final int ANS_CHOICES = 5;
    int maximumNumber = 50, minimumNumber = 4;
    int[] problemSet;

    boolean answer = true;
    boolean addition = true, subtraction = false, division = false, multiplication = false;

    int speed = 10;

    final int MAX_HEALTH=200;
    final int BAR_WIDTH=200, BAR_HEIGHT=20;
    
    int Health1 = MAX_HEALTH;
    int Health2 = MAX_HEALTH;

    final int GOOD_HIT = 20, BAD_HIT = 10;

    String Question = new String("1+1");

    public static void main(String[] args) throws Exception {
        new GameRunner();

    }

    public GameRunner() {
        frame = new JFrame("Insert Title Here");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display = new DisplayPanel();
        frame.add(display);
        randNums = new int[ANS_CHOICES];

        problemSet = getRandomProblem();
        randy = new Random();
        for (int i = 0; i < ANS_CHOICES; i++) {
            randNums[i] = randy.nextInt(27 * Wall.WALL_SIZE);
        }
        int goodNum = randy.nextInt(ANS_CHOICES);
        //put constructor code here
        player1 = new Player(350, 625, new ImageIcon("p1.png").getImage());
        player2 = new Player(775, 625, new ImageIcon("p2.png").getImage());
        walls = new ArrayList<Wall>();
        spikes = new ArrayList<Spike>();
        for (int i = 0; i < ANS_CHOICES; i++) {
            Spike a = new Spike(11 * Wall.WALL_SIZE + randNums[i], 10 * Wall.WALL_SIZE + 6 * Wall.WALL_SIZE * i);
            a.setBadSpike(goodNum == i);
            spikes.add(a);

            if (goodNum == i) {
                a.setText("" + problemSet[2]);
            } else {
                int fake = problemSet[2] + (ANS_CHOICES - i);
                a.setText("" + fake);
            }

            //spikes.add(new Spike(40*Wall.WALL_SIZE+randNums[i],10*Wall.WALL_SIZE+6*Wall.WALL_SIZE*i));
            Spike b = new Spike(40 * Wall.WALL_SIZE + randNums[i], 10 * Wall.WALL_SIZE + 6 * Wall.WALL_SIZE * i);
            b.setBadSpike(goodNum == i);
            spikes.add(b);

            if (goodNum == i) {
                b.setText("" + problemSet[2]);
            } else {
                int fake = problemSet[2] + (ANS_CHOICES - i);
                b.setText("" + fake);
            }
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
        if (Health1 == 0) {
            JOptionPane.showMessageDialog(null, "Player 2 wins!");
        }
        if (Health2 == 0) {
            JOptionPane.showMessageDialog(null, "Player 1 wins!");
        }

        //end your constructor code
        timer = new javax.swing.Timer(1 / speed, this);
        timer.start();
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //type what needs to be performed every time the timer ticks
        player1.move(walls);
        player2.move(walls);
        boolean done = false;
        for (Spike spike : spikes) {
            if (player1.intersects(spike)) {
                if (!spike.isBadSpike()) {
                    Health1 = Health1 - BAD_HIT;
                    if (Health1 <= 0) {

                        JOptionPane.showMessageDialog(null, "Player 2 wins!");
                        System.exit(1);
                    }
                } else {
                    Health2 = Health2 - GOOD_HIT;
                    done = true;
                    if (Health2 <= 0) {

                        JOptionPane.showMessageDialog(null, "Player 1 wins!");
                        System.exit(1);
                    }
                }
            }
        }
        for (Spike spike : spikes) {
            if (player2.intersects(spike)) {
                if (!spike.isBadSpike()) {
                    Health2 = Health2 - BAD_HIT;
                    if (Health2 <= 0) {

                        JOptionPane.showMessageDialog(null, "Player 1 wins!");
                        System.exit(1);
                    }

                } else {
                    Health1 = Health1 - GOOD_HIT;
                    done = true;
                    if (Health1 <= 0) {

                        JOptionPane.showMessageDialog(null, "Player 2 wins!");
                        System.exit(1);
                    }

                }
            }
        }
        for (int i = 0; i < spikes.size(); i++) {
            Spike spike = spikes.get(i);

            if (player1.intersects(spike) || player2.intersects(spike)) {
                spikes.remove(i);
                i--;
            }
        }
        if(done) resetBoard();
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
            g.setFont(new Font("Georgia", Font.PLAIN, 30));
            player1.draw(g);
            player2.draw(g);
            for (Wall wall : walls) {
                wall.draw(g);
            }
            for (Spike spike : spikes) {
                spike.draw(g);
            }

            
            g.setColor(Color.red);
            g.fillRect(100, 50, BAR_WIDTH, BAR_HEIGHT);
            g.setColor(Color.green);
            g.fillRect(100, 50, (int)(Health1*1.0/MAX_HEALTH*BAR_WIDTH), BAR_HEIGHT);

            
            g.setColor(Color.red);
            g.fillRect(800, 50, 200, 10);
            int barWidth=(int)(Health2*1.0/MAX_HEALTH*200);
            g.setColor(Color.green);
            g.fillRect(800+(200-barWidth), 50, barWidth, 10);

            g.setColor(Color.BLACK);
            g.drawString(Question, 550, 50);

        }
    }

    public int[] getAdditionProblem() {
        int[] prob = new int[3];
        Random randy = new Random();
        prob[0] = randy.nextInt(maximumNumber / 2 - minimumNumber) + minimumNumber;
        prob[1] = randy.nextInt(maximumNumber / 2 - minimumNumber) + minimumNumber;
        prob[2] = prob[0] + prob[1];
        Question = prob[0] + "+" + prob[1];
        return prob;
    }

    public int[] getRandomProblem() {
        //if random number AND addition
        return getAdditionProblem();
        //return null;
    }

    public void resetBoard() {
        problemSet = getRandomProblem();
        randy = new Random();
        for (int i = 0; i < ANS_CHOICES; i++) {
            randNums[i] = randy.nextInt(27 * Wall.WALL_SIZE);
        }
        int goodNum = randy.nextInt(ANS_CHOICES);
        //put constructor code here
        player1.setLocation(350, 625);
        player2.setLocation(775, 625);
        spikes.clear();
        for (int i = 0; i < ANS_CHOICES; i++) {
            Spike a = new Spike(11 * Wall.WALL_SIZE + randNums[i], 10 * Wall.WALL_SIZE + 6 * Wall.WALL_SIZE * i);
            a.setBadSpike(goodNum == i);
            spikes.add(a);
            if (goodNum == i) {
                a.setText("" + problemSet[2]);
            } else {
                int fake = problemSet[2] + (ANS_CHOICES - i);
                a.setText("" + fake);
            }

            //spikes.add(new Spike(40*Wall.WALL_SIZE+randNums[i],10*Wall.WALL_SIZE+6*Wall.WALL_SIZE*i));
            Spike b = new Spike(40 * Wall.WALL_SIZE + randNums[i], 10 * Wall.WALL_SIZE + 6 * Wall.WALL_SIZE * i);
            b.setBadSpike(goodNum == i);
            spikes.add(b);

            if (goodNum == i) {
                b.setText("" + problemSet[2]);
            } else {
                int fake = problemSet[2] + (ANS_CHOICES - i);
                b.setText("" + fake);
            }
        }
    }
}
