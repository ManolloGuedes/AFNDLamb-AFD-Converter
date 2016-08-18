package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import model.Af;
import model.AfndLamb;
import model.Estado;
import model.Transicao;

public class Conversor {
	
	public Af AfndLambToAfd () {
		return null;
	}
	public Af afndToAfd (Af afnd) {		
		return null;
	}
	public Af afndLambToAfnd (AfndLamb afndLamb) {
		//procurar um estado
		for (Entry<String, Estado> a : afndLamb.getEstados().entrySet()) {
			if (a.getValue() != null) {
				//recupero cada transição que existe e acrescento o fecho lambda de cada destino de transicao
				for(String leitura : afndLamb.getLinguagem()) {
					Transicao transicao = a.getValue().getTransicao().get(leitura);
					Transicao aux = this.completarTransicaoLambdaAfnd(transicao, afndLamb);
					afndLamb.getEstados().get(a.getKey()).addTransicao(aux, leitura);
					aux = this.completarTransicao(transicao, afndLamb, a, leitura);
					afndLamb.getEstados().get(a.getKey()).addTransicao(aux, leitura);
				}
			}
			
		}
		Af afnd = afndLamb;
		return afnd;
		//ver onde suas transições o levam
		//acrescentar o fecho lambda do destino no estado inicial
		//ver se o estado inicial possui transição lambda
		//ver se o destino da transicao lambda possui transição com a enrada escolhida
		//fazer isso para o restante dos estados
	}
	private Transicao completarTransicao(Transicao transicao, AfndLamb afndLamb, Entry<String, Estado> a, String leitura) {
		//navega nos lambdas procurando transição para a entrada referida
		Map<String, Transicao> fecho = afndLamb.getFechoLambda();
		String[] conjuntoTransicoes = null;
		if (transicao != null) {
			conjuntoTransicoes = transicao.getCaminhos();
		}
		if (fecho.get(a.getKey()) != null) {
			for(String lambda : fecho.get(a.getKey()).getCaminhos()) {
				Transicao caminhosAntigos = afndLamb.getEstados().get(lambda).getTransicao().get(leitura);
				if (caminhosAntigos != null) {
					conjuntoTransicoes = Utils.concatenarArray(conjuntoTransicoes, caminhosAntigos.getCaminhos());
				}
			}
		}
		Transicao aux = new Transicao();
		aux.setCaminhos(conjuntoTransicoes);
		aux = Utils.RemoverRepeticoes(aux);
		return aux;
	}
	private Transicao completarTransicaoLambdaAfnd(Transicao transicao, AfndLamb afndLamb) {
		if (transicao != null) {
			String[] conjuntoTransicoes = transicao.getCaminhos();
			for(String str : transicao.getCaminhos()) {
				if (afndLamb.getFechoLambda() != null && afndLamb.getFechoLambda().get(str) != null)
					conjuntoTransicoes = Utils.concatenarArray(conjuntoTransicoes, afndLamb.getFechoLambda().get(str).getCaminhos());
			}
			Transicao aux = new Transicao();
			aux.setCaminhos(conjuntoTransicoes);
			aux = Utils.RemoverRepeticoes(aux);
			
			return aux;
		}
		return null;
	}
	
}
