package com.treyzania.gigalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogBuilder {

	public final Ruleset rules;
	public final int spread; // Inclusive.
	
	public LogBuilder(Ruleset rules, int spread) {
		
		this.rules = rules;
		this.spread = spread;
		
	}
	
	public LogBuilder(Ruleset rules) {
		this(rules, 0);
	}
	
	public Log buildLog(File f) {
		
		Scanner input = null;
		
		// Set up the input.
		try {
			input = new Scanner(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			System.out.println("Error: Base log file.");
			System.exit(-1);
		}
		
		if (input == null) throw new IllegalStateException("Something went wrong.");
		
		// Set up the output.
		Log output = new Log(f);
		
		long line = 1;
		while (input.hasNextLine()) {
			
			// Actually flag the line.
			String l = input.nextLine();
			if (this.rules.isImporant(l)) {
				
				output.flag(line);
				
				// Flag line around the flagged line.
				for (int i = 1; i <= this.spread; i++) {
					
					// Not just One Direction.
					output.flag(line + i);
					output.flag(line - i);
					
				}
				
			}
			
			line++;
			
		}
		
		return output;
		
	}
	
}
