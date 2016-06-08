package GameMenu;

import GameComponents.Block;
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
     * @param thisTime the last time of the level.
     * @param bestTime the best time of the level.
     */
    public void gameFinished(String thisTime, String bestTime){
        String newBestTime;
        if(bestTime.equals("--:--")){ //if there is no best time.
            newBestTime=thisTime;
        }
        else{ //if there is best time, check which is better.
            String[] thisTimeSplit = thisTime.split(":");
            String[] bestTimeSplit = bestTime.split(":");
            if(Integer.parseInt(thisTimeSplit[0])<Integer.parseInt(bestTimeSplit[0])) //if the minutes of the last time is lower.
                newBestTime=thisTime;
            else if(Integer.parseInt(thisTimeSplit[0])>Integer.parseInt(bestTimeSplit[0])) //if the minutes of the last time is higher.
                newBestTime=bestTime;
            else{ //if the minutes of the last time and best time is even.
                if(Integer.parseInt(thisTimeSplit[1])<Integer.parseInt(bestTimeSplit[1])) //if the seconds of the last time is lower.
                    newBestTime=thisTime;
                else if(Integer.parseInt(thisTimeSplit[1])>Integer.parseInt(bestTimeSplit[1])) //if the seconds of the last time is higher.
                    newBestTime=bestTime;
                else //if the time fo both is even, it doesn't matter what to set.
                    newBestTime=thisTime;
            }
        }
        levelsDB.elementAt(openLevel).bestTime=newBestTime; //set the new best time.
        boolean stop = false;
        while(!stop){ //while not pressed good option on the dialog.
            String[] options = { "Back to Main menu", "Select another level", "Next level" };
            JPanel panel = new JPanel();
            String dialogString;
            if(newBestTime.equals(thisTime))
                dialogString = "<html><center>Congradulations! New record!<br/>Best time: "+newBestTime+"<br/>Your time: "+thisTime+"</center></html>";
            else //if the last time wasn't record.
                dialogString = "<html><center>Congradulations! Successfully complete this level<br/>Best time: "+newBestTime+"<br/>Your time: "+thisTime+"</center></html>";
            panel.add(new JLabel(dialogString), BorderLayout.CENTER);
            int selected = JOptionPane.showOptionDialog(controller,panel,"", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if(selected == 0){ //back to main menu
                stop = true;
                openLevel=null;
                cards.show(getContentPane(),"Home");
                gameWindow = null;
            }
            else if(selected == 1){ //select another level
                stop=true;
                openLevel=null;
                homeWindow.addLevelChoosePanel(); //Add again the levels for the levels window, maybe the best time changed.
                cards.show(getContentPane(),"Select level");
                gameWindow = null;
            }
            else if(selected==2){ //select next level
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
            getContentPane().remove(gameWindow);
        }
        gameWindow = new Game(controller /*,levelsDB.elementAt(levelSlot) */); //create a new game.
        openLevel = levelSlot;
        getContentPane().add(gameWindow, "Game");
        cards.show(getContentPane(), "Game"); //show the game window.
    }

}
