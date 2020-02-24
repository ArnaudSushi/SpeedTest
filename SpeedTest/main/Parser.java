package main;

public class Parser {
	
	SpeedTest speedTest;
	
	public Parser(SpeedTest speedTest) {
		this.speedTest = speedTest;
	}
	
	public int parse(String command) {
		switch (command.toLowerCase()) {
		case "start":
			return this.speedTest.start();
		case "quit":
			return this.speedTest.quit();
		default:
			return 1;
		}
	}
}
