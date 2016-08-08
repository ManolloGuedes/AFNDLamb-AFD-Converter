package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Af {
	private Map<String, Estado> estados;
	private ArrayList <String> linguagem;
	
	public Af() {
		super();
		estados = new HashMap<String, Estado>(); 
		linguagem = new ArrayList<String>();
	}

	public Af(Map<String, Estado> estados, String[] linguagem) {
		this.setEstados(estados);
		
		this.linguagem = new ArrayList<String>();
		this.setLinguagem(linguagem);
	}

	public Map<String, Estado> getEstados() {
		return estados;
	}

	public void setEstados(Map<String, Estado> estados) {
		this.estados = estados;
	}

	public ArrayList<String> getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String[] ling) {
		for (String s : ling) {
			this.linguagem.add(s);
		}
	}
}