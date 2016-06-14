package GameComponents.Balls;

import GameComponents.Bricks.IVisitor;

import java.awt.*;

/**
 * Created by guyal on 08/06/2016.
 */
public class WaterBall extends Ball{

    public WaterBall(double ballx, double bally){
        super(ballx,bally);
        color = Color.BLUE;
    }

    @Override
    public void impact(IVisitor visitor) {
        visitor.visit(this);
    }
}
