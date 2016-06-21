package GameComponents.Bricks;
import GameComponents.Balls.*;
import GameComponents.Board;

import GameComponents.Game;
import java.awt.geom.Rectangle2D;

/**
 *  The class that represent an X brick
 */
public class XBrick extends Brick {
    //Constructor
    public XBrick(int x, int y, int index) {
        super(x, y, index);
        image = Board.xImage;
    }

    /**
     * Private function that changes the ball direction and kill all the bricks that are diagonal to him.
     *
     * @param ball saves the ball that hit the brick
     */
    private void ballIntersection(Ball ball) {
        Rectangle2D hitPoint = this.createIntersection(ball);
        ChangeDirection(hitPoint, this);
        int row = index / 10;
        int col = index % 10;

        //Kill all bricks in x (right,down)
        int tmpRow = row;
        int tmpCol = col;

        while (tmpCol < 10 && tmpRow < 8) {
            if (Board.bricks[tmpRow * 10 + tmpCol] != null) {
                Board.bricks[tmpRow * 10 + tmpCol] = null;
                Game.addDeadBrick();
            }
            tmpCol++;
            tmpRow++;
        }

        //Kill all bricks in x (left, down)
        tmpRow = row;
        tmpCol = col;

        while (tmpCol > 0 && tmpRow < 8) {
            if (Board.bricks[tmpRow * 10 + tmpCol] != null) {
                Board.bricks[tmpRow * 10 + tmpCol] = null;
                Game.addDeadBrick();
            }
            tmpCol--;
            tmpRow++;
        }


        //Kill all bricks in x (left,up)
        tmpRow = row;
        tmpCol = col;

        while (tmpCol > 0 && tmpRow >= 0) {
            if (Board.bricks[tmpRow * 10 + tmpCol] != null) {
                Board.bricks[tmpRow * 10 + tmpCol] = null;
                Game.addDeadBrick();
            }
            tmpCol--;
            tmpRow--;
        }


        //Kill all bricks in x (right,up)
        tmpRow = row;
        tmpCol = col;

        while (tmpCol < 10 && tmpRow >= 0) {
            if (Board.bricks[tmpRow * 10 + tmpCol] != null) {
                Board.bricks[tmpRow * 10 + tmpCol] = null;
                Game.addDeadBrick();
            }
            tmpCol++;
            tmpRow--;
        }
    }

    /**
     * The function change the ball direction and kill all the bricks that are diagonal to him.
     *
     * @param elementalBall saves the ball that hit the brick
     */
    public void visit(ElementalBall elementalBall) {

        ballIntersection(elementalBall);
    }

    /**
     * The function change the ball direction and kill all the bricks that are diagonal to him.
     *
     * @param fireBall saves the ball that hit the brick
     */
    public void visit(FireBall fireBall) {
        ballIntersection(fireBall);

    }

    /**
     * The function change the ball direction and kill all the bricks that are diagonal to him.
     *
     * @param waterBall saves the ball that hit the brick
     */
    public void visit(WaterBall waterBall) {

        ballIntersection(waterBall);
    }

    /**
     * The function change the ball direction and kill all the bricks that are diagonal to him.
     *
     * @param woodBall saves the ball that hit the brick
     */
    public void visit(WoodBall woodBall) {

        ballIntersection(woodBall);
    }
    /**
     * The function change the ball direction and kill all the bricks that are diagonal to him.
     *
     * @param electricBall saves the ball that hit the brick
     */
    public void visit(ElectricBall electricBall) {

        ballIntersection(electricBall);
    }
}