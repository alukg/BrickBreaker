package GameComponents.Bricks;

import GameComponents.Balls.*;
import GameComponents.Board;
import GameComponents.Game;
import java.awt.geom.Rectangle2D;

/**
 * Created by yaniv on 08/06/2016.
 */
public class RectangleBrick extends Brick
{
    public RectangleBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.rectangleImage;
    }
    public void visit(ElementalBall elementalBall) {
        Rectangle2D hitPoint = this.createIntersection(elementalBall);
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
    }

    public void visit(FireBall fireBall ){
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
        int row = index / 10;
        int col = index % 10;

        //kill center ball
        Board.bricks[row * 10 + col] = null;
        Game.addDeadBrick();


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
    }
    public void visit(WaterBall waterBall) {
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
        int row = index / 10;
        int col = index % 10;

        //kill center ball
        Board.bricks[row * 10 + col] = null;
        Game.addDeadBrick();


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
    }
    public void visit(WoodBall woodBall) {
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
        int row = index / 10;
        int col = index % 10;

        //kill center ball
        Board.bricks[row * 10 + col] = null;
        Game.addDeadBrick();


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
    }
    public void visit(ElectricBall electricBall){
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
        int row = index / 10;
        int col = index % 10;

        //kill center ball
        Board.bricks[row * 10 + col] = null;
        Game.addDeadBrick();


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
    }
}