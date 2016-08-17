package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import controller.Utils;

public class AfndLamb extends Af {
	private Map<String, Transicao> fechoLambda; //nome do estado é a chave e a transição (vetor de strings) é o valor;

	public AfndLamb(Map<String, Estado> estadosAF, String[] ling) {
		super(estadosAF, ling);
		fechoLambda = new HashMap<String, Transicao>();
	}
	public Map<String, Transicao> getFechoLambda() {
		return fechoLambda;
	}

	public void setFechoLambda() {
		Map<String, Estado> estados = this.getEstados();
		for (Entry<String, Estado> iterator : estados.entrySet()) {
			this.calcularFecho(iterator.getKey());
		}
	}
	private void calcularFecho(String key) {
		
		/*percorre caminho recursivamente criando fecho lambda*/
		Transicao lamb = this.getEstados().get(key).getTransicao().get(".");
		if (lamb != null) {
			String[] transLamb = lamb.getCaminhos();
			Transicao fechoAtual = null ;
			for (int i = 0; i < transLamb.length; i++) {
				fechoAtual = fechoLambda.get(key);
				String[] aux;
				if (fechoAtual != null) {
					aux = new String[fechoAtual.getCaminhos().length + 1];
				}
				else {
					aux = new String[1];
					fechoAtual = new Transicao();
				}
				if (fechoLambda.get(key) != null) {
					if (fechoLambda.get(key).existe(transLamb[i])) {
						break;
					}
				}
				aux[aux.length-1] = transLamb[i];
				fechoAtual.setCaminhos(aux);
				fechoLambda.put(key, fechoAtual);
				calcularFecho(transLamb[i]);
				if (this.getFechoLambda().get(transLamb[i]) != null) {
					String[] aux2 = Utils.concatenarArray(fechoAtual.getCaminhos(), this.getFechoLambda().get(transLamb[i]).getCaminhos());
					fechoAtual.setCaminhos(aux2);
					fechoLambda.put(key, fechoAtual);
				}
			}
			String[] str = new String[1];
			str[0] = key;
			transLamb = Utils.concatenarArray(fechoLambda.get(key).getCaminhos(), str);
			fechoAtual.setCaminhos(transLamb);
			fechoLambda.put(key, fechoAtual);
			fechoLambda.put(key, Utils.RemoverRepeticoes(fechoLambda.get(key)));
		}
	}
}
