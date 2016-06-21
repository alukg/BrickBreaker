package GameComponents.Balls;

import GameComponents.Bricks.IVisitor;

/**
 * Interface represents the visited objects from the visitor pattern.
 */
public interface IVisitable {
    void impact(IVisitor visitor);
}
