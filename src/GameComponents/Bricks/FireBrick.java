package GameComponents.Bricks;
import GameComponents.Balls.*;
import java.awt.*;

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
