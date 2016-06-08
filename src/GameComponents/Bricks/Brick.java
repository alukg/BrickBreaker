package GameComponents.Bricks;

import java.awt.*;

/**
 * The class that represents a block in the game.
 *
 */
public class Brick extends Rectangle
{
	//Variables
	private Color color;
	
	//Constructors
	public Brick(int x, int y, int width, int height, int type)
	{
		super(x,y,width,height);
		switch (type)
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
		}
	}

	public Brick(Brick b)
	{
		 super(b.x, b.y, b.width, b.height);
		 this.color = b.color;
	}

	public Color getColor(){
		return color;
	}
}