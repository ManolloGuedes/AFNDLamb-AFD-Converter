package model;

import java.util.ArrayList;

public class Estado {
	private String id;
	private ArrayList<Transicao> transicao;
	private boolean isFinal;
	private boolean isInicial;
	
	public Estado(String id, ArrayList<Transicao> transicao) {
		this.setId(id);
		this.setTransicao(transicao);
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public ArrayList<Transicao> getTransicao() {
		return transicao;
	}

	private void setTransicao(ArrayList<Transicao> transicao) {
		this.transicao = transicao;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public boolean isInicial() {
		return isInicial;
	}

	public void setInicial(boolean isInicial) {
		this.isInicial = isInicial;
	}
}