package model;

import java.util.ArrayList;

public class Transicao {
	
	private String entrada;
	private ArrayList<Estado> caminhos;
	
	public String getEntrada() {
		return entrada;
	}
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}
	public ArrayList<Estado> getCaminhos() {
		return caminhos;
	}
	public void setCaminhos(ArrayList<Estado> caminhos) {
		this.caminhos = caminhos;
	}

}
