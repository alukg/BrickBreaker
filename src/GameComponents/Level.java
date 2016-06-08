package GameComponents;

/**
 * The class that represents a game level.
 */
public class Level
{
    //Variables
    public int[][] bricks;
    public Integer bestScore;

    //Constructors
    public Level(int[][] bricks, Integer bestScore){
        this.bestScore = bestScore;
        this.bricks = bricks;
    }
}