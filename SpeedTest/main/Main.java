package main;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		SpeedTest st = new SpeedTest();
		try {
			st.launch();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
