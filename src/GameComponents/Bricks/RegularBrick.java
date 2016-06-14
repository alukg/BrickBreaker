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
 * Created by yaniv on 09/06/2016.
 */
public class RegularBrick extends Brick
{
    public RegularBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.regularImage;
    }
    //Direction doesn't changed
    //brick disappear
    public void visit(ElementalBall elementalBall)
    {

        Board.bricks[this.index] = null;
        Game.addDeadBrick();
    }

    //Direction changed
    //brick disappear
    public void visit(FireBall fireBall)
    {
        Rectangle2D hitPoint = this.createIntersection(fireBall);
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

    //Direction changed
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
    //Direction changed
    //brick disappear
    public void visit(WoodBall woodBall)
    {
        Rectangle2D hitPoint = this.createIntersection(woodBall);
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

    //Direction changed
    //brick disappear
    public void visit(ElectricBall electricBall)
    {
        Rectangle2D hitPoint = this.createIntersection(electricBall);
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
}
