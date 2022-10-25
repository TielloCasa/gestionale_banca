package it.gianluca;

import java.util.ArrayList;
import java.util.Scanner;

public class Banca {

	private ArrayList<Correntista> correntista = new ArrayList<Correntista>();
	private Scanner in = new Scanner(System.in);
	
	public void azzera() {
		correntista.clear();
	}
	
	
	public void caricamento() {
		boolean verifica; //verifica del n_conto duplicato
		int np; //mi serve per indicare il numero di persone che l'utente vuole inserire
		String nome;
		String cognome;
		String cf;
		String n_conto;
		double saldo=0;
		System.out.println("Dimmi quanti conti vuoi inserire");
		np=Integer.parseInt(in.nextLine());
		
		
		azzera();//mi serve per azzerare l'ArrayList
		for(int i=0;i<np;i++) {
			verifica=true;
			System.out.println("Inserire il nome del propietario del "+(i+1)+ "o conto");
			nome=in.nextLine();
			System.out.println("Inserire il cognome del propietario del "+(i+1)+"o conto");
			cognome=in.nextLine();
			System.out.println("Inserire il cf del propietario del "+(i+1)+"o conto");
			cf=in.nextLine();
			
			System.out.println("Dammi il numero del conto della "+(i+1)+"o conto");
			n_conto=in.nextLine();
			for(int j=0; j<correntista.size(); j++) { //controllo se esiste di già n_conto
				for(int s=0; s<correntista.get(j).conto.size();s++) {
				if(this.correntista.get(j).conto.get(s).getNumero_conto().equals(n_conto)) {
					System.out.println("N_CONTO già esistenti, reinserire il campo");
					i--;
					verifica=false;
				}
				}
				}
			
			if(verifica) {
			System.out.println("Inserire il saldo");
			saldo=Double.parseDouble(in.nextLine());
			for(int j=0;j<correntista.size();j++) {  //controllo se già esiste il cf cosi da assegnare il conto a quella persona
				if(correntista.get(j).getCf().equals(cf)) {
					if(correntista.get(j).getNome().equals(nome) && correntista.get(j).getCognome().equals(cognome)) {
					System.out.println("Conto aggiunto a persona esistente");
					this.correntista.get(j).conto.add(new ContoCorrente(n_conto, saldo));
					verifica=false;
				}else{
					System.out.println("IL CF PUO' APPARTENERE AD UN SOLO CORRENTISTA, reinserisci");
					verifica=false;
					i--;
				}
				}
			}
			}
			
			if(verifica) {
			this.correntista.add(new Correntista(nome, cognome,cf,n_conto,saldo));
			System.out.println("Utente inserito");
			}
		}
		
		}
	
	
	
	
	
	
	public void stampa() {
		
			System.out.println(correntista);
		
	}
	
	
	
	
	
	
	
	public void ordinamento() {
		Correntista temp = new Correntista("","","","",0.00); //oggetto temporaneo per l'ordinamento
		
		String appoggio; //variabile d'appoggio per il nome, cognome, cf e n_conto
		double appo; //variabile d'appoggio per il saldo
		int verifica; //mi serve per verificare la differenza tra due stringhe tramite il metodo compareTO
		for(int i=0;i<correntista.size();i++) {
			for(int j=0;j<correntista.size();j++) {
				verifica = correntista.get(i).getCognome().compareToIgnoreCase(correntista.get(j).getCognome());
				if(verifica<0) {
				
					temp.setCorrentista(correntista.get(i)); //vado a scambiare direttaemnte gli oggetti cosi da non avere problemi con i conti(vedere il metodo setCorrentista in Correntista)
					correntista.get(i).setCorrentista(correntista.get(j));
					correntista.get(j).setCorrentista(temp);
				}
			}
		}
		
		stampa();
	}
	
	
	
	
	public void modifica() {
		
		String nome;
		String cognome;
		String cf;
		System.out.println("Dammi il nome");
		nome=in.nextLine();
		System.out.println("Dammi il cognome");
		cognome=in.nextLine();

		for(int i=0;i<correntista.size();i++) {
			if(correntista.get(i).getNome().equals(nome) && correntista.get(i).getCognome().equals(cognome)) {
				//stampa tutte le persone che hanno quel nome e cognome
				System.out.println(correntista.get(i));	
			}
		}
		
		System.out.println("Inserisci il cf dell'utente da modificare");
		cf=in.nextLine();
		for(int i=0;i<correntista.size();i++) {
		if(correntista.get(i).getNome().equals(nome) && correntista.get(i).getCognome().equals(cognome) && correntista.get(i).getCf().equals(cf)) {
			//se il cf dato in input corrisponde a al nome ed il cognome della persona che ha quel cf, allora prosegui con la modifica
			String nuovo_n;
			String nuovo_c;
			System.out.println("Dammi il nuovo nome");
			correntista.get(i).setNome(in.nextLine());
			System.out.println("Dammi il nuovo cognome");
			correntista.get(i).setCognome(in.nextLine());
			System.out.println("Modifica completata...");
		}
		}
	}
	
	
	
