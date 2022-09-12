package game;

public class Match {

	private Words words; //objeto da Classe Words
	private String chosenWord;

	//Construtor genérico
	public Match() {
		
	}
	
	//Construtor
	public Match(String file) {
		this.words = new Words(file);
		this.chosenWord = randomWord();
	}
	
	//Escolhe uma palavra aleatória da lista
	public String randomWord() {
		
		//Gera uma posição aleatória na lista
		int randomPosition = (int)(Math.random()*words.getWordsList().size()); 
		
		String word = words.getWordsList().get(randomPosition); //Palavra escolhida
		return word;
	}

	@Override
	public String toString() {
		return chosenWord;
	}
	
}
