package GameComponents.Bricks;
import GameComponents.Balls.*;

import java.awt.*;

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

	public Image getImage()
	{
		return this.image;
	}

	public abstract void visit(Ball ball);

	public abstract void visit(ElementalBall regularBall );

	public abstract void visit(FireBall fireBall);

	public abstract void visit(WaterBall waterBall);

	public abstract void visit(WoodBall treeBall) ;

	public abstract void visit(ElectricBall electricBall);
}