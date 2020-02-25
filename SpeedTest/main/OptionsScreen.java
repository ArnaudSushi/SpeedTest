package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class OptionsScreen {
	
	private SpeedTest window;
	
	private JPanel optionsPanel = new JPanel();
	
	private JPanel numberOptionPanel = new JPanel();
	private JLabel numberOptionLabel = new JLabel("Nombre d'épreuve(s) :");
	private JTextField numberOptionField = new JTextField(5);
	
	private JPanel letterWordPanel = new JPanel();
	private JLabel letterWordLabel = new JLabel("Type d'épreuve");//Mots/Lettres
	private JComboBox<String> letterWordChoice = new JComboBox<String>();
	
	private JPanel caseOptionPanel = new JPanel();
	private JLabel caseOptionLabel = new JLabel("Which case ?");
	private JRadioButton lowerCaseOption = new JRadioButton("a");
	private JRadioButton upperCaseOption = new JRadioButton("A");
	private JRadioButton bothCaseOption = new JRadioButton("aA");
	
	private JPanel buttonPanel = new JPanel();
	private JButton cancelButton = new JButton("Cancel");
	private JButton applyButton = new JButton("Apply changes");
	
	public OptionsScreen(SpeedTest speedTest) {
		this.window = speedTest;
		
		this.numberOptionPanel.add(this.numberOptionLabel);
		this.numberOptionPanel.add(this.numberOptionField);

		this.letterWordChoice.addItem("Letters");
		this.letterWordChoice.addItem("Words");
		this.letterWordPanel.add(this.letterWordLabel);
		this.letterWordPanel.add(this.letterWordChoice);
		
		this.caseOptionPanel.add(this.caseOptionLabel);
		this.caseOptionPanel.add(lowerCaseOption);
		this.caseOptionPanel.add(upperCaseOption);
		this.caseOptionPanel.add(bothCaseOption);
		
		this.buttonPanel.add(this.cancelButton);
		this.buttonPanel.add(this.applyButton);

		this.optionsPanel.setLayout(new BoxLayout(this.optionsPanel, BoxLayout.PAGE_AXIS));
		this.optionsPanel.add(this.numberOptionPanel);
		this.optionsPanel.add(this.letterWordPanel);
		this.optionsPanel.add(this.caseOptionPanel);
		this.optionsPanel.add(this.buttonPanel);
				
		this.window.getContentPane().add(this.optionsPanel, BorderLayout.NORTH);
		
		this.window.update(this.window.getGraphics());
		this.window.setVisible(true);
	}
}
