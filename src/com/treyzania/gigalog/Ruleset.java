package com.treyzania.gigalog;

public class Ruleset {

	private final String[][] rules;
	
	public Ruleset(String[][] rules) {
		this.rules = rules;
	}
	
	public boolean isImporant(String line) {
		
		for (String[] ruleEntry : this.rules) {
			
			boolean hasAll = true;
			
			for (String word : ruleEntry) {
				hasAll &= line.contains(word);
			}
			
			if (hasAll) return true;
			
		}
		
		return false;
		
	}
	
}
