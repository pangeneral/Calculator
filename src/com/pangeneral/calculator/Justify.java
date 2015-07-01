package com.pangeneral.calculator;


public class Justify {
	
	public static boolean isOperation(char inputChar){
		if( inputChar == '+' || inputChar == '-' || inputChar == '*' || inputChar == '/')
			return true;
		else
			return false;
	}
	
	
}
