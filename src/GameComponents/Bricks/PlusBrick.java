package GameComponents.Bricks;

import GameComponents.Balls.*;
import GameComponents.Board;

import GameComponents.Game;

import java.awt.geom.Rectangle2D;


/**
 * Created by yaniv on 08/06/2016.
 */
public class PlusBrick extends Brick
{
    public PlusBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.plusImage;
    }

    public void visit(ElementalBall elementalBall)
    {

        Rectangle2D hitPoint = this.createIntersection(elementalBall);
        if( ((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y))
        {
            Board.movex = -Board.movex;
            elementalBall.addXByOne();
        } else
        {
            Board.movey = -Board.movey;
            elementalBall.addYByOne();
        }
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

    @Override
    public void visit(FireBall fireBall ) {

        Rectangle2D hitPoint = this.createIntersection(fireBall);
        if (((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y)) {
            Board.movex = -Board.movex;
            fireBall.addXByOne();
        } else {
            Board.movey = -Board.movey;
            fireBall.addYByOne();
        }
        int row = index / 10;
        int col = index % 10;
        //Kill all bricks in same col;
        for (int i = 0; i < 8; i++)
        {
            if (Board.bricks[i * 10 + col] != null)
            {
                Board.bricks[i * 10 + col] = null;
                Game.addDeadBrick();
            }
        }
        //Kill all bricks in same row;
        for (int i = 0; i < 10; i++)
        {
            if (Board.bricks[row*10 + i] != null) {
                Board.bricks[row*10 + i] = null;
                Game.addDeadBrick();
            }
        }

    }

    public void visit(WaterBall waterBall) {

        Rectangle2D hitPoint = this.createIntersection(waterBall);
        if (((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y)) {
            Board.movex = -Board.movex;
            waterBall.addXByOne();
        } else {
            Board.movey = -Board.movey;
            waterBall.addYByOne();
        }
        int row = index / 10;
        int col = index % 10;
        //Kill all bricks in same col;
        for (int i = 0; i < 8; i++) {
            if (Board.bricks[i * 10 + col] != null) {
                Board.bricks[i * 10 + col] = null;
                Game.addDeadBrick();
            }
        }
        //Kill all bricks in same row;
        for (int i = 0; i < 10; i++) {
            if (Board.bricks[row * 10 + i] != null) {
                Board.bricks[row * 10 + i] = null;
                Game.addDeadBrick();
            }
        }
    }

    public void visit(WoodBall woodBall)  {

        Rectangle2D hitPoint = this.createIntersection(woodBall);
        if (((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y)) {
            Board.movex = -Board.movex;
            woodBall.addXByOne();
        } else {
            Board.movey = -Board.movey;
            woodBall.addYByOne();
        }
        int row = index / 10;
        int col = index % 10;
        //Kill all bricks in same col;
        for (int i = 0; i < 8; i++) {
            if (Board.bricks[i * 10 + col] != null) {
                Board.bricks[i * 10 + col] = null;
                Game.addDeadBrick();
            }
        }
        //Kill all bricks in same row;
        for (int i = 0; i < 10; i++) {
            if (Board.bricks[row * 10 + i] != null) {
                Board.bricks[row * 10 + i] = null;
                Game.addDeadBrick();
            }
        }
    }

    public void visit(ElectricBall electricBall) {

        Rectangle2D hitPoint = this.createIntersection(electricBall);
        if (((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y)) {
            Board.movex = -Board.movex;
            electricBall.addXByOne();
        } else {
            Board.movey = -Board.movey;
            electricBall.addYByOne();
        }
        int row = index / 10;
        int col = index % 10;
        //Kill all bricks in same col;
        for (int i = 0; i < 8; i++) {
            if (Board.bricks[i * 10 + col] != null) {
                Board.bricks[i * 10 + col] = null;
                Game.addDeadBrick();
            }
        }
        //Kill all bricks in same row;
        for (int i = 0; i < 10; i++) {
            if (Board.bricks[row * 10 + i] != null) {
                Board.bricks[row * 10 + i] = null;
                Game.addDeadBrick();
            }
        }
    }
}
