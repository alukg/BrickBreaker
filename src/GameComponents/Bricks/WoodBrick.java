package GameComponents.Bricks;

import GameComponents.Balls.*;
import GameComponents.Board;

import GameComponents.Game;

import java.awt.geom.Rectangle2D;

/**
 * Created by yaniv on 09/06/2016.
 */
public class WoodBrick  extends Brick
{
    public WoodBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.woodImage;
    }

    //Direction doesn't changed
    //brick disappear
    public void visit(ElementalBall elementalBall)
    {

        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }
    //Direction doesn't change
    //brick disappear
    public void visit(FireBall fireBall )
    {
        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }
    //Direction change
    //brick disappear
    public void visit(WaterBall waterBall)
    {
        Rectangle2D hitPoint = this.createIntersection(waterBall);
        if ((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1))
        {
            System.out.println("change x direction");
            Board.movex = -Board.movex;
        }
        if((hitPoint.getY() == this.y || hitPoint.getY() == this.y+ this.height -1))
        {
            System.out.println("change y direction");
            Board.movey = -Board.movey;
        }
        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }
    //Direction change
    //brick doesn't disappear
    public void visit(WoodBall woodBall)
    {
        Rectangle2D hitPoint = this.createIntersection(woodBall);
        //Change direction
        if ((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) {
            System.out.println("change x direction");
            Board.movex = -Board.movex;
        }
        if((hitPoint.getY() == this.y || hitPoint.getY() == this.y+ this.height -1))
        {
            System.out.println("change y direction");
            Board.movey = -Board.movey;
        }
    }
    //Ball disappear
    //brick doesn't disappear
    public void visit(ElectricBall electricBall)
    {
        Board.ballMove = false;
        Board.ballDisappear = true;
        Board.timerForBallDown.start();
    }
}

