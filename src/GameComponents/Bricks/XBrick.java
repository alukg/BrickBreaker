package GameComponents.Bricks;
import GameComponents.Balls.*;
import GameComponents.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by yaniv on 08/06/2016.
 */
public class XBrick extends Brick
{
    public XBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.xImage;
    }
    @Override
    public  void visit(Ball ball)
    {
        ball.impact(this);
    }
    @Override
    public void visit(ElementalBall regularBall) {

    }

    @Override
    public void visit(FireBall fireBall ) {

    }

    @Override
    public void visit(WaterBall waterBall) {

    }

    @Override
    public void visit(WoodBall treeBall) {

    }

    @Override
    public void visit(ElectricBall electricBall) {

    }
}