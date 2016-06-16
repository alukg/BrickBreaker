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
 * *  The class that represent an regular brick

 */
public class RegularBrick extends Brick
{
    //Constructor
    public RegularBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.regularImage;
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
     * The function change the ball direction and kill the brick.
     *
     * @param  fireBall  saves the ball that hit the brick
     */
    public void visit(FireBall fireBall)
    {
        Rectangle2D hitPoint = this.createIntersection(fireBall);
        ChangeDirection(hitPoint,this);
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
