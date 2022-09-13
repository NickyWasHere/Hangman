package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Words {

	private File file;
	private Scanner read;
	private List<String> wordsList = new ArrayList<>();
	
	//Construtor genérico
	public Words() {
		
	}
	
	//Construtor
	public Words(String file) {
		this.file = new File(file);
		try {
			this.read = new Scanner(this.file);
		} catch (FileNotFoundException err) {
			System.out.println("File not found");
		}
		this.wordsList = readFile();
	}
	
	public File getFile() {
		return file;
	}

	public List<String> getWordsList() {
		return wordsList;
	}

	//Lê o arquivo e salva cada linha numa lista wordsList
	public List<String> readFile() {
		while (read.hasNextLine()) { //Salva as palavras na lista auxiliar
			String word = read.nextLine();
			wordsList.add(word);
		}
		read.close();
		return wordsList;
	}
}
