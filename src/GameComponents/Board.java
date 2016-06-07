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

	private int batx = 250;
	private int baty = 590;

	private Rectangle ball = new Rectangle(ballx,bally,12,12);
	private Rectangle bat = new Rectangle(batx,baty,80,10);

	private boolean GameFinished = false;
	private boolean ballFallDown = false;
	private boolean ballMove = false;
	private int count = 0;
	private int movey = -1;
	private int movex = 1;

	Rectangle[] bricks = new Rectangle[80];

	//Constructors
	public Board(Game game)
	{
		this.game = game;
		for(int i=0;i<bricks.length;i++){
			bricks[i] = new Rectangle(75+(i%10)*50,80+(i/10)*20,45,15);
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
				g.fill3DRect(bricks[i].x, bricks[i].y, bricks[i].width, bricks[i].height, true);
			}
		}
	}

	public void run() {
		while (!GameFinished) {
			//if(ballMove){

				for(int i=0;i<bricks.length;i++){
					if(bricks[i]!=null){
						if(bricks[i].intersects(ball)){
							Rectangle2D r = bricks[i].createIntersection(ball);
							if(r.getX()==bricks[i].getX() || r.getX()==(bricks[i].getX()+bricks[i].width-1)){
								movex = -movex;
							}
							else{
								movey = -movey;
							}
							bricks[i] = null;
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
				if(ball.intersects(bat)){
					Rectangle2D r = bat.createIntersection(ball);
					if(r.getY()<=590)
						movey = movey*-1;
				}
				try {
					Thread.sleep(5);
				} catch (Exception e) {

				}
			//}
		}
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
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
	public void mouseMoved(MouseEvent e) {
		Point locationOnPanel = e.getPoint();
		if (locationOnPanel.getX()<=570){
			batx=(int)(locationOnPanel.getX());
		}
		else{
			batx = 570;
		}
	}
}