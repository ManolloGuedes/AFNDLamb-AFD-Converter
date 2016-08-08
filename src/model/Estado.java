package model;

import java.util.ArrayList;

public class Estado {
	private ArrayList<Transicao> transicao;
	private boolean isFinal;
	private boolean isInicial;
	
	
	public Estado() {
		super();
		this.transicao = new ArrayList<Transicao>();
	}

	public ArrayList<Transicao> getTransicao() {
		return transicao;
	}

	public void setTransicao(ArrayList<Transicao> transicao) {
		this.transicao = transicao;
	}
	
	public void addTransicao(Transicao transicao) {
		this.transicao.add(transicao);
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