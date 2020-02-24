package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpeedTest {
	
	Parser parser;
	
	public SpeedTest() {
		this.parser = new Parser(this);
		System.out.println("Welcome to the typing speed test!\n"
				+ "Type the following commands to continue:\n"
				+ "\"Start\" : Launch the game!\n"
				+ "\"Quit\" : Quit the game!\n");
	}
	
	public void launch() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int parseResult;
			while ((parseResult = this.parser.parse(br.readLine())) != 0) {
				System.out.println(parseResult == 1 ? "Game finished, we had so much fun didn't we!\n\n" : "Oops, this command is unknown!\n"
						+ "Type the following commands to continue:\n"
						+ "\"Start\" : Launch the game!\n"
						+ "\"Quit\" : Quit the game!\n");
			}
		} catch (IOException e) {
			throw e;
		}
	}
	
	public int start() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 50; ++j) System.out.println();
			
		}
		return 1;
	}
	
	public int quit() {
		System.out.println("Quit");
		return 0;
	}
}
