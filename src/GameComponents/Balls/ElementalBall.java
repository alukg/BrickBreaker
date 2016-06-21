package GameComponents.Balls;

import GameComponents.Bricks.IVisitor;

import java.awt.*;

/**
 * Ball class from elemental type.
 */
public class ElementalBall extends Ball{

    /**
     * Constructor.
     */
    public ElementalBall(double ballx, double bally){
        super(ballx,bally);
        color = Color.BLACK;
    }

    @Override
    public void impact(IVisitor visitor) {
        visitor.visit(this);
    }
}