	public void prelievo() {
		
		boolean verifica=false; //verifica se il prelievo è andato a buon fine
		int scelta=0; //scelta del prelievo tramite cf o n_conto
		int prelievo; //somma da prelevare
		String appoggio; //appoggio per prendere il cf o n_conto della persona che vuole prelevare 
		System.out.println("1)Prelievo per codice fiscale");
		System.out.println("2)Prelievo per numero conto");
		
		
		scelta=Integer.parseInt(in.nextLine());
		switch(scelta) {
		case 1:
			System.out.println("Dammi il CF");
			appoggio=in.nextLine();
			
			for(int i=0;i<correntista.size();i++) {
				if(correntista.get(i).getCf().equals(appoggio)) {
					if(correntista.get(i).conto.size()>1) { //se il correntista ha più conti, blocca la ricerca per cf
						System.out.println("L'utente ha più conti, è preferibile fare la ricerca tramite n_conto");
					}else{
					System.out.println("Il saldo disponibile è di "+correntista.get(i).conto.get(0).getSaldo());
					System.out.println("Quanto vuoi prelevare?");
					prelievo=Integer.parseInt(in.nextLine());
					if(prelievo>correntista.get(i).conto.get(0).getSaldo()) { //se il prelievo è maggiore del conto, annulla il prelievo
					System.out.println("Non è possibile svolgere l'azione");
					}else{
					correntista.get(i).conto.get(0).setSaldo((correntista.get(i).conto.get(0).getSaldo()-prelievo));
					System.out.println("Prelievo eseguito");
					verifica=true;
					}
					}
				}
			}
			break;
		case 2:
			System.out.println("Dammi il N_CONTO");
			appoggio=in.nextLine();
			for(int i=0;i<correntista.size();i++) {
				for(int j=0;j<correntista.get(i).conto.size();j++) {
				if(correntista.get(i).conto.get(j).getNumero_conto().equals(appoggio)) {
					System.out.println("Il saldo disponibile è di "+correntista.get(i).conto.get(j).getSaldo());
					System.out.println("Quanto vuoi prelevare?");
					prelievo=Integer.parseInt(in.nextLine());
					if(prelievo>correntista.get(i).conto.get(j).getSaldo()) {
					System.out.println("Non è possibile svolgere l'azione");
					}else{
					correntista.get(i).conto.get(j).setSaldo((correntista.get(i).conto.get(j).getSaldo()-prelievo));
					System.out.println("Prelievo eseguito");
					verifica=true;
					}
				}
			}
			}
			if(!verifica) {
				System.out.println("La ricerca NON è andata a buon fine");
			}
			
			
			break;
		default:
			System.out.println("Annullamento in corso...");
			break;
		}	
		
	}
	
	
	public void versamento() { //il funzionamento è simile al metodo preleva
		
		boolean verifica; //verifica per controllare se il versamento è andato a buon fine
		String cf;//cf da prendere in input
		int versamento; //valore versamento
		int scelta=0;
		String appoggio;
		System.out.println("Versamento:");
		stampa();
		
		System.out.println("1)Versamento per codice fiscale");
		System.out.println("2)Versamento per numero conto");
		
		verifica=false;
		scelta=Integer.parseInt(in.nextLine());
		switch(scelta) {
		case 1:
			System.out.println("Dammi il CF");
			appoggio=in.nextLine();
			
			for(int i=0;i<correntista.size();i++) {
				if(correntista.get(i).getCf().equals(appoggio)) {
					if(correntista.get(i).conto.size()>1) {
						System.out.println("L'utente ha più conti, è preferibile fare la ricerca tramite n_conto");
					}else{
					System.out.println("Il saldo disponibile è di "+correntista.get(i).conto.get(0).getSaldo());
					System.out.println("Quanto vuoi versare?");
					versamento=Integer.parseInt(in.nextLine());
					
					correntista.get(i).conto.get(0).setSaldo((correntista.get(i).conto.get(0).getSaldo()+versamento));
					System.out.println("Versamento eseguito");
					verifica=true;
					}
				
				}
			}
			break;
		case 2:
			System.out.println("Dammi il N_CONTO");
			appoggio=in.nextLine();
			for(int i=0;i<correntista.size();i++) {
				for(int j=0;j<correntista.get(i).conto.size();j++) {
				if(correntista.get(i).conto.get(j).getNumero_conto().equals(appoggio)) {
					System.out.println("Il saldo disponibile è di "+correntista.get(i).conto.get(j).getSaldo());
					System.out.println("Quanto vuoi versare?");
					versamento=Integer.parseInt(in.nextLine());
					
					correntista.get(i).conto.get(j).setSaldo((correntista.get(i).conto.get(j).getSaldo()+versamento));
					System.out.println("Versamento eseguito");
					verifica=true;
					}
				
			}
			}
			if(!verifica) {
				System.out.println("La ricerca NON è andata a buon fine");
			}
			
			
			break;
		default:
			System.out.println("Annullamento in corso...");
			break;
		}	
		
	
		if(!verifica) {
			System.out.println("Il versamento non è andato a buon fine");
		}
	}
	
}
