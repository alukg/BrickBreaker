package GameComponents.Bricks;

import GameComponents.Balls.*;

import java.awt.*;

/**
 * Created by yaniv on 09/06/2016.
 */
public class WoodBrick  extends Brick
{
    public WoodBrick(int x, int y, int width, int height, int index)
    {
        super(x,y,width,height,index);
        this.color = new Color(110, 44, 15);
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

