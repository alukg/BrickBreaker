package GameComponents.Balls;

import GameComponents.Bricks.IVisitor;

import java.awt.*;

/**
 * Created by guyal on 08/06/2016.
 */
public class ElementalBall extends Ball{

    public ElementalBall(double ballx, double bally){
        super(ballx,bally);
        color = Color.BLACK;
    }

    @Override
    public void impact(IVisitor visitor) {
        visitor.visit(this);
    }
}
