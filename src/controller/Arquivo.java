package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.Af;
import model.AfndLamb;
import model.Estado;
import model.Transicao;
import view.Converte;

public class Arquivo {
	
	public static BufferedReader lerArquivo(String nome) throws FileNotFoundException {
		FileReader arq = new FileReader(nome);
		BufferedReader ler = new BufferedReader(arq);
		return ler;
	}
	public static AfndLamb getAf (BufferedReader ler) throws IOException {
		AfndLamb afndLamb;
		ler.readLine(); //l� o caracter de abertura da especifica��o do AFND-Lamb "("
		
		/*L� o conjunto de estados no arquivo*/
		String estados = ler.readLine(); 
		
		/*L� a linguagem*/
		String linguagem = ler.readLine();
		
		ler.readLine(); //l� o caracter de abertura das transi��es '{'
		
		/*L� as transi��es*/
		List<String> transicoes;	//lista que receber� a String que contem as transi��es antes de serem processadas
		transicoes = new ArrayList<String>();
		
		String aux = ler.readLine();
		while (!aux.equals("},")) {
			transicoes.add(aux);
			aux = ler.readLine();
		}
		
		/*L� estados finais e inicial*/
		String estadoInicial = ler.readLine();
		String estadoFinal = ler.readLine();
		
		afndLamb = tratarDados(estados, linguagem, transicoes, estadoInicial, estadoFinal);
		return afndLamb;
	}
	public static AfndLamb tratarDados(String estados, String linguagem, List<String> transicoes, String estadoInicial, String estadoFinal) {	
		/*Preparar estados*/
		estados = estados.substring(1, estados.length()-2); //elimina os par�nteses
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
		for(int i = 0; i < transicoes.size(); i++) {		//ser� uma transi��o nao tratada. 
			transAux[i] = transicoes.get(i).split("->");
			transAux[i][0] = transAux[i][0].substring(1, transAux[i][0].length()-1); //remove os parenteses
			if (i != transicoes.size()-1)
				transAux[i][1] = transAux[i][1].substring(1, transAux[i][1].length()-2);	//remove as chaves e a virgula final
			else
				transAux[i][1] = transAux[i][1].substring(1, transAux[i][1].length()-1);	//remove apenas as chaves pq � o �ltimo elemento
		}											
		
		/*caminhar pelo transAux e ir jogando dentro do estadosAF*/
		for(int i = 0; i < transicoes.size(); i++) { 
			String[] funcaoTransicao = transAux[i][0].split(","); //separa estado e entrada da fun��o de transi��o do AF
			Transicao transicaoAux = new Transicao();
			
			String[] caminhos = transAux[i][1].split(","); //separa os caminhos da transicao
			transicaoAux.setCaminhos(caminhos);//insere os caminhos
			
			Estado estadoAuxiliar = estadosAF.get(funcaoTransicao[0]); //busca o estado em quest�o
			
			if (estadoAuxiliar == null) { //verifica se � o primeiro valor a ser inserido na posi��o
				estadoAuxiliar = new Estado();
			}
			
			if (funcaoTransicao[0].equals(estadoInicial)) {
				estadoAuxiliar.setInicial(true);
			} else {
				estadoAuxiliar.setInicial(false);
			}
			estadoAuxiliar.setFinal(Utils.Exists(estFinal, funcaoTransicao[0])); //verifica se existe � final e seta
			
			estadoAuxiliar.addTransicao(transicaoAux, funcaoTransicao[1]); //adiciona transi��o no estado com a referida entrada
			
			estadosAF.put(funcaoTransicao[0], estadoAuxiliar); //adiciona estado na cole��o de estados
		}
		
		AfndLamb afndLamb = new AfndLamb(estadosAF, ling);
		afndLamb.setFechoLambda();
		return afndLamb;
	}
	private static void imprimir(AfndLamb afnd) {
		System.out.println("Estados");
		Map<String, Estado> estado = afnd.getEstados();

		for (Map.Entry<String,Estado> pair : estado.entrySet()) {
		    System.out.println(pair.getKey());
		    System.out.println(pair.getValue());
		}
		Conversor conversor = new Conversor();
		conversor.afndLambToAfnd(afnd);
	}
	public static void escreverArquivo(String nomeArq, AfndLamb afndLamb, Af afnd, Af afd) throws IOException {
		Writer output;
		output = new BufferedWriter(new FileWriter(nomeArq));
		escreverAfndLamb(output, afndLamb);
		output.append("\n\r\n");
		output.append("\n\r\n");
		escreverAfnd(output, afndLamb, afnd);
		output.append("\n\r\n");
		output.append("\n\r\n");
		escreverAfd(output, afndLamb, afd);
		output.close();
	}
	private static void escreverAfd(Writer output, AfndLamb afndLamb, Af afd) throws IOException {
		output.append("Fun��o de Transi��o D\t\t\t");
		for (String entrada : afndLamb.getLinguagem()) {
			output.append(entrada + "\t\t\t");
		}
		output.append("\n\r\n");
		for(Entry<String, Estado> loop : afd.getEstados().entrySet()) {
			output.append("<"+loop.getKey()+">");
			for (String entrada : afndLamb.getLinguagem()) {
				if (loop.getValue().getTransicao().get(entrada) == null) {
					output.append("\t\t\t\t\t\t " + "-"+" ");
				}
				else
					output.append("\t\t\t\t\t\t<" + Utils.arrayToString(loop.getValue().getTransicao().get(entrada).getCaminhos()) + ">");
			}
			output.append("\n\r\n");
		}
	}
	private static void escreverAfndLamb(Writer output, AfndLamb afndLamb) throws IOException {
		output.append("Fun��o de Transi��o ND-Lambda\t\t\t");
		for (String entrada : afndLamb.getLinguagem()) {
			output.append(entrada + "\t\t\t");
		}
		output.append("lambda\n\r\n");
		for(Entry<String, Estado> loop : afndLamb.getEstados().entrySet()) {
			output.append(loop.getKey());
			for (String entrada : afndLamb.getLinguagem()) {
				if (loop.getValue() == null || loop.getValue().getTransicao().get(entrada) == null) {
					output.append("\t\t\t\t\t\t{}");
				}
				else
					output.append("\t\t\t\t\t\t{" + Utils.arrayToString(loop.getValue().getTransicao().get(entrada).getCaminhos()) + "}");
			}
			if (loop.getValue() == null || loop.getValue().getTransicao().get(".") == null) {
				output.append("\t\t\t\t\t\t{}");
			}
			else
				output.append("\t\t\t\t\t\t{" + Utils.arrayToString(loop.getValue().getTransicao().get(".").getCaminhos()) + "}");
			output.append("\n\r\n");
		}
		
	}
	private static void escreverAfnd(Writer output, AfndLamb afndLamb, Af afnd) throws IOException {
		output.append("Fun��o de Transi��o ND\t\t\t");
		for (String entrada : afndLamb.getLinguagem()) {
			output.append(entrada + "\t\t\t");
		}
		output.append("\n\r\n");
		for(Entry<String, Estado> loop : afnd.getEstados().entrySet()) {
			output.append(loop.getKey());
			for (String entrada : afndLamb.getLinguagem()) {
				if (loop.getValue() == null || loop.getValue().getTransicao().get(entrada) == null) {
					output.append("\t\t\t\t\t\t{}");
				}
				else
					output.append("\t\t\t\t\t\t{" + Utils.arrayToString(loop.getValue().getTransicao().get(entrada).getCaminhos()) + "}");
			}
			output.append("\n\r\n");
		}
		
	}
}
