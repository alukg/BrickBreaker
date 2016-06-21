package GameComponents.Balls;

import GameComponents.Bricks.IVisitor;

import java.awt.*;

/**
 * Ball class from water type.
 */
public class WaterBall extends Ball{

    /**
     * Constructor.
     */
    public WaterBall(double ballx, double bally){
        super(ballx,bally);
        color = Color.BLUE;
    }

    @Override
    public void impact(IVisitor visitor) {
        visitor.visit(this);
    }
}
