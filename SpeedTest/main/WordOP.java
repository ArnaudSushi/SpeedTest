package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WordOP {
	
	private String[] words;
	private int wordCount;
	
	public WordOP() {
		try {
			InputStream wordFile = WordOP.class.getResourceAsStream("/resources/index.json");
			BufferedReader buf = new BufferedReader(new InputStreamReader(wordFile));
			String line = buf.readLine();
			words = line.split(",");
			for (int i = 0; i < words.length; i++) {
				words[i] = words[i].split("\"")[1];
			}
			this.wordCount = words.length;
			buf.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public String[] getAllWords() {
		return this.words;
	}
	
	public String getWord(int index) {
		return words[index];
	}
	
	public int getwordCount() {
		return this.wordCount;
	}
}
