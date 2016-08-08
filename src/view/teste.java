package view;

import java.util.ArrayList;
import java.util.List;

import model.Af;
import controller.*;

public class teste {

	public static void main(String[] args) {
		//Af tratarDados(String estados, List<String> transicoes, String estadoInicial, String estadoFinal) {
		List<String> lista = new ArrayList<String>();
		lista.add("(q0,a)->{q0,q1,q2},");
		lista.add("(q1,b)->{q1},");
		lista.add("(q2,c)->{q2},");
		lista.add("(q2,.)->{q1}");
		
		Arquivo.tratarDados("[q0,q1,q2],", "{a,b,c},", lista, "q0,","{q1}");
	}

}
