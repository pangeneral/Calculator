package com.pangeneral.test;



import android.test.AndroidTestCase;


public class TestCalculate extends AndroidTestCase{
	
	
	public void test_string_cast_to_double() throws Exception{
		String s = ".32";
		Double d = Double.valueOf(s);
		System.out.println(d);
	}
}
