package com.treyzania.gigalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RulesetBuilder {

	public RulesetBuilder() {
		// Nothing!
	}
	
	public Ruleset buildRuleset(File f) {
		
		Scanner input = null;
		
		try {
			input = new Scanner(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			System.out.println("Error: Ruleset file does not exist.");
			System.exit(-1);
		}
		
		if (input == null) throw new IllegalStateException("Something went wrong.");
		
		List<String[]> rules = new ArrayList<>();
		
		// Actually gather the rules and stuff.
		while (input.hasNextLine()) {
			rules.add(input.nextLine().split(" "));
		}
		
		System.out.println("Loaded " + rules.size() + " rules.");
		
		return new Ruleset(rules.toArray(new String[rules.size()][]));
		
	}
	
}
