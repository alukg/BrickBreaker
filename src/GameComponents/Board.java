package GameComponents;

import GameComponents.Balls.*;
import GameComponents.Bricks.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * The class that represents the board in the game.
 */
public class Board extends JPanel implements Runnable, MouseMotionListener, KeyListener , ActionListener , MouseListener
{
	//Variables
	private Game game;
	private final int BALL_START_X = 319;
	private final int BALL_START_Y = 578;
	public static Timer timerForBallDown;
	private final int BAT_START_X = 285;
	private final int BAT_START_Y = 590;

	private Ball ball;
	private Rectangle bat;

	public static Thread thread;

	private boolean gameFinished;
	public static boolean ballDisappear = false;
	public static boolean ballMove = false;
	public static double movey;
	public static double movex;

	public static Brick[] bricks;

	public static Image electricImage;
	public static Image regularImage;
	public static Image fireImage;
	public static Image plusImage;
	public static Image rectangleImage;
	public static Image waterImage;
	public static Image woodImage;
	public static Image xImage;

	public Image background;

	//Constructors
	public Board(Game game, int[][] bricksArr)
	{
		/**
		 * Load game images.
		 */
		try {
			electricImage = ImageIO.read(new File("design/Bricks/electricBrick.jpg"));
			regularImage = ImageIO.read(new File("design/Bricks/regularBrick.jpg"));
			fireImage = ImageIO.read(new File("design/Bricks/fireBrick.jpg"));
			plusImage = ImageIO.read(new File("design/Bricks/plusBrick.jpg"));
			rectangleImage = ImageIO.read(new File("design/Bricks/rectangleBrick.jpg"));
			waterImage = ImageIO.read(new File("design/Bricks/waterBrick.jpg"));
			woodImage = ImageIO.read(new File("design/Bricks/woodBrick.jpg"));
			xImage = ImageIO.read(new File("design/Bricks/xBrick.jpg"));
			background = ImageIO.read(new File("design/background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ball = new FireBall(BALL_START_X,BALL_START_Y);
		ball.x = BALL_START_X;
		ball.dballx = BALL_START_X;
		ball.y = BALL_START_Y;
		ball.dbally = BALL_START_Y;
		bat = new Rectangle(BAT_START_X,BAT_START_Y,80,10);
		ballDisappear = false;
		ballMove = false;
		gameFinished = false;
		this.game = game;
		bricks = new Brick[80];
		for(int row=0;row<8;row++)
		{
			for(int col=0;col<10;col++)
			{
				//Create each brick by his type.
				switch (bricksArr[row][col])
				{
					case 0:
						this.bricks[row*10+col] = new RegularBrick(75+col*50,80+row*27,row*10+col);
						break;
					case 1:
						this.bricks[row*10+col] = new FireBrick(75+col*50,80+row*27,row*10+col);
						break;
					case 2:
						this.bricks[row*10+col] = new WaterBrick(75+col*50,80+row*27,row*10+col);
						break;
					case 3:
						this.bricks[row*10+col] = new ElectricBrick(75+col*50,80+row*27,row*10+col);
						break;
					case 4:
						this.bricks[row*10+col] = new WoodBrick(75+col*50,80+row*27,row*10+col);
						break;
					case 5:
						this.bricks[row*10+col] = new XBrick(75+col*50,80+row*27,row*10+col);
						break;
					case 6:
						this.bricks[row*10+col] = new PlusBrick(75+col*50,80+row*27,row*10+col);
						break;
					case 7:
						this.bricks[row*10+col] = new RectangleBrick(75+col*50,80+row*27,row*10+col);
						break;
				}

			}
		}

		chooseRandomDirection();

		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		timerForBallDown = new Timer(3000, this);

		thread = new Thread(this);
		thread.start();
	}

	/**
	 * The function that randomize the ball direction.
	 */
	private void chooseRandomDirection()
	{
		Random rand = new Random();
		int cases = rand.nextInt(8)+1;

		if (cases == 1) {
			movex = -3*Math.sqrt(0.1);
			movey = -Math.sqrt(0.1);
		} else if (cases == 2) {
			movex = -Math.sqrt(0.5);
			movey = -Math.sqrt(0.5);
		} else if (cases == 3) {
			movex = -Math.sqrt(0.1);
			movey = -3*Math.sqrt(0.1);
		} else if (cases == 4) {
			movex = 0;
			movey = -1;
		} else if (cases == 5) {
			movex = Math.sqrt(0.1);
			movey = -3*Math.sqrt(0.1);
		} else if (cases == 6) {
			movex = Math.sqrt(0.5);
			movey = -Math.sqrt(0.5);
		} else {
			movex = 3*Math.sqrt(0.1);
			movey = -Math.sqrt(0.1);
		}
	}

	/**
	 * The function draws the board.
	 *
	 * @param  g  saves the base graphics
	 */
	public void paint (Graphics g)
	{
		g.drawImage(background, 0, 0, null);
		g.setColor(ball.getColor());
		if(!ballDisappear) {
			g.fillOval(ball.x, ball.y, ball.width, ball.height);
		}
		g.setColor(Color.BLACK);
		g.fill3DRect(bat.x, bat.y, bat.width, bat.height, true);
		g.setColor(Color.GRAY);
		g.fillRect(0,600,650,50);
		g.setColor(Color.BLACK);
		g.drawRect(0,0,649,600);

		for (int i=0;i<bricks.length;i++){
			if(bricks[i] != null){
				g.drawImage(bricks[i].getImage(), bricks[i].x, bricks[i].y, null);
			}
		}
	}
	/**
	 * The function that runs the brick.
	 */
	public void run()
	{
		while (!gameFinished) {
			if (ballMove)
			{
				for (int i = 0; i < bricks.length; i++) {
					if (bricks[i] != null) {
						if (ball.intersects(bricks[i])) {
							ball.impact(bricks[i]);
							Game.addHit();
							if (game.getDeadBricks() == 80) {
								gameFinished = true;
							}
							break;
						}
					}
				}
				repaint();

				ball.addX(movex);
				ball.addY(movey);

				if (ball.x <= 0 || ball.x + ball.width >= 649) {
					movex = -movex;
				}
				if (ball.y <= 0) {
					movey = -movey;
				}
				if (ball.y >= 600)
				{
					ballDisappear = true;
					ballMove = false;
					timerForBallDown.start();
				}
				if (ball.intersects(bat)) {
					Rectangle2D r = bat.createIntersection(ball);
					if (r.getY() <= 590) {
						int dis = (int) (r.getX() - bat.getX());
						if (dis < 13) {
							movex = -3*Math.sqrt(0.1);
							movey = -Math.sqrt(0.1);
						} else if (dis >= 13 && dis < 23) {
							movex = -Math.sqrt(0.5);
							movey = -Math.sqrt(0.5);
						} else if (dis >= 23 && dis < 35) {
							movex = -Math.sqrt(0.1);
							movey = -3*Math.sqrt(0.1);
						} else if (dis >= 35 && dis < 45) {
							movex = 0;
							movey = -1;
						} else if (dis >= 45 && dis < 57) {
							movex = Math.sqrt(0.1);
							movey = -3*Math.sqrt(0.1);
						} else if (dis >= 57 && dis < 67) {
							movex = Math.sqrt(0.5);
							movey = -Math.sqrt(0.5);
						} else {
							movex = 3*Math.sqrt(0.1);
							movey = -Math.sqrt(0.1);
						}
					}
				}
			}
			try {
				thread.sleep(2);
			}
			catch (Exception e)
			{

			}
		}
		game.finish();
	}

	/**
	 * The function change the ball's type correspondingly to the key that was pressed.
	 * @parm  e saves the key event details.
	 ***/
	public void keyPressed(KeyEvent event)
	{
		int keyCode = event.getKeyCode();
		if(keyCode == KeyEvent.VK_1)
		{
			ball = new FireBall(ball.dballx,ball.dbally);
			repaint();
		}
		if(keyCode == KeyEvent.VK_2)
		{
			ball = new WaterBall(ball.dballx,ball.dbally);
			repaint();
		}
		if(keyCode == KeyEvent.VK_3)
		{
			ball = new ElectricBall(ball.dballx,ball.dbally);
			repaint();
		}
		if(keyCode == KeyEvent.VK_4)
		{
			ball = new WoodBall(ball.dballx,ball.dbally);
			repaint();
		}
		if(keyCode == KeyEvent.VK_F7)
		{
			ball = new ElementalBall(ball.dballx,ball.dbally);
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

	/**
	 * The function moves the bat in correspondingly to the mouse x point.
	 * @parm  e saves the mouse event details.
	 ***/
	public void mouseMoved(MouseEvent e)
	{
		e.getComponent().setFocusable(true);
		e.getComponent().requestFocus();
		if(ballMove)
		{
			Point locationOnPanel = e.getPoint();
			if (locationOnPanel.getX() <= 570) {
				bat.x = (int) (locationOnPanel.getX());
				repaint();
			} else {
				bat.x = 570;
			}
		}
	}

	/**
	 * The function moves the ball and the bat to their initial location.
	 * @parm  e saves the action event details.
	 ***/
	public void actionPerformed(ActionEvent e)
	{
		ball.x = BALL_START_X;
		ball.dballx = BALL_START_X;
		ball.y = BALL_START_Y;
		ball.dbally = BALL_START_Y;
		bat.x =  BAT_START_X;
		bat.y = BAT_START_Y;
		ballDisappear = false;
		chooseRandomDirection();
		timerForBallDown.stop();
		repaint();
	}

	/**
	 * The function start the ball movement after mouse click.
	 * @parm  e saves the mouse event details.
	 ***/
	public void mouseClicked(MouseEvent e)
	{
		ballMove = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}