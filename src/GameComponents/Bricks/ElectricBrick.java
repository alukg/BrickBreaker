package GameComponents.Bricks;
import GameComponents.Balls.*;
import GameComponents.Board;

/**
 * Created by yaniv on 08/06/2016.
 */
public class ElectricBrick extends Brick
{
    public ElectricBrick(int x, int y, int index)
    {
        super(x,y,index);
        image = Board.electricImage;
    }
    @Override
    public  void visit(Ball ball)
    {
        ball.impact(this);
    }

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
