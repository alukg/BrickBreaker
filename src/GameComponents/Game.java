package GameComponents;

import GameMenu.Controller;
import GameMenu.ModelClasses.JButtonWithIcon;
import GameMenu.ModelClasses.PanelModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class that represents the a game.
 */
public class Game extends PanelModel implements ActionListener
{
	//Variables
	private Board board;
	Controller controller;
	private Timer time;
	private Integer bestScore;
	private static int min=0,sec=0;
	private JLabel labelTimer;
	private static JLabel labelHits;
	JButton homeButton;
	public static int deadBricks = 0;
	public static int count = 0;

	public Game(Controller controller, Level level)
	{
		this.controller = controller;
		this.bestScore = level.bestScore;

		labelTimer = new JLabel("00:00",SwingConstants.CENTER);
		labelTimer.setForeground(Color.WHITE);
		labelTimer.setFont(new Font(labelTimer.getFont().getFontName(),Font.BOLD,20));
		this.time = new Timer(1000, this);
		menuPanel.add(labelTimer);

		labelHits = new JLabel("Hits: " + "0",SwingConstants.CENTER);
		labelHits.setForeground(Color.WHITE);
		labelHits.setFont(new Font(labelHits.getFont().getFontName(),Font.BOLD,20));
		menuPanel.add(labelHits);

		JLabel labelBestTime = new JLabel("Best score is : "+this.bestScore,SwingConstants.CENTER);
		labelBestTime.setForeground(Color.WHITE);
		labelBestTime.setFont(new Font(labelBestTime.getFont().getFontName(),Font.BOLD,16));
		menuPanel.add(labelBestTime);

		homeButton = new JButtonWithIcon("design\\returnPirate.jpg");
		homeButton.setActionCommand("Home");
		homeButton.addActionListener(this);
		homeButton.addActionListener(controller.new menuPress());
		menuPanel.add(homeButton);

		this.board = new Board(this, level.bricks);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(this.board,BorderLayout.CENTER);
		this.time.start();

		Thread thread = new Thread(board);
		thread.start();
	}

	//Getters and Setters
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) 
	{
		this.board = board;
		mainPanel.add(this.board);
	}

	public int getBestScore() {
		return bestScore;
	}

	public void setBestScore(String bestScore)
	{
		bestScore = bestScore;
		JLabel labelbestTime = new JLabel("Best time is : "+this.bestScore);
		menuPanel.add(labelbestTime);
	}

	/**
	 * The function operate the timer and stop it and operate undo function of the board if the undo button was pressed.
	 * @parm  e saves the action event details.
	 ***/
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == time)
		{
			if (sec < 59) {
				sec++;
				String sec2 = (sec < 10 ? "0" : "") + sec;
				labelTimer.setText(min + ":" + sec2);
			} else {
				min++;
				sec = 0;
				String min2 = (min < 10 ? "0" : "") + min;
				labelTimer.setText(min2 + ":00");
			}
		}
		else
		{
			if(e.getSource() == homeButton)
			{
				this.time.stop();
				min = 0;
				sec = 0;
			}
		}
	}

	public static void addHit(){
		count++;
		labelHits.setText("Hits: "+count);
	}

	public static void addDeadBrick(){
		deadBricks++;
	}

	public int getDeadBricks(){
		return deadBricks;
	}

	/**
	 * The function stops the timer of the board and runs the controller game finished function.
	 **/
	public void finish()
	{
		this.time.stop();
		int levelScore = Math.max(0, 1500- count -2*(min*60+sec));
		deadBricks = 0;
		count = 0;

		min = 0;
		sec = 0;

		this.controller.gameFinished(levelScore,bestScore);
	}
	
}
