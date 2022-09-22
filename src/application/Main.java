package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import game.Match;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean playGame = false;
		
		do { //Repete o jogo enquanto o usuário quiser continuar jogando 
			UI.clearScreen();
			Match match = chooseMode(sc);
			
			int length = match.getChosenWord().length;
			boolean[] letraDescoberta = new boolean[length];
			
			//Inicializa o vetor com tudo falso para o primeiro print da UI
			for (int i=0; i<length; i++) {
				letraDescoberta[i] = false; 
			}
			
			while (match.getLives()>0 && match.getWin()==false) {
				UI.clearScreen();
				gameplayLoop(match, letraDescoberta, sc);
				
			}
			
			//Mostra mensagem de fim de jogo
			if (match.getWin()) {
				UI.youWin();
				
			} else if (match.getLives()<=0) {
				UI.youLose(match.getChosenWord());
				
			}
		
			playGame = playAgain(sc); //Verifica se o usuário deseja jogar novamente
			
		} while (playGame);
		sc.close();
	}
	
	//Função para escolher o modo de jogo
	public static Match chooseMode(Scanner sc) {
		UI.chooseMode();
		Match match = new Match(); //Construtor genérico para ter a variável de antemão
		try {
			int mode = sc.nextInt();
			
			switch (mode) {
			
			case 1: 
				match = new Match("../words/normal.txt");
				break;
				
			case 2:
				match = new Match("../words/hard.txt");
				break;
				
			case 3:
				match = new Match("../words/ingles.txt");
				break;
				
			default:
				UI.error("Por favor escolha uma das 3 opções");
				match = chooseMode(sc);
			}
			
		} catch (InputMismatchException err) {
			//Jogo encerra pois houve erro ao tentar repetir este pedaço
			System.out.println("Jogo encerrado");
			System.exit(0);
		}
		
		return match;
	}

	public static void gameplayLoop(Match match, boolean[] letraDescoberta, Scanner sc) {
		//Mostra o menu
		UI.printMatch(match.getChosenWord(), letraDescoberta, match.getLives(), match.getUsedLetters()); 
		
		try {
			char letra = sc.next().charAt(0);
			
			if (Character.isDigit(letra)) { //Checa se o valor é um número
				UI.error("Por favor digite uma letra");
				gameplayLoop(match, letraDescoberta, sc);
				
			} else {
				//Checa se o usuário acertou alguma letra
				letraDescoberta = match.checkLetter(letra, letraDescoberta, match.getChosenWord()); 
				match.didYouWin(letraDescoberta);
				
			}
			
		} catch (InputMismatchException err) {
			//Jogo encerra pois houve erro ao tentar repetir este pedaço
			System.out.println("Jogo encerrado");
			System.exit(0);
			
		}
	}
	
	public static boolean playAgain(Scanner sc) {
		boolean playGame = false;
		
		UI.playAgain();
		char again = sc.next().charAt(0);
		
		if (again=='S' || again=='s') {
			playGame = true;
			
		} else if (again=='N' || again=='n') {
			playGame = false;
			
		} else {
			UI.error("Por favor digite uma das 2 opções");
			playGame=playAgain(sc);
		}
		
		return playGame;
	}

}
