package GameComponents;

/**
 * The class that represents a game level.
 */
public class Level
{
    //Variables
    public int[][] bricks;
    public String bestTime;

    //Constructors
    public Level(int[][] bricks, String bestTime){
        this.bestTime = bestTime;
        this.bricks = bricks;
    }
}