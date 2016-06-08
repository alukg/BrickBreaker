package GameComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

/**
 * The class that represents the board in the game.
 */
public class Board extends JPanel implements Runnable, MouseMotionListener, KeyListener
{
	//Variables
	private Game game;
	private int ballx = 319;
	private int bally = 578;

	private int batx = 285;
	private int baty = 590;

	private Rectangle ball = new Rectangle(ballx,bally,12,12);
	private Rectangle bat = new Rectangle(batx,baty,80,10);

	private boolean gameFinished = false;
	private boolean ballFallDown = false;
	private boolean ballMove = false;
	private int movey = -1;
	private int movex = 1;

	Brick[] bricks = new Brick[80];

	//Constructors
	public Board(Game game, int[][] bricks)
	{
		this.game = game;
		for(int row=0;row<8;row++){
			for(int col=0;col<10;col++){
				this.bricks[row*10+col] = new Brick(75+col*50,80+row*20,45,15,bricks[row][col]);
			}
		}
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
	}

	public void paint (Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0,0,649,600);
		g.setColor(Color.blue);
		g.fillOval(ball.x, ball.y, ball.width, ball.height);
		g.setColor(Color.BLACK);
		g.fill3DRect(bat.x, bat.y, bat.width, bat.height, true);
		g.setColor(Color.GRAY);
		g.fillRect(0,600,650,50);
		g.setColor(Color.BLACK);
		g.drawRect(0,0,649,600);

		for (int i=0;i<bricks.length;i++){
			if(bricks[i] != null){
				g.setColor(bricks[i].getColor());
				g.fill3DRect(bricks[i].x, bricks[i].y, bricks[i].width, bricks[i].height, true);
			}
		}
	}

	public void run() {
		while (!gameFinished) {
			if(ballMove){
				for (int i = 0; i < bricks.length; i++) {
					if (bricks[i] != null) {
						if (bricks[i].intersects(ball)) {
							Rectangle2D r = bricks[i].createIntersection(ball);

							/***************************** checks *************************
							 System.out.println(bricks[i].getX() +" , " + bricks[i].getY() +" , " + bricks[i].width +" , " + bricks[i].height);
							 System.out.println(r.getX() +" , "+r.getY());
							 **************************************************************/

							if ((r.getX() == bricks[i].getX() || r.getX() == (bricks[i].getX() + bricks[i].width - 1)) && (r.getY() <= bricks[i].getY() + bricks[i].height - 2 && r.getY() > bricks[i].getY())) {
								movex = -movex;
							} else {
								movey = -movey;
							}
							bricks[i] = null;
							game.addOneForCounter();
							if(game.getCounter()==80){
								gameFinished=true;
							}
							break;
						}
					}
				}

				repaint();

				ball.x = ball.x + movex;
				ball.y = ball.y + movey;

				if (ball.x <= 0 || ball.x + ball.width >= 649) {
					movex = -movex;
				}
				if (ball.y <= 0) {
					movey = -movey;
				}
				if (ball.y >= 600) {
					ballFallDown = true;
					//Function to show the ball again
				}
				if (ball.intersects(bat)) {
					Rectangle2D r = bat.createIntersection(ball);
					if (r.getY() <= 590)
						movey = movey * -1;
				}
			}
			try {
				Thread.sleep(5);
			} catch (Exception e) {

			}
		}
		game.finish();
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		System.out.println("Key Pressed");
		int keyCode = event.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE)
		{
			ballMove = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		e.getComponent().setFocusable(true);
		e.getComponent().requestFocus();
		if(ballMove)
		{
			Point locationOnPanel = e.getPoint();
			System.out.println("Mouse location is " + locationOnPanel.getX());
			if (locationOnPanel.getX() <= 570) {
				bat.x = (int) (locationOnPanel.getX());
				repaint();
			} else {
				batx = 570;
			}
		}
	}

}