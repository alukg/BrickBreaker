package GameComponents.Balls;

import GameComponents.Bricks.IVisitor;

import java.awt.*;

/**
 * Created by guyal on 08/06/2016.
 */
public class FireBall extends Ball{

    public FireBall(int ballx, int bally){
        super(ballx,bally);
        color = Color.RED;
    }

    @Override
    public void impact(IVisitor visitor) {
        visitor.visit(this);
    }
}
