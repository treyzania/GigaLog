package com.treyzania.gigalog;

public class Line {

	public final long number;
	public final String text;
	
	public Line(long num, String text) {
		
		this.number = num;
		this.text = text;
		
	}

	@Override
	public String toString() {
		return Long.toString(this.number) + ": " + text;
	}
	
}
