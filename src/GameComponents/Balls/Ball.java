package GameComponents.Balls;

import java.awt.*;

/**
 * Created by guyal on 08/06/2016.
 */
abstract public class Ball extends Rectangle implements IVisitable{

    protected Color color;
    public double dballx;
    public double dbally;

    public Ball(double dballx, double dbally){
        super((int)dballx,(int)dbally,12,12);
        this.dballx = dballx;
        this.dbally = dbally;
    }

    public Color getColor(){
        return color;
    }

    public void addX(double movex){
        dballx = dballx + movex;
        x = (int)dballx;
    }

    public void addY(double movey){
        dbally = dbally + movey;
        y = (int)dbally;
    }

}