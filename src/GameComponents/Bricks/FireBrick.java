package GameComponents.Bricks;
import GameComponents.Balls.*;
import GameComponents.Board;
import GameComponents.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by yaniv on 08/06/2016.
 */
public class FireBrick extends Brick
{
    public FireBrick(int x, int y, int index)
    {
        super(x,y,index);
        this.color = Color.RED;
    }


    //Direction doesn't changed
    //brick disappear
    public void visit(ElementalBall elementalBall)
    {

        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }

    //Direction changed
    //brick doesn't disappear
    public void visit(FireBall fireBall )
    {
        Rectangle2D hitPoint = this.createIntersection(fireBall);
        //Change direction
        if( ((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y))
        {
            Board.movex = -Board.movex;
        } else
        {
            Board.movey = -Board.movey;
        }
    }
    //Direction doesn't changed
    //brick disappear
    public void visit(WaterBall waterBall)
    {
        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }

    //Ball disappear
    //brick doesn't disappear
    public void visit(WoodBall treeBall)
    {
        Board.ballMove = false;
        Board.ballDisappear = true;

        Board.timerForBallDown.start();
    }

    @Override
    //Direction changed
    //brick disappear
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
            Board.bricks[this.index] = null;
            Game.addDeadBrick();
    }
}
