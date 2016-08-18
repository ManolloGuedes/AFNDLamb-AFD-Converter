package view;

import java.util.ArrayList;
import java.util.List;

import model.Af;
import model.AfndLamb;
import controller.*;

public class teste {

	public static void main(String[] args) {
		//Af tratarDados(String estados, List<String> transicoes, String estadoInicial, String estadoFinal) {
		List<String> lista = new ArrayList<String>();
		/*lista.add("(q0,a)->{q0,q1,q2},");
		lista.add("(q1,b)->{q1},");
		lista.add("(q2,c)->{q2},");
		lista.add("(q2,.)->{q1}");*/
		
		/*lista.add("(q0,a)->{q0,q1,q2},");
		lista.add("(q0,.)->{q2},");
		lista.add("(q1,b)->{q1},");
		lista.add("(q1,.)->{q0},");
		lista.add("(q2,c)->{q2},");
		lista.add("(q2,.)->{q1}");*/
		
		lista.add("(q0,a)->{q1},");
		lista.add("(q1,b)->{q2},");
		lista.add("(q1,.)->{q3},");
		lista.add("(q2,.)->{q3},");
		lista.add("(q3,a)->{q3,q4},");
		lista.add("(q3,b)->{q3},");
		lista.add("(q4,.)->{q2}");
		
		AfndLamb afndLamb = Arquivo.tratarDados("[q0,q1,q2],", "{a,b,c},", lista, "q0,","{q1}");
		Conversor conversor = new Conversor();
		conversor.AfndLambToAfd(afndLamb);
	}

}
