package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpeedTest {
	
	Parser parser;
	
	public SpeedTest() {
		this.parser = new Parser(this);
	}
	
	public void launch() throws IOException {
		System.out.println("Welcome to the typing speed test!\n"
				+ "Type the following commands to continue:\n"
				+ "\"Start\" : Launch the game!\n"
				+ "\"Quit\" : Quit the game!\n");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			this.parser.parse(br.readLine());
		} catch (IOException e) {
			throw e;
		}
	}
}
