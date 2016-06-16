package GameComponents.Bricks;

import GameComponents.Balls.*;
import GameComponents.Board;

import GameComponents.Game;

import java.awt.geom.Rectangle2D;


/**
 *  The class that represent an plus brick
 */
public class PlusBrick extends Brick
{
    //Constructor
    public PlusBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.plusImage;
    }
    /**
     * Private function that changes the ball direction and
     * kill all the bricks that are in the same row or column with the specific brick
     *
     * @param  ball saves the ball that hit the brick
     */
    private void ballIntersection(Ball ball)
    {
        Rectangle2D hitPoint = this.createIntersection(ball);
        ChangeDirection(hitPoint,this);
        Board.bricks[this.index] = null;
        int row = index /10;
        int col = index % 10;
        //Kill all bricks in same col;
        for(int i = 0; i < 8; i++)
        {
            if(Board.bricks[i*10 + col] != null)
            {
                Board.bricks[i*10 + col] = null;
                Game.addDeadBrick();
            }
        }
        //Kill all bricks in same row;
        for(int i = 1; i <= 10; i++)
        {
            if(Board.bricks[row + i] != null)
            {
                Board.bricks[row + i] = null;
                Game.addDeadBrick();
            }
        }
    }
    /**
     * The function change the ball direction and
     * kill all the bricks that are in the same row or column with the specific brick
     *
     * @param  elementalBall saves the ball that hit the brick
     */
    public void visit(ElementalBall elementalBall)
    {
        ballIntersection(elementalBall);
    }
    /**
     * The function change the ball direction and
     * kill all the bricks that are in the same row or column with the specific brick
     *
     * @param  fireBall saves the ball that hit the brick
     */
    public void visit(FireBall fireBall )
    {
        ballIntersection(fireBall);
    }
    /**
     * The function change the ball direction and
     * kill all the bricks that are in the same row or column with the specific brick
     *
     * @param  waterBall saves the ball that hit the brick
     */
    public void visit(WaterBall waterBall) {

        ballIntersection(waterBall);
    }
    /**
     * The function change the ball direction and
     * kill all the bricks that are in the same row or column with the specific brick
     *
     * @param  woodBall saves the ball that hit the brick
     */
    public void visit(WoodBall woodBall)  {

        ballIntersection(woodBall);
    }
    /**
     * The function change the ball direction and
     * kill all the bricks that are in the same row or column with the specific brick
     *
     * @param  electricBall saves the ball that hit the brick
     */
    public void visit(ElectricBall electricBall)
    {
        ballIntersection(electricBall);
    }
}
