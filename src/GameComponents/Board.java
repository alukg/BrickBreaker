package GameComponents;

import GameComponents.Balls.*;
import GameComponents.Bricks.Brick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * The class that represents the board in the game.
 */
public class Board extends JPanel implements Runnable, MouseMotionListener, KeyListener , ActionListener
{
	//Variables
	private Game game;
	private final int BALL_START_X = 319;
	private final int BALL_START_Y = 578;
	private Timer timerForBallDown;
	private final int BAT_START_X = 285;
	private final int BAT_START_Y = 590;

	private Ball ball;
	private Rectangle bat = new Rectangle(BAT_START_X,BAT_START_Y,80,10);

	private boolean gameFinished = false;
	private boolean ballFallDown = false;
	private boolean ballMove = false;
	private int movey;
	private int movex;

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
		ball = new FireBall(BALL_START_X,BALL_START_Y);

		Random rand = new Random();
		movex = rand.nextInt(7) -3;

		if(movex!=0)
			movey = -4 + Math.abs(movex);
		else
			movey = -3;

		this.addKeyListener(this);
		this.addMouseMotionListener(this);
	}

	public void paint (Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0,0,649,600);
		g.setColor(ball.getColor());
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
							game.addDeadBrick();
							if(game.getDeadBricks()==80){
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
				if (ball.y >= 600)
				{
					ballFallDown = true;
					ballMove = false;
					timerForBallDown = new Timer(3000, this);
					timerForBallDown.start();
				}
				if (ball.intersects(bat)) {
					Rectangle2D r = bat.createIntersection(ball);
					if (r.getY() <= 590){
						int dis = (int)(r.getX()-bat.getX());
						if(dis<13){
							movex = -3;
							movey = -1;
						}
						else if(dis>=13 && dis<23){
							movex = -2;
							movey = -2;
						}
						else if(dis>=23 && dis<35){
							movex = -1;
							movey = -3;
						}
						else if(dis>=35 && dis<45){
							movex = 0;
							movey = -3;
						}
						else if(dis>=45 && dis<57){
							movex = 1;
							movey = -3;
						}
						else if(dis>=57 && dis<67){
							movex = 2;
							movey = -2;
						}
						else{
							movex = 3;
							movey = -1;
						}
					}
				}
			}
			try {
				Thread.sleep(10);
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
		if(keyCode == KeyEvent.VK_1)
		{
			ball = new FireBall(ball.x,ball.y);
			repaint();
		}
		if(keyCode == KeyEvent.VK_2)
		{
			ball = new WaterBall(ball.x,ball.y);
			repaint();
		}
		if(keyCode == KeyEvent.VK_3)
		{
			ball = new ElectricBall(ball.x,ball.y);
			repaint();
		}
		if(keyCode == KeyEvent.VK_4)
		{
			ball = new WoodBall(ball.x,ball.y);
			repaint();
		}
		if(keyCode == KeyEvent.VK_F7)
		{
			ball = new ElementalBall(ball.x,ball.y);
			repaint();
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
				bat.x = 570;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		ball.x = BALL_START_X;
		ball.y = BALL_START_Y;
		bat.x =  BAT_START_X;
		bat.y = BAT_START_Y;
		ballFallDown = false;
		//ballMove = true;
		movey = -movey;
		timerForBallDown.stop();
		repaint();
	}
}