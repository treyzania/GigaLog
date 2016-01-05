package com.treyzania.gigalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogBuilder {

	private static final int NOTIFICATION_PERIOD = 1000; // Lines.
	
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
			System.out.println("Error: Input file does not exist.");
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
			
			if (line % NOTIFICATION_PERIOD == 0) System.out.println("Processed " + line + " lines.");
			
			line++;
			
		}
		
		System.out.println("Flagged " + output.getFlagCount() + " lines.");
		input.close();
		
		return output;
		
	}
	
}
