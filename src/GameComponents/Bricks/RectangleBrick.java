package GameComponents.Bricks;

import GameComponents.Balls.*;
import GameComponents.Board;
import GameComponents.Game;
import java.awt.geom.Rectangle2D;

/**
 *  The class that represent an rectangle brick
 */
public class RectangleBrick extends Brick
{
    //Constructor
    public RectangleBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.rectangleImage;
    }

    /**
     * Private function that changes the ball direction and kill all the bricks that are neighbours to this brick
     *
     * @param ball saves the ball that hit the brick
     */
    private void ballIntersection(Ball ball)
    {
        Rectangle2D hitPoint = this.createIntersection(ball);
        ChangeDirection(hitPoint,this);
        int row = index / 10;
        int col = index % 10;
        if (row != 0)//Can check bricks (row-1)
        {
            if (Board.bricks[(row - 1) * 10 + col] != null) //check up
            {
                Board.bricks[(row - 1) * 10 + col] = null;
                Game.addDeadBrick();
            }
            if (col != 9)//can check brick col+1
            {
                if (Board.bricks[(row - 1) * 10 + col + 1] != null)//check right,up
                {
                    Board.bricks[(row - 1) * 10 + col + 1] = null;
                    Game.addDeadBrick();
                }
                if (Board.bricks[(row) * 10 + col + 1] != null)//check right
                {
                    Board.bricks[(row) * 10 + col + 1] = null;
                    Game.addDeadBrick();
                }
            }
            if (col != 0)//can check brick col-1
            {
                if (Board.bricks[(row - 1) * 10 + col - 1] != null)//check left,up
                {
                    Board.bricks[(row - 1) * 10 + col - 1] = null;
                    Game.addDeadBrick();
                }
                if (Board.bricks[(row) * 10 + col - 1] != null)//check left
                {
                    Board.bricks[(row) * 10 + col - 1] = null;
                    Game.addDeadBrick();
                }
            }
        }
        if (row != 7)//Can check bricks (row+1)
        {
            if (Board.bricks[(row + 1) * 10 + col] != null) //check down
            {
                Board.bricks[(row + 1) * 10 + col] = null;
                Game.addDeadBrick();
            }
            if (col != 9)//can check brick col+1
            {
                if (Board.bricks[(row + 1) * 10 + col + 1] != null)//check right,down
                {
                    Board.bricks[(row + 1) * 10 + col + 1] = null;
                    Game.addDeadBrick();
                }
                if (Board.bricks[(row) * 10 + col + 1] != null)//check right
                {
                    Board.bricks[(row) * 10 + col + 1] = null;
                    Game.addDeadBrick();
                }
            }
            if (col != 0)//can check brick col-1
            {
                if (Board.bricks[(row + 1) * 10 + col - 1] != null)//check left,down
                {
                    Board.bricks[(row + 1) * 10 + col - 1] = null;
                    Game.addDeadBrick();
                }
                if (Board.bricks[(row) * 10 + col - 1] != null)//check left
                {
                    Board.bricks[(row) * 10 + col - 1] = null;
                    Game.addDeadBrick();
                }
            }
        }
        Board.bricks[this.index] = null;
        Game.addDeadBrick();

    }
/**
 * The function change the ball direction and kill all the bricks that are neighbours to this brick
 *
 * @param elementalBall saves the ball that hit the brick
*/
public void visit(ElementalBall elementalBall)
    {
       ballIntersection(elementalBall);
    }

    /**
     * The function change the ball direction and kill all the bricks that are neighbours to this brick
     *
     * @param fireBall saves the ball that hit the brick
     */
    public void visit(FireBall fireBall ){
        ballIntersection(fireBall);
    }

    /**
     * The function change the ball direction and kill all the bricks that are neighbours to this brick
     *
     * @param waterBall saves the ball that hit the brick
     */
    public void visit(WaterBall waterBall) {
        ballIntersection(waterBall);

    }

    /**
     * The function change the ball direction and kill all the bricks that are neighbours to this brick
     *
     * @param woodBall saves the ball that hit the brick
     */
    public void visit(WoodBall woodBall) {
        ballIntersection(woodBall);
    }

    /**
     * The function change the ball direction and kill all the bricks that are neighbours to this brick
     *
     * @param electricBall saves the ball that hit the brick
     */
    public void visit(ElectricBall electricBall){
        ballIntersection(electricBall);
    }
}