package model;

import java.util.ArrayList;

public class Transicao {
	
	private String[] caminhos;
	
	public String[] getCaminhos() {
		return caminhos;
	}
	public void setCaminhos(String[] caminhos) {
		this.caminhos = caminhos;
	}
	public boolean existe (String str) {
		for (String s : caminhos) {
			if (s.equals(str)) {
				return true;
			}
		}
		return false;
	}

}
