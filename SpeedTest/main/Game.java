package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Game implements KeyListener {
	
	private SpeedTest window;
	private JPanel targetPanel = new JPanel();
	private JLabel timer = new JLabel("0");
	private int time = 0;
	private JLabel target = new JLabel();
	private JTextField answerField = new JTextField();
	private Timer timeCounter;
	
	private int remainingRounds;
	
	private boolean gameOver = false;
	
	public Game(SpeedTest mainFrame) {
		this.window = mainFrame;
		
		this.remainingRounds = Integer.parseInt(this.window.getOptions().getRoundNumber());
		
		this.targetPanel.setLayout(new BoxLayout(this.targetPanel, BoxLayout.PAGE_AXIS));
		this.target.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.targetPanel.add(this.target);
		
		this.timer.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.answerField.setHorizontalAlignment(JTextField.CENTER);
		
		this.window.getContentPane().add(this.targetPanel, BorderLayout.CENTER);
		this.window.getContentPane().add(this.answerField, BorderLayout.SOUTH);
		this.window.getContentPane().add(this.timer, BorderLayout.NORTH);
		this.window.update(this.window.getGraphics());
		
		this.answerField.addKeyListener(this);
		
		this.window.setVisible(true);
		this.answerField.grabFocus();
		
		this.setupRound();
		this.launchTimer();
	}
	
	private void launchTimer() {
		this.timeCounter = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (gameOver) timeCounter.stop();
				time++;
				timer.setText(String.valueOf(time));
			}
		});
		this.timeCounter.start();
	}
	
	private void setupRound() {
		if (this.window.getOptions().getTrialType().equals("letters")) {
			this.target.setText(this.randLetter());
		} else {
			this.target.setText(this.randWord());
		}
	}
	
	private String randWord() {
		Random rand = new Random();
		int wordNum = this.window.getWordOP().getwordCount();
		int targetRank = rand.nextInt(wordNum);
		return this.window.getWordOP().getWord(targetRank);
	}
	
	private String randLetter() {
		Random rand = new Random();
		int asciiRank = 0;
		String letterCase = this.window.getOptions().getLetterCase();
		if (letterCase.equals("a")) {
			asciiRank = rand.nextInt(26) + 97;
		} else if (letterCase.equals("A")) {
			asciiRank = rand.nextInt(26) + 65;
		} else if (letterCase.equals("aA")) {
			asciiRank = rand.nextInt(52);
			if (asciiRank < 26) {
				asciiRank = asciiRank + 65;
			} else {
				asciiRank = asciiRank + 97 - 26;
			}
		}
		return Character.toString((char) asciiRank);
	}
	
	private void nextRound() {
		this.answerField.setText("");
		if (this.remainingRounds-- > 1) {
			this.setupRound();
		} else {
			this.endGame();
		}
	}
	
	private String calculTime() {
		int hours = this.time / 3600;
		int minutes = this.time/60 - hours*60;
		int seconds = this.time - minutes*60;
		return (hours != 0 ? hours + " hours and" : "") + (minutes != 0 ? minutes + " minutes and" : "") + (seconds + " seconds.");
	}
	
	private void endGame() {
		this.gameOver = true;
		this.answerField.setEditable(false);
		JLabel result = new JLabel("End of the game!");
		JLabel timeRes = new JLabel("You wrote "
				+ this.window.getOptions().getRoundNumber()
				+ " "
				+ this.window.getOptions().getTrialType()
				+ " in "
				+ this.calculTime());
		this.targetPanel.add(result);
		this.targetPanel.add(timeRes);
		JButton menuButton = new JButton("Main menu");
		menuButton.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  returnToMenu();
		      }
		});
		this.targetPanel.add(menuButton);
	}
	
	private void returnToMenu() {
		this.window.getContentPane().removeAll();
		this.window.displayMenu();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent key) {
		if (this.answerField.getText().equals(this.target.getText())) {
			this.nextRound();
		} else {
			if (this.window.getOptions().getTrialType().contentEquals("letters")) {
				this.answerField.setText("");
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
