package GameComponents.Balls;

import java.awt.*;

/**
 * Created by guyal on 08/06/2016.
 */
abstract public class Ball extends Rectangle implements IVisitable{

    protected Color color;

    public Ball(int ballx, int bally){
        super(ballx,bally,12,12);
    }

    public Color getColor(){
        return color;
    }

    public void addXByOne(){
        if(x>0) x++;
        else x--;
    }

    public void addYByOne(){
        if(y>0) y++;
        else y--;
    }

}