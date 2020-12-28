package com.mtech.saassignment;

import java.util.Scanner;

public class ConsoleReader {
	
	public static String inputReader()
	
	{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter Movie name  :");
		String mname = sc.next();
		System.out.println("Please enter Release year :");
		String year = sc.next();
		return mname + ":" + year;
		
	}

}
