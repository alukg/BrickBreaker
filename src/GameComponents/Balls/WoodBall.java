package GameComponents.Balls;

import GameComponents.Bricks.IVisitor;

import java.awt.*;

/**
 * Ball class from wood type.
 */
public class WoodBall extends Ball{

    /**
     * Constructor.
     */
    public WoodBall(double ballx, double bally){
        super(ballx,bally);
        color = Color.GREEN;
    }

    @Override
    public void impact(IVisitor visitor) {
        visitor.visit(this);
    }
}
