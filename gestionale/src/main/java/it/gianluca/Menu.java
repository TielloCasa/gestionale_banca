package it.gianluca;

import java.util.Scanner;

public class Menu {

	private boolean charge; // mi serve a controllare se il caricamento è stato eseguito
	private int scelta=0; // mi serve per gestire la scelta dell'utente
	Scanner in = new Scanner(System.in);
	
	
public void stampaMenu(Banca banca) {
	
	charge=false;
	while(scelta!=7) {
	System.out.println("Cosa vuoi fare?");
	System.out.println("1)Caricamento");
	System.out.println("2)Modifica dati utenti");
	System.out.println("3)Ordina correntisti per cognome e stampa");
	System.out.println("4)Prelievo da fare tramite numero conto e anche per Codice Fiscale");
	System.out.println("5)Versamenti");
	System.out.println("6)Stampa dettagliata egli utenti");
	System.out.println("7)Esci");
	
	scelta = Integer.parseInt(in.nextLine());
	switch(scelta) {
	case 1:
		banca.caricamento();
		charge=true;
		break;
	case 2:
		if(charge) {
		banca.modifica();
		}else {
			System.out.println("Non puoi farlo senza aver caricato prima");
		}
		break;
	case 3:
		if(charge) {
		banca.ordinamento();
		}else {
			System.out.println("Non puoi farlo senza aver caricato prima");
		}
		break;
	case 4:
		if(charge) {
		banca.prelievo();
		}else {
			System.out.println("Non puoi farlo senza aver caricato prima");
		}
		break;
	case 5:
		if(charge) {
		banca.versamento();
		}else {
			System.out.println("Non puoi farlo senza aver caricato prima");
		}
		break;
	case 6:
		if(charge) {
		banca.stampa();
		}else {
			System.out.println("Non puoi farlo senza aver caricato prima");
		}
		break;
	case 7:
		System.out.println("Arrivederci...");
		break;
	default:
		System.out.println("Funzione non ancora implementata");
		break;
	}
	}
	}
	
}
