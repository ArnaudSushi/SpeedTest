package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Options {
	
	private String roundNumber;
	private String trialType;
	private String letterCase;
	
	public Options() {
		try {
			File propFile = new File("C:\\ProgramData\\SpeedTest\\config.properties");
			InputStream propertiesFile;
			if (!propFile.exists()) {
				propertiesFile = new FileInputStream("/resources/config.properties");
			} else {
				propertiesFile = new FileInputStream(propFile);
			}
			Properties prop = new Properties();
			
			prop.load(propertiesFile);
			this.roundNumber = prop.getProperty("round");
			this.trialType = prop.getProperty("type");
			this.letterCase = prop.getProperty("case");
			propertiesFile.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public String getRoundNumber() {
		return this.roundNumber;
	}
	
	public String getTrialType() {
		return this.trialType;
	}
	
	public String getLetterCase() {
		return this.letterCase;
	}
	
	public void setOptions(String newNumber, String newTrial, String newCase) throws IOException {
		this.roundNumber = newNumber;
		this.trialType = newTrial;
		this.letterCase = newCase;
		try {
			this.writeOptions();
		} catch (IOException e) { throw e; }
	}
	
	private void writeOptions() throws IOException {
		try {
			File propFile = new File("C:\\ProgramData\\SpeedTest\\config.properties");
			if (!propFile.exists()) {
				propFile.getParentFile().mkdirs();
				propFile.createNewFile();
			}
			OutputStream propertiesFile = new FileOutputStream(propFile);
			Properties prop = new Properties();
			
			prop.setProperty("round", this.roundNumber);
			prop.setProperty("type", this.trialType);
			prop.setProperty("case", this.letterCase);
			prop.store(propertiesFile, null);
			propertiesFile.close();
		} catch (IOException e) { e.printStackTrace(); throw e; }
	}
}