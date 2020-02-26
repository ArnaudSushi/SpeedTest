package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SpeedTest extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel titleLabel = new JLabel("Welcome to the typing speed test!");
	private JPanel buttonsPanel = new JPanel(); 
	private JButton playButton = new JButton("Play");
	private JButton optionButton = new JButton("Option");
	private JButton quitButton = new JButton("Quit");
	private Game game;
	private OptionsScreen optionsScreen;
	private Options options;
	private WordOP wordOP;
	
	public SpeedTest() {
		this.options = new Options();
		this.wordOP = new WordOP();
		
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

		this.optionButton.addActionListener(this);
		this.optionButton.setActionCommand("option");
		
		this.quitButton.addActionListener(this);
		this.quitButton.setActionCommand("quit");
		
		this.buttonsPanel.add(playButton);
		this.buttonsPanel.add(optionButton);
		this.buttonsPanel.add(quitButton);
		
		this.add(buttonsPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
		
	}
	
	public void displayMenu() {
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(titleLabel, BorderLayout.NORTH);
		this.add(buttonsPanel, BorderLayout.CENTER);
		this.update(this.getGraphics());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "play":
			this.launchGame();
			break;
		case "option":
			this.manageOptions();
			break;
		case "quit":
			this.dispose();
			break;
		default:
			break;
		}
	}
	
	private void manageOptions() {
		this.getContentPane().removeAll();
		this.optionsScreen = new OptionsScreen(this);
	}
		
	private void launchGame() {
		this.getContentPane().removeAll();
		this.game = new Game(this);
	}
	
	public Options getOptions() {
		return this.options;
	}
	
	public WordOP getWordOP() {
		return this.wordOP;
	}
}
