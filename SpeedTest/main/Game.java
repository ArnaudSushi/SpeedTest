package main;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Game implements KeyListener {
	
	private JFrame window;
	private JPanel targetPanel = new JPanel(new BorderLayout());
	private JLabel target = new JLabel();
	private JTextField answerField = new JTextField();
	
	private int remainingLetters = 9;
	
	public Game(JFrame jFrame) {
		this.window = jFrame;
		
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
		int asciiRank = rand.nextInt(26) + 97;
		return Character.toString((char) asciiRank);
	}
	
	private void nextLetter() {
		this.answerField.setText("");
		if (this.remainingLetters-- > 0) {
			this.target.setText(this.randLetter());
		} else {
			this.endGame();
		}
	}
	
	private void endGame() {
		this.target.setText("End of the game!");
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
