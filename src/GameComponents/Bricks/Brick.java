package GameComponents.Bricks;
import GameComponents.Balls.*;
import GameComponents.Board;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * The class that represents a block in the game.
 */
abstract public class Brick extends Rectangle implements IVisitor
{
	//Variables
	protected int index;
	protected Image image;
	
	//Constructors
	protected Brick(int x, int y, int index)
	{
		super(x,y,45,15);
		this.index=index;
	}

	/*public Brick(Brick b)
	{
		 super(b.x, b.y, b.width, b.height);
		 this.color = b.color;
	}*/
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
				System.out.println("change x direction");
				if(!(hitPoint.getY() - Board.movey < brick.y  || hitPoint.getY() - Board.movey > brick.y + brick.height - 1 ))
					Board.movex = -Board.movex;
			}
			if ((hitPoint.getY() == brick.y || hitPoint.getY() == brick.y + brick.height - 1)) {
				System.out.println("change y direction");
				if(!(hitPoint.getX() -  Board.movex < brick.x || hitPoint.getX() - Board.movex > brick.x + brick.width - 1 ))
					Board.movey = -Board.movey;
			}
		}
	}
	public Image getImage()
	{
		return this.image;
	}

	//public abstract void visit(Ball ball);

	public abstract void visit(ElementalBall elementalBall );

	public abstract void visit(FireBall fireBall);

	public abstract void visit(WaterBall waterBall);

	public abstract void visit(WoodBall treeBall) ;

	public abstract void visit(ElectricBall electricBall);
}