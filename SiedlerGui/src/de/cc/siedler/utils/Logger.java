package de.cc.siedler.utils;

public class Logger {

	public static boolean aktiv = false;
	
	
	public static void info(String str){
		if (aktiv){
			System.out.println(str);	
		}	
	}
	public static void debug(String str){
		if (aktiv){
			System.out.println(str);	
		}	
	}
	public static void fehler(String str, Exception e){
		if (aktiv){
			System.out.println(str+ "\n\n\n" + e);	
		}	
	}
}
