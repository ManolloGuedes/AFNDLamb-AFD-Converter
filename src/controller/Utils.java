package controller;

import java.util.Arrays;
import java.util.HashSet;

import model.Transicao;

public class Utils {
	public static boolean Exists (String[] vecString, String str) {
		for (int i = 0; i < vecString.length; i++) {
			if (vecString[i].equals(str)) {
				return true;
			}
		}
		return false;
	}

	public static Transicao RemoverRepeticoes(Transicao transicao) {
		String[] original = transicao.getCaminhos();
		if (original != null) {	
			HashSet<String> hsOriginal = new HashSet<String>();
	        for(String n : original) {
	            hsOriginal.add(n);
	        }
	        int i = 0;
	        String[] semRepeticoes = new String[hsOriginal.size()];
	        for(String nome : hsOriginal) {
	            semRepeticoes[i++] = nome;
	        }
	        transicao.setCaminhos(semRepeticoes);
	        return transicao;
		}
		return null;
	}

	public static String[] concatenarArray(String[]...arrays ) {
		int length = 0;
        for (String[] array : arrays) { 
        	if (array != null)
        		length += array.length; 
        }
        String[] retorno = new String[length];
        int destPos = 0;
        for (String[] array : arrays) {
        	if (array != null) {
	            System.arraycopy (array, 0, retorno, destPos, array.length);
	            destPos += array.length;
        	}
        }
        return retorno;
	}
	public static String arrayToString(String[] array) {
		String str = "";
		for(String s : array) {
			str+=s;
			str+=",";
		}
		str = str.substring(0, str.length()-1);
		return str;
	}
}
