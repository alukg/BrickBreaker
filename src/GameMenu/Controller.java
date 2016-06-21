package GameMenu;

import GameComponents.Game;
import GameComponents.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class Controller extends JFrame {

    private Controller controller = this;
    private CardLayout cards; //The var that control the windows change.
    private HomeWindow homeWindow;
    private Game gameWindow;
    private Vector<Level> levelsDB; //DB of all the game levels.
    private Integer openLevel; //The number of the running level.

    public Controller(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); //prevent changing the size of the frame.
        CardLayout cardL = new CardLayout(); //card layout gives the option to change between windows on the same frame.
        getContentPane().setLayout(cardL);

        /**
         * adds to the window close the step of saving the DB to the file.
         */
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveDB();
                System.exit(0);
            }
        });

        levelsDB = DataBase.Serializer.deserialize(); //Get all the levels from the DB file.

        homeWindow = new HomeWindow(this);

        // add the windows to the card layout.
        getContentPane().add(homeWindow, "Home");

        cards = (CardLayout) getContentPane().getLayout();
        cards.show(getContentPane(), "Home"); //show now the home window.

        /* add new levels
        levelsDB.removeAllElements();
        Scanner reader = new Scanner(System.in);
        for(int i=0;i<4;i++){
            int[][] bricks = new int[8][10];
            String string = reader.nextLine();
            for(int rows=0;rows<8;rows++){
                for(int cols=0;cols<10;cols++){
                    bricks[rows][cols] = Integer.parseInt(string.charAt(rows*10+cols)+"");
                }
            }
            levelsDB.addElement(new Level(bricks,null));
        }
        */

        pack();
        this.setVisible(true); //show the frame.
    }

    /**
     * Getter for the levels DB.
     * @return vector of all the levels.
     */
    public Vector<Level> getLevelsDB(){
        return levelsDB;
    }

    /**
     * ask the serializer to save the levels to the DB file.
     */
    public void saveDB(){
        DataBase.Serializer.serialize(levelsDB);
    }

    /**
     * Run when the game finished, show and save the best time. gives the option what to do next.
     * @param thisScore the last time of the level.
     * @param bestScore the best time of the level.
     */
    public void gameFinished(Integer thisScore, Integer bestScore){
        int newBestScore;
        if(bestScore == null){ //if there is no best time.
            newBestScore=thisScore;
        }
        else{ //if there is best time, check which is better.
            newBestScore = Math.max(thisScore,bestScore);
        }
        levelsDB.elementAt(openLevel).bestScore = newBestScore; //set the new best score.
        boolean stop = false;
        while(!stop){ //while not pressed good option on the dialog.
            String[] options = { "Back to Main menu", "Next level" };
            JPanel panel = new JPanel();
            String dialogString;
            if(newBestScore == thisScore)
                dialogString = "<html><center>Congradulations! New record!<br/>Best Score: "+newBestScore+"<br/>Your Score: "+thisScore+"</center></html>";
            else //if the last time wasn't record.
                dialogString = "<html><center>Congradulations! Successfully complete this level<br/>Best Score: "+newBestScore+"<br/>Your Score: "+thisScore+"</center></html>";
            panel.add(new JLabel(dialogString), BorderLayout.CENTER);
            int selected = JOptionPane.showOptionDialog(controller,panel,"", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if(selected == 0){ //back to main menu
                stop = true;
                openLevel=null;
                homeWindow.addLevelChoosePanel();
                cards.show(getContentPane(),"Home"); //Add again the levels for the levels window, maybe the best time changed.
                gameWindow = null;
            }
            else if(selected==1){ //select next level
                if(openLevel==levelsDB.size()-1){ //if this is the last level, show information dialog.
                    String[] ok = {"Ok"};
                    JPanel panel2 = new JPanel();
                    panel2.add(new JLabel("There is no next level"), BorderLayout.CENTER);
                    JOptionPane.showOptionDialog(controller,panel2,"", JOptionPane.OK_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, ok, options[0]);
                    continue;
                }
                else{ //else, open the next level.
                    stop = true;
                    newGame(openLevel+1);
                }
            }
            else{ //if pressed X, show the dialog again. Must press any option.
                continue;
            }
        }
    }

    /**
     * Listener that replace the shown window by the press command.
     */
    public class menuPress implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Home")){
                Game.deadBricks = 0;
                Game.count = 0;
                homeWindow.addLevelChoosePanel();
                cards.show(getContentPane(), "Home");
            }
        }
    }

    /**
     * Listener that starts a new level by the number of it.
     */
    public class gamePress implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            newGame(Integer.parseInt(e.getActionCommand())); //get from the button action commend the number of the level, and send it to the new game function.
        }
    }

    /**
     * start a new game.
     * @param levelSlot the level number to start.
     */
    public void newGame(int levelSlot){
        if(gameWindow!=null){ //remove the last game window.
            gameWindow.getBoard().thread.stop();
            gameWindow.getBoard().thread = null;
            getContentPane().remove(gameWindow);
        }
        gameWindow = new Game(controller,levelsDB.elementAt(levelSlot)); //create a new game.
        openLevel = levelSlot;
        getContentPane().add(gameWindow, "Game");
        cards.show(getContentPane(), "Game"); //show the game window.
    }

}
