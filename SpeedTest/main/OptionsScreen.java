package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class OptionsScreen implements ActionListener {
	
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
	private ButtonGroup caseOptionButtonGroup = new ButtonGroup();
	private JRadioButton lowerCaseOption = new JRadioButton("a");
	private JRadioButton upperCaseOption = new JRadioButton("A");
	private JRadioButton bothCaseOption = new JRadioButton("aA");
	private String selectedCase = "";
	
	private JPanel buttonPanel = new JPanel();
	private JButton cancelButton = new JButton("Cancel");
	private JButton applyButton = new JButton("Apply changes");
	
	private JLabel didApplyWorked = new JLabel();
	
	public OptionsScreen(SpeedTest speedTest) {
		this.window = speedTest;
		
		this.numberOptionPanel.add(this.numberOptionLabel);
		this.numberOptionPanel.add(this.numberOptionField);
		this.setOptionField();

		this.letterWordChoice.addItem("letters");
		this.letterWordChoice.addItem("words");
		this.letterWordPanel.add(this.letterWordLabel);
		this.letterWordPanel.add(this.letterWordChoice);
		this.setLetterWordChoice();
		
		this.lowerCaseOption.addActionListener(this);
		this.lowerCaseOption.setActionCommand("a");
		this.upperCaseOption.addActionListener(this);
		this.upperCaseOption.setActionCommand("A");
		this.bothCaseOption.addActionListener(this);
		this.bothCaseOption.setActionCommand("aA");
		this.caseOptionButtonGroup.add(this.lowerCaseOption);
		this.caseOptionButtonGroup.add(this.upperCaseOption);
		this.caseOptionButtonGroup.add(this.bothCaseOption);
		this.caseOptionPanel.add(this.caseOptionLabel);
		this.caseOptionPanel.add(this.lowerCaseOption);
		this.caseOptionPanel.add(this.upperCaseOption);
		this.caseOptionPanel.add(this.bothCaseOption);
		this.setcaseOption();
		
		this.cancelButton.setActionCommand("cancel");
		this.cancelButton.addActionListener(this);
		this.applyButton.setActionCommand("apply");
		this.applyButton.addActionListener(this);
		
		this.buttonPanel.add(this.cancelButton);
		this.buttonPanel.add(this.applyButton);

		this.optionsPanel.setLayout(new BoxLayout(this.optionsPanel, BoxLayout.PAGE_AXIS));
		this.optionsPanel.add(this.numberOptionPanel);
		this.optionsPanel.add(this.letterWordPanel);
		this.optionsPanel.add(this.caseOptionPanel);
		this.optionsPanel.add(this.buttonPanel);
		
		this.didApplyWorked.setVerticalAlignment(SwingConstants.CENTER);
		this.didApplyWorked.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.window.getContentPane().add(this.optionsPanel, BorderLayout.NORTH);
		this.window.getContentPane().add(this.didApplyWorked, BorderLayout.CENTER);
		
		this.window.update(this.window.getGraphics());
		this.window.setVisible(true);
	}
	
	private void setOptionField() {
		this.numberOptionField.setText(this.window.getOptions().getRoundNumber());
	}
	
	private void setLetterWordChoice() {
		this.letterWordChoice.setSelectedItem(this.window.getOptions().getTrialType());
	}
	
	private void setcaseOption() {
		this.selectedCase = this.window.getOptions().getLetterCase();
		switch (this.selectedCase) {
		case "a":
			this.lowerCaseOption.setSelected(true);
			break;
		case "A":
			this.upperCaseOption.setSelected(true);
			break;
		case "aA":
			this.bothCaseOption.setSelected(true);
			break;
		default:
			this.lowerCaseOption.setSelected(true);
			break;
		}
	}
	
	private void returnToMenu() {
		this.window.getContentPane().removeAll();
		this.window.displayMenu();
	}
	
	private void applyOption() {
		try {
			this.window.getOptions().setOptions(this.numberOptionField.getText(), this.letterWordChoice.getSelectedItem().toString(), this.selectedCase);
			this.didApplyWorked.setText("Options saved!");
		} catch (IOException e) {
			this.didApplyWorked.setText("Oops, it seems there's been an error saving the changes, please try again!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().getClass() == this.upperCaseOption.getClass()) this.selectedCase = event.getActionCommand();
		switch (event.getActionCommand()) {
		case "cancel":
			this.returnToMenu();
			break;
		case "apply":
			this.applyOption();
			break;
		default:
			break;
		}
	}
}
