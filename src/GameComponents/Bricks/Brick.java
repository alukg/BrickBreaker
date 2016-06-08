package GameComponents.Bricks;

import com.sun.org.apache.bcel.internal.classfile.Visitor;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * The class that represents a block in the game.
 *
 */
abstract class Brick extends Rectangle implements GameComponents.Visitor

{
	//Variables
	protected Color color;
	
	//Constructors
	protected Brick(int x, int y, int width, int height)
	{
		super(x,y,width,height);
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

	public abstract void impact(RegularBall regularBall );

	public abstract void impact(FireBall fireBall);

	public abstract void impact(WaterBall waterBall);

	public abstract void impact(TreeBall treeBall) ;

	public abstract void impact(ElectricBall electricBall);
}