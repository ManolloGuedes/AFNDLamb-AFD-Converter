package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import model.Af;
import model.AfndLamb;
import model.Estado;
import model.Transicao;

public class Conversor {
	
	public Af AfndLambToAfd (AfndLamb afndLamb) {
		Af afnd = this.afndLambToAfnd(afndLamb);
		Af afd = this.afndToAfd(afnd);
		return afd;
	}
	public Af afndToAfd (Af afnd) {
		String inicial = afnd.getEstadoInicial();
		Map<String, Estado> estados = new HashMap<String, Estado>();
		
		estados = this.GerarTabelaAfd(inicial, afnd, estados);
		//acessar inicial
		//caminhar pelas transições de acordo com a linguagem
		//pegar os estados novos que surgiram e jogar denro do Map
		//caminhar dentro deles
		Af afd = new Af();
		afd.setEstados(estados);
		return afd;
	}
	private Map<String, Estado> GerarTabelaAfd(String estadoNome, Af afnd, Map<String, Estado>estadosAfd) {
		String[] estadosCompoe = estadoNome.split(",");
		Estado estado = new Estado();
		//para cada entrada
		for(String entrada : afnd.getLinguagem()) {
			String[] caminhos = null;
			//pego os caminhos de cada estado que compõe o estado do AFD
			for(String estadoAtual : estadosCompoe) {
				if (afnd.getEstados().get(estadoAtual).getTransicao().get(entrada) != null) {
					caminhos = Utils.concatenarArray(caminhos, afnd.getEstados().get(estadoAtual).getTransicao().get(entrada).getCaminhos());
					Transicao aux = new Transicao();
					aux.setCaminhos(caminhos);
					aux = Utils.RemoverRepeticoes(aux);
					String estadoNome2 = Utils.arrayToString(aux.getCaminhos());
					if(!estadosAfd.containsKey(estadoNome2)) {
						estadosAfd.put(estadoNome2, null);
						GerarTabelaAfd(estadoNome2, afnd, estadosAfd);
					}
					caminhos = aux.getCaminhos();
				}
			}
			//Crio uma transicao
			Transicao transicao = new Transicao();
			transicao.setCaminhos(caminhos);
			transicao = Utils.RemoverRepeticoes(transicao);
			estado.addTransicao(transicao, entrada);
			estadosAfd.put(estadoNome, estado);
		}
		return estadosAfd;
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
