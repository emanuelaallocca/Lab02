package it.polito.tdp.alien.model;
import java.util.*;

public class Dizionario {
	
	String nome;
	String traduzione;

	Map <String, String> mapParole;
	
	public Dizionario() {
		this.mapParole = new HashMap<>();
	}
	
	public void addParole(String nome, String traduzione) {
		if(this.cercaTraduzione(nome)==null)
		  this.mapParole.put(nome, traduzione);
		else
			this.mapParole.put(nome, this.cercaTraduzione(nome)+" "+traduzione);
	}
	
	public String cercaTraduzione(String nome) {
		return this.mapParole.get(nome);
	}

	
	public void cancellaTutto () {
		this.mapParole.clear();
	}

	public  List<String> cercaChiave (String s1, String s2) {
		int i1 = s1.length();
		int i2 = s2.length();
		Set <String> parole = this.mapParole.keySet();
		List <String> possibile = new ArrayList<>();
		for (String s : parole) {
			//if (s.length()== (i1+i2+1))
				if (s.contains(s1)&& s.contains(s2))
					possibile.add(s);
			
		}
		List <String> traduzioni = new ArrayList<>();
		for (String ss : possibile)
			traduzioni.add(this.cercaTraduzione(ss));
		return traduzioni;
	}
}
