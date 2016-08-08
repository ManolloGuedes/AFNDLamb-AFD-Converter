package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import controller.Arquivo;
import model.Af;

public class Converte {

	public static void main(String[] args) {
		Af afndLamb = null;
		BufferedReader leitor = null;
		try {
			leitor = Arquivo.LerArquivo(args[0]);
		} catch (FileNotFoundException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", 
			e.getMessage());
		}
		try {
			afndLamb = Arquivo.getAf(leitor);
		} catch (IOException e) {
			System.err.printf("Erro na leitura do arquivo: %s.\n",
			e.getMessage());
		}
	}

}
