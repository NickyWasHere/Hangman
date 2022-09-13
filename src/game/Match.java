package game;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Match {

	private Words words; //objeto da Classe Words
	private char[] chosenWord;
	private List<Character> usedLetters;
	private int lives;
	private boolean win;

	//Construtor genérico
	public Match() {
		
	}
	
	//Construtor
	public Match(String file) {
		this.words = new Words(file);
		this.chosenWord = randomWord();
		this.usedLetters = new ArrayList<>();
		this.lives = 5;
		this.win = false;
	}
	
	public Words getWords() {
		return words;
	}

	public char[] getChosenWord() {
		return chosenWord;
	}
	
	public List<Character> getUsedLetters() {
		return usedLetters;
	}
	
	public int getLives() {
		return lives;
	}
	
	public boolean getWin() {
		return win;
	}

	//Escolhe uma palavra aleatória da lista
	public char[] randomWord() {
		
		//Gera uma posição aleatória na lista
		int randomPosition = (int)(Math.random()*words.getWordsList().size()); 
		
		String word = words.getWordsList().get(randomPosition); //Palavra escolhida
		
		return convertWord(word);
	}
	
	//Método auxiliar do método randomWord
	public char[] convertWord(String word) {
		
		//Retira todo acento e converte letras maiúsculas para minúsculas
		word = Normalizer.normalize(word, Normalizer.Form.NFKD);
		word = word.replaceAll("[^\\p{ASCII}]", "");
		word = word.toLowerCase();
		
		//Transforma a String em vetor de caracteres
		char[] letras = word.toCharArray();
		
		return letras;
	}
	
	public boolean[] checkLetter(char letra, boolean[] letraDescoberta, char[] letras) {
		//vetor booleano para verificar se alguma letra já foi descoberta
		int cont = 0;
		int contErro = 0; 
		
		checkRepeat(letra);
		
		//Checa cada letra da palavra vendo se alguma bate com a digitada pelo usuário
		for (char c : letras) {
			if (letra==c) {
				letraDescoberta[cont] = true;
				usedLetters.add(letra);
				
			} else {
				contErro++; //Soma quantas vezes o usuário errou uma das letras
			}
			cont++;
		}
		
		checkError(contErro, letras);
		
		return letraDescoberta;
	}
	
	//Método auxiliar ao checkLetter
	public void checkError(int contErro, char[] letras) {	
		//Se nenhuma letra da palavra bate com a letra digitada
		if (contErro>=letras.length) {
			lives--;
			
		}
		
	}
	
	public void checkRepeat (char letra) {
		boolean repeat = false; //Variável auxiliar pra testar se uma letra já foi usada
		
		for (Character c : usedLetters) {
			if (letra==c) {
				repeat = true;
			}
		}
		
		//Retira uma vida caso o usuário digite uma letra já usada
		if (repeat) {
			lives--;
		}
	}
	
	public void didYouWin(boolean[] letraDescoberta) {
		int contAcerto = 0;
		
		//Verifica se o usuário acertou todas as letras
		for (int i=0; i<letraDescoberta.length; i++) {
			if (letraDescoberta[i]) {
				contAcerto++;
			}
		}
		
		if (contAcerto>=letraDescoberta.length) {
			win = true;
		}
	}
	
}
