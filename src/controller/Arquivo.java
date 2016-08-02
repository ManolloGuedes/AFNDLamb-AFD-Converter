package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Af;

public class Arquivo {
	
	public static BufferedReader LerArquivo(String nome) throws FileNotFoundException {
		FileReader arq = new FileReader(nome);
		BufferedReader ler = new BufferedReader(arq);
		return ler;
	}
	public static Af getAf (BufferedReader ler) throws IOException {
		Af afndLamb;
		ler.readLine(); //l� o caracter de abertura da especifica��o do AFND-Lamb "("
		
		/*L� o conjunto de estados no arquivo*/
		String estados = ler.readLine(); 
		
		ler.readLine(); //l� o caracter de abertura das transi��es '{'
		
		/*L� as transi��es*/
		List transicoes;	//lista que receber� a String que contem as transi��es antes de serem processadas
		transicoes = new ArrayList<String>();
		
		String aux = ler.readLine();
		while (aux!="},") {
			transicoes.add(aux);
			aux = ler.readLine();
		}
		
		/*L� estados finais e inicial*/
		String estadoInicial = ler.readLine();
		String estadoFinal = ler.readLine();
		
		afndLamb = tratarDados(aux, transicoes, estadoInicial, estadoFinal);		
		return afndLamb;
	}
	private static Af tratarDados(String estados, List transicoes, String estadoInicial, String estadoFinal) {
		/*Preparar estados*/
		estados = estados.substring(1, estados.length()-1); //elimina os par�nteses
		String[] estadosAux = desmembrar(estados, ","); //separa os estados
		
		/*Preparar transicoes*/
		
		/*Preparar estado inicial*/
		
		/*Preparar estado final*/
		
		/*Criar um af e retorn�-lo*/
		return null;
	}
	
	private static String[] desmembrar(String str, String separador) {
		return str.split(separador); //separa os estados
	}
}
