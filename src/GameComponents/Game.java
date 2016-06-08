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
	private String bestTime;
	private static int min=0,sec=0;
	private JLabel labelTimer;
	JButton homeButton;

	public Game(Controller controller, Level level)
	{
		this.controller = controller;
		this.bestTime = level.bestTime;

		labelTimer = new JLabel("00:00",SwingConstants.CENTER);
		labelTimer.setForeground(Color.WHITE);
		labelTimer.setFont(new Font(labelTimer.getFont().getFontName(),Font.BOLD,20));
		this.time = new Timer(1000, this);
		menuPanel.add(labelTimer);

		JLabel labelBestTime = new JLabel("Best time is : "+this.bestTime,SwingConstants.CENTER);
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

	public String getBestTime() {
		return bestTime;
	}

	public void setBestTime(String bestTime)
	{
		bestTime = bestTime;
		JLabel labelbestTime = new JLabel("Best time is : "+this.bestTime);
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

	/**
	 * The function stops the timer of the board and runs the controller game finished function.
	 **/
	public void Finish()
	{
		this.time.stop();
		String currentTime = this.labelTimer.getText();
		min = 0;
		sec = 0;
		this.controller.gameFinished(currentTime,bestTime);
	}
	
}
