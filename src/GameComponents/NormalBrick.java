package GameComponents;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by yaniv on 08/06/2016.
 */
public class NormalBrick extends Brick
{
    public NormalBrick(int x, int y, int width, int height)
    {
        super(x,y,width,height);
        this.color = Color.BLACK;
    }
    @Override
    public void impact(RegularBall regularBall)
    {
        Rectangle2D hitPoint = this.createIntersection(regularBall);
        if( ((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
        (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y))
        {
            Board.movex = -Board.movex;
        } else
        {
            Board.movey = -Board.movey;
        }
        //Board.bricks[brickNum] = null;
        Game.addOneForCounter();
    }

    @Override
    public void impact(FireBall fireBall)
    {
        Rectangle2D hitPoint = this.createIntersection(fireBall);
        if( ((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y))
        {
            Board.movex = -Board.movex;
        } else
        {
            Board.movey = -Board.movey;
        }
       // Board.bricks[brickNum] = null;
        Game.addOneForCounter();
    }

    @Override
    public void impact(WaterBall waterBall)
    {
        Rectangle2D hitPoint = this.createIntersection(waterBall);
        if( ((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y))
        {
            Board.movex = -Board.movex;
        } else
        {
            Board.movey = -Board.movey;
        }
       // Board.bricks[brickNum] = null;
        Game.addOneForCounter();
    }
    @Override
    public void impact(TreeBall treeBall)
    {
        Rectangle2D hitPoint = this.createIntersection(treeBall);
        if( ((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y))
        {
            Board.movex = -Board.movex;
        } else
        {
            Board.movey = -Board.movey;
        }
       // Board.bricks[brickNum] = null;
        Game.addOneForCounter();
    }

    @Override
    public void impact(ElectricBall electricBall)
    {
        Rectangle2D hitPoint = this.createIntersection(electricBall);
        if( ((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y))
        {
            Board.movex = -Board.movex;
        } else
        {
            Board.movey = -Board.movey;
        }
        //Board.bricks[brickNum] = null;
        Game.addOneForCounter();
    }
}
