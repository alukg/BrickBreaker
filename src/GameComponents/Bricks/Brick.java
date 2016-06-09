package GameComponents.Bricks;
import GameComponents.Balls.*;
import com.sun.org.apache.bcel.internal.classfile.Visitor;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * The class that represents a block in the game.
 *
 */
abstract public class Brick extends Rectangle implements IVisitor

{
	//Variables
	protected Color color;
	protected int index;
	
	//Constructors
	protected Brick(int x, int y, int index)
	{
		super(x,y,45,15);
		this.index=index;
		/*switch (type)
		{
			case 0:
				color = Color.BLACK;
				break;
			case 1:
				color = Color.RED;
				break;
			case 2:
				color = Color.BLUE;
				break;
			case 3:
				color = Color.YELLOW;
				break;
			case 4:
				color = new Color(110, 44, 15);
				break;
			case 5:
				color = Color.MAGENTA;
				break;
			case 6:
				color = Color.MAGENTA;
				break;
			case 7:
				color = Color.MAGENTA;
				break;
		}*/
	}

	/*public Brick(Brick b)
	{
		 super(b.x, b.y, b.width, b.height);
		 this.color = b.color;
	}*/

	public Color getColor()
	{
		return this.color;
	}

	//public abstract void visit(Ball ball);

	public abstract void visit(ElementalBall elementalBall );

	public abstract void visit(FireBall fireBall);

	public abstract void visit(WaterBall waterBall);

	public abstract void visit(WoodBall treeBall) ;

	public abstract void visit(ElectricBall electricBall);
}