package GameComponents.Balls;

import GameComponents.Bricks.IVisitor;

import java.awt.*;

/**
 * Ball class from fire type.
 */
public class FireBall extends Ball{

    /**
     * Constructor.
     */
    public FireBall(double ballx, double bally){
        super(ballx,bally);
        color = Color.RED;
    }

    @Override
    public void impact(IVisitor visitor) {
        visitor.visit(this);
    }
}
