package GameComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

/**
 * The class that represents the board in the game.
 */
public class Board extends JPanel implements Runnable, MouseMotionListener, KeyListener , ActionListener , MouseListener
{
	//Variables
	private Game game;
	private int ballx = 319;
	private int bally = 578;
	private Timer timerForBallDown;
	private int batx = 285;
	private int baty = 590;

	private Ball ball = new RegularBall(ballx,bally,12,12);
	private Rectangle bat = new Rectangle(batx,baty,80,10);

	private boolean gameFinished = false;
	private boolean ballFallDown = false;
	private boolean ballMove = false;
	public static int movey = -1;
	public static int movex = 1;

	public static Brick[] bricks = new Brick[80];

	//Constructors
	public Board(Game game, int[][] bricks)
	{
		this.game = game;
		for(int row=0;row<8;row++)
		{
			for(int col=0;col<10;col++)
			{
				//Create each brick by his type.
				switch (bricks[row][col])
				{
					case 0:
						this.bricks[row*10+col] = new NormalBrick(75+col*50,80+row*20,45,15);
						break;
					case 1:
						this.bricks[row*10+col] = new FireBrick(75+col*50,80+row*20,45,15);
						break;
					case 2:
						this.bricks[row*10+col] = new WaterBrick(75+col*50,80+row*20,45,15);
						break;
					case 3:
						this.bricks[row*10+col] = new ElectricBrick(75+col*50,80+row*20,45,15);
						break;
					case 4:
						this.bricks[row*10+col] = new TreeBrick(75+col*50,80+row*20,45,15);
						break;
					case 5:
						this.bricks[row*10+col] = new XBrick(75+col*50,80+row*20,45,15);
						break;
					case 6:
						this.bricks[row*10+col] = new PlusBrick(75+col*50,80+row*20,45,15);
						break;
					case 7:
						this.bricks[row*10+col] = new RectangleBrick(75+col*50,80+row*20,45,15);
						break;
					//this.bricks[row*10+col] = new Brick(75+col*50,80+row*20,45,15,bricks[row][col]);
				}

			}
		}
		this.addKeyListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
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

	public void run()
	{
		while (!gameFinished)
		{
			if(ballMove)
			{
				for (int i = 0; i < bricks.length; i++)
				{
					if (bricks[i] != null)
					{
						if (bricks[i].intersects(ball))
						{

							//Rectangle2D r = bricks[i].createIntersection(ball);
							//if ((r.getX() == bricks[i].getX() || r.getX() == (bricks[i].getX() + bricks[i].width - 1)) && (r.getY() <= bricks[i].getY() + bricks[i].height - 2 && r.getY() > bricks[i].getY()))
							//{
							//	movex = -movex;
								bricks[i].impact(ball);
							}
							else
							{
								bricks[i].impact(ball);
								//movey = -movey;
							}
							if(game.getCounter()==80){
								gameFinished=true;
							}
							break;
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
				if (ball.intersects(bat))
				{
					Rectangle2D r = bat.createIntersection(ball);
					if (r.getY() <= 590)
						movey = movey * -1;
				}
			}
			try
			{
				Thread.sleep(5);
			} catch (Exception e)
			{

			}
		}
		game.finish();
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		//System.out.println("Key Pressed");
		//int keyCode = event.getKeyCode();
		//if(keyCode == KeyEvent.VK_SPACE)
		//{
		//	ballMove = true;
		//}
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
		ball.x = 319;
		ball.y = 578;
		bat.x =  250;
		bat.y = 590;
		ballFallDown = false;
		//ballMove = true;
		movey = -movey;
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