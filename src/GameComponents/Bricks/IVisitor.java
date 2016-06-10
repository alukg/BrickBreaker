package GameComponents.Bricks;

import GameComponents.Balls.*;

/**
 * Created by guyal on 08/06/2016.
 */
public interface IVisitor
{
    //void visit(Ball ball);
    void visit(ElectricBall electricBall);
    void visit(ElementalBall elementalBall);
    void visit(FireBall fireBall);
    void visit(WaterBall waterBall);
    void visit(WoodBall woodBall);
}
