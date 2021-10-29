package birckBracker;

import javax.swing.*;
//import java.util.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;

public class Birckpanel extends JPanel implements KeyListener, ActionListener{

	private boolean play = false;
	private int score = 0;
	private int bricks = 21;
	private Timer time;
	private int delay = 8;
	private int player = 310;
	private int xball = 120;
	private int yball = 350;
	private int xdirball = -1;
	private int ydirball = -2;
	private brickmap map;
	
	public Birckpanel(){
		map = new brickmap(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
//		setBackground(Color.gray);
		time = new Timer(delay,this);
		time.start();
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		//border
		g.setColor(Color.yellow);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(683,0,3,592);
		
		//paddle
		g.setColor(Color.green);
		g.fillRect(player,550,100,8);

		//ball
		g.setColor(Color.red);
		g.fillOval(xball,yball,20,20);
		
		map.draw((Graphics2D)g);
		
		//score
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score,590,30);
		
		if (yball>570) {
			play = false;
			xdirball = 0;
			ydirball = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("GAME OVER, Score: "+score,190,300);

			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Enter to restart game ",270,400);
		}
		
		if (bricks <= 0) {
			play = false;
			xdirball = 0;
			ydirball = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("YOu won!!",190,300);

			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Enter to restart game ",270,400);
		}

		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		time.start();
		if(play) {
			if(new Rectangle(xball,yball,20,20).intersects(new Rectangle(player,550,100,8))) {
				ydirball = -ydirball;
			}
			
			A: for(int i = 0; i< map.map.length; i++) {
				for(int j = 0; j< map.map[i].length; j++) {
					if(map.map[i][j]>0) {
						int ybrick = i*map.height+50;
						int xbrick = j*map.width+50;
						int height = map.height;
						int width = map.width;
						
						Rectangle rect = new Rectangle(xbrick,ybrick,width,height);
						Rectangle ballrect = new Rectangle(xball,yball,20,20);
						Rectangle interrect = rect;
						
						if(ballrect.intersects(interrect)) {
							map.setbrickvalue(0, i, j);
							bricks--;
							score += 5;
							
							if(xball+19<=ballrect.x || xball+1>=ballrect.x+ballrect.width) {
								xdirball = -xdirball;
							} else {
								ydirball = -ydirball;
							}
							break A;
						}
					}
				}
			}
			
			xball+= xdirball;
//			System.out.print(xball+"--    ");
//			System.out.print(xdirball+"<-->  ");
			yball+= ydirball;
			if(xball<0) {
				xdirball = -xdirball;
			}
			if(yball<0) {
				ydirball = -ydirball;
			}
			if(xball<670) {
				xdirball = -xdirball;
			}
			
		}
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(player >= 600) {
				player = 600;
			} else {
				moveRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(player <= 10) {
				player = 10;
			} else {
				moveLeft();
			}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(!play) {
				play =true;
				score = 0;
				bricks = 21;
				player = 310;
				xball = 120;
				yball = 350;
				xdirball = -1;
				ydirball = -2;
//				map = new brickmap(3,7);
//				repaint();
			}
		}
	}
	
	public void moveRight() {
		play = true;
		player += 20;
//		System.out.print(player);
	}

	public void moveLeft() {
		play = true;
		player -= 20;
//		System.out.print(player);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
