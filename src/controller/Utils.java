package controller;

public class Utils {
	public static boolean Exists (String[] vecString, String str) {
		for (int i = 0; i < vecString.length; i++) {
			if (vecString[i].equals(str)) {
				return true;
			}
		}
		return false;
	}
}
