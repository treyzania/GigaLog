package com.treyzania.gigalog;

public class Line {

	public static int maxDigits = -1;
	
	public final long number;
	public final String text;
	
	public Line(long num, String text) {
		
		this.number = num;
		this.text = text;
		
		maxDigits = (int) Math.max(maxDigits, Math.ceil(Math.log10(num)));
		
	}

	@Override
	public String toString() {
		
		return String.format("%" + maxDigits + "d", this.number) + ": " + text;
		
	}
	
}
