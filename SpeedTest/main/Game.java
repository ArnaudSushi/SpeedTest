package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Game implements KeyListener {
	
	private SpeedTest window;
	private JPanel targetPanel = new JPanel(new BorderLayout());
	private JLabel target = new JLabel();
	private JTextField answerField = new JTextField();
	
	private int remainingLetters;
	
	public Game(SpeedTest mainFrame) {
		this.window = mainFrame;
		
		this.remainingLetters = Integer.parseInt(this.window.getOptions().getRoundNumber());
		
		this.target.setText(this.randLetter());
		
		this.target.setHorizontalAlignment(SwingConstants.CENTER);
		this.target.setVerticalAlignment(SwingConstants.CENTER);
		this.targetPanel.add(this.target, BorderLayout.CENTER);
		
		this.window.getContentPane().add(targetPanel, BorderLayout.CENTER);
		this.window.getContentPane().add(answerField, BorderLayout.SOUTH);
		this.window.update(this.window.getGraphics());
		
		this.answerField.addKeyListener(this);
		
		this.window.setVisible(true);
		this.answerField.grabFocus();
	}
	
	private String randLetter() {
		Random rand = new Random();
		int asciiRank = 0;
		int offset;
		int letterCount;
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
	
	private void nextLetter() {
		this.answerField.setText("");
		if (this.remainingLetters-- > 1) {
			this.target.setText(this.randLetter());
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
		if (Character.toString(key.getKeyChar()).equals(this.target.getText())) {
			this.nextLetter();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
