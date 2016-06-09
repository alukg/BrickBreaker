package GameComponents.Bricks;

import GameComponents.Balls.*;

/**
 * Created by yaniv on 08/06/2016.
 */
public class WaterBrick extends Brick
{
    public WaterBrick(int x, int y, int width, int height)
    {
        super(x,y,width,height);
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