package com.tech.prjm09.util;

public class DBUtil {
	
	/**
	 * 디비 finally close(값)
	 */

	public static void close(AutoCloseable ac) {
		if (ac != null) {
			try {
				ac.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
