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
		/*String[] original = transicao.getCaminhos();
		
        String[] semRepeticao = new String[original.length];
        int qtd = 0;
        //fixa o vetor original e compara com o vetor sem repetição
        for(int i = 0 ; i < original.length ; i++) {
            boolean existe = false;
            //primeira vez não entra no for, a partir da segunda percorre o vetor sem repetição pra saber se já foi
            //registrado o elemento em questão dentro dele
            for(int j = 0 ; j < qtd ; j++) {
                if(semRepeticao[j] == original[i]) {
                    existe = true;
                    break;
                }
            }
            if(!existe) {
                semRepeticao[qtd++] = original[i];
            }
        }
        // arrumando tamanho do vetor
        semRepeticao = Arrays.copyOf(semRepeticao, qtd);
        transicao.setCaminhos(semRepeticao);
        return transicao;*/
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
