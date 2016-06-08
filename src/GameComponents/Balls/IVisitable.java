package GameComponents.Balls;

import GameComponents.Bricks.IVisitor;

/**
 * Created by guyal on 08/06/2016.
 */
public interface IVisitable {
    void impact(IVisitor visitor);
}
