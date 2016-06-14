package GameComponents.Bricks;
import GameComponents.Balls.*;
import GameComponents.Board;

import GameComponents.Game;

import java.awt.geom.Rectangle2D;


/**
 * Created by yaniv on 08/06/2016.
 */
public class ElectricBrick extends Brick
{
    public ElectricBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.electricImage;
    }
    //Direction doesn't changed
    //brick disappear
    public void visit(ElementalBall elementalBall)
    {
        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }

    //Direction change
    //brick disappear
    public void visit(FireBall fireBall )
    {
        Rectangle2D hitPoint = this.createIntersection(fireBall);
        ChangeDirection(hitPoint,this);
        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }


    //Ball disappear
    //brick doesn't disappear
    public void visit(WaterBall waterBall)
    {
        Board.ballMove = false;
        Board.ballDisappear = true;
        Board.timerForBallDown.start();
    }
    //Direction doesn't change
    //brick disappear
    public void visit(WoodBall woodBall)
    {
        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }
    //Direction change
    //brick doesn't disappear
    public void visit(ElectricBall electricBall)
    {
        Rectangle2D hitPoint = this.createIntersection(electricBall);
        ChangeDirection(hitPoint,this);

    }
}
