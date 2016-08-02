package model;

import java.util.ArrayList;

public class Af {
	private ArrayList<Estado> estados;
	private ArrayList <String> linguagem;

	public Af(ArrayList<Estado> estados, ArrayList<String> linguagem) {
		this.setEstados(estados);
		this.setLinguagem(linguagem);
	}

	public ArrayList<Estado> getEstados() {
		return estados;
	}

	private void setEstados(ArrayList<Estado> estados) {
		this.estados = estados;
	}

	public ArrayList<String> getLinguagem() {
		return linguagem;
	}

	private void setLinguagem(ArrayList<String> linguagem) {
		this.linguagem = linguagem;
	}
}