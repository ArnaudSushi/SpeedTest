package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SpeedTest extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel titleLabel = new JLabel("Welcome to the typing speed test!");
	private JPanel buttonsPanel = new JPanel(); 
	private JButton playButton = new JButton("Play");
	private JButton quitButton = new JButton("Quit");
	private JButton hideButton = new JButton("hide");
	private Game game;
	
	public SpeedTest() {
		this.setTitle("Speed Test!");
		this.setSize(600, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.titleLabel.setVerticalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(titleLabel, BorderLayout.NORTH);
		
		this.playButton.addActionListener(this);
		this.playButton.setActionCommand("play");
		
		this.quitButton.addActionListener(this);
		this.quitButton.setActionCommand("quit");
		
		this.hideButton.addActionListener(this);
		this.hideButton.setActionCommand("hide");
		
		this.buttonsPanel.add(playButton);
		this.buttonsPanel.add(quitButton);
		this.buttonsPanel.add(hideButton);
		
		this.add(buttonsPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "play":
			this.launchGame();
			break;
		case "quit":
			this.dispose();
			break;
		default:
			break;
		}
	}
		
	public void launchGame() {
		this.getContentPane().removeAll();
		this.game = new Game(this);
	}
}
