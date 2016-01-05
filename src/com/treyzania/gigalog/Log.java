package com.treyzania.gigalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Log {

	public final File file;
	public Set<Long> importantLines;
	
	public Log(File f) {
		
		this.file = f;
		
		this.importantLines = new HashSet<>();
		
	}
	
	public void flag(long num) {
		if (!this.importantLines.contains(num)) this.importantLines.add(num);
	}
	
	/**
	 * Pass 2 of the manifest generation.
	 * 
	 * @return
	 */
	public List<Line> getFlaggedLines() {
		
		Scanner input = null;
		List<Line> output = new ArrayList<>();
		
		// Set up the input.
		try {
			
			input = new Scanner(new FileInputStream(this.file));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (input == null) throw new IllegalStateException("Something went wrong.");
		
		// Actually read the lines.
		long line = 1;
		while (input.hasNextLine()) {
			
			String l = input.nextLine();
			if (this.importantLines.contains(line)) output.add(new Line(line, l));
			
			line++;
			
		}
		
		input.close();
		
		return output;
		
	}
	
}
