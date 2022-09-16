package application;

import java.util.List;
import java.util.stream.Collectors;

public class UI {

	//Cores
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";
	
	//Limpa a tela
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		
	}
	
	//Escolhe o modo de jogo
	public static void chooseMode() {		
		String titulo = ANSI_GREEN + "Jogo da Forca" + ANSI_RESET;
		
		System.out.println();
		System.out.println("===========================================");
		System.out.println("               " + titulo);
		System.out.println("===========================================");
		System.out.println();
		
		System.out.println(" Digite o número do modo que desenha jogar ");
		System.out.println();
		System.out.println(ANSI_YELLOW + "1 - Normal");
		System.out.println("2 - Difícil");
		System.out.println("3 - Inglês" + ANSI_RESET);
		System.out.println();
		
		System.out.println("===========================================");
		System.out.println();
		System.out.print(ANSI_GREEN + "Escolha sua opção: " + ANSI_RESET);
	}
	
	//Mostra o menu para o usuário (Pela primeira vez)
	public static void printMatch(char[] letras, boolean[] letraDescoberta, int lives, List<Character> usedLetters) {
		char[] word = printLetters(letras, letraDescoberta);
		
		//Impede que seja mostrado mais de uma vez a mesma letra nas letras usadas
		String used = usedLetters.stream().distinct().collect(Collectors.toList()).toString();
		
		String titulo = ANSI_GREEN + "Jogo da Forca" + ANSI_RESET;
		
		System.out.println();
		System.out.println("===========================================");
		System.out.println("               " + titulo);
		System.out.println("===========================================");
		System.out.println();
		
		System.out.print(ANSI_YELLOW + "Palavra: " + ANSI_RESET);
		System.out.println(word);
		
		System.out.println();
		System.out.println(ANSI_GREEN + "Vidas: " + ANSI_RESET + lives);
		System.out.print(ANSI_YELLOW + "Letras usadas: " + ANSI_RESET);
		System.out.println(used.toString()); //Imprime as letras usadas
		
		System.out.println("===========================================");
		System.out.println();
		System.out.print(ANSI_GREEN + "Digite uma letra: " + ANSI_RESET);
	}
	
	public static char[] printLetters(char[] letras, boolean[] letraDescoberta) {
		//Vetor auxiliar para printar a palavra
		char[] aux = new char[letras.length];
		
		//Checa se alguma letra foi descoberta e a mostra se tiver sido descoberta
		for (int i=0; i<letras.length; i++) {
			if (letraDescoberta[i]) {
				aux[i] = letras[i];
			} else {
				aux[i] = '*';
			}
		}
		
		return aux;
	}
	
	public static void youWin() {
		clearScreen();
		
		System.out.println();
		System.out.println("===========================================");
		System.out.println();
		System.out.println(ANSI_GREEN + "         Parabéns, você venceu! :)");    
		System.out.println("              Jogo encerrado" + ANSI_RESET);
	}
	
	public static void youLose(char[] chosenWord) {
		clearScreen();
		
		System.out.println();
		System.out.print(ANSI_YELLOW + "A palavra era: " + ANSI_RESET);
		for (char c : chosenWord) {
			System.out.print(c);
		}
		
		System.out.println();
		System.out.println("===========================================");
		System.out.println();
		System.out.println(ANSI_GREEN + "          Suas vidas acabaram! :(");    
		System.out.println("              Jogo encerrado" + ANSI_RESET);
	}
	
	public static void playAgain() {
		System.out.println();
		System.out.println("===========================================");
		System.out.println("       Gostaria de jogar novamente?        ");    
		System.out.println("                  (S/N)                    ");
		System.out.println("===========================================");	
		
		System.out.println();
		System.out.print(ANSI_GREEN + "Digite sua resposta: " + ANSI_RESET);
	}
	
	public static void error(String mensagem) {
		clearScreen();
		System.out.println();
		System.out.print(ANSI_RED + "ERRO: " + ANSI_RESET);
		System.out.println(mensagem);
	}
}
