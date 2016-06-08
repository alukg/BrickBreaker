package GameComponents.Bricks;

import GameComponents.Balls.*;

/**
 * Created by guyal on 08/06/2016.
 */
public interface IVisitor {
    void visit(ElectricBall ball);
    void visit(ElementalBall ball);
    void visit(FireBall ball);
    void visit(WaterBall ball);
    void visit(WoodBall ball);
}
