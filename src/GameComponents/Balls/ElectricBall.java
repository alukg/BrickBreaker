package GameComponents.Balls;

import GameComponents.Bricks.IVisitor;

import java.awt.*;

/**
 * Ball class from electric type.
 */
public class ElectricBall extends Ball{

    /**
     * Constructor.
     */
    public ElectricBall(double ballx, double bally){
        super(ballx,bally);
        color = Color.YELLOW;
    }

    @Override
    public void impact(IVisitor visitor) {
        visitor.visit(this);
    }
}
