package GameComponents.Bricks;
import GameComponents.Balls.*;

import java.awt.*;

/**
 * Created by yaniv on 08/06/2016.
 */
public class XBrick extends Brick
{
    public XBrick(int x, int y, int width, int height)
    {
        super(x,y,width,height);
        color = new Color(110, 44, 15);
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