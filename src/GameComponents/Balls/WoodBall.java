package GameComponents.Balls;

import GameComponents.Bricks.IVisitor;

import java.awt.*;

/**
 * Created by guyal on 08/06/2016.
 */
public class WoodBall extends Ball{

    public WoodBall(int ballx, int bally){
        super(ballx,bally);
        color = new Color(110, 44, 15);
    }

    @Override
    public void impact(IVisitor visitor) {
        visitor.visit(this);
    }
}
