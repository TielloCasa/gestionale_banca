package it.gianluca;

import java.util.ArrayList;

public class Correntista extends Utente{

	private String nome;
	private String cognome;
	private String cf;
	
	//ContoCorrente conto = new ContoCorrente();
	ArrayList<ContoCorrente> conto = new ArrayList(); //mi gestisco più conti ad un singolo correntista
	
	
	@Override
	public String toString() {
		return "Correntista [nome=" + nome + ", cognome=" + cognome + ", cf=" + cf + ", conto=" + conto + "]";
	}


	public Correntista(String nome, String cognome, String cf, String n_conto, double saldo) {
		this.nome=nome;
		this.cognome=cognome;
		this.cf=cf;
		this.conto.add(new ContoCorrente(n_conto, saldo));
		//this.conto.setSaldo(saldo);
		
	}

	
	//metodo che mi agevola l'ordinamento
	public void setCorrentista(Correntista corr) {
		this.nome = corr.getNome();
		this.cognome = corr.getCognome();
		this.cf= corr.getCf();
		
		conto.clear();
		if(corr.conto.size()>1) {
			for(int i=0;i<corr.conto.size();i++) {
				this.conto.add(new ContoCorrente(corr.conto.get(i).getNumero_conto(),corr.conto.get(i).getSaldo()));
			}
		}
	}
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getCf() {
		return cf;
	}


	public void setCf(String cf) {
		this.cf = cf;
	}

	
	
}
