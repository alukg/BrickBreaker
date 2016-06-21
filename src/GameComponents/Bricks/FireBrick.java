package GameComponents.Bricks;
import GameComponents.Balls.*;
import GameComponents.Board;

import GameComponents.Game;

import java.awt.geom.Rectangle2D;

/**
 *  The class that represent an fire brick
 */
public class FireBrick extends Brick
{
    //Constructor
    public FireBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.fireImage;
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
     * The function change the ball direction.
     *
     * @param  fireBall  saves the ball that hit the brick
     */
    public void visit(FireBall fireBall)
    {
        Rectangle2D hitPoint = this.createIntersection(fireBall);
       ChangeDirection(hitPoint,this);
    }
    /**
 * The function kill the brick.
 *
 * @param  waterBall  saves the ball that hit the brick
 */
    public void visit(WaterBall waterBall)
    {
        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }
    /**
     * The function disappears the ball and takes the ball and bat to their initial point.
     *
     * @param  woodBall  saves the ball that hit the brick
     */
    public void visit(WoodBall woodBall)
    {
        Board.ballMove = false;
        Board.ballDisappear = true;

        Board.timerForBallDown.start();
    }

    /**
     * The function change the ball direction and kill the brick.
     *
     * @param  electricBall  saves the ball that hit the brick
     */
    public void visit(ElectricBall electricBall)
    {
        Rectangle2D hitPoint = this.createIntersection(electricBall);
        ChangeDirection(hitPoint,this);
        Board.bricks[this.index] = null;
            Game.addDeadBrick();
    }
}
