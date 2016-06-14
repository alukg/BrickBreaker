package GameComponents;

import GameComponents.Balls.*;
import GameComponents.Bricks.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;

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
	private Rectangle bat = new Rectangle(BAT_START_X,BAT_START_Y,80,10);

	private boolean gameFinished = false;
	public static boolean ballDisappear = false;
	public static boolean ballMove = false;
	public static double movey;
	public static double movex;

	Rectangle2D hitForPaint = null;

	public static Brick[] bricks = new Brick[80];

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
	public Board(Game game, int[][] bricks)
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

		ballDisappear = false;
		ballMove = false;

		this.game = game;
		for(int row=0;row<8;row++)
		{
			for(int col=0;col<10;col++)
			{
				//Create each brick by his type.
				switch (bricks[row][col])
				{
					case 0:
						this.bricks[row*10+col] = new RegularBrick(75+col*50,80+row*20,row*10+col);
						break;
					case 1:
						this.bricks[row*10+col] = new FireBrick(75+col*50,80+row*20,row*10+col);
						break;
					case 2:
						this.bricks[row*10+col] = new WaterBrick(75+col*50,80+row*20,row*10+col);
						break;
					case 3:
						this.bricks[row*10+col] = new ElectricBrick(75+col*50,80+row*20,row*10+col);
						break;
					case 4:
						this.bricks[row*10+col] = new WoodBrick(75+col*50,80+row*20,row*10+col);
						break;
					case 5:
						this.bricks[row*10+col] = new XBrick(75+col*50,80+row*20,row*10+col);
						break;
					case 6:
						this.bricks[row*10+col] = new PlusBrick(75+col*50,80+row*20,row*10+col);
						break;
					case 7:
						this.bricks[row*10+col] = new RectangleBrick(75+col*50,80+row*20,row*10+col);
						break;
				}

			}
		}
		ball = new FireBall(BALL_START_X,BALL_START_Y);

		chooseRandomDirection();

		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		timerForBallDown = new Timer(3000, this);

	}

	private void chooseRandomDirection() {
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

	public void paint (Graphics g){
		g.drawImage(background, 0, 0, null);
		g.setColor(ball.getColor());
		g.fillRect(ball.x, ball.y, ball.width, ball.height);
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

		if(hitForPaint != null){
			System.out.println("paint point");
			g.setColor(Color.MAGENTA);
			g.fillOval((int)hitForPaint.getX(), (int)hitForPaint.getY(), 5, 5);
		}
	}

	public void run()
	{
		while (!gameFinished) {
			if (ballMove)
			{
				for (int i = 0; i < bricks.length; i++) {
					if (bricks[i] != null) {
						if (ball.intersects(bricks[i])) {
							Rectangle2D r = bricks[i].createIntersection(ball);
							hitForPaint = r;
							System.out.println("intersects: " + r.getX() + " , " + r.getY());
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
				Thread.sleep(30);
			} catch (Exception e)
			{

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
		ballDisappear = false;
		//ballMove = true;
		chooseRandomDirection();
		timerForBallDown.stop();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		System.out.println("Mouse clicked");
		//int keyCode = event.getKeyCode();
		//if(keyCode == KeyEvent.VK_SPACE)
		//{
			ballMove = true;
		//}
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