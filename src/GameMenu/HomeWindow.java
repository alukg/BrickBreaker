package GameMenu;

import GameComponents.Level;
import GameMenu.ModelClasses.JButtonWithIcon;
import GameMenu.ModelClasses.PanelModel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

/**
 * The window of level choose to play.
 */
public class HomeWindow extends PanelModel {

    private Controller controller;
    private JPanel levelsChoose;
    private Clip clip; //the music controller.
    private JButton music;

    /**
     * Constructor.
     * @param controller the controller instance for getting the controller functions.
     */
    public HomeWindow(Controller controller) {
        this.controller = controller;

        JButton exit = new JButtonWithIcon("design\\exitPirate.jpg");
        exit.addActionListener(e -> {controller.saveDB(); System.exit(0);});
        music = new JButtonWithIcon("design\\musicOffPirate.jpg");
        music.addActionListener(new MusicListener());
        menuPanel.add(exit);
        menuPanel.add(music);

        levelsChoose = null;

        addLevelChoosePanel(); //add the levels from the DB to the panel.

        //Add music
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("PiratesOfTheCaribbean.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream); //open the sound file.
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**
     * add the levels from the DB function.
     */
    public void addLevelChoosePanel() {
        if (levelsChoose!=null && levelsChoose.getParent()!=null) //if there is panel of levels, create a new one.
            mainPanel.remove(levelsChoose);
        levelsChoose = new JPanel();
        levelsChoose.setLayout(new GridLayout(2,2,20,20));
        levelsChoose.setBackground(Color.BLACK);

        mainPanel.add(levelsChoose); //set the levels panel in the mainPanel.

        Vector<Level> vector = controller.getLevelsDB();

        if(vector==null || vector.size()==0){ //if there is no levels.
            JLabel noLevels = new JLabel("There is no levels",SwingConstants.CENTER);
            noLevels.setForeground(Color.WHITE);
            noLevels.setFont(new Font(noLevels.getFont().getFontName(),Font.BOLD,20));
            levelsChoose.add(noLevels); //set message on the panel that there is no levels to the user.
        }
        else{
            levelsChoose.setLayout(new GridBagLayout());
            int i = 0;
            while(i<vector.size()){ //add the levels from the DB to the panel.
                //new level button.
                JButton levelButton = new JButtonWithIcon("design/levelButtonPirate.jpg");
                levelButton.setPreferredSize(new Dimension(100,100));
                levelButton.setHorizontalTextPosition(JButton.CENTER);
                levelButton.setVerticalTextPosition(JButton.CENTER);
                levelButton.setActionCommand(""+i);
                levelButton.setText("<html><center>Level "+(i+1)+"<br/>"+vector.elementAt(i).bestTime+"</center></html>");
                levelButton.addActionListener(controller.new gamePress());
                levelsChoose.add(levelButton);
                i++;
            }
        }
    }

    /**
     * Listener for the music player.
     */
    public class MusicListener implements ActionListener {

        private boolean musicOn = false; //decide if the music is on or off.

        @Override
        public void actionPerformed(ActionEvent e) {
            if(musicOn == true){
                musicOn = false;
                music.setIcon(new ImageIcon("design\\musicOffPirate.jpg")); //change the music icon to on.
                clip.stop();
            }
            else{
                musicOn = true;
                music.setIcon(new ImageIcon("design\\musicOnPirate.jpg")); //change the music icon to off.
                clip.start();
            }
        }
    }
}
