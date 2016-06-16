package GameComponents.Bricks;

import GameComponents.Balls.*;
import GameComponents.Board;

import GameComponents.Game;

import java.awt.geom.Rectangle2D;

/**
 *  The class that represent an wood brick
 */
public class WoodBrick  extends Brick
{
    //Constructor
    public WoodBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.woodImage;
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
     * The function kill the brick.
     *
     * @param  fireBall  saves the ball that hit the brick
     */
    public void visit(FireBall fireBall )
    {
        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }
    /**
     * The function change the ball direction and kill the brick.
     *
     * @param  waterBall  saves the ball that hit the brick
     */
    public void visit(WaterBall waterBall)
    {
        Rectangle2D hitPoint = this.createIntersection(waterBall);
        ChangeDirection(hitPoint,this);
        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }
    /**
     * The function change the ball direction.
     *
     * @param  woodBall  saves the ball that hit the brick
     */
    public void visit(WoodBall woodBall)
    {
        Rectangle2D hitPoint = this.createIntersection(woodBall);
        ChangeDirection(hitPoint,this);
    }
    /**
     * The function disappears the ball and takes the ball and bat to their initial point.
     *
     * @param  electricBall  saves the ball that hit the brick
     */
    public void visit(ElectricBall electricBall)
    {
        Board.ballMove = false;
        Board.ballDisappear = true;
        Board.timerForBallDown.start();
    }
}

