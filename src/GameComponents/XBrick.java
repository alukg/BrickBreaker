package GameComponents;



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
    public void impact(RegularBall regularBall) {

    }

    @Override
    public void impact(FireBall fireBall ) {

    }

    @Override
    public void impact(WaterBall waterBall) {

    }

    @Override
    public void impact(TreeBall treeBall) {

    }

    @Override
    public void impact(ElectricBall electricBall) {

    }
}
