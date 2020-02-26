package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Game implements KeyListener, ActionListener {
	
	private SpeedTest window;
	private JPanel targetPanel = new JPanel(new BorderLayout());
	private JLabel timer = new JLabel("0");
	private int time = 0;
	private JLabel target = new JLabel();
	private JTextField answerField = new JTextField();
	
	private int remainingRounds;
	
	public Game(SpeedTest mainFrame) {
		this.window = mainFrame;
		
		this.remainingRounds = Integer.parseInt(this.window.getOptions().getRoundNumber());
				
		this.target.setHorizontalAlignment(SwingConstants.CENTER);
		this.target.setVerticalAlignment(SwingConstants.CENTER);
		this.targetPanel.add(this.target, BorderLayout.CENTER);
		
		this.timer.setHorizontalAlignment(SwingConstants.CENTER);
		
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
		Timer timer = new Timer(1000, this);
		timer.start();
	}
	
	private void setupRound() {
		if (this.window.getOptions().getTrialType() == "letter") {
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
	
	private void endGame() {
		this.target.setText("End of the game!");
		JButton menuButton = new JButton("Main menu");
		menuButton.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent event){
		    	  returnToMenu();
		      }
		});
		this.targetPanel.add(menuButton, BorderLayout.SOUTH);
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
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent event) {
		this.time++;
		this.timer.setText(String.valueOf(this.time));
	}
}
