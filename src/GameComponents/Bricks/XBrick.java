package GameComponents.Bricks;
import GameComponents.Balls.*;
import GameComponents.Board;
import GameComponents.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by yaniv on 08/06/2016.
 */
public class XBrick extends Brick
{
    public XBrick(int x, int y, int index)
    {
        super(x,y,index);
        color = new Color(110, 44, 15);
    }
    public void visit(ElementalBall elementalBall) {

        Rectangle2D hitPoint = this.createIntersection(elementalBall);
        if (((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y)) {
            Board.movex = -Board.movex;
        } else {
            Board.movey = -Board.movey;
        }
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

        while (tmpCol >0 && tmpRow < 8) {
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


    public void visit(FireBall fireBall ) {

        Rectangle2D hitPoint = this.createIntersection(fireBall);
        if (((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y)) {
            Board.movex = -Board.movex;
        } else {
            Board.movey = -Board.movey;
        }
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

        while (tmpCol >0 && tmpRow < 8) {
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

    public void visit(WaterBall waterBall){

        Rectangle2D hitPoint = this.createIntersection(waterBall);
        if (((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y)) {
            Board.movex = -Board.movex;
        } else {
            Board.movey = -Board.movey;
        }
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

        while (tmpCol >0 && tmpRow < 8) {
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


    public void visit(WoodBall woodBall) {

        Rectangle2D hitPoint = this.createIntersection(woodBall);
        if (((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y)) {
            Board.movex = -Board.movex;
        } else {
            Board.movey = -Board.movey;
        }
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

        while (tmpCol >0 && tmpRow < 8) {
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

    public void visit(ElectricBall electricBall) {

        Rectangle2D hitPoint = this.createIntersection(electricBall);
        if (((hitPoint.getX() == this.x || hitPoint.getX() == this.x + this.width - 1)) &&
                (hitPoint.getY() <= this.y + this.height - 2 && hitPoint.getX() > this.y)) {
            Board.movex = -Board.movex;
        } else {
            Board.movey = -Board.movey;
        }
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

        while (tmpCol >0 && tmpRow < 8) {
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

}