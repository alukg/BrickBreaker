package GameComponents.Balls;

import java.awt.*;

/**
 * Ball abstract class.
 */
abstract public class Ball extends Rectangle implements IVisitable{

    protected Color color;
    public double dballx;
    public double dbally;

    /**
     * Constructor.
     * @param dballx the X-axis of the ball.
     * @param dbally the Y-axis of the ball.
     */
    public Ball(double dballx, double dbally){
        super((int)dballx,(int)dbally,12,12);
        this.dballx = dballx;
        this.dbally = dbally;
    }

    public Color getColor(){
        return color;
    }

    /**
     * move the ball on the X-axis.
     * @param movex
     */
    public void addX(double movex){
        dballx = dballx + movex;
        x = (int)dballx;
    }

    /**
     * move the ball on the Y-axis.
     * @param movey
     */
    public void addY(double movey){
        dbally = dbally + movey;
        y = (int)dbally;
    }

}