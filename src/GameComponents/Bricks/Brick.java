package GameComponents.Bricks;
import GameComponents.Balls.*;
import GameComponents.Board;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * An abstract class that represents a brick in the game.
 */
abstract public class Brick extends Rectangle implements IVisitor
{
	//Variables
	protected int index;
	protected Image image;
	
	//Constructors
	protected Brick(int x, int y, int index)
	{
		super(x,y,45,22);
		this.index=index;
	}


	/**
	 * The function change the ball direction in correspondingly to the hit and the current direction.
	 *
	 * @param  hitPoint  saves the intersection point
	 * @param  brick saves which type of brick had the intersection
	 */
	public void ChangeDirection(Rectangle2D hitPoint, Brick brick)
	{
		if((hitPoint.getX() -  Board.movex < brick.x || hitPoint.getX() - Board.movex > brick.x + brick.width - 1 )
				&& (hitPoint.getY() - Board.movey < brick.y  || hitPoint.getY() - Board.movey > brick.y + brick.height - 1 ))
		{
			Board.movex = -Board.movex;
			Board.movey = -Board.movey;
		}
		else
		{
			if ((hitPoint.getX() == brick.x || hitPoint.getX() == brick.x + brick.width - 1))
			{
				//System.out.println("change x direction");
				if(!(hitPoint.getY() - Board.movey < brick.y  || hitPoint.getY() - Board.movey > brick.y + brick.height - 1 ))
					Board.movex = -Board.movex;
			}
			if ((hitPoint.getY() == brick.y || hitPoint.getY() == brick.y + brick.height - 1)) {
				//System.out.println("change y direction");
				if(!(hitPoint.getX() -  Board.movex < brick.x || hitPoint.getX() - Board.movex > brick.x + brick.width - 1 ))
					Board.movey = -Board.movey;
			}
		}
	}
	/**
	 * The function returns the image of the brick
	 *
	 * @return Image return the image of the brick
	 */
	public Image getImage()
	{
		return this.image;
	}

	/**
	 * The function change the ball direction and bricks situation when the ball is elemental ball
	 *
	 * @param  elementalBall  saves the ball that hit the brick
	 */
	public abstract void visit(ElementalBall elementalBall );

	/**
	 * The function change the ball direction and bricks situation when the ball is fire ball
	 *
	 * @param  fireBall  saves the ball that hit the brick
	 */
	public abstract void visit(FireBall fireBall);

	/**
	 * The function change the ball direction and bricks situation when the ball is water ball
	 *
	 * @param  waterBall  saves the ball that hit the brick
	 */
	public abstract void visit(WaterBall waterBall);

	/**
	 * The function change the ball direction and bricks situation when the ball is wood ball
	 *
	 * @param  woodBall  saves the ball that hit the brick
	 */
	public abstract void visit(WoodBall woodBall) ;

	/**
	 * The function change the ball direction and bricks situation when the ball is electric ball
	 *
	 * @param  electricBall  saves the ball that hit the brick
	 */
	public abstract void visit(ElectricBall electricBall);
}