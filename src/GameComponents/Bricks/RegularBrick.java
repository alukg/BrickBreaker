package GameComponents.Bricks;

import GameComponents.Balls.*;
import GameComponents.Board;
import GameComponents.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

/**
 * Created by yaniv on 09/06/2016.
 */
public class RegularBrick extends Brick
{
    public RegularBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.regularImage;
    }
    @Override
    public  void visit(Ball ball)
    {
        ball.impact(this);
    }
    public void visit(ElementalBall elementalBall)
    {
        /*Rectangle2D hitPoint = this.createIntersection(elementalBall);
        if( ((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y))
        {
            Board.movex = -Board.movex;
        } else
        {
            Board.movey = -Board.movey;
        }
        //Board.bricks[brickNum] = null;
        Game.addDeadBrick();*/
    }

    @Override
    public void visit(FireBall fireBall)
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
        Game.addDeadBrick();
    }

    @Override
    public void visit(WaterBall waterBall)
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
        Game.addDeadBrick();
    }
    @Override
    public void visit(WoodBall woodBall)
    {
        Rectangle2D hitPoint = this.createIntersection(woodBall);
        if( ((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y))
        {
            Board.movex = -Board.movex;
        } else
        {
            Board.movey = -Board.movey;
        }
        // Board.bricks[brickNum] = null;
        Game.addDeadBrick();
    }

    @Override
    public void visit(ElectricBall electricBall)
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
        Game.addDeadBrick();
    }
}
