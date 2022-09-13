package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import game.Match;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Match match = new Match("words.txt"); //Chama o construtor
		
		int length = match.getChosenWord().length;
		boolean[] letraDescoberta = new boolean[length];
		
		//Inicializa o vetor com tudo falso para o primeiro print da UI
		for (int i=0; i<length; i++) {
			letraDescoberta[i] = false; 
		}
		
		while (match.getLives()>0 && match.getWin()==false) {
		
			UI.clearScreen();
			UI.printMatch(match.getChosenWord(), letraDescoberta, match.getLives(), match.getUsedLetters()); //Mostra o menu
			
			try {
				char letra = sc.next().charAt(0);
				
				//Checa se o usuário acertou alguma letra
				letraDescoberta = match.checkLetter(letra, letraDescoberta, match.getChosenWord()); 
				match.didYouWin(letraDescoberta);
				
			} catch (InputMismatchException err) {
				System.out.println("Por favor digite uma letra");
				
			}
			
			
		}
		
		System.out.println();
		if (match.getWin()) {
			UI.youWin();
			
		} else if (match.getLives()<=0) {
			UI.youLose();
			
		}
		
		sc.close();
	}

}
