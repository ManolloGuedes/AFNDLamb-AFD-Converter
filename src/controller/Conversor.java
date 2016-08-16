package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import model.Af;
import model.Estado;
import model.Transicao;

public class Conversor {
	private Map<String, Transicao> fechoLambda;
	
	public Conversor () {
		fechoLambda = new HashMap<String, Transicao>();
	}
	
	public static Af AfndLambToAfd () {
		return null;
	}
	
	public static Af afndToAfd () {
		return null;
	}
	
	public static Af afndLambToAfnd (Af afndLamb) {
		for (Entry<String, Estado> estado : afndLamb.getEstados().entrySet()) {
			
		}
		return null;
	}
	
}
