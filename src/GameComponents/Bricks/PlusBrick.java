package GameComponents.Bricks;

import GameComponents.Balls.*;

import java.awt.*;

/**
 * Created by yaniv on 08/06/2016.
 */
public class PlusBrick extends Brick
{
    public PlusBrick(int x, int y, int width, int height, int index)
    {
        super(x,y,width,height,index);
        this.color = Color.MAGENTA;
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
