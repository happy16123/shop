package com.myshop.util;

import java.util.Random;

public class CertNumber {
	
	private final char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
											'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
											'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
	
	public String executeCert() {
		Random ran = new Random();
		StringBuffer sb  = new StringBuffer();
		
		for(int i=0; i<6; i++) {
			sb.append(characterTable[ran.nextInt(characterTable.length)]);
		}
		
		return sb.toString();
	}
}
