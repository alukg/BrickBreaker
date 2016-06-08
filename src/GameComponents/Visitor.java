package GameComponents;

/**
 * Created by yaniv on 08/06/2016.
 */
public interface Visitor
{
    void impact(RegularBall regularBall);
    void impact(FireBall fireBall);
    void impact(WaterBall waterBall );
    void impact(TreeBall treeBall);
    void impact(ElectricBall electricBall);
}
