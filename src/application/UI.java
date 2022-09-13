package application;

import java.util.List;
import java.util.stream.Collectors;

public class UI {

	//Limpa a tela
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		
	}
	
	//Mostra o menu para o usuário (Pela primeira vez)
	public static void printMatch(char[] letras, boolean[] letraDescoberta, int lives, List<Character> usedLetters) {
		char[] word = printLetters(letras, letraDescoberta);
		//Impede que seja mostrado mais de uma vez a mesma letra nas letras usadas
		String used = usedLetters.stream().distinct().collect(Collectors.toList()).toString();
		
		System.out.println();
		System.out.println("===========================================");
		System.out.println("               Jogo da Forca               ");
		System.out.println("===========================================");
		System.out.println();
		
		System.out.print("Palavra: ");
		System.out.println(word);
		
		System.out.println();
		System.out.println("Vidas: " + lives);
		System.out.print("Letras usadas: ");
		System.out.println(used.toString()); //Imprime as letras usadas
		
		System.out.println("===========================================");
		System.out.println();
		System.out.print("Digite uma letra: ");
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
		System.out.println("===========================================");
		System.out.println("         Parabéns, você venceu! :)         ");    
		System.out.println("              Jogo encerrado               ");
		System.out.println("===========================================");	
	}
	
	public static void youLose() {
		System.out.println("===========================================");
		System.out.println("          Suas vidas acabaram! :(          ");    
		System.out.println("              Jogo encerrado               ");
		System.out.println("===========================================");	
	}
}
