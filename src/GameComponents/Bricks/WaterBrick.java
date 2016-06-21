package GameComponents.Bricks;

import GameComponents.Balls.*;
import GameComponents.Board;

import GameComponents.Game;

import java.awt.geom.Rectangle2D;

/**
 *  The class that represent an water brick
 */
public class WaterBrick extends Brick
{
    //Constructor

    public WaterBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.waterImage;
    }

    /**
     * The function kill the brick.
     *
     * @param  elementalBall  saves the ball that hit the brick
     */
    public void visit(ElementalBall elementalBall)
    {
        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }
    /**
     * The function disappears the ball and takes the ball and bat to their initial point.
     *
     * @param  fireBall  saves the ball that hit the brick
     */
    public void visit(FireBall fireBall )
    {
        Board.ballMove = false;
        Board.ballDisappear = true;
        Board.timerForBallDown.start();
    }
    /**
     * The function change the ball direction.
     *
     * @param  waterBall  saves the ball that hit the brick
     */
    public void visit(WaterBall waterBall)
    {
        Rectangle2D hitPoint = this.createIntersection(waterBall);
        ChangeDirection(hitPoint,this);
    }
    /**
     * The function change the ball direction and kill the brick.
     *
     * @param  woodBall  saves the ball that hit the brick
     */
    public void visit(WoodBall woodBall)
    {
        Rectangle2D hitPoint = this.createIntersection(woodBall);
        ChangeDirection(hitPoint,this);
        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }
    /**
     * The function kill the brick.
     *
     * @param  electricBall  saves the ball that hit the brick
     */
    public void visit(ElectricBall electricBall)
    {
        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }
}