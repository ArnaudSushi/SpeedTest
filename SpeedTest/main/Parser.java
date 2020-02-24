package main;

public class Parser {
	
	SpeedTest speedTest;
	
	public Parser(SpeedTest speedTest) {
		this.speedTest = speedTest;
	}
	
	public int parse(String command) {
		switch (command.toLowerCase()) {
		case "start":
			return 0;
		case "quit":
			return 1;
		default:
			return 2;
		}
	}
}
