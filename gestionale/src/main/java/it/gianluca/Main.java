/*Ho scelto di rendere la classe Utente, classe padre di classe correntista per gestire eventuali implementazioni
  di login dell'utente.
  La classe correntista ha all'interno i dati annessi + un ArrayList di tipo conto corrente(per gestire più conti 
  annessi ad una singola persona, visto che il correntista ha un conto(composizione).
  La classe conto corrente ha all'interno n_conto e saldo.
  Per gestire al meglio i dati, ho deciso di creare una classe banca, dove ho all'interno un ArrayList di correntisti
  e i vari metodi annessi all'esercizio(caricamento, ordinamento, ecc...)
  Per rendere il Main il più pulito e possibile ho creato una classe Menu che ha all'interno una switch case*/
  	

package it.gianluca;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Banca banca = new Banca();
		Menu menu = new Menu();
		menu.stampaMenu(banca);
		
	}

}
