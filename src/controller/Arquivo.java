package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Af;
import model.Estado;
import model.Transicao;

public class Arquivo {
	
	public static BufferedReader LerArquivo(String nome) throws FileNotFoundException {
		FileReader arq = new FileReader(nome);
		BufferedReader ler = new BufferedReader(arq);
		return ler;
	}
	public static Af getAf (BufferedReader ler) throws IOException {
		Af afndLamb;
		ler.readLine(); //lê o caracter de abertura da especificação do AFND-Lamb "("
		
		/*Lê o conjunto de estados no arquivo*/
		String estados = ler.readLine(); 
		
		/*Lê a linguagem*/
		String linguagem = ler.readLine();
		
		ler.readLine(); //lê o caracter de abertura das transições '{'
		
		/*Lê as transições*/
		List<String> transicoes;	//lista que receberá a String que contem as transições antes de serem processadas
		transicoes = new ArrayList<String>();
		
		String aux = ler.readLine();
		while (aux!="},") {
			transicoes.add(aux);
			aux = ler.readLine();
		}
		
		/*Lê estados finais e inicial*/
		String estadoInicial = ler.readLine();
		String estadoFinal = ler.readLine();
		
		afndLamb = tratarDados(aux, linguagem, transicoes, estadoInicial, estadoFinal);		
		return afndLamb;
	}
	public static Af tratarDados(String estados, String linguagem, List<String> transicoes, String estadoInicial, String estadoFinal) {	
		/*Preparar estados*/
		estados = estados.substring(1, estados.length()-2); //elimina os parênteses
		String[] estadosAux = estados.split(","); //separa os estados
		Map<String, Estado> estadosAF = new HashMap<String, Estado>(); /*cria mapa de estados*/
		for(int i = 0; i < estadosAux.length; i++) {
			estadosAF.put(estadosAux[i], null);
		}
		
		/*Preparar linguagem*/
		linguagem = linguagem.substring(1, linguagem.length()-2);
		String[] ling = linguagem.split(",");
		
		/*Preparar estado inicial*/
		estadoInicial = estadoInicial.substring(0, estadoInicial.length()-1);
		
		/*Preparar estado final*/
		estadoFinal = estadoFinal.substring(1,estadoFinal.length()-1);
		String[] estFinal = estadoFinal.split(",");
		
		/*Preparar transicoes*/
		String[][] transAux = new String[transicoes.size()][2]; 	//cria uma matriz de Strings, onde cada linha 
		for(int i = 0; i < transicoes.size(); i++) {		//será uma transição nao tratada. 
			transAux[i] = transicoes.get(i).split("->");
			transAux[i][0] = transAux[i][0].substring(1, transAux[i][0].length()-1); //remove os parenteses
			if (i != transicoes.size()-1)
				transAux[i][1] = transAux[i][1].substring(1, transAux[i][1].length()-2);	//remove as chaves e a virgula final
			else
				transAux[i][1] = transAux[i][1].substring(1, transAux[i][1].length()-1);	//remove apenas as chaves pq é o último elemento
		}											
		
		/*caminhar pelo transAux e ir jogando dentro do estadosAF*/
		for(int i = 0; i < transicoes.size(); i++) { 
			String[] funcaoTransicao = transAux[i][0].split(","); //separa estado e entrada da função de transição do AF
			Transicao transicaoAux = new Transicao();
			transicaoAux.setEntrada(funcaoTransicao[1]); //insere a entrada para a transicao
			
			String[] caminhos = transAux[i][1].split(","); //separa os caminhos da transicao
			transicaoAux.setCaminhos(caminhos);//insere os caminhos
			
			Estado estadoAuxiliar = estadosAF.get(funcaoTransicao[0]); //busca o estado em questão
			
			if (estadoAuxiliar == null) { //verifica se é o primeiro valor a ser inserido na posição
				estadoAuxiliar = new Estado();
			}
			
			if (funcaoTransicao[0].equals(estadoInicial)) {
				estadoAuxiliar.setInicial(true);
			} else {
				estadoAuxiliar.setInicial(false);
			}
			estadoAuxiliar.setFinal(Utils.Exists(estFinal, funcaoTransicao[0])); //verifica se existe é final e seta
			
			estadoAuxiliar.addTransicao(transicaoAux); //adiciona transição no estado
			
			estadosAF.put(funcaoTransicao[0], estadoAuxiliar); //adiciona estado na coleção de estados
		}
		
		Af afndLamb = new Af(estadosAF, ling);
		Imprimir(afndLamb);
		return afndLamb;
	}
	private static void Imprimir(Af afnd) {
		System.out.println("Estados");
		Map<String, Estado> estado = afnd.getEstados();

		for (Map.Entry<String,Estado> pair : estado.entrySet()) {
		    System.out.println(pair.getKey());
		    System.out.println(pair.getValue());
		}
	}
}
