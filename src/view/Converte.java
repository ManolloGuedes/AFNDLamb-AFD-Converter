package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import controller.Arquivo;
import controller.Conversor;
import model.Af;
import model.AfndLamb;

public class Converte {

	public static void main(String[] args) {
		AfndLamb afndLamb = null;
		Af afnd = null;
		Af afd = null;
		Conversor conversor = new Conversor();
		BufferedReader leitor = null;
		try {
			leitor = Arquivo.lerArquivo(args[0]);
		} catch (FileNotFoundException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", 
			e.getMessage());
		}
		try {
			afndLamb = Arquivo.getAf(leitor);
			afnd = conversor.afndLambToAfnd(afndLamb);
			afd = conversor.afndToAfd(afnd);
			Arquivo.escreverArquivo(args[1], afndLamb, afnd, afd);
		} catch (IOException e) {
			System.err.printf("Erro na leitura do arquivo: %s.\n",
			e.getMessage());
		}
		
		
	}

}
